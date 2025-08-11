package nekit.corporation.shift_app.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import androidx.navigation.toRoute
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import nekit.corporation.shift_app.list.ListUi
import nekit.corporation.shift_app.models.User
import nekit.corporation.shift_app.profile.ProfileUi
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun MainNavHost(navHostController: NavHostController, viewModelFactory: ViewModelProvider.Factory) {
    NavHost(
        navHostController,
        startDestination = Routes.List,
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {

        composable<Routes.List> {
            ListUi(viewModelFactory) {
                navHostController.navigate(
                    Routes.Item(
                        URLEncoder.encode(
                            Json.encodeToString(it),
                            StandardCharsets.UTF_8.toString()
                        )
                    )
                )
            }
        }
        composable<Routes.Item>(deepLinks = listOf(navDeepLink {
            uriPattern = "your_app://item/{user}"
        })) { backStackEntry ->
            val userJson = backStackEntry.arguments?.getString("user")?.let {
                URLDecoder.decode(it, StandardCharsets.UTF_8.toString())
            }

            if (userJson == null) {
                navHostController.popBackStack()
                return@composable
            }

            val profile = Json.decodeFromString<User>(userJson)
            ProfileUi(profile, viewModelFactory) {
                navHostController.popBackStack()
            }
        }
    }
}

