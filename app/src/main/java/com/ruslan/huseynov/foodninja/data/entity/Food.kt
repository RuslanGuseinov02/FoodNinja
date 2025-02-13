package com.ruslan.huseynov.foodninja.data.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

open class Food (

    @SerializedName("yemek_adi") var foodName : String,

    @SerializedName("yemek_resim_adi")val foodPhotoName : String,

    @SerializedName("yemek_fiyat")val foodPrice : String,

    ) : Serializable