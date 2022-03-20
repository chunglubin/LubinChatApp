package com.lubin.lubinchatapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.lubin.lubinchatapp.databinding.ActivitySearchBinding

class SearchActivity : AppCompatActivity() {
    private val TAG=SearchActivity::class.java.simpleName
    lateinit var binding: ActivitySearchBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d(TAG, "onCreate: ")
        //val search2=intent.getStringExtra("Anchor Name")

    }
    fun searchBT2(view:View){
        //val search2=binding.idSearch2.text.toString()
        val search2=intent.getStringExtra("Your Anchor Name")
        Log.d(TAG, "I search this anchor is $search2, I'm so happy.")
        binding.idSearchFirst.inputType
    }
    /*fun personBT(view: View){
        var person=binding.idPerson.text.toString()
        Intent(this,LogInActivity::class.java).apply {
            putExtra("Your Anchor name",person)
            startActivity(this)
        } //當按下searchBT按鈕,intent物件轉換至PersonActivity(增加可讀性)
    }*/
}