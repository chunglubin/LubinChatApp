package com.lubin.lubinchatapp

import android.content.Intent
import android.net.Uri
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

    lateinit var binding: FragmentRoomBinding
    private val TAG= FragmentRoomBinding::class.java.simpleName
    val messages = mutableListOf<String>("你好呀","哈哈 很棒","加油 你可以的","繼續保持","明天見")

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
        val room=arguments?.getParcelable<Lightyear>("ROOM")
        Log.d(TAG, "room:${room?.stream_title}")
        Log.d(TAG, "room:${room?.stream_id}")
        Log.d(TAG, "room:${room?.start_time}")
        binding.video.setVideoURI((Uri.parse("https://player.vimeo.com/video/653928650")))
    }
}
