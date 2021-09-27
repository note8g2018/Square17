package com.coolme.me.square17

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.coolme.me.square17.modifier.extention.shadowWithColor
import com.coolme.me.square17.widget.TopBarRegistration
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import com.coolme.me.square17.screen.Registration
import com.coolme.me.square17.ui.theme.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContent {
            Square16Theme(darkTheme = true) {
                Registration()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview()
{
    Square16Theme {
        Registration()
    }
}




