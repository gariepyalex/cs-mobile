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
import com.mirego.rebelchat.models.Message;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import okhttp3.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MailboxActivity extends BaseActivity {

    @Bind(R.id.root)
    View root;
    private String currentUserId;
    private MessageController messageController;
    private static String EXTRA_USER_ID = "extra_user_id";
    private ArrayList<Message> messages = new ArrayList<>();

    ListView messagesListView;
    private MailboxAdapter messagesAdapter;

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
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setContentView(R.layout.activity_mailbox);
        messages.add(new Message(0, "LOADGIN", "..."));
        messagesListView = (ListView) findViewById(R.id.listView);
        messagesAdapter = new MailboxAdapter(this, messages);
        messagesListView.setAdapter(messagesAdapter);
        fetchMessages();
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
                    public void onSendMessageSuccess(Response response) throws IOException, JSONException {
                        JSONArray jsonArray = new JSONArray(response.body().string());
                        messages.clear();
                        Log.d("mailbox", jsonArray.toString());
                        for(int i = 0; i < jsonArray.length(); i++) {
                            JSONObject messageJSON = jsonArray.getJSONObject(i);
                            Log.d("mailbox", messageJSON.toString());
                            messages.add(new Message(i, messageJSON.getString("text"), messageJSON.getString("userId")));
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                messagesAdapter.notifyDataSetChanged();
                            }
                        });
                    }

                    @Override
                    public void onSendMessageFail() {

                    }
                });
    }
}
