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
class ProfileViewModel @Inject constructor(private val frepo : FoodRepository) : ViewModel(){

    fun changePassword(email : String,view : View) = CoroutineScope(Dispatchers.Main).launch{
        frepo.changePassword(email,view)
    }

    fun deleteAccount(view : View) = CoroutineScope(Dispatchers.Main).launch{
        frepo.deleteAccount(view)
    }
}