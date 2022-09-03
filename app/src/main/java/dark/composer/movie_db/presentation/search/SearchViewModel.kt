package dark.composer.movie_db.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dark.composer.movie_db.data.base.BaseNetworkResult
import dark.composer.movie_db.data.remote.models.response.pagination.movie.MoviePaginationResponse
import dark.composer.movie_db.domain.usecase.movie.MovieUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val useCase: MovieUseCase) : ViewModel() {
    private val _search = MutableSharedFlow<BaseNetworkResult<MoviePaginationResponse>>()
    val search = _search.asSharedFlow()

    fun getSearch(page:Int, query:String){
        viewModelScope.launch {
            useCase.getSearchMovie(page = page,query = query).onEach { result ->
                when(result){
                    is BaseNetworkResult.Error -> {
                        _search.emit(BaseNetworkResult.Error(result.message))
                    }
                    is BaseNetworkResult.Loading -> {
                        _search.emit(BaseNetworkResult.Loading(result.isLoading))
                    }
                    is BaseNetworkResult.Success -> {
                        result.data?.let {
                            _search.emit(BaseNetworkResult.Success(it))
                        }
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

}