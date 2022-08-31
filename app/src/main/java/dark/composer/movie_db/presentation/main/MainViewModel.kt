package dark.composer.movie_db.presentation.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dark.composer.movie_db.data.base.BaseNetworkResult
import dark.composer.movie_db.data.models.actor.response.Result
import dark.composer.movie_db.data.models.kino.genres.response.Genre
import dark.composer.movie_db.data.models.kino.popular.response.ResultX
import dark.composer.movie_db.domain.main.MainUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val useCase: MainUseCase) : ViewModel() {
    private val movieList = MutableLiveData<List<ResultX>>()
    val moviesListLiveData: LiveData<List<ResultX>> get() = movieList

    private val moviesByGenreList = MutableLiveData<List<ResultX>>()
    val moviesByGenreListLiveData: LiveData<List<ResultX>> get() = moviesByGenreList

    private val moviesGenreList = MutableLiveData<List<Genre>>()
    val moviesGenreListLiveData: LiveData<List<Genre>> get() = moviesGenreList

    private val topRated = MutableLiveData<List<ResultX>>()
    val topRatedListLiveData: LiveData<List<ResultX>> get() = topRated

    private val upcomingList = MutableLiveData<List<ResultX>>()
    val upcomingListLiveData: LiveData<List<ResultX>> get() = upcomingList

    private val popularList = MutableLiveData<List<ResultX>>()
    val popularListLiveData: LiveData<List<ResultX>> get() = popularList

    private val actorsList = MutableLiveData<List<Result>>()
    val actorsListLiveData: LiveData<List<Result>> get() = actorsList

    private val _isLoadingLiveData = MutableLiveData<Boolean>()
    val isLoadingLiveData: LiveData<Boolean> get() = _isLoadingLiveData

    fun getMovies() {
        viewModelScope.launch {
            useCase.getAllNewMovies().catch{ t->
                //xatolik beriladi
//                Log.d("DDDD", "Xato: $t")
            }.collect { result ->
                when (result) {
                    is BaseNetworkResult.Success -> {
                        result.data?.let { list ->
//                            Log.d("DDDD", "get: $list")
                            movieList.value = list
                        }
                    }
                    is BaseNetworkResult.Error -> {
                        //network error
//                        Log.d("DDDD", "getServicesResponse: xatooooooo movies ${result.message}")
                    }
                    is BaseNetworkResult.Loading -> {
                        //loading
                        result.isLoading?.let {
                            _isLoadingLiveData.value = it
                        }
                    }
                    else -> {}
                }
            }
        }
    }

    fun getPopular() {
        viewModelScope.launch {
            useCase.getPopularMovies().catch {t->
//                Log.d("DDDD", "Xato: $t")
            }.collect { result ->
                when (result) {
                    is BaseNetworkResult.Success -> {
                        result.data?.let { list ->
//                            Log.d("DDDD", "getServicesResponse: $list")
                            popularList.value = list
                        }
                    }
                    is BaseNetworkResult.Error -> {
                        //network error
//                        Log.d("DDDD", "getServicesResponse: xatooooooo popular")
                    }
                    is BaseNetworkResult.Loading -> {
                        //loading
                        result.isLoading?.let {
                            _isLoadingLiveData.value = it
                        }
                    }
                    else -> {}
                }
            }
        }
    }

    fun getActors() {
        viewModelScope.launch {
            useCase.getActors().catch {t->
//                Log.d("DDDD", "Xato: $t")
            }.collect { result ->
                when (result) {
                    is BaseNetworkResult.Success -> {
                        result.data?.let { list ->
//                            Log.d("DDDD", "getServicesResponse: $list")
                            actorsList.value = list
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
                    else -> {}
                }
            }
        }
    }

    fun getUpcoming() {
        viewModelScope.launch {
            useCase.getUpcomingMovies().catch {t->
//                Log.d("DDDD", "Xato: $t")
            }.collect { result ->
                when (result) {
                    is BaseNetworkResult.Success -> {
                        result.data?.let { list ->
//                            Log.d("DDDD", "getServicesResponse: $list")
                            upcomingList.value = list
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

    fun getTopRated() {
        viewModelScope.launch {
            useCase.getTopRatedMovies().catch {t->
//                Log.d("DDDD", "Xato: $t")
            }.collect { result ->
                when (result) {
                    is BaseNetworkResult.Success -> {
                        result.data?.let { list ->
//                            Log.d("DDDD", "getServicesResponse: $list")
                            topRated.value = list
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

    fun getMoviesByGenres(id:Int) {
        viewModelScope.launch {
            useCase.getMoviesByGenre(id).catch {t->
//                Log.d("DDDD", "Xato: $t")
            }.collect { result ->
                when (result) {
                    is BaseNetworkResult.Success -> {
                        result.data?.let { list ->
                            list.forEach {
//                                Log.d("DDDD", "getServicesResponse: ${it.original_title}")
                            }
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

    fun getMoviesGenres() {
        viewModelScope.launch {
            useCase.getMoviesGenre().catch {t->
//                Log.d("DDDD", "Xato: $t")
            }.collect { result ->
                when (result) {
                    is BaseNetworkResult.Success -> {
                        result.data?.let { list ->
//                            Log.d("DDDD", "getServicesResponse: $list")
                            moviesGenreList.value = list
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