package nekit.corporation.shift_app.models

import kotlinx.serialization.Serializable
import nekit.corporation.shift_app.data.local_data_source.dbo.LoginDbo

@Serializable
data class Login(
    val uuid: String,
    val username: String,
    val password: String,
    val salt: String,
    val md5: String,
    val sha1: String,
    val sha256: String
)

fun Login.toLoginDbo() = LoginDbo(uuid, username, password, salt, md5, sha1, sha256)
fun LoginDbo.toLogin() = Login(uuid, username, password, salt, md5, sha1, sha256)
