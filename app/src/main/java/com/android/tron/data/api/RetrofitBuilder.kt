package com.android.tron.data.api

import com.osmanboy.mymovie.api.ApiService
import com.osmanboy.mymovie.api.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitBuilder {


    private val mRetrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun apiService(): ApiService {
        return mRetrofit.create(ApiService::class.java)
    }

    companion object {
        private var mInstance: RetrofitBuilder? = null

        fun getInstance(): RetrofitBuilder {
            mInstance?.let {
                return it
            }
            val newInstance = RetrofitBuilder()
            mInstance = newInstance
            return newInstance
        }


    }


}