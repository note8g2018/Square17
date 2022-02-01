package com.coolme.me.square17.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.coolme.me.square17.modifier.extention.shadowWithColor
import com.coolme.me.square17.ui.theme.*
import com.coolme.me.square17.viewModel.RegisterVM

@Composable
fun Username(
    registerVM: RegisterVM,
    screenWidth: Int,
    xOffset: Dp,
    onBack: () -> Unit,
    onNext: () -> Unit,
            )
{
    Box(
        modifier = Modifier
            .offset(
                x = xOffset + Dp(0.35f * 2 * screenWidth.toFloat()),
                y = 0.dp
                   )
            //.absoluteOffset(x = xOffset + Dp(0.35f * 2 * screenWidth.toFloat()) )
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
                isError = registerVM.usernameHasError,
                modifier = Modifier.fillMaxWidth(),
                value = registerVM.username,
                textStyle = InputText,
                singleLine = true,
                onValueChange = {
                    registerVM.onUsernameChange(it)
                },
                label = {
                    Text(
                        text = "Username",
                        style = LabelText,
                        )
                },
                colors = OutlinedTextFieldColors(),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Create,
                        contentDescription = "Username",
                        )
                },
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.None,
                    autoCorrect = false,
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Send,
                                                 ),
                keyboardActions = KeyboardActions(
                    //onNext = {validate()},
                    onSend = { onClickNext(registerVM, onNext) },
                                                 ),
                             )

            if (registerVM.usernameHasError)
            {
                Text(
                    text = "This is NOT valid Username",
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
                    text = "The first 5 characters must be from [a, z]",
                    style = StyleRole,
                    )
            }

            Row()
            {
                Text(
                    text = "3. ",
                    style = StyleRole,
                    )
                Text(
                    text = "The rest characters must be from [a, z] or [0, 9]",
                    style = StyleRole,
                    )
            }

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
                        onClickNext(registerVM, onNext)
                    },
                    modifier = Modifier
                        .weight(weight = 1.0f, fill = true)
                        .background(color = GreenButton)
                          )
                {
                    Text(
                        text = "Submit",
                        style = StyleGreenButton,
                        )
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowRight,
                        contentDescription = "Submit",
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
    registerVM.validateUsername()
    if (!registerVM.usernameHasError)
    {
        onNext()
    }
}

//*****************************************