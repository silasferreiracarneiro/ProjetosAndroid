package com.silasferreira.youtube.ui.player

import android.os.Bundle
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.silasferreira.youtube.R
import com.silasferreira.youtube.utils.AppContants.Companion.VIDEO_ID
import kotlinx.android.synthetic.main.activity_player.*

class PlayerActivity : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {

    private var idVideo: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)

        idVideo = intent.extras.getString(VIDEO_ID)
        playerVideo.initialize(idVideo, this)
    }

    override fun onInitializationSuccess(p0: YouTubePlayer.Provider?, p1: YouTubePlayer?, p2: Boolean) {
        p1?.setFullscreen(true)
        p1?.setShowFullscreenButton(false)
        if(!p2){
            p1?.loadVideo(idVideo)
        }
    }

    override fun onInitializationFailure(p0: YouTubePlayer.Provider?, p1: YouTubeInitializationResult?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
