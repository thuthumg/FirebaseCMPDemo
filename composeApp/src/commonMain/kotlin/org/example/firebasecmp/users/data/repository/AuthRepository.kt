package org.example.firebasecmp.users.data.repository

import dev.gitlive.firebase.auth.FirebaseUser
import org.example.firebasecmp.core.data.repositories.BaseRepository
import org.example.firebasecmp.core.network.DataSource
import org.example.firebasecmp.core.network.FireStoreDataSource
import org.example.firebasecmp.users.data.vos.UserVO
import org.example.firebasecmp.users.network.Authentication

object AuthRepository : BaseRepository() {



    suspend fun register(email: String,
                         password : String,
                         userName : String){
       val firebaseUser =  authentication
           .register(email = email,password = password)

        dataSource.createUser(
            firebaseUser.uid,
            email = email,
            userName = userName)

    }

    suspend fun login(email: String, password : String): UserVO{
       val firebaseUser =  authentication.login(email = email, password = password)
        val user = dataSource.getUser(firebaseUser.uid)
        if(user != null){
            return user
        }else{
            throw Exception("User does not exist")
        }
    }

    fun isUserLoggedIn(): Boolean{
        return authentication.isUserLoggedIn()
    }


}