package com.example.mohit_api_calling.Interface

import com.example.mohit_api_calling.Data.Flower_Data
import com.example.mohit_api_calling.Data.Flower_DataItem
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface FlowerApi {
    @GET("1d0d7935-3c14-41f0-aecb-3184090a5127")
    fun getFlower() : Call<List<Flower_DataItem>>
}