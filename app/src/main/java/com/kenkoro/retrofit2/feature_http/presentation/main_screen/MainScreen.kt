package com.kenkoro.retrofit2.feature_http.presentation.main_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kenkoro.retrofit2.R
import com.kenkoro.retrofit2.feature_http.domain.model.AuthRequest
import com.kenkoro.retrofit2.feature_http.domain.repository.Repository
import com.kenkoro.retrofit2.ui.theme.Retrofit2Theme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
  repository: Repository,
  modifier: Modifier = Modifier
) {
  val login = remember { mutableStateOf("") }
  val password = remember { mutableStateOf("") }
  val scope = rememberCoroutineScope()
  val snackbarHostState = remember { SnackbarHostState() }

  Scaffold(
    snackbarHost = {
      SnackbarHost(hostState = snackbarHostState)
    }
  ) {
    Surface(
      modifier = modifier
        .fillMaxSize()
        .padding(it)
    ) {
      Column(
        modifier = Modifier
          .fillMaxSize()
          .padding(top = 200.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
      ) {
        Image(
          painter = painterResource(id = R.drawable.ic_launcher_foreground),
          contentDescription = null,
          modifier = Modifier.size(130.dp)
        )
        TextField(
          value = login.value,
          onValueChange = { newLogin ->
            login.value = newLogin
          },
          label = {
            Text(text = "Login")
          },
          leadingIcon = {
            Icon(imageVector = Icons.Default.AccountCircle, contentDescription = null)
          },
          maxLines = 1
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
          value = password.value,
          onValueChange = { newPassword ->
            password.value = newPassword
          },
          label = {
            Text(text = "Password")
          },
          leadingIcon = {
            Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = null)
          },
          maxLines = 1,
          keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password
          )
        )
        Spacer(Modifier.height(16.dp))
        Button(
          onClick = {
            scope.launch {
              val user = repository.auth(
                AuthRequest(username = login.value, password = password.value)
              )

              if (user.firstName.isNotBlank()) {
                snackbarHostState.showSnackbar(
                  message = "${user.firstName} exists!",
                  withDismissAction = true,
                  duration = SnackbarDuration.Short
                )
              }
            }
          }
        ) {
          Text(text = "Get")
        }
      }
    }
  }
}

@Preview
@Composable
fun MainPreview() {
  Retrofit2Theme {
  }
}