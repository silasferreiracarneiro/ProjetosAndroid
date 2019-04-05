package com.example.instagram.ui.home

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.instagram.R
import com.example.instagram.ui.base.BaseActivity
import com.example.instagram.ui.home.feed.FeedFragment
import com.example.instagram.ui.home.posting.PostingFragment
import com.example.instagram.ui.home.profile.ProfileFragment
import com.example.instagram.ui.home.search.SearchFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx
import kotlinx.android.synthetic.main.bottton_navigation.*
import kotlinx.android.synthetic.main.toolbar.*
import javax.inject.Inject

class HomeActivity : BaseActivity(), HomeContract.View {

    @Inject lateinit var presenter: HomeContract.Presenter<HomeContract.View, HomeContract.Interactor>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        getActivityComponent().inject(this)
        presenter.onAttach(this)

        setSupportActionBar(toolbarHome)

        configurationBottonNavigationView()
        enableNavigation()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        var inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.loggout -> {
                presenter.signOut()
                hideLoading()
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun configurationBottonNavigationView(){
        var navigation = this.findViewById(R.id.bottonNavigation) as BottomNavigationViewEx
        navigation.enableAnimation(true)
        navigation.enableItemShiftingMode(false)
        navigation.enableShiftingMode(false)
        navigation.setTextVisibility(false)
    }

    private fun enableNavigation(){
        var navigation = this.findViewById(R.id.bottonNavigation) as BottomNavigationViewEx
        var transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.viewPager, FeedFragment()).commit()

        navigation.setOnNavigationItemReselectedListener {
            var transaction = supportFragmentManager.beginTransaction()

            when(it.itemId){
                R.id.ic_home -> transaction.replace(R.id.viewPager, FeedFragment()).commit()
                R.id.ic_posting -> transaction.replace(R.id.viewPager, PostingFragment()).commit()
                R.id.ic_profile -> transaction.replace(R.id.viewPager, ProfileFragment()).commit()
                R.id.ic_search -> transaction.replace(R.id.viewPager, SearchFragment()).commit()
                else -> transaction.replace(R.id.viewPager, FeedFragment()).commit()
            }
        }
    }
}
