package com.startingandroid.switchactivities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

public class FirstActivity extends AppCompatActivity {

    private EditText user_name, user_email, website_url;
    private Button start_activity, activity_for_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.title_activity_first));
        setSupportActionBar(toolbar);

        user_name = (EditText) findViewById(R.id.user_name);
        user_email = (EditText) findViewById(R.id.user_email);
        website_url = (EditText) findViewById(R.id.website_url);
        start_activity = (Button) findViewById(R.id.start_activity);
        activity_for_result = (Button) findViewById(R.id.activity_for_result);

        start_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateFields()) {
                    Intent i = new Intent(FirstActivity.this, SecondActivity.class);
                    i.putExtra("name", user_name.getText().toString());
                    i.putExtra("url", website_url.getText().toString());
                    i.putExtra("email", user_email.getText().toString());
                    startActivity(i);
                }
            }
        });

        activity_for_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateFields()) {
                    Intent i = new Intent(FirstActivity.this, SecondActivity.class);
                    i.putExtra("name", user_name.getText().toString());
                    i.putExtra("url", website_url.getText().toString());
                    i.putExtra("email", user_email.getText().toString());
                    i.putExtra("flag", true);
                    startActivityForResult(i, 123);
                }
            }
        });
    }

    public boolean validateFields() {
        if (TextUtils.isEmpty(user_name.getText())) {
            user_name.setError("Please enter user name");
            return false;
        }
        if (TextUtils.isEmpty(user_email.getText())) {
            user_email.setError("Please enter email first!");
            return false;
        }
        if (TextUtils.isEmpty(website_url.getText())) {
            website_url.setError("Enter url to continue!");
            return false;
        }

        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 123) {
            user_email.setText(data.getStringExtra("email"));
            user_name.setText(data.getStringExtra("name"));
            website_url.setText(data.getStringExtra("url"));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_first, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
