package com.lubin.lubinchatapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.lubin.lubinchatapp.databinding.ActivityPickBinding

class PickActivity : AppCompatActivity() {
    lateinit var binding: ActivityPickBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityPickBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}