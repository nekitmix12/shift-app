package nekit.corporation.shift_app.data.local_data_source.dbo

import androidx.room.Entity

@Entity("picture")
data class PictureDbo(
    val large: String,
    val medium: String,
    val thumbnail: String
)