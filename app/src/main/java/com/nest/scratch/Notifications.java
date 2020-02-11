package com.nest.scratch;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class Notifications extends android.support.v4.app.Fragment {

    CircleImageView profilepic;
    ArrayList<Notification> notifications;
    Bundle savedInstances;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_notifications,container,false);
        this.savedInstances=savedInstanceState;

        notifications = getNotification();
        ListView listView =(ListView) root.findViewById(R.id.not_listView);
        NotifAdapter notifAdapter = new NotifAdapter();

        listView.setAdapter(notifAdapter);


        return root;
    }


    public ArrayList getNotification(){
        ArrayList<Notification> notifications = new ArrayList<>();

        //FETCH NOTS FROM DATABASE
        int ctr=0;
        while(ctr<20){

            notifications.add(new Notification("Hacked"));
            ctr++;
        }


        return notifications;
    }


    class Notification {

        private String notificationText;
        private String time;

        public Notification(String notificationText){
            setNotificationText(notificationText);
        }


        public String getNotificationText() {
            return notificationText;
        }

        public void setNotificationText(String notificationText) {
            this.notificationText = notificationText;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }


    class NotifAdapter extends BaseAdapter{

        @Override
        public int getCount() {
          return notifications.size();
        }

        @Override
        public Notification getItem(int position) {
            return notifications.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater(savedInstances).inflate(R.layout.notbox,parent,false);

            TextView notificationView =(TextView) convertView.findViewById(R.id.not_text);
            TextView notificationTimeView =(TextView) convertView.findViewById(R.id.not_time);
            notificationView.setText(getItem(position).getNotificationText());
            notificationTimeView.setText(getItem(position).getTime());

            return convertView;
        }
    }
}