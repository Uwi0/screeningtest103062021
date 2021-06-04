package com.kakapo.screeningtest1.utils

import com.kakapo.screeningtest1.R
import com.kakapo.screeningtest1.model.EventModel
import com.kakapo.screeningtest1.model.GuestData
import com.kakapo.screeningtest1.model.GuestImageData

object Constant {
    fun eventList() : List<EventModel>{
        val eventData = arrayListOf<EventModel>()
        val event1 = EventModel(
            "ABC event",
            "9 april 2021",
        R.drawable.rage_comic_1
        )
        eventData.add(event1)
        val event2 = EventModel(
            "event ngoding",
            "21 september 2021",
            R.drawable.rage_comic_2
        )
        eventData.add(event2)
        val event3 = EventModel(
            "event game",
            "30 mei 2021",
            R.drawable.rage_comic_3
        )
        eventData.add(event3)
        val event4 = EventModel(
            "event random",
            "4 januari 2021",
            R.drawable.rage_comic_4
        )
        eventData.add(event4)
        return eventData
    }

    fun guestImageProfile() : List<GuestImageData>{
        val guestImageData = arrayListOf<GuestImageData>()
        val guestImage1 = GuestImageData(R.drawable.rage_comic_1)
        guestImageData.add(guestImage1)
        val guestImage2 = GuestImageData(R.drawable.rage_comic_1)
        guestImageData.add(guestImage2)
        val guestImage3 = GuestImageData(R.drawable.rage_comic_1)
        guestImageData.add(guestImage3)
        val guestImage4 = GuestImageData(R.drawable.rage_comic_1)
        guestImageData.add(guestImage4)
        val guestImage5 = GuestImageData(R.drawable.rage_comic_1)
        guestImageData.add(guestImage5)

        return guestImageData
    }
}