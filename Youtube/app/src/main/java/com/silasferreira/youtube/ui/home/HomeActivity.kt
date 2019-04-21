package com.silasferreira.youtube.ui.home

import android.os.Bundle
import androidx.core.view.MenuItemCompat
import android.view.Menu
import android.widget.SearchView
import com.example.instagram.ui.base.BaseActivity
import com.silasferreira.youtube.R
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.toolbar.*
import javax.inject.Inject

class HomeActivity : BaseActivity(), HomeContract.View {

    @Inject lateinit var presenter: HomeContract.Presenter<HomeContract.View, HomeContract.Interactor>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        getActivityComponent().inject(this)
        presenter.onAttach(this)

        setSupportActionBar(toolbar)
        toolbar.title = getString(R.string.app_name)

        recyclerVideo.setHasFixedSize(true)
        recyclerVideo.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        recyclerVideo.adapter = HomeAdapter()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        var inflater = menuInflater.inflate(R.menu.menu, menu)
        var searchView = MenuItemCompat.getActionView(menu?.findItem(R.id.searchMenu)) as SearchView

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if(newText != null){

                }
                return false
            }
        })

        return super.onCreateOptionsMenu(menu)

    }
}
