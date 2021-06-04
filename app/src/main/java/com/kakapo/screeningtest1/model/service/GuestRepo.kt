package com.kakapo.screeningtest1.model.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object GuestRepo {

    fun create(): GuestService{
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://mocky.io/v2/")
            .build()
        return retrofit.create(GuestService::class.java)
    }
}