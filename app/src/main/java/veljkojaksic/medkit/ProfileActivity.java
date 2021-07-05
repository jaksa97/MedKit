package veljkojaksic.medkit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class ProfileActivity extends AppCompatActivity {

    ImageView profilePicture;
    Button changeProfilePicture, saveChanges, editProfile;
    EditText userFullName, userEmail, userAddress, userPhoneNumber;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profilePicture = findViewById(R.id.profilePicture);
        changeProfilePicture = findViewById(R.id.changeProfilePicture);
        saveChanges = findViewById(R.id.btnProfileSave);
        editProfile = findViewById(R.id.btnProfileEdit);
        bottomNavigationView = findViewById(R.id.profilebnv);
        userFullName = findViewById(R.id.profileFullName);
        userEmail = findViewById(R.id.profileEmail);
        userAddress = findViewById(R.id.profileAddress);
        userPhoneNumber = findViewById(R.id.profilePhoneNumber);

        changeProfilePicture.setVisibility(View.INVISIBLE);
        saveChanges.setVisibility(View.INVISIBLE);
        userFullName.setEnabled(false);
        userEmail.setEnabled(false);
        userAddress.setEnabled(false);
        userPhoneNumber.setEnabled(false);

        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeProfilePicture.setVisibility(View.VISIBLE);
                saveChanges.setVisibility(View.VISIBLE);
                userFullName.setEnabled(true);
                userEmail.setEnabled(true);
                userAddress.setEnabled(true);
                userPhoneNumber.setEnabled(true);

                Toast.makeText(ProfileActivity.this, "Editing enable!",Toast.LENGTH_SHORT).show();
            }
        });

        saveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeProfilePicture.setVisibility(View.INVISIBLE);
                saveChanges.setVisibility(View.INVISIBLE);
                userFullName.setEnabled(false);
                userEmail.setEnabled(false);
                userAddress.setEnabled(false);
                userPhoneNumber.setEnabled(false);

                Toast.makeText(ProfileActivity.this, "Editing disable!",Toast.LENGTH_SHORT).show();
            }
        });

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
        bottomNavigationView.getMenu().getItem(0).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                //FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
                return true;
            }
        });

        //set checked menu item at the beggining
        bottomNavigationView.getMenu().getItem(3).setChecked(true);
    }
}