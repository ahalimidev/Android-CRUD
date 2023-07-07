package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.myapplication.databinding.ActivityDetailDataBinding
import com.example.myapplication.koneksi.ApiEndPoint
import com.example.myapplication.koneksi.retofit
import com.example.myapplication.model.ArrayUser
import com.example.myapplication.model.ObjectUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailData : AppCompatActivity() {
    lateinit var binding: ActivityDetailDataBinding
    //panggil koneksi retofit
    var apiInterface: ApiEndPoint? = retofit().getApiClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  ActivityDetailDataBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //membuat onclick
        binding.btKeluar.setOnClickListener {
            //tutup halaman
            finish()
        }
        tampil_data()
    }
    fun tampil_data(){
        //memanggil data intent yang diperoleh dari adapter tampil data
        val id_crud = intent.getStringExtra("id_crud")
        //koding retrofit untuk detail data yang jalur dari class ApiEndPoint -> CariData -> Ke API cari.php
        apiInterface!!.CariData(id_crud.toString()).enqueue(object : Callback<ObjectUser> {
            override fun onResponse(call: Call<ObjectUser>, response: Response<ObjectUser>) {
                if (response.isSuccessful){
                    val result = response.body()!!.data!!

                    binding.tvNamaLengkap.text = result.nama
                    if(result.jenis_kelamin == "L"){
                        binding.tvJenisKelamin.text = "Laki-laki"
                    }else{
                        binding.tvJenisKelamin.text = "Perempuan"
                    }
                    binding.tvNomorHp.text = result.nomor_hp
                }else{
                    Toast.makeText(this@DetailData,"Gagal Tampil, Terjadi Kesalahan Pada API", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<ObjectUser>, t: Throwable) {
                Toast.makeText(this@DetailData,t.toString(), Toast.LENGTH_LONG).show()
            }
        })
    }
}