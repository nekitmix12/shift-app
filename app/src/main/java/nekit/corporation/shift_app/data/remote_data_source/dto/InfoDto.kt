package nekit.corporation.shift_app.data.remote_data_source.dto

import kotlinx.serialization.Serializable

@Serializable
data class InfoDto(
    val seed: String,
    val results: Int,
    val page: Int,
    val version: String
)
