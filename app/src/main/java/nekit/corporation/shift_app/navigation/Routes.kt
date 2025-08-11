package nekit.corporation.shift_app.navigation

import kotlinx.serialization.Serializable

sealed class Routes {
    @Serializable
    object List

    @Serializable
    data class Item(val user: String)
}