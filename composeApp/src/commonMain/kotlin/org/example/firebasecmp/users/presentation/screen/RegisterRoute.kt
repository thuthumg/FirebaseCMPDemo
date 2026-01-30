package org.example.firebasecmp.users.presentation.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.collectLatest
import org.example.firebasecmp.core.MARGIN_LARGE
import org.example.firebasecmp.core.MARGIN_MEDIUM
import org.example.firebasecmp.core.MARGIN_MEDIUM_2
import org.example.firebasecmp.core.MARGIN_MEDIUM_3
import org.example.firebasecmp.core.MARGIN_XLARGE
import org.example.firebasecmp.core.POST_DATE_COLOR
import org.example.firebasecmp.core.TEXT_BIG
import org.example.firebasecmp.core.TEXT_LARGE
import org.example.firebasecmp.core.TEXT_REGULAR_2X
import org.example.firebasecmp.core.presentation.components.ErrorDialog
import org.example.firebasecmp.core.presentation.components.FirebasePrimaryButton
import org.example.firebasecmp.core.presentation.components.LoadingDialog
import org.example.firebasecmp.users.presentation.RegisterViewModel
import org.example.firebasecmp.users.presentation.actions.RegisterActions
import org.example.firebasecmp.users.presentation.events.RegisterEvents
import org.example.firebasecmp.users.presentation.state.RegisterState
import org.jetbrains.compose.resources.painterResource

@Composable
fun RegisterRoute(viewModel: RegisterViewModel,
               onNavigation: (RegisterEvents) -> Unit) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit){

        viewModel.events.collectLatest {
            onNavigation(it)
        }
    }

    RegisterScreen(
        state = state,
        onAction = viewModel::handleActions
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    state: RegisterState,
    onAction: (RegisterActions) -> Unit
) {

    if(state.isLoading){
        LoadingDialog()
    }



    if(state.errMsg.isNotEmpty()){

        ErrorDialog(
            message = state.errMsg,
            onDismissRequest = {
                onAction(RegisterActions.OnErrorDialogDismissed)
            }
        )



    }

    Scaffold (
        containerColor = Color.White,
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                ),
                title = {
//                    Text("Back",
//                        fontSize = TEXT_LARGE,
//                        color = Color.White,
//                        fontWeight = FontWeight.Bold)
                },
                navigationIcon = {

                    Icon(
                        Icons.AutoMirrored.Default.ArrowBack,
                        tint = Color.Black,
                        modifier = Modifier.size(MARGIN_XLARGE).clickable{
                            onAction(RegisterActions.OnTapBack)
                        },
                        contentDescription = "Back"
                    )


                }

            )
        }
    ){ innerPadding ->
        Box(modifier = Modifier.padding(innerPadding).fillMaxSize()){
            Column(Modifier.padding(horizontal = MARGIN_MEDIUM_3),
                horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Register",
                    fontSize = TEXT_BIG,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier)

                //User Name
                OutlinedTextField(
                    value = state.userName,
                    onValueChange = {
                        onAction(RegisterActions.OnUserNameChanged(it))
                    },
                    placeholder = {
                        Text("User Name",
                            fontSize = TEXT_REGULAR_2X,
                            color = POST_DATE_COLOR
                        )
                    },
                    shape = RoundedCornerShape(MARGIN_MEDIUM),
                    modifier = Modifier.fillMaxWidth().padding(top = MARGIN_LARGE)
                )

                //Email
                OutlinedTextField(
                    value = state.email,
                    onValueChange = {
                        onAction(RegisterActions.OnEmailChanged(it))
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
                        onAction(RegisterActions.OnPasswordChanged(it))
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

                //Sign Up Button
                FirebasePrimaryButton(
                    title = "Sign Up",
                    onClick = {
                        onAction(RegisterActions.OnTapRegister)
                    },
                    modifier = Modifier .padding(top = MARGIN_MEDIUM_3)
                        .fillMaxWidth()
                )


            }
        }



    }
}