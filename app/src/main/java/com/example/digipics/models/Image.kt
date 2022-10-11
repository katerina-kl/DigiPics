package com.example.digipics.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Image(
    var total: Long,
    var totalHits: Long,
    var hits: ArrayList<Hits>
) : Parcelable

