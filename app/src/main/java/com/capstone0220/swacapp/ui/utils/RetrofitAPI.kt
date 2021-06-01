package com.capstone0220.swacapp.ui.utils

import retrofit2.Retrofit

class RetrofitAPI {
    private val url = "https://reqres.in"
    fun getRetroClientInstance() : Retrofit{
        return Retrofit.Builder()
            .baseUrl(url)
            .build()
    }
}