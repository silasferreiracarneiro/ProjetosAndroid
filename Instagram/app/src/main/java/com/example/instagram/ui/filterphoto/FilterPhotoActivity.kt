package com.example.instagram.ui.filterphoto

import android.graphics.Bitmap
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.instagram.R
import com.example.instagram.model.Posting
import com.example.instagram.ui.base.BaseActivity
import com.example.instagram.utils.AppConstants
import com.example.instagram.utils.Base64Utils
import com.example.instagram.utils.RecyclerViewItemClickListener
import com.zomato.photofilters.FilterPack
import com.zomato.photofilters.utils.ThumbnailItem
import com.zomato.photofilters.utils.ThumbnailsManager
import kotlinx.android.synthetic.main.activity_filter_photo.*
import kotlinx.android.synthetic.main.toolbar.*
import javax.inject.Inject

class FilterPhotoActivity : BaseActivity(), FilterPhotoContract.View {

    @Inject lateinit var presenter: FilterPhotoContract.Presenter<FilterPhotoContract.View, FilterPhotoContract.Interactor>

    init {
        System.loadLibrary("NativeImageProcessor")
    }

    private var filters: ArrayList<ThumbnailItem> = arrayListOf()
    private var image: Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter_photo)

        getActivityComponent().inject(this)
        presenter.onAttach(this)

        setSupportActionBar(toolbarHome)
        toolbarHome.title = getString(R.string.filter)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close_black_24dp)

        var photo = intent.extras.get(AppConstants.PHOTO_POSTING) as ByteArray
        image = Base64Utils.decodebase64InBitmap(photo)
        imgPhoto.setImageBitmap(image)

        getFilters()

        recyclerFilter.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerFilter.adapter = FilterPhotoAdapter(filters)

        recyclerFilter.addOnItemTouchListener(RecyclerViewItemClickListener(applicationContext, recyclerFilter, object:RecyclerViewItemClickListener.OnItemClickListener{
            override fun onItemClick(view: View, position: Int) {
                var imageCopy = image!!.copy(image!!.config, true)
                var filter = filters[position].filter
                imgPhoto.setImageBitmap(filter.processFilter(imageCopy))
            }

            override fun onItemLongClick(view: View?, position: Int) {

            }
        }))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_publish, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when(item?.itemId){
            R.id.publishPhoto -> {
                var posting = Posting(

                )
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return false
    }

    private fun getFilters(){
        ThumbnailsManager.clearThumbs()
        filters.clear()

        var fiiltersList = FilterPack.getFilterPack(applicationContext)

        fiiltersList.forEach {
            var item = ThumbnailItem()
            item.image = image
            item.filter = it
            item.filterName = it.name

            ThumbnailsManager.addThumb(item)
            recyclerFilter.adapter?.notifyDataSetChanged()
        }

        filters.addAll(ThumbnailsManager.processThumbs(applicationContext))
    }
}
