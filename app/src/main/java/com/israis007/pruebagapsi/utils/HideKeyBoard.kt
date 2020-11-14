package com.israis007.pruebagapsi.utils

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager

class HideKeyBoard {

    companion object {
        fun hide(activity: Activity){
            val imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            var v = activity.currentFocus
            if (v == null)
                v = View(activity)
            imm.hideSoftInputFromWindow(v.windowToken, 0)
        }
    }
}