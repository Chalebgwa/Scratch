package com.nest.scratch;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.InputStream;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.app.Activity.RESULT_OK;


public class Profile extends Fragment{

    private Bitmap profile_picture;
    private Bitmap profile_cover;
    private String domain_name;
    private final int REQUEST_CODE=874;
    private final int REQUEST_CODE_PROFILE=231;
    private final int REQUEST_CODE_COVER=833;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root=inflater.inflate(R.layout.fragment_profile, container, false);
        final TabHost tabHost = (TabHost) root.findViewById(R.id.tabhost_profile);
        tabHost.setup();

        TabHost.TabSpec spec = tabHost.newTabSpec("tag1");
        spec.setContent(R.id.Posts);
        spec.setIndicator("Read");
        tabHost.addTab(spec);
        spec = tabHost.newTabSpec("tag2");
        spec.setContent(R.id.Comunities);
        spec.setIndicator("Write");
        tabHost.addTab(spec);
        spec = tabHost.newTabSpec("tag3");
        spec.setContent(R.id.requests);
        spec.setIndicator("edit profile");
        tabHost.addTab(spec);
        setUserDefaults(root);
        //Uri profilePictureUri = com.facebook.Profile.getCurrentProfile().getProfilePictureUri(175, 175);
        //Bitmap bitmap = BitmapFactory.decodeFile(profilePictureUri.getPath());
        //setProfile_cover(bitmap);
        //TextView usernameView = (TextView) root.findViewById(R.id.username);
        //String username =com.facebook.Profile.getCurrentProfile().getName();
        //usernameView.setText(username);

        //CircleImageView profilePictureView = (CircleImageView) root.findViewById(R.id.profile_picture);
        //profilePictureView.setImageURI(profilePictureUri);

        ImageButton editProfilepic = (ImageButton) root.findViewById(R.id.editProPic);
        editProfilepic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImageGallery(REQUEST_CODE_PROFILE);
            }
        });
        ImageButton editCover =(ImageButton) root.findViewById(R.id.editCover);
        editCover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImageGallery(REQUEST_CODE_COVER);
            }
        });


        return root;
    }

    public void setUserDefaults(View root){
        if(profile_cover != null){
            ImageView coverView = (ImageView) root.findViewById(R.id.cover);
            coverView.setImageBitmap(profile_cover);
        }
        if(profile_picture!=null){
            CircleImageView profilePictureView = (CircleImageView) root.findViewById(R.id.profile_picture);
            profilePictureView.setImageBitmap(profile_picture);
        }
        if (domain_name!=null){

        }
    }


    //Open your device's local image gallery and click on the image you need
    public void openImageGallery(int code) {
        Intent photopicker = new Intent(Intent.ACTION_PICK);
        File file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        Uri data = Uri.parse(file.getPath());
        photopicker.setDataAndType(data,"image/*");
        startActivityForResult(photopicker,code);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            Uri img_uri = data.getData();

            InputStream inputStream;
            try {
                inputStream = getActivity().getContentResolver().openInputStream(img_uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                if(requestCode==REQUEST_CODE_PROFILE){
                    setProfile_picture(bitmap);
                }
                if(requestCode==REQUEST_CODE_COVER){
                    setProfile_cover(bitmap);
                }


            } catch (Exception e) {
                Toast.makeText(getActivity(), "Unable to read image", Toast.LENGTH_LONG).show();
            }

        }
    }



    public Bitmap getProfile_picture() {
        return profile_picture;
    }

    public void setProfile_picture(Bitmap profile_picture) {
        CircleImageView pic = (CircleImageView)  getActivity().findViewById(R.id.profile_picture);
        pic.setImageBitmap(profile_picture);
    }

    public Bitmap getProfile_cover() {
        return profile_cover;
    }

    public void setProfile_cover(Bitmap profile_cover) {
        ImageView coverView = (ImageView) getActivity().findViewById(R.id.cover);
        coverView.setImageBitmap(profile_cover);
    }

    public String getDomain_name() {
        return domain_name;
    }

    public void setDomain_name(String domain_name) {
        this.domain_name = domain_name;
    }
}
