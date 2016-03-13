package com.mirego.rebelchat.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.mirego.rebelchat.R;
import com.mirego.rebelchat.controllers.MessageController;
import com.mirego.rebelchat.controllers.MessageControllerImpl;

import butterknife.Bind;
import okhttp3.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MailboxActivity extends BaseActivity {

    @Bind(R.id.root)
    View root;
    private String currentUserId;
    private MessageController messageController;
    private static String EXTRA_USER_ID = "extra_user_id";

    public static Intent newIntent(Activity fromActivity) {
        Intent intent = new Intent(fromActivity, MailboxActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        messageController = new MessageControllerImpl();
        currentUserId = getIntent().getStringExtra(EXTRA_USER_ID);
        fetchMessages();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        // Add support for custom fonts
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    private void fetchMessages() {
        messageController.getMessages(this.getApplicationContext(), EXTRA_USER_ID,
                new MessageController.GetMessagesCallback() {
                    @Override
                    public void onSendMessageSuccess(Response response) {
                        Log.d("messages", response.toString());
                    }

                    @Override
                    public void onSendMessageFail() {

                    }
                });
    }
}
