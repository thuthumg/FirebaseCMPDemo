package org.example.firebasecmp.post.screens

import androidx.compose.foundation.Image
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import firebasecmpdemo.composeapp.generated.resources.Res
import firebasecmpdemo.composeapp.generated.resources.chat_ic
import firebasecmpdemo.composeapp.generated.resources.dummy_profile
import firebasecmpdemo.composeapp.generated.resources.post_placeholder
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
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostScreen() {
    Scaffold(
        containerColor = Color.White,
        topBar = {
            TopAppBar(
                title = {
                    Text("Hello John....",
                        fontWeight = FontWeight.Bold,
                        fontSize = TEXT_LARGE
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                ),

            )
        }
    ){ paddingValues ->
        LazyColumn(
            modifier = Modifier.padding(paddingValues),
            contentPadding = PaddingValues(vertical = MARGIN_MEDIUM),
            verticalArrangement = Arrangement.spacedBy(MARGIN_MEDIUM)) {
            itemsIndexed((1..20).toList()){ index, _ ->
                PostItem()
                if(index<19){
                    HorizontalDivider(modifier = Modifier.padding(
                        vertical = MARGIN_CARD_MEDIUM_2
                    ))
                }
            }
        }
    }
}

@Composable
fun PostItem() {
    Column {
        PostUserInfo()
        Text("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla et tincidunt metus, ac tincidunt nisl. Sed vel felis eget erat accumsan feugiat a ut lorem. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Aliquam eget elit elit. Aenean fringilla viverra felis, sit amet ultricies nibh. In lorem elit, suscipit et eros nec, convallis posuere tellus. In blandit risus diam, a suscipit elit lacinia sed. Curabitur vel leo nulla. Aliquam purus tellus, imperdiet ut pellentesque quis, ultrices vitae arcu. Phasellus dictum tempor nulla ut elementum. Praesent tempus velit ligula, sit amet fermentum risus iaculis vitae. Nam in tincidunt orci, ut luctus mi.",
            modifier = Modifier.padding(start = MARGIN_MEDIUM_2, end = MARGIN_MEDIUM_2, top = MARGIN_CARD_MEDIUM_2,),
            maxLines = 4,
            overflow = TextOverflow.Ellipsis)

        Image(
            contentScale = ContentScale.Crop,
            painter = painterResource(Res.drawable.post_placeholder),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth().height(265.dp)
                .padding(top = MARGIN_CARD_MEDIUM_2)
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

@Composable
private fun PostUserInfo() {
    Row(modifier = Modifier.padding(start = MARGIN_MEDIUM_2, end = MARGIN_MEDIUM_2, top = MARGIN_CARD_MEDIUM_2,)) {
        Image(
            contentScale = ContentScale.Crop,
            painter = painterResource(Res.drawable.dummy_profile), contentDescription = null,
            modifier = Modifier.size(MARGIN_XXLARGE).clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(MARGIN_MEDIUM))
        Column {
            Text(text = "Jack", fontSize = TEXT_REGULAR_2X, fontWeight = FontWeight.Bold)
            Text(
                text = "26 December 2025", fontSize = TEXT_REGULAR,
                fontWeight = FontWeight.Bold,
                color = POST_DATE_COLOR
            )
        }
        Spacer(modifier = Modifier.weight(1.0f))
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