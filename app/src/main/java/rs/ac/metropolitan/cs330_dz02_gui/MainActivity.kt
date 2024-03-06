package rs.ac.metropolitan.cs330_dz02_gui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import rs.ac.metropolitan.cs330_dz02_gui.ui.theme.CS330DZ02GUITheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CS330DZ02GUITheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Greeting() {
    val (height, setHeight) = remember { mutableStateOf("") }
    val (weight, setWeight) = remember { mutableStateOf("") }
    val (bmi, setBmi) = remember { mutableStateOf(0f) }

    fun calculateBMI() {
        val h = height.toFloatOrNull() ?: 0f
        val w = weight.toFloatOrNull() ?: 0f
        if (h > 0 && w > 0) {
            val bmiValue = w / (h * h) * 10000
            setBmi(bmiValue)
        } else {
            setBmi(0f)
        }
    }

    fun resetFields() {
        setHeight("")
        setWeight("")
        setBmi(0f)
    }
    MaterialTheme {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopAppBar(title = { Text("BMI kalkulator") })
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = height,
                onValueChange = { setHeight(it) },
                label = { Text("Unesite visinu (cm)") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = weight,
                onValueChange = { setWeight(it) },
                label = { Text("Unesite težinu (kg)") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { calculateBMI() }) {
                Text("Izračunaj")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "BMI: $bmi")
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { resetFields() }) {
                Text("Resetuj")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CS330DZ02GUITheme {
        Greeting()
    }
}