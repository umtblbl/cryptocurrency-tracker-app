package com.umit.cryptocurrencytrackerapp.shared.utilities

import android.graphics.Color
import java.util.Random

object Color {

    val randomColor: Int
        get() {
            Random().let {
                return Color.argb(
                    255,
                    it.nextInt(256),
                    it.nextInt(256),
                    it.nextInt(256)
                )
            }
        }
}
