package com.ruslan.huseynov.foodninja.retrofit

class ApiUtils {

    companion object{

        private const val BASE_URL = "http://kasimadalan.pe.hu/"

        fun getFoodsDao() : FoodsDao{

            return RetrofitClient.getClient(BASE_URL).create(FoodsDao::class.java)
        }
    }
}