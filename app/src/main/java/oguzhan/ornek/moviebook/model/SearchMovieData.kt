package oguzhan.ornek.moviebook.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SearchMovieData(
    val id: Int,
    val title: String,
    val poster_path: String,
    val overview: String,
    val release_date: String,
    val vote_count: Int,
    val vote_average: Double,
) : Parcelable
