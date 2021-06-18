package oguzhan.ornek.moviebook.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import oguzhan.ornek.moviebook.repository.MovieRepository
import oguzhan.ornek.moviebook.service.MovieService
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule  {
    @Provides
    @Singleton
    fun provideMovieRepository(movieService: MovieService) = MovieRepository(movieService)

}