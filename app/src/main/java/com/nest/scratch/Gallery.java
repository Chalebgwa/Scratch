package com.nest.scratch;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;
import static com.nest.scratch.R.id.description;
import static com.nest.scratch.R.id.ifRoom;
import static com.nest.scratch.R.id.item_image;


public class Gallery extends Fragment {

    private EditText price,description,title;
    private CheckBox share;
    private ImageView itemimage;
    private static final int GALLERY_REQUEST_CODE = 20;
    private static final int CAMERA_REQUEST_CODE = 40;
    private ImageButton[] imageButtons;
    private ImageButton addswitch;
    private int index = 0;
    private Bitmap[] itemPics;
    private DBLocal dbHandler;
    private Bitmap bitmap;



    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root=inflater.inflate(R.layout.activity_gallery,container,false);
        imageButtons = new ImageButton[3];

        imageButtons[0] =(ImageButton) root.findViewById(R.id.addbutton0);
        imageButtons[1] =(ImageButton) root.findViewById(R.id.addbutton1);
        imageButtons[2] =(ImageButton) root.findViewById(R.id.addbutton2);

        imageButtons[1].setVisibility(imageButtons[1].INVISIBLE);
        imageButtons[2].setVisibility(imageButtons[2].INVISIBLE);

        itemPics = new Bitmap[3];

        price = (EditText) root.findViewById(R.id.price);
        description=(EditText) root.findViewById(R.id.description);
        title =(EditText) root.findViewById(R.id.Title);
        share=(CheckBox) root.findViewById(R.id.share);

        final Button button = (Button) root.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postItem();
            }
        });

        final ImageButton openCamera = (ImageButton) root.findViewById(R.id.camera_acess);
        openCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(index<2){
                    index++;
                }
                openCameraIntent(v);
            }
        });


        itemimage=(ImageView) root.findViewById(item_image);

        imageButtons[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index =0;
                openImageGallery(v);
            }
        });
        imageButtons[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index = 1;
                openImageGallery(v);
            }
        });
        imageButtons[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index =2;
                openImageGallery(v);
            }
        });



        dbHandler = new DBLocal(this.getContext());

        return root;

    }


    //Add item to the datase
    public void postItem(){

        String description = String.valueOf(this.description.getText());
        String title=String.valueOf(this.title.getText());
        String price=String.valueOf(this.price.getText());
        Item item = new Item(title,description,price,itemPics);
        dbHandler.addItem(item);
        //Toast.makeText(getContext(),"Saved",Toast.LENGTH_LONG).show();

    }

    //open camera intent and snap a picture of the sold item
    public void openCameraIntent(View v){

        Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(camera,CAMERA_REQUEST_CODE);
    }

    //Open your device's local image gallery and click on the image you need
    public void openImageGallery(View v) {

        Intent photopicker = new Intent(Intent.ACTION_PICK);
        File file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        Uri data = Uri.parse(file.getPath());
        photopicker.setDataAndType(data,"image/*");
        startActivityForResult(photopicker,GALLERY_REQUEST_CODE);


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK) {

            if ((requestCode == GALLERY_REQUEST_CODE)) {

                Uri img_uri = data.getData();

                InputStream inputStream;
                try
                {

                    inputStream = getActivity().getContentResolver().openInputStream(img_uri);
                    bitmap = BitmapFactory.decodeStream(inputStream);
                    itemimage.setImageBitmap(bitmap);
                    if(index<2){
                    imageButtons[index+1].setVisibility(imageButtons[0].VISIBLE);
                }
                    imageButtons[index].setImageBitmap(bitmap);
                    itemPics[index]=bitmap;


                } catch (Exception e) {
                    Toast.makeText(getActivity(), "Unable to read image", Toast.LENGTH_LONG).show();
                }

            } else if ((requestCode == CAMERA_REQUEST_CODE)) {
                super.onActivityResult(requestCode, resultCode, data);
                bitmap = (Bitmap) data.getExtras().get("data");
                if(index<2){
                    imageButtons[index+1].setVisibility(imageButtons[0].VISIBLE);
                }
                imageButtons[index].setImageBitmap(bitmap);

                itemPics[index]=bitmap;

                itemimage.setImageBitmap(bitmap);

            }







        }


    }



}

