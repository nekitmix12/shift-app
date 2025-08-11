package nekit.corporation.shift_app.profile

import androidx.lifecycle.ViewModel
import nekit.corporation.shift_app.IntentSender
import java.time.Instant
import java.time.LocalDate
import java.time.Period
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class ProfileViewModel @Inject constructor(val intentSender: IntentSender) : ViewModel() {

    fun calculateAgeFromIso(isoTimestamp: String, zone: ZoneId = ZoneId.systemDefault()): Int {
        val birthDate: LocalDate = Instant.parse(isoTimestamp).atZone(zone).toLocalDate()
        val today: LocalDate = LocalDate.now(zone)
        return Period.between(birthDate, today).years
    }


    fun toCorrectFormat(isoTimestamp: String, zone: ZoneId = ZoneId.systemDefault()): String {
        val date = Instant.parse(isoTimestamp).atZone(zone).toLocalDate()
        val formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy")
        return date.format(formatter)
    }
}