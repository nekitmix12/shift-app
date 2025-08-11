package nekit.corporation.shift_app.models

import java.util.UUID

data class ListModel(
    val uuid: UUID,
    val name: Name,
    val picture: String,
    val address: Location,
    val phoneNumber: String
)
