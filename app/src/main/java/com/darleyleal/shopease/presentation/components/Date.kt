package com.darleyleal.shopease.presentation.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.darleyleal.shopease.ui.theme.LightGray
import com.darleyleal.shopease.ui.theme.Typography

@Composable
fun Date(modifier: Modifier = Modifier, text: String) {
    Text(
        modifier = modifier.fillMaxWidth(),
        text = text,
        style = Typography.bodySmall,
        color = LightGray,
    )
    Spacer(modifier = modifier.padding(top = 8.dp))
    HorizontalDivider(
        modifier = Modifier.padding(horizontal = 12.dp)
    )
}