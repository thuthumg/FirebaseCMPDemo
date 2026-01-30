package org.example.firebasecmp.core.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.DialogProperties
import org.example.firebasecmp.core.MARGIN_LARGE
import org.example.firebasecmp.core.MARGIN_MEDIUM
import org.example.firebasecmp.core.MARGIN_XLARGE

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoadingDialog(){
    BasicAlertDialog(
        onDismissRequest = {

        },
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false
        ),
        modifier = Modifier.background(
            color = Color.White,
            shape = RoundedCornerShape(MARGIN_MEDIUM)
        ).padding(
            horizontal = MARGIN_LARGE, vertical = MARGIN_XLARGE
        )
    ){
        Row (
            verticalAlignment = Alignment.CenterVertically
        ){
            CircularProgressIndicator(
                color = Color.Black
            )
            Spacer(Modifier.width(
                MARGIN_LARGE
            ))
            Text("Loading")
        }
    }
}