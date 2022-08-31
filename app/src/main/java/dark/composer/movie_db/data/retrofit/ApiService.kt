package dark.composer.movie_db.data.retrofit

import dark.composer.movie_db.data.models.actor.response.ActorListResponse
import dark.composer.movie_db.data.models.actor.response.actor_detail.PersonResult
import dark.composer.movie_db.data.models.kino.genres.response.MoviesGenresResponse
import dark.composer.movie_db.data.models.kino.movie.response.MoviesResponse
import dark.composer.movie_db.data.models.kino.movie_details.response.MovieDetailResponse
import dark.composer.movie_db.data.models.kino.popular.response.PopularResponse
import dark.composer.movie_db.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/now_playing")
    suspend fun getAllNewMovies(
        @Query("api_key") closeReason: String = Constants.TOKEN,
    ): Response<MoviesResponse>

    @GET("genre/movie/list")
    suspend fun getAllMoviesGenres(
        @Query("api_key") closeReason: String = Constants.TOKEN,
    ): Response<MoviesGenresResponse>

    @GET("discover/movie")
    suspend fun getMoviesByGenre(
        @Query("with_genres") genreId: Int,
        @Query("api_key") closeReason: String = Constants.TOKEN,
    ): Response<MoviesResponse>

    @GET("trending/person/week")
    suspend fun getAllFamousPersons(
        @Query("api_key") closeReason: String = Constants.TOKEN,
    ): Response<ActorListResponse>

    @GET("movie/popular")
    suspend fun getAllPopularMovies(
        @Query("api_key") closeReason: String = Constants.TOKEN,
    ): Response<PopularResponse>

    @GET("movie/top_rated")
    suspend fun getAllTopRatedMovies(
        @Query("api_key") closeReason: String = Constants.TOKEN,
    ): Response<MoviesResponse>

    @GET("movie/upcoming")
    suspend fun getAlUpcomingMovies(
        @Query("api_key") closeReason: String = Constants.TOKEN,
    ): Response<MoviesResponse>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetailById(
        @Path("movie_id") movie_id: Long,
        @Query("api_key") closeReason: String = Constants.TOKEN,
    ): Response<MovieDetailResponse>

    @GET("person/{person_id}")
    suspend fun getPersonDetailById(
        @Path("person_id") person_id: Long,
        @Query("api_key") closeReason: String = Constants.TOKEN,
    ): Response<PersonResult>

//    @GET("movie/{movie_id}/videos")
//    suspend fun getMovieTrailerListById(
//        @Path("movie_id") movie_id: Long,
//        @Query("api_key") closeReason: String = Constants.TOKEN,
//    ): Response<MovieTrailerResponse>
}