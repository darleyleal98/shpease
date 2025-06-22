package com.darleyleal.shopease.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.darleyleal.shopease.ui.theme.Coral
import com.darleyleal.shopease.ui.theme.Typography

@Composable
fun Title(modifier: Modifier = Modifier, text: String) {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = text,
        color = Coral,
        fontWeight = FontWeight.W700,
        textAlign = TextAlign.Center,
        style = Typography.headlineLarge
    )
}