package com.ruslan.huseynov.foodninja.ui.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.ruslan.huseynov.foodninja.data.repository.FoodRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(private val frepo : FoodRepository) : ViewModel(){

    fun addBasket(foodName : String,foodPhotoName : String,foodPrice : Int,foodOrderAmount : Int,username : String) =

        CoroutineScope(Dispatchers.Main).launch {
            frepo.addBasket(foodName,foodPhotoName,foodPrice,foodOrderAmount,username)
        }

    fun phoneVibrate(context: Context) = CoroutineScope(Dispatchers.Main).launch{
        frepo.vibratePhone(context)
    }
}
