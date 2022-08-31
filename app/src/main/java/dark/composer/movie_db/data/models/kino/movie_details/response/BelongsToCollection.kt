package dark.composer.movie_db.data.models.kino.movie_details.response


import com.google.gson.annotations.SerializedName

data class BelongsToCollection(
    @SerializedName("id")
    var id: Int,
    @SerializedName("name")
    var name: String,
    @SerializedName("poster_path")
    var posterPath: Any,
    @SerializedName("backdrop_path")
    var backdropPath: Any
)