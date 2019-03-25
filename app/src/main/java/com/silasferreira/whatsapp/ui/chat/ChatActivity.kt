package com.silasferreira.whatsapp.ui.chat

import android.os.Bundle
import android.os.Message
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import com.silasferreira.whatsapp.R
import com.silasferreira.whatsapp.model.MessageUser
import com.silasferreira.whatsapp.model.Usuario
import com.silasferreira.whatsapp.ui.base.BaseActivity
import com.silasferreira.whatsapp.utils.AppConstants.Companion.CHAT_USER
import com.silasferreira.whatsapp.utils.Base64Utils

import kotlinx.android.synthetic.main.activity_chat.*
import kotlinx.android.synthetic.main.activity_setting.*
import kotlinx.android.synthetic.main.content_chat.*
import javax.inject.Inject

class ChatActivity : BaseActivity(), ChatContract.View {

    @Inject lateinit var presenter: ChatContract.Presenter<ChatContract.View, ChatContract.Interactor>

    var user: Usuario? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        setSupportActionBar(toolbar)

        getActivityComponent().inject(this)
        presenter.onAttach(this)

        supportActionBar?.title = ""
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        user = intent.extras.getSerializable(CHAT_USER) as Usuario?
        setInformationUser()

        fabSubmitMessage.setOnClickListener {
            if(editText?.text?.toString() != null && !editText?.text?.toString().isNullOrEmpty()){
                var message = MessageUser(
                    "",
                    Base64Utils.encode(user?.email),
                    editText.text.toString())

                presenter.sendMessage(message)
                editText?.setText("")
            }
        }
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
        }
    }

}
