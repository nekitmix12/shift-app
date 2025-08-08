package nekit.corporation.shift_app.models

import kotlinx.serialization.Serializable

@Serializable
data class DateInfo(
    val date: String,
    val age: Int
)