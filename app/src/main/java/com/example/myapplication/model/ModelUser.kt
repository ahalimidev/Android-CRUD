package com.example.myapplication.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ListUser {
    @SerializedName("id_crud")
    @Expose
    var id_crud: String? = null

    @SerializedName("nama")
    @Expose
    var nama: String? = null

    @SerializedName("jenis_kelamin")
    @Expose
    var jenis_kelamin: String? = null

    @SerializedName("nomor_hp")
    @Expose
    var nomor_hp: String? = null

}