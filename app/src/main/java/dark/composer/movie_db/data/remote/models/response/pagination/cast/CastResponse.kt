package dark.composer.movie_db.data.remote.models.response.pagination.cast

data class CastResponse(
    val cast: List<Cast>,
    val crew: List<Crew>,
    val id: Int
)