package com.ruslan.huseynov.foodninja.data.datasource

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import android.view.View
import androidx.navigation.Navigation
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.ruslan.huseynov.foodninja.R
import com.ruslan.huseynov.foodninja.data.entity.Basket
import com.ruslan.huseynov.foodninja.data.entity.FakeFood
import com.ruslan.huseynov.foodninja.data.entity.Food
import com.ruslan.huseynov.foodninja.data.entity.Restaurant
import com.ruslan.huseynov.foodninja.retrofit.FoodsDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FoodDataSource(private val fdao : FoodsDao){

    private val auth = Firebase.auth

    suspend fun loadFoods() : List<Food> =

        withContext(Dispatchers.IO){

            return@withContext fdao.loadFoods().foodsList
        }

    suspend fun searchFood(searchWord : String) : List<Food> =

        withContext(Dispatchers.IO){

            val list = fdao.loadFoods()
            return@withContext list.foodsList.filter {

                it.foodName.contains(searchWord,ignoreCase = true)
            }
        }

    suspend fun addBasket(foodName : String,foodPhotoName : String,foodPrice : Int,foodOrderAmount : Int,username : String) =

        withContext(Dispatchers.IO){
            return@withContext fdao.addBasket(foodName,foodPhotoName,foodPrice,foodOrderAmount,username)
        }

    suspend fun getBasket(username : String) : MutableList<Basket> =

        withContext(Dispatchers.IO){
            return@withContext fdao.getBasket(username).basketList
        }

    suspend fun deleteBasket(basketId : Int,username : String) =

        withContext(Dispatchers.IO){
            return@withContext fdao.deleteBasket(basketId,username)
        }

    suspend fun loadFakeFood() : List<FakeFood> =

        withContext(Dispatchers.IO){

            val fakeFoodList = ArrayList<FakeFood>()

            val fakeFood1 = FakeFood("McDonald's","Big Mac Menü", 11.80, R.drawable.mcdonalds)
            val fakeFood2 = FakeFood("KFC","Bucket-L", 29.50, R.drawable.kfc)
            val fakeFood3 = FakeFood("Burger King","Big King Menü", 16.85, R.drawable.burgerking)

            fakeFoodList.add(fakeFood1)
            fakeFoodList.add(fakeFood2)
            fakeFoodList.add(fakeFood3)
            return@withContext fakeFoodList
        }

    suspend fun loadRestaurants() : List<Restaurant> =

        withContext(Dispatchers.IO){

            val restaurantList = ArrayList<Restaurant>()

            val r1 = Restaurant(R.drawable.mcdonaldslogo,"McDonald's")
            val r2 = Restaurant(R.drawable.kfclogo,"KFC")
            val r3 = Restaurant(R.drawable.dominoslogo,"Domino's")
            val r4 = Restaurant(R.drawable.pjlogo5,"Papa Johns")
            val r5 = Restaurant(R.drawable.bkogo,"Burger King")
            val r6 = Restaurant(R.drawable.sblogo,"Starbucks")
            val r7 = Restaurant(R.drawable.pelogo,"Popeyes")
            val r8 = Restaurant(R.drawable.kdlogo,"Kasap Döner")
            val r9 = Restaurant(R.drawable.kblogo,"Köşebaşı")
            val r10 = Restaurant(R.drawable.madologo,"Mado")
            val r11 = Restaurant(R.drawable.bhlogo,"Burger House")
            val r12 = Restaurant(R.drawable.pmlogo,"Pizza-Mizza")
            val r13 = Restaurant(R.drawable.nelogo,"Nusr-Et")

            restaurantList.add(r1)
            restaurantList.add(r2)
            restaurantList.add(r3)
            restaurantList.add(r4)
            restaurantList.add(r5)
            restaurantList.add(r6)
            restaurantList.add(r7)
            restaurantList.add(r8)
            restaurantList.add(r9)
            restaurantList.add(r10)
            restaurantList.add(r11)
            restaurantList.add(r12)
            restaurantList.add(r13)
            return@withContext restaurantList
        }

    suspend fun login(email : String,password : String,view : View) =

        withContext(Dispatchers.IO){

            if (email.isNotEmpty() && password.isNotEmpty()){

                auth.signInWithEmailAndPassword(email,password).addOnSuccessListener {

                    Navigation.findNavController(view).navigate(R.id.actionLoginToHome)
                }.addOnFailureListener {

                    Snackbar.make(view, it.localizedMessage!!,2000).show()
                }
            }else{

                Snackbar.make(view,"Enter Email and Password",2000).show()
            }
        }

    suspend fun resetPassword(email : String, view : View) =

        withContext(Dispatchers.IO){

            if (email.isNotEmpty()){

                auth.sendPasswordResetEmail(email).addOnSuccessListener {

                    Snackbar.make(view,"We sent a password reset email to your address",2000).show()
                }.addOnFailureListener {

                    Snackbar.make(view,it.localizedMessage!!,2000).show()
                }
            }else{

                Snackbar.make(view,"Enter Email",2000).show()
            }
        }

    suspend fun changePassword(email : String,view : View) =

        withContext(Dispatchers.IO){

            auth.sendPasswordResetEmail(email).addOnSuccessListener {

                Snackbar.make(view,"We sent a password reset email to your address",2000).show()

            }.addOnFailureListener {

                Snackbar.make(view,it.localizedMessage!!,2000).show()
            }
        }

    suspend fun signUp(email : String,password : String,view : View) =

        withContext(Dispatchers.IO){

            if (email.isNotEmpty() && password.isNotEmpty()) {

                auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener {

                    Snackbar.make(view, "Account Created", 2000).show()

                    Navigation.findNavController(view).navigate(R.id.actionCreateToHome)

                }.addOnFailureListener {

                    Snackbar.make(view, it.localizedMessage!!, 2000).show()
                }
            }else{

                Snackbar.make(view,"Enter Email and Password",2000).show()
            }
        }

    suspend fun deleteAccount(view : View) =

        withContext(Dispatchers.IO){

            val currentUser = auth.currentUser

            currentUser?.let {
                it.delete().addOnSuccessListener {

                    Navigation.findNavController(view).navigate(R.id.actionProfileToLogin)
                    Snackbar.make(view,"Account Deleted",3000).show()
                }.addOnFailureListener {
                    Snackbar.make(view,it.localizedMessage!!,2000).show()
                }
            }
        }

    suspend fun vibratePhone(context: Context) {

        withContext(Dispatchers.IO){

            val vibrator = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {

                val vibratorManager = context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
                vibratorManager.defaultVibrator
            } else {
                context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                vibrator.vibrate(VibrationEffect.createOneShot(40, VibrationEffect.DEFAULT_AMPLITUDE))
            } else {

                vibrator.vibrate(40)
            }
        }
    }
}
