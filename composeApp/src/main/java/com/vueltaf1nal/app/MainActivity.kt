import androidx.compose.ui.res.painterResource
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

data class DriverResult(
    val pos: Int,
    val name: String,
    val team: String,
    val time: String,
    val topSpeed: Double,
    val speedLap: Int
)

sealed class Screen {
    object List : Screen()
    data class Details(val driver: DriverResult) : Screen()
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VueltaF1nalTheme {
                var currentScreen by remember { mutableStateOf<Screen>(Screen.List) }

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = DeepNavy
                ) {
                    when (val screen = currentScreen) {
                        is Screen.List -> ResultListScreen(
                            onSeeDetails = { currentScreen = Screen.Details(it) }
                        )
                        is Screen.Details -> TopSpeedDetailScreen(
                            driver = screen.driver,
                            onBack = { currentScreen = Screen.List }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ResultListScreen(onSeeDetails: (DriverResult) -> Unit) {
    val results = remember {
        listOf(
            DriverResult(1, "Max Verstappen", "Red Bull Racing", "1:23:45.678", 325.4, 44),
            DriverResult(2, "Lando Norris", "McLaren", "+2.456s", 328.1, 56),
            DriverResult(3, "Charles Leclerc", "Ferrari", "+5.123s", 322.9, 52),
            DriverResult(4, "Oscar Piastri", "McLaren", "+12.678s", 324.5, 48),
            DriverResult(5, "Lewis Hamilton", "Mercedes", "+15.901s", 321.2, 58)
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DeepNavy)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Logo en vez de texto
        // Nota: El archivo debe estar en res/drawable/logo_minimalista.png
        Box(modifier = Modifier.size(120.dp), contentAlignment = Alignment.Center) {
            // Usamos un placeholder si el resource no existe aun
            Text("LOGO", color = RacingRed, fontWeight = FontWeight.Bold)
            /* 
            Image(
                painter = painterResource(id = R.drawable.logo_minimalista), 
                contentDescription = "Logo",
                modifier = Modifier.size(80.dp)
            )
            */
        }
        
        Text(
            text = "GP BRASIL 2024",
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items(results) { driver ->
                ResultCard(driver, onSeeDetails)
            }
        }
    }
}

@Composable
fun ResultCard(driver: DriverResult, onSeeDetails: (DriverResult) -> Unit) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = BrightNavy),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = "P${driver.pos} ${driver.name}", color = Color.White, fontWeight = FontWeight.Bold)
                Text(text = driver.team, color = AeroSilver, fontSize = 12.sp)
            }
            Button(
                onClick = { onSeeDetails(driver) },
                colors = ButtonDefaults.buttonColors(containerColor = RacingRed),
                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 4.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("VELOCIDADES", fontSize = 10.sp, color = Color.White)
            }
        }
    }
}

@Composable
fun TopSpeedDetailScreen(driver: DriverResult, onBack: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = driver.name.uppercase(), color = RacingRed, fontSize = 24.sp, fontWeight = FontWeight.ExtraBold)
        Text(text = driver.team, color = Color.White, fontSize = 16.sp)
        
        Spacer(modifier = Modifier.height(32.dp))
        
        Card(
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = BrightNavy),
            modifier = Modifier.size(200.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "TOP SPEED", color = AeroSilver, fontSize = 14.sp)
                Text(text = "${driver.topSpeed}", color = TelemetryGreen, fontSize = 48.sp, fontWeight = FontWeight.Black)
                Text(text = "KM/H", color = TelemetryGreen, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                
                Spacer(modifier = Modifier.height(8.dp))
                
                Text(text = "EN VUELTA ${driver.speedLap}", color = Color.White, fontSize = 12.sp)
            }
        }

        Spacer(modifier = Modifier.height(48.dp))

        TextButton(onClick = onBack) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("← VOLVER A RESULTADOS", color = AeroSilver, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun F1TelemetryTheme(content: @Composable () -> Unit) {
    MaterialTheme(content = content)
}