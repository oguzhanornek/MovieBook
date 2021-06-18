package oguzhan.ornek.moviebook.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import oguzhan.ornek.moviebook.model.Popular
import oguzhan.ornek.moviebook.model.Upcoming
import oguzhan.ornek.moviebook.service.MovieService
import javax.inject.Inject

class MovieRepository @Inject constructor(val movieService: MovieService){

    suspend fun getUpcomingMovie() : List<Upcoming> = withContext(Dispatchers.IO){
        return@withContext movieService.getUpcomingMovie().results
    }

    suspend fun getPopularMovie() : List<Popular> = withContext(Dispatchers.IO){
        return@withContext movieService.getPopularMovie().results
    }
}