package com.mck.weathernow.asynctask;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;

import com.mck.weathernow.service.OpenWeatherMapService;

/**
 * AsyncTask to get a weather icon requ
 * to start.
 * Created by Michael on 5/16/2016.
 */
public class GetWeatherIconAsyncTask extends AsyncTask<Object,Integer,Bitmap>{
    private Integer reqId;

    public interface callback {
        void onWeatherIconResult(Bitmap icon, Integer requestId);
        Context getContext();
    }

    GetWeatherIconAsyncTask.callback callback;

    @Override
    protected Bitmap doInBackground(Object... params) {
        callback = (GetWeatherIconAsyncTask.callback) params[0];
        String iconId = (String) params[1];
        if (params.length > 2){
            reqId = (Integer) params[2];
        }
        return OpenWeatherMapService.instance().requestWeatherIcon(callback.getContext(), iconId);
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        if (!isCancelled()){
            callback.onWeatherIconResult(bitmap, reqId);
        }
    }
}
