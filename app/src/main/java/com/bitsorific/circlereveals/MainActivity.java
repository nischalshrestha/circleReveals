package com.bitsorific.circlereveals;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;

import io.codetail.animation.SupportAnimator;
import io.codetail.animation.ViewAnimationUtils;


public class MainActivity extends ActionBarActivity {

    private ImageView image;
    private SupportAnimator circleReveal;
    private Button revealBtn;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image = (ImageView) findViewById(R.id.main_image);
        revealBtn = (Button) findViewById(R.id.revealButton);

        revealBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.post(revealAnimationRunnable);
            }
        });

    }

    private final Runnable revealAnimationRunnable = new Runnable() {
        @Override
        public void run() {

            ImageView view = image;
            float startRadius = 60f;
            float finalRadius = Math.max(image.getWidth(), image.getHeight());

            int cx = (image.getLeft() + image.getRight()) / 2;
            int cy = (image.getTop() + image.getBottom()) / 2;

//            Log.d("x", " " + cx);
//            Log.d("x", " " + cy);
//            Log.d("x", " " + finalRadius);


//            if (effect.equals("Reverse Circle")) {
//                startRadius = finalRadius - 100f;
//                finalRadius = 0f;
//            } else if (effect.equals("Masked Reverse Circle")) {
//                ifrag.blackLayer.setVisibility(View.VISIBLE);
//                view = ifrag.blackLayer;
//                startRadius = finalRadius - 300f;
//                finalRadius = 0f;
//            }

            circleReveal = ViewAnimationUtils.createCircularReveal(view, cx, cy, startRadius, finalRadius);
            circleReveal.setInterpolator(new AccelerateInterpolator());
            circleReveal.setDuration(10000);
//
//            if (effect.equals("Reverse Circle") || effect.equals("Masked Reverse Circle")){
//                circleReveal.setInterpolator(new DecelerateInterpolator());
//                circleReveal.setDuration(15000);
//            }
//
//            // make the black view invisible when the animation is for black reveal
//            circleReveal.addListener(new SupportAnimator.AnimatorListener() {
//                @Override
//                public void onAnimationStart() {}
//                @Override
//                public void onAnimationEnd() {
//                    if(effect.contains("Masked")) {
//                        ifrag.blackLayer.setVisibility(View.INVISIBLE);
//                    }
//                }
//                @Override
//                public void onAnimationCancel() {}
//                @Override
//                public void onAnimationRepeat() {}
//            });
            circleReveal.start();
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
