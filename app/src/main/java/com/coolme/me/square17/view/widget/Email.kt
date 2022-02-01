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
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.coolme.me.square17.view.modifierExtention.shadowWithColor
import com.coolme.me.square17.view.theme.*
import com.coolme.me.square17.controller.RegisterVM

@Composable
fun Email(
    xOffset: Dp,
    onNext: () -> Unit,
    registerVM: RegisterVM,
         )
{
    Box(
        modifier = Modifier
            .offset(x = xOffset, y = 0.dp)
            //.absoluteOffset(x = xOffset)
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
                isError = registerVM.emailHasError,
                modifier = Modifier.fillMaxWidth(),
                value = registerVM.email,
                textStyle = InputText,
                singleLine = true,
                onValueChange = {
                    registerVM.onEmailChange(it)
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
                    onNext = { onClickNext(registerVM, onNext) },
                                                 ),
                             )

            if (registerVM.emailHasError)
            {
                Text(
                    text = "This is NOT valid Email",
                    style = StyleError,
                    )
            }
            Spacer(Modifier.height(SpaceHeight))
            TextButton(
                onClick = {
                    onClickNext(registerVM, onNext)
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

private fun onClickNext(
    registerVM: RegisterVM,
    onNext: () -> Unit
                       )
{
    registerVM.validateEmail()
    if (!registerVM.emailHasError)
    {
        onNext()
    }
}

//*****************************************

