package org.example.firebasecmp.post.data.repositories

import kotlinx.coroutines.flow.Flow
import org.example.firebasecmp.core.data.repositories.BaseRepository
import org.example.firebasecmp.post.data.vos.PostVO
import org.example.firebasecmp.core.network.DataSource
import org.example.firebasecmp.core.network.FireStoreDataSource
import org.example.firebasecmp.users.network.Authentication

object PostsRepository : BaseRepository() {

    fun getPostsRealtime(): Flow<List<PostVO>>{
        return dataSource.getPostsRealtime()
    }

    suspend fun getPostsOneShot(): List<PostVO>{
        return dataSource.getPostsOneShot()
    }

    suspend fun createPost(content: String){
        val loggedInUserName = getLoggedInUser().userName
        dataSource.createNewPost(
            loggedInUserName,
            content =  content
        )

    }

    suspend fun getPost(postId: String): PostVO? {
        return dataSource.getPost(postId)
    }

}