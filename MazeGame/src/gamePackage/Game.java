package gamePackage;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable{
	
	private static final long serialVersionUID = -654684493721385565L;
	public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;
	
	private Thread thread;
	private boolean running = false;
	
	private Handler handler;
	public static int LEVEL = 1;
	private HUD hud;
	public static int wallSize = 40;
	private Menu menu;
	public static boolean paused = false;
	private Pause pause;
	
	public enum STATE{
		Menu,
		Game,
		Help
	};
	
	STATE gameState=STATE.Menu;
	
	private void init(){
		handler = new Handler(this);
		this.addKeyListener(new KeyInput(handler));
		menu = new Menu(this, handler);
		this.addMouseListener(menu);
		pause=new Pause(this);
		this.addMouseListener(pause);
		hud = new HUD();
		
	}
	
	public Game(){
		new Window(WIDTH, HEIGHT, "Maze Game", this);
	}
	
	public synchronized void start(){
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop(){
		try{
			thread.join();
			running = false;
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void run() {
		init();
		requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
	
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				tick();
				delta --;
				
			}
			if(running)
				render();
				
				if(System.currentTimeMillis() - timer > 1000){
					timer += 1000;
				
				}
		}
		stop();
	}
	
	public void tick(){
		if(this.gameState==STATE.Game&&Game.paused==false){
			if(gameState==STATE.Game){
				hud.tick();
				pause.tick();
			}else{
				menu.tick();
			}
			handler.tick();
		}
	}
	
	public void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		handler.render(g);
		if(gameState==STATE.Game){
			hud.render(g);
			pause.render(g);
		}else{
			menu.render(g);
		}
		
		g.dispose();
		bs.show();
	}
	
	public static int clamp(int var, int min, int max){
		if(var <= min)
			return var=min;
		if(var>=max)
			return var=max;
		else return var;
	}

	
	
	public static void main(String[] args){
		
		new Game();
		
	}

}
