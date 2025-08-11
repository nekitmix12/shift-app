package nekit.corporation.shift_app.data.local_data_source.dbo

import androidx.room.Entity

@Entity("street")
data class StreetDbo(
    val number: Int,
    val name: String
)