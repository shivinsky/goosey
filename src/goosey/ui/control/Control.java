package goosey.ui.control;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Font;

public abstract class Control extends Canvas {
    
    private int x;
    private int y;
    private int width;
    private int height;
        
    public Control(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public int getWidth() {
        return width;
    }
    
    public int getHeight() {
        return height;
    }
    
    public Font getFont() {
        return Font.getDefaultFont();
    }
    
}
