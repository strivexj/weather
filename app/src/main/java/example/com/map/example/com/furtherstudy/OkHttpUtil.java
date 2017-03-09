package example.com.map.example.com.furtherstudy;

/**
 * Created by 10032 on 2017/2/7.
 */

import android.widget.EditText;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;



import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
public class OkHttpUtil {
    public static String string;
       public static String parseXMLWithPull(String xmlData){
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
            return translate.toString();
        }
       public static String sendRequestWithOkHttp(final String ourl){
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
                        string=parseXMLWithPull(responseData);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }).start();
           return string;
        }


    //不用这个
   /* private void sendRequestWithHttpURLConnection(final String ourl) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                BufferedReader reader = null;
                try {
                    URL url = new URL(ourl);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setReadTimeout(8000);
                    InputStream in = connection.getInputStream();
                    reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    //response.append(url);
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    //showResponse(response.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }
    private void showResponse(final String response) {
        runOnUiThread(new Runnable() {
            public void run() {
                translate.setText(response);
            }
        });
    }*/

}
