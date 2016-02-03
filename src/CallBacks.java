package rootfeeder.iz.com.asyncexample;

import java.util.ArrayList;

/**
 * Created by Yusuf on 03/02/2016.
 */
public class CallBacks {

    public interface UpdateProgressBar {
        void updateProgress(int progressValue);
    }
    public interface FetchData{
        void handleData(ArrayList<String> httpData);
    }

}
