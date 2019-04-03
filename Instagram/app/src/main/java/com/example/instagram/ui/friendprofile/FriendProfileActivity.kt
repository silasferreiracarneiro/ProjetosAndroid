package com.example.instagram.ui.friendprofile

import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.instagram.R
import com.example.instagram.model.Follower
import com.example.instagram.model.Posting
import com.example.instagram.model.User
import com.example.instagram.ui.base.BaseActivity
import com.example.instagram.utils.AppConstants.Companion.USER_INTENT
import com.example.instagram.utils.Base64Utils
import com.example.instagram.utils.Base64Utils.decodeBase64ToByte
import com.example.instagram.utils.Base64Utils.decodebase64InBitmap
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache
import com.nostra13.universalimageloader.core.ImageLoader
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration
import kotlinx.android.synthetic.main.activity_edit_profile.*
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.toolbar.*
import javax.inject.Inject

class FriendProfileActivity : BaseActivity(), FriendProfileContract.View {

    @Inject lateinit var presenter: FriendProfileContract.Presenter<FriendProfileContract.View, FriendProfileContract.Interactor>

    private var userIntent: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friend_profile)

        getActivityComponent().inject(this)
        presenter.onAttach(this)

        userIntent = intent.extras.get(USER_INTENT) as User

        setSupportActionBar(toolbarHome)
        supportActionBar?.title = userIntent?.nameUser
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close_black_24dp)

        presenter.following(userIntent!!)
        presenter.getAllPosting(userIntent?.email!!)

        var config = ImageLoaderConfiguration
            .Builder(this)
            .memoryCache(LruMemoryCache(2 * 1024 * 1024))
            .memoryCacheSize(2 * 1024 * 1024)
            .diskCacheSize(50 * 1024 * 1024)
            .diskCacheFileCount(100)
            .diskCacheFileNameGenerator(HashCodeFileNameGenerator())
            .build()

        ImageLoader.getInstance().init(config)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return false
    }

    private fun loadPhoto(photo: String){
        if(photo != null && photo != ""){
            Glide.with(this).load(photo).into(imgProfile)
        }else{
            imgProfile.setImageResource(R.drawable.padrao)
        }
    }

    override fun setTextButtonFollowing(text: String, enableField: Boolean) {
        buttonEdtProfile.text = text
        buttonEdtProfile.isEnabled = enableField

        if(enableField){
            buttonEdtProfile.setOnClickListener{
                var follower = Follower(
                    "",
                    Base64Utils.encode(userIntent!!.email),
                    userIntent!!
                )
                presenter.savedFollower(follower)
            }
        }
    }
    override fun setUserPae(user: User?) {
        loadPhoto(user?.photo!!)
        qtPublicao.text = user?.qtPosting!!.toString()
        qtSeguidor.text = user?.qtFollower!!.toString()
        qtSeguindo.text = user?.qtFollowing!!.toString()
    }

    override fun setAllPosting(postagens: ArrayList<Posting>) {
        qtPublicao.text = postagens?.size!!.toString()
        griviewProfile.columnWidth = resources.displayMetrics.widthPixels / 3
        griviewProfile.adapter = FriendProfileAdapter(applicationContext, postagens)
    }
}
