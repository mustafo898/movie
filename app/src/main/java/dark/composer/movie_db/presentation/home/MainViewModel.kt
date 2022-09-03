package dark.composer.movie_db.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dark.composer.movie_db.data.base.BaseNetworkResult
import dark.composer.movie_db.data.remote.models.response.pagination.genre.GenreResponse
import dark.composer.movie_db.data.remote.models.response.pagination.movie.MoviePaginationResponse
import dark.composer.movie_db.domain.usecase.movie.MovieUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val useCase: MovieUseCase) : ViewModel() {
    private val _topRated = MutableSharedFlow<BaseNetworkResult<MoviePaginationResponse>>()
    val topRated = _topRated.asSharedFlow()

    private val _genres = MutableSharedFlow<BaseNetworkResult<GenreResponse>>()
    val genres = _genres.asSharedFlow()

    private val _movieByGenre = MutableSharedFlow<BaseNetworkResult<MoviePaginationResponse>>()
    val movieByGenre = _movieByGenre.asSharedFlow()

    fun getTopRated(page:Int){
        viewModelScope.launch {
            useCase.getTopRated(page = page).onEach { result ->
                when(result){
                    is BaseNetworkResult.Error -> {
                        _topRated.emit(BaseNetworkResult.Error(result.message))
                    }
                    is BaseNetworkResult.Loading -> {
                        _topRated.emit(BaseNetworkResult.Loading(result.isLoading))
                    }
                    is BaseNetworkResult.Success -> {
                        result.data?.let {
                            _topRated.emit(BaseNetworkResult.Success(it))
                        }
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    fun getMovieByGenre(id:Int){
        viewModelScope.launch {
            useCase.getMovieByGenre(id = id).onEach { result ->
                when(result){
                    is BaseNetworkResult.Error -> {
                        _movieByGenre.emit(BaseNetworkResult.Error(result.message))
                    }
                    is BaseNetworkResult.Loading -> {
                        _movieByGenre.emit(BaseNetworkResult.Loading(result.isLoading))
                    }
                    is BaseNetworkResult.Success -> {
                        _movieByGenre.emit(BaseNetworkResult.Success(result.data!!))
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    fun getGenres(){
        viewModelScope.launch {
            useCase.getGenres().onEach { result ->
                when(result){
                    is BaseNetworkResult.Error -> {
                        _genres.emit(BaseNetworkResult.Error(result.message))
                    }
                    is BaseNetworkResult.Loading -> {
                        _genres.emit(BaseNetworkResult.Loading(result.isLoading))
                    }
                    is BaseNetworkResult.Success -> {
                        _genres.emit(BaseNetworkResult.Success(result.data!!))
                    }
                }
            }.launchIn(viewModelScope)
        }
    }
}