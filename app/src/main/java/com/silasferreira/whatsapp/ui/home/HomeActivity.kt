package com.silasferreira.whatsapp.ui.home

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems
import com.silasferreira.whatsapp.App
import com.silasferreira.whatsapp.R
import com.silasferreira.whatsapp.ui.base.BaseActivity
import com.silasferreira.whatsapp.ui.home.contato.ContatoFragment
import com.silasferreira.whatsapp.ui.home.conversa.ConversaFragment
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.toolbar.*
import javax.inject.Inject

class HomeActivity : BaseActivity(), HomeContract.View {

    @Inject
    lateinit var presenter: HomeContract.Presenter<HomeContract.View, HomeContract.Interactor>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        (application as App).getComponent().inject(this)
        presenter.onAttach(this)

        //Configation Toolbar
        var toolbar = toolbarHome as Toolbar
        setSupportActionBar(toolbar)

        //Configuration Tabs
        var adapter = FragmentPagerItemAdapter(
            supportFragmentManager,
            FragmentPagerItems.with(this)
                .add(R.string.title_conversa, ConversaFragment::class.java)
                .add(R.string.title_contato, ContatoFragment::class.java)
                .create())

        vpHome.adapter = adapter
        tabHome.setViewPager(vpHome)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        var inflater = menuInflater
        inflater.inflate(R.menu.menu_home, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.itemSearch -> ""
            R.id.itemLoggout -> presenter.logout()
            R.id.itemSettings -> ""
        }
        return super.onOptionsItemSelected(item)
    }
}
