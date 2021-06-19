package oguzhan.ornek.moviebook.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import oguzhan.ornek.moviebook.model.MovieDetail
import oguzhan.ornek.moviebook.repository.MovieRepository
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel   @Inject constructor(
    private val movieRepository: MovieRepository

): ViewModel() {
    private val _movieDetailLiveData: MutableLiveData<MovieDetail> = MutableLiveData()
    val movieDetailLiveData: LiveData<MovieDetail> = _movieDetailLiveData

    private val _errorMessage: MutableLiveData<String> = MutableLiveData()
    val errorMessage: LiveData<String> = _errorMessage

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData(true)
    val isLoading: LiveData<Boolean> = _isLoading

    fun getMovieDetail( movieId : Int) = viewModelScope.launch{
        try {
            _movieDetailLiveData.value = movieRepository.getMovieDetail(movieId)
        }catch (exception:Exception){
            _errorMessage.value = exception.localizedMessage
        }finally {
            _isLoading.value = false
        }
    }
}