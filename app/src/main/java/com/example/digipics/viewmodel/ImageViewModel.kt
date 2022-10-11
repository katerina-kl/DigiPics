package com.example.digipics.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.digipics.models.Image
import com.example.digipics.repository.ImageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImageViewModel @Inject constructor(private val repository: ImageRepository) : ViewModel(){

    private val _response = MutableLiveData<Image>()
    val responseImage : LiveData<Image>
        get() = _response


    fun getAllImages(q: String) = viewModelScope.launch {
        repository.getImages(q).let { response ->

            if (response.isSuccessful){
                _response.postValue(response.body())
            }else{
                Log.d("tag", "getAllImages: Error: ${response.code()}")
            }

        }
    }

}