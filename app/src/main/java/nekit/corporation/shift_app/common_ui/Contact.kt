package nekit.corporation.shift_app.common_ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nekit.corporation.shift_app.R
import nekit.corporation.shift_app.models.ContactInfoModel
import nekit.corporation.shift_app.ui.theme.Blue
import nekit.corporation.shift_app.ui.theme.Grey

@Composable
fun ContactInformation(
    contactInfoModel: ContactInfoModel,
    onMailClick: (String) -> Unit,
    onPhoneClick: (String) -> Unit
) {
    ContactRow(
        ImageVector.vectorResource(R.drawable.mail),
        stringResource(R.string.email),
        contactInfoModel.email,
        onMailClick
    )
    ContactRow(
        ImageVector.vectorResource(R.drawable.phone),
        stringResource(R.string.phone),
        contactInfoModel.phone,
        onPhoneClick,
        modifier = Modifier.padding(vertical = 16.dp)
    )
    ContactRow(
        ImageVector.vectorResource(R.drawable.mobile),
        stringResource(R.string.mobile),
        contactInfoModel.mobile,
        onPhoneClick
    )
}

@Composable
fun ContactRow(
    icon: ImageVector,
    name: String,
    description: String,
    onClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(modifier) {
        Icon(
            icon,
            "",
            tint = Blue,
            modifier = Modifier
                .clip(CircleShape)
                .background(Blue.copy(alpha = 0.1f))
                .padding(8.dp)
                .size(24.dp)
        )
        Column(modifier = Modifier.padding(start = 12.dp)) {
            Text(name, color = Grey, fontSize = 16.sp, fontWeight = FontWeight.W500)
            Text(
                description,
                fontSize = 18.sp,
                fontWeight = FontWeight.W500,
                modifier = Modifier.clickable { onClick(description) })
        }
    }
}