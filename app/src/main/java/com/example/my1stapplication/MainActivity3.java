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


        String email = emailText.getText().toString();
        String password = passText.getText().toString();

        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);

        if (sharedPreferences.getString("keye", null) != null) {

            emailText.setText(sharedPreferences.getString("keye",null));
            passText.setText(sharedPreferences.getString("keyp",null));


        }

    }


    public void registerPage(View view) {

        Animation rotateanimation = AnimationUtils.loadAnimation(this, R.anim.rotateanimation);
        view.startAnimation(rotateanimation);

        Intent intentReg = new Intent(this, RegisterPage.class);
        // intent.putExtra(KEY,String.valueOf(format(lastResult)));
        startActivity(intentReg);
    }

    public void login(View view) {

        String email = emailText.getText().toString();
        String password = passText.getText().toString();

        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);

        if (sharedPreferences.getString("keye", null) == null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("keye", email);
            editor.putString("keyp", password);



            editor.apply();
        }

        Animation rotateanimation = AnimationUtils.loadAnimation(this, R.anim.rotateanimation);
        view.startAnimation(rotateanimation);



        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(MainActivity3.this, "Login worked.",
                                    Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();

                            String uid = user.getUid();


                            Intent intentLogin = new Intent(MainActivity3.this, MainActivity.class);
                            intentLogin.putExtra("uidkey",uid);
                            startActivity(intentLogin);

                        } else {
                            // If sign in fails, display a message to the user.

                            Toast.makeText(MainActivity3.this, "Login failed.",
                                    Toast.LENGTH_SHORT).show();

                        }


                    }
                });
    }

    //    myRef.addValueEventListener(new ValueEventListener() {
    //        @Override
    //        public void onDataChange(DataSnapshot dataSnapshot) {
    //            // This method is called once with the initial value and again
    //            // whenever data at this location is updated.
    //            String value = dataSnapshot.getValue(String.class);
    //            Log.d(TAG, "Value is: " + value);
    //        }
    //
    //        @Override
    //        public void onCancelled(DatabaseError error) {
    //            // Failed to read value
    //            Log.w(TAG, "Failed to read value.", error.toException());
    //        }
    //    });

}
