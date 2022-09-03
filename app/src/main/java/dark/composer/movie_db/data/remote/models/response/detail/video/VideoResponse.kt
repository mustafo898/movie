package dark.composer.movie_db.data.remote.models.response.detail.video

data class VideoResponse(
    val id: Int,
    val results: List<ResultVideo>
)