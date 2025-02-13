package com.ruslan.huseynov.foodninja.ui.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.ruslan.huseynov.foodninja.R
import com.ruslan.huseynov.foodninja.databinding.FragmentBasketBinding
import com.ruslan.huseynov.foodninja.ui.adapter.BasketAdapter
import com.ruslan.huseynov.foodninja.ui.viewmodel.BasketViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BasketFragment : Fragment(){

    private lateinit var binding: FragmentBasketBinding
    private lateinit var adapter : BasketAdapter
    private val viewModel: BasketViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View{
        binding = FragmentBasketBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvBasket.layoutManager = LinearLayoutManager(requireContext()) // Səbətdəki yeməklərin görüntüsü

        viewModel.basketList.observe(viewLifecycleOwner){

            // Səbetdəki yeməkləri gətirmək üçün
            adapter = BasketAdapter(it,viewModel)
            binding.rvBasket.adapter = adapter

            viewModel.totalPrice(it)

        }
        // Anlıq qiymətləri görmək üçün
        viewModel.result.observe(viewLifecycleOwner){
            binding.tvTotalPrice.text = "$it AZN"
            binding.tvOrders.text = "$it AZN"
        }

        // Səbət səhifəsindəki ödənişləri göstərmək üçün
        binding.ivBuy.setOnClickListener {
            binding.cardView.visibility = View.VISIBLE
        }

        // Səbət səhifəsindəki back button
        binding.cardViewBack.setOnClickListener {

            Navigation.findNavController(it).navigate(R.id.homeFragment2) //HomeFragment-a qayıdıb backStackdən basketFragmenti silmək üçün
            Navigation.findNavController(it).popBackStack(R.id.basketFragment2,true)
        }

        // Sifarişləri almaq üçün
        binding.btnOrder.setOnClickListener {

            if (viewModel.result.value == 0.0){
                //Səbət boş olduğu halda istifadəçi click edərsə
                Snackbar.make(it,"Your basket is empty",2000).show()
            }else{

                val alert = AlertDialog.Builder(requireContext())
                alert.setMessage("Do you confirm the order?")
                alert.setPositiveButton("Yes"){_,_ ->

                    Navigation.findNavController(it).navigate(R.id.action_sheet)
                    binding.cardView.visibility = View.GONE
                }

                alert.setNegativeButton("No"){dialog,_ ->

                    dialog.dismiss()
                }
                alert.show()
            }
        }
    }
}