package com.example.digipics.repository

import com.example.digipics.api.ApiService
import javax.inject.Inject

class ImageRepository @Inject constructor(private val apiService: ApiService){

    suspend fun getImages(q :String) =apiService.getImages(q)

}