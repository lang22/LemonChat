package sse.bupt.androidwifichatroom;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import sse.bupt.androidwifichatroom.FriendAdapter.OnItemClickListener;

/**
 * Created by lang22 on 2019/7/10.
 */
public class FriendListActivity extends AppCompatActivity {

    private RecyclerView fRecycleView;
    private LinearLayout fLinearLayout;
    private ImageView fImageView;
    private TextView fTextView;
    private List<Friend> friendList = new ArrayList<Friend>();
    private FriendAdapter friendAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friend_list);

        initFriendList();

        fRecycleView = (RecyclerView)findViewById(R.id.friend_list_window);
        LinearLayoutManager mlayoutManager = new LinearLayoutManager(this);
//        mlayoutManager.setStackFromEnd(true);
        fRecycleView.setLayoutManager(mlayoutManager);
        friendAdapter= new FriendAdapter(friendList);
        friendAdapter.setmOnItemClickListener(new OnItemClickListener(){

            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(FriendListActivity.this,ChatActivity.class);
                intent.putExtra("Info",friendList.get(position));//将头像和名称传递给chatactivity
                Log.d("初始",friendList.get(position).toString());
                startActivityForResult(intent,1);
            }
        });
        fRecycleView.setAdapter(friendAdapter);
    }

    @Override
    public void onActivityResult(int requestCode,int resultCode,Intent intent){
        Log.d("到这了1",requestCode+"");
        switch (requestCode){
            case 1:
                Log.d("到这了2",resultCode+"");
                if(resultCode == RESULT_OK){
                    Log.d("到这了3",resultCode+"");
                    Intent intent1 = this.getIntent();
//                    Friend f = (Friend) intent1.getSerializableExtra("records");
//                    ChatRecords.update(f);
//                    Log.d("返回",ChatRecords.getRecords(f.getName()).toString());
                }
                break;
            default:
        }
    }


    private void initFriendList() {
        ImageView tx1 = new ImageView(this);
        tx1.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.circle_arrow_left));
        Friend f1 = new Friend("邢凯");
        Log.d("骚凯", "initFriendList: "+friendList.toString());
        friendList.add(f1);
        ChatRecords.update(f1);

        ImageView tx2 = new ImageView(this);
        tx2.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.circle_arrow_right));
        Friend f2 = new Friend("程元");
        friendList.add(f2);
        ChatRecords.update(f1);

    }
}
