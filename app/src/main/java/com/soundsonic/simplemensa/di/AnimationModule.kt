package com.soundsonic.simplemensa.di

import androidx.recyclerview.widget.RecyclerView
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator

@Module
@InstallIn(FragmentComponent::class)
object AnimationModule {

    @Provides
    fun provideItemAnimator(): RecyclerView.ItemAnimator = SlideInLeftAnimator().apply {
        addDuration = 300
        removeDuration = 300
        moveDuration = 300
        changeDuration = 300
    }
}
