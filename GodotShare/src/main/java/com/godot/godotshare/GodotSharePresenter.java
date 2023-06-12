package com.godot.godotshare;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;

import androidx.core.content.FileProvider;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class GodotSharePresenter {
    private Activity activity;
    public GodotSharePresenter(Activity activity){
        this.activity = activity;
    }

    public void share_img(String path, String title, String message) {
        File file = new File(getActivity().getFilesDir() + File.pathSeparator + path);
        Uri uri = FileProvider.getUriForFile(getActivity(), getAuthority(), file);
        share_img_helper(uri, title, message);
    }

    public void share_img_web(String url, String title, String message) {
        Bitmap bmp = getBitmapFromURL(url);
        String path = MediaStore.Images.Media.insertImage(getActivity().getContentResolver(), bmp, "", null);
        Uri uri = Uri.parse(path);
        share_img_helper(uri, title, message);
    }

    private void share_img_helper(Uri uri, String title, String message){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_TEXT, message);
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        getActivity().startActivity(Intent.createChooser(intent, title));
    }

    private Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            return BitmapFactory.decodeStream(input);
        } catch (IOException e) {
            return null;
        }
    }

    private Activity getActivity() {
        return activity;
    }

    private String getAuthority(){
        return getActivity().getPackageName()+".godotshare.fileprovider";
    }
}
