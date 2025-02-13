package com.ruslan.huseynov.foodninja.data.entity

import com.google.gson.annotations.SerializedName

data class FoodsResult(

    @SerializedName("yemekler")val foodsList : List<Food>,

    @SerializedName("success")val success : Int
)