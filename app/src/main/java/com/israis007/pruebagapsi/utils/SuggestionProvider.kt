package com.israis007.pruebagapsi.utils

import android.content.SearchRecentSuggestionsProvider


class SuggestionProvider() : SearchRecentSuggestionsProvider() {

    init {
        setupSuggestions(AUTHORITY, MODE)
    }

    companion object {
        const val AUTHORITY = "com.israis007.pruebagapsi.utils.SuggestionProvider"
        const val MODE = DATABASE_MODE_QUERIES
    }
}