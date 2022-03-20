package com.lubin.lubinchatapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import com.lubin.lubinchatapp.R
import com.lubin.lubinchatapp.databinding.FragmentSignUpBinding


/**
 * A simple [Fragment] subclass.
 * Use the [SignUpFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SignUpFragment : Fragment() {
    lateinit var binding: FragmentSignUpBinding
    val selectPictureFromGallery=
        registerForActivityResult(ActivityResultContracts.GetContent()){uri->
            uri?.let {
                binding.idPersonalPicture.setImageURI(it)
                uri.toString()
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentSignUpBinding.inflate(inflater)
        val s="Sky"
        val uri=Uri.parse(s)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.idPickPicture.setOnClickListener {
            pickFromGallery()
        }
        binding.idSubmit.setOnClickListener {
            val id_update=binding.editUserid.text.toString()
            val password_update=binding.editPassword.text.toString()
            if(id_update=="lubinflower"&&password_update=="20220321"){
                findNavController().navigate(R.id.action_SignUpFragment_to_FirstFragment)
            }
        }
    }
    private fun pickFromGallery(){
        selectPictureFromGallery.launch("image/*")
    }
}