package nekit.corporation.shift_app.models

import kotlinx.serialization.Serializable
import nekit.corporation.shift_app.data.local_data_source.dbo.IdDbo

@Serializable
data class Id(
    val name: String,
    val value: String
)

fun Id.toIdDbo() = IdDbo(name, value)
fun IdDbo.toId() = Id(name, value)
