package org.example.firebasecmp.core.presentation.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import org.example.firebasecmp.core.MARGIN_MEDIUM
import org.example.firebasecmp.core.MARGIN_XXLARGE
import org.example.firebasecmp.core.TEXT_REGULAR_2X

@Composable
fun FirebasePrimaryButton(
    title: String,
    onClick: () -> Unit,
    modifier: Modifier
) {
    Button(
        onClick = {
           onClick()
        },
        shape = RoundedCornerShape(MARGIN_MEDIUM),
        colors = ButtonDefaults.buttonColors().copy(
            containerColor = Color.Black
        ),
        modifier = modifier
            .height(
                MARGIN_XXLARGE
            )
    ){
        Text(title,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = TEXT_REGULAR_2X)
    }
}