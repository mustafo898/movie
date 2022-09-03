package dark.composer.movie_db.data.remote.models.response.pagination.review

data class ResultReview(
    val author: String,
    val author_details: AuthorDetails,
    val content: String,
    val created_at: String,
    val id: String,
    val updated_at: String,
    val url: String
)