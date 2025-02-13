package com.ruslan.huseynov.foodninja.data.repository

import android.content.Context
import android.view.View
import com.ruslan.huseynov.foodninja.data.datasource.FoodDataSource
import com.ruslan.huseynov.foodninja.data.entity.FakeFood
import com.ruslan.huseynov.foodninja.data.entity.Food
import com.ruslan.huseynov.foodninja.data.entity.Restaurant
import javax.inject.Inject

class FoodRepository @Inject constructor(private val fds : FoodDataSource) {

    suspend fun loadFoods() : List<Food> = fds.loadFoods()

    suspend fun searchFood(searchWord : String) : List<Food> = fds.searchFood(searchWord)

    suspend fun addBasket(foodName : String,foodPhotoName : String,foodPrice : Int,foodOrderAmount : Int,username : String) = fds.addBasket(foodName,foodPhotoName,foodPrice,foodOrderAmount,username)

    suspend fun getBasket(username : String)  = fds.getBasket(username)

    suspend fun deleteBasket(basketId : Int,username : String) = fds.deleteBasket(basketId,username)

    suspend fun loadFakeFood() : List<FakeFood> = fds.loadFakeFood()

    suspend fun loadRestaurants() : List<Restaurant> = fds.loadRestaurants()

    suspend fun login(email : String,password : String,view : View) = fds.login(email,password,view)

    suspend fun resetPassword(email : String, view : View) = fds.resetPassword(email,view)

    suspend fun changePassword(email : String,view : View) = fds.changePassword(email,view)

    suspend fun signUp(email : String,password : String,view : View) = fds.signUp(email,password,view)

    suspend fun deleteAccount(view : View) = fds.deleteAccount(view)

    suspend fun vibratePhone(context: Context) = fds.vibratePhone(context)
}