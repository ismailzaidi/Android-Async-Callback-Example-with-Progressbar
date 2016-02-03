package rootfeeder.iz.com.asyncexample;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String DATA = "DATA";
    private ProgressDialogLoader dialogLoader;
    private CallBacks.FetchData callBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        dialogLoader = new ProgressDialogLoader(MainActivity.this);
        callBack = new CallBacks.FetchData() {
            @Override
            public void handleData(ArrayList<String> httpData) {
                // What happens when the process finishes
                //Display the view that use data fetched from the internet. It is important to put it here otherwise a nullpointerexception will be thrown
                Log.v(DATA, "ArrayList Content: " + httpData.toString());
                dialogLoader.dismiss();
                displayData(httpData);
            }
        };
        DataAsync task = new DataAsync(getApplicationContext(),callBack,dialogLoader);
        task.execute();
    }
    private void displayData(ArrayList<String> data){
        ListView listView = (ListView) findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,data);
        listView.setAdapter(adapter);
    }
    /**
     * When the activity is killed we want to dismiss the dialog otherwise an exection is thrown
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(dialogLoader!=null){
            dialogLoader.dismiss();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }
}
