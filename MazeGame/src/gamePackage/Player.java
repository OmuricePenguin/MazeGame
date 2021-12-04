package gamePackage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends GameObject{
	
	Handler handler;
	
	private static int lastX;
	private static int lastY;
	public static int playerSize = 32;
	
	public Player(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		lastX = x;
		lastY = y;
	}

	
	public void tick() {
		lastX=x;
		lastY=y;
		
		x+= velX;
		y+= velY;
		
		x = Game.clamp(x, 0, Game.WIDTH-40);
		y = Game.clamp(y, 0, Game.HEIGHT-60);
		
		collision();
	}

	
	private void collision() {
		for(int i = 0; i< handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getID()==ID.Enemy){
				if(getBounds().intersects(tempObject.getBounds())){
					HUD.HEALTH -= 2;
					if(HUD.HEALTH<=0){
						HUD.HEALTH=100;
						Game.LEVEL=1;
						handler.loadLevel();
					}
				}
			}
			if(tempObject.getID()==ID.Wall){
				if(this.getBounds().intersects(tempObject.getBounds())){
					
					if(Player.lastX + playerSize> tempObject.x && Player.lastX<tempObject.x+tempObject.width){
						if(Player.lastY>=tempObject.y+tempObject.height)
							this.setY(tempObject.y + tempObject.height +1);
						else if(Player.lastY+playerSize<=tempObject.y)
							this.setY(tempObject.y-playerSize-1);
					}
					
					
				if(Player.lastY + playerSize>= tempObject.y && Player.lastY<= tempObject.y+tempObject.height){
						if(Player.lastX >=tempObject.x +tempObject.width)
							this.setX(tempObject.x+tempObject.width + 1);
						else if(Player.lastX+playerSize <=tempObject.x)
							this.setX(tempObject.x -playerSize-1);
					}
				}
			}
			else if(tempObject.getID()==ID.HealthPack){
				if(getBounds().intersects(tempObject.getBounds())){
					handler.removeObject(tempObject);
					HUD.HEALTH+=50;
				}
			}
		}
	}


	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(x, y, playerSize, playerSize);
	}



	public Rectangle getBounds(){
		
		return new Rectangle(x, y, playerSize, playerSize);
	}

}
