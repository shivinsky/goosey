package goosey.ui.control;

import javax.microedition.lcdui.*;

/**
 * Progress bar
 */
public class ProgressBar extends Canvas {
    
    private int width;
    private int height;
    private int x;
    private int y;
    
    private int position;
    private int max;
    private int value;
    
    public ProgressBar(int x, int y, int width, int height, int max) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.max = max;
    }

    public int getValue() {
        return value;
    }
    
    public void setValue(int value) {
        this.value = value;
        position = (int)(width * value / (float)max);
    }
    
    public void paint(Graphics g) {
        g.setColor(0x000000);
        g.drawRect(x, y, width, height);    
        g.setColor(0xc0c0c0);        
        g.fillRect(x + 1, y + 1, position - 1, height - 1);
    }
        
}
