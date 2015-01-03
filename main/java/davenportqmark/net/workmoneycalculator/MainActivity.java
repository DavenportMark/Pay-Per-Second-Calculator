package davenportqmark.net.workmoneycalculator;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.text.SpannableStringBuilder;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends Activity {

    Long firstMomentYouPressedTheButton;
    Long lastRunTime = 1001l;
    public Handler mHandler = new Handler();

    //Default pay $8 an hour
    Float pay = 8f;
    Timer t = new Timer();
    Long ThisMomentRightNow = System.currentTimeMillis();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
    public void allSystemsGo(View view) {

        firstMomentYouPressedTheButton = System.currentTimeMillis();
        EditText et = (EditText) findViewById(R.id.inputNumber);
        pay = Float.parseFloat(et.getText().toString());


    }

    public void showMoneyEarned(View view) {


        mHandler.postDelayed(updateTask, 1000);



    }
    private Runnable updateTask = new Runnable () {
        public void run() {
            Log.d(getString(R.string.app_name) + " ChatList.updateTask()",
                    "updateTask run!");

            // run any code here...

            EditText et = (EditText) findViewById(R.id.outputBox);

            ThisMomentRightNow = System.currentTimeMillis();
            Long howManySecondsItHasBeen = ((ThisMomentRightNow - firstMomentYouPressedTheButton) / 500);
            Float wageSoFar = ((pay / 60) / 60) * howManySecondsItHasBeen;
            // This gives wage in seconds times rate of pay per second.
            String textWage = wageSoFar.toString();

            et.setText(textWage.toString());
            lastRunTime = System.currentTimeMillis();


            // queue the task to run again in 15 seconds...
            mHandler.postDelayed(updateTask, 500);


        }
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
