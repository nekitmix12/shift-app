package nekit.corporation.shift_app.common_ui

import android.graphics.BlurMaskFilter
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nekit.corporation.shift_app.R
import nekit.corporation.shift_app.ui.theme.Blue
import nekit.corporation.shift_app.ui.theme.Grey

@Composable
fun InfoLabel(
    icon: ImageVector, text: String, content: @Composable (ColumnScope.() -> Unit)
) {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp),

        ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                icon,
                "",
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .padding(end = 8.dp)
                    .size(24.dp),
                tint = Blue
            )
            Text(text, fontSize = 20.sp, fontWeight = FontWeight.W600)
        }
        HorizontalDivider(
            Modifier.padding(vertical = 16.dp), thickness = 1.dp, color = Grey.copy(alpha = 0.2f)
        )
        content()
        HorizontalDivider(
            Modifier.padding(vertical = 16.dp), thickness = 1.dp, color = Grey.copy(alpha = 0.2f)
        )
    }
}

@Composable
fun Label(onBackClick: () -> Unit) {
    val blurPx = with(LocalDensity.current) { 2.dp.toPx() }
    val heightPx = with(LocalDensity.current) { 2.dp.toPx() }
    val cornerPx = with(LocalDensity.current) { 0.dp.toPx() }
    val heightUnderPx = with(LocalDensity.current) { 10.dp.toPx() }

    Row(
        Modifier
            .background(Blue)
            .drawBehind {
                shadow(blurPx, heightUnderPx, heightPx, cornerPx)
            }.fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 22.dp)

            ,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onBackClick) {
            Icon(
                ImageVector.vectorResource(R.drawable.back),
                "",
                tint = Color.White,
                modifier = Modifier
                    .size(48.dp)
                    .padding(end = 12.dp)
            )
        }
        Text(
            stringResource(R.string.user_profile),
            fontSize = 24.sp,
            color = Color.White,
            fontWeight = FontWeight.W600
        )
    }
}

fun DrawScope.shadow(blurPx: Float, heightUnderPx: Float, heightPx: Float, cornerPx: Float) {


    val paint = Paint().asFrameworkPaint().apply {
        color = Black.copy(alpha = 0.15f).toArgb()
        isAntiAlias = true
        maskFilter = BlurMaskFilter(blurPx, BlurMaskFilter.Blur.NORMAL)
    }
    val top = size.height - heightPx / 2f - heightUnderPx

    val cornerRadius = cornerPx / 2f
    val left = 2f
    val right = size.width - 2
    val bottom = top + heightPx

    val rectF = android.graphics.RectF(left, top, right, bottom)
    drawContext.canvas.nativeCanvas.drawRoundRect(
        rectF, cornerRadius, cornerRadius, paint
    )
}