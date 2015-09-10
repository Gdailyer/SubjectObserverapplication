package com.example.lucia.observeapplicaton;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.lucia.observeapplicaton.lib.BaseActivity.BaseObserverActivity;
import com.example.lucia.observeapplicaton.lib.EventType.EventType;
import com.example.lucia.observeapplicaton.lib.Observer.EventObserver;
import com.example.lucia.observeapplicaton.lib.Subject.EventSubject;

import java.lang.ref.WeakReference;

public class MainActivity extends BaseObserverActivity {

    private TextView text;
    private Button mainbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainbutton = (Button)findViewById(R.id.Mainbutton);
        mainbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,PostActivity.class));
               // finish();
            }
        });
        text = (TextView)findViewById(R.id.maintextview);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    /**
     * 接受通知
     * @param eventType 事件类型
     */
    @Override
    protected void onChange(String eventType){
        if(EventType.UPDATE_MAIN == eventType){
         runOnUiThread(new Runnable() {
             @Override
             public void run() {
                 //更新Main界面
                 mainbutton.setText(R.string.Mainreack);
             }
         });
        }else if(EventType.UPDATE_Text == eventType){
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    //更新Main界面

                    text.setText(R.string.MainSecondlayout);
                }
            });
        }

    }

    @Override
    protected  String[] getObserverEventType(){
        return new String[]{
                EventType.UPDATE_MAIN,
                EventType.UPDATE_Text
        };
    }


/*
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
*/

}
