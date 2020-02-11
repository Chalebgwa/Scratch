package com.nest.scratch;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.net.URI;

/**
 * Coded with love.
 * Created by black on 1/29/17.
 */

public class FirebaseHandler {
    private StorageReference mStorageRef;

    public FirebaseHandler(){
        mStorageRef = FirebaseStorage.getInstance().getReference();
    }

    public void uploadFile(Bitmap bitmap){

        //mStorageRef.;
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }
}
