package nekit.corporation.shift_app.models

import kotlinx.serialization.Serializable
import nekit.corporation.shift_app.data.local_data_source.dbo.StreetDbo

@Serializable
data class Street(
    val number: Int,
    val name: String
)

fun Street.toStreetDbo() = StreetDbo(number, name)
fun StreetDbo.toStreet() = Street(number, name)
