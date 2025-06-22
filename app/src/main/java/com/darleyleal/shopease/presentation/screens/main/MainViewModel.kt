package com.darleyleal.shopease.presentation.screens.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.darleyleal.shopease.data.model.Item
import com.darleyleal.shopease.data.repository.ShopEaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = ShopEaseRepository(application.applicationContext)

    private var _listItems = MutableStateFlow<List<Item>>(emptyList())
    val listItems = _listItems.asStateFlow()

    private var _purchasedItemsList = MutableStateFlow<List<Item>>(emptyList())
    var purshasedItemsList = _purchasedItemsList.asStateFlow()

    init {
        listAllItems()
        listPurshasedItems()
    }

    fun insert(item: Item) {
        viewModelScope.launch {
            repository.insert(item)
        }
    }

    fun update(item: Item) {
        viewModelScope.launch {
            repository.update(item)
        }
    }

    fun delete(item: Item) {
        viewModelScope.launch {
            repository.delete(item)
        }
    }

    private fun listAllItems() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.listItems().distinctUntilChanged()
                .collect { itemList ->
                    _listItems.value = itemList
                }
        }
    }

    private fun listPurshasedItems() {
        viewModelScope.launch {
            repository.listPurshasedItems().distinctUntilChanged().collect { itemList ->
                _purchasedItemsList.value = itemList
            }
        }
    }
}