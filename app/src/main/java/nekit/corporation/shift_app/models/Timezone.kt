package nekit.corporation.shift_app.models

import kotlinx.serialization.Serializable

@Serializable
data class Timezone(
    val offset: String,
    val description: String
)
