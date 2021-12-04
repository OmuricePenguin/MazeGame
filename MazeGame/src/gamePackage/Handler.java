package gamePackage;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;

public class Handler {
	Game game;
	List<GameObject> object = new LinkedList<GameObject>();
	private BufferedImage level = null, level2 = null, level3 = null, level4 = null, level5 = null, level6=null, level7 = null, level8=null, level9=null, level10 = null, level11 = null;
	public Handler(Game game){
		this.game=game;
		BufferedImageLoader loader = new BufferedImageLoader();
		level = loader.loadImage("/level.png");
		level2 = loader.loadImage("/level2.png");
		level3 = loader.loadImage("/level3.png");
		level4 = loader.loadImage("/level4.png");
		level5 = loader.loadImage("/level5.png");
		level6 = loader.loadImage("/level6.png");
		level7 = loader.loadImage("/level7.png");
		level8 = loader.loadImage("/level8.png");
		level9 = loader.loadImage("/level9.png");
		level10 = loader.loadImage("/level10.png");
		level11 = loader.loadImage("/level11.png");
	}
	
	public void tick(){

			for(int i=0; i<object.size(); i++){
				
				GameObject tempObject = object.get(i);
				
				tempObject.tick();
			
		}
	}
	public void render(Graphics g){
		for(int i=0; i<object.size(); i++){
			
			GameObject tempObject = object.get(i);
		
			tempObject.render(g);
			
		}	
		if(Game.LEVEL==11){
			g.setColor(Color.WHITE);
			Font font = new Font("Arial", 1, 50);
			g.setFont(font);
			g.drawString("You Win!", 200, 220);
		}
		
	}
	public void addObject(GameObject object){
		
		this.object.add(object);
		
	}
	
	public void removeObject(GameObject object){
		this.object.remove(object);
	}
	
	private void clearLevel(){
		this.object.clear();
	}
	
	public void loadLevel(){
		clearLevel();
		if(Game.LEVEL==1){
			LoadImageLevel(level);
			this.addObject(new Door(0, 150, ID.Door, this));
		}
		else if(Game.LEVEL==2){
			LoadImageLevel(level2);
			this.addObject(new Door(0, 150, ID.Door, this));
		}
		else if(Game.LEVEL==3){
			LoadImageLevel(level3);
			this.addObject(new Door(0, 150, ID.Door, this));
		}
		else if(Game.LEVEL==4){
			LoadImageLevel(level4);
			this.addObject(new Door(0, 150, ID.Door, this));
		}
		else if(Game.LEVEL==5){
			LoadImageLevel(level5);
			this.addObject(new Door(0, 300, ID.Door, this));
		}
		else if(Game.LEVEL==6){
			LoadImageLevel(level6);
			this.addObject(new Door(0, 150, ID.Door, this));
		}
		else if(Game.LEVEL==7){
			LoadImageLevel(level7);
			this.addObject(new Door(300, 180, ID.Door, this));
		}
		else if(Game.LEVEL==8){
			LoadImageLevel(level8);
			this.addObject(new Door(0, 180, ID.Door, this));
		}
		else if(Game.LEVEL==9){
			LoadImageLevel(level9);
			this.addObject(new Door(0, 180, ID.Door, this));
		}
		else if(Game.LEVEL==10){
			LoadImageLevel(level10);
			this.addObject(new Door(0, 180, ID.Door, this));
		}
		else if(Game.LEVEL==11)
			LoadImageLevel(level11);
	}


	public void LoadImageLevel(BufferedImage image){
		int w = image.getWidth();
		int h = image.getHeight();
		for(int xx=0; xx<h; xx++){
			for(int yy=0; yy<w; yy++){
				int pixel=image.getRGB(xx, yy);
				int red = (pixel>>16)&0xff;
				int green = (pixel>>8)&0xff;
				int blue =(pixel)&0xff;
				//room size should be 16 for WIDTH, 12 for HEIGHT
				if(red == 255 && green == 255 & blue == 255)addObject(new Wall(xx*40, yy*40, ID.Wall));
				if(red == 0 && green == 0 & blue == 255)addObject(new Player(xx*40, yy*40, ID.Player, this));
				if(red == 255 && green == 0 & blue == 0)addObject(new Enemy(xx*40, yy*40, ID.Enemy, this));
				if(red ==0 && green == 255 & blue == 255)addObject(new HealthPack(xx*40, yy*40, ID.HealthPack));
				if(red ==255 && green == 0 & blue == 255)addObject(new IntelEnemy(xx*40, yy*40, ID.IntelEnemy, this));
			}
		}
		
	}
	
}
