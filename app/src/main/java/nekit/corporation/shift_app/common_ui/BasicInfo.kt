package nekit.corporation.shift_app.common_ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nekit.corporation.shift_app.R
import nekit.corporation.shift_app.models.BasicInfoModel
import nekit.corporation.shift_app.ui.theme.Grey

@Composable
fun BasicInfo(basicInfoModel: BasicInfoModel) {
    BasicRow(
        stringResource(R.string.full_name),
        basicInfoModel.name.title + " " + basicInfoModel.name.first + " " + basicInfoModel.name.last
    )
    BasicRow(
        stringResource(R.string.birthday),
        basicInfoModel.birthdate,
        Modifier.padding(vertical = 16.dp)
    )
    BasicRow(stringResource(R.string.nationality), basicInfoModel.nationality)
    BasicRow(
        stringResource(R.string.id), basicInfoModel.id, Modifier.padding(vertical = 16.dp)
    )
    BasicRow(stringResource(R.string.registration), basicInfoModel.registration)
}

@Composable
fun BasicRow(name: String, description: String, modifier: Modifier = Modifier) {
    Row(modifier) {
        Text(name, fontSize = 18.sp, fontWeight = FontWeight.W500, color = Grey)
        Spacer(Modifier.weight(1f))
        Text(description, fontSize = 18.sp, fontWeight = FontWeight.W500)
    }
}
