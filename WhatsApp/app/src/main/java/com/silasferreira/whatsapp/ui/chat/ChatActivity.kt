package com.silasferreira.whatsapp.ui.chat

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import androidx.recyclerview.widget.LinearLayoutManager
import com.silasferreira.whatsapp.R
import com.silasferreira.whatsapp.data.prefs.PreferencesHelper
import com.silasferreira.whatsapp.model.Conversation
import com.silasferreira.whatsapp.model.Group
import com.silasferreira.whatsapp.model.MessageUser
import com.silasferreira.whatsapp.model.Usuario
import com.silasferreira.whatsapp.ui.base.BaseActivity
import com.silasferreira.whatsapp.utils.AppConstants.Companion.CHAT_GROUP
import com.silasferreira.whatsapp.utils.AppConstants.Companion.CHAT_USER
import com.silasferreira.whatsapp.utils.Base64Utils

import kotlinx.android.synthetic.main.activity_chat.*
import kotlinx.android.synthetic.main.content_chat.*
import java.io.ByteArrayOutputStream
import java.util.*
import javax.inject.Inject

class ChatActivity : BaseActivity(), ChatContract.View {

    @Inject lateinit var presenter: ChatContract.Presenter<ChatContract.View, ChatContract.Interactor>
    @Inject lateinit var prefs: PreferencesHelper

    private val CAMERA = 100

    var user: Usuario? = null
    var group: Group? = null
    var adapter: ChatAdapter? = null
    var listMessages = arrayListOf<MessageUser>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        setSupportActionBar(toolbar)

        getActivityComponent().inject(this)
        presenter.onAttach(this)

        supportActionBar?.title = ""
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if(intent.extras?.containsKey(CHAT_USER)!!){
            user = intent.extras.getSerializable(CHAT_USER) as Usuario?
        }else{
            group = intent.extras.getSerializable(CHAT_GROUP) as Group?
        }
        setInformationUser()

        fabSubmitMessage.setOnClickListener {
            if(editText?.text?.toString() != null && !editText?.text?.toString().isNullOrEmpty()){

                var message = MessageUser(
                    "",
                    "",
                    editText.text.toString())

                if(user != null){
                    message.recipientUser = Base64Utils.encode(user?.email)
                    presenter.sendMessage(message)
                }else{
                    presenter.sendMessageGroup(message, group!!)
                }
                editText?.setText("")
            }
        }
        configurationRecycler()

        imageCamera.setOnClickListener{
            var intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            if(intent.resolveActivity(packageManager) != null){
                startActivityForResult(intent, CAMERA)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        var map: Bitmap? = null
        if(resultCode == Activity.RESULT_OK){
            try {
                map = data?.extras?.get("data") as Bitmap?
            }catch (e: Exception){
                e.printStackTrace()
            }

            if(map != null){
                var baos = ByteArrayOutputStream()
                map?.compress(Bitmap.CompressFormat.JPEG, 100, baos)

                var message = MessageUser(
                    "",
                    Base64Utils.encode(user?.email),
                    "",
                    Date(),
                    Base64Utils.encodeByte(baos.toByteArray()))

                presenter.sendMessage(message)
            }
        }
    }

    override fun setNewListMessage(messages: ArrayList<MessageUser>) {
        listMessages = messages
        configurationRecycler()
        adapter?.notifyDataSetChanged()
    }

    private fun setInformationUser(){
        if(user != null){
            textNameMessage.text = user?.nameUser
            if(user?.foto != null && user?.foto != ""){
                var byte = Base64Utils.decodeBase64ToByte(user?.foto)
                imageUserMessage.setImageBitmap(Base64Utils.decodebase64InBitmap(byte))
            }else{
                imageUserMessage.setImageResource(R.drawable.padrao)
            }
        }else{
            textNameMessage.text = group?.name
            if(group?.photo != null && group?.photo != ""){
                var byte = Base64Utils.decodeBase64ToByte(group?.photo)
                imageUserMessage.setImageBitmap(Base64Utils.decodebase64InBitmap(byte))
            }else{
                imageUserMessage.setImageResource(R.drawable.padrao)
            }
        }
    }

    private fun configurationRecycler(){
        adapter = ChatAdapter(listMessages, prefs.getUserId())
        var layoutManager = LinearLayoutManager(this)
        recyclerMessages.removeAllViewsInLayout()
        recyclerMessages.layoutManager = layoutManager
        recyclerMessages.setHasFixedSize(true)
        recyclerMessages.adapter = adapter
    }

    override fun onStart() {
        super.onStart()
        if(user != null){
            presenter.getMessages(Base64Utils.encode(user?.email))
        }else{
            presenter.getMessages(group?.id!!)
        }
    }

    override fun getUserChat(): Usuario? {
        return user
    }

    override fun getGroupChat(): Group? {
        return group
    }
}
