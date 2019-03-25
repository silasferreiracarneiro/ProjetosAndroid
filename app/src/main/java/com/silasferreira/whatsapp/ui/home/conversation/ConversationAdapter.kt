package com.silasferreira.whatsapp.ui.home.conversation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.silasferreira.whatsapp.R
import com.silasferreira.whatsapp.model.Conversation
import com.silasferreira.whatsapp.utils.Base64Utils
import de.hdodenhof.circleimageview.CircleImageView

class ConversationAdapter (var listConversation: List<Conversation>) : RecyclerView.Adapter<ConversationAdapter.ListConversationAdapterViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListConversationAdapterViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_list_user, parent, false)
        return ListConversationAdapterViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listConversation.size
    }

    override fun onBindViewHolder(holder: ListConversationAdapterViewHolder, position: Int) {
        var conversation = listConversation[position]

        holder.title.text = conversation.usuario.nome
        holder.subtile.text = conversation.lastMessage

        if(conversation.usuario.foto != null && conversation.usuario.foto != ""){
            holder.foto.setImageBitmap(Base64Utils.decodebase64InBitmap(Base64Utils.decodeBase64ToByte(conversation.usuario.foto)))
        }else{
            holder.foto.setImageResource(R.drawable.padrao)
        }
    }


    class ListConversationAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        lateinit var foto: CircleImageView
        lateinit var title: TextView
        lateinit var subtile: TextView

        init {
            this.foto = itemView.findViewById(R.id.imageUser)
            this.title = itemView.findViewById(R.id.edtTitleUser)
            this.subtile = itemView.findViewById(R.id.edtSuTitleUser)
        }
    }
}