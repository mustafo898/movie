package dark.composer.movie_db.presentation.detail

import android.content.LocusId
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dark.composer.movie_db.data.base.BaseNetworkResult
import dark.composer.movie_db.data.remote.models.response.detail.movie.MovieDetail
import dark.composer.movie_db.data.remote.models.response.detail.person.PersonDetail
import dark.composer.movie_db.data.remote.models.response.detail.video.VideoResponse
import dark.composer.movie_db.data.remote.models.response.pagination.cast.CastResponse
import dark.composer.movie_db.data.remote.models.response.pagination.movie.MoviePaginationResponse
import dark.composer.movie_db.data.remote.models.response.pagination.person.PersonPaginationResponse
import dark.composer.movie_db.data.remote.models.response.pagination.review.ReviewPaginationResponse
import dark.composer.movie_db.domain.usecase.movie.MovieUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val movieUseCase: MovieUseCase
) : ViewModel() {
    private val _movie = MutableSharedFlow<BaseNetworkResult<MovieDetail>>()
    val movie = _movie.asSharedFlow()

    private val _recommend = MutableSharedFlow<BaseNetworkResult<MoviePaginationResponse>>()
    val recommend = _recommend.asSharedFlow()

    private val _similar = MutableSharedFlow<BaseNetworkResult<MoviePaginationResponse>>()
    val similar = _similar.asSharedFlow()

    private val _person = MutableSharedFlow<BaseNetworkResult<CastResponse>>()
    val person = _person.asSharedFlow()

    private val _review = MutableSharedFlow<BaseNetworkResult<ReviewPaginationResponse>>()
    val review = _review.asSharedFlow()

    private val _video = MutableSharedFlow<BaseNetworkResult<VideoResponse>>()
    val video = _video.asSharedFlow()

    fun getGenres(id: Long){
        viewModelScope.launch {
            movieUseCase.getDetail(id = id).onEach { result ->
                when(result){
                    is BaseNetworkResult.Error -> {
                        _movie.emit(BaseNetworkResult.Error(result.message))
                    }
                    is BaseNetworkResult.Loading -> {
                        _movie.emit(BaseNetworkResult.Loading(result.isLoading))
                    }
                    is BaseNetworkResult.Success -> {
                        _movie.emit(BaseNetworkResult.Success(result.data!!))
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    fun getReview(id: Long){
        viewModelScope.launch {
            movieUseCase.getReview(id = id, page = 1).onEach { result ->
                when(result){
                    is BaseNetworkResult.Error -> {
                        _review.emit(BaseNetworkResult.Error(result.message))
                    }
                    is BaseNetworkResult.Loading -> {
                        _review.emit(BaseNetworkResult.Loading(result.isLoading))
                    }
                    is BaseNetworkResult.Success -> {
                        _review.emit(BaseNetworkResult.Success(result.data!!))
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    fun getCasts(id: Long){
        viewModelScope.launch {
            movieUseCase.getCast(id = id).onEach { result ->
                when(result){
                    is BaseNetworkResult.Error -> {
                        _person.emit(BaseNetworkResult.Error(result.message))
                    }
                    is BaseNetworkResult.Loading -> {
                        _person.emit(BaseNetworkResult.Loading(result.isLoading))
                    }
                    is BaseNetworkResult.Success -> {
                        _person.emit(BaseNetworkResult.Success(result.data!!))
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    fun getVideo(id: Long){
        viewModelScope.launch {
            movieUseCase.getVideo(id = id).onEach { result ->
                when(result){
                    is BaseNetworkResult.Error -> {
                        _video.emit(BaseNetworkResult.Error(result.message))
                    }
                    is BaseNetworkResult.Loading -> {
                        _video.emit(BaseNetworkResult.Loading(result.isLoading))
                    }
                    is BaseNetworkResult.Success -> {
                        _video.emit(BaseNetworkResult.Success(result.data!!))
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    fun getRecommendMovie(id: Long, page:Int){
        viewModelScope.launch {
            movieUseCase.getRecommendedMovie(id = id, page = page).onEach { result ->
                when(result){
                    is BaseNetworkResult.Error -> {
                        _recommend.emit(BaseNetworkResult.Error(result.message))
                    }
                    is BaseNetworkResult.Loading -> {
                        _recommend.emit(BaseNetworkResult.Loading(result.isLoading))
                    }
                    is BaseNetworkResult.Success -> {
                        _recommend.emit(BaseNetworkResult.Success(result.data!!))
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    fun getSimilarMovie(id: Long,page:Int){
        viewModelScope.launch {
            movieUseCase.getSimilarMoviesById(id = id, page = page).onEach { result ->
                when(result){
                    is BaseNetworkResult.Error -> {
                        _similar.emit(BaseNetworkResult.Error(result.message))
                    }
                    is BaseNetworkResult.Loading -> {
                        _similar.emit(BaseNetworkResult.Loading(result.isLoading))
                    }
                    is BaseNetworkResult.Success -> {
                        _similar.emit(BaseNetworkResult.Success(result.data!!))
                    }
                }
            }.launchIn(viewModelScope)
        }
    }
}