package com.darleyleal.shopease.presentation.screens.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.darleyleal.shopease.data.model.Item
import com.darleyleal.shopease.presentation.components.CustomModalBottomSheet
import com.darleyleal.shopease.presentation.components.CustomTopAppBar
import com.darleyleal.shopease.presentation.components.IconOptionRow
import com.darleyleal.shopease.presentation.components.PurchasedItem
import com.darleyleal.shopease.presentation.components.RemoveDialog
import com.darleyleal.shopease.presentation.components.Title
import com.darleyleal.shopease.ui.theme.Blue
import com.darleyleal.shopease.ui.theme.Coral
import com.darleyleal.shopease.ui.theme.LightGray

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(modifier: Modifier = Modifier, viewModel: MainViewModel) {

    val allItems by viewModel.listItems.collectAsState()
    val purshasedItems by viewModel.purshasedItemsList.collectAsState()

    var showModalBottomSheet by rememberSaveable { mutableStateOf(false) }
    var openAlertDialog by remember { mutableStateOf(false) }

    var itemToBeEdited by rememberSaveable { mutableStateOf<Item?>(null) }
    var showEditedItemModalBottomSheet by rememberSaveable { mutableStateOf(false) }

    var onConfirmDeletedItem by remember { mutableStateOf(false) }
    var itemToBeRemoved by rememberSaveable { mutableStateOf<Item?>(null) }

    var itemStatus by rememberSaveable { mutableStateOf<Boolean>(false) }

    Scaffold(
        containerColor = Blue,
        contentColor = Blue,
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(Blue),
                title = {
                    CustomTopAppBar()
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                containerColor = Coral,
                onClick = {
                    showModalBottomSheet = true
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null,
                    tint = LightGray
                )
            }
        },
        content = { it ->
            Box(modifier = Modifier.padding(it)) {
                LazyColumn(modifier = Modifier.padding(top = 32.dp)) {
                    item {
                        Title(text = "Lista de Compras")
                    }

                    items(allItems) { item ->
                        IconOptionRow(
                            item = item,
                            onRemoveItem = { removedItem ->
                                itemToBeRemoved = removedItem
                                openAlertDialog = true
                            },
                            onEditItem = { editedItem ->
                                itemToBeEdited = editedItem
                                showEditedItemModalBottomSheet = true
                            },
                            itemStatus = { status ->
                                itemStatus = status

                                if (status) {
                                    viewModel.update(
                                        Item(
                                            id = item.id,
                                            name = item.name,
                                            dateTime = item.dateTime,
                                            isChecked = status
                                        )
                                    )
                                }
                            }
                        )
                    }

                    item {
                        Spacer(modifier = modifier.padding(top = 22.dp))
                        Title(text = "Itens Comprados")
                    }

                    items(purshasedItems) { item ->
                        PurchasedItem(item = item, onRemoveItem = {
                            itemToBeRemoved = it
                            openAlertDialog = true
                        })
                    }
                }
            }

            when {
                showModalBottomSheet -> {
                    CustomModalBottomSheet(
                        viewModel = viewModel,
                        showBottomSheet = { state ->
                            showModalBottomSheet = state
                        }
                    )
                }

                showEditedItemModalBottomSheet -> {
                    CustomModalBottomSheet(
                        viewModel = viewModel,
                        item = itemToBeEdited,
                        showBottomSheet = { state ->
                            showEditedItemModalBottomSheet = state
                        }
                    )
                }

                openAlertDialog -> {
                    RemoveDialog(
                        onDismissRequest = {
                            openAlertDialog = false
                        },
                        onConfirmation = {
                            onConfirmDeletedItem = true
                            openAlertDialog = false
                        },
                        onDismissButton = {
                            openAlertDialog = false
                        }
                    )
                }

                onConfirmDeletedItem -> {
                    itemToBeRemoved?.let { item ->
                        viewModel.delete(item)
                    }
                }
            }
        }
    )
}