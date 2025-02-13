package com.ruslan.huseynov.foodninja.ui.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.material.snackbar.Snackbar
import com.ruslan.huseynov.foodninja.R
import com.ruslan.huseynov.foodninja.databinding.FragmentDetailsBinding
import com.ruslan.huseynov.foodninja.ui.viewmodel.DetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    private var number = 1
    private val viewModel: DetailsViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View{
        binding = FragmentDetailsBinding.inflate(inflater,container,false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Food Adapterdən göndərilən məlumatları almaq üçün
        val bundle : DetailsFragmentArgs by navArgs()
        val food = bundle.food
        val convertedPrice = food.foodPrice.toDouble() *  0.050 // APİ-dən gələn qiyməti AZN-ə çevirmək üçün

        binding.tvNameDetails.text = food.foodName
        binding.tvPriceDetails.text = "$convertedPrice AZN"
        binding.btnAddToCart.text = "Add To Cart  (${convertedPrice} AZN)"

        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${food.foodPhotoName}" // Şəkillərin yükləndiyi URL
        Glide.with(requireContext()).load(url).diskCacheStrategy(DiskCacheStrategy.NONE).into(binding.ivDetails) // Şəkilləri yükləmək üçün

        binding.cardViewBack.setOnClickListener { // Details fragmentdəki back button

            Navigation.findNavController(it).navigate(R.id.homeFragment2)
            Navigation.findNavController(it).popBackStack(R.id.detailsFragment,true)
        }

        binding.ivPlus.setOnClickListener { // Details fragmentdə yemək sayını artırmaq üçün

            viewModel.phoneVibrate(requireContext())
            number++
            binding.tvPeaces.text = "$number"

            val totalPrice = convertedPrice * number
            binding.btnAddToCart.text = "Add To Cart  ($totalPrice AZN)"
        }

        binding.ivMinus.setOnClickListener { // Details fragmentdə yemək sayını azaltmaq üçün

            viewModel.phoneVibrate(requireContext())

            if (number > 1) {

                number--

                binding.tvPeaces.text = "$number"
                val totalPrice = convertedPrice * number

                binding.btnAddToCart.text = "Add To Cart  ($totalPrice AZN)"
            }
        }

        binding.btnAddToCart.setOnClickListener { // Səbətə əlavə et buttonu

            binding.btnAddToCart.isClickable = false
            binding.ivPlus.isClickable = false
            binding.ivMinus.isClickable = false

            viewModel.addBasket(food.foodName,food.foodPhotoName,food.foodPrice.toInt(),number,"ruslan")
            Snackbar.make(it,"${food.foodName} added to basket",2000).show()
        }
    }
}