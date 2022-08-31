package dark.composer.movie_db.domain.kino

import dark.composer.movie_db.data.base.BaseNetworkResult
import dark.composer.movie_db.data.models.kino.movie_details.response.MovieDetailResponse
import dark.composer.movie_db.data.models.kino.popular.response.ResultX
import dark.composer.movie_db.domain.main.MainRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class KinoUseCase @Inject constructor(private val mainRepository: KinoRepository){
    suspend fun getMoviesByGenre(id:Long): Flow<BaseNetworkResult<MovieDetailResponse>> {
        return mainRepository.getMoviesByGenres(id)
    }
}