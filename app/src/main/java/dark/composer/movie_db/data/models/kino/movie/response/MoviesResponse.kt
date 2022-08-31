package dark.composer.movie_db.data.models.kino.movie.response


import com.google.gson.annotations.SerializedName
import dark.composer.movie_db.data.models.kino.popular.response.ResultX

data class MoviesResponse(
    @SerializedName("results")
    var results: List<ResultX>,
)