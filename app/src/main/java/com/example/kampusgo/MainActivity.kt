package com.example.kampusgo

import android.os.Bundle
import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.example.kampusgo.ui.theme.KampusGoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val activity = this

        setContent {
            KampusGoTheme {
                var name by remember { mutableStateOf("") }
                var npm by remember { mutableStateOf("") }

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                            .padding(24.dp)
                    ) {
                        Text(text = "KampusGo")

                        OutlinedTextField(
                            value = name,
                            onValueChange = { name = it },
                            label = { Text("Your name") }
                        )

                        OutlinedTextField(
                            value = npm,
                            onValueChange = { npm = it },
                            label = { Text("Your NPM") }
                        )

                        Button(onClick = {
                            val intent = Intent(
                                activity,
                                ProfileActivity::class.java
                            )
                            intent.putExtra("name", name)
                            intent.putExtra("npm", npm)
                            activity.startActivity(intent)
                        }) {
                            Text("Open profile")
                        }

                        Button(onClick = {
                            val intent = Intent(
                                activity,
                                ProfileXmlActivity::class.java
                            )
                            activity.startActivity(intent)
                        }) {
                            Text("XML form")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    KampusGoTheme {
        Greeting("Android")
    }
}