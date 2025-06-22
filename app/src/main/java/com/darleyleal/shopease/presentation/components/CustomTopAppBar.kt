package com.darleyleal.shopease.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.darleyleal.shopease.ui.theme.Coral
import com.darleyleal.shopease.ui.theme.LightGray
import com.darleyleal.shopease.ui.theme.Typography

@Composable
fun CustomTopAppBar(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(
            Icons.Default.ShoppingBag,
            contentDescription = null,
            tint = Coral,
            modifier = modifier.size(34.dp)
        )

        Text(
            modifier = modifier.offset(y = 1.dp),
            text = "ShopEase",
            color = LightGray,
            style = Typography.headlineLarge,
            fontSize = 28.sp,
            textAlign = TextAlign.Center,
        )
    }
}