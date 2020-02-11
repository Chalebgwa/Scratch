package com.nest.scratch;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Coded with love.
 * Created by black on 1/23/17.
 */



public class Messages extends Fragment{
    private Bundle bundle;
    private ArrayList<Thread.Message> messages;
    MessageHeaderListener activityCommander;
    private DBLocal dbLocal;

    public interface  MessageHeaderListener{
        void openHeader(Bundle args);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            activityCommander = (MessageHeaderListener) context;
        }catch (Exception e){

        }
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_messages,container,false);

        bundle = savedInstanceState;
        dbLocal = new DBLocal(this.getContext());


        ListView messageListView =(ListView) root.findViewById(R.id.message_list);
        messageListView.setAdapter(new InboxAdapter());

        messageListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                headerClicked(position);
            }
        });


        final TabHost tabHost =(TabHost) root.findViewById(R.id.messageTabs);
        tabHost.setup();

        TabHost.TabSpec spec = tabHost.newTabSpec("tag1");
        spec.setContent(R.id.tab1);
        spec.setIndicator("Read");
        tabHost.addTab(spec);
        spec = tabHost.newTabSpec("tag2");
        spec.setContent(R.id.tab2);
        spec.setIndicator("Write");
        tabHost.addTab(spec);
        spec = tabHost.newTabSpec("tag2");
        spec.setContent(R.id.tab3);
        spec.setIndicator("Delete");
        tabHost.addTab(spec);



        return root;
    }


    public void headerClicked(int position){
        Bundle args = new Bundle();
        args.putInt("messageID",messages.get(position).getMessageID());
        activityCommander.openHeader(args);
    }



    class InboxAdapter extends BaseAdapter{



        @Override
        public int getCount() {
            return messages.size();
        }



        @Override
        public Thread.Message getItem(int position) {
            return messages.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView =getLayoutInflater(bundle).inflate(R.layout.message_header,parent,false);
            TextView fromUsername =(TextView) convertView.findViewById(R.id.contactName);
            TextView header = (TextView) convertView.findViewById(R.id.messageHeader);

            fromUsername.setText(getItem(position).getFromUsername().toString());
            header.setText(getItem(position).getMessage().toString());

            return convertView;

        }
    }

    class messageTextAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView =getLayoutInflater(bundle).inflate(R.layout.message_header,parent,false);
            TextView fromUsername =(TextView) convertView.findViewById(R.id.contactName);
            TextView message = (TextView) convertView.findViewById(R.id.messageHeader);

            return convertView;

        }
    }

}

class ThreadIntent extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}

class Thread{
    private ArrayList<Message> messages;
    private int userId;
    private DBLocal dbLocal;


    Thread(int userId){
        this.userId = userId;
        messages=dbLocal.getMessages(userId);

    }

    class Message{
        private String fromUsername;
        private String message;
        private int messageID;



        Message(int userID,String message){
            this.fromUsername = "Simone Flavour";//get username from database using id
            this.message = message;

            //this.messageID = getId();
        }

        public int getMessageID() {
            return messageID;
        }

        public String getMessage() {
            return message;
        }

        public String getFromUsername() {
            return fromUsername;
        }


    }






}


