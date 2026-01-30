package org.example.firebasecmp.core.data.repositories

import dev.gitlive.firebase.auth.FirebaseUser
import org.example.firebasecmp.core.network.FireStoreDataSource
import org.example.firebasecmp.users.data.repository.AuthRepository
import org.example.firebasecmp.users.data.repository.AuthRepository.isUserLoggedIn
import org.example.firebasecmp.users.data.vos.UserVO
import org.example.firebasecmp.users.network.Authentication

abstract class BaseRepository {
    val authentication = Authentication
    val dataSource = FireStoreDataSource

    suspend fun getLoggedInUser(): UserVO{
        if(isUserLoggedIn()){
            val firebaseUser: FirebaseUser =  authentication.getCurrentUser()!!
            val loggedInUser = AuthRepository.dataSource.getUser(firebaseUser.uid) ?: throw Exception("Logged in user not found")
            return  loggedInUser

        }else{
            throw Exception(" User must be logged in")
        }
    }
}