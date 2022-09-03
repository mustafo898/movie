package dark.composer.movie_db.data.repository

import dark.composer.movie_db.data.base.BaseNetworkResult
import dark.composer.movie_db.data.remote.ApiService
import dark.composer.movie_db.data.remote.models.response.pagination.movie.MoviePaginationResponse
import dark.composer.movie_db.domain.repository.search.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(private val service: ApiService) : SearchRepository {
    override fun getSearchMovies(
        page: Int,
        query: String
    ): Flow<BaseNetworkResult<MoviePaginationResponse>> {
        return flow {
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
    }
}