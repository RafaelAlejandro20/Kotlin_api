package com.tesji.rafael_work

import com.google.gson.annotations.SerializedName

data class PostModelResponse (
    @SerializedName("Id")
    var Id:Int,
    @SerializedName("Nombre")
    var Nombre:String
)