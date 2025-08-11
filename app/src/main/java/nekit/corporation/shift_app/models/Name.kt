package nekit.corporation.shift_app.models

import kotlinx.serialization.Serializable
import nekit.corporation.shift_app.data.local_data_source.dbo.NameDbo

@Serializable
data class Name(
    val title: String,
    val first: String,
    val last: String
)

fun Name.toNameDbo() = NameDbo(title, first, last)
fun NameDbo.toName() = Name(title, first, last)
