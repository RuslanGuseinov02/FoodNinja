package com.ruslan.huseynov.foodninja.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ruslan.huseynov.foodninja.data.entity.FakeFood
import com.ruslan.huseynov.foodninja.data.entity.Food
import com.ruslan.huseynov.foodninja.data.entity.Restaurant
import com.ruslan.huseynov.foodninja.data.repository.FoodRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(val frepo : FoodRepository) : ViewModel() {

    var foodList = MutableLiveData<List<Food>>()
    var fakeFoodList = MutableLiveData<List<FakeFood>>()
    var restaurantList = MutableLiveData<List<Restaurant>>()

    init {
        loadFoods()
        loadFakeFood()
        loadRestaurants()
    }

    private fun loadFoods() = CoroutineScope(Dispatchers.Main).launch{

        try {

            foodList.value = frepo.loadFoods()

        }catch (e : Exception){

            e.printStackTrace()
        }
    }

    fun searchFood(searchWord : String) = CoroutineScope(Dispatchers.Main).launch{
        foodList.value = frepo.searchFood(searchWord)
    }

    private fun loadFakeFood() = CoroutineScope(Dispatchers.Main).launch{

        try {
            fakeFoodList.value = frepo.loadFakeFood()
        }catch (e : Exception){
            e.printStackTrace()
        }
    }

    private fun loadRestaurants() = CoroutineScope(Dispatchers.Main).launch{

        try {
            restaurantList.value = frepo.loadRestaurants()
        }catch (e : Exception){
            e.printStackTrace()
        }
    }
}
