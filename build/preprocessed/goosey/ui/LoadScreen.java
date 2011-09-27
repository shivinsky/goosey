package goosey.ui;

import goosey.ui.control.ProgressBar;
import goosey.*;
import goosey.common.Locale;

import java.io.IOException;

import javax.microedition.lcdui.*;
import javax.microedition.lcdui.game.*;

/**
 * Load screen
 */
public class LoadScreen extends GameCanvas implements Runnable {
    
    private final int DELAY = 100;
    private volatile Thread thread;
    
    private Sprite sprite;
    private ProgressBar progress;
    
    private Command abort = new Command(Locale.STR_ABORT, Command.BACK, 0); 
    
    private BoardScreen boardScreen;
    private WordScreen wordScreen;
    private Noodle noodle;
    
    private class LoadScreenListener implements CommandListener {
        
        public void commandAction(Command c, Displayable d) {
            if (c == abort)
               DisplayManager.getDisplay().setCurrent(boardScreen);
        }
        
    }
    
    public LoadScreen(BoardScreen boardScreen, WordScreen wordScreen) {
        super(true);
        
        this.boardScreen = boardScreen;
        this.wordScreen = wordScreen;
        
        try {
            sprite = new Sprite(Image.createImage("/loading.png"), 80, 80);
        } catch (IOException ex) {
        }
        
        sprite.setPosition(getWidth() / 2 - sprite.getWidth() / 2, 
            getHeight() / 2 - sprite.getHeight() / 2);

        progress = new ProgressBar(25, getWidth() - 25, getWidth() - 50, 15, 
            Locale.ALPHABET.length());

        addCommand(abort);
        setCommandListener(new LoadScreenListener());
    }

    public void run() {
        Graphics g = getGraphics();
        while (thread == Thread.currentThread()) {
            paint(g);
            if (!noodle.isAlive()) {
                wordScreen.setResult(noodle.getResult());
                DisplayManager.getDisplay().setCurrent(wordScreen);
            }
            try { 
                Thread.sleep(DELAY); 
            } catch (InterruptedException ex) {
            }
        }

    }
    
    public void increment() {
        progress.setValue(progress.getValue() + 1);
    }
    
    protected void showNotify() {
        noodle = new Noodle(boardScreen.getBoard(), new ProgressListener() {
            
            public void onProgress() {
                increment();
            }
            
        });    
        noodle.start();
        progress.setValue(0);
        thread = new Thread(this);
	thread.start();
    }
    
    protected void hideNotify() {
	thread = null;
        noodle.quit();
    }
         
    public void paint(Graphics g) {
        g.setColor(0xffffff);
        g.fillRect(0, 0, getWidth(), getHeight());
        
        sprite.nextFrame();
        sprite.paint(g);
        progress.paint(g);

        flushGraphics();    
    }
    
} 
