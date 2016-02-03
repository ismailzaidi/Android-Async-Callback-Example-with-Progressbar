package rootfeeder.iz.com.asyncexample;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;


public class ProgressDialogLoader implements CallBacks.UpdateProgressBar{
    /**
     * Declare Callback function for updating the progress dialog
     */
    private Context context;
    private ProgressDialog dialog;
    public ProgressDialogLoader(Context context) {
        this.context = context;
        buildDialog();
    }
    private void buildDialog() {
        dialog = ProgressDialog.show(context, "",
                "Loading. Please wait...", true);
        dialog.setCancelable(false);
    }
    @Override
    public void updateProgress(int progressValue) {
        dialog.setProgress(progressValue);
    }
    public void dismiss(){
        dialog.dismiss();
    }
}
