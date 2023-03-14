package main;

import java.awt.*;

public class EventRect extends Rectangle {
    public int eventRectDefaultX,eventRectDefaultY;
    public EventRect(){
        this.x=23;
        this.y=23;
        this.height = 2;
        this.width = 2;
        this.eventRectDefaultX = this.x;
        this.eventRectDefaultY = this.y;
    }
    public boolean eventDone = false;
}
