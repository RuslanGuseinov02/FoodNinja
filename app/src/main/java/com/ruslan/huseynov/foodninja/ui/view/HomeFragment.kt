package com.ruslan.huseynov.foodninja.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ruslan.huseynov.foodninja.R
import com.ruslan.huseynov.foodninja.data.entity.Restaurant
import com.ruslan.huseynov.foodninja.databinding.FragmentHomeBinding
import com.ruslan.huseynov.foodninja.ui.adapter.FakeFoodAdapter
import com.ruslan.huseynov.foodninja.ui.adapter.FoodAdapter
import com.ruslan.huseynov.foodninja.ui.adapter.RestaurantAdapter
import com.ruslan.huseynov.foodninja.ui.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var fakeAdapter : FakeFoodAdapter
    private lateinit var adapter : FoodAdapter
    private lateinit var restaurantAdapter : RestaurantAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View{
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // HomeFragmentdəki recycler viewlərin görüntüsü
        binding.rvrestaurant.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        binding.rvFake.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        binding.rv.layoutManager = GridLayoutManager(requireContext(), 2)

        // Restoranların logosun göstərmək üçün
        viewModel.restaurantList.observe(viewLifecycleOwner) {
            restaurantAdapter = RestaurantAdapter(it)
            binding.rvrestaurant.adapter = restaurantAdapter
        }
        // Yaşıl card içindəki yeməkləri göstərmək üçün
        viewModel.fakeFoodList.observe(viewLifecycleOwner) {
            fakeAdapter = FakeFoodAdapter(it)
            binding.rvFake.adapter = fakeAdapter
        }
        // APİ-dən gələn yeməkləri göstərmək üçün
        viewModel.foodList.observe(viewLifecycleOwner) {
            adapter = FoodAdapter(it)
            binding.rv.adapter = adapter
        }

        // Yeməkləri axtarmaq üçün
        binding.searchView.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {

                viewModel.searchFood(query)
                return true
            }
            override fun onQueryTextChange(newText: String): Boolean {

                viewModel.searchFood(newText)
                return true
            }
        })
    }
}