package com.soundsonic.simplemensa.util

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

fun replaceFragmentNoBackStack(
    fm: FragmentManager,
    containerViewId: Int,
    fragment: Fragment
) {
    fm.beginTransaction().apply {
        replace(containerViewId, fragment)
        commit()
    }
}

fun replaceFragment(
    fm: FragmentManager,
    containerViewId: Int,
    fragment: Fragment
) {
    fm.beginTransaction().apply {
        replace(containerViewId, fragment)
        addToBackStack(null)
        commit()
    }
}
