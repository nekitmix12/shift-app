package nekit.corporation.shift_app.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import nekit.corporation.shift_app.R
import nekit.corporation.shift_app.models.ListModel
import nekit.corporation.shift_app.models.User
import nekit.corporation.shift_app.ui.theme.Blue
import nekit.corporation.shift_app.ui.theme.BlueLight
import nekit.corporation.shift_app.ui.theme.Grey
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListUi(viewModelFactory: ViewModelProvider.Factory, onClick: (User) -> Unit) {
    val viewModel: ListViewModel = viewModel(factory = viewModelFactory)
    val items = viewModel.users.collectAsState()
    Column {
        Text(
            stringResource(R.string.list),
            fontSize = 32.sp,
            modifier = Modifier
                .background(Blue)
                .padding(horizontal = 16.dp, vertical = 20.dp)
                .fillMaxWidth(),
            color = Color.White,

            )
        Spacer(Modifier.height(24.dp))
        PullToRefreshBox(
            isRefreshing = viewModel.showScroll.collectAsState().value,
            onRefresh = viewModel::update,
        ) {
            LazyColumn(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxSize()
            ) {

                items(items.value) {
                    ListItem(it) { uuid ->
                        viewModel.getUserByUUId(uuid)?.let { user -> onClick(user) }
                    }
                    Spacer(Modifier.height(12.dp))
                }
            }
        }
        Spacer(Modifier.height(24.dp))
    }

}

@Composable
fun ListItem(listModel: ListModel, onClick: (UUID) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(listModel.uuid) }
            .height(IntrinsicSize.Min)
            .shadow(4.dp, spotColor = Blue, shape = RoundedCornerShape(8.dp)),
        colors = CardDefaults.cardColors().copy(containerColor = Color.White)
    ) {
        Text(
            listModel.name.last + " " + listModel.name.first,
            fontSize = 22.sp,
            modifier = Modifier
                .background(BlueLight)
                .fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = 20.dp),
            color = Color.White,
            fontWeight = FontWeight.W500

        )
        Row(Modifier.padding(vertical = 16.dp)) {
            Image(
                painterResource(R.drawable.mountain), "", Modifier
                    .padding(
                        horizontal = 16.dp
                    )
                    .size(100.dp)
                    .clip(
                        CircleShape
                    ),
                contentScale = ContentScale.Crop
            )
            Column(Modifier.fillMaxWidth()) {
                Text(stringResource(R.string.address), color = Grey, fontSize = 16.sp)
                Text(
                    stringResource(
                        R.string.street,
                        listModel.address.street.name
                    ) + ", " + listModel.address.street.number, fontSize = 18.sp
                )
                Spacer(Modifier.weight(1f))
                Text(stringResource(R.string.phone), color = Grey, fontSize = 16.sp)
                Text(listModel.phoneNumber, color = Blue, fontSize = 18.sp)
            }
        }

    }
}