package org.example.firebasecmp.core.network

import kotlinx.coroutines.flow.Flow
import org.example.firebasecmp.post.data.vos.PostVO
import org.example.firebasecmp.users.data.vos.UserVO

interface DataSource {

    fun getPostsRealtime() : Flow<List<PostVO>>
    suspend fun getPostsOneShot() : List<PostVO>

    suspend fun createUser(uid: String,email: String, userName: String)

    suspend fun getUser(uid: String): UserVO?

    suspend fun createNewPost(userName: String,content: String)

    suspend fun getPost(postId: String): PostVO?

}