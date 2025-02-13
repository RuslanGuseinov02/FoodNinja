package com.ruslan.huseynov.foodninja.data.entity

import com.google.gson.annotations.SerializedName

data class CRUDResult(

    @SerializedName("success")val success : Int,

    @SerializedName("message")val message : String,
)
