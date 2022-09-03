package dark.composer.movie_db.domain.usecase.movie

import dark.composer.movie_db.data.base.BaseNetworkResult
import dark.composer.movie_db.data.remote.models.response.detail.movie.MovieDetail
import dark.composer.movie_db.data.remote.models.response.detail.video.VideoResponse
import dark.composer.movie_db.data.remote.models.response.pagination.cast.CastResponse
import dark.composer.movie_db.data.remote.models.response.pagination.genre.GenreResponse
import dark.composer.movie_db.data.remote.models.response.pagination.movie.MoviePaginationResponse
import dark.composer.movie_db.data.remote.models.response.pagination.review.ReviewPaginationResponse
import dark.composer.movie_db.domain.repository.movie.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieUseCase @Inject constructor(
    private val repo: MovieRepository
) {
    suspend fun getPopular(page: Int): Flow<BaseNetworkResult<MoviePaginationResponse>> {
        return repo.getPopular(page = page)
    }

    suspend fun getMovieByGenre(id: Int): Flow<BaseNetworkResult<MoviePaginationResponse>> {
        return repo.getMoviesByGenre(id = id)
    }

    suspend fun getTopRated(page: Int): Flow<BaseNetworkResult<MoviePaginationResponse>> {
        return repo.getTopRated(page = page)
    }

    suspend fun getNowPLaying(page: Int): Flow<BaseNetworkResult<MoviePaginationResponse>> {
        return repo.getNowPlaying(page = page)
    }

    suspend fun getUpcoming(page: Int): Flow<BaseNetworkResult<MoviePaginationResponse>> {
        return repo.getUpcoming(page = page)
    }

    suspend fun getPopularMoviesOfWeek(page: Int): Flow<BaseNetworkResult<MoviePaginationResponse>> {
        return repo.getPopularMoviesOfWeek(page = page)
    }

    suspend fun getSimilarMoviesById(
        page: Int,
        id: Long
    ): Flow<BaseNetworkResult<MoviePaginationResponse>> {
        return repo.getSimilarMovieByID(page = page, id = id)
    }

    suspend fun getSearchMovie(
        page: Int,
        query: String
    ): Flow<BaseNetworkResult<MoviePaginationResponse>> {
        return repo.getSearchMovies(page = page, query = query)
    }

    suspend fun getRecommendedMovie(
        page: Int,
        id: Long
    ): Flow<BaseNetworkResult<MoviePaginationResponse>> {
        return repo.getRecommendMovies(page = page, id = id)
    }

    suspend fun getGenres(): Flow<BaseNetworkResult<GenreResponse>> {
        return repo.getGenre()
    }

    suspend fun getDetail(
        id: Long,
    ): Flow<BaseNetworkResult<MovieDetail>> {
        return repo.getMoviesByID(id = id)
    }

    suspend fun getReview(
        id: Long,
        page: Int
    ): Flow<BaseNetworkResult<ReviewPaginationResponse>> {
        return repo.getReview(id = id, page = page)
    }

    suspend fun getCast(
        id: Long,
    ): Flow<BaseNetworkResult<CastResponse>> {
        return repo.getCast(id = id)
    }

    suspend fun getVideo(
        id: Long,
    ): Flow<BaseNetworkResult<VideoResponse>> {
        return repo.getVideoByID(id = id)
    }
}