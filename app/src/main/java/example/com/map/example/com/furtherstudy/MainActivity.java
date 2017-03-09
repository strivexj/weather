package example.com.map.example.com.furtherstudy;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import example.com.map.LoationActivity;
import example.com.map.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private DrawerLayout mDrawerLayout;
    private Picture[]pictures= {
            new Picture("1",R.mipmap.a0),new Picture("2",R.mipmap.a1),new Picture("3",R.mipmap.a2),new Picture("4",R.mipmap.a3),new Picture("5",R.mipmap.a4),new Picture("6",R.mipmap.a5),new Picture("7",R.mipmap.a6),new Picture("8",R.mipmap.a7),new Picture("9",R.mipmap.a8),new Picture("10",R.mipmap.a9),new Picture("11",R.mipmap.a10),new Picture("12",R.mipmap.a11),new Picture("13",R.mipmap.a12),new Picture("14",R.mipmap.a13),new Picture("15",R.mipmap.a14),new Picture("16",R.mipmap.a15),new Picture("17",R.mipmap.a16),new Picture("18",R.mipmap.a17),new Picture("19",R.mipmap.a18),new Picture("20",R.mipmap.a19),new Picture("21",R.mipmap.a20),new Picture("22",R.mipmap.a21),new Picture("23",R.mipmap.a22),new Picture("24",R.mipmap.a23),new Picture("25",R.mipmap.a24),new Picture("26",R.mipmap.a25),new Picture("27",R.mipmap.a26),new Picture("28",R.mipmap.a27)};
    private List<Picture>pictureList=new ArrayList<>();
    private PictureAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDrawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);
        ActionBar actionBar=getSupportActionBar();



        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }

        initPictures();
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recycler_view) ;
        GridLayoutManager layoutManager=new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        adapter=new PictureAdapter(pictureList);
        recyclerView.setAdapter(adapter);

        swipeRefreshLayout=(SwipeRefreshLayout)findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){
            public  void onRefresh(){
                refreshPictures();
            }
        });

        NavigationView navigationView=(NavigationView)findViewById(R.id.nav_view);
        //navigationView.setCheckedItem(R.id.nav_call);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_call:{
                        Intent intent=new Intent(Intent.ACTION_DIAL);
                        startActivityForResult(intent,0);
                        break;
                    }
                    case R.id.nav_friends:{
                        Intent intent=new Intent(Intent.ACTION_PICK);
                        intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
                        startActivityForResult(intent,0);
                        break;
                    }
                }
                //mDrawerLayout.closeDrawers();
                return true;
            }
        });
        FloatingActionButton fab=(FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener(){

                public void onClick(View v) {
                    Snackbar.make(v, "Date deleted ~!", Snackbar.LENGTH_SHORT)
                            .setAction("Undo", new View.OnClickListener() {
                                public void onClick(View v) {
                                    Toast.makeText(MainActivity.this, "Date restored", Toast.LENGTH_SHORT).show();
                                }

                            }).show();
                }
        });

    }
    public  void onClick(View v){

    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }
    public  boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.navigation:{
                Intent intent=new Intent(MainActivity.this,LoationActivity.class);
                startActivityForResult(intent,0);
                break;
            }
            case R.id.delete:{
                Toast.makeText(MainActivity.this,"You clicked delete~!",Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.settings:{
                Toast.makeText(MainActivity.this,"You clicked settings~!",Toast.LENGTH_SHORT).show();
                break;
            }
            case android.R.id.home:{
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            }
            case R.id.translate:{
                Intent intent=new Intent(MainActivity.this,TranslateActivity.class);
                startActivity(intent);
                break;
            }
            default:
        }
        return true;
    }
    private void initPictures(){
        pictureList.clear();
      for(int i=0;i<100;i++){
          Random random=new Random();
          int index=random.nextInt(pictures.length);
          pictureList.add(pictures[index]);
        }
    }
    private void refreshPictures(){
        new Thread(new Runnable() {
            @Override
            public void run() {
               try{
                    Thread.sleep(1000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initPictures();
                        adapter.notifyDataSetChanged();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
            }
        }).start();
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode==KeyEvent.KEYCODE_BACK&&mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
            mDrawerLayout.closeDrawers();
            return true;
        }
       return super.onKeyDown(keyCode,event);
    }
}


