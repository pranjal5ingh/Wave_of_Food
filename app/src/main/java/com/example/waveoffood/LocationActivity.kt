package com.example.waveoffood

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.waveoffood.databinding.ActivityLocationBinding
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.FusedLocationProviderClient
import java.util.Locale
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.gms.location.Priority
import com.google.android.gms.location.SettingsClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class LocationActivity : AppCompatActivity() {

    private lateinit var locationProviderClient: FusedLocationProviderClient
    private lateinit var gpsLauncher: ActivityResultLauncher<IntentSenderRequest>
    private val binding: ActivityLocationBinding by lazy {
        ActivityLocationBinding.inflate(layoutInflater)
    }

    companion object {
        private const val LOCATION_PERMISSION_CODE = 1001
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        Handler(Looper.getMainLooper()).postDelayed({
            binding.locationCard.visibility = View.VISIBLE
            val animation = AnimationUtils.loadAnimation(this, R.anim.slide_up)
            binding.locationCard.startAnimation(animation)
        }, 500)

        locationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        checkGpsAndPrompt()
        binding.getLocationButton.setOnClickListener {
            getCurrentLocation()
        }

        gpsLauncher =
            this.registerForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    getCurrentLocation()
                } else {
                    Toast.makeText(this, "GPS is still off", Toast.LENGTH_SHORT).show()
                }
            }


    }

    private fun getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                LOCATION_PERMISSION_CODE
            )
            return
        }

        locationProviderClient.getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, null)
            .addOnSuccessListener { location ->
                if (location != null) {
                    val lat = location.latitude
                    val lon = location.longitude
                    getLocationNameFromCoordinates(lat, lon)
                } else {
                    AlertDialog.Builder(this)
                        .setTitle("Location Unavailable")
                        .setMessage("We couldn't detect your current location. Please make sure GPS is enabled and try again.")
                        .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
                        .setCancelable(false)
                        .show()
                }
            }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_PERMISSION_CODE &&
            grantResults.isNotEmpty() &&
            grantResults[0] == PackageManager.PERMISSION_GRANTED
        ) {
            checkGpsAndPrompt() // Let GPS check flow call getCurrentLocation()
        } else {
            binding.userLocationTextView.text = "Location permission denied"
        }
    }

    private fun getLocationNameFromCoordinates(latitude: Double, longitude: Double) {
        val geocoder = Geocoder(this, Locale.getDefault())
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val addresses = geocoder.getFromLocation(latitude, longitude, 1)
                withContext(Dispatchers.Main) {
                    if (!addresses.isNullOrEmpty()) {
                        val address = addresses[0]
                        val fullAddress = address.getAddressLine(0)
                        val city = address.locality ?: ""
                        val state = address.adminArea ?: ""
                        val country = address.countryName ?: ""
                        val postalCode = address.postalCode ?: ""

                        binding.userLocationTextView.text =
                            "$fullAddress\n$city, $state, $country - $postalCode"

                        // ✅ Redirect to MainActivity with location data
                        val intent = Intent(this@LocationActivity, MainActivity::class.java).apply {
                            putExtra("latitude", latitude)
                            putExtra("longitude", longitude)
                            putExtra("address", fullAddress)
                            putExtra("city", city)
                            putExtra("state", state)
                            putExtra("country", country)
                            putExtra("postalCode", postalCode)
                        }
                        startActivity(intent)
                        finish()
                    } else {
                        binding.userLocationTextView.text = "Address not found"
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                withContext(Dispatchers.Main) {
                    binding.userLocationTextView.text = "Error fetching address"
                }
            }
        }
    }


    private fun checkGpsAndPrompt() {
        val locationRequest = LocationRequest.Builder(
            Priority.PRIORITY_HIGH_ACCURACY,
            10000L
        ).setMinUpdateIntervalMillis(5000L).build()

        val builder = LocationSettingsRequest.Builder()
            .addLocationRequest(locationRequest)
            .setAlwaysShow(true)

        val settingsClient = LocationServices.getSettingsClient(this)
        val task = settingsClient.checkLocationSettings(builder.build())

        task.addOnSuccessListener {
            getCurrentLocation()
        }

        task.addOnFailureListener { exception ->
            if (exception is ResolvableApiException) {
                try {
                    val intentSenderRequest =
                        IntentSenderRequest.Builder(exception.resolution).build()
                    gpsLauncher.launch(intentSenderRequest)
                } catch (sendEx: Exception) {
                    sendEx.printStackTrace()
                }
            } else {
                Toast.makeText(this, "GPS is not available", Toast.LENGTH_SHORT).show()
            }
        }
    }
}