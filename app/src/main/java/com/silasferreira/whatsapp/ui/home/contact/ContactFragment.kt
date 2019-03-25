package com.silasferreira.whatsapp.ui.home.contact


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.silasferreira.whatsapp.R
import com.silasferreira.whatsapp.model.Usuario
import com.silasferreira.whatsapp.ui.base.BaseFragment
import com.silasferreira.whatsapp.ui.chat.ChatActivity
import javax.inject.Inject

class ContactFragment : BaseFragment(), ContactContract.View  {

    @Inject lateinit var presenter: ContactContract.Presenter<ContactContract.View, ContactContract.Integractor>

    private var listContact: ArrayList<Usuario> = arrayListOf()
    private var adaper: ContactAdapter? = null
    private var recyler: RecyclerView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view= inflater.inflate(R.layout.fragment_contact, container, false)

        val component = getActivityComponent()

        if(component != null){
            component.inject(this)
            presenter.onAttach(this)
        }

        recyler = view.findViewById(R.id.recycleContact) as RecyclerView
        recyler?.addOnItemTouchListener(object: RecyclerView.OnItemTouchListener {
            override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {
                startActivity(Intent(context, ChatActivity::class.java))
            }

            override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                return true
            }

            override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {

            }
        })
        return view
    }

    override fun setUp(view: View) {
        presenter.onViewPrepared()
    }

    override fun setNewListUser(list: ArrayList<Usuario>) {
        this.listContact = list

        adaper = ContactAdapter(listContact)

        var layoutManager = LinearLayoutManager(context)
        recyler?.layoutManager = layoutManager
        recyler?.setHasFixedSize(true)
        recyler?.adapter = adaper

        adaper?.notifyDataSetChanged()
    }
}
