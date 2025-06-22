package com.darleyleal.shopease.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.darleyleal.shopease.data.model.Item
import com.darleyleal.shopease.ui.theme.LightGray
import com.darleyleal.shopease.ui.theme.Typography

@Composable
fun PurchasedItem(
    modifier: Modifier = Modifier,
    item: Item,
    onRemoveItem: (item: Item) ->  Unit,
) {
    Column(modifier = modifier.padding(top = 22.dp, start = 16.dp, end = 16.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Checkbox(
                    colors = CheckboxDefaults.colors(uncheckedColor = Color.White),
                    checked = true,
                    onCheckedChange = {}
                )

                Spacer(modifier = Modifier.padding(start = 8.dp))

                item.name?.let {
                    Text(
                        text = it,
                        color = LightGray,
                        fontSize = 20.sp,
                        textDecoration = TextDecoration.LineThrough,
                        style = Typography.titleLarge
                    )
                }
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(
                    onClick = {
                        onRemoveItem(item)
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = null,
                        tint = LightGray,
                        modifier = Modifier.size(26.dp)
                    )
                }
            }
        }
        item.dateTime?.let {
            Date(
                modifier = Modifier.padding(start = 12.dp),
                text = it
            )
        }
    }
}