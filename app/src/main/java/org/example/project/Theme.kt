package org.example.project

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.composables.compose.ripple.Material3DefaultRipple
import com.composables.compose.ripple.rememberRippleIndication
import com.composeunstyled.theme.ComponentInteractiveSize
import com.composeunstyled.theme.ThemeProperty
import com.composeunstyled.theme.ThemeToken
import com.composeunstyled.theme.buildTheme

val textStyles = ThemeProperty<TextStyle>("text_styles")
val headerStyle = ThemeToken<TextStyle>("header")
val titleStyle = ThemeToken<TextStyle>("title")
val bodyStyle = ThemeToken<TextStyle>("body")

val colors = ThemeProperty<Color>("colors")
val primaryColor = ThemeToken<Color>("primary")
val onPrimaryColor = ThemeToken<Color>("on_primary")
val backgroundColor = ThemeToken<Color>("background")
val onBackgroundColor = ThemeToken<Color>("on_background")
val outline = ThemeToken<Color>("outline")
val focusRing = ThemeToken<Color>("focusRing")

val shapes = ThemeProperty<Shape>("shapes")
val smallShape = ThemeToken<Shape>("small")

private val Inter = FontFamily(
    Font(R.font.inter, FontWeight.Normal),
)

val AppTheme = buildTheme {
    defaultContentColor = Color(0xFF171717)

    val accentColor = Color(0xFF3b82f6)
    properties[colors] = mapOf(
        backgroundColor to Color(0xFFFAFAFA),
        onBackgroundColor to defaultContentColor,
        primaryColor to accentColor,
        onPrimaryColor to Color.White,
        outline to Color.Black.copy(0.1f),
        focusRing to Color(0xff1e5ac3),
    )
    defaultIndication = rememberRippleIndication(
        color = Color.Black,
        rippleAlpha = Material3DefaultRipple
    )
    defaultTextStyle = TextStyle(
        fontFamily = Inter,
    )
    properties[textStyles] = mapOf(
        headerStyle to defaultTextStyle.copy(
            fontSize = 32.sp,
            fontWeight = FontWeight.Normal,
            letterSpacing = (-2.5).sp
        ),
        titleStyle to defaultTextStyle.copy(fontSize = 18.sp, fontWeight = FontWeight.SemiBold),
        bodyStyle to defaultTextStyle
    )
    properties[shapes] = mapOf(
        smallShape to RoundedCornerShape(100)
    )
    defaultComponentInteractiveSize = ComponentInteractiveSize(
        touchInteractionSize = 48.dp,
        nonTouchInteractionSize = 32.dp,
    )
}
