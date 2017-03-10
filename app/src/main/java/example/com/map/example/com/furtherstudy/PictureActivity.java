package example.com.map.example.com.furtherstudy;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import example.com.map.R;

import com.bumptech.glide.Glide;

public class PictureActivity extends AppCompatActivity {

    public static final String PICTURE_NAME = "picture_name";

    public static final String PICTURE_IMAGE_ID = "picture_image_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);

        Intent startmusicIntent=new Intent(PictureActivity.this,MusicService.class);;
        startService(startmusicIntent);

        Intent intent = getIntent();
        String pictureName = intent.getStringExtra(PICTURE_NAME);
        int pictureImageId = intent.getIntExtra(PICTURE_IMAGE_ID, 0);

       /* MediaPlayer mediaPlayer=new MediaPlayer();
        mediaPlayer=MediaPlayer.create(PictureActivity.this,R.raw.music);
        mediaPlayer.start();*/

        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        ImageView pictureImageView = (ImageView) findViewById(R.id.picture_image_view);
        TextView fruitContentText = (TextView) findViewById(R.id.picture_content_text);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        //collapsingToolbar.setTitle(pictureName);
        collapsingToolbar.setTitle("娃哈哈~!");

        Glide.with(this).load(pictureImageId).into(pictureImageView);
        String fruitContent = generatePictureContent(pictureName);
        fruitContentText.setText(fruitContent);
    }

    @Override
    protected void onDestroy() {
        Intent stopIntent=new Intent(PictureActivity.this,MusicService.class);
        stopService(stopIntent);
        super.onDestroy();
    }

    private String generatePictureContent(String pictureName) {
        StringBuilder pictureContent = new StringBuilder();
        String lyric="传说有个魔仙堡\n有个女王不得了\n每个魔仙得她指导  \n都盼望世界更美好  \n变大变小真的奇妙  \n一个咒语一个符号  \n一不小心就会一团糟  \n我有个好提议  \n就约定在一起  \n去寻找魔法的秘密  \n一看到巧克力  \n特别是草莓的  \n我知道我无能为力  \n巴啦啦小魔仙  \n咒语一呼喊  \n就开始正义的一战  \n巴啦啦小魔仙  \n咒语一呼喊  \n会实现最美的梦想  \n有了友爱力量  \n我的法力变强  \n战胜灰暗忧伤  \n我们才能够成长  \n就算魔法多神奇  \n夺到魔法的彩石  \n比不上我得到友谊  \n有了爱世界才美丽  \n一次探险一起游历  \n一种鼓励一点勇气  \n为这世界送上了奇迹  \n我有个好提议  \n就约定在一起  \n去寻找魔法的秘密  \n一看到巧克力  \n特别是草莓的  \n我知道我无能为力  \n" +
                "巴啦啦小魔仙  \n咒语一呼喊  \n就展开正义的一战  \n巴啦啦小魔仙  \n咒语一呼喊  \n会实现最美的梦想  \n巴啦啦小魔仙  \n咒语一呼喊  \n为将来不断快长大  \n巴啦啦小魔仙  \n咒语一呼喊  \n这世界马上不一样  \n有了友爱力量  \n我的法力变强  \n战胜灰暗忧伤  \n我们才能够成长.  \n小魔仙  \n来到人间  \n一整天  \n帮助别人不空闲  \n小魔仙  \n不怕危险  \n守一天  \n不怕困难好未来  \n小魔仙  \n友爱永远  \n小魔仙  \n正义的心好未来多\n";
       pictureContent.append(lyric);
        pictureContent.append(lyric);
        return pictureContent.toString();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
