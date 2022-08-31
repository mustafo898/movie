package dark.composer.movie_db.presentation.kino

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dark.composer.movie_db.data.base.BaseNetworkResult
import dark.composer.movie_db.data.models.kino.movie_details.response.MovieDetailResponse
import dark.composer.movie_db.data.models.kino.popular.response.ResultX
import dark.composer.movie_db.domain.kino.KinoUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val useCase: KinoUseCase) : ViewModel() {
    private val moviesByGenreList = MutableLiveData<MovieDetailResponse>()
    val moviesByGenreListLiveData: LiveData<MovieDetailResponse> get() = moviesByGenreList

    private val _isLoadingLiveData = MutableLiveData<Boolean>()
    val isLoadingLiveData: LiveData<Boolean> get() = _isLoadingLiveData

    fun getMoviesByGenres(id:Long) {
        viewModelScope.launch {
            useCase.getMoviesByGenre(id).catch {t->
//                Log.d("DDDD", "Xato: $t")
            }.collect { result ->
                when (result) {
                    is BaseNetworkResult.Success -> {
                        result.data?.let { list ->
//                            list.forEach {
////                                Log.d("DDDD", "getServicesResponse: ${it.original_title}")
//                            }
                            moviesByGenreList.value = list
                        }
                    }
                    is BaseNetworkResult.Error -> {
                        //network error
//                        Log.d("DDDD", "getServicesResponse: xatooooooo actor")
                    }
                    is BaseNetworkResult.Loading -> {
                        //loading
                        result.isLoading?.let {
                            _isLoadingLiveData.value = it
                        }
                    }
                }
            }
        }
    }
}