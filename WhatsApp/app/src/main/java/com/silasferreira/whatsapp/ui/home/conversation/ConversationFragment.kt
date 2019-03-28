package com.silasferreira.whatsapp.ui.home.conversation


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.silasferreira.whatsapp.R
import com.silasferreira.whatsapp.model.Conversation
import com.silasferreira.whatsapp.model.Usuario
import com.silasferreira.whatsapp.ui.base.BaseFragment
import com.silasferreira.whatsapp.ui.chat.ChatActivity
import com.silasferreira.whatsapp.ui.home.contact.ContactAdapter
import com.silasferreira.whatsapp.utils.AppConstants
import com.silasferreira.whatsapp.utils.RecyclerViewItemClickListener
import javax.inject.Inject

class ConversationFragment : BaseFragment(), ConversationContract.View {

    @Inject lateinit var presenter: ConversationContract.Presenter<ConversationContract.View, ConversationContract.Integractor>

    private var adaper: ConversationAdapter? = null
    private var recyler: RecyclerView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_conversation, container, false)

        val component = getActivityComponent()

        if(component != null){
            component.inject(this)
            presenter.onAttach(this)
        }

        recyler = view.findViewById(R.id.recyclerConversation) as RecyclerView

        recyler?.addOnItemTouchListener(
            RecyclerViewItemClickListener(context!!, recyler!!, object : RecyclerViewItemClickListener.OnItemClickListener{
                override fun onItemClick(view: View, position: Int) {
                    var conversation = presenter.getUserSelect(position)
                    var i = Intent(context, ChatActivity::class.java)

                    if(conversation.groupConversation){
                        i.putExtra(AppConstants.CHAT_GROUP, conversation.group)
                    }else{
                        i.putExtra(AppConstants.CHAT_USER, conversation.usuario)
                    }
                    startActivity(i)
                }

                override fun onItemLongClick(view: View?, position: Int) { }
            })
        )

        return view
    }

    override fun setUp(view: View) {
        presenter.onViewPrepared()
    }

    override fun setNewListUser(list: ArrayList<Conversation>) {
        adaper = ConversationAdapter(list)

        var layoutManager = LinearLayoutManager(context)
        recyler?.layoutManager = layoutManager
        recyler?.setHasFixedSize(true)
        recyler?.adapter = adaper

        adaper?.notifyDataSetChanged()
    }
}
