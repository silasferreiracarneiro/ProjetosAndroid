package com.example.instagram.ui.home.profile


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.instagram.R
import com.example.instagram.ui.editprofile.EditProfileActivity
import kotlinx.android.synthetic.main.fragment_profile.view.*

class ProfileFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_profile, container, false)

        view.buttonEdtProfile.setOnClickListener{
            startActivity(Intent(context, EditProfileActivity::class.java))
        }

        return view
    }
}
