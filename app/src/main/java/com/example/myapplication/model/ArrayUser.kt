package com.example.myapplication.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ResponUser {
    @SerializedName("success")
    @Expose
    var success: Int? = 0

    @SerializedName("pesan")
    @Expose
    var pesan: String? = null

    @SerializedName("data")
    @Expose
    var data: ArrayList<ListUser>? = null
}