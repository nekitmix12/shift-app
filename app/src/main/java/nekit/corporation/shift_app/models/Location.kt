package nekit.corporation.shift_app.models

import kotlinx.serialization.Serializable
import nekit.corporation.shift_app.data.local_data_source.dbo.LocationDbo
import nekit.corporation.shift_app.data.remote_data_source.AnyToStringSerializer

@Serializable
data class Location(
    val street: Street,
    val city: String,
    val state: String,
    val country: String,
    @Serializable(with = AnyToStringSerializer::class)
    val postcode: String,
    val coordinates: Coordinates,
    val timezone: Timezone
)

fun Location.toLocationDbo() =
    LocationDbo(
        street.toStreetDbo(),
        city,
        state,
        country,
        postcode,
        coordinates.toCoordinatesDbo(),
        timezone.toTimezoneDbo()
    )

fun LocationDbo.toLocation() =
    Location(
        street.toStreet(),
        city,
        state,
        country,
        postcode,
        coordinates.toCoordinates(),
        timezone.toTimezone()
    )