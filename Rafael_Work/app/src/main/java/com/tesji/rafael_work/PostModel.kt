package com.tesji.rafael_work

import com.google.gson.annotations.SerializedName

data class PostModel (
    @SerializedName("id")
    var Id:Int,
    @SerializedName("title")
    var Nombre:String
)