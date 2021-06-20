package oguzhan.ornek.moviebook.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import oguzhan.ornek.moviebook.model.Popular
import oguzhan.ornek.moviebook.repository.MovieRepository
import javax.inject.Inject

@HiltViewModel
class PopularViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {


    private val _popularMoviesLiveData: MutableLiveData<List<Popular>> = MutableLiveData()
    val popularMoviesLiveData: LiveData<List<Popular>> = _popularMoviesLiveData

    private val _errorMessage: MutableLiveData<String> = MutableLiveData()
    val errorMessage: LiveData<String> = _errorMessage

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData(true)
    val isLoading: LiveData<Boolean> = _isLoading

    fun getPopularMovie() = viewModelScope.launch {
        try {
            _popularMoviesLiveData.value = movieRepository.getPopularMovie()
        } catch (exception: Exception) {
            _errorMessage.value = exception.localizedMessage
        } finally {
            _isLoading.value = false
        }
    }
}