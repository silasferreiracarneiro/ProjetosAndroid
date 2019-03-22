package com.silasferreira.whatsapp.ui.setting

import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageView
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.core.content.ContextCompat.checkSelfPermission
import com.silasferreira.whatsapp.App
import com.silasferreira.whatsapp.R
import com.silasferreira.whatsapp.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_setting.*
import kotlinx.android.synthetic.main.toolbar.*
import java.io.ByteArrayOutputStream
import javax.inject.Inject

class SettingActivity : BaseActivity(), SettingContract.View {

    @Inject
    lateinit var presenter: SettingContract.Presenter<SettingContract.View, SettingContract.Interactor>

    private val awards = arrayListOf(READ_EXTERNAL_STORAGE)
    private val CAMERA = 100
    private val GALERY = 200
    private val REQUEST_PERMISSION = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        getActivityComponent().inject(this)
        presenter.onAttach(this)

        //Configurations Toolbar
        toolbar.setTitle(R.string.title_setting)
        setSupportActionBar(toolbar)
        //Back button in toolbar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        validatedPermissions()

        imageButtonCamera.setOnClickListener {
            var intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            if(intent.resolveActivity(packageManager) != null){
                startActivityForResult(intent, CAMERA)
            }
        }

        imageButtonGalery.setOnClickListener{
            var intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            if(intent.resolveActivity(packageManager) != null){
                startActivityForResult(intent, GALERY)
            }
        }

        edtProfileUser.setOnClickListener{
            this.presenter.updateNameUser(edtNameUser?.text.toString())
        }

        this.presenter.searchUser()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        var map: Bitmap? = null
        if(resultCode == Activity.RESULT_OK){
            try {
                map = when(requestCode){
                    CAMERA -> data?.extras?.get("data") as Bitmap?
                    else -> MediaStore.Images.Media.getBitmap(contentResolver, data?.data)
                }
            }catch (e: Exception){
                e.printStackTrace()
            }

            if(map != null){
                var baos = ByteArrayOutputStream()
                map?.compress(Bitmap.CompressFormat.JPEG, 100, baos)
                this.presenter.uploadImage(baos.toByteArray())
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        grantResults.forEach {
            if(it == PackageManager.PERMISSION_DENIED){
                alertPermission()
            }
        }
    }

    fun validatedPermissions(){
        var arrayRequest = arrayListOf<String>()

        awards.forEach{
            if(checkSelfPermission(this, it) != PERMISSION_GRANTED){
                arrayRequest.add(it)
            }
        }

        if(arrayRequest.size > 0){
            val array = arrayOfNulls<String>(arrayRequest.size)
            requestPermissions(this, arrayRequest.toArray(array), REQUEST_PERMISSION)
        }
    }


    fun alertPermission(){

        var builder = AlertDialog.Builder(this)
        builder.setTitle("Permissões negadas")
        builder.setMessage("Para utilizar o app é necessário aceitar as permissões")
        builder.setCancelable(false)
        builder.setPositiveButton("Confirmar"){ dialog, which ->
            finish()
        }
        builder.create().show()
    }

    override fun setImageUser(map: Bitmap){
        imagePerfilUser.setImageBitmap(map)
    }

    override fun setNameUser(name: String) {
        edtNameUser.setText(name)
    }
}
