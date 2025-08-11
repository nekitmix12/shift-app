package nekit.corporation.shift_app.data.local_data_source.dbo

import androidx.room.Entity

@Entity("login")
data class LoginDbo(
    val uuid: String,
    val username: String,
    val password: String,
    val salt: String,
    val md5: String,
    val sha1: String,
    val sha256: String
)