package com.jordan.jetpacknavigation.domain

import com.jordan.jetpacknavigation.R
import com.jordan.jetpacknavigation.domain.model.ListItem

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
}