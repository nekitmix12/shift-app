package nekit.corporation.shift_app

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProfileUi() {
    LazyColumn {
        item() {
            Label()
        }
        item {
            ProfileMainInfo()
        }
        item {
            InfoLabel()
        }
        item {
            BasicInfo()
        }
        item {
            ContactInformation()
        }
        item {
            LocationInformation()
        }
        item {
            Photos()
        }

    }
}

@Composable
fun Label() {

}


@Composable
fun ProfileMainInfo() {

}

@Composable
fun InfoLabel() {

}

@Composable
fun BasicInfo() {

}

@Composable
fun ContactInformation() {

}

@Composable
fun LocationInformation() {

}

@Composable
fun Photos() {

}
