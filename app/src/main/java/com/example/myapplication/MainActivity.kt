package com.example.myapplication

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.koneksi.ApiEndPoint
import com.example.myapplication.koneksi.retofit
import com.example.myapplication.model.ArrayUser
import com.example.myapplication.model.ObjectUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(),AdapterList.OnAdapterListener {
    lateinit var binding : ActivityMainBinding
    //panggil koneksi retofit
    var apiInterface: ApiEndPoint? = retofit().getApiClient()
    //panggil adapter list
    lateinit var adapter: AdapterList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //membuat onlick
        binding.imgTambah.setOnClickListener {
            //pindah ke halaman add data
            startActivity(Intent(this@MainActivity,AddData::class.java))
        }

    }

    override fun onResume() {
        super.onResume()
        tampil()
    }
    override fun onDelete(id: String) {
        AlertDialog.Builder(this@MainActivity)
            .setTitle("Delete")
            .setMessage("Apakah Kamu Yakin Hapus Data Ini?")
            .setPositiveButton("Ya") { dialogInterface, i ->
                hapus(id)
                dialogInterface.dismiss()
            }
            .setNegativeButton("No") { dialogInterface, i ->
                dialogInterface.dismiss()
            }
            .show()
    }

    override fun onDetail(id: String) {
        val pindah = Intent(this@MainActivity,DetailData::class.java)
        pindah.putExtra("id_crud",id)
        startActivity(pindah)
    }

    override fun onEdit(id: String) {
        val pindah = Intent(this@MainActivity,EditData::class.java)
        pindah.putExtra("id_crud",id)
        startActivity(pindah)
    }
    fun tampil(){
        //tampil data
        apiInterface!!.TampilData().enqueue(object : Callback<ArrayUser> {
            override fun onResponse(
                call: Call<ArrayUser>, response: Response<ArrayUser>) {
                if (response.isSuccessful){
                    val result = response.body()!!.data
                    val linearLayoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
                    linearLayoutManager.scrollToPositionWithOffset(0, 0)
                    adapter = AdapterList(result!!,this@MainActivity)
                    binding.rvTampil.layoutManager = linearLayoutManager
                    binding.rvTampil.adapter = adapter
                    binding.rvTampil.setHasFixedSize(true)

                }
            }
            override fun onFailure(call: Call<ArrayUser>, t: Throwable) {
                Toast.makeText(this@MainActivity,t.toString(), Toast.LENGTH_LONG).show()
            }
        })
    }
    fun hapus(id : String){
        //koding retrofit untuk edit data yang jalur dari class ApiEndPoint -> HapusData -> Ke API hapus.php
        apiInterface!!.HapusData(id).enqueue(object :Callback<ObjectUser> {
            override fun onResponse(call: Call<ObjectUser>, response: Response<ObjectUser>) {
                if (response.isSuccessful){
                    if(response.body()!!.success == 1){
                        Toast.makeText(this@MainActivity,"Berhasil Hapus",Toast.LENGTH_LONG).show()
                        //tutup halaman
                        tampil()
                    }else{
                        Toast.makeText(this@MainActivity,"Gagal Hapus",Toast.LENGTH_LONG).show()
                    }
                }else{
                    Toast.makeText(this@MainActivity,"Gagal Hapus, Terjadi Kesalahan Pada API",Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<ObjectUser>, t: Throwable) {
                Toast.makeText(this@MainActivity,t.toString(),Toast.LENGTH_LONG).show()
            }
        })
    }
}