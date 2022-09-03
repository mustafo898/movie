package dark.composer.movie_db.data.remote.models.response.pagination.person

data class PersonPaginationResponse(
    val page: Int,
    val results: List<ResultPerson>,
    val total_pages: Int,
    val total_results: Int
)