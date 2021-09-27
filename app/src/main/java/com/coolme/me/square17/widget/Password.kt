package com.coolme.me.square17.widget

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.material.icons.filled.Create
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.Absolute.SpaceBetween
import androidx.compose.foundation.layout.Arrangement.SpaceBetween
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.coolme.me.square17.enum.ScreenState
import com.coolme.me.square17.modifier.extention.shadowWithColor
import com.coolme.me.square17.ui.theme.*
import com.coolme.me.square17.util.isEmailValid
import com.coolme.me.square17.util.isPasswordValid

@Composable
fun Password(screenWidth : Int,
             xOffset : Dp,
             onNext : () -> Unit,
             onBack : () -> Unit,
)
{

    var password1: String by rememberSaveable { mutableStateOf("") }
    var password2: String by rememberSaveable { mutableStateOf("") }
    var isError: Boolean by rememberSaveable { mutableStateOf(false) }

//    var xOffsetState : Dp by remember { mutableStateOf(Dp(screenWidth.toFloat())) }
//
//    val xOffset : Dp by animateDpAsState(
//        targetValue = xOffsetState,
//        animationSpec = tween(
//            durationMillis = 30000,
//        ),
//    )

    fun validate()
    {
        println("p1 = $password1")
        println("p2 = $password2")
        isError = !isPasswordValid(password1 = password1, password2 = password2)
    }

    Box(
        modifier = Modifier
            .absoluteOffset(x = xOffset + Dp(0.35f * screenWidth.toFloat()))
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
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(space = SpaceColumnHeight),
        )
        {
            OutlinedTextField(

                isError = isError,
                modifier = Modifier.fillMaxWidth(),
                value = password1,
                textStyle = InputText,
                singleLine = true,
                onValueChange = {
                    password1 = it
                },
                label = {
                    Text(
                        text = "Password",
                        style = LabelText,
                    )
                },
                colors = OutlinedTextFieldColors(),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Create,
                        contentDescription = "Password",
                    )
                },
                visualTransformation = PasswordVisualTransformation(mask = '*'),
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.None,
                    autoCorrect = false,
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Next,
                ),
                keyboardActions = KeyboardActions(
                    onNext = {validate()},
                ),
            )

            if(isError)
            {
                Text(
                    text = "This is NOT valid Password",
                    style = StyleError,
                )
            }

            Row()
            {
                Text(
                    text = "1. ",
                    style = StyleRole,
                )
                Text(
                    text = "At least 8 characters [8, 31]",
                    style = StyleRole,
                )
            }
            Row()
            {
                Text(
                    text = "2. ",
                    style = StyleRole,
                )
                Text(
                    text = "Must be from [a, z] or [A, Z] or [0, 9] or " +
                            "[ ! @ # $ % ^ & * ( ) - _ = + ]",
                    style = StyleRole,
                )
            }

            OutlinedTextField(

                isError = isError,
                modifier = Modifier.fillMaxWidth(),
                value = password2,
                textStyle = InputText,
                singleLine = true,
                onValueChange = {
                    password2 = it
                },
                label = {
                    Text(
                        text = "Repeat Password",
                        style = LabelText,
                    )
                },
                colors = OutlinedTextFieldColors(),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Create,
                        contentDescription = "Password",
                    )
                },
                visualTransformation = PasswordVisualTransformation(mask = '*'),
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.None,
                    autoCorrect = false,
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Next,
                ),
                keyboardActions = KeyboardActions(
                    onNext = {validate()},
                ),
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
            )
            {
                TextButton(
                    onClick = {
                        onBack()
                        validate()
                    },
                    modifier = Modifier
                        .weight(weight = 1.0f, fill = true)
                        //.fillMaxWidth(fraction = 0.5f)
                        .background(color = Error)
                )
                {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowLeft,
                        contentDescription = "Back",
                        tint = OnGreenButton,
                    )
                    Text(
                        text = "Back",
                        style = StyleGreenButton,
                    )
                }

                TextButton(
                    onClick = {
                        onNext()
                        validate()
                    },
                    modifier = Modifier
                        .weight(weight = 1.0f, fill = true)
                        //.fillMaxWidth()
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
}



//*****************************************

