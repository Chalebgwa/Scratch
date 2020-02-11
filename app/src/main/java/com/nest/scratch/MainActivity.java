package com.nest.scratch;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.facebook.AccessToken;
import com.facebook.login.LoginManager;

public class MainActivity extends AppCompatActivity  implements BottomNavigationView.OnNavigationItemSelectedListener, Messages.MessageHeaderListener{


    /*
     *the user
     */
    private User user;
    private Bundle userData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Check if user has logged in
        if(AccessToken.getCurrentAccessToken() == null){

            //Start login activity
            Intent intent = new Intent(this, FacebookLoginActivity.class);
            //startActivity(intent);
        }

        //String name = com.facebook.Profile.getCurrentProfile().getName();
        //Uri profilePictureUri = com.facebook.Profile.getCurrentProfile().getProfilePictureUri(75, 75);
        user = new User("123456789");
        userData = new Bundle();
        userData.putString("username",user.getUsername());
        userData.putString("domain",user.getDomain());
       /// userData.putByteArray("profile_picture",Util.getImageBytes(BitmapFactory.decodeFile(user.getProfile_picture().getPath())));




        //This shit
        //FacebookSdk.sdkInitialize(getApplicationContext());
        //AppEventsLogger.activateApp(this);


        setContentView(R.layout.activity_main);

        ImageButton logoutButton = (ImageButton) findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginManager.getInstance().logOut();
                Intent intent = new Intent(getBaseContext(), FacebookLoginActivity.class);
                startActivity(intent);
            }
        });


        BottomNavigationView bottomNavigationView =(BottomNavigationView) findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        //Display the main screen
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.main_frame,new Main_fragrament()).commit();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        if (item.getItemId()==R.id.sell){
            getSupportFragmentManager().beginTransaction().replace(R.id.main_frame,new Gallery()).commit();
        }
        if(item.getItemId()==R.id.profile){
            Profile profile = new Profile();
            profile.setArguments(userData);
            getSupportFragmentManager().beginTransaction().replace(R.id.main_frame,profile).commit();
        }
        if (item.getItemId()==R.id.inbox){
            getSupportFragmentManager().beginTransaction().replace(R.id.main_frame,new Messages()).commit();
        }
        if (item.getItemId()==R.id.home){
            getSupportFragmentManager().beginTransaction().replace(R.id.main_frame,new Main_fragrament()).commit();
        }
        if (item.getItemId()==R.id.notification){
            getSupportFragmentManager().beginTransaction().replace(R.id.main_frame,new Notifications()).commit();
        }

        return true;
    }


    @Override
    public void openHeader(Bundle args) {
        messageThread fragment = new messageThread();
        fragment.setArguments(args);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame,fragment).commit();
    }
}
