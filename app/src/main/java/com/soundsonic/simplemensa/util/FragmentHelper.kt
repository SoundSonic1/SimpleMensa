package com.soundsonic.simplemensa.util

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.soundsonic.simplemensa.R

fun replaceFragmentNoBackStack(
    fm: FragmentManager,
    containerViewId: Int,
    fragment: Fragment
) {
    fm.beginTransaction().apply {
        setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out)
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
        setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out)
        replace(containerViewId, fragment)
        addToBackStack(null)
        commit()
    }
}
