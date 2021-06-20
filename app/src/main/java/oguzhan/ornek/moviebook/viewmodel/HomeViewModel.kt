package oguzhan.ornek.moviebook.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import oguzhan.ornek.moviebook.model.Upcoming
import oguzhan.ornek.moviebook.repository.MovieRepository
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val _upComingMoviesLiveData: MutableLiveData<List<Upcoming>> = MutableLiveData()
    val upComingMoviesLiveData: LiveData<List<Upcoming>> = _upComingMoviesLiveData

    private val _errorMessage: MutableLiveData<String> = MutableLiveData()
    val errorMessage: LiveData<String> = _errorMessage

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData(true)
    val isLoading: LiveData<Boolean> = _isLoading


    fun getUpcomingMovie() = viewModelScope.launch {
        try {
            _upComingMoviesLiveData.value = movieRepository.getUpcomingMovie()
        } catch (exception: Exception) {
            _errorMessage.value = exception.localizedMessage
        } finally {
            _isLoading.value = false
        }
    }
}