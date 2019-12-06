package com.soundsonic.simplemensa.util

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

fun replaceFragmentNoBackstack(
    fm: FragmentManager,
    containerViewId: Int,
    fragment: Fragment
) {
    val transaction = fm.beginTransaction()
    transaction
        .replace(containerViewId, fragment)
        .commit()
}
