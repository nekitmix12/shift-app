package nekit.corporation.shift_app.models

import kotlinx.serialization.Serializable
import nekit.corporation.shift_app.data.local_data_source.dbo.TimezoneDbo

@Serializable
data class Timezone(
    val offset: String,
    val description: String
)

fun Timezone.toTimezoneDbo() = TimezoneDbo(offset, description)
fun TimezoneDbo.toTimezone() = Timezone(offset, description)
