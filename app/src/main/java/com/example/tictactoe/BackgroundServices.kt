package com.example.tictactoe

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.widget.Toast

class BackgroundServices :Service(){

     private lateinit var mediaPlayer:MediaPlayer
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        mediaPlayer=MediaPlayer.create(this,R.raw.song)
        mediaPlayer.isLooping=true
        //mediaPlayer.setVolume(100F,100F)

    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        //return super.onStartCommand(intent, flags, startId)
        mediaPlayer.start()
        Toast.makeText(applicationContext,"Playing Music",Toast.LENGTH_SHORT).show()
        return startId
    }

    override fun onDestroy() {
        mediaPlayer.stop()
        mediaPlayer.release()
    }
}