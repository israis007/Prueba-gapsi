package com.israis007.pruebagapsi.ui.ui.main.splashscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.airbnb.lottie.LottieAnimationView

class SplashViewModel: ViewModel() {

    private val _launchNext = MutableLiveData<Boolean>()
    val launchNext: LiveData<Boolean> get() = _launchNext

    fun startAnimation(lottieAnimationView: LottieAnimationView){
        lottieAnimationView.addAnimatorListener(LottieAnimator(this))
    }

    internal fun finishAnimation(){
        _launchNext.postValue(true)
    }

}