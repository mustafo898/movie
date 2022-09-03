package dark.composer.movie_db.data.repository

import android.util.Log
import dark.composer.movie_db.data.base.BaseNetworkResult
import dark.composer.movie_db.data.remote.ApiService
import dark.composer.movie_db.data.remote.models.response.detail.movie.MovieDetail
import dark.composer.movie_db.data.remote.models.response.detail.video.VideoResponse
import dark.composer.movie_db.data.remote.models.response.pagination.cast.CastResponse
import dark.composer.movie_db.data.remote.models.response.pagination.genre.GenreResponse
import dark.composer.movie_db.data.remote.models.response.pagination.movie.MoviePaginationResponse
import dark.composer.movie_db.data.remote.models.response.pagination.review.ReviewPaginationResponse
import dark.composer.movie_db.domain.repository.movie.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import kotlin.math.log

class MovieRepositoryImpl @Inject constructor(private val service: ApiService) : MovieRepository {
    override fun getPopular(page: Int): Flow<BaseNetworkResult<MoviePaginationResponse>> = flow {
        val response = service.getPopularMovies(page = page)
        emit(BaseNetworkResult.Loading(true))
        if (response.isSuccessful) {
            emit(BaseNetworkResult.Loading(false))
            response.body()?.let {
                emit(BaseNetworkResult.Success(it))
            }
        } else {
            emit(BaseNetworkResult.Loading(false))
            emit(BaseNetworkResult.Error(response.message()))
        }
    }

    override fun getTopRated(page: Int): Flow<BaseNetworkResult<MoviePaginationResponse>> {
        return flow {
            val response = service.getTopRatedMovies(page = page)
            emit(BaseNetworkResult.Loading(true))
            if (response.isSuccessful) {
                emit(BaseNetworkResult.Loading(false))
                response.body()?.let {
                    emit(BaseNetworkResult.Success(it))
                    Log.d("EEEE", "getTopRated: $it")
                }
            } else {
                emit(BaseNetworkResult.Loading(false))
                emit(BaseNetworkResult.Error(response.message()))
            }
        }
    }

    override fun getUpcoming(page: Int): Flow<BaseNetworkResult<MoviePaginationResponse>> = flow {
        val response = service.getPopularMovies(page = page)
        emit(BaseNetworkResult.Loading(true))
        if (response.isSuccessful) {
            emit(BaseNetworkResult.Loading(false))
            response.body()?.let {
                emit(BaseNetworkResult.Success(it))
            }
        } else {
            emit(BaseNetworkResult.Loading(false))
            emit(BaseNetworkResult.Error(response.message()))
        }
    }

    override fun getNowPlaying(page: Int): Flow<BaseNetworkResult<MoviePaginationResponse>> = flow {
        val response = service.getNowPlayingMovies(page = page)
        emit(BaseNetworkResult.Loading(true))
        if (response.isSuccessful) {
            emit(BaseNetworkResult.Loading(false))
            response.body()?.let {
                emit(BaseNetworkResult.Success(it))
            }
        } else {
            emit(BaseNetworkResult.Loading(false))
            emit(BaseNetworkResult.Error(response.message()))
        }
    }


    override fun getSimilarMovieByID(
        page: Int,
        id: Long
    ): Flow<BaseNetworkResult<MoviePaginationResponse>> = flow {
        val response = service.getSimilarMovieById(page = page, movie_id = id)
        emit(BaseNetworkResult.Loading(true))
        if (response.isSuccessful) {
            emit(BaseNetworkResult.Loading(false))
            response.body()?.let {
                emit(BaseNetworkResult.Success(it))
            }
        } else {
            emit(BaseNetworkResult.Loading(false))
            emit(BaseNetworkResult.Error(response.message()))
        }
    }

    override fun getMoviesByID(id: Long): Flow<BaseNetworkResult<MovieDetail>> = flow {
        val response = service.getMovieDetailById(movie_id = id)
        emit(BaseNetworkResult.Loading(true))
        if (response.isSuccessful) {
            emit(BaseNetworkResult.Loading(false))
            response.body()?.let {
                emit(BaseNetworkResult.Success(it))
            }
        } else {
            emit(BaseNetworkResult.Loading(false))
            emit(BaseNetworkResult.Error(response.message()))
        }
    }


    override fun getRecommendMovies(
        id: Long,
        page: Int
    ): Flow<BaseNetworkResult<MoviePaginationResponse>> = flow {
        val response = service.getRecommendationsById(page = page, movie_id = id)
        emit(BaseNetworkResult.Loading(true))
        if (response.isSuccessful) {
            emit(BaseNetworkResult.Loading(false))
            response.body()?.let {
                emit(BaseNetworkResult.Success(it))
            }
        } else {
            emit(BaseNetworkResult.Loading(false))
            emit(BaseNetworkResult.Error(response.message()))
        }
    }

    override fun getPopularMoviesOfWeek(page: Int): Flow<BaseNetworkResult<MoviePaginationResponse>> =
        flow {
            val response = service.getPopularMoviesOfWeek(page = page)
            emit(BaseNetworkResult.Loading(true))
            if (response.isSuccessful) {
                emit(BaseNetworkResult.Loading(false))
                response.body()?.let {
                    emit(BaseNetworkResult.Success(it))
                }
            } else {
                emit(BaseNetworkResult.Loading(false))
                emit(BaseNetworkResult.Error(response.message()))
            }
        }

    override fun getSearchMovies(
        page: Int,
        query: String
    ): Flow<BaseNetworkResult<MoviePaginationResponse>> = flow {
        val response = service.getSearchMovies(page = page, query = query)
        emit(BaseNetworkResult.Loading(true))
        if (response.isSuccessful) {
            emit(BaseNetworkResult.Loading(false))
            response.body()?.let {
                emit(BaseNetworkResult.Success(it))
            }
        } else {
            emit(BaseNetworkResult.Loading(false))
            emit(BaseNetworkResult.Error(response.message()))
        }
    }

    override fun getGenre(): Flow<BaseNetworkResult<GenreResponse>> {
        return flow {
            val response = service.getGenres()
            emit(BaseNetworkResult.Loading(true))
            if (response.isSuccessful) {
                emit(BaseNetworkResult.Loading(false))
                response.body()?.let {
                    emit(BaseNetworkResult.Success(it))
                    Log.d("EEEE", "getGenre: $it")
                }
            } else {
                emit(BaseNetworkResult.Loading(false))
                emit(BaseNetworkResult.Error(response.message()))
            }
        }
    }

    override fun getCast(id: Long): Flow<BaseNetworkResult<CastResponse>> = flow {
        val response = service.getCreditsById(movie_id = id)
        emit(BaseNetworkResult.Loading(true))
        if (response.isSuccessful) {
            emit(BaseNetworkResult.Loading(false))
            response.body()?.let {
                emit(BaseNetworkResult.Success(it))
            }
        } else {
            emit(BaseNetworkResult.Loading(false))
            emit(BaseNetworkResult.Error(response.message()))
        }
    }

    override fun getReview(page: Int, id: Long): Flow<BaseNetworkResult<ReviewPaginationResponse>> =
        flow {
            val response = service.getReviewsById(movie_id = id, page = page)
            emit(BaseNetworkResult.Loading(true))
            if (response.isSuccessful) {
                emit(BaseNetworkResult.Loading(false))
                response.body()?.let {
                    emit(BaseNetworkResult.Success(it))
                }
            } else {
                emit(BaseNetworkResult.Loading(false))
                emit(BaseNetworkResult.Error(response.message()))
            }
        }

    override fun getVideoByID(id: Long): Flow<BaseNetworkResult<VideoResponse>> = flow {
        val response = service.getVideoById(movie_id = id)
        emit(BaseNetworkResult.Loading(true))
        if (response.isSuccessful) {
            emit(BaseNetworkResult.Loading(false))
            response.body()?.let {
                emit(BaseNetworkResult.Success(it))
            }
        } else {
            emit(BaseNetworkResult.Loading(false))
            emit(BaseNetworkResult.Error(response.message()))
        }
    }

    override fun getMoviesByGenre(id: Int): Flow<BaseNetworkResult<MoviePaginationResponse>> =
        flow {
            val response = service.getMoviesByGenre(genreId = id)
            emit(BaseNetworkResult.Loading(true))
            if (response.isSuccessful) {
                emit(BaseNetworkResult.Loading(false))
                response.body()?.let {
                    emit(BaseNetworkResult.Success(it))
                }
            } else {
                emit(BaseNetworkResult.Loading(false))
                emit(BaseNetworkResult.Error(response.message()))
            }
        }
}