package example.com.map.example.com.furtherstudy;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;

import de.hdodenhof.circleimageview.CircleImageView;
import example.com.map.R;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TranslateActivity extends AppCompatActivity {
    private EditText original;
    private TextView translate;
    private CircleImageView confirm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        original = (EditText) findViewById(R.id.original);
        translate = (TextView) findViewById(R.id.translateit);
        confirm = (CircleImageView) findViewById(R.id.confirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRequestWithOkHttp("http://fanyi.youdao.com/openapi." +
                        "do?keyfrom=strive123&key=181998645&type=data&doctype=xml&version=1.1&q="
                        + original.getText().toString());
                //showResponse(translate);
            }
        });

        FloatingActionButton transfab=(FloatingActionButton)findViewById(R.id.transfab);
        transfab.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                confirm.callOnClick();
            }
        });
    }
    private void sendRequestWithOkHttp(final String ourl){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    OkHttpClient client=new OkHttpClient();
                    Request request=new Request.Builder()
                            .url(ourl)
                            .build();
                    Response response=client.newCall(request).execute();
                    String responseData=response.body().string();
                    parseXMLWithPull(responseData);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }
    private void parseXMLWithPull(String xmlData){
        StringBuilder translate=new StringBuilder();;
        try{
            XmlPullParserFactory factory=XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser=factory.newPullParser();
            xmlPullParser.setInput(new StringReader(xmlData));
            int eventType=xmlPullParser.getEventType();
            while(eventType!=XmlPullParser.END_DOCUMENT){
                String nodeName=xmlPullParser.getName();

                if("paragraph".equals(nodeName)||"phonetic".equals(nodeName)||"ex".equals(nodeName)){
                    translate.append(xmlPullParser.nextText());
                    translate.append("\n");
                }
                eventType=xmlPullParser.next();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        showResponse( translate.toString());
    }
    private void showResponse(final String response) {
        runOnUiThread(new Runnable() {
            public void run() {
                translate.setText(response);
            }
        });
    }
    //使用OkHttpUtil按钮有时候按了没反应
}
