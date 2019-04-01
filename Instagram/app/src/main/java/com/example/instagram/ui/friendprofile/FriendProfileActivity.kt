package com.example.instagram.ui.friendprofile

import android.os.Bundle
import com.example.instagram.R
import com.example.instagram.model.User
import com.example.instagram.ui.base.BaseActivity
import com.example.instagram.utils.AppConstants.Companion.USER_INTENT
import com.example.instagram.utils.Base64Utils.decodeBase64ToByte
import com.example.instagram.utils.Base64Utils.decodebase64InBitmap
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.toolbar.*
import javax.inject.Inject

class FriendProfileActivity : BaseActivity(), FriendProfileContract.View {

    @Inject lateinit var presenter: FriendProfileContract.Presenter<FriendProfileContract.View, FriendProfileContract.Interactor>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friend_profile)

        getActivityComponent().inject(this)
        presenter.onAttach(this)

        var user = intent.extras.get(USER_INTENT) as User

        setSupportActionBar(toolbarHome)
        supportActionBar?.title = user?.nameUser
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close_black_24dp)

        loadPhoto(user.photo)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return false
    }

    private fun loadPhoto(photo: String){
        if(photo != null && photo != ""){
            imgProfile.setImageBitmap(decodebase64InBitmap(decodeBase64ToByte(photo)))
        }else{
            imgProfile.setImageResource(R.drawable.padrao)
        }
    }
}
