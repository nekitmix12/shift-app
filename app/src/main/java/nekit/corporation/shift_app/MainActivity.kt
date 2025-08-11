package nekit.corporation.shift_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import nekit.corporation.shift_app.di.App
import nekit.corporation.shift_app.navigation.MainNavHost
import nekit.corporation.shift_app.ui.theme.ShiftappTheme
import javax.inject.Inject

class MainActivity : ComponentActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        (application as App).appComponent.inject(this)
        setContent {
            val navController = rememberNavController()

            ShiftappTheme {
                MainNavHost(navController,viewModelFactory)
            }
        }
    }
}

