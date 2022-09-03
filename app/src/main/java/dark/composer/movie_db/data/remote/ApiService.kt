package dark.composer.movie_db.data.remote

import dark.composer.movie_db.data.remote.models.response.pagination.cast.CastResponse
import dark.composer.movie_db.data.remote.models.response.detail.movie.MovieDetail
import dark.composer.movie_db.data.remote.models.response.detail.person.PersonDetail
import dark.composer.movie_db.data.remote.models.response.pagination.genre.GenreResponse
import dark.composer.movie_db.data.remote.models.response.pagination.movie.MoviePaginationResponse
import dark.composer.movie_db.data.remote.models.response.pagination.review.ReviewPaginationResponse
import dark.composer.movie_db.data.remote.models.response.detail.video.VideoResponse
import dark.composer.movie_db.data.remote.models.response.pagination.person.PersonPaginationResponse
import dark.composer.movie_db.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("api_key") closeReason: String = Constants.TOKEN,
        @Query("page") page: Int,
    ): Response<MoviePaginationResponse>

    @GET("genre/movie/list")
    suspend fun getGenres(
        @Query("api_key") closeReason: String = Constants.TOKEN,
    ): Response<GenreResponse>

    @GET("discover/movie")
    suspend fun getMoviesByGenre(
        @Query("with_genres") genreId: Int,
        @Query("api_key") closeReason: String = Constants.TOKEN,
    ): Response<MoviePaginationResponse>

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") closeReason: String = Constants.TOKEN,
        @Query("page") page: Int,
    ): Response<MoviePaginationResponse>

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("api_key") closeReason: String = Constants.TOKEN,
        @Query("page") page: Int,
    ): Response<MoviePaginationResponse>

    @GET("movie/upcoming")
    suspend fun getAlUpcomingMovies(
        @Query("api_key") closeReason: String = Constants.TOKEN,
        @Query("page") page: Int,
    ): Response<MoviePaginationResponse>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetailById(
        @Path("movie_id") movie_id: Long,
        @Query("api_key") closeReason: String = Constants.TOKEN,
    ): Response<MovieDetail>

    @GET("movie/{movie_id}/similar")
    suspend fun getSimilarMovieById(
        @Path("movie_id") movie_id: Long,
        @Query("api_key") closeReason: String = Constants.TOKEN,
        @Query("page") page: Int,
    ): Response<MoviePaginationResponse>

    @GET("movie/{movie_id}/videos")
    suspend fun getVideoById(
        @Path("movie_id") movie_id: Long,
        @Query("api_key") closeReason: String = Constants.TOKEN,
    ): Response<VideoResponse>

    @GET("movie/{movie_id}/recommendations")
    suspend fun getRecommendationsById(
        @Path("movie_id") movie_id: Long,
        @Query("api_key") closeReason: String = Constants.TOKEN,
        @Query("page") page: Int,
    ): Response<MoviePaginationResponse>

    @GET("movie/{movie_id}/reviews")
    suspend fun getReviewsById(
        @Path("movie_id") movie_id: Long,
        @Query("api_key") closeReason: String = Constants.TOKEN,
        @Query("page") page: Int,
    ): Response<ReviewPaginationResponse>

    @GET("movie/{movie_id}/credits")
    suspend fun getCreditsById(
        @Path("movie_id") movie_id: Long,
        @Query("api_key") closeReason: String = Constants.TOKEN,
    ): Response<CastResponse>

    @GET("trending/person/week")
    suspend fun getPopularPersonOfWeek(
        @Query("api_key") closeReason: String = Constants.TOKEN,
        @Query("page") page:Int
    ): Response<PersonPaginationResponse>

    @GET("trending/person/week")
    suspend fun getPopularMoviesOfWeek(
        @Query("api_key") closeReason: String = Constants.TOKEN,
        @Query("page") page:Int
    ): Response<MoviePaginationResponse>

    @GET("person/{person_id}")
    suspend fun getPersonDetailById(
        @Path("person_id") person_id: Long,
        @Query("api_key") closeReason: String = Constants.TOKEN,
    ): Response<PersonDetail>

    @GET("person/popular")
    suspend fun getFamousPersons(
        @Query("api_key") closeReason: String = Constants.TOKEN,
        @Query("page") page:Int
    ): Response<PersonPaginationResponse>

    @GET("search/movie")
    suspend fun getSearchMovies(
        @Query("api_key") closeReason: String = Constants.TOKEN,
        @Query("page") page:Int,
        @Query("query") query: String
    ): Response<MoviePaginationResponse>
}