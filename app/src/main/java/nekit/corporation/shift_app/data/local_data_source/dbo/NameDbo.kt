package nekit.corporation.shift_app.data.local_data_source.dbo

import androidx.room.Entity

@Entity("name")
data class NameDbo(
    val title: String,
    val first: String,
    val last: String
)