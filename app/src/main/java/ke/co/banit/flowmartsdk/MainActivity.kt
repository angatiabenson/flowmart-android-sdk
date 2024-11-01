package ke.co.banit.flowmartsdk


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import ke.co.banit.flowmartsdk.features.category.CategoryViewModel
import ke.co.banit.flowmartsdk.navigation.AppNavigation
import ke.co.banit.flowmartsdk.ui.theme.FlowMartSDKTheme

class MainActivity : ComponentActivity() {
    private val categoryViewModel: CategoryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FlowMartSDKTheme {
                AppNavigation(
                    categoryViewModel = categoryViewModel
                )
            }
        }
    }
}