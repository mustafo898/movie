package dark.composer.movie_db.domain.repository.movie

import androidx.lifecycle.ViewModel
import dark.composer.movie_db.data.base.BaseNetworkResult
import dark.composer.movie_db.data.remote.models.response.detail.movie.MovieDetail
import dark.composer.movie_db.data.remote.models.response.detail.video.VideoResponse
import dark.composer.movie_db.data.remote.models.response.pagination.cast.CastResponse
import dark.composer.movie_db.data.remote.models.response.pagination.genre.GenreResponse
import dark.composer.movie_db.data.remote.models.response.pagination.movie.MoviePaginationResponse
import dark.composer.movie_db.data.remote.models.response.pagination.review.ReviewPaginationResponse
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getPopular(page: Int): Flow<BaseNetworkResult<MoviePaginationResponse>>
    fun getTopRated(page: Int): Flow<BaseNetworkResult<MoviePaginationResponse>>
    fun getUpcoming(page: Int): Flow<BaseNetworkResult<MoviePaginationResponse>>
    fun getNowPlaying(page: Int): Flow<BaseNetworkResult<MoviePaginationResponse>>
    fun getSimilarMovieByID(page: Int, id: Long): Flow<BaseNetworkResult<MoviePaginationResponse>>
    fun getMoviesByID(id: Long): Flow<BaseNetworkResult<MovieDetail>>
    fun getRecommendMovies(id: Long, page: Int): Flow<BaseNetworkResult<MoviePaginationResponse>>
    fun getPopularMoviesOfWeek(page: Int): Flow<BaseNetworkResult<MoviePaginationResponse>>
    fun getSearchMovies(page: Int, query: String): Flow<BaseNetworkResult<MoviePaginationResponse>>
    fun getGenre(): Flow<BaseNetworkResult<GenreResponse>>
    fun getCast(id: Long): Flow<BaseNetworkResult<CastResponse>>
    fun getReview(page: Int, id: Long): Flow<BaseNetworkResult<ReviewPaginationResponse>>
    fun getVideoByID(id: Long): Flow<BaseNetworkResult<VideoResponse>>
    fun getMoviesByGenre(id: Int): Flow<BaseNetworkResult<MoviePaginationResponse>>
}