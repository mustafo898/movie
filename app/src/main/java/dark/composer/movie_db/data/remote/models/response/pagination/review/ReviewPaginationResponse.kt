package dark.composer.movie_db.data.remote.models.response.pagination.review

data class ReviewPaginationResponse(
    val id: Int,
    val page: Int,
    val results: List<ResultReview>,
    val total_pages: Int,
    val total_results: Int
)