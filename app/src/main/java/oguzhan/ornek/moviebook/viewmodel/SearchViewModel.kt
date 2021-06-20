package oguzhan.ornek.moviebook.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import oguzhan.ornek.moviebook.model.SearchMovieData
import oguzhan.ornek.moviebook.repository.MovieRepository
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {
    private val _searchMovieLiveData: MutableLiveData<List<SearchMovieData>> = MutableLiveData()
    val searchMovieLiveData: LiveData<List<SearchMovieData>> = _searchMovieLiveData

    private val _errorMessage: MutableLiveData<String> = MutableLiveData()
    val errorMessage: LiveData<String> = _errorMessage

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData(true)
    val isLoading: LiveData<Boolean> = _isLoading


    fun getSearchedMovie(query: String) = viewModelScope.launch {
        try {
            _searchMovieLiveData.value = movieRepository.getSearchedMovie(query)
        } catch (exception: Exception) {
            _errorMessage.value = exception.localizedMessage
        } finally {
            _isLoading.value = false
        }
    }
}