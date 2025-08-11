package nekit.corporation.shift_app.data.local_data_source.dbo

import androidx.room.Entity

@Entity("coordinate")
data class CoordinatesDbo(
    val latitude: String,
    val longitude: String
)