package nekit.corporation.shift_app.models

import kotlinx.serialization.Serializable
import nekit.corporation.shift_app.data.local_data_source.dbo.PictureDbo

@Serializable
data class Picture(
    val large: String,
    val medium: String,
    val thumbnail: String
)

fun Picture.toPictureDbo() = PictureDbo(large, medium, thumbnail)
fun PictureDbo.toPicture() = Picture(large, medium, thumbnail)
