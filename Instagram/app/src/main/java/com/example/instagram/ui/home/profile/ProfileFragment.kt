package com.example.instagram.ui.home.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide

import com.example.instagram.R
import com.example.instagram.model.Posting
import com.example.instagram.model.User
import com.example.instagram.ui.base.BaseFragment
import com.example.instagram.ui.editprofile.EditProfileActivity
import com.example.instagram.ui.friendprofile.FriendProfileAdapter
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache
import com.nostra13.universalimageloader.core.ImageLoader
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile.view.*
import javax.inject.Inject

class ProfileFragment : BaseFragment(), ProfileContract.View {

    @Inject lateinit var presenter: ProfileContract.Presenter<ProfileContract.View, ProfileContract.Interactor>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_profile, container, false)

        val component = getActivityComponent()

        if(component != null){
            component.inject(this)
            presenter.onAttach(this)
        }

        view.buttonEdtProfile.setOnClickListener{
            startActivity(Intent(context, EditProfileActivity::class.java))
        }


        var config = ImageLoaderConfiguration
            .Builder(context!!)
            .memoryCache(LruMemoryCache(2 * 1024 * 1024))
            .memoryCacheSize(2 * 1024 * 1024)
            .diskCacheSize(50 * 1024 * 1024)
            .diskCacheFileCount(100)
            .diskCacheFileNameGenerator(HashCodeFileNameGenerator())
            .build()

        ImageLoader.getInstance().init(config)

        return view
    }

    override fun setUp(view: View) {
        presenter.getUser()
    }

    override fun setUser(user: User) {
        loadPhoto(user?.photo!!)
        qtPublicao.text = user?.qtPosting!!.toString()
        qtSeguidor.text = user?.qtFollowing!!.toString()
        qtSeguindo.text = user?.qtFollower!!.toString()
        presenter.loadPosting()
    }

    private fun loadPhoto(photo: String){
        if(photo != null && photo != ""){
            Glide.with(this).load(photo).into(imgProfile)
        }else{
            imgProfile.setImageResource(R.drawable.padrao)
        }
    }

    override fun setPosting(listPosting: ArrayList<Posting>) {
        qtPublicao.text = listPosting?.size!!.toString()
        griviewProfile.columnWidth = resources.displayMetrics.widthPixels / 3
        griviewProfile.adapter = FriendProfileAdapter(context!!, listPosting)
    }
}
