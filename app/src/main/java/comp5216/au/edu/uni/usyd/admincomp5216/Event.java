package comp5216.au.edu.uni.usyd.admincomp5216;

/**
 * Created by desktop on 19/10/2017.
 */

public class Event {

    String title;
    String locaiton;
    String desc;

    public Event(){

    }

    public Event(String title, String locaiton, String desc){
        this.title = title;
        this.locaiton = locaiton;
        this.desc = desc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocaiton() {
        return locaiton;
    }

    public void setLocaiton(String locaiton) {
        this.locaiton = locaiton;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
