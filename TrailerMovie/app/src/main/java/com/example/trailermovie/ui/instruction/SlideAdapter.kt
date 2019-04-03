package com.example.trailermovie.ui.instruction

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.trailermovie.R
import com.example.trailermovie.model.Slide
import kotlinx.android.synthetic.main.instroduction_page.view.*

class SlideAdapter(var listSlide: ArrayList<Slide>, var context: Context) : PagerAdapter() {

    private val listImage: IntArray = intArrayOf(R.drawable.logo, R.drawable.logo, R.drawable.logo)
    private val listTitle = arrayListOf("Teste 1", "Teste 2", "Teste 3")
    private val listDescription = arrayListOf("Teste 1", "Teste 2", "Teste 3")

    override fun getCount(): Int {
        return listTitle.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return (view == `object` as LinearLayout)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        var inflater = container.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var view = inflater?.inflate(R.layout.instroduction_page, container, false)
        view.imgLogo.setImageResource(listImage[position])
        view.txtTitle.text = listTitle[position]
        view.txtDescription.text = listDescription[position]
        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as LinearLayout)
    }

    fun addIndicator(position: Int) {
        var layout = (context as Activity).findViewById(R.id.dotsLayout) as LinearLayout
        layout.removeAllViews()

        for ((index, value) in listTitle.withIndex()) {

            var text = TextView(context)
            text.text = Html.fromHtml("&#8226")
            text.textSize = 35f

            if(index == position){
                text.setTextColor(Color.WHITE)
            }else{
                text.setTextColor(Color.GRAY)
            }

            layout.addView(text)
        }
    }

    var litener = object: ViewPager.OnPageChangeListener{
        override fun onPageScrollStateChanged(state: Int) {

        }

        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

        }

        override fun onPageSelected(position: Int) {
            addIndicator(position)
        }
    }
}