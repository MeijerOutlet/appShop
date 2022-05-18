package com.example.appshop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.example.appshop.db.DatabaseHelper;
import com.example.appShop.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializaciones();


    }
    void inicializaciones(){
        ImageView logo = findViewById(R.id.logoView);
        AnimationSet animationSet = new AnimationSet(this, null);
        animationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Intent i = new Intent(MainActivity.this, LogIn.class);
                startActivity(i);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        AlphaAnimation alphaAnimation = new AlphaAnimation(0,1);
        ScaleAnimation scaleAnimation = new ScaleAnimation(0f, 1f,0f,1f,150f,150f);
        animationSet.addAnimation(alphaAnimation);
        animationSet.addAnimation(scaleAnimation);
        animationSet.setDuration(700);
        logo.setAnimation(animationSet);

        // Inicializar BBDD
        DatabaseHelper db = new DatabaseHelper(this);
        db.getWritableDatabase();
    }
    @Override
    protected void onStop() {
        super.onStop();
        this.finish();
    }
}