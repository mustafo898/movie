package dark.composer.movie_db.domain.main

import dark.composer.movie_db.data.base.BaseNetworkResult
import dark.composer.movie_db.data.models.actor.response.Result
import dark.composer.movie_db.data.models.kino.genres.response.Genre
import dark.composer.movie_db.data.models.kino.popular.response.ResultX
import kotlinx.coroutines.flow.Flow
import java.net.IDN

interface MainRepository {
    suspend fun getAllNewMovies(): Flow<BaseNetworkResult<List<ResultX>>>
    suspend fun getPopularMovies(): Flow<BaseNetworkResult<List<ResultX>>>
    suspend fun getActors(): Flow<BaseNetworkResult<List<Result>>>
    suspend fun getTopRated(): Flow<BaseNetworkResult<List<ResultX>>>
    suspend fun getUpcoming(): Flow<BaseNetworkResult<List<ResultX>>>
    suspend fun getMoviesByGenres(id:Int): Flow<BaseNetworkResult<List<ResultX>>>
    suspend fun getMoviesGenres(): Flow<BaseNetworkResult<List<Genre>>>
}