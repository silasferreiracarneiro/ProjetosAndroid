package com.silasferreira.whatsapp.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.core.view.MenuItemCompat
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems
import com.silasferreira.whatsapp.R
import com.silasferreira.whatsapp.ui.base.BaseActivity
import com.silasferreira.whatsapp.ui.home.contact.ContactFragment
import com.silasferreira.whatsapp.ui.home.conversation.ConversationFragment
import com.silasferreira.whatsapp.ui.setting.SettingActivity
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.toolbar.*
import javax.inject.Inject

class HomeActivity : BaseActivity(), HomeContract.View {

    @Inject
    lateinit var presenter: HomeContract.Presenter<HomeContract.View, HomeContract.Interactor>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        getActivityComponent().inject(this)
        presenter.onAttach(this)

        //Configuration Toolbar
        var toolbar = toolbar as Toolbar
        setSupportActionBar(toolbar)

        //Configuration Tabs
        var adapter = FragmentPagerItemAdapter(
            supportFragmentManager,
            FragmentPagerItems.with(this)
                .add(R.string.title_conversa, ConversationFragment::class.java)
                .add(R.string.title_contato, ContactFragment::class.java)
                .create())

        vpHome.adapter = adapter
        tabHome.setViewPager(vpHome)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        var inflater = menuInflater
        inflater.inflate(R.menu.menu_home, menu)

        var searchView = MenuItemCompat.getActionView(menu?.findItem(R.id.itemSearch)) as SearchView

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if(newText != null){
                    presenter.searchListConversation(newText!!)
                }
                return false
            }
        })

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.itemLoggout -> presenter.logout()
            R.id.itemSettings -> getSetting()
        }
        return super.onOptionsItemSelected(item)
    }

    fun getSetting(){
        startActivity(Intent(applicationContext, SettingActivity::class.java))
    }
}
