package com.darleyleal.shopease.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.darleyleal.shopease.data.model.Item
import com.darleyleal.shopease.ui.theme.LightGray
import com.darleyleal.shopease.ui.theme.Typography

@Composable
fun IconOptionRow(
    modifier: Modifier = Modifier,
    item: Item,
    itemStatus: (isChecked: Boolean) -> Unit,
    onRemoveItem: (item: Item) -> Unit,
    onEditItem: (item: Item) -> Unit
) {
    Column(modifier = modifier.padding(top = 22.dp, start = 16.dp, end = 16.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            var checked by rememberSaveable { mutableStateOf(false) }
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Checkbox(
                    colors = CheckboxDefaults.colors(uncheckedColor = Color.White),
                    checked = checked,
                    onCheckedChange = {
                        checked = it
                        itemStatus(it)
                    }
                )

                Spacer(modifier = Modifier.padding(start = 8.dp))

                item.name?.let {
                    Text(
                        text = it,
                        color = LightGray,
                        style = Typography.titleLarge
                    )
                }
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                IconButton(
                    onClick = {
                        onEditItem(item)
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = null,
                        tint = LightGray,
                        modifier = Modifier.size(26.dp)
                    )
                }

                Spacer(modifier = Modifier.padding(vertical = 8.dp))

                IconButton(
                    onClick = {
                    onRemoveItem(item)
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
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