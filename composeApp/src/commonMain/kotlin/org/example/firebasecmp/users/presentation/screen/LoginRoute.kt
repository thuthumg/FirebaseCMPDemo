package org.example.firebasecmp.users.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.collectLatest
import org.example.firebasecmp.core.MARGIN_LARGE
import org.example.firebasecmp.core.MARGIN_MEDIUM
import org.example.firebasecmp.core.MARGIN_MEDIUM_2
import org.example.firebasecmp.core.MARGIN_MEDIUM_3
import org.example.firebasecmp.core.MARGIN_XLARGE
import org.example.firebasecmp.core.MARGIN_XXLARGE
import org.example.firebasecmp.core.POST_DATE_COLOR
import org.example.firebasecmp.core.TEXT_BIG
import org.example.firebasecmp.core.TEXT_LARGE
import org.example.firebasecmp.core.TEXT_REGULAR_2X
import org.example.firebasecmp.core.presentation.components.ErrorDialog
import org.example.firebasecmp.core.presentation.components.FirebasePrimaryButton
import org.example.firebasecmp.core.presentation.components.LoadingDialog
import org.example.firebasecmp.users.data.repository.AuthRepository.register
import org.example.firebasecmp.users.presentation.LoginViewModel
import org.example.firebasecmp.users.presentation.actions.LoginActions
import org.example.firebasecmp.users.presentation.events.LoginEvents
import org.example.firebasecmp.users.presentation.state.LoginState

@Composable
fun LoginRoute(viewModel: LoginViewModel,
               onNavigation: (LoginEvents) -> Unit) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit){

      //  register("ttm@gmail.com","111111","ttm")


        viewModel.events.collectLatest {
            onNavigation(it)
        }
    }

    LoginScreen(
        state = state,
        onAction = viewModel::handleActions
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    state: LoginState,
    onAction: (LoginActions) -> Unit
) {

    if(state.isLoading){
        LoadingDialog()
    }



    if(state.errMsg.isNotEmpty()){

        ErrorDialog(
            message = state.errMsg,
            onDismissRequest = {
                onAction(LoginActions.OnErrorDialogDismissed)
            }
        )



    }



    Scaffold (
        containerColor = Color.White
    ){
        Column(Modifier.padding(horizontal = MARGIN_MEDIUM_3),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Welcome",
                fontSize = TEXT_BIG,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(
                    top = 88.dp
                ))

            //Email
            OutlinedTextField(
                value = state.email,
                onValueChange = {
                    onAction(LoginActions.OnEmailChanged(it))
                },
                placeholder = {
                    Text("Email",
                        fontSize = TEXT_REGULAR_2X,
                        color = POST_DATE_COLOR
                    )
                },
                shape = RoundedCornerShape(MARGIN_MEDIUM),
                modifier = Modifier.fillMaxWidth().padding(top = MARGIN_LARGE)
            )


            //Password
            OutlinedTextField(
                value = state.password,
                onValueChange = {
                    onAction(LoginActions.OnPasswordChanged(it))
                },
                visualTransformation = PasswordVisualTransformation(),
                placeholder = {
                    Text("Password",
                        fontSize = TEXT_REGULAR_2X,
                        color = POST_DATE_COLOR
                    )
                },
                shape = RoundedCornerShape(MARGIN_MEDIUM),
                modifier = Modifier.fillMaxWidth().padding(top = MARGIN_MEDIUM_2)
            )

            //Login Button
            FirebasePrimaryButton(
                title = "Login",
                onClick = {
                    onAction(LoginActions.OnTapLogin)
                },
                modifier = Modifier .padding(top = MARGIN_MEDIUM_3)
                    .fillMaxWidth()
            )

            Text("Don't have an account", modifier = Modifier.padding(vertical = MARGIN_MEDIUM_2))
            //Signup Button


                Text("Sign Up",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = TEXT_REGULAR_2X,
                    modifier = Modifier.padding(MARGIN_MEDIUM)
                        .clickable{
                            onAction(LoginActions.OnTapSignUp)
                        })

        }


    }
}