package com.soundsonic.simplemensa.util

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import com.soundsonic.simplemensa.R

fun replaceFragmentNoBackStack(
    fm: FragmentManager,
    containerViewId: Int,
    fragment: Fragment,
    tag: String
) {
    fm.commit {
        setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out)
        replace(containerViewId, fragment, tag)
    }
}

fun replaceFragment(
    fm: FragmentManager,
    containerViewId: Int,
    fragment: Fragment,
    tag: String? = null
) {
    fm.commit {
        setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out)
        replace(containerViewId, fragment, tag)
        addToBackStack(null)
    }
}
