package com.capstone0220.swacapp.ui.utils

import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface APIService {
    @POST("/laporan")
    suspend fun createReport(@Body requestBody: RequestBody): Response<ResponseBody>
}