package goosey.ui;

import javax.microedition.lcdui.Display;

/**
 * Display manager
 */
public class DisplayManager {
    
    private static Display display;
    
    public static Display getDisplay() {
        return display;
    }
    
    public static void initialize(Display display) {
        DisplayManager.display = display;
    }
    
}
