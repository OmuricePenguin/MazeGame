package gamePackage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Wall extends GameObject{
	
	Handler handler;
	
	public Wall(int x, int y, ID id){
		super(x, y, id);
		
		this.width=40;
		this.height=40;
	}

	
	public void tick() {
		
		
	}

	
	public void render(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(x, y, 40, 40);
		
	}

	
	public Rectangle getBounds() {
		return new Rectangle(x, y, 40, 40);
	}

}
