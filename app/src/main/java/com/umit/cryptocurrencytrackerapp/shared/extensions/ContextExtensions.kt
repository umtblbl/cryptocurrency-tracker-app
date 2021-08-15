package com.umit.cryptocurrencytrackerapp.shared.extensions

import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

fun Context.showKeyboard(editText: EditText) {
    (getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager)?.toggleSoftInputFromWindow(
        editText.applicationWindowToken,
        InputMethodManager.SHOW_IMPLICIT, 0
    )
}
