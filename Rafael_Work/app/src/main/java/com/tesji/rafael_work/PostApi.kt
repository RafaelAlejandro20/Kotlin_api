package com.tesji.rafael_work

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PostApi {
    @GET("empleados")
    suspend fun getUserPost():ArrayList<PostModel>
    @GET("buscar/{id}")
    suspend fun getUserPostById(@Path("id") id:String):Response<PostModel>
}