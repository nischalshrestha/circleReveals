package com.bitsorific.circlereveals;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends ActionBarActivity {

    private View backdrop;
    private View icon;
    private Button revealBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        backdrop = findViewById(R.id.like_circle);
        icon = findViewById(R.id.like_icon);
        revealBtn = (Button) findViewById(R.id.revealButton);

        revealBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AnimatorSet set = new AnimatorSet();

                ObjectAnimator scaleUpXAnimator =
                        new ObjectAnimator().ofFloat(backdrop, View.SCALE_X, 0f, 1f);
                scaleUpXAnimator.setDuration(100);

                ObjectAnimator scaleUpYAnimator =
                        new ObjectAnimator().ofFloat(backdrop, View.SCALE_Y, 0f, 1f);
                scaleUpYAnimator.setDuration(100);

                ObjectAnimator scaleUpXFinalAnimator =
                        new ObjectAnimator().ofFloat(backdrop, View.SCALE_X, 1f, 4f);
                scaleUpXFinalAnimator.setDuration(200);
                scaleUpXFinalAnimator.setStartDelay(800);

                ObjectAnimator scaleUpYFinalAnimator =
                        new ObjectAnimator().ofFloat(backdrop, View.SCALE_Y, 1f, 4f);
                scaleUpYFinalAnimator.setDuration(200);
                scaleUpYFinalAnimator.setStartDelay(800);

                ObjectAnimator fadeInAnimator =
                        ObjectAnimator.ofFloat(backdrop, View.ALPHA, 0, 1);
                fadeInAnimator.setDuration(300);

                ObjectAnimator fadeOutAnimator =
                        ObjectAnimator.ofFloat(backdrop, View.ALPHA, 1, 0);
                fadeOutAnimator.setDuration(800);
                fadeOutAnimator.setStartDelay(1200);

                ObjectAnimator fadeInIconAnimator =
                        ObjectAnimator.ofFloat(icon, View.ALPHA, 0, 1);
                fadeInIconAnimator.setDuration(300);

                ObjectAnimator fadeOutIconAnimator =
                        ObjectAnimator.ofFloat(backdrop, View.ALPHA, 1, 0);
                fadeOutIconAnimator.setDuration(800);
                fadeOutIconAnimator.setStartDelay(1200);

                set.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        backdrop.setVisibility(View.VISIBLE);
                        icon.setVisibility(View.VISIBLE);
                    }
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        backdrop.setVisibility(View.GONE);
                        icon.setVisibility(View.GONE);
                    }
                });
                set.play(scaleUpXAnimator).with(scaleUpYAnimator).with(fadeInAnimator).with(fadeInIconAnimator)
                        .before(fadeOutAnimator).with(fadeOutIconAnimator)
                        .with(scaleUpXFinalAnimator).with(scaleUpYFinalAnimator);

                set.start();

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
