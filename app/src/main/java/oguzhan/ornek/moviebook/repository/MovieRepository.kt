package oguzhan.ornek.moviebook.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import oguzhan.ornek.moviebook.model.*
import oguzhan.ornek.moviebook.service.MovieService
import javax.inject.Inject

class MovieRepository @Inject constructor(val movieService: MovieService) {

    suspend fun getUpcomingMovie(): List<Upcoming> = withContext(Dispatchers.IO) {
        return@withContext movieService.getUpcomingMovie().results
    }

    suspend fun getPopularMovie(): List<Popular> = withContext(Dispatchers.IO) {
        return@withContext movieService.getPopularMovie().results
    }

    suspend fun getMovieDetail(movieId: Int): MovieDetail = withContext(Dispatchers.IO) {
        return@withContext movieService.getMoviesDetail(movieId)
    }

    suspend fun getSimilarMovie(movieId: Int): List<SimilarMovie> = withContext(Dispatchers.IO) {
        return@withContext movieService.getSimilar(movieId).results
    }

    suspend fun getSearchedMovie(query: String): List<SearchMovieData> =
        withContext(Dispatchers.IO) {
            return@withContext movieService.getSearchedMovie(query).results
        }
}