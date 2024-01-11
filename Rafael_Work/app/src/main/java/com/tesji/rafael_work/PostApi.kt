package com.tesji.rafael_work

import retrofit2.http.GET

interface PostApi {
    @GET("empleados")
    suspend fun getUserPost():ArrayList<PostModel>
}