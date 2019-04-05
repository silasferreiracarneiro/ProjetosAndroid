package com.example.instagram.ui.comment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.instagram.R
import com.example.instagram.model.Comment
import de.hdodenhof.circleimageview.CircleImageView

class CommentAdapter(var list: ArrayList<Comment>) : RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        var view  = LayoutInflater.from(parent.context).inflate(R.layout.adapter_comment, parent, false)
        return CommentViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        var item = list[position]

        holder.name.text = item.nameUser
        holder.comment.text = item.text

        if(item.pathPhotoUser != null && item.pathPhotoUser != ""){
            Glide.with(holder.itemView.context).load(item.pathPhotoUser).into(holder.photo)
        }else{
            holder.photo.setImageResource(R.drawable.padrao)
        }
    }

    class CommentViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val photo: CircleImageView = itemView.findViewById(R.id.logoImgeUserComment)
        val name: TextView = itemView.findViewById(R.id.txtNameUserComment)
        val comment: TextView = itemView.findViewById(R.id.txtComment)
    }
}