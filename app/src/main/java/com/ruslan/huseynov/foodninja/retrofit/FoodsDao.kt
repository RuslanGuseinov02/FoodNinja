package com.ruslan.huseynov.foodninja.retrofit

import com.ruslan.huseynov.foodninja.data.entity.FoodsResult
import com.ruslan.huseynov.foodninja.data.entity.CRUDResult
import com.ruslan.huseynov.foodninja.data.entity.BasketResult
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface FoodsDao {

    @GET("yemekler/tumYemekleriGetir.php")
    suspend fun loadFoods() : FoodsResult // Bütün yeməkləri çəkmək üçün
    ////////////////////////////////////////////////////////////////////////////////////////////////
    @POST("yemekler/sepeteYemekEkle.php")
    @FormUrlEncoded
    suspend fun addBasket( // Səbətə yemək əlavə etmək üçün

        @Field("yemek_adi") foodName : String,
        @Field("yemek_resim_adi") foodPhotoName : String,
        @Field("yemek_fiyat") foodPrice : Int,
        @Field("yemek_siparis_adet") foodOrderAmount : Int,
        @Field("kullanici_adi") username : String

    ) : CRUDResult
    ////////////////////////////////////////////////////////////////////////////////////////////////
    @POST("yemekler/sepettekiYemekleriGetir.php")
    @FormUrlEncoded
    suspend fun getBasket( // Səbətdəki yeməkləri çəkmək üçün

        @Field("kullanici_adi") userName : String) : BasketResult
    ////////////////////////////////////////////////////////////////////////////////////////////////
    @POST("yemekler/sepettenYemekSil.php")
    @FormUrlEncoded
    suspend fun deleteBasket( // Səbətdəki yeməkləri silmək üçün

        @Field("sepet_yemek_id") basketId : Int,

        @Field("kullanici_adi") username : String

    ) : CRUDResult
}