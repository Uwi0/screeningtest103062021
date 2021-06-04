package com.kakapo.screeningtest1.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class EventViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(EventViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return EventViewModel() as T
        }
        throw IllegalArgumentException("Unknown View Model")
    }
}