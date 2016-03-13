package com.mirego.rebelchat.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.mirego.rebelchat.R;
import com.mirego.rebelchat.controllers.MessageController;
import com.mirego.rebelchat.models.MailboxAdapter;
import com.mirego.rebelchat.controllers.MessageControllerImpl;

import java.io.IOException;

import butterknife.Bind;
import okhttp3.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MailboxActivity extends BaseActivity {

    @Bind(R.id.root)
    View root;
    private String currentUserId;
    private MessageController messageController;
    private static String EXTRA_USER_ID = "extra_user_id";

    ListView messagesListView;

    public static Intent newIntent(Activity fromActivity, String userId) {
        Intent intent = new Intent(fromActivity, MailboxActivity.class);
        intent.putExtra(EXTRA_USER_ID, userId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        messageController = new MessageControllerImpl();
        currentUserId = getIntent().getStringExtra(EXTRA_USER_ID);
        Log.d("mailbox", currentUserId);
        fetchMessages();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

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

    private void fetchMessages() {
        messageController.getMessages(this.getApplicationContext(), currentUserId,
                new MessageController.GetMessagesCallback() {
                    @Override
                    public void onSendMessageSuccess(Response response) throws IOException {
                        Log.d("mailbox", response.body().string());
                    }

                    @Override
                    public void onSendMessageFail() {

                    }
                });
    }
}
