package oguzhan.ornek.moviebook.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Upcoming(
    val id: Int,
    val original_language: String,
    val title: String,
    val overview: String,
    val poster_path: String,
    val release_date: String,
) : Parcelable


