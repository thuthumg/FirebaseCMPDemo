package org.example.firebasecmp.post.presentation.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.collectLatest
import org.example.firebasecmp.core.MARGIN_MEDIUM
import org.example.firebasecmp.core.MARGIN_MEDIUM_2
import org.example.firebasecmp.core.MARGIN_XLARGE
import org.example.firebasecmp.core.MARGIN_XXLARGE
import org.example.firebasecmp.core.POST_DATE_COLOR
import org.example.firebasecmp.core.TEXT_BIG
import org.example.firebasecmp.core.TEXT_LARGE
import org.example.firebasecmp.core.TEXT_REGULAR_2X
import org.example.firebasecmp.core.network.FireStoreDataSource
import org.example.firebasecmp.core.presentation.components.ErrorDialog
import org.example.firebasecmp.core.presentation.components.FirebasePrimaryButton
import org.example.firebasecmp.core.presentation.components.LoadingDialog
import org.example.firebasecmp.post.presentation.actions.CreatePostActions
import org.example.firebasecmp.post.presentation.components.UserInfo
import org.example.firebasecmp.post.presentation.events.CreatePostEvents
import org.example.firebasecmp.post.presentation.state.CreatePostState
import org.example.firebasecmp.post.presentation.viewmodels.CreatePostViewModel
import org.example.firebasecmp.users.presentation.actions.LoginActions

@Composable
fun CreatePostRoute(viewModel: CreatePostViewModel,
                    onNavigation: (CreatePostEvents) -> Unit) {

    val state by viewModel.state.collectAsStateWithLifecycle()


    LaunchedEffect(Unit){
        viewModel.events.collectLatest {
            onNavigation(it)
        }
    }

    CreatePostScreen(
        state = state,
        onAction = viewModel::handleAction
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreatePostScreen(state: CreatePostState,
                     onAction : (CreatePostActions) -> Unit) {

    if(state.isLoading){
        LoadingDialog()
    }



    if(state.errMsg.isNotEmpty()){

        ErrorDialog(
            message = state.errMsg,
            onDismissRequest = {
                onAction(CreatePostActions.OnErrorDialogDismissed)
            }
        )



    }




    Scaffold(
        containerColor = Color.White,
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors().copy(
                    containerColor = Color.White
                ),
                title = {
                    Text("Create Post",
                        fontSize = TEXT_LARGE,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    Icon(
                        Icons.AutoMirrored.Default.KeyboardArrowLeft,
                        contentDescription = null,
                        modifier = Modifier.padding(MARGIN_MEDIUM)
                            .size(MARGIN_XLARGE)
                            .clickable{
                            onAction(CreatePostActions.OnTapBack)
                        }
                    )
                }
            )
        }
    ) { paddingValues ->

        Column(modifier = Modifier.padding(paddingValues)) {
            //User Info


            UserInfo(
                userName =
                    state.loggedInUser?.userName?: "",
                date = ""
            )

            Spacer(modifier = Modifier.height(MARGIN_MEDIUM_2))


            //Text Area

            Surface(
                shape = RoundedCornerShape(MARGIN_MEDIUM_2),
                color = Color.White,
                border = BorderStroke(width = 1.dp, color = Color.Black),
                modifier = Modifier.height(200.dp).fillMaxWidth().padding(
                    horizontal = MARGIN_MEDIUM_2
                )
            ){
                TextField(
                    value = state.content,
                    onValueChange = {
                        onAction(CreatePostActions.OnContentChanged(it))
                    },
                    colors = TextFieldDefaults.colors().copy(
                        disabledContainerColor = Color.White,
                        errorContainerColor = Color.White,
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        disabledIndicatorColor = Color.Transparent,
                        errorIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    placeholder = {
                        Text("What's on your mind....?",
                            color = POST_DATE_COLOR,
                            fontSize = TEXT_REGULAR_2X
                        )
                    },

                    modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.height(MARGIN_MEDIUM_2))

            //Create Post Button
            FirebasePrimaryButton(
                title = "Create Post",
                onClick = {
                    onAction(CreatePostActions.OnTapCreatePost)
                },
                modifier = Modifier.fillMaxWidth().padding(horizontal = MARGIN_MEDIUM_2)
            )

        }


    }


}