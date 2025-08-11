package nekit.corporation.shift_app.models

import kotlinx.serialization.Serializable
import nekit.corporation.shift_app.data.local_data_source.dbo.DateInfoDbo

@Serializable
data class DateInfo(
    val date: String,
    val age: Int
)

fun DateInfo.toDataInfoDbo() = DateInfoDbo(date, age)
fun DateInfoDbo.toDataInfo() = DateInfo(date, age)
