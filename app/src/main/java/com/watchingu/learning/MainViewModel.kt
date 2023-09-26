package com.watchingu.learning

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class MainViewModel : ViewModel() {

    val emailFlow = MutableStateFlow("")
}