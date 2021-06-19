package oguzhan.ornek.moviebook.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import oguzhan.ornek.moviebook.model.Popular
import oguzhan.ornek.moviebook.model.SimilarMovie
import oguzhan.ornek.moviebook.repository.MovieRepository
import javax.inject.Inject

@HiltViewModel
class SimilarViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {
    private val _similarMoviesLiveData: MutableLiveData<List<SimilarMovie>> = MutableLiveData()
    val similarMoviesLiveData: LiveData<List<SimilarMovie>> = _similarMoviesLiveData

    private val _errorMessage: MutableLiveData<String> = MutableLiveData()
    val errorMessage: LiveData<String> = _errorMessage

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData(true)
    val isLoading: LiveData<Boolean> = _isLoading

    fun getSimilarMovie(movieId : Int) = viewModelScope.launch{
        try {
            _similarMoviesLiveData.value = movieRepository.getSimilarMovie(movieId)
        }catch (exception:Exception){
            _errorMessage.value = exception.localizedMessage
        }finally {
            _isLoading.value = false
        }
    }
}