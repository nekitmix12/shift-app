package nekit.corporation.shift_app.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import nekit.corporation.shift_app.R
import nekit.corporation.shift_app.common_ui.BasicInfo
import nekit.corporation.shift_app.common_ui.BasicRow
import nekit.corporation.shift_app.common_ui.ContactInformation
import nekit.corporation.shift_app.common_ui.InfoLabel
import nekit.corporation.shift_app.common_ui.Label
import nekit.corporation.shift_app.models.BasicInfoModel
import nekit.corporation.shift_app.models.ContactInfoModel
import nekit.corporation.shift_app.models.LabelModel
import nekit.corporation.shift_app.models.LocationInfoModel
import nekit.corporation.shift_app.models.User
import nekit.corporation.shift_app.models.toLocationInfoModel
import nekit.corporation.shift_app.ui.theme.Blue
import nekit.corporation.shift_app.ui.theme.Grey

//@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProfileUi(user: User, factory: ViewModelProvider.Factory, onBackClick: () -> Unit) {
    val context = LocalContext.current
    val viewModel: ProfileViewModel = viewModel(factory = factory)
    LazyColumn {
        item {
            Label(onBackClick)

        }
        item() {
            ProfileMainInfo(
                LabelModel(
                    user.picture.medium,
                    user.name,
                    viewModel.calculateAgeFromIso(user.registered.date).toString()
                )
            )
            Spacer(Modifier.height(90.dp))
        }
        item {
            InfoLabel(
                icon = ImageVector.vectorResource(R.drawable.profile),
                text = stringResource(R.string.basic_info)
            ) {
                BasicInfo(
                    BasicInfoModel(
                        user.name,
                        viewModel.toCorrectFormat(user.dob.date) + " (${user.dob.age})",
                        user.location.city,
                        user.location.postcode.toString(),
                        viewModel.toCorrectFormat(user.registered.date) + " (${user.registered.age})"
                    )
                )
            }
        }

        item {
            InfoLabel(
                icon = ImageVector.vectorResource(R.drawable.phone_book),
                text = stringResource(R.string.contact_info)
            ) {
                ContactInformation(
                    ContactInfoModel(
                        user.email, user.phone, user.cell
                    ),
                    onPhoneClick = { viewModel.intentSender.openPhone(context, it) },
                    onMailClick = { viewModel.intentSender.openEmail(context, it) })

            }
        }
        item {
            InfoLabel(
                icon = ImageVector.vectorResource(R.drawable.map_pin),
                text = stringResource(R.string.location_info)
            ) {
                LocationInformation(
                    user.location.toLocationInfoModel(), onClick = { lat, long ->
                        viewModel.intentSender.openMapWithCoordinates(
                            context, lat, long
                        )
                    })

            }
        }
        item {
            InfoLabel(
                icon = ImageVector.vectorResource(R.drawable.gallery),
                text = stringResource(R.string.photos)
            ) {
                Photos(user.picture.medium, user.picture.large, user.picture.thumbnail)
            }
        }

    }
}

@Composable
fun ProfileMainInfo(labelModel: LabelModel) {
    Row(
        Modifier
            .fillMaxWidth()
            .background(Blue)
            .offset(y = 50.dp)
            .padding(horizontal = 16.dp)
    ) {
        Box(
            Modifier
                .clip(CircleShape)
                .size(110.dp)
                .background(Color.White),
        ) {
            Image(
                painter = painterResource(R.drawable.mountain),
                "",
                Modifier
                    .clip(CircleShape)
                    .size(100.dp)
                    .align(Alignment.Center),
                contentScale = ContentScale.Crop,
            )
        }

        Column(
            Modifier
                .fillMaxSize()
                .align(Alignment.Bottom)
                .padding(horizontal = 12.dp)
        ) {
            Text(
                labelModel.name.last + " " + labelModel.name.first + " " + labelModel.name.title,
                fontWeight = FontWeight.W600,
                fontSize = 20.sp
            )
            Text(
                stringResource(R.string.active_user) + " • " + labelModel.active,
                color = Grey,
                fontSize = 16.sp
            )
        }
    }
}


@Composable
fun LocationInformation(locationInfoModel: LocationInfoModel, onClick: (Double, Double) -> Unit) {
    BasicRow(
        stringResource(R.string.street_),
        locationInfoModel.street.number.toString() + " " + locationInfoModel.street.name
    )
    BasicRow(
        stringResource(R.string.city), locationInfoModel.city, Modifier.padding(vertical = 16.dp)
    )
    BasicRow(
        stringResource(R.string.state), locationInfoModel.state
    )
    BasicRow(
        stringResource(R.string.postal_code),
        locationInfoModel.postalCode,
        Modifier.padding(vertical = 16.dp)
    )
    BasicRow(
        stringResource(R.string.time_zone), locationInfoModel.timezone
    )
    Column(
        Modifier
            .padding(top = 16.dp)
            .clickable {
                onClick(
                    locationInfoModel.lat.toDouble(), locationInfoModel.long.toDouble()
                )
            }
            .clip(RoundedCornerShape(12.dp))
            .background(Grey.copy(alpha = 0.2f))
            .fillMaxWidth()
            .padding(16.dp)) {
        Text(
            stringResource(R.string.coordinates),
            fontSize = 18.sp,
            fontWeight = FontWeight.W500,
            color = Grey
        )
        Text(
            stringResource(R.string.lat_and_long, locationInfoModel.lat, locationInfoModel.long),
            fontSize = 18.sp,
            fontWeight = FontWeight.W500,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

@Composable
fun Photos(imageUrlsFirst: String, imageUrlsSecond: String, imageUrlsThird: String) {
    Row(Modifier.fillMaxWidth()) {
        AsyncImage(
            imageUrlsFirst,
            "",
            Modifier
                .weight(1f)
                .aspectRatio(1f)
                .padding(end = 4.dp)
                .clip(RoundedCornerShape(12.dp))
                .fillMaxWidth(),
            error = painterResource(R.drawable.mountain),
            contentScale = ContentScale.Crop

        )
        AsyncImage(
            imageUrlsSecond,
            "",
            Modifier
                .weight(1f)
                .aspectRatio(1f)
                .padding(
                    horizontal = 4.dp
                )
                .clip(RoundedCornerShape(12.dp))
                .fillMaxWidth(),
            error = painterResource(R.drawable.mountain),
            contentScale = ContentScale.Crop
        )
        AsyncImage(
            imageUrlsThird,
            "",
            Modifier
                .weight(1f)
                .aspectRatio(1f)
                .padding(start = 4.dp)
                .clip(RoundedCornerShape(12.dp))
                .fillMaxWidth(),
            error = painterResource(R.drawable.mountain),
            contentScale = ContentScale.Crop
        )
    }
}
