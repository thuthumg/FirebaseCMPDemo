package org.example.firebasecmp.core.network

import androidx.compose.ui.text.intl.Locale
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.firestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.example.firebasecmp.core.formatCurrentDate
import org.example.firebasecmp.post.data.vos.PostVO
import org.example.firebasecmp.users.data.vos.UserVO
import kotlin.time.Clock

object FireStoreDataSource : DataSource {
    val db = Firebase.firestore

    override fun getPostsRealtime(): Flow<List<PostVO>>{
        return db.collection("post")
            .snapshots
            .map { querySnapshot ->
                querySnapshot.documents.map { document ->
                    document.data(PostVO.serializer())
                }
            }
    }

    override suspend fun getPostsOneShot() : List<PostVO>{
       val querySnapshot =  db.collection("post")
            .get()
       return querySnapshot.documents.map { document ->  document.data(PostVO.serializer())
        }
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

        db.collection("users")
            .document(uid)
            .set(strategy = UserVO.serializer(),
                data = userToAdd)

    }

    override suspend fun getUser(uid: String): UserVO? {
       val documentSnapshot =  db.collection("users")
            .document(uid)
            .get()

        return if(documentSnapshot.exists){
            documentSnapshot.data(UserVO.serializer())
        }else{
            null
        }

    }

    override suspend fun createNewPost(userName: String, content: String) {

        //Current time milliseconds
        val currentMoment = Clock.System.now()
        val millis = currentMoment.toEpochMilliseconds()

        //Date Formatting
        val currentDateFormatted = formatCurrentDate()

        val post = PostVO(
            id = millis,
            content = content,
            postUserName = userName,
            postDate = currentDateFormatted
        )

        db.collection("post")
            .document(millis.toString())
            .set(PostVO.serializer(),post)
    }

    override suspend fun getPost(postId: String): PostVO? {
        val documentSnapshot =  db.collection("post")
            .document(postId)
            .get()

        return if(documentSnapshot.exists){
            documentSnapshot.data(PostVO.serializer())
        }else{
            null
        }
    }
}