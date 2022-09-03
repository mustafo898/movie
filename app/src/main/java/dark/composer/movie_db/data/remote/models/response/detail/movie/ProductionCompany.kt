package dark.composer.movie_db.data.remote.models.response.detail.movie

data class ProductionCompany(
    val id: Int,
    val logo_path: String,
    val name: String,
    val origin_country: String
)