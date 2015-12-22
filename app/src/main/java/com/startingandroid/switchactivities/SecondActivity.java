package com.startingandroid.switchactivities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SecondActivity extends AppCompatActivity {

    private TextView user_name_view, user_email_view, website_url_view;
    private EditText user_name, user_email, website_url;
    private Button close_activity, send_result;
    private LinearLayout first_layout, second_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        first_layout = (LinearLayout) findViewById(R.id.first_layout);
        second_layout = (LinearLayout) findViewById(R.id.second_layout);

        user_name = (EditText) findViewById(R.id.user_name);
        user_email = (EditText) findViewById(R.id.user_email);
        website_url = (EditText) findViewById(R.id.website_url);

        user_name_view = (TextView) findViewById(R.id.user_name_view);
        user_email_view = (TextView) findViewById(R.id.user_email_view);
        website_url_view = (TextView) findViewById(R.id.website_url_view);


        Intent i = getIntent();
        if (i.hasExtra("flag") && i.getBooleanExtra("flag", false)) {
            first_layout.setVisibility(View.VISIBLE);
            second_layout.setVisibility(View.GONE);
            user_email.setText(i.getStringExtra("email"));
            user_name.setText(i.getStringExtra("name"));
            website_url.setText(i.getStringExtra("url"));
        } else {
            first_layout.setVisibility(View.GONE);
            second_layout.setVisibility(View.VISIBLE);
            user_email_view.setText("Email: " +i.getStringExtra("email"));
            user_name_view.setText("Name: "+i.getStringExtra("name"));
            website_url_view.setText("URL: "+i.getStringExtra("url"));
        }


        close_activity = (Button) findViewById(R.id.activity_finish);
        send_result = (Button) findViewById(R.id.activity_send_result);

        close_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        send_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateFields()) {
                    Intent i = new Intent();
                    i.putExtra("name", user_name.getText().toString());
                    i.putExtra("url", website_url.getText().toString());
                    i.putExtra("email", user_email.getText().toString());
                    setResult(RESULT_OK, i);
                    finish();
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
}
