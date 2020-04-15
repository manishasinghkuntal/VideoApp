package com.manisha.videoapp.adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.VideoView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.manisha.videoapp.MainActivity
import com.manisha.videoapp.R
import com.manisha.videoapp.model.Video
import com.manisha.videoapp.model.VideosModel
import kotlinx.android.synthetic.main.video_card.view.*
import java.net.URL
import java.util.ArrayList

class RecyclerAdapter(val items: ArrayList<Video>, val context: Context,val videoView: VideoView) : RecyclerView.Adapter<RecyclerAdapter.PhotoHolder>()  {
    override fun onBindViewHolder(holder: RecyclerAdapter.PhotoHolder, position: Int) {
    holder.tvDesc.setText(items.get(position).description)
        holder.tvTitle.setText(items.get(position).title)

        Glide.with(context).load(items.get(position).thumb).error(R.drawable.loaderror).placeholder(R.drawable.loaderror).into(holder.imageView)
        holder.linearLayout.setOnClickListener(){
            playThisVideo(position,items.get(position).sources,videoView)
        }

    }
fun playThisVideo(position: Int,url: String,videoView: VideoView) {
    var mainActivity:MainActivity= MainActivity()
    mainActivity.payVideo(position,context,url,videoView)

}
    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.PhotoHolder {
      return  PhotoHolder(LayoutInflater.from(context).inflate(R.layout.video_card, parent, false))    }
    class PhotoHolder (view: View) : RecyclerView.ViewHolder(view) {
        // Holds the TextView that will add each animal to
        val imageView = view.itemImage
        val tvTitle = view.itemTitle
        val tvDesc = view.itemDescription
        val linearLayout=view.layout


    }
}
