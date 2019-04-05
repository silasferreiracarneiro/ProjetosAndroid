package com.example.instagram.ui.postingdetail

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.example.instagram.R
import com.example.instagram.model.Posting
import com.example.instagram.model.User
import com.example.instagram.utils.AppConstants.Companion.POSTING
import com.example.instagram.utils.AppConstants.Companion.USER_INTENT
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache
import com.nostra13.universalimageloader.core.ImageLoader
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration
import com.nostra13.universalimageloader.core.assist.FailReason
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener
import kotlinx.android.synthetic.main.adapter_feed.*
import kotlinx.android.synthetic.main.adapter_list_user_search.*
import kotlinx.android.synthetic.main.toolbar.*

class PostingDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_posting_detail)

        var posting = intent.extras.get(POSTING) as Posting
        var user = intent.extras.get(USER_INTENT) as User

        setSupportActionBar(toolbarHome)
        supportActionBar?.title = "Visualizar postagem"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close_black_24dp)

        Glide.with(this).load(user.photo).into(imgUserSearch)
        txtNameUser.text = user.nameUser

        var config = ImageLoaderConfiguration
            .Builder(this)
            .memoryCache(LruMemoryCache(2 * 1024 * 1024))
            .memoryCacheSize(2 * 1024 * 1024)
            .diskCacheSize(50 * 1024 * 1024)
            .diskCacheFileCount(100)
            .diskCacheFileNameGenerator(HashCodeFileNameGenerator())
            .build()

        ImageLoader.getInstance().init(config)
        loadPhotoDetailt(posting.pathPhoto)
    }

    private fun loadPhotoDetailt(pathPhoto: String){

        val loader = ImageLoader.getInstance()
        loader.displayImage(pathPhoto, imgPostingDetail, object: ImageLoadingListener {
            override fun onLoadingComplete(imageUri: String?, view: View?, loadedImage: Bitmap?) {
                progressBarLoadPhotoDetail?.visibility = View.GONE
            }

            override fun onLoadingStarted(imageUri: String?, view: View?) {
                progressBarLoadPhotoDetail?.visibility = View.VISIBLE
            }

            override fun onLoadingCancelled(imageUri: String?, view: View?) {
                progressBarLoadPhotoDetail?.visibility = View.GONE
            }

            override fun onLoadingFailed(imageUri: String?, view: View?, failReason: FailReason?) {
                progressBarLoadPhotoDetail?.visibility = View.GONE
            }
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return false
    }
}
