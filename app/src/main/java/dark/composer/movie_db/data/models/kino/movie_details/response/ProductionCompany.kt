package dark.composer.movie_db.data.models.kino.movie_details.response


import com.google.gson.annotations.SerializedName

data class ProductionCompany(
    @SerializedName("id")
    var id: Int,
    @SerializedName("logo_path")
    var logoPath: String,
    @SerializedName("name")
    var name: String,
    @SerializedName("origin_country")
    var originCountry: String
)