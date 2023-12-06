package com.example.recyclerview

import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListAdapter(private val videoList: List<video>) :
    RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

    private var mediaPlayer: MediaPlayer? = null
    private var isPlaying = false

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val gambar: ImageView = itemView.findViewById(R.id.img_view)
        val judulvideo: TextView = itemView.findViewById(R.id.tv_judul_video)
        val deskripsi: TextView = itemView.findViewById(R.id.tv_description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recycler_view, parent, false)
        return ListViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (gambar, judul, deskripsi, audioId) = videoList[position]
        holder.gambar.setImageResource(gambar)
        holder.judulvideo.text = judul
        holder.deskripsi.text = deskripsi

        holder.itemView.setOnClickListener {
            if (isPlaying) {
                stopAudio()
            } else {
                playAudio(audioId, holder)
            }
        }
    }

    override fun getItemCount(): Int = videoList.size

    fun isAudioPlaying(): Boolean {
        return isPlaying
    }

    private fun playAudio(audioId: Int, holder: ListViewHolder) {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer()
        } else {
            mediaPlayer?.reset()
        }

        try {
            val context = holder.itemView.context
            val assetFileDescriptor = context.resources.openRawResourceFd(audioId)
            mediaPlayer?.setDataSource(
                assetFileDescriptor.fileDescriptor,
                assetFileDescriptor.startOffset,
                assetFileDescriptor.length
            )
            assetFileDescriptor.close()

            mediaPlayer?.prepare()
            mediaPlayer?.start()
            isPlaying = true
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun stopAudio() {
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
        isPlaying = false
    }
}
