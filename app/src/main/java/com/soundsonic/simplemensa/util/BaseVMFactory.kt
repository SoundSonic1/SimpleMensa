package com.soundsonic.simplemensa.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class BaseVMFactory<T>(val creator: () -> T) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return creator() as T
    }
}
