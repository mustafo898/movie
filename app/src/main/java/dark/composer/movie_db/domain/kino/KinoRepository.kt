package dark.composer.movie_db.domain.kino

import dark.composer.movie_db.data.base.BaseNetworkResult
import dark.composer.movie_db.data.models.kino.movie_details.response.MovieDetailResponse
import dark.composer.movie_db.data.models.kino.popular.response.ResultX
import dark.composer.movie_db.domain.main.MainRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface KinoRepository {
    suspend fun getMoviesByGenres(id:Long): Flow<BaseNetworkResult<MovieDetailResponse>>
}