package com.vueltaf1nal.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vueltaf1nal.app.ui.theme.*
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
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

// Cliente HTTP Global (Simple para este MVP)
val client = HttpClient(OkHttp) {
    install(ContentNegotiation) {
        json(Json {
            ignoreUnknownKeys = true
        })
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VueltaF1nalTheme {
                var currentScreen by remember { mutableStateOf<Screen>(Screen.List) }
                var results by remember { mutableStateOf<List<DriverResult>>(emptyList()) }
                var isLoading by remember { mutableStateOf(true) }
                var error by remember { mutableStateOf<String?>(null) }

                // Fetch data from Backend
                LaunchedEffect(Unit) {
                    try {
                        results = client.get("http://10.0.2.2:8080/api/results").body()
                        isLoading = false
                    } catch (e: Exception) {
                        error = "No se pudo conectar con el motor: ${e.message}"
                        isLoading = false
                    }
                }

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = DeepNavy
                ) {
                    if (isLoading) {
                        Box(contentAlignment = Alignment.Center) {
                            CircularProgressIndicator(color = RacingRed)
                        }
                    } else if (error != null) {
                        Box(contentAlignment = Alignment.Center) {
                            Text(text = error!!, color = Color.White, modifier = Modifier.padding(20.dp))
                        }
                    } else {
                        when (val screen = currentScreen) {
                            is Screen.List -> ResultListScreen(
                                results = results,
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
}

@Composable
fun ResultListScreen(results: List<DriverResult>, onSeeDetails: (DriverResult) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DeepNavy)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = Modifier.size(120.dp), contentAlignment = Alignment.Center) {
            Text("LOGO", color = RacingRed, fontWeight = FontWeight.Bold)
            // Image(painter = painterResource(id = R.drawable.logo_minimalista), ...)
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
            Text("← VOLVER A RESULTADOS", color = AeroSilver, fontWeight = FontWeight.Bold)
        }
    }
}