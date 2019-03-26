package com.silasferreira.whatsapp.ui.group

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.silasferreira.whatsapp.R
import com.silasferreira.whatsapp.model.Usuario
import com.silasferreira.whatsapp.utils.Base64Utils
import de.hdodenhof.circleimageview.CircleImageView

class GroupAdapter(var listUsers: List<Usuario>) : RecyclerView.Adapter<GroupAdapter.AdapterViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_list_group, parent, false)
        return AdapterViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listUsers.size
    }

    override fun onBindViewHolder(holder: AdapterViewHolder, position: Int) {
        var user = listUsers[position]

        holder.title.text = user.nome

        if(user.foto != null && user.foto != ""){
            holder.foto.setImageBitmap(Base64Utils.decodebase64InBitmap(Base64Utils.decodeBase64ToByte(user.foto)))
        }else{
            holder.foto.setImageResource(R.drawable.padrao)
        }
    }


    class AdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        lateinit var foto: CircleImageView
        lateinit var title: TextView

        init {
            this.foto = itemView.findViewById(R.id.imageUserSelect)
            this.title = itemView.findViewById(R.id.txtTitleSelect)
        }
    }
}