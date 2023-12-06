package com.example.recyclerview

import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import video_Activity

class DetailActivity : AppCompatActivity() {

    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val receivedData = intent.getParcelableExtra<video>("shadow")
        val gambar: ImageView = findViewById(R.id.img_gambar)
        val judul: TextView = findViewById(R.id.tv_Judul)
        val description: TextView = findViewById(R.id.tv_description)
        val playButton: ImageView = findViewById(R.id.tombol_play)

        if (receivedData != null) {
            gambar.setImageResource(receivedData.gambar)
            judul.text = receivedData.judul
            description.text = receivedData.deskripsi

            playButton.setOnClickListener {
                val videoIntent = Intent(this, video_Activity::class.java)
                videoIntent.putExtra("videoId", receivedData.videoId)

                if (mediaPlayer.isPlaying) {
                    videoIntent.putExtra("isAudioPlaying", true)
                }

                startActivity(videoIntent)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        val isAudioPlaying = intent.getBooleanExtra("isAudioPlaying", false)

        if (isAudioPlaying) {
            val audioIdArray = resources.getIntArray(R.array.audio_id)
            val audioIndex = intent.getIntExtra("audioIndex", -1)

            if (audioIndex != -1 && audioIndex < audioIdArray.size) {
                mediaPlayer = MediaPlayer.create(this, audioIdArray[audioIndex])
                mediaPlayer.start()
            }

            mediaPlayer.start()
        }
    }

    override fun onPause() {
        super.onPause()
        mediaPlayer.stop()
        mediaPlayer.release()
    }
}
