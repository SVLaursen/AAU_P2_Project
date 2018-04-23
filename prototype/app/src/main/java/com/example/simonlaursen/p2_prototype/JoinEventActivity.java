package com.example.simonlaursen.p2_prototype;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class JoinEventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_event);

        ImageButton home = findViewById(R.id.HomeButton);
        ImageButton calendar = findViewById(R.id.CalendarButton);
        ImageButton profile = findViewById(R.id.ProfileButton);
        ImageButton comment = findViewById(R.id.CommentButton);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),MainActivity.class);
                intent.getStringExtra("key");
                startActivity(intent);
                finish();
            }
        });

        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), CalendarActivity.class);
                intent.getStringExtra("key");
                startActivity(intent);
                finish();
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),ProfileActivity.class); //Go to profile activity
                intent.getStringExtra("key");
                startActivity(intent);
                finish();
            }
        });

        comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Comment pressed",Toast.LENGTH_SHORT).show();

                AlertDialog.Builder mBuilder = new AlertDialog.Builder(JoinEventActivity.this, R.style.AppTheme_DialogTheme);
                View mView = getLayoutInflater().inflate(R.layout.dialogue_comment_area,null);

                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();
                dialog.show();
                dialog.setCanceledOnTouchOutside(true);
            }
        });

    }
}
