package org.example.firebasecmp

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import org.example.firebasecmp.post.presentation.events.CreatePostEvents
import org.example.firebasecmp.post.presentation.events.PostDetailEvents
import org.example.firebasecmp.post.presentation.events.PostEvents
import org.example.firebasecmp.post.presentation.screens.CreatePostRoute
import org.example.firebasecmp.post.presentation.screens.PostDetailRoute
import org.example.firebasecmp.post.presentation.screens.PostRoute
import org.jetbrains.compose.ui.tooling.preview.Preview

import org.example.firebasecmp.post.presentation.screens.PostScreen
import org.example.firebasecmp.post.presentation.viewmodels.CreatePostViewModel
import org.example.firebasecmp.post.presentation.viewmodels.PostDetailViewModel
import org.example.firebasecmp.post.presentation.viewmodels.PostViewModel
import org.example.firebasecmp.users.network.Authentication
import org.example.firebasecmp.users.presentation.LoginViewModel
import org.example.firebasecmp.users.presentation.RegisterViewModel
import org.example.firebasecmp.users.presentation.events.LoginEvents
import org.example.firebasecmp.users.presentation.events.RegisterEvents
import org.example.firebasecmp.users.presentation.screen.LoginRoute
import org.example.firebasecmp.users.presentation.screen.RegisterRoute

@Composable
@Preview
fun App() {

    val navController = rememberNavController()


    MaterialTheme {

        NavHost(navController,startDestination = if (Authentication.isUserLoggedIn()) AppRoute.Home else AppRoute.Login){

            composable<AppRoute.Login>{
                val loginViewModel = viewModel { LoginViewModel() }
                LoginRoute(
                    loginViewModel,
                    onNavigation = {
                        when(it){
                            LoginEvents.NavigateToHome -> {
                                navController.navigate(AppRoute.Home)
                            }
                            LoginEvents.NavigateToRegister -> {
                                navController.navigate(AppRoute.Register)
                            }
                        }
                    }
                )
            }

            composable<AppRoute.Register>{
                val registerViewModel = viewModel { RegisterViewModel() }
                RegisterRoute(

                    registerViewModel,
                    onNavigation = {
                        when(it){
                            RegisterEvents.NavigateToLogin -> {
                                navController.navigate(AppRoute.Login)
                            }

                            RegisterEvents.NavigateToHome -> {
                                navController.navigate(AppRoute.Home)
                            }
                        }
                    }
                )
            }

            composable<AppRoute.Home>{

                val postViewModel = viewModel{ PostViewModel() }

                PostRoute(postViewModel,
                    onNavigation = {
                        when(it){
                            PostEvents.NavigateToCreatePost -> {
                                navController.navigate(AppRoute.CreatePost)
                            }
                            is PostEvents.NavigateToPostDetails -> {
                                navController.navigate(AppRoute.PostDetails(it.postId))
                            }
                        }
                    })
            }

            composable<AppRoute.CreatePost>{

                val createPostViewModel = viewModel { CreatePostViewModel() }
                CreatePostRoute(
                    viewModel = createPostViewModel,
                    onNavigation = {
                        when(it){
                            CreatePostEvents.NavigateBack ->{
                                navController.navigateUp()
                            }
                        }
                    }
                )
            }

            composable<AppRoute.PostDetails>{backStackEntry ->
                val args = backStackEntry.toRoute<AppRoute.PostDetails>()

                val viewModel = viewModel { PostDetailViewModel(args.postId) }

                PostDetailRoute(
                    viewModel,
                    onNavigation = {
                        when(it){
                            PostDetailEvents.NavigateToPostList -> {
                                navController.navigateUp()
                            }
                        }
                    }
                )
            }
        }

//
//        PostScreen(
//            viewModel = postViewModel
//        )
    }
}

