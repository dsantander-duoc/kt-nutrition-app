package com.example.mobiledevelopment

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.mobiledevelopment.helper.AuthHelper
import com.example.mobiledevelopment.shared.NavBar
import com.example.mobiledevelopment.ui.components.AccessibleTextField
import com.example.mobiledevelopment.ui.components.LinkButton
import com.example.mobiledevelopment.ui.components.PrimaryButton
import com.example.mobiledevelopment.ui.components.SecondaryButton
import com.example.mobiledevelopment.ui.components.clearFocusOnTapOutside
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Login(goToRegister: () -> Unit, goToRecoverPassword: () -> Unit, goBack: () -> Unit, goHome: (String) -> Unit) {
    val focusManager = LocalFocusManager.current
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    Scaffold(topBar = { NavBar(title = "Bienvenido a NutriGO!", screenDescription = "Pantalla de inicio de sesión, ingresa usuario y clave. Si no recuerdas tu clave puedes recuperarla. Si no tienes una cuenta, puedes registrarte.", goBack = goBack) }) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .clearFocusOnTapOutside(focusManager),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                Image(
                    painter = painterResource(id = R.drawable.logo_app),
                    contentDescription = "Logo de la aplicación NutriGO",
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .heightIn(min = 80.dp)
                )

                AccessibleTextField(
                    value = username,
                    onValueChange = { username = it },
                    label = "Usuario",
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    imeAction = ImeAction.Next
                )

                AccessibleTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = "Contraseña",
                    isPassword = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    imeAction = ImeAction.Done
                )

                PrimaryButton(text = "Iniciar sesión", onClick = {
                    scope.launch {
                        val result = AuthHelper.login(context,username, password)
                        if (result.ok && result.user != null) {
                            goHome(result.user.name)
                        }
                        else {
                            Toast.makeText(context, result.message, Toast.LENGTH_SHORT).show()
                        }
                    }

                })

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    SecondaryButton(
                        text = "Registrarme",
                        onClick = goToRegister,
                        modifier = Modifier.weight(1f)
                    )
                    LinkButton(
                        text = "Olvidé mi contraseña",
                        onClick = goToRecoverPassword,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}
