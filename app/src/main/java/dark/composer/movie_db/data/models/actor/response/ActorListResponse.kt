package dark.composer.movie_db.data.models.actor.response

data class ActorListResponse(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)