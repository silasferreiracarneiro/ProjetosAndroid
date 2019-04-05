package com.example.instagram.ui.home.feed

import android.content.Intent
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.instagram.R
import com.example.instagram.model.Feed
import com.example.instagram.model.LikePolisting
import com.example.instagram.ui.comment.CommentActivity
import com.example.instagram.utils.AppConstants.Companion.ID_POSTING
import com.like.LikeButton
import com.like.OnLikeListener
import com.nostra13.universalimageloader.core.ImageLoader
import com.nostra13.universalimageloader.core.assist.FailReason
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener
import de.hdodenhof.circleimageview.CircleImageView
import javax.inject.Inject

class FeedAdapter @Inject constructor(var list: List<Feed>,
                                      var presenter: FeedContract.Presenter<FeedContract.View, FeedContract.Interactor>)
    : RecyclerView.Adapter<FeedAdapter.FeedViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_feed, parent, false)
        return FeedViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        var item = list[position]

        holder.nameUser.text = item.nameUser
        holder.txtCommentPhoto.text = item.description
        Glide.with(holder.itemView.context).load(item.pathPhotoUser).into(holder.photoUser)
        Glide.with(holder.itemView.context).load(item.pathPhotoPosting).into(holder.photoPosting)
        holder.progressBar.visibility = View.GONE

        holder.like.setOnLikeListener(object: OnLikeListener{
            override fun liked(p0: LikeButton?) {
                item.likes.add(LikePolisting(item.id, item.pathPhotoUser))
                presenter.saveFeed(item)
            }

            override fun unLiked(p0: LikeButton?) {
                var newLikes = arrayListOf<LikePolisting>()
                    item.likes.forEach {
                    if(it.userId != item.id)
                        newLikes.add(it)
                }
                item.likes.clear()
                item.likes = newLikes
                presenter.saveFeed(item)
            }
        })

        holder.likePhoto.text = "${item.likes.count()} curtidas"
        item.likes.forEach {
            holder.like.isLiked = it.userId == it.userId
        }

        holder.comment.setOnClickListener {
            var i = Intent(holder.itemView.context, CommentActivity::class.java)
            i.putExtra(ID_POSTING, item.id)
            holder.itemView.context.startActivity(i)
        }
    }

    class FeedViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val photoUser: CircleImageView = itemView.findViewById(R.id.imgUserSearch)
        val nameUser: TextView = itemView.findViewById(R.id.txtNameUser)
        val photoPosting: ImageView = itemView.findViewById(R.id.imgPostingDetail)
        val txtCommentPhoto: TextView = itemView.findViewById(R.id.txtComentPhoto)
        val progressBar: ProgressBar = itemView.findViewById(R.id.progressBarLoadPhotoDetail)
        val like: LikeButton = itemView.findViewById(R.id.likeButtonFeed)
        val comment: ImageView = itemView.findViewById(R.id.likeComment)
        val likePhoto: TextView = itemView.findViewById(R.id.txtLikePhoto)
    }
}