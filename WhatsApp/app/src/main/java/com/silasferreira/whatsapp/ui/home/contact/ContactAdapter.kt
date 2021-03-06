package com.silasferreira.whatsapp.ui.home.contact

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.silasferreira.whatsapp.R
import com.silasferreira.whatsapp.model.Usuario
import com.silasferreira.whatsapp.utils.Base64Utils
import de.hdodenhof.circleimageview.CircleImageView

class ContactAdapter(var listUsers: List<Usuario>) : RecyclerView.Adapter<ContactAdapter.ListAdapterViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapterViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_list_user, parent, false)
        return ListAdapterViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listUsers.size
    }

    override fun onBindViewHolder(holder: ListAdapterViewHolder, position: Int) {
        var user = listUsers[position]

        holder.title.text = user.nome
        holder.subtile.text = user.email

        if(user.foto != null && user.foto != ""){
            holder.foto.setImageBitmap(Base64Utils.decodebase64InBitmap(Base64Utils.decodeBase64ToByte(user.foto)))
        }else if(user.email.isEmpty()){
            holder.foto.setImageResource(R.drawable.icone_grupo)
            holder.subtile.visibility = View.GONE
        }else{
            holder.foto.setImageResource(R.drawable.padrao)
        }
    }


    class ListAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

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