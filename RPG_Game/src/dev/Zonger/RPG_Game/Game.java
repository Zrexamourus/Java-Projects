package dev.Zonger.RPG_Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import dev.Zonger.RPG_Game.display.Display;
import dev.Zonger.RPG_Game.gfx.ImageLoader;
import dev.Zonger.RPG_Game.gfx.SpriteSheet;

public class Game implements Runnable {
	
	private Display display;
	public int width, height;
	private boolean running = false;
	private Thread thread;
	public String title;
	private BufferStrategy bs;
	private Graphics g;
		
	
	public Game(String title, int width, int height) {
		
		this.width = width;
		this.height = height;
		this.title = title;	
		
	}
	
	private void init() {
		
		display = new Display(title, width, height);
		
	}
	
	private void tick() {
		
	}
	
	private void render() {
		
		bs = display.getCanvas().getBufferStrategy();
		if(bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		
		g = bs.getDrawGraphics();
		//Clear
		g.clearRect(0, 0, width, height);
		
		//Draw Here
		
	
		
		//End
		
		bs.show();
		g.dispose();
	}
	
	public void run() {
		
		init();
		while(running) {
		tick();
		render();
		}
		stop();
	}
	
	public synchronized void start() {
		
		if (running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
		
	}
	
	public synchronized void stop() {
		
		if(!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
			
		}	
	}
}
