package com.kakapo.screeningtest1.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kakapo.screeningtest1.model.EventModel

class EventViewModel : ViewModel() {
    var list = MutableLiveData<List<EventModel>>()
    var newList = arrayListOf<EventModel>()

    fun add(event: EventModel){
        newList.add(event)
        list.value = newList
    }
}