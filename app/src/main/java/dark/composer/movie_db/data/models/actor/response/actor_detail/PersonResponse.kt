package dark.composer.movie_db.data.models.actor.response.actor_detail


import com.google.gson.annotations.SerializedName

data class PersonResponse(
    @SerializedName("results")
    var results: List<PersonResult>,
)