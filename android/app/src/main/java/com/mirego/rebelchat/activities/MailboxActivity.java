package com.mirego.rebelchat.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.mirego.rebelchat.R;
import com.mirego.rebelchat.models.MailboxAdapter;

import butterknife.Bind;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MailboxActivity extends BaseActivity {

    @Bind(R.id.root)
    View root;

    ListView messagesListView;

    public static Intent newIntent(Activity fromActivity) {
        Intent intent = new Intent(fromActivity, MailboxActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mailbox);
        messagesListView = (ListView) findViewById(R.id.listView);
        messagesListView.setAdapter(new MailboxAdapter(this, new String[]{"data1",
                "data2"}));
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        // Add support for custom fonts
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
