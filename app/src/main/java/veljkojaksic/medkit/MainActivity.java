package veljkojaksic.medkit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageSlider imageSlider = findViewById(R.id.imageSlider);
        logout = findViewById(R.id.LogoutBtn);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                finish();
            }
        });

        List<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel("https://cdn.graciousquotes.com/wp-content/uploads/2020/05/Take-care-of-your-body.-Its-the-only-place-you-have-to-live..jpg",""));
        slideModels.add(new SlideModel("https://cdn.graciousquotes.com/wp-content/uploads/2020/05/A-healthy-outside-starts-from-the-inside..jpg",""));
        slideModels.add(new SlideModel("https://cdn.graciousquotes.com/wp-content/uploads/2020/03/Health-is-like-money-we-never-have-a-true-idea-of-its-value-until-we-lose-it.jpg",""));
        slideModels.add(new SlideModel("https://cdn.graciousquotes.com/wp-content/uploads/2020/03/If-you-truly-treat-your-body-like-a-temple-it-will-serve-you-well-for-decades.-If-you-abuse-it-you-must-be-prepared-for-poor-health-and-a-lack-of-energy.jpg",""));
        slideModels.add(new SlideModel("https://cdn.graciousquotes.com/wp-content/uploads/2020/03/Healthy-citizens-are-the-greatest-asset-any-country-can-have.jpg",""));
        slideModels.add(new SlideModel("https://cdn.graciousquotes.com/wp-content/uploads/2020/03/Your-body-will-be-around-a-lot-longer-than-that-expensive-handbag.-Invest-in-yourself.jpg",""));
        slideModels.add(new SlideModel("https://cdn.graciousquotes.com/wp-content/uploads/2020/03/Good-health-is-not-something-we-can-buy.-However-it-can-be-an-extremely-valuable-savings-account.jpg",""));
        imageSlider.setImageList(slideModels, true);

    }
}