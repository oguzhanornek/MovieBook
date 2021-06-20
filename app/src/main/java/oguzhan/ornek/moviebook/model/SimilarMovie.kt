package oguzhan.ornek.moviebook.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SimilarMovie(
    val id: Int,
    val poster_path: String,
    val title: String

) : Parcelable
