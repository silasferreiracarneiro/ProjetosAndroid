package com.example.instagram.ui.home.posting

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.instagram.R
import com.example.instagram.ui.base.BaseFragment
import com.example.instagram.ui.filterphoto.FilterPhotoActivity
import com.example.instagram.utils.AppConstants.Companion.PHOTO_POSTING
import com.example.instagram.utils.Base64Utils
import kotlinx.android.synthetic.main.activity_edit_profile.*
import kotlinx.android.synthetic.main.fragment_posting.view.*
import java.io.ByteArrayOutputStream
import java.lang.Exception
import javax.inject.Inject

class PostingFragment : BaseFragment(), PostingContract.View {

    @Inject lateinit var presenter: PostingContract.Presenter<PostingContract.View, PostingContract.Interactor>

    private val REQUEST_PERMISSION_GALERY = 100
    private val REQUEST_PERMISSION_CAM = 200

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view =     inflater.inflate(R.layout.fragment_posting, container, false)

        val component = getActivityComponent()

        if(component != null){
            component.inject(this)
            presenter.onAttach(this)
        }

        view.btnGalery.setOnClickListener{
            var intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            if(intent.resolveActivity(activity?.packageManager) != null){
                startActivityForResult(intent, REQUEST_PERMISSION_GALERY)
            }
        }

        view.btnCam.setOnClickListener{
            var intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            if(intent.resolveActivity(activity?.packageManager) != null){
                startActivityForResult(intent, REQUEST_PERMISSION_CAM)
            }
        }

        validatedPermissions()

        return view
    }

    override fun setUp(view: View) {

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        var map : Bitmap? = null
        if(resultCode == Activity.RESULT_OK){
            try {
                map = when(requestCode){
                    REQUEST_PERMISSION_GALERY -> MediaStore.Images.Media.getBitmap(activity?.contentResolver, data?.data)
                    REQUEST_PERMISSION_CAM -> data?.extras?.get("data") as Bitmap
                    else -> null
                }
            }catch (e: Exception) {
                e.printStackTrace()
            }
        }

        if(map != null){
            try {
                var baos = ByteArrayOutputStream()
                map?.compress(Bitmap.CompressFormat.JPEG, 50, baos)
                var byte = baos.toByteArray()

                var i = Intent(context!!, FilterPhotoActivity::class.java)
                i.putExtra(PHOTO_POSTING, byte)
                startActivity(i)

            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }
}
