package gamePackage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Enemy extends GameObject{
public static int enemySize = Player.playerSize/2;
Handler handler;
private static int lastX;
private static int lastY;
	public Enemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		lastX = x;
		lastY = y;
		this.velX =7;
		this.velY = -7;
	}

	public void tick() {
		
		lastX = x;
		lastY = y;
		
		x+=velX;
		y+=velY;
		
		if(x+enemySize > Game.WIDTH - 30)x=Game.WIDTH-70;
		if(x<0)x=50;
		if(y+enemySize > Game.HEIGHT-60)y=Game.HEIGHT-110;
		if(y<0)y=50;	
		
		collision();
	}
	private void collision() {
		
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getID() == ID.Wall){
				if(this.getBounds().intersects(tempObject.getBounds())){
					
					if(Enemy.lastY+enemySize > tempObject.y && Enemy.lastY< tempObject.y+Game.wallSize){
						this.setVelX(velX * -1);
						return;
					}
					
				}
			}
		}
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getID() == ID.Wall){
				if(this.getBounds().intersects(tempObject.getBounds())){
					if(Enemy.lastX+enemySize > tempObject.x && Enemy.lastX< tempObject.x+Game.wallSize){
						this.setVelY(velY* (-1));
						return;
					}
				}
			}
		}
		
		
	}
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(x, y, enemySize, enemySize);
	}

	public Rectangle getBounds() {
	
		return new Rectangle(x, y, enemySize, enemySize);
	}

}


