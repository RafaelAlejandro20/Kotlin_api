package com.tesji.rafael_work

import com.google.gson.annotations.SerializedName

data class PostModel (
    @SerializedName("Id")
    var Id:Int,
    @SerializedName("Nombre")
    var Nombre:String
)