package dark.composer.movie_db.data.remote.models.response.pagination.movie

data class MoviePaginationResponse(
    val dates: Dates,
    val page: Int,
    val results : List<ResultMovie>,
    val total_pages: Int,
    val total_results: Int
)