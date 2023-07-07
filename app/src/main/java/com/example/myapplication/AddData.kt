package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myapplication.databinding.ActivityAddDataBinding
import com.example.myapplication.koneksi.ApiEndPoint
import com.example.myapplication.koneksi.retofit
import com.example.myapplication.model.ArrayUser
import com.example.myapplication.model.ObjectUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddData : AppCompatActivity() {
    lateinit var binding: ActivityAddDataBinding
    //panggil koneksi retofit
    var apiInterface: ApiEndPoint? = retofit().getApiClient()
    var jenis_kelamin = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //membuat check radio
        binding.rgJenisKelamin.setOnCheckedChangeListener { _, _ ->
            if (binding.rgJenisKelaminL.isChecked) {
                jenis_kelamin = "L"
            } else if (binding.rgJenisKelaminP.isChecked) {
                jenis_kelamin = "P"
            } else {
                jenis_kelamin = ""
            }
        }
        //membuat onlick
        binding.btSimpan.setOnClickListener {
            //input data
            val nama_lengkap = binding.etNamaLengkap.text.toString()
            val nomor_hp = binding.etNomorHp.text.toString()
            //membuat kondisi data input tidak boleh kosong
            if (nama_lengkap.isEmpty()) {
                binding.etNamaLengkap.error = "Nama Lengkap Kosong"
                binding.etNamaLengkap.requestFocus()
            } else if (nomor_hp.isEmpty()) {
                binding.etNomorHp.error = "Nomor HP Kosong"
                binding.etNomorHp.requestFocus()
            } else if (jenis_kelamin == "") {
                Toast.makeText(this@AddData, "Jenis Kelamin Kosong", Toast.LENGTH_LONG).show()
            } else {
                simpan(nama_lengkap,jenis_kelamin,nomor_hp)
            }
        }
    }
    fun simpan(nama_lengkap : String, jk : String, nomor_hp : String){
        //koding retrofit untuk tambah data yang jalur dari class ApiEndPoint -> TambahData -> Ke API simpan.php
        //tambahdata ini membawak data yang telah diinput dari nama_lengkap,jenis_kelamin dan nomorhp
        apiInterface!!.TambahData(nama_lengkap,jk,nomor_hp).enqueue(object : Callback<ObjectUser>{
            override fun onResponse(call: Call<ObjectUser>, response: Response<ObjectUser>) {
                if (response.isSuccessful){
                    if(response.body()!!.success == 1){
                        Toast.makeText(this@AddData,"Berhasil Simpan",Toast.LENGTH_LONG).show()
                        //tutup halaman
                        finish()
                    }else{
                        Toast.makeText(this@AddData,"Gagal Simpan",Toast.LENGTH_LONG).show()
                    }
                }else{
                    Toast.makeText(this@AddData,"Gagal Simpan, Terjadi Kesalahan Pada API",Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<ObjectUser>, t: Throwable) {
                Toast.makeText(this@AddData,t.toString(),Toast.LENGTH_LONG).show()
            }
        })
    }
}