package com.lubin.lubinchatapp

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.fragment.findNavController
import com.lubin.lubinchatapp.databinding.FragmentRoomBinding
import okhttp3.*

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
        Log.d(TAG, "messages=${messages}")
        //binding.recycler.adapter=messages
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var mediaController=MediaController(requireContext())
        mediaController.setAnchorView(binding.idVideo)

        val uri = "android.resource://"+requireContext().packageName+ "/"+R.raw.hime3
        val videoURI = Uri.parse(uri)
        //binding.idVideo.setMediaController(mediaController)
        binding.idVideo.setVideoURI(videoURI)
        //binding.idVideo.requestFocus()
        binding.idVideo.setOnPreparedListener{
            binding.idVideo.start()
        }


        val room=arguments?.getParcelable<Lightyear>("ROOM")
        Log.d(TAG, "room:${room?.stream_title}")
    //        Log.d(TAG, "room:${room?.stream_id}")
//        Log.d(TAG, "room:${room?.start_time}")
        //binding.idVideo.setVideoPath(context.getFileStreamPath("/data/data/com.lubin.bmi3"))
        val input_message=binding.inputMessage.text.toString()
        Log.d(TAG, "message=${input_message}")
        binding.idExit.setOnClickListener {
            findNavController().navigate(R.id.action_RoomFragment_to_SecondFragment)
        }

    }
//    private fun setupVideoView() {
//        val videoView = view?.findViewById<VideoView>(R.id.id_video)
//        videoView?.setVideoURI(Uri.parse("android.resource://"  +requireContext().packageName+ "/" + R.raw.hime3))
//        videoView?.start()
//
//        // hide media controller
//        videoView?.setMediaController(null)
//    }
}
