package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityDetailDataBinding

class DetailData : AppCompatActivity() {
    lateinit var binding: ActivityDetailDataBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  ActivityDetailDataBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //membuat onclick
        binding.btKeluar.setOnClickListener {
            //tutup halaman
            finish()
        }
    }
}