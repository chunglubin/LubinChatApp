package com.lubin.lubinchatapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.ImageView
import android.widget.SearchView
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.lubin.lubinchatapp.databinding.FragmentSecondBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.*
import okio.ByteString
import java.net.URL
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class SecondFragment : Fragment() {//首頁出現直播主的資料
    private lateinit var adapter: ChatRoomAdapter
    val viewModel by viewModels<ChatViewModel>()
    val chatRoom= listOf<ChatRoom>(
        ChatRoom("20220321","apple","welcome1"),
        ChatRoom("20220322","banana","welcome2"),
        ChatRoom("20220323","cherry","welcome3"),
        ChatRoom("20220324","orange","welcome4"),
        ChatRoom("20220325","apple","welcome5"),
        ChatRoom("20220326","apple","welcome6"),
        ChatRoom("20220327","apple","welcome7"),
        ChatRoom("20220328","apple","welcome8"),
        ChatRoom("20220329","apple","welcome9"),
        ChatRoom("20220330","apple","welcome10"),
        ChatRoom("20220331","apple","welcome11"),
        ChatRoom("20220401","apple","welcome12"),
        ChatRoom("20220402","apple","welcome13"),
        ChatRoom("20220403","apple","welcome14"),
        ChatRoom("20220404","apple","welcome15"),
        ChatRoom("20220405","apple","welcome16"),
    )
    val rooms= mutableListOf<Lightyear>()
    private var _binding: FragmentSecondBinding?= null

    // This property is only valid between onCreateView and
    // onDestroyView.
//    val rooms= mutableListOf<Lightyear>()

    val binding get() = _binding!!
    lateinit var websocket:WebSocket
    //val rooms= mutableListOf<Lightyear>()
    val TAG= FragmentSecondBinding::class.java.simpleName
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
        val client= OkHttpClient.Builder()
            .readTimeout(3, TimeUnit.SECONDS)
            .build()//source來源端
        val reqeust= Request.Builder()
            .url("wss://lott-dev.lottcube.asia/ws/chat/chat:app_test?nickname=Lubin")
            .build()//destination目的端

        websocket=client.newWebSocket(reqeust, object : WebSocketListener() {
            override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
                super.onClosed(webSocket, code, reason)
                Log.d(TAG, ":onClosed")
            }

            override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
                super.onClosing(webSocket, code, reason)
                Log.d(TAG, ":onClosing")
            }

            override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                super.onFailure(webSocket, t, response)
                Log.d(TAG, ":onFailure")
            }

            override fun onMessage(webSocket: WebSocket, text: String) {
                super.onMessage(webSocket, text)
                Log.d(TAG, ":onMessage $text")
            }

            override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
                super.onMessage(webSocket, bytes)
                Log.d(TAG, ":onMessage ${bytes.hex()}")
            }

            override fun onOpen(webSocket: WebSocket, response: Response) {
                super.onOpen(webSocket, response)
                Log.d(TAG, ":onOpen")
                //webSocket.send("Hello, my name is lubinflower.")
            }
        })
        binding.bSend.setOnClickListener {
            val message=binding.edMessage.text.toString()
            websocket.send(Gson().toJson(MessageSend("N",message)))
        }
//        binding.idSearch.setOnClickListener {
//            val intent=Intent(context,SearchActivity::class.java)
//            startActivity(intent)
//        }
        binding.idPerson.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
        binding.recycler.setHasFixedSize(true)
        binding.recycler.layoutManager=GridLayoutManager(requireContext(),2)
        adapter=ChatRoomAdapter()
        binding.recycler.adapter=adapter


        //viewModel
        viewModel.chatRooms.observe(viewLifecycleOwner){rooms->
            adapter.updateRooms(rooms)
        }
        viewModel.getAllRooms()
        //CoroutineScope(Dispatchers.IO)
        CoroutineScope(Dispatchers.IO).launch {
            val json=URL("https://api.jsonserve.com/J0kY56").readText()
            val msg=Gson().fromJson(json, Message::class.java)
            Log.d(TAG, "msg:${msg.body.text}")//test msg
        }
        /*thread {//Coroutine
            val json=URL("https://api.jsonserve.com/J0kY56").readText()
            val msg=Gson().fromJson(json, Message::class.java)
            Log.d(TAG, "msg:${msg.body.text}")//test msg
        }
        thread {//viewModel
            val json=URL("https://api.jsonserve.com/f868pa").readText()
            val chatRooms=Gson().fromJson(json, ChatRooms::class.java)
            Log.d(TAG, "rooms:${chatRooms.result.lightyear_list.size}")
            rooms.clear()
            rooms.addAll(chatRooms.result.lightyear_list)//fill list with new coming data
            activity?.runOnUiThread {
                adapter.notifyDataSetChanged()
            }
        }*/
    }
    inner class ChatRoomAdapter:RecyclerView.Adapter<ChatRoomViewHolder>(){
        val chatRooms= mutableListOf<Lightyear>()
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatRoomViewHolder {
            val view=layoutInflater.inflate(R.layout.row_chatroom,parent,false)
            return ChatRoomViewHolder(view)
        }

        override fun onBindViewHolder(holder: ChatRoomViewHolder, position: Int) {
            val lightYear=chatRooms[position]
            holder.host.setText(lightYear.stream_title)
            holder.title.setText(lightYear.nickname)
            Glide.with(this@SecondFragment)
                .load(lightYear.head_photo)
                .into(holder.headshot)
            holder.itemView.setOnClickListener {
                chatRoomClicked(lightYear)
            }
        }

        override fun getItemCount(): Int {
            return chatRooms.size//回傳陣列長度
        }
        fun updateRooms(rooms: List<Lightyear>){
            chatRooms.clear()
            chatRooms.addAll(rooms)
            notifyDataSetChanged()
        }
    }
    inner class ChatRoomViewHolder(view: View):RecyclerView.ViewHolder(view){//recycler回收的人
        val host=view.findViewById<TextView>(R.id.hostname)
        val title=view.findViewById<TextView>(R.id.chatroom_title)
        val headshot=view.findViewById<ImageView>(R.id.headshot)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    fun chatRoomClicked(lightyear: Lightyear){
        val bundle=Bundle().apply {
            putParcelable("room",lightyear)
        }
        findNavController().navigate(R.id.action_roomList_to_singleRoom,bundle)
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_roomlist, menu)
        val item = menu.findItem(R.id.app_bar_search)
        val searchView = item.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                return false
            }
        })
        super.onCreateOptionsMenu(menu, inflater)
    }
}