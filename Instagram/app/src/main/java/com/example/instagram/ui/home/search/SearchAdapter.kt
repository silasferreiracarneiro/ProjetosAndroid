package com.example.instagram.ui.home.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.instagram.R
import com.example.instagram.model.User
import de.hdodenhof.circleimageview.CircleImageView

class SearchAdapter(var list: ArrayList<User>):
    RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_list_user_search, parent, false)
        return SearchViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        var user = list[position]
        holder.name.text = user.nameUser
        loadPhoto(user.photo, holder)
    }

    private fun loadPhoto(photo: String, holder: SearchViewHolder){
        if(photo != null && photo != ""){
            Glide.with(holder.itemView.context).load(photo).into(holder.photo)
        }else{
            holder.photo.setImageResource(R.drawable.padrao)
        }
    }

    class SearchViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val photo: CircleImageView = itemView.findViewById(R.id.imgUserSearch)
        val name: TextView = itemView.findViewById(R.id.txtNameUser)
    }
}