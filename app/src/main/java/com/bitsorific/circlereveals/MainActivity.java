package com.bitsorific.circlereveals;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;

import io.codetail.animation.SupportAnimator;
import io.codetail.animation.ViewAnimationUtils;


public class MainActivity extends ActionBarActivity {

    private ImageView image;
    private ImageView overlay;
    private ImageView secondOverlay;


    private SupportAnimator circleRevealOrange;
    private SupportAnimator circleRevealGreen;
    private SupportAnimator circleRevealYellow;

    private Button revealBtn;
    private Handler handler = new Handler();

    private float startRadius = 0f;
    private float finalRadius = 0f;
    private int cx;
    private int cy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image = (ImageView) findViewById(R.id.main_image);
        overlay = (ImageView) findViewById(R.id.overlay);
        secondOverlay = (ImageView) findViewById(R.id.overlay_two);
        revealBtn = (Button) findViewById(R.id.revealButton);

        image.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                cx = (int)event.getX();
                cy = (int)event.getY();
                finalRadius = Math.max(image.getWidth(), image.getHeight());
                handler.post(revealAnimationRunnable);
                return false;
            }
        });

        revealBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startRadius = 0f;
                finalRadius = Math.max(image.getWidth(), image.getHeight());
                cx = (image.getLeft() + image.getRight()) / 2;
                cy = (image.getTop() + image.getBottom()) / 2;
                handler.post(revealAnimationRunnable);
            }
        });

    }

    private final Runnable revealAnimationRunnable = new Runnable() {
        @Override
        public void run() {

            //Primary
            circleRevealOrange = ViewAnimationUtils.createCircularReveal(image, cx, cy, startRadius, finalRadius);
            circleRevealOrange.setInterpolator(new AccelerateInterpolator());
            circleRevealOrange.setDuration(1000);
            circleRevealOrange.start();

            //First ring
            circleRevealGreen = ViewAnimationUtils.createCircularReveal(overlay, cx, cy, startRadius, finalRadius);
            circleRevealGreen.setInterpolator(new AccelerateInterpolator());
            circleRevealGreen.setDuration(500);
            circleRevealGreen.start();

            //Second ring
            circleRevealYellow = ViewAnimationUtils.createCircularReveal(secondOverlay, cx, cy, startRadius, finalRadius);
            circleRevealYellow.setInterpolator(new AccelerateInterpolator());
            circleRevealYellow.setDuration(800);
            circleRevealYellow.start();
        }
    };

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
