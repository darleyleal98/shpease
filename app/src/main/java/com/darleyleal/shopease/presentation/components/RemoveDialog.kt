package com.darleyleal.shopease.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun RemoveDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    onDismissButton: () -> Unit
) {
    AlertDialog(
        icon = {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = null
            )
        },
        title = {
            Text(
                text = "Do you want to remove this item?"
            )
        },
        text = {
            Text(
                text = "By confirming this item will be permanently removed from the device"
            )
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text(text = "Confirm")
            }
        },
        dismissButton = {
           TextButton(
               onClick = {
                   onDismissButton()
               }
           ) {
               Text(text = "Dismiss")
           }
        }
    )
}