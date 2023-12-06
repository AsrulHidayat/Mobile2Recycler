package com.example.recyclerview

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class video(
    val gambar: Int,
    val judul: String,
    val deskripsi: String,
    val videoId: Int,
    val audioId: Int
) : Parcelable
