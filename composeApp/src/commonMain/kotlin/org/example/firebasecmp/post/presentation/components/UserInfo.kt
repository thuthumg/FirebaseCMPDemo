package org.example.firebasecmp.post.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import firebasecmpdemo.composeapp.generated.resources.Res
import firebasecmpdemo.composeapp.generated.resources.dummy_profile
import org.example.firebasecmp.core.MARGIN_CARD_MEDIUM_2
import org.example.firebasecmp.core.MARGIN_MEDIUM
import org.example.firebasecmp.core.MARGIN_MEDIUM_2
import org.example.firebasecmp.core.MARGIN_XLARGE
import org.example.firebasecmp.core.MARGIN_XXLARGE
import org.example.firebasecmp.core.POST_DATE_COLOR
import org.example.firebasecmp.core.TEXT_REGULAR
import org.example.firebasecmp.core.TEXT_REGULAR_2X
import org.jetbrains.compose.resources.painterResource

@Composable
fun UserInfo(
    userName: String,
    date: String,
    showContextualMenu: Boolean = false
) {
    Row(modifier = Modifier.padding(start = MARGIN_MEDIUM_2, end = MARGIN_MEDIUM_2, top = MARGIN_CARD_MEDIUM_2,)) {
        Image(
            contentScale = ContentScale.Crop,
            painter = painterResource(Res.drawable.dummy_profile), contentDescription = null,
            modifier = Modifier.size(MARGIN_XXLARGE).clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(MARGIN_MEDIUM))
        Column {
            Text(text = userName, fontSize = TEXT_REGULAR_2X, fontWeight = FontWeight.Bold)
            Text(
                text = date, fontSize = TEXT_REGULAR,
                fontWeight = FontWeight.Bold,
                color = POST_DATE_COLOR
            )
        }
        Spacer(modifier = Modifier.weight(1.0f))

        if(showContextualMenu){
            IconButton(
                onClick = {

                }
            ) {
                Icon(
                    Icons.Default.MoreVert,
                    contentDescription = null,
                    modifier = Modifier.size(MARGIN_XLARGE),
                    tint = POST_DATE_COLOR
                )
            }
        }


    }
}