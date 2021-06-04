package com.kakapo.screeningtest1.model.service

import com.kakapo.screeningtest1.model.GuestData
import retrofit2.Call
import retrofit2.http.GET

interface GuestService {
    @GET("596dec7f0f000023032b8017")
    fun getGuest() : Call<List<GuestData>>
}