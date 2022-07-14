package com.jordan.jetpacknavigation.data.source

import com.jordan.jetpacknavigation.R
import com.jordan.jetpacknavigation.domain.model.Intro
import com.jordan.jetpacknavigation.domain.model.ListItem
import com.jordan.jetpacknavigation.domain.model.WordItem

class Datasource {

    fun loadAffirmations(): List<ListItem> {
        return listOf<ListItem>(
            ListItem(R.string.affirmation1, R.drawable.image1),
            ListItem(R.string.affirmation2, R.drawable.image2),
            ListItem(R.string.affirmation3, R.drawable.image3),
            ListItem(R.string.affirmation4, R.drawable.image4),
            ListItem(R.string.affirmation5, R.drawable.image5),
            ListItem(R.string.affirmation6, R.drawable.image6),
            ListItem(R.string.affirmation7, R.drawable.image7),
            ListItem(R.string.affirmation8, R.drawable.image8),
            ListItem(R.string.affirmation9, R.drawable.image9),
            ListItem(R.string.affirmation10, R.drawable.image10)
        )
    }

    fun loadLetters(): List<WordItem> {
        val letterList = mutableListOf<WordItem>()
        for (i in 'a'..'z') {
            letterList.add(
                WordItem(i.toString())
            )
        }
        return letterList
    }

    fun loadIntroList(): List<Intro> {
        return listOf(
            Intro(R.drawable.image1,"Sea", R.string.affirmation1),
            Intro(R.drawable.image2,"Lake", R.string.affirmation2),
            Intro(R.drawable.image3,"Sunshine", R.string.affirmation3)
        )
    }

    fun loadSunshine(): List<Intro> {
        return listOf(
            Intro(R.drawable.image1,"Sea and Sunshine", null),
            Intro(R.drawable.image3,"Sunshine and Mountain", null),
            Intro(R.drawable.image10,"Sunshine", null)
        )
    }

    fun loadMountain(): List<Intro> {
        return listOf(
            Intro(R.drawable.image6,"Mountain 1", null),
            Intro(R.drawable.image7,"Mountain 2", null),
            Intro(R.drawable.image9,"Mountain 3", null)
        )
    }

    fun loadCloud(): List<Intro> {
        return listOf(
            Intro(R.drawable.image2,"Cloud 1", null),
            Intro(R.drawable.image4,"Cloud 2", null),
            Intro(R.drawable.image8,"Cloud 3", null)
        )
    }
}