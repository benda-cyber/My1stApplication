package com.example.my1stapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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


public class RegisterPage extends AppCompatActivity {

    private FirebaseAuth mAuth;
    EditText name;
    EditText phone;
    EditText address;
    EditText email;
    EditText password;
    FirebaseDatabase database;
    String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);
        address = findViewById(R.id.address);
        email = findViewById(R.id.email);
        password = findViewById(R.id.registerPassword);
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();


    }
    public void register(View view) {

        Animation rotateanimation = AnimationUtils.loadAnimation(this, R.anim.rotateanimation);
        view.startAnimation(rotateanimation);

        String nameStr = name.getText().toString();
        String phoneStr = phone.getText().toString();
        String addressStr = address.getText().toString();
        String emailStr = email.getText().toString();
        String passwordStr = password.getText().toString();

//        User newUser = new User(nameStr,phoneStr,addressStr,emailStr,passwordStr);

        mAuth.createUserWithEmailAndPassword(emailStr, passwordStr).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Toast.makeText(RegisterPage.this, "Registered successfully.",
                                Toast.LENGTH_SHORT).show();
                        FirebaseUser user = mAuth.getCurrentUser();

                        uid = user.getUid();

                        DatabaseReference myRef = database.getReference("users").child(uid);

                        User u = new User(nameStr,phoneStr,addressStr,emailStr,passwordStr);
                        myRef.setValue(u);

                        Intent intentLogin = new Intent(RegisterPage.this, MainActivity.class);
                        intentLogin.putExtra("keyuid",uid);
                        startActivity(intentLogin);


                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(RegisterPage.this, "Registration failed.",
                                Toast.LENGTH_SHORT).show();
                    }

                }
        });
    }

}
