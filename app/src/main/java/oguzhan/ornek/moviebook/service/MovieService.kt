package oguzhan.ornek.moviebook.service

import oguzhan.ornek.moviebook.model.Response
import oguzhan.ornek.moviebook.model.Upcoming
import retrofit2.Call
import retrofit2.http.GET

interface MovieService {

    @GET("movie/upcoming")
    suspend fun getUpcomingMovie() : Response

}