package com.soulcare.shahin.soulcare.app;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;

/**
 * Created by shahin on 23-02-17.
 */

public class App_Intro extends AppIntro {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addSlide(AppIntroFragment.newInstance("shahin", "nouthing", R.drawable.alarm,Color.parseColor("#757575")));
        addSlide(AppIntroFragment.newInstance("shahin", "nouthing", R.drawable.maa,Color.parseColor("#757575")));

        setBarColor(Color.parseColor("#30AD49"));
       setSeparatorColor(Color.parseColor("#30AD49"));

        showSkipButton(false);
        setFadeAnimation();
        setVibrate(true);
        setVibrateIntensity(30);
    }


    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        startActivity(new Intent(App_Intro.this,LoginActivityUser.class));

        finish();

    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        finish();
    }

    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {
        super.onSlideChanged(oldFragment, newFragment);


    }

}


