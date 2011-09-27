package goosey.ui.control;

import javax.microedition.lcdui.Graphics;

/**
 * Button
 */
public class Button extends Control {
    
    private Label label;

    public Button(int x, int y, int width, int height, String caption) {
        super(x, y, width, height);
        
        label = new Label(x, y, width, height);
        label.setCaption(caption);
    }
    
    protected void paint(Graphics g) {
        label.paint(g);
    }
    
}
