package org.example.firebasecmp.users.network

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.FirebaseUser
import dev.gitlive.firebase.auth.auth

object Authentication {
    val auth = Firebase.auth

    suspend fun register(email: String, password: String): FirebaseUser {
        val createdUser : FirebaseUser = auth.createUserWithEmailAndPassword(email = email,password = password).user ?:  throw Exception("Error creating user")

        return createdUser
    }

    suspend fun login(email: String,password: String): FirebaseUser {

            val loggedInUser: FirebaseUser? = auth.signInWithEmailAndPassword(email = email, password =  password).user

            if(loggedInUser != null){
                return loggedInUser
            }
            else{
                throw Exception("Login Failed")
            }


    }

    fun isUserLoggedIn(): Boolean{
       return auth.currentUser != null
    }

    fun getCurrentUser(): FirebaseUser?{
        return auth.currentUser
    }
}