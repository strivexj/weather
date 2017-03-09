package example.com.map.example.com.furtherstudy;

/**
 * Created by 10032 on 2017/2/7.
 */

public class Picture {
    private String name;
    private int imageId;
    public Picture(String name,int imageId){
        this.name=name;
        this.imageId=imageId;
    }
    public String getName(){
        return  name;
    }
    public  int getImageId(){
        return imageId;
    }
}
