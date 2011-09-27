package goosey.ui.control;

import javax.microedition.lcdui.Graphics;

public class SpinEdit extends Control {
    
    private Button prevButton;
    private Button nextButton;
    private Label textLabel;
    
    public SpinEdit(int x, int y, int width, int height) {
        super(x, y, width, height);
        
        prevButton = new Button(x - 15, y, 15, 15, "<");
        nextButton = new Button(x + width, y, 15, 15, ">");
        
        textLabel = new Label(x, y, width, height);
        textLabel.setCaption("ololo!");
    }
    
    public void paint(Graphics g) {
        g.setColor(0x000000);
        
        prevButton.paint(g);
        textLabel.paint(g);
        nextButton.paint(g);
    }
    
    public void setText(String text) {
        
    }
    
    public void pointerPressed(int x, int y) {
        System.out.println(x + " " + y);
    }
    
    
}
