package nekit.corporation.shift_app.models

import kotlinx.serialization.Serializable

@Serializable
data class Street(
    val number: Int,
    val name: String
)