package com.example.trailermovie.ui.instruction

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.paolorotolo.appintro.AppIntro
import android.graphics.Color.parseColor
import com.github.paolorotolo.appintro.AppIntroFragment
import androidx.databinding.adapters.ImageViewBindingAdapter.setImageDrawable
import com.example.trailermovie.R
import com.github.paolorotolo.appintro.AppIntro2
import com.github.paolorotolo.appintro.model.SliderPage
import kotlinx.android.synthetic.main.instroduction_page.view.*


class InstructionActivity : AppIntro() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(com.example.trailermovie.R.layout.activity_instruction)

        /*var adapter = IntroductionAdapter(arrayListOf(), this)
        viewPager.adapter = adapter
        viewPager.addOnPageChangeListener(adapter.litener)
        adapter.addIndicator(1)*/

// Observe aqui que NÃO usamos setContentView ();

        // Adicione seus fragmentos de slides aqui.
        // AppIntro irá gerar automaticamente o indicador de pontos e botões.
        /*addSlide(firstFragment)
        addSlide(secondFragment)
        addSlide(thirdFragment)
        addSlide(fourthFragment)*/

        // Em vez de fragmentos, você também pode usar nosso slide padrão.
        // Basta criar um `SliderPage` e fornecer título, descrição, fundo e imagem.
        // AppIntro fará o resto.
        var sliderPage = SliderPage()
        sliderPage.title = "Teste"
        sliderPage.description = "Teste"
        sliderPage.imageDrawable = R.drawable.logo
        sliderPage.bgColor = R.color.primary_dark_material_dark
        addSlide(AppIntroFragment.newInstance(sliderPage))

        sliderPage = SliderPage()
        sliderPage.title = "Teste"
        sliderPage.description = "Teste"
        sliderPage.imageDrawable = R.drawable.logo
        sliderPage.bgColor = R.color.primary_dark_material_dark
        addSlide(AppIntroFragment.newInstance(sliderPage))

        // MÉTODOS OPCIONAIS
        // Substituir a cor da barra / separador.
        /*setBarColor(Color.parseColor("#3F51B5"))
        setSeparatorColor(Color.parseColor("#2196F3"))

        // Ocultar o botão Ignorar / Concluído.
        showSkipButton(false)
        isProgressButtonEnabled = false

        // Ligue a vibração e ajuste a intensidade.
        // NOTA: provavelmente você precisará pedir permissão ao VIBRATE no Manifest.
        setVibrate(true)
        setVibrateIntensity(30)*/

    }
}
