package dark.composer.movie_db.data.remote.models.response.detail.cast

data class CastDetail(
    val credit_type: String,
    val department: String,
    val id: String,
    val job: String,
    val media: Media,
    val media_type: String,
    val person: Person
)