package nekit.corporation.shift_app.models

import kotlinx.serialization.Serializable
import nekit.corporation.shift_app.data.local_data_source.dbo.CoordinatesDbo

@Serializable
data class Coordinates(
    val latitude: String,
    val longitude: String
)

fun Coordinates.toCoordinatesDbo() = CoordinatesDbo(latitude, longitude)
fun CoordinatesDbo.toCoordinates() = Coordinates(latitude, longitude)
