package com.tesji.care

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PostApi {
    @GET("notas")
    suspend fun getUserPost():ArrayList<PostModel>
    @GET("buscar/{id}")
    suspend fun getUserById(@Path("id") id:String):Response<PostModel>
    @GET("insertar/{nombre}/{contenido}")
    suspend fun insertUser(@Path("nombre") nombre:String, @Path("contenido") contenido:String):Response<PostModel>
    @GET("editar/{id}/{nombre}/{contenido}")
    suspend fun editUser(@Path("id") id:String, @Path("nombre") nombre:String, @Path("contenido") contenido:String):Response<PostModel>
    @GET("eliminar/{id}")
    suspend fun deleteUser(@Path("id") id:String):Response<PostModel>
}