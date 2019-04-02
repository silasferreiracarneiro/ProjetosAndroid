package com.example.instagram.ui.filterphoto

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.instagram.R
import com.zomato.photofilters.utils.ThumbnailItem

class FilterPhotoAdapter(var listFilters: List<ThumbnailItem>): RecyclerView.Adapter<FilterPhotoAdapter.FilterPhotoVieHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterPhotoVieHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_filters, parent, false)
        return FilterPhotoAdapter.FilterPhotoVieHolder(view)
    }

    override fun getItemCount(): Int {
        return listFilters.count()
    }

    override fun onBindViewHolder(holder: FilterPhotoVieHolder, position: Int) {
        var item = listFilters[position]
        holder.name.text = item.filterName
        holder.photo.setImageBitmap(item.image)
    }

    class FilterPhotoVieHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val photo: ImageView = itemView.findViewById(R.id.imgPhotoFilter)
        val name: TextView = itemView.findViewById(R.id.txtFilter)
    }
}