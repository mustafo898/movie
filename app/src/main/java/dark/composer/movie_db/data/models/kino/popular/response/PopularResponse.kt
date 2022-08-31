package dark.composer.movie_db.data.models.kino.popular.response

data class PopularResponse(
    val page: Int,
    val results: List<ResultX>,
    val total_pages: Int,
    val total_results: Int
)