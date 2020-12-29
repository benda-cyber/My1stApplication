package com.example.my1stapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity3 extends AppCompatActivity {

    private FirebaseAuth mAuth;
    EditText emailText;
    EditText passText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        emailText = findViewById(R.id.emailText);
        passText = findViewById(R.id.passwordText);

        mAuth = FirebaseAuth.getInstance();


        EditText email = findViewById(R.id.emailText);
        EditText password = findViewById(R.id.passwordText);

        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);

        if (sharedPreferences.getString("keye", null) != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("keye", email.getText().toString());
            editor.putString("keyp", password.getText().toString());
        }

    }


    public void register(View view) {

        Animation rotateanimation = AnimationUtils.loadAnimation(this, R.anim.rotateanimation);
        view.startAnimation(rotateanimation);

        Intent intent = new Intent(MainActivity3.this, RegisterPage.class);
        // intent.putExtra(KEY,String.valueOf(format(lastResult)));
        startActivity(intent);
    }

    public void login(View view) {

        EditText email = findViewById(R.id.emailText);
        EditText password = findViewById(R.id.passwordText);

        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);

        if (sharedPreferences.getString("keye", null) == null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("keye", email.getText().toString());
            editor.putString("keyp", password.getText().toString());

            editor.apply();
        }

        Animation rotateanimation = AnimationUtils.loadAnimation(this, R.anim.rotateanimation);
        view.startAnimation(rotateanimation);

        String email = emailText.getText().toString();
        String password = passText.getText().toString();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(MainActivity3.this, "Login worked.",
                                    Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();

                            Intent intent = new Intent(MainActivity3.this, MainActivity.class);
                            // intent.putExtra(KEY,String.valueOf(format(lastResult)));
                            startActivity(intent);

                        } else {
                            // If sign in fails, display a message to the user.

                            Toast.makeText(MainActivity3.this, "Login failed.",
                                    Toast.LENGTH_SHORT).show();

                        }


                    }
                });
    }


}
