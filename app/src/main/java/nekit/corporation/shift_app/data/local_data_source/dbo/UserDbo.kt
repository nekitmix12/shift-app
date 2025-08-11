package nekit.corporation.shift_app.data.local_data_source.dbo

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserDbo(
    @PrimaryKey(autoGenerate = true) val uid: Long = 0L,
    val gender: String,
    @Embedded(prefix = "name_") val name: NameDbo,
    @Embedded(prefix = "location_") val location: LocationDbo,
    val email: String,
    @Embedded(prefix = "login_") val login: LoginDbo,
    @Embedded(prefix = "dob_") val dob: DateInfoDbo,
    @Embedded(prefix = "reg_") val registered: DateInfoDbo,
    val phone: String,
    val cell: String,
    @Embedded(prefix = "id_") val id: IdDbo,
    @Embedded(prefix = "pic_") val picture: PictureDbo,
    val nat: String
)
