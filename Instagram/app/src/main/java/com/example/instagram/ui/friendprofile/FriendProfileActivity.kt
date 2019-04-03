package com.example.instagram.ui.friendprofile

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import com.bumptech.glide.Glide
import com.example.instagram.R
import com.example.instagram.model.Follower
import com.example.instagram.model.Posting
import com.example.instagram.model.User
import com.example.instagram.ui.base.BaseActivity
import com.example.instagram.ui.postingdetail.PostingDetailActivity
import com.example.instagram.utils.AppConstants.Companion.POSTING
import com.example.instagram.utils.AppConstants.Companion.USER_INTENT
import com.example.instagram.utils.Base64Utils
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache
import com.nostra13.universalimageloader.core.ImageLoader
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.toolbar.*
import javax.inject.Inject

class FriendProfileActivity : BaseActivity(), FriendProfileContract.View {

    @Inject lateinit var presenter: FriendProfileContract.Presenter<FriendProfileContract.View, FriendProfileContract.Interactor>

    private var userIntent: User? = null
    private var postagens= arrayListOf<Posting>()

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

        griviewProfile.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            var posting = postagens[position]
            var i = Intent(applicationContext, PostingDetailActivity::class.java)
            i.putExtra(POSTING, posting)
            i.putExtra(USER_INTENT, userIntent)
            startActivity(i)
        }
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
        this.postagens = postagens
        qtPublicao.text = postagens?.size!!.toString()
        griviewProfile.columnWidth = resources.displayMetrics.widthPixels / 3
        griviewProfile.adapter = FriendProfileAdapter(applicationContext, postagens)
    }
}
