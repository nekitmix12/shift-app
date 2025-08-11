package nekit.corporation.shift_app.models

data class LocationInfoModel(
    val street: Street,
    val city: String,
    val state: String,
    val country: String,
    val postalCode: String,
    val timezone: String,
    val lat: String,
    val long: String,
)

fun Location.toLocationInfoModel() = LocationInfoModel(
    street,
    city,
    state,
    country,
    postcode.toString(),
    timezone.offset + " " + timezone.description,
    coordinates.latitude,
    coordinates.latitude
)
