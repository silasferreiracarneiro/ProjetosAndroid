package com.silasferreira.whatsapp.ui.home.conversation


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.silasferreira.whatsapp.R
import com.silasferreira.whatsapp.model.Usuario
import com.silasferreira.whatsapp.ui.base.BaseFragment
import com.silasferreira.whatsapp.ui.home.ListAdapter
import kotlinx.android.synthetic.main.fragment_conversation.*
import javax.inject.Inject

class ConversationFragment : BaseFragment(), ConversationContract.View {

    @Inject lateinit var presenter: ConversationContract.Presenter<ConversationContract.View, ConversationContract.Integractor>

    private var listContact: ArrayList<Usuario> = arrayListOf()
    private var adaper: ListAdapter? = null
    private var recyler: RecyclerView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_conversation, container, false)

        val component = getActivityComponent()

        if(component != null){
            component.inject(this)
            presenter.onAttach(this)
        }

        recyler = view.findViewById(R.id.recyclerConversation) as RecyclerView

        return view
    }

    override fun setUp(view: View) {
        presenter.onViewPrepared()
    }

    override fun setNewListUser(list: ArrayList<Usuario>) {
        this.listContact = list

        adaper = ListAdapter(arrayListOf())

        var layoutManager = LinearLayoutManager(context)
        recyler?.layoutManager = layoutManager
        recyler?.setHasFixedSize(true)
        recyler?.adapter = adaper

        adaper?.notifyDataSetChanged()
    }
}
