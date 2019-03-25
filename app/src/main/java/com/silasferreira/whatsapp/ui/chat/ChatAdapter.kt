package com.silasferreira.whatsapp.ui.chat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.silasferreira.whatsapp.R
import com.silasferreira.whatsapp.model.MessageUser
import com.silasferreira.whatsapp.utils.Base64Utils

class ChatAdapter(var listMessages: List<MessageUser>, var emailUser: String): RecyclerView.Adapter<ChatAdapter.ViewHolder>() {

    private val TYPE_SENDER = 1
    private val TYPE_RECIPIENT = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if(viewType == TYPE_RECIPIENT){
            var item = LayoutInflater.from(parent.context).inflate(R.layout.adapter_message_recipient, parent, false)
            return ViewHolder(item)
        }
        var item = LayoutInflater.from(parent.context).inflate(R.layout.adapter_message_sender, parent, false)
        return ViewHolder(item)
    }

    override fun getItemCount(): Int {
        return listMessages.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var message = listMessages[position]

        if(message.image != null && message.image != ""){
            var byte = Base64Utils.decodeBase64ToByte(message?.image)
            holder.image.setImageBitmap(Base64Utils.decodebase64InBitmap(byte))
            holder.message.visibility = View.GONE
        }else{
            holder.message.text = message.message
            holder.image.visibility = View.GONE
        }
    }

    override fun getItemViewType(position: Int): Int {
        var message = listMessages[position]

        if(emailUser == message.recipientUser)
            return TYPE_RECIPIENT

        return TYPE_SENDER
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        lateinit var message: TextView
        lateinit var image: ImageView

        init {
            this.message = itemView.findViewById(R.id.textMessageUserChat)
            this.image = itemView.findViewById(R.id.imageMessaPhotoChat)
        }
    }
}