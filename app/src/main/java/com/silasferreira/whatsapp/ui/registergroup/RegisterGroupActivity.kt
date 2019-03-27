package com.silasferreira.whatsapp.ui.registergroup

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import com.google.android.material.snackbar.Snackbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.silasferreira.whatsapp.R
import com.silasferreira.whatsapp.model.Group
import com.silasferreira.whatsapp.model.Usuario
import com.silasferreira.whatsapp.ui.base.BaseActivity
import com.silasferreira.whatsapp.ui.chat.ChatActivity
import com.silasferreira.whatsapp.ui.group.GroupAdapter
import com.silasferreira.whatsapp.utils.AppConstants.Companion.CHAT_GROUP
import com.silasferreira.whatsapp.utils.AppConstants.Companion.MEMBERS
import com.silasferreira.whatsapp.utils.Base64Utils

import kotlinx.android.synthetic.main.activity_register_group.*
import kotlinx.android.synthetic.main.content_register_group.*
import java.io.ByteArrayOutputStream
import javax.inject.Inject

class RegisterGroupActivity : BaseActivity(), RegisterGroupContract.View {

    @Inject
    lateinit var presenter: RegisterGroupContract.Presenter<RegisterGroupContract.View, RegisterGroupContract.Interactor>

    lateinit var adaperUserSelect: GroupAdapter
    var map: Bitmap? = null
    var group : Group? = null

    private val GALERY = 200

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_group)
        setSupportActionBar(toolbarRegister)

        getActivityComponent().inject(this)
        presenter.onAttach(this)

        toolbarRegister.title = "Novo grupo"
        toolbarRegister.subtitle = "Defina o nome"

        var usersMembers = intent.extras?.getSerializable(MEMBERS) as ArrayList<Usuario>
        adaperUserSelect = GroupAdapter(usersMembers)

        group = Group(
            "",
            "",
            "",
            usersMembers
        )

        txtNumberMember.text = "Participantes: ${usersMembers.size}"

        fabRegisterGroup.setOnClickListener {
            group!!.name = edtNameGroup.text.toString()
            presenter.savedGroup(group!!)
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        configurationAdapterRecycler()

        imageUserMessage.setOnClickListener{
            var intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            if(intent.resolveActivity(packageManager) != null){
                startActivityForResult(intent, GALERY)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode == Activity.RESULT_OK){
            try {
                map = MediaStore.Images.Media.getBitmap(contentResolver, data?.data)
                if(map != null){
                    var baos = ByteArrayOutputStream()
                    map?.compress(Bitmap.CompressFormat.JPEG, 100, baos)
                    imageUserMessage.setImageBitmap(map)
                    group?.photo = Base64Utils.encodeByte(baos.toByteArray())
                }
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }

    private fun configurationAdapterRecycler() {
        recyclerMembers?.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
        recyclerMembers?.setHasFixedSize(true)
        recyclerMembers?.adapter = adaperUserSelect
        adaperUserSelect?.notifyDataSetChanged()
    }

    override fun goChatGroup(group: Group) {
        var i = Intent(this, ChatActivity::class.java)
        i.putExtra(CHAT_GROUP, group)
        startActivity(i)
    }
}
