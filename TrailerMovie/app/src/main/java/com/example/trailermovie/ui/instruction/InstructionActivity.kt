package com.example.trailermovie.ui.instruction

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.github.paolorotolo.appintro.AppIntro
import com.github.paolorotolo.appintro.AppIntroFragment
import com.example.trailermovie.R
import com.example.trailermovie.ui.login.LoginActivity
import com.github.paolorotolo.appintro.model.SliderPage


class InstructionActivity : AppIntro() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var sliderPage = SliderPage()
        sliderPage.title = getString(R.string.introducao)
        sliderPage.description = getString(R.string.leia_com_atencao)
        sliderPage.imageDrawable = R.drawable.logo
        sliderPage.bgColor = android.R.color.black
        addSlide(AppIntroFragment.newInstance(sliderPage))

        sliderPage = SliderPage()
        sliderPage.title = ""
        sliderPage.description = getString(R.string.apenas_conhecimento)
        sliderPage.imageDrawable = R.drawable.logo
        sliderPage.bgColor = android.R.color.black
        addSlide(AppIntroFragment.newInstance(sliderPage))

        sliderPage = SliderPage()
        sliderPage.title = getString(R.string.mostra_trailer)
        sliderPage.description = getString(R.string.usando_api_the_movie)
        sliderPage.imageDrawable = R.drawable.logo
        sliderPage.bgColor = android.R.color.black
        addSlide(AppIntroFragment.newInstance(sliderPage))

        sliderPage = SliderPage()
        sliderPage.title = getString(R.string.basta_continuar)
        sliderPage.description = getString(R.string.vamos_nessa)
        sliderPage.imageDrawable = R.drawable.logo
        sliderPage.bgColor = android.R.color.black
        addSlide(AppIntroFragment.newInstance(sliderPage))

        showSkipButton(true)
        isProgressButtonEnabled = true
    }

    override fun onDonePressed(currentFragment: Fragment?) {
        super.onDonePressed(currentFragment)
        startActivity(Intent(this, LoginActivity::class.java))
    }

    override fun onSkipPressed(currentFragment: Fragment?) {
        super.onSkipPressed(currentFragment)
        startActivity(Intent(this, LoginActivity::class.java))
    }
}
