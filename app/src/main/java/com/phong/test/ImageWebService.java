package com.phong.test;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by huynhducthanhphong on 12/27/15.
 */
public class ImageWebService extends AsyncTask<String, Integer, Bitmap>{
    private ProgressDialog progressDialog;
    private ImageView imageView;

    public ImageWebService(Context context, ImageView imageView1) {
        progressDialog = ProgressDialog.show(context, "Wait", "Please wait, contacting server..."); // show title and nội dung của đoạn thông báo
        progressDialog.setCancelable(false); // Sets whether this dialog is cancelable with the BACK key.
        imageView = imageView1;
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        String url = params[0];
        Bitmap bitmap = null;

        // time taking process
        InputStream inputStream = null;
        try {
            inputStream = new URL(url).openStream();
            bitmap = BitmapFactory.decodeStream(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        progressDialog.dismiss(); // tải xong thì bỏ dòng thông báo
        imageView.setImageBitmap(bitmap);
    }

}
