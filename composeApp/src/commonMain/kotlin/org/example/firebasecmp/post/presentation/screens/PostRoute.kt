package org.example.firebasecmp.post.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import firebasecmpdemo.composeapp.generated.resources.Res
import firebasecmpdemo.composeapp.generated.resources.chat_ic
import firebasecmpdemo.composeapp.generated.resources.dummy_profile
import firebasecmpdemo.composeapp.generated.resources.post_placeholder
import kotlinx.coroutines.flow.collectLatest
import org.example.firebasecmp.core.MARGIN_CARD_MEDIUM_2
import org.example.firebasecmp.core.MARGIN_MEDIUM
import org.example.firebasecmp.core.MARGIN_MEDIUM_2
import org.example.firebasecmp.core.MARGIN_MEDIUM_3
import org.example.firebasecmp.core.MARGIN_XLARGE
import org.example.firebasecmp.core.MARGIN_XXLARGE
import org.example.firebasecmp.core.POST_DATE_COLOR
import org.example.firebasecmp.core.TEXT_LARGE
import org.example.firebasecmp.core.TEXT_REGULAR
import org.example.firebasecmp.core.TEXT_REGULAR_2X
import org.example.firebasecmp.post.data.vos.PostVO
import org.example.firebasecmp.post.presentation.actions.PostActions
import org.example.firebasecmp.post.presentation.components.UserInfo
import org.example.firebasecmp.post.presentation.events.PostEvents
import org.example.firebasecmp.post.presentation.state.PostState
import org.example.firebasecmp.post.presentation.viewmodels.PostViewModel
import org.jetbrains.compose.resources.painterResource


@Composable
fun PostRoute(viewModel: PostViewModel,
              onNavigation: (PostEvents) -> Unit) {

    val state by viewModel.state.collectAsStateWithLifecycle()


    LaunchedEffect(Unit){
        viewModel.events.collectLatest {
            onNavigation(it)
        }
    }

    PostScreen(
        state = state,
        onActions = {
            viewModel.handleAction(it)
        })
}




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostScreen(
    state : PostState,
    onActions: (PostActions) -> Unit
) {

//    LaunchedEffect(Unit){
//      //  AuthRepository.register(email = "thu45@gmail.com", password = "aaAA11@@", "Thu")
//
////       val user =  AuthRepository.login(email = "thu45@gmail.com", password = "aaAA11@@")
////        println("Logged in user = $user")
//
////        val firebaseUser = Authentication.getCurrentUser()
////        println("Current user id = ${firebaseUser?.uid}")
//
//      //  AuthRepository.login(email = "thu45@gmail.com", password = "aaAA11@@")
////        val user = AuthRepository.getLoggedInUser()
////        println("Logged in user = $user")
////
////        AuthRepository.register(email = "thuthu@gmail.com", password = "aaAA11@@", "Thu")
////        val user = Authentication.getCurrentUser()
////        println("Logged in user = ${user?.uid}")
//
////        FireStoreDataSource.createNewPost(
////            userName = "Jack Black",
////            content = "This is a test post."
////        )
//
//    }




    Scaffold(
        containerColor = Color.White,
        topBar = {
            TopAppBar(
                title = {
                    Text("Hello ${state.user?.userName ?: ""}...",
                        fontWeight = FontWeight.Bold,
                        fontSize = TEXT_LARGE
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                ),
                actions = {
                    Icon(
                        Icons.Default.Add,
                        contentDescription = null,
                        modifier = Modifier.padding(MARGIN_MEDIUM)
                            .clickable{
                                onActions(PostActions.OnTapCreatePost)
                            }
                    )
                }

            )
        }
    ){ paddingValues ->
        LazyColumn(
            modifier = Modifier.padding(paddingValues),
            contentPadding = PaddingValues(vertical = MARGIN_MEDIUM),
            verticalArrangement = Arrangement.spacedBy(MARGIN_MEDIUM)) {
            itemsIndexed(state.posts){ index, post ->
                PostItem(post = post,
                    onTapPostItem = {
                        onActions(PostActions.OnTapPost(it))
                    })
                if(index<(state.posts.count() - 1)){
                    HorizontalDivider(modifier = Modifier.padding(
                        vertical = MARGIN_CARD_MEDIUM_2
                    ))
                }
            }
        }
    }
}

@Composable
fun PostItem(post: PostVO,
             onTapPostItem:(String) -> Unit) {
    Column {
        UserInfo(
            userName = post.postUserName,
            date = post.postDate,
            showContextualMenu = true)
        Text(post.content,
            modifier = Modifier.padding(start = MARGIN_MEDIUM_2, end = MARGIN_MEDIUM_2, top = MARGIN_CARD_MEDIUM_2,),
            maxLines = 4,
            overflow = TextOverflow.Ellipsis)

        Image(
            contentScale = ContentScale.Crop,
            painter = painterResource(Res.drawable.post_placeholder),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth().height(265.dp)
                .padding(top = MARGIN_CARD_MEDIUM_2).clickable{
                    onTapPostItem(post.id.toString())
                }
        )

        Text("4 Likes",
            modifier = Modifier.padding(top = MARGIN_CARD_MEDIUM_2,
                start = MARGIN_MEDIUM_2, end = MARGIN_MEDIUM_2))

        Row(modifier = Modifier.padding(top = MARGIN_CARD_MEDIUM_2)) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1.0f)){
                Icon(
                    Icons.Outlined.ThumbUp,
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(MARGIN_MEDIUM))
                Text("Like")
            }
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1.0f)) {
                Icon(
                    painterResource(Res.drawable.chat_ic),
                    contentDescription = null,
                    modifier = Modifier.size(MARGIN_MEDIUM_3)
                )
                Spacer(modifier = Modifier.width(MARGIN_MEDIUM))

                Text("Comment")
            }
        }


    }
}




//    LaunchedEffect(Unit){
//
//       // getPostOneshotTest()
//      //  getPostRealtimeTest()
//
//
////        FireStoreDataSource.getPostsRealtime().collect {
////            println("Posts from firestore ==> $it")
////        }
//
////        val postIds = FireStoreDataSource.getPostsOneShot()
////        println("Posts from firestore one shot ==> $postIds")
////
//
////        RealtimeDatabaseDataSource.getPostsRealtime()
////            .collect {
////                println("Posts from realtimedb ==> $it")
////            }
//
//        val posts = RealtimeDatabaseDataSource.getPostsOneShot()
//
//                println("Posts from realtimedb one shot ==> $posts")
//
//    }

