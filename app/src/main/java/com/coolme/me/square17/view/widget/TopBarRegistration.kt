package com.coolme.me.square17.view.widget

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.coolme.me.square17.view.theme.BarIcon
import com.coolme.me.square17.view.theme.RegistrationTopBar
import com.coolme.me.square17.view.theme.TopBarContent

@Composable
fun TopBarRegistration(navController: NavController)
{
    TopAppBar(
        contentColor = TopBarContent,
        modifier = Modifier.fillMaxWidth(),
        content =
        {
            Box(modifier = Modifier.fillMaxWidth())
            {
                IconButton(
                    onClick = {
                      navController.popBackStack()
                    },
                )
                {
                    Icon(
                        modifier = Modifier.size(BarIcon),
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Backward",
                    )
                }
                Box(
                    Modifier.align(Alignment.Center)
                )
                {
                    Text(
                        text = "Registration",
                        textAlign = TextAlign.Center,
                        maxLines = 1,
                        style = RegistrationTopBar,
                    )
                }
            }
        }
    )
}