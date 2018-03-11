
import android.content.Context.LOCATION_SERVICE
import android.location.Location
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.location.LocationManager
import android.location.LocationListener
import android.util.Log
import com.spaulding.geolocation.R


import kotlinx.android.synthetic.main.activity_geo_reporter.*

class GeoReporter : AppCompatActivity() {

    private var locationManager: LocationManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_geo_reporter)


        // Create persistent LocationManager reference
        locationManager = getSystemService(LOCATION_SERVICE) as LocationManager?

        fab.setOnClickListener {
            try {
                // Request location updates
                locationManager?.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0L, 0f, locationListener)
            } catch (ex: SecurityException) {
                Log.d("myTag", "Security Exception, no location available")
            }
        }
    }

    //define the listener
    private val locationListener: LocationListener = object : LocationListener {
        override fun onLocationChanged(location: Location) {
            Log.i("Loc changed",  "long: $location.longitude lat: $location.latitude")
        }

        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
        override fun onProviderEnabled(provider: String) {}
        override fun onProviderDisabled(provider: String) {}
    }
}
