package veljkojaksic.medkit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageSlider imageSlider = findViewById(R.id.imageSlider);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bnv);

        List<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel("https://cdn.graciousquotes.com/wp-content/uploads/2020/05/Take-care-of-your-body.-Its-the-only-place-you-have-to-live..jpg",""));
        slideModels.add(new SlideModel("https://cdn.graciousquotes.com/wp-content/uploads/2020/05/A-healthy-outside-starts-from-the-inside..jpg",""));
        slideModels.add(new SlideModel("https://cdn.graciousquotes.com/wp-content/uploads/2020/03/Health-is-like-money-we-never-have-a-true-idea-of-its-value-until-we-lose-it.jpg",""));
        slideModels.add(new SlideModel("https://cdn.graciousquotes.com/wp-content/uploads/2020/03/If-you-truly-treat-your-body-like-a-temple-it-will-serve-you-well-for-decades.-If-you-abuse-it-you-must-be-prepared-for-poor-health-and-a-lack-of-energy.jpg",""));
        slideModels.add(new SlideModel("https://cdn.graciousquotes.com/wp-content/uploads/2020/03/Healthy-citizens-are-the-greatest-asset-any-country-can-have.jpg",""));
        slideModels.add(new SlideModel("https://cdn.graciousquotes.com/wp-content/uploads/2020/03/Good-health-is-not-something-we-can-buy.-However-it-can-be-an-extremely-valuable-savings-account.jpg",""));
        slideModels.add(new SlideModel("https://cdn.graciousquotes.com/wp-content/uploads/2020/03/Your-body-will-be-around-a-lot-longer-than-that-expensive-handbag.-Invest-in-yourself.jpg",""));
        imageSlider.setImageList(slideModels, true);

        //Remove shadow from Bottom Navigation Bar
        bottomNavigationView.setBackground(null);
        //Disable click on placeholder
        bottomNavigationView.getMenu().getItem(2).setEnabled(false);
        //Set OnClickListener to Logout on Bottom navigation bar
        bottomNavigationView.getMenu().getItem(4).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                finish();
                return true;
            }
        });
        bottomNavigationView.getMenu().getItem(3).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                //FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
                finish();
                return true;
            }
        });

        //set checked menu item at the beggining
        //bottomNavigationView.getMenu().getItem(1).setChecked(true);

    }
}