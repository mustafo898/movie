package dark.composer.movie_db.data.models.main

import android.util.Log
import dark.composer.movie_db.data.base.BaseNetworkResult
import dark.composer.movie_db.data.models.actor.response.Result
import dark.composer.movie_db.data.models.kino.genres.response.Genre
import dark.composer.movie_db.data.models.kino.popular.response.ResultX
import dark.composer.movie_db.data.retrofit.ApiService
import dark.composer.movie_db.domain.main.MainRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(private val service: ApiService) : MainRepository {

    override suspend fun getAllNewMovies(): Flow<BaseNetworkResult<List<ResultX>>> {
        return flow {
            emit(BaseNetworkResult.Loading(true))
            val response = service.getAllNewMovies()
            emit(BaseNetworkResult.Loading(false))
            if (response.code() == 200) {
                response.body()?.let {
                    emit(BaseNetworkResult.Success(it.results))
                    it.results.forEach { t->
                        Log.d("QQQQQQQ", "getAllNewMovies: ${t.original_title}")
                    }
                }
            } else {
                emit(BaseNetworkResult.Error(response.message()))
            }
        }
    }

    override suspend fun getPopularMovies(): Flow<BaseNetworkResult<List<ResultX>>> {
        return flow {
            emit(BaseNetworkResult.Loading(true))
            val response = service.getAllPopularMovies()
            emit(BaseNetworkResult.Loading(false))
            if (response.code() == 200) {
                response.body()?.let {
                    emit(BaseNetworkResult.Success(it.results))
                }
            } else {
                emit(BaseNetworkResult.Error("Xatolik"))
            }
        }
    }

    override suspend fun getActors(): Flow<BaseNetworkResult<List<Result>>> {
        return flow {
            emit(BaseNetworkResult.Loading(true))
            val response = service.getAllFamousPersons()
            emit(BaseNetworkResult.Loading(false))
            if (response.code() == 200) {
                response.body()?.let {
                    emit(BaseNetworkResult.Success(it.results))
                }
            } else {
                emit(BaseNetworkResult.Error("Xatolik"))
            }
        }
    }

    override suspend fun getTopRated(): Flow<BaseNetworkResult<List<ResultX>>> {
        return flow {
            emit(BaseNetworkResult.Loading(true))
            val response = service.getAllTopRatedMovies()
            emit(BaseNetworkResult.Loading(false))
            if (response.code() == 200) {
                response.body()?.let {
                    emit(BaseNetworkResult.Success(it.results))
                }
            } else {
                emit(BaseNetworkResult.Error("Xatolik"))
            }
        }
    }

    override suspend fun getUpcoming(): Flow<BaseNetworkResult<List<ResultX>>> {
        return flow {
            emit(BaseNetworkResult.Loading(true))
            val response = service.getAlUpcomingMovies()
            emit(BaseNetworkResult.Loading(false))
            if (response.code() == 200) {
                response.body()?.let {
                    emit(BaseNetworkResult.Success(it.results))
                }
            } else {
                emit(BaseNetworkResult.Error("Xatolik"))
            }
        }
    }

    override suspend fun getMoviesByGenres(id: Int): Flow<BaseNetworkResult<List<ResultX>>> {
        return flow {
            emit(BaseNetworkResult.Loading(true))
            val response = service.getMoviesByGenre(id)
            emit(BaseNetworkResult.Loading(false))
            if (response.code() == 200) {
                response.body()?.let {
                    emit(BaseNetworkResult.Success(it.results))
                    it.results.forEach { t->
//                        Log.d("QQQQQQQ", "getAllNewMovies: ${t.original_title}")
                    }
                }
            } else {
                emit(BaseNetworkResult.Error("Xatolik"))
            }
        }
    }

    override suspend fun getMoviesGenres(): Flow<BaseNetworkResult<List<Genre>>> {
        return flow {
            emit(BaseNetworkResult.Loading(true))
            val response = service.getAllMoviesGenres()
            emit(BaseNetworkResult.Loading(false))
            if (response.code() == 200) {
                response.body()?.let {
                    emit(BaseNetworkResult.Success(it.genres))
                    it.genres.forEach { t->
//                        Log.d("QQQQQQQ", "getAllNewMovies: ${t.name}")
                    }
                }
            } else {
                emit(BaseNetworkResult.Error("Xatolik"))
            }
        }
    }
}