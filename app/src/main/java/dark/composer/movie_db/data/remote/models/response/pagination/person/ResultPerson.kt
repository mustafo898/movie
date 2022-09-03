package dark.composer.movie_db.data.remote.models.response.pagination.person

data class ResultPerson(
    val adult: Boolean,
    val gender: Int,
    val id: Int,
    val known_for: List<KnownFor>,
    val known_for_department: String,
    val media_type: String,
    val name: String,
    val original_name: String,
    val popularity: Double,
    val profile_path: String
)