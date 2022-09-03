package dark.composer.movie_db.data.remote.models.response.pagination.review

data class AuthorDetails(
    val avatar_path: String,
    val name: String,
    val rating: Double,
    val username: String
)