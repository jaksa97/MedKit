package veljkojaksic.medkit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.SignInMethodQueryResult;

public class SignUpActivity extends AppCompatActivity {

    EditText userFullName, userAddress, userPhoneNumber, userEmail, userPassword;
    TextView login;
    Button register;
    FirebaseAuth fAuth;

    boolean check;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        userFullName = findViewById(R.id.signUpFullName);
        userAddress = findViewById(R.id.signUpAddress);
        userPhoneNumber = findViewById(R.id.signUpPhoneNumber);
        userEmail = findViewById(R.id.signUpEmail);
        userPassword = findViewById(R.id.signUpPassword);
        login = findViewById(R.id.existAccount);
        register = findViewById(R.id.signUpBtn);

        fAuth = FirebaseAuth.getInstance();

        if (fAuth.getCurrentUser()!=null)
        {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = userEmail.getText().toString().trim();
                String password = userPassword.getText().toString().trim();
                String fullName = userFullName.getText().toString().trim();
                String address = userAddress.getText().toString().trim();
                String phoneNemuber = userPhoneNumber.getText().toString().trim();

                if (TextUtils.isEmpty(fullName))
                {
                    userFullName.setError("Name is required!");
                    return;
                } else if (TextUtils.isEmpty(address))
                {
                    userEmail.setError("Address is required!");
                    return;
                } else if (TextUtils.isEmpty(phoneNemuber))
                {
                    userEmail.setError("Phone number is required!");
                    return;
                }
                else if (TextUtils.isEmpty(email))
                {
                    userEmail.setError("Email is required!");
                    return;
                } else if(TextUtils.isEmpty(password))
                {
                    userPassword.setError("Password is required!");
                    return;
                }

                fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            Toast.makeText(SignUpActivity.this, "Register successful!",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        } else {
                            Toast.makeText(SignUpActivity.this, "Error! " + task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });
    }

    /*private boolean checkEmail(View v)
    {
        fAuth.fetchSignInMethodsForEmail(userEmail.getText().toString()).addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
            @Override
            public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
                check = task.getResult().getSignInMethods().contains(userEmail.getText().toString());
            }
        });
        return check;
    }*/
}