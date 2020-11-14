package com.israis007.pruebagapsi.ui.splashscreen

import android.animation.Animator

class LottieAnimator(
    private val splashViewModel: SplashViewModel
): Animator.AnimatorListener {
    override fun onAnimationStart(animation: Animator?) {
        
    }

    override fun onAnimationEnd(animation: Animator?) {
        splashViewModel.finishAnimation()
    }

    override fun onAnimationCancel(animation: Animator?) {
        
    }

    override fun onAnimationRepeat(animation: Animator?) {
        
    }
}