package com.example.usama.imageqrcode;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by USAMA on 7/15/2016.
 */
class GetImageAsync extends AsyncTask<URL, Integer, String> {

    private Bitmap bitmap;
    public void setImg(Bitmap bitmap) {
        this.bitmap = bitmap;
    }


    @Override
    protected String doInBackground(URL... urls) {

        /*try
        {*/
            /*InputStream inputStream = activity.getContentResolver().openInputStream(urls[0]);*/

        // get QRCode from URL
        // String url = "http://www.qrstuff.com/images/default_qrcode.png";
        /*InputStream inputStream = null;
        try {
            inputStream = (InputStream) new URL(url).getContent();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);*/

        if (bitmap == null) {
                /*Log.e(TAG, "uri is not a bitmap," + uri.toString());*/
            return null;
        }
        int width = bitmap.getWidth(), height = bitmap.getHeight();
        int[] pixels = new int[width * height];
        bitmap.getPixels(pixels, 0, width, 0, 0, width, height);
        bitmap.recycle();
        bitmap = null;
        RGBLuminanceSource source = new RGBLuminanceSource(width, height, pixels);
        BinaryBitmap bBitmap = new BinaryBitmap(new HybridBinarizer(source));
        MultiFormatReader reader = new MultiFormatReader();
        try {
            Result result = reader.decode(bBitmap);
            String decodedtext = result.getText();
            return decodedtext;

        } catch (NotFoundException e) {
                /*Log.e(TAG, "decode exception", e);*/
            return null;
        }
        /*}*/
        /*catch (FileNotFoundException e)
        {
            *//*Log.e(TAG, "can not open file" + uri.toString(), e);*//*
            return null;
        }*/
    }



}
