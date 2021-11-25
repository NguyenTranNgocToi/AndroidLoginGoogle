package com.example.googleidentity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.squareup.picasso.Picasso;

public class MainActivity2 extends AppCompatActivity {
    Button btnSignOut;
    GoogleSignInClient mGoogleSignInClient;
    ImageView img;
    TextView txt1, txt2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        btnSignOut = findViewById(R.id.button);

        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.button:
                        signOut();
                        break;
                }
            }
        });
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        String personEmail = acct.getEmail();
        Toast.makeText(this, "Dang nhap email: "+personEmail, Toast.LENGTH_SHORT).show();

        img = findViewById(R.id.imageView);
        txt1 = findViewById(R.id.textView);
        txt2 = findViewById(R.id.textView2);

        txt1.setText(acct.getEmail());
        txt2.setText(acct.getDisplayName());
       // Picasso.with(this).load(acct.getPhotoUrl()).into(img);
        Glide.with(this).load(acct.getPhotoUrl().toString()).into(img);

    }
    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(MainActivity2.this, "Đăng xuất thành công", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
    }
}