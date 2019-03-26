package com.silasferreira.whatsapp.ui.group

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.silasferreira.whatsapp.R
import com.silasferreira.whatsapp.model.Usuario
import com.silasferreira.whatsapp.ui.base.BaseActivity
import com.silasferreira.whatsapp.ui.home.contact.ContactAdapter
import com.silasferreira.whatsapp.ui.registergroup.RegisterGroupActivity
import com.silasferreira.whatsapp.utils.AppConstants.Companion.MEMBERS
import com.silasferreira.whatsapp.utils.RecyclerViewItemClickListener

import kotlinx.android.synthetic.main.activity_group.*
import kotlinx.android.synthetic.main.content_group.*
import java.io.Serializable
import javax.inject.Inject

class GroupActivity : BaseActivity(), GroupContract.View {

    @Inject lateinit var presenter: GroupContract.Presenter<GroupContract.View, GroupContract.Interactor>

    lateinit var adaperUser: ContactAdapter
    lateinit var adaperUserSelect: GroupAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_group)
        setSupportActionBar(toolbar)

        getActivityComponent().inject(this)
        presenter.onAttach(this)

        toolbar.title = "Novo grupo"

        fabNextRegisterGroup.setOnClickListener {
            var i = Intent(this, RegisterGroupActivity::class.java)
            i.putExtra(MEMBERS, presenter.getListUserSelects() as Serializable)
            startActivity(i)
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        presenter.getListUser()

        listUserRecycler?.addOnItemTouchListener(
            RecyclerViewItemClickListener(applicationContext, listUserRecycler!!, object : RecyclerViewItemClickListener.OnItemClickListener{
                override fun onItemClick(view: View, position: Int) {
                    presenter.userSelect(position)
                }

                override fun onItemLongClick(view: View?, position: Int) { }
            })
        )

        listUserSelect?.addOnItemTouchListener(
            RecyclerViewItemClickListener(applicationContext, listUserRecycler!!, object : RecyclerViewItemClickListener.OnItemClickListener{
                override fun onItemClick(view: View, position: Int) {
                    presenter.userDeselect(position)
                }

                override fun onItemLongClick(view: View?, position: Int) { }
            })
        )
    }

    override fun setNewListUser(listUser: ArrayList<Usuario>) {
        adaperUser = ContactAdapter(listUser)
        listUserRecycler?.layoutManager = LinearLayoutManager(applicationContext)
        listUserRecycler?.setHasFixedSize(true)
        listUserRecycler?.adapter = adaperUser

        adaperUser?.notifyDataSetChanged()
    }


    override fun setNewListUserSelect(listUser: ArrayList<Usuario>) {
        adaperUserSelect = GroupAdapter(listUser)

        listUserSelect?.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
        listUserSelect?.setHasFixedSize(true)
        listUserSelect?.adapter = adaperUserSelect

        adaperUserSelect?.notifyDataSetChanged()
    }

    override fun updateToolbar(userSelect: Int, userTotal: Int) {
        toolbar.subtitle = "$userSelect de ${userTotal + userSelect}"
    }
}
