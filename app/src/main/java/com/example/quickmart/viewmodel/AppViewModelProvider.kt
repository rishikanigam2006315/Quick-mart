package com.example.quickmart.viewmodel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.quickmart.QuickMartApplication
import com.example.quickmart.repository.QuickMartRepository

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            HomeViewModel(quickMartApplication().repository)
        }
        initializer {
            CartViewModel(quickMartApplication().repository)
        }
    }
}

fun CreationExtras.quickMartApplication(): QuickMartApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as QuickMartApplication)
