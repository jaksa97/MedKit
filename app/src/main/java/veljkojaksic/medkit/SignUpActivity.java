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

    EditText userName, userEmail, userPassword;
    TextView login;
    Button register;
    FirebaseAuth fAuth;

    boolean check;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        userName = findViewById(R.id.signUpName);
        userEmail = findViewById(R.id.signUpEmail);
        userPassword = findViewById(R.id.signUpPassword);
        login = findViewById(R.id.existAccount);
        register = findViewById(R.id.signUpBtn);

        fAuth = FirebaseAuth.getInstance();

        //fAuth.signOut(); TODO: LogOut on Closing App

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
                String name = userName.getText().toString().trim();

                if (TextUtils.isEmpty(name))
                {
                    userName.setError("Name is required!");
                    return;
                } else if (TextUtils.isEmpty(email))
                {
                    userEmail.setError("Email is required!");
                    return;
                /*} else  if(!TextUtils.isEmpty(email) && checkEmail(userEmail))  TODO: Check if email alredy exist
                {
                    userEmail.setError("Email is already exist!");
                    return;*/
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