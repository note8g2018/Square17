package com.coolme.me.square17.view.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Password
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.coolme.me.square17.view.modifierExtention.shadowWithColor
import com.coolme.me.square17.view.theme.*
import com.coolme.me.square17.controller.RegisterVM

@Composable
fun Password(
    xOffset: Dp,
    onNext: () -> Unit,
    onBack: () -> Unit,
    registerVM: RegisterVM,
    screenWidth: Int,
            )
{
    Box(
        modifier = Modifier
            .offset(x = xOffset + Dp(0.35f * screenWidth.toFloat()), y = 0.dp)
            //.absoluteOffset(x = xOffset + Dp(0.35f * screenWidth.toFloat()))
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
                isError = registerVM.passwordHasError,
                modifier = Modifier.fillMaxWidth(),
                value = registerVM.password1,
                textStyle = InputText,
                singleLine = true,
                onValueChange = {
                    registerVM.onPassword1Change(it)
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
                        imageVector = Icons.Filled.Password,
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
                    onNext = { onClickNext(registerVM, onNext) },
                                                 ),
                             )

            if (registerVM.passwordHasError)
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
                isError = registerVM.passwordHasError,
                modifier = Modifier.fillMaxWidth(),
                value = registerVM.password2,
                textStyle = InputText,
                singleLine = true,
                onValueChange = {
                    registerVM.onPassword2Change(it)
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
                        imageVector = Icons.Filled.Password,
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
                    onNext = { onClickNext(registerVM, onNext) },
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
                    },
                    modifier = Modifier
                        .weight(weight = 1.0f, fill = true)
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
                        onClickNext(registerVM, onNext)
                    },
                    modifier = Modifier
                        .weight(weight = 1.0f, fill = true)
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

private fun onClickNext(
    registerVM: RegisterVM,
    onNext: () -> Unit
                       )
{
    registerVM.validatePassword()
    if (!registerVM.passwordHasError)
    {
        onNext()
    }
}

//*****************************************

