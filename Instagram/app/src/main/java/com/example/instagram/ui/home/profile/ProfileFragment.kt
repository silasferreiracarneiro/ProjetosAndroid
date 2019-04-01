package com.example.instagram.ui.home.profile


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.instagram.R
import com.example.instagram.ui.base.BaseFragment
import com.example.instagram.ui.editprofile.EditProfileActivity
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

        return view
    }

    override fun setUp(view: View) {

    }
}
