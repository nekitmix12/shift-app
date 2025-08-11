package nekit.corporation.shift_app.data.local_data_source.dbo

import androidx.room.Entity

@Entity("timezone")
data class TimezoneDbo(
    val offset: String,
    val description: String
)
