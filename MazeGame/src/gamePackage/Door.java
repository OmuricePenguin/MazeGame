package gamePackage;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Door extends GameObject{
	
	private Handler handler;
	
	public Door(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler=handler;
		
	
		
	}

	
	public void tick() {
		
		collision();
		
	}
	
	private void collision(){
		for(int i=0; i<handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getID() == ID.Player){
				if(this.getBounds().intersects(tempObject.getBounds())){
					Game.LEVEL++;
					handler.loadLevel();

											
				}
			}
		}
	}

	public void render(Graphics g) {
		
		g.setColor(Color.darkGray);
		g.fillRect(x, y, Game.wallSize+1, 80);
		g.setColor(Color.black);
		for(int i = this.x; i<Game.wallSize; i +=10) g.fillRect(this.x+i, this.y, 5, 80);
	}


	public Rectangle getBounds() {
		
		return new Rectangle(x, y, Game.wallSize+2, 80);
		
	}

}
