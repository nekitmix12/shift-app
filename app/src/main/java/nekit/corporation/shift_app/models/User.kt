package nekit.corporation.shift_app.models

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val gender: String,
    val name: Name,
    val location: Location,
    val email: String,
    val login: Login,
    val dob: DateInfo,
    val registered: DateInfo,
    val phone: String,
    val cell: String,
    val id: Id,
    val picture: Picture,
    val nat: String
)