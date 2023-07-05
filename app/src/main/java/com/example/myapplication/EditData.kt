package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myapplication.databinding.ActivityAddDataBinding
import com.example.myapplication.databinding.ActivityEditDataBinding

class EditData : AppCompatActivity() {
    lateinit var binding: ActivityEditDataBinding
    var jenis_kelamin = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
                //insert api
            }
        }
    }
}