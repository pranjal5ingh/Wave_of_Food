package com.example.waveoffood

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.waveoffood.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private  val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        var NavController : NavController = findNavController(R.id.fragmentContainerView)
        binding.bottomMenu.setupWithNavController(NavController)

        val latitude = intent.getDoubleExtra("latitude", 0.0)
        val longitude = intent.getDoubleExtra("longitude", 0.0)
        val address = intent.getStringExtra("address")
        val city = intent.getStringExtra("city")
        val state = intent.getStringExtra("state")
        val country = intent.getStringExtra("country")
        val postalCode = intent.getStringExtra("postalCode")

        // You can use this data now, for example:
        val fullLocationText = "$address\n$city, $state, $country - $postalCode"
        binding.textViewLocation.text = fullLocationText

    }
}