package com.ruslan.huseynov.foodninja.ui.viewmodel

import android.view.View
import androidx.lifecycle.ViewModel
import com.ruslan.huseynov.foodninja.data.repository.FoodRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val frepo : FoodRepository) : ViewModel() {

    fun login(email : String,password : String,view : View) = CoroutineScope(Dispatchers.Main).launch{
        frepo.login(email,password,view)
    }

    fun resetPassword(email: String,view: View) = CoroutineScope(Dispatchers.Main).launch{
        frepo.resetPassword(email,view)
    }
}
