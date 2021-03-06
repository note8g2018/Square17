package com.coolme.me.square17.widget

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.coolme.me.square17.ui.theme.RegistrationTopBar
import com.coolme.me.square17.ui.theme.TopBarContent

@Composable
fun TopBarLogin()
{
    TopAppBar(
        contentColor = TopBarContent,
        modifier = Modifier.fillMaxWidth(),
        content =
        {
            Box(modifier = Modifier.fillMaxWidth())
            {
                Box(
                    Modifier.align(Alignment.Center)
                )
                {
                    Text(
                        text = "Login",
                        textAlign = TextAlign.Center,
                        maxLines = 1,
                        style = RegistrationTopBar,
                    )
                }
            }
        }
    )
}