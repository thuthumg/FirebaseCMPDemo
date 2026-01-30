package org.example.firebasecmp.post.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.collectLatest
import org.example.firebasecmp.core.MARGIN_LARGE
import org.example.firebasecmp.core.TEXT_LARGE
import org.example.firebasecmp.core.presentation.components.ErrorDialog
import org.example.firebasecmp.core.presentation.components.LoadingDialog
import org.example.firebasecmp.post.presentation.actions.CreatePostActions
import org.example.firebasecmp.post.presentation.actions.PostDetailActions
import org.example.firebasecmp.post.presentation.events.PostDetailEvents
import org.example.firebasecmp.post.presentation.state.PostDetailState
import org.example.firebasecmp.post.presentation.viewmodels.PostDetailViewModel


@Composable
fun PostDetailRoute(viewModel: PostDetailViewModel,
                    onNavigation: (PostDetailEvents) -> Unit) {

    val state by viewModel.state.collectAsStateWithLifecycle()


    LaunchedEffect(Unit){
        viewModel.events.collectLatest {
            onNavigation(it)
        }
    }

    PostDetailScreen(
        postDetailState = state,
        onAction = viewModel::handleAction
    )

}




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostDetailScreen(
    postDetailState: PostDetailState,
    onAction: (PostDetailActions) -> Unit
) {

    if(postDetailState.isLoading){
        LoadingDialog()
    }



    if(postDetailState.error.isNotEmpty()){

        ErrorDialog(
            message = postDetailState.error,
            onDismissRequest = {
                onAction(PostDetailActions.OnErrorDialogDismissed)
            }
        )



    }


    Scaffold(
        containerColor = Color.White,
        topBar = {
            TopAppBar(
                title = {
                    Text("Post Detail",
                        fontWeight = FontWeight.Bold,
                        fontSize = TEXT_LARGE
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                ),
                navigationIcon = {
                    Icon(Icons.AutoMirrored.Default.KeyboardArrowLeft,
                        contentDescription = null,
                        modifier = Modifier.size(MARGIN_LARGE).clickable{
                            onAction(PostDetailActions.OnTapBack)
                        })
                }

            )
        }
    ){ paddingValues ->
        Box(
            modifier = Modifier.padding(paddingValues).fillMaxSize()
        ){
            postDetailState.post?.let {
                PostItem(
                    post = it,
                    onTapPostItem = {}
                )
            }

        }
    }
}