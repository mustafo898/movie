package dark.composer.movie_db.data.models.kino

import android.util.Log
import dark.composer.movie_db.data.base.BaseNetworkResult
import dark.composer.movie_db.data.models.actor.response.Result
import dark.composer.movie_db.data.models.kino.genres.response.Genre
import dark.composer.movie_db.data.models.kino.movie_details.response.MovieDetailResponse
import dark.composer.movie_db.data.models.kino.popular.response.ResultX
import dark.composer.movie_db.data.retrofit.ApiService
import dark.composer.movie_db.domain.kino.KinoRepository
import dark.composer.movie_db.domain.main.MainRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class KinoRepositoryImpl @Inject constructor(private val service: ApiService) : KinoRepository {

    override suspend fun getMoviesByGenres(id: Long): Flow<BaseNetworkResult<MovieDetailResponse>> {
        return flow {
            emit(BaseNetworkResult.Loading(true))
            val response = service.getMovieDetailById(id.toLong())
            emit(BaseNetworkResult.Loading(false))
            if (response.code() == 200) {
                response.body()?.let {
                    emit(BaseNetworkResult.Success(it))
                }
            } else {
                emit(BaseNetworkResult.Error("Xatolik"))
            }
        }
    }
}