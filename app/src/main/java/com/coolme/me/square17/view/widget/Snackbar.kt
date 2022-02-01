package com.coolme.me.square17.view.widget

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.coolme.me.square17.util.ErrorMine


@Composable
fun SnackbarMine(
    snackberHostState: SnackbarHostState,
    error: ErrorMine? = null,
)
{
    SnackbarHost(
        hostState = snackberHostState,
        snackbar = { SnackbarData ->
            Snackbar(
                backgroundColor = Color.Red,
            )
            {
                Text(text = "mmmmmmmmmmm")
            }
        },
    )
}