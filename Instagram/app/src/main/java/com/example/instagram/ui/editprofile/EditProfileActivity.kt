package com.example.instagram.ui.editprofile

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import com.example.instagram.R
import com.example.instagram.model.User
import com.example.instagram.ui.base.BaseActivity
import com.example.instagram.utils.Base64Utils
import com.example.instagram.utils.Base64Utils.decodeBase64ToByte
import com.example.instagram.utils.Base64Utils.decodebase64InBitmap
import kotlinx.android.synthetic.main.activity_edit_profile.*
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.toolbar.*
import java.io.ByteArrayOutputStream
import java.lang.Exception
import javax.inject.Inject

class EditProfileActivity : BaseActivity(), EditProfileContract.View {

    @Inject
    lateinit var presenter: EditProfileContract.Presenter<EditProfileContract.View, EditProfileContract.Interactor>

    private val GALERY_CODE = 200
    private var user = User()
    private var photo: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        getActivityComponent().inject(this)
        presenter.onAttach(this)

        supportActionBar?.title = "Editar perfil"
        setSupportActionBar(toolbarHome)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close_black_24dp)
        validatedPermissions()

        edtEmailUser.isFocusable = false

        btnSaveInfomation.setOnClickListener{
            this.user.nameUser = edtNameUser?.text.toString()
            this.user.photo = photo
            presenter.updateUser(this.user)
        }

        txtUpdatePhoto.setOnClickListener{
            var intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            if(intent.resolveActivity(packageManager) != null){
                startActivityForResult(intent, GALERY_CODE)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        var map : Bitmap? = null
        if(resultCode == Activity.RESULT_OK){
            try {
                map = MediaStore.Images.Media.getBitmap(contentResolver, data?.data)
                imgProfileUser.setImageBitmap(map)
            }catch (e:Exception){
                e.printStackTrace()
            }
        }

        if(map != null){
            var baos = ByteArrayOutputStream()
            map?.compress(Bitmap.CompressFormat.JPEG, 100, baos)
            photo = Base64Utils.encodeByte(baos.toByteArray())
            imgProfileUser.setImageBitmap(map)
        }
    }

    override fun setInformationUser(user: User) {
        this.user = user
        edtNameUser.setText(user.nameUser)
        edtEmailUser.setText(user.email)
        if(user.photo != ""){
            photo = user.photo
            imgProfileUser.setImageBitmap(decodebase64InBitmap(decodeBase64ToByte(user.photo)))
        }
    }

    override fun onStart() {
        super.onStart()
        presenter.getUser()
    }

    override fun onSupportNavigateUp(): Boolean {
        onFinish()
        return false
    }
}