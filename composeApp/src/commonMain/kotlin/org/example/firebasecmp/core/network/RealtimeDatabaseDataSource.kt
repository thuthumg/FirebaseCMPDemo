package org.example.firebasecmp.core.network

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.database.database
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import org.example.firebasecmp.post.data.vos.PostVO
import org.example.firebasecmp.users.data.vos.UserVO

object RealtimeDatabaseDataSource : DataSource {

    val db = Firebase.database

    override fun getPostsRealtime(): Flow<List<PostVO>> {

        return db.reference("posts")
            .valueEvents
            .map { dataSnapshot ->
                dataSnapshot.value<List<PostVO?>>().filterNotNull()
            }
    }

//    suspend fun getPostRealtimeTest(){
//        db.reference("posts")
//            .valueEvents
//            .map { dataSnapshot ->
//                dataSnapshot.value<List<PostVO?>>().filterNotNull()
//            }
//            .collect{
//                println("Type of data ===> ${ it}")
//            }
//    }

//    suspend fun getPostOneshotTest(){
//       val dataSnapshot =  db.reference("posts")
//            .valueEvents
//            .first()
//
//       val result =  dataSnapshot.value<List<PostVO?>>().filterNotNull()
//        println("Get post one shot realtime db ===> $result")
//    }

    override suspend fun getPostsOneShot(): List<PostVO> {
        val dataSnapshot = db.reference("posts")
            .valueEvents
            .first()

        val result = dataSnapshot.value<List<PostVO?>>().filterNotNull()
        return result
    }

    override suspend fun createUser(
        uid: String,
        email: String,
        userName: String,
    ) {

        val userToAdd = UserVO(
            uid = uid,
            email = email,
            userName = userName
        )

        db.reference("users")
            .child(uid)
            .setValue(
            strategy = UserVO.serializer(),
            value = userToAdd
        )
    }

    override suspend fun getUser(uid: String): UserVO? {
        val dataSnapshot = db.reference("users")
            .child(uid).valueEvents.first()

       return if(dataSnapshot.exists)
       {
           dataSnapshot.value<UserVO>()
       }else{
           null
       }


    }

    override suspend fun createNewPost(userName: String, content: String) {
        TODO("Not yet implemented")
    }

    override suspend fun getPost(postId: String): PostVO? {
        TODO("Not yet implemented")
    }

}