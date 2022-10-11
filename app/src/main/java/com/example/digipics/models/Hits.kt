package com.example.digipics.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Hits(
    var id: Long,
    var pageURL: String,
    var tags: String,
    var previewURL: String,
    var views: Long,
    var downloads: Long,
    var likes: Long,
    var comments: Long,
    var user: String
) : Parcelable