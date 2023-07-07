package com.example.myapplication.koneksi

import com.example.myapplication.model.ArrayUser
import com.example.myapplication.model.ObjectUser
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiEndPoint {

    @GET("tampil.php")
    fun TampilData(): Call<ArrayUser>

    @GET("cari.php")
    fun CariData(
        @Query("id_crud") id_crud: String
    ): Call<ObjectUser>


    @FormUrlEncoded
    @POST("simpan.php")
    fun TambahData(
        @Field("nama") nama: String,
        @Field("jenis_kelamin") jenis_kelamin: String,
        @Field("nomor_hp") nomor_hp: String
    ): Call<ObjectUser>

    @FormUrlEncoded
    @POST("edit.php")
    fun EditData(
        @Field("id") id_crud: String,
        @Field("nama") nama: String,
        @Field("jenis_kelamin") jenis_kelamin: String,
        @Field("nomor_hp") nomor_hp: String
    ): Call<ObjectUser>

    @FormUrlEncoded
    @POST("hapus.php")
    fun HapusData(
        @Field("id") id_crud: String
    ): Call<ObjectUser>
}