package com.gaucho.android.tvshowreminder.utils

import android.os.Build
import android.text.Html
import android.view.View
import android.widget.TextView

/**
 * Helper method to setup a textview inside a component, or hide it in case the text is empty or null.
 *
 * @param text the information to set
 */
fun TextView.setupTextView(text: String?) {
    if (text.isNullOrEmpty()) {
        this.visibility = View.GONE
    } else {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            this.text = Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY)
        } else {
            @Suppress("DEPRECATION")
            Html.fromHtml(text)
        }
        this.visibility = View.VISIBLE
    }
}
