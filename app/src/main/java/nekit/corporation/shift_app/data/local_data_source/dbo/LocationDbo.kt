package nekit.corporation.shift_app.data.local_data_source.dbo

import androidx.room.Embedded
import androidx.room.Entity

data class LocationDbo(
    @Embedded(prefix = "street_") val street: StreetDbo,
    val city: String,
    val state: String,
    val country: String,
    val postcode: String,
    @Embedded(prefix = "coord_") val coordinates: CoordinatesDbo,
    @Embedded(prefix = "tz_") val timezone: TimezoneDbo
)