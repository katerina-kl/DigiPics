package com.example.digipics.api

import com.example.digipics.helper.Constants
import com.example.digipics.models.Image
import com.example.digipics.models.ImageResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(Constants.LIST_IMAGES)
    suspend fun getImages(@Query("q") page :String): Response<Image>
}