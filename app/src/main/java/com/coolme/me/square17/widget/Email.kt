package com.coolme.me.square17.widget

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateOffsetAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.coolme.me.square17.enum.ScreenState
import com.coolme.me.square17.modifier.extention.shadowWithColor
import com.coolme.me.square17.ui.theme.*
import com.coolme.me.square17.util.isEmailValid

@Composable
fun Email(screenWidth : Int,
          xOffset : Dp,
          onNext : () -> Unit,
)
{
    var email: String by rememberSaveable { mutableStateOf("") }
    var isError: Boolean by rememberSaveable { mutableStateOf(false) }

    //var xOffsetState : Dp by remember { mutableStateOf(0.dp) }

//    val xOffset : Dp by animateDpAsState(
//        targetValue = xOffsetState,
//        animationSpec = tween(
//            durationMillis = 30000,
//        ),
//    )

    fun validate()
    {
        isError = !isEmailValid(email)
    }

    Box(
        modifier = Modifier
            .absoluteOffset(x = xOffset)
            .padding(PaddingAll)
            .fillMaxWidth()
            .shadowWithColor(
                color = TopBarContent,
                shadowRadius = ShadowRadius,
            )
            .background(color = BoxBackground)

    )
    {
        Column(
            modifier = Modifier
                .padding(PaddingColumn)
                .fillMaxWidth()
        )
        {
            OutlinedTextField(
                isError = isError,
                modifier = Modifier.fillMaxWidth(),
                value = email,
                textStyle = InputText,
                singleLine = true,
                onValueChange = {
//                        val regex = Regex("""[0-9a-z.@\-_]+""")
//                        val kk = it.matches(regex)
//                        text = when (kk)
//                        {
//                            true  -> it
//                            false -> it.dropLast(1)
//                        }
                    email = it
                },
                label = {
                    Text(
                        text = "Email",
                        style = LabelText,
                    )
                },
                colors = OutlinedTextFieldColors(),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Email,
                        contentDescription = "Email",
                    )
                },
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.None,
                    autoCorrect = false,
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next,
                ),
                keyboardActions = KeyboardActions(
                    onNext = {validate()},
                ),
            )

            if(isError)
            {
                Text(
                    text = "This is NOT valid Email",
                    style = StyleError,
                )
            }
            Spacer(Modifier.height(SpaceHeight))
            TextButton(
                onClick = {
                    //xOffsetState = Dp(-screenWidth.toFloat())
                    onNext()
                    validate()
                    //xOffset = animateDpAsState(targetValue = Dp(screenWidth.toFloat())).value
                    //xOffset = Dp(screenWidth.toFloat())
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = GreenButton)
            )
            {
                Text(
                    text = "Next",
                    style = StyleGreenButton,
                )
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowRight,
                    contentDescription = "Next",
                    tint = OnGreenButton,
                )
            }
        }
    }
}

//*****************************************

