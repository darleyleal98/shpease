package com.darleyleal.shopease.presentation.components


import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.darleyleal.shopease.R
import com.darleyleal.shopease.data.model.Item
import com.darleyleal.shopease.domain.utils.getCurrentDateTime
import com.darleyleal.shopease.presentation.screens.main.MainViewModel
import com.darleyleal.shopease.ui.theme.Coral
import com.darleyleal.shopease.ui.theme.LightGray

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomModalBottomSheet(
    modifier: Modifier = Modifier,
    item: Item? = null,
    showBottomSheet: (Boolean) -> Unit,
    viewModel: MainViewModel
) {
    val context = LocalContext.current
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = false)
    var name by rememberSaveable { mutableStateOf("") }
    var fieldIsEmpty by rememberSaveable { mutableStateOf(false) }
    val currentDateTime = getCurrentDateTime()

    SideEffect { item?.let { name = it.name.toString() } }

    ModalBottomSheet(
        containerColor = Color.White,
        modifier = modifier.fillMaxSize(),
        onDismissRequest = {
            showBottomSheet(false)
        },
        sheetState = sheetState
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text(
                modifier = modifier.fillMaxWidth(),
                text = stringResource(R.string.adicionar_item),
                fontSize = 22.sp,
                color = Coral,
                fontWeight = FontWeight.W700,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = modifier.padding(top = 22.dp))

            TextField(
                modifier = modifier.fillMaxWidth(),
                value = name,
                isError = fieldIsEmpty,
                supportingText = {
                    when {
                        fieldIsEmpty -> {
                            Text(
                                text = stringResource(R.string.esse_campo_obrigat_rio),
                                color = Color.Red,
                                fontSize = 16.sp
                            )
                        }
                    }
                },
                onValueChange = { newText ->
                    name = newText
                },
                leadingIcon = {
                    Icon(
                        Icons.Default.ShoppingCart,
                        contentDescription = null
                    )
                },
                trailingIcon = {
                    when {
                        name.isNotEmpty() -> {
                            IconButton(
                                onClick = {
                                    name = ""
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Close,
                                    contentDescription = null
                                )
                            }
                        }
                    }
                },
                singleLine = true,
                placeholder = {
                    Text(stringResource(R.string.digite_o_nome))
                }
            )
        }

        Spacer(modifier = modifier.padding(top = 16.dp))

        Button(
            colors = ButtonDefaults.buttonColors(containerColor = Coral),
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .height(48.dp),
            onClick = {
                when {
                    name.isEmpty() -> {
                        fieldIsEmpty = true
                    }

                    else -> {
                        when {
                            item == null -> {
                                viewModel.insert(
                                    Item(
                                        name = name,
                                        dateTime = currentDateTime,
                                        isChecked = false
                                    )
                                )
                                showBottomSheet(false)
                                Toast.makeText(
                                    context,
                                    "$name adicionado com sucesso",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }

                            else -> {
                                item.let {
                                    viewModel.update(
                                        Item(
                                            id = it.id,
                                            name = name,
                                            isChecked = it.isChecked,
                                            dateTime = it.dateTime
                                        )
                                    )
                                    showBottomSheet(false)
                                    Toast.makeText(
                                        context,
                                        "$name atualizado com sucesso",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        }
                    }
                }
            }
        ) {
            Text(
                text = if (item == null) stringResource(R.string.adicionar) else "Atualizar",
                fontSize = 20.sp,
                fontWeight = FontWeight.W800,
                color = LightGray
            )
        }
    }
}