package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myapplication.databinding.ActivityEditDataBinding
import com.example.myapplication.koneksi.ApiEndPoint
import com.example.myapplication.koneksi.retofit
import com.example.myapplication.model.ArrayUser
import com.example.myapplication.model.ObjectUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditData : AppCompatActivity() {
    lateinit var binding: ActivityEditDataBinding
    //panggil koneksi retofit
    var apiInterface: ApiEndPoint? = retofit().getApiClient()

    var jenis_kelamin = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditDataBinding.inflate(layoutInflater)
        setContentView(binding.root)
        tampil_data()
        //membuat check radio
        binding.rgJenisKelamin.setOnCheckedChangeListener { _, _ ->
            if (binding.rgJenisKelaminL.isChecked){
                jenis_kelamin = "L"
            }else if (binding.rgJenisKelaminP.isChecked){
                jenis_kelamin = "P"
            }else{
                jenis_kelamin = ""
            }
        }
        //membuat onlick
        binding.btSimpan.setOnClickListener {
            //input data
            val nama_lengkap = binding.etNamaLengkap.text.toString()
            val nomor_hp = binding.etNomorHp.text.toString()
            //membuat kondisi data input tidak boleh kosong
            if(nama_lengkap.isEmpty()){
                binding.etNamaLengkap.error = "Nama Lengkap Kosong"
                binding.etNamaLengkap.requestFocus()
            }else if(nomor_hp.isEmpty()){
                binding.etNomorHp.error = "Nomor HP Kosong"
                binding.etNomorHp.requestFocus()
            }else if(jenis_kelamin == ""){
                Toast.makeText(this@EditData,"Jenis Kelamin Kosong", Toast.LENGTH_LONG).show()
            }else{
                edit(nama_lengkap,jenis_kelamin,nomor_hp)
            }
        }
    }
    fun tampil_data(){
        //memanggil data intent yang diperoleh dari adapter tampil data
        val id_crud = intent.getStringExtra("id_crud")
        //koding retrofit untuk detail data yang jalur dari class ApiEndPoint -> CariData -> Ke API cari.php
        apiInterface!!.CariData(id_crud.toString()).enqueue(object : Callback<ObjectUser> {
            override fun onResponse(call: Call<ObjectUser>, response: Response<ObjectUser>) {
                if (response.isSuccessful){
                    val result = response.body()!!.data!!

                    binding.etNamaLengkap.setText(result.nama)
                    if(result.jenis_kelamin == "L"){
                        binding.rgJenisKelaminL.isChecked = true
                    }else{
                        binding.rgJenisKelaminP.isChecked = true
                    }
                    binding.etNomorHp.setText(result.nomor_hp)
                }else{
                    Toast.makeText(this@EditData,"Gagal Tampil, Terjadi Kesalahan Pada API", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<ObjectUser>, t: Throwable) {
                Toast.makeText(this@EditData,t.toString(), Toast.LENGTH_LONG).show()
            }
        })
    }
    fun edit(nama_lengkap : String, jk : String, nomor_hp : String){
        //memanggil data intent yang diperoleh dari adapter tampil data
        val id_crud = intent.getStringExtra("id_crud")

        //koding retrofit untuk edit data yang jalur dari class ApiEndPoint -> EdiData -> Ke API edit.php
        //tambahdata ini membawak data yang telah diinput dari nama_lengkap,jenis_kelamin dan nomorhp dengan indentitas id_crud sebagai kata kunci edit
        apiInterface!!.EditData(id_crud.toString(),nama_lengkap,jk,nomor_hp).enqueue(object :Callback<ObjectUser> {
            override fun onResponse(call: Call<ObjectUser>, response: Response<ObjectUser>) {
                if (response.isSuccessful){
                    if(response.body()!!.success == 1){
                        Toast.makeText(this@EditData,"Berhasil Simpan",Toast.LENGTH_LONG).show()
                        //tutup halaman
                        finish()
                    }else{
                        Toast.makeText(this@EditData,"Gagal Simpan",Toast.LENGTH_LONG).show()
                    }
                }else{
                    Toast.makeText(this@EditData,"Gagal Simpan, Terjadi Kesalahan Pada API",Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<ObjectUser>, t: Throwable) {
                Toast.makeText(this@EditData,t.toString(),Toast.LENGTH_LONG).show()
            }
        })
    }
}