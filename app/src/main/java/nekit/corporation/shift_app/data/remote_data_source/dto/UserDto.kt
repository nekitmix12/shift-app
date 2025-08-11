package nekit.corporation.shift_app.data.remote_data_source.dto

import kotlinx.serialization.Serializable
import nekit.corporation.shift_app.models.User

@Serializable
data class UserDto(
    val results: List<User>,
    val info: InfoDto
)
