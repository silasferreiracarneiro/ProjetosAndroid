package com.silasferreira.youtube.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.silasferreira.youtube.R
import com.silasferreira.youtube.model.Items
import com.squareup.picasso.Picasso

class HomeAdapter(var listVideo: ArrayList<Items> = arrayListOf()): androidx.recyclerview.widget.RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): HomeViewHolder {
        var view = LayoutInflater.from(p0.context).inflate(R.layout.adapter_video, p0, false)
        return HomeViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listVideo.size
    }

    override fun onBindViewHolder(holder: HomeViewHolder, p1: Int) {
        var video = listVideo[p1]
        holder.title.text = video.snippet.title
        Picasso.get().load(video.snippet.thumbnails.high.url).into(holder.cover)
    }

    class HomeViewHolder(itemView: View): androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView){
        val title = itemView.findViewById<TextView>(R.id.textTitle)!!
        val cover = itemView.findViewById<ImageView>(R.id.imageCover)!!
    }
}