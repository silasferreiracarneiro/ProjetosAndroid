package com.example.trailermovie.ui.instruction

import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.example.trailermovie.model.Slide

class SlideAdapter(var listSlide: ArrayList<Slide>) : PagerAdapter() {

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return true
    }

    override fun getCount(): Int {
        return listSlide.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        return super.instantiateItem(container, position)
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        super.destroyItem(container, position, `object`)
    }
}