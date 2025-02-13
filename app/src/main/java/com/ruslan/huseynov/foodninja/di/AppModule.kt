package com.ruslan.huseynov.foodninja.di

import com.ruslan.huseynov.foodninja.data.datasource.FoodDataSource
import com.ruslan.huseynov.foodninja.data.repository.FoodRepository
import com.ruslan.huseynov.foodninja.retrofit.ApiUtils
import com.ruslan.huseynov.foodninja.retrofit.FoodsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun providesDataSource(fdao : FoodsDao) : FoodDataSource{

        return FoodDataSource(fdao)
    }

    @Provides
    @Singleton
    fun providesRepository(fds : FoodDataSource) : FoodRepository{

        return FoodRepository(fds)
    }

    @Provides
    @Singleton
    fun providesFoodDao() : FoodsDao{

        return ApiUtils.getFoodsDao()
    }
}