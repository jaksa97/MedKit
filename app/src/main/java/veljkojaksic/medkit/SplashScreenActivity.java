package veljkojaksic.medkit;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class SplashScreenActivity extends AppCompatActivity {

    ImageView splashTop, splashHeart, splashHeartBeat, splashBottom;
    TextView splashAppName;
    CharSequence appName;
    int index;
    long delay = 200;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        splashTop = findViewById(R.id.top_splash_picture);
        splashHeart = findViewById(R.id.splash_heart_picture);
        splashHeartBeat = findViewById(R.id.splash_heart_beat);
        splashBottom = findViewById(R.id.bottom_splash_picture);
        splashAppName = findViewById(R.id.splash_app_name);

        //Set full screen, remove top android navigation bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Top annimation
        Animation topAnimation = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        splashTop.startAnimation(topAnimation);

        //Heart animation
        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(splashHeart, PropertyValuesHolder.ofFloat("scaleX",1.2f), PropertyValuesHolder.ofFloat("scaleY",1.2f));
        objectAnimator.setDuration(500);
        objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
        objectAnimator.setRepeatMode(ValueAnimator.REVERSE);
        objectAnimator.start();

        //App name animation
        animateSplashAppName("MedKit");

        //Heart beat gif
        Glide.with(this).load(R.drawable.heart_beat).asGif().diskCacheStrategy(DiskCacheStrategy.ALL).into(splashHeartBeat);

        //Bottom animation
        Animation bottomAnimation = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);
        splashBottom.startAnimation(bottomAnimation);

        //Initialize handler
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreenActivity.this, SignUpActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                finish();
            }
        }, 4000);

    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            splashAppName.setText(appName.subSequence(0,index++));

            if (index<=appName.length())
            {
                handler.postDelayed(runnable, delay);
            }
        }
    };

    //splash_app_name animation method
    public void animateSplashAppName(CharSequence cs)
    {
        appName = cs;
        index = 0;
        splashAppName.setText("");
        handler.removeCallbacks(runnable);
        handler.postDelayed(runnable,delay);
    }
}