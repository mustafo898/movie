package dark.composer.movie_db.domain.usecase.search

import dark.composer.movie_db.data.base.BaseNetworkResult
import dark.composer.movie_db.data.remote.models.response.pagination.movie.MoviePaginationResponse
import dark.composer.movie_db.domain.repository.search.SearchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchUseCase @Inject constructor(
    private val repo: SearchRepository
) {
    suspend fun getPopular(
        page: Int,
        query: String
    ): Flow<BaseNetworkResult<MoviePaginationResponse>> {
        return repo.getSearchMovies(page = page, query = query)
    }
}