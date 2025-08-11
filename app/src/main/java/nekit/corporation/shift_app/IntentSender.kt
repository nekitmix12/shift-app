package nekit.corporation.shift_app

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import androidx.core.net.toUri
import javax.inject.Inject

class IntentSender @Inject constructor() {
    fun openEmail(context: Context, email: String, subject: String = "", body: String = "") {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = "mailto:".toUri()
            putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
            //  putExtra(Intent.EXTRA_SUBJECT, subject)
            //  putExtra(Intent.EXTRA_TEXT, body)
        }

        try {
            context.startActivity(intent)
        } catch (e: ActivityNotFoundException) {
        }
    }

    fun openPhone(context: Context, phoneNumber: String) {
        val intent = Intent(Intent.ACTION_DIAL).apply {
            data = "tel:$phoneNumber".toUri()
        }

        try {
            context.startActivity(intent)
        } catch (e: ActivityNotFoundException) {
        }
    }

    fun openMapWithCoordinates(context: Context, lat: Double, lng: Double) {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = "geo:$lat,$lng".toUri()
        }

        try {
            context.startActivity(intent)
        } catch (e: ActivityNotFoundException) {
        }
    }
}