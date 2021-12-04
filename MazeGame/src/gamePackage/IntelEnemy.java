package gamePackage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class IntelEnemy extends GameObject{
	
	Handler handler;
	private static int lastX, lastY;
	
	public IntelEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler=handler;
	}


	public void tick() {
		
		lastX=x;
		lastY=y;
		
		x+=velX; y+=velY;
		collsion();
		
	}
	
	private void collsion(){
		for(int i = 0; i<handler.object.size(); i++){
			GameObject tempObject= handler.object.get(i);
			if(tempObject.getID()==ID.Wall){
				if(this.getBounds().intersects(tempObject.getBounds())){
					
					if(IntelEnemy.lastX + 32> tempObject.x && IntelEnemy.lastX<tempObject.x+tempObject.height){
						if(IntelEnemy.lastY>=tempObject.y+height)
							this.setY(tempObject.y + tempObject.height +1);
						else if(IntelEnemy.lastY+32<=tempObject.y)
							this.setY(tempObject.y-32-1);
					}
					
					
				if(IntelEnemy.lastY + 32>= tempObject.y && IntelEnemy.lastY<= tempObject.y+tempObject.height){
						if(IntelEnemy.lastX >=tempObject.x +tempObject.width)
							this.setX(tempObject.x+tempObject.width + 1);
						else if(IntelEnemy.lastX+32 <=tempObject.x)
							this.setX(tempObject.x -32-1);
					}
				}
			}
			if(tempObject.getID()==ID.Player){
				if(tempObject.x+Player.playerSize/2 +1<x+16)setVelX(-2);
				if(tempObject.x+Player.playerSize/2+1>x+16)setVelX(2);
				if(tempObject.y+Player.playerSize/2+1<y+16)setVelY(-2);
				if(tempObject.y+Player.playerSize/2+1>y+16)setVelY(2);
				if(getBounds().intersects(tempObject.getBounds())){
					HUD.HEALTH-=1;
					if(HUD.HEALTH<=0){
						Game.LEVEL=1;
						HUD.HEALTH=100;
						handler.loadLevel();
					}
				}
			}
		}
	}

	public void render(Graphics g) {

		g.setColor(Color.GREEN);
		g.fillRect(x, y, 32, 32);
		g.setColor(Color.RED);
		g.fillRect(x+12, y+12, 8, 8);
		
	}


	public Rectangle getBounds() {

		return new Rectangle(x, y, 32, 32);
	}

}
