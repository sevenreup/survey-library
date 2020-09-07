package com.skybox.seven.survey.helper

import android.util.Log

class Helpers {
    companion object {
        @JvmStatic
        fun checkBetween(current: Int, total: Int): Boolean {
            Log.e("TAG", "checkBetween: $current $total")
            return (current > 0 && current < (total - 1))
        }
    }
}
