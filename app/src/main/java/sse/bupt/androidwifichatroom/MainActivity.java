package sse.bupt.androidwifichatroom;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("TAG", "我kai了");
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this,FriendListActivity.class);

        FriendListService.friendListService = new FriendListService();
        MyNameService.myNameService = new MyNameService();
        ChatService.chatService = new ChatService();
        ChatService.chatService.startListening();

        RefreshEveryParticipatorConcurrentArrayListService refreshEveryParticipatorConcurrentArrayListService = new RefreshEveryParticipatorConcurrentArrayListService();
        refreshEveryParticipatorConcurrentArrayListService.我来了();
        refreshEveryParticipatorConcurrentArrayListService.startListening();

        startActivity(intent);
    }

    public void log(String s) {
    }
}
