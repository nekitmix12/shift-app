package nekit.corporation.shift_app.models

import kotlinx.serialization.Serializable
import nekit.corporation.shift_app.data.local_data_source.dbo.UserDbo

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

fun User.toUserDbo() =
    UserDbo(
        uid = 0,
        gender,
        name.toNameDbo(),
        location.toLocationDbo(),
        email,
        login.toLoginDbo(),
        dob.toDataInfoDbo(),
        registered.toDataInfoDbo(),
        phone,
        cell,
        id.toIdDbo(),
        picture.toPictureDbo(),
        nat
    )

fun UserDbo.toUser() =
    User(
        gender,
        name.toName(),
        location.toLocation(),
        email,
        login.toLogin(),
        dob.toDataInfo(),
        registered.toDataInfo(),
        phone,
        cell,
        id.toId(),
        picture.toPicture(),
        nat
    )