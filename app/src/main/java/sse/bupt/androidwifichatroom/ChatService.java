package sse.bupt.androidwifichatroom;

import android.util.Log;

import java.util.Random;

/**
 * Created by Little Dva on 7/8/2019.
 */

class ChatService {
    public static ChatService chatService;
    public ChatActivity chatActivity;
    public String name;
    private ClientSocketHelper clientSocketHelper;

    ChatService() {
        this.name = MyInfo.getMyName();
    }

    void sendMessage(String msg) {
        MsgP ms = new MsgP();
        ms.contenT = name + ": " + msg;
        ms.type = MsgP.MESSAGE_BROADCAST_MESSAGE;
        Log.d("TAG", "我发了："+ms.toString());

        ServerSocketHelper ssh = new ServerSocketHelper(chatActivity, 4747);
        ssh.broadcastMessage(ms.toString());
    }

    void startListening() {
        clientSocketHelper = new ClientSocketHelper(chatActivity, 4747){
            @Override
            void processMessage(String s, String ip){
                Log.d("TAG", s);
                MsgP msgP = MsgP.fromString(s);
                chatActivity.log(s);

                switch(msgP.type){
                    case MsgP.MESSAGE_BROADCAST_MESSAGE:
                        MsgQ msgQ = new MsgQ(msgP.contenT,MsgQ.TYPE_RECEIVED);
                        Log.d("TAG","我的名字: "+name+"   msg: "+msgP.contenT);
                        // hack: 收到自己广播的消息，不显示
                        if(msgP.contenT.startsWith(name)){
                            break;
                        }

                        String filtered_Name = msgP.contenT;
                        if(!"群聊".equals(name)){
                            filtered_Name = filtered_Name.substring(name.length()+1);
                        }
                        msgQ.setContent(filtered_Name);

                        if(chatActivity != null) {
                            chatActivity.showMsg(msgQ);
                        }
                        break;
                    default:
                        if(chatActivity != null) {
                            chatActivity.log("Unknown message: " + msgP.contenT);
                        }
                }
            }
        };
        clientSocketHelper.listenMessage();
    }

}
