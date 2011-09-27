package goosey.ui.control;

import javax.microedition.lcdui.Graphics;

public class Label extends Control {
    
    private String caption;
    private int captionX;
    private int captionY;
    
    public Label(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public void setCaption(String caption) {
        this.caption = caption;
        
        captionX = getX() + getWidth() / 2 - getFont().stringWidth(caption) / 2;
        captionY = getY() + getHeight() / 2 - getFont().getHeight() / 2;   
    }
    
    protected void paint(Graphics g) {
        g.setColor(0x000000);
        g.drawString(caption, captionX, captionY, Graphics.TOP | Graphics.LEFT);
    }
    
}
