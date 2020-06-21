package com.example.tictactoe

import android.content.Intent
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.media.SoundPool
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    private lateinit var mediaPlayer: MediaPlayer
    private  lateinit var soundPool: SoundPool
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        supportActionBar?.hide()
        //startActivity(Intent(this,BackgroundServices::class.java))
//        mediaPlayer=MediaPlayer.create(this,R.raw.song)
//        mediaPlayer.isLooping=true
//        mediaPlayer.start()
        val audioAttributes=AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build()
       soundPool=SoundPool.Builder()
                .setMaxStreams(1)
                .setAudioAttributes(audioAttributes)
                .build()
        val soundPop=soundPool.load(this,R.raw.button_pop,1)

        multi.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))

            soundPool.play(soundPop, 1F,1F,0,0,1F)

            overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left)
        }
        vsAndroid.setOnClickListener {
            soundPool.play(soundPop, 1F,1F,0,0,1F)

            Toast.makeText(this,"This feature will be implemented soon,Sorry for the delay",Toast.LENGTH_SHORT).show()
        }
        settings_btn.setOnClickListener {
            startActivity(Intent(this,SettingsActivity::class.java))
            soundPool.play(soundPop, 1F,1F,0,0,1F)

            overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left)
        }
        rate_us_btn.setOnClickListener {
            soundPool.play(soundPop, 1F,1F,0,0,1F)
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=$packageManager")))
        }

    }
//    override fun onPause() {
//        super.onPause()
//        mediaPlayer.stop()
//        mediaPlayer.release()
//    }
    override fun onDestroy() {
        super.onDestroy()
        soundPool.release()
        //soundPool=null
    }
}