package nekit.corporation.shift_app.models

import kotlinx.serialization.Serializable

@Serializable
data class Id(
    val name: String,
    val value: String
)
