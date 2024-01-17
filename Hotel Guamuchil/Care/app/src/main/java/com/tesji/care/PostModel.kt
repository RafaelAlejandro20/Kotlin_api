package com.tesji.care

import com.google.gson.annotations.SerializedName

data class PostModel (
    @SerializedName("Id")
    var Id:Int,
    @SerializedName("Nombre")
    var Nombre:String,
    @SerializedName("Nota")
    var Nota:String,
    @SerializedName("Resultado")
    var Resultado:String
)