package com.example.recyclerview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var videoAdapter: ListAdapter
    private val videoList = ArrayList<video>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.vv_video)
        recyclerView.layoutManager = LinearLayoutManager(this)

        videoList.addAll(getList())

        videoAdapter = ListAdapter(videoList)
        recyclerView.adapter = videoAdapter
    }

    override fun onResume() {
        super.onResume()
        if (videoAdapter.isAudioPlaying()) {
            // Mulai pemutaran audio di sini jika diperlukan.
        }
    }

    override fun onPause() {
        super.onPause()
        videoAdapter.stopAudio()
    }

    override fun onDestroy() {
        super.onDestroy()
        videoAdapter.stopAudio()
    }

    private fun getList(): ArrayList<video> {
        val dummyData = ArrayList<video>()

        val judulVideoArray = resources.getStringArray(R.array.judul_video)
        val deskripsiArray = resources.getStringArray(R.array.data_dekripsi)
        val gambarArray = resources.obtainTypedArray(R.array.data_gambar)
        val audioIdArray = resources.getIntArray(R.array.audio_id)
        val videoIdArray = resources.obtainTypedArray(R.array.video_id)

        for (i in judulVideoArray.indices) {
            dummyData.add(
                video(
                    gambarArray.getResourceId(i, -1),
                    judulVideoArray[i],
                    deskripsiArray[i],
                    videoIdArray.getResourceId(i, -1),
                    audioIdArray[i]
                )
            )
        }

        gambarArray.recycle()
        videoIdArray.recycle()

        return dummyData
    }
}
