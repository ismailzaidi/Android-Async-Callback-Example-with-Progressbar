package rootfeeder.iz.com.asyncexample;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;

/**
 * This class deals with reading data and handelling it accordingly.
 *
 *
 * AsyncTask firstParam is Void because I don't want to execute anything
 * Param1 = Void, meaning I don't want to execute using the execute() method. Usually developers pass URLs
 * Param2 = Integer, used for onProgressUpdate(). Change to String if you wish to display the content of the progress.
 * Param3 = ArrayList<String>, this is a sample data that I want returned to the user
 */
public class DataAsync extends AsyncTask<Void,Integer,ArrayList<String>>{




    /**
     * Declare both interfaces in DataAsync and ProgressDialogLoader
     */
    private CallBacks.FetchData callBack;
    private CallBacks.UpdateProgressBar progressBarCallBack;
    private Context context;
    public DataAsync(Context context, CallBacks.FetchData callBack, CallBacks.UpdateProgressBar progressBarCallBack) {
        this.context = context;
        this.callBack = callBack;
        this.progressBarCallBack = progressBarCallBack;
    }

    /**
     * Called when using publishResults() in doInBackground
     * @param values
     */
    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        //Update the progress Dialog
        Log.v("Data","Progress: " + values[0]);
        progressBarCallBack.updateProgress(values[0]);
    }
    @Override
    protected ArrayList<String> doInBackground(Void... params) {
        ArrayList<String> listOfRandom = new ArrayList<String>();
        for(int i=1;i<100;i++){
            listOfRandom.add(String.valueOf(i));
            publishProgress(i);
            if(i%20==0){
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
        return listOfRandom;
    }

    /**
     * Called when the task is finished in doInBackground
     * @param strings
     */
    @Override
    protected void onPostExecute(ArrayList<String> strings) {
        super.onPostExecute(strings);
        callBack.handleData(strings);
    }
}
