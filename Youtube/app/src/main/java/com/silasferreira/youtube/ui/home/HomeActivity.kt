package com.silasferreira.youtube.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.core.view.MenuItemCompat
import android.view.Menu
import android.view.View
import android.widget.SearchView
import com.example.instagram.ui.base.BaseActivity
import com.silasferreira.youtube.R
import com.silasferreira.youtube.model.Items
import com.silasferreira.youtube.ui.player.PlayerActivity
import com.silasferreira.youtube.utils.AppContants.Companion.VIDEO_ID
import com.silasferreira.youtube.utils.RecyclerViewItemClickListener
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

        getVideo()


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        var inflater = menuInflater.inflate(R.menu.menu, menu)
        var searchView = MenuItemCompat.getActionView(menu?.findItem(R.id.searchMenu)) as SearchView

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if(query != null){
                    presenter.getVideo(query?.replace(" ", "+")!!)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        return super.onCreateOptionsMenu(menu)
    }

    private fun getVideo(){
        presenter.getVideo("")
    }

    override fun setVideo(items: List<Items>) {
        recyclerVideo.setHasFixedSize(true)
        recyclerVideo.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        recyclerVideo.adapter = HomeAdapter(items)
        recyclerVideo.adapter?.notifyDataSetChanged()

        recyclerVideo.addOnItemTouchListener(RecyclerViewItemClickListener(this, recyclerVideo, object:RecyclerViewItemClickListener.OnItemClickListener{

            override fun onItemClick(view: View, position: Int) {
                var video = items[position]
                var i = Intent(applicationContext, PlayerActivity::class.java)
                i.putExtra(VIDEO_ID, video.id.videoId)
                startActivity(i)
            }

            override fun onItemLongClick(view: View?, position: Int) {
                TODO()
            }
        }))
    }
}
