package com.ruslan.huseynov.foodninja.data.entity

import com.google.gson.annotations.SerializedName

data class BasketResult(

    @SerializedName("sepet_yemekler")val basketList : MutableList<Basket>,

    @SerializedName("success")val success : Int
)
