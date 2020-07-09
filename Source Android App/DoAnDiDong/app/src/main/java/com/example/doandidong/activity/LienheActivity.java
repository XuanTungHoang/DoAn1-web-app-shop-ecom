package com.example.doandidong.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.doandidong.R;
import android.os.Bundle;
import android.view.View;

public class LienheActivity extends AppCompatActivity {

    Toolbar toolbar_lienhe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_lienhe );
        toolbar_lienhe=(Toolbar) findViewById( R.id.Toolbar_lienhe );
        ActionBar();
    }

    private void ActionBar() {
        setSupportActionBar( toolbar_lienhe );
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );

        toolbar_lienhe.setNavigationOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        } );

    }
}
