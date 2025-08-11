package nekit.corporation.shift_app.data.local_data_source.dbo

import androidx.room.Entity

@Entity("date_info")
data class DateInfoDbo(
    val date: String,
    val age: Int
)