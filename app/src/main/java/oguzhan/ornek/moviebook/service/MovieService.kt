package oguzhan.ornek.moviebook.service

import oguzhan.ornek.moviebook.model.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    @GET("movie/upcoming")
    suspend fun getUpcomingMovie() : Response

    @GET("movie/popular")
    suspend fun getPopularMovie() : PopularResponse

    @GET("movie/{movie_id}")
    suspend fun getMoviesDetail(@Path("movie_id") movie_id : Int) : MovieDetail

    @GET("movie/{movie_id}/similar")
    suspend fun getSimilar(@Path("movie_id") movie_id: Int) : SimilarResponse

}
