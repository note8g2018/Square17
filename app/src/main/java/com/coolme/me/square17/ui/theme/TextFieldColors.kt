package com.coolme.me.square17.ui.theme

import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable

@Composable
fun OutlinedTextFieldColors() = TextFieldDefaults.outlinedTextFieldColors(
    unfocusedBorderColor = UnfocusedBorderColor,
    leadingIconColor = UnfocusedBorderColor,
    focusedBorderColor = TopBarContent,
    errorBorderColor = Error,
)