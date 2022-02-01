package com.coolme.me.square17.screen

import android.graphics.drawable.Icon
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.focusTarget
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.navigation.NavController
import com.coolme.me.square17.modifier.extention.shadowWithColor
import com.coolme.me.square17.ui.theme.*
import com.coolme.me.square17.widget.TopBarLogin
import org.w3c.dom.Text

@Composable
fun Login(navController: NavController)
{
    val focusRequester by remember { mutableStateOf(FocusRequester()) }

    Scaffold(
        modifier = Modifier
            .focusRequester(focusRequester)
            .focusTarget()
            .pointerInput(Unit) { detectTapGestures { focusRequester.requestFocus() } },
        topBar = { TopBarLogin() },
    )
    {
        var username: String by rememberSaveable { mutableStateOf("") }
        var password1: String by rememberSaveable { mutableStateOf("") }
        var isError: Boolean by rememberSaveable { mutableStateOf(false) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())

        )
        {
            Box(
                modifier = Modifier
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
                        value = username,
                        textStyle = InputText,
                        singleLine = true,
                        onValueChange = {
                            username = it
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
                                imageVector = Icons.Filled.VerifiedUser,
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
                            onSend = {  },
                        ),
                    )

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
                            onNext = {},
                        ),
                    )

                    if(isError)
                    {
                        Text(
                            text = "Your username or password is wrong",
                            style = StyleError,
                        )
                    }

                    TextButton(
                        onClick = {

                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = GreenButton)
                    )
                    {
                        Text(
                            text = "Login",
                            style = StyleGreenButton,
                        )
                    }

                    TextButton(
                        onClick = {
                           navController.navigate("Registration")
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = Error)
                    )
                    {
                        Text(
                            text = "Register",
                            style = StyleGreenButton,
                        )
                    }
                }

            }
            
            Spacer(modifier = Modifier.weight(weight = 1.0f, fill = true))

            OutlinedButton(
                onClick = {

                },
                modifier = Modifier
                    .padding(PaddingAll)
                    .fillMaxWidth()
                    .background(color = GreenButton)
            )
            {
                Text(
                    text = "I Forgot my Password",
                    style = StyleOutlinedButton,
                )
            }
        }
    }
}