package com.ruslan.huseynov.foodninja.data.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Basket(

    @SerializedName("sepet_yemek_id")val basketFoodId : String,

    @SerializedName("yemek_adi")val basketFoodName : String,

    @SerializedName("yemek_resim_adi")val basketFoodPhoto : String,

    @SerializedName("yemek_fiyat")val basketFoodPrice : String,

    @SerializedName("yemek_siparis_adet")val basketFoodOrderAmount : String,

    @SerializedName("kullanici_adi")val basketFoodUsername : String

) : Serializable
