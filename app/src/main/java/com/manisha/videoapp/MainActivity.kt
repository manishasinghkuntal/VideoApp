package com.manisha.videoapp

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.MediaController
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.manisha.videoapp.adapter.RecyclerAdapter
import com.manisha.videoapp.model.Video
import com.manisha.videoapp.model.VideosModel
import kotlinx.android.synthetic.main.activity_main.*
import java.nio.charset.Charset
import java.util.*
import androidx.recyclerview.widget.LinearLayoutManager
import android.util.DisplayMetrics
import android.view.View
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.core.content.ContextCompat


class MainActivity : AppCompatActivity() {
    var activity: MainActivity? = null
    private lateinit var adapter: RecyclerAdapter
    var videoView: VideoView? = null
    lateinit var storesListProjo: VideosModel
    var isFullScreen:Boolean=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
         videoView = findViewById(R.id.videoView1) as VideoView
        storesListProjo = Gson().fromJson(loadJSONFromAssets(),VideosModel::class.java)
        activity = this
     //   jsoncall()
payVideo(0,this,storesListProjo.videos.get(0).sources, videoView!!)
        setItems()

fullScreen.setOnClickListener(){
    if(isFullScreen==false) {
        fullScreen.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.fullscreen_exit))
        isFullScreen = true
        recyclerView.visibility = View.GONE
        val metrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(metrics)
        val params = videoView!!.getLayoutParams() as android.widget.RelativeLayout.LayoutParams
        params.width = metrics.widthPixels
        params.height = metrics.heightPixels
        params.leftMargin = 0
        videoView!!.setLayoutParams(params)
    }
    else if(isFullScreen==true ){
        fullScreen.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.fullscreen))
        isFullScreen = false

        recyclerView.visibility = View.VISIBLE
    }
}
    }

    fun setItems(){
        val dataList: ArrayList<Video> = ArrayList()
      var  gridLayoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = gridLayoutManager
        adapter = RecyclerAdapter(storesListProjo.videos as ArrayList<Video>,this,videoView!!)
        recyclerView.adapter = adapter
        adapter.notifyItemInserted(storesListProjo.videos.size-1)

    }
    fun payVideo(position: Int,context: Context,url:String,videoView: VideoView){
        videoView!!.stopPlayback();
        val mediaController = MediaController(context)
        mediaController.setAnchorView(videoView)
        Log.e("VideoList",url)
        val uri = Uri.parse(url)
        videoView?.setMediaController(mediaController)
        videoView?.setVideoURI(uri)
        videoView?.requestFocus()
        videoView?.start()

        videoView?.setOnPreparedListener { mp ->
            mp.setOnVideoSizeChangedListener { mp, width, height ->

                videoView!!.setMediaController(mediaController)
                mediaController.setAnchorView(videoView)
            }
        }
    }

//    fun jsoncall() {
//        try {
//            val obj = JSONObject(loadJSONFromAssets())
//         //   val status = obj.getString("status")
//          //  if (status == "Success") {
//                val videoArray = obj.getJSONArray("videos")
//                val videoList =
//                    ArrayList<HashMap<String, String>>()
//                var contact: HashMap<String, String>
//                for (i in 0 until videoArray.length()) {
//                    val contacts = videoArray.getJSONObject(i)
//                    val name = contacts.getString("subtitle")
//                    val number = contacts.getString("title")
//                    contact = HashMap()
//                    contact["name"] = name
//                    contact["number"] = number
//                    videoList.add(contact)
//                }
//
//            //}
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//    }

    private fun loadJSONFromAssets(): String? {
        val json: String
        json = try {
            val `is` = applicationContext.assets.open("videolist.json")
            val size = `is`.available()
            val buffer = ByteArray(size)
            `is`.read(buffer)
            `is`.close()
            String(buffer, Charset.forName("UTF-8"))
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
        return json
    }
}
