package com.tesji.care

import android.telecom.Call
import retrofit2.http.GET

interface ConsumirApi {
    @GET("empleados")
    fun getTraer(): retrofit2.Call<empleados>
}