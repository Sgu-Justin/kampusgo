package com.example.kampusgo

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.kampusgo.ui.theme.KampusGoTheme

class ProfileActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("KampusGo", "onCreate")

        val activity = this
        val name = intent.getStringExtra("name") ?: "Guest"
        val npm = intent.getStringExtra("npm") ?: "-"

        setContent {
            KampusGoTheme {
                Column(modifier = Modifier.padding(24.dp)) {
                    Text(text = "Hello, $name")
                    Text(text = "NPM: $npm")

                    Button(onClick = {
                        val dial = Intent(
                            Intent.ACTION_DIAL,
                            Uri.parse("tel:0211234567")
                        )

                        try {
                            activity.startActivity(dial)
                        } catch (e: ActivityNotFoundException) {
                            Toast.makeText(
                                activity,
                                "No dialer on this device",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }) {
                        Text("Call campus")
                    }

                    Button(onClick = {
                        val website = Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("https://www.sgu.ac.id")
                        )

                        try {
                            activity.startActivity(website)
                        } catch (e: ActivityNotFoundException) {
                            Toast.makeText(
                                activity,
                                "No browser on this device",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }) {
                        Text("Open SGU website")
                    }
                }
            }
        }
    }
    override fun onStart() {
        super.onStart()
        Log.d("KampusGo", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("KampusGo", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("KampusGo", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("KampusGo", "onStop")
    }
}