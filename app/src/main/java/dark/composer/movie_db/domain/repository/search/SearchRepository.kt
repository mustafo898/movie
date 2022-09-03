package dark.composer.movie_db.domain.repository.search

import dark.composer.movie_db.data.base.BaseNetworkResult
import dark.composer.movie_db.data.remote.models.response.pagination.movie.MoviePaginationResponse
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    fun getSearchMovies(page: Int, query: String): Flow<BaseNetworkResult<MoviePaginationResponse>>
}