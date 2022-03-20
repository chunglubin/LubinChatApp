package com.lubin.lubinchatapp

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import com.lubin.lubinchatapp.databinding.FragmentFirstBinding
import com.google.gson.Gson
import okhttp3.*
import okio.ByteString
import java.util.concurrent.TimeUnit

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class FirstFragment : Fragment() {//login system登入系統
//lateinit var binding: FragmentFirstBinding
var remember=false
    var _binding: FragmentFirstBinding? =null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pref=requireContext().getSharedPreferences("Login",Context.MODE_PRIVATE)
        val checked=pref.getBoolean("Rem_userid",remember)
        checked.also {
            binding.idRemember.isChecked=it
        }
        binding.idRemember.setOnCheckedChangeListener { compoundButton, checked ->
            remember=checked
            pref.edit().putBoolean("Rem_USERID",remember).apply()
            if (!checked){
                pref.edit().putString("user","").apply()
            }
        }
        val prefuser=pref.getString("user","")
        if(prefuser!="")binding.inputId.setText(prefuser)
        binding.idButtonLogin.setOnClickListener {
            val userid=binding.inputId.text.toString()
            val password=binding.inputPassword.text.toString()
            if (userid=="lubinflower"&&password=="20220321"){
                //Please input 4-20 number or character
                if(remember){//如果帳號被記得的話2022.3.17
                    pref.edit()
                        .putString("user",userid)
                        .putInt("Level",2)
                        .apply()
                }
                findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
            }else{
                AlertDialog.Builder(requireContext())//顯示登入錯誤的訊息
                    .setTitle("Login failure")
                    .setMessage("Please try again to input your id & password")
                    .setPositiveButton("okay",null)
                    //.setNeutralButton("Back to the head page",null)
                    .show()
            }
            //indNavController().navigate(R.id.action_SecondFragment_to_RoomFragment)
        }
        binding.idButtonRegister2.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SignUpFragment)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}