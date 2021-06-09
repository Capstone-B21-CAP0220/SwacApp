package com.capstone0220.swacapp.ui.utils

import retrofit2.Retrofit

class RetrofitAPI {
    private val url = "https://b21-cap0220v2.uc.r.appspot.com/"
    fun getRetroClientInstance() : Retrofit{
        return Retrofit.Builder()
            .baseUrl(url)
            .build()
    }
}