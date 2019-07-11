package sse.bupt.androidwifichatroom;

import android.util.Log;
import android.widget.Toast;

/**
 * Created by Administrator on 7/11/2019.
 */

public class RefreshEveryParticipatorConcurrentArrayListService {
    public void 我来了() {
        ServerSocketHelper serverSocketHelper = new ServerSocketHelper(null, 4399);
        serverSocketHelper.sendInit(MyInfo.getMyName());
        Log.d("TAG", "我来了");
    }

    public void startListening() {
        ClientSocketHelper clientSocketHelper = new ClientSocketHelper(null, 4399){
            @Override
            void processMessage(String s, String ip){
                Log.d("TAG", s);

                MsgP msgP = MsgP.fromString(s);
                switch(msgP.type){
                    case MsgP.MESSAGE_BROADCAST_INIT:
                        final Friend friend = new Friend(msgP.contenT, null);
                        FriendListService.setIPtoFriend(ip, friend);
                        ServerSocketHelper serverSocketHelper = new ServerSocketHelper(null, 4399);
                        serverSocketHelper.sendReply(MyInfo.getMyName(), ip);
                        break;
                    case MsgP.MESSAGE_BROADCAST_REPLY:
                        final Friend friend2 = new Friend(msgP.contenT, null);
                        FriendListService.setIPtoFriend(ip, friend2);
                        break;
                }
            }
        };
        clientSocketHelper.listenMessage();
    }
}
