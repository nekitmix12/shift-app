package nekit.corporation.shift_app.data.local_data_source.dbo

import androidx.room.Entity

@Entity("id")
data class IdDbo(
    val name: String,
    val value: String
)
