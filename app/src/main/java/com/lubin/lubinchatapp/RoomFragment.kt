package com.lubin.lubinchatapp

import android.content.Intent
import android.os.Binder
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.lubin.lubinchatapp.databinding.FragmentRoomBinding
import okhttp3.*
import okio.ByteString
import java.net.URL
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread

/**
 * A simple [Fragment] subclass.
 * Use the [RoomFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RoomFragment : Fragment() {
//    private lateinit var adapter: ChatRoomAdapter
//    val chatRoom_person= listOf<ChatRoom>(
//        ChatRoom("20220321","apple","welcome"),
//        ChatRoom("20220321","banana","welcome"),
//        ChatRoom("20220321","cherry","welcome"),
//        ChatRoom("20220321","orange","welcome"),
//    )
    val rooms= mutableListOf<Lightyear>()
    lateinit var websocket:WebSocket
    private val TAG= FragmentRoomBinding::class.java.simpleName
    lateinit var binding: FragmentRoomBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentRoomBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}
