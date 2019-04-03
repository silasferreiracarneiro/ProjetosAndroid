package com.example.instagram.ui.friendprofile

import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.instagram.model.Posting
import com.example.instagram.R
import com.nostra13.universalimageloader.core.ImageLoader
import com.nostra13.universalimageloader.core.assist.FailReason
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener
import kotlinx.android.synthetic.main.grid_posting.view.*

class FriendProfileAdapter : BaseAdapter {
    var postagens = ArrayList<Posting>()
    var context: Context? = null

    constructor(context: Context, foodsList: ArrayList<Posting>) : super() {
        this.context = context
        this.postagens = foodsList
    }

    override fun getCount(): Int {
        return postagens.size
    }

    override fun getItem(position: Int): Any {
        return postagens[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var posting = postagens[position]

        var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        var viewHolder = inflator.inflate(R.layout.grid_posting, null)

        var loader = ImageLoader.getInstance()

        loader.displayImage(posting.pathPhoto, viewHolder.imageGridProfile, object: ImageLoadingListener {
            override fun onLoadingComplete(imageUri: String?, view: View?, loadedImage: Bitmap?) {
                viewHolder?.progressBarGrid?.visibility = View.GONE
            }

            override fun onLoadingStarted(imageUri: String?, view: View?) {
                viewHolder?.progressBarGrid?.visibility = View.VISIBLE
            }

            override fun onLoadingCancelled(imageUri: String?, view: View?) {
                viewHolder?.progressBarGrid?.visibility = View.GONE
            }

            override fun onLoadingFailed(imageUri: String?, view: View?, failReason: FailReason?) {
                viewHolder?.progressBarGrid?.visibility = View.GONE
            }
        })

        return viewHolder
    }
}