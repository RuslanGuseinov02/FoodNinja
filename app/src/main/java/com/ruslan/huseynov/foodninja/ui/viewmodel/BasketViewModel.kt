package com.ruslan.huseynov.foodninja.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ruslan.huseynov.foodninja.data.entity.Basket
import com.ruslan.huseynov.foodninja.data.repository.FoodRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BasketViewModel @Inject constructor(private val frepo : FoodRepository) : ViewModel() {

    val basketList = MutableLiveData<MutableList<Basket>>()

    val result = MutableLiveData(0.0)

    init {

        getBasket()
    }

    private fun getBasket() = CoroutineScope(Dispatchers.Main).launch{

        try {

            basketList.value = frepo.getBasket("ruslan")
        }catch (e : Exception){

            e.printStackTrace()
        }
    }

    fun deleteBaskte(basketId : Int,username : String) = CoroutineScope(Dispatchers.Main).launch{

        frepo.deleteBasket(basketId,username)
    }

    fun totalPrice(basketList : List<Basket>){

        var totalPrice = 0.0

        for (i in basketList){

            val price = (i.basketFoodPrice.toDouble() * 0.050) * i.basketFoodOrderAmount.toInt()
            totalPrice += price

            result.value = totalPrice
        }
    }
}
