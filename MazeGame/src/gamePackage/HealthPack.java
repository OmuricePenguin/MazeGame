package gamePackage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class HealthPack extends GameObject{

	public HealthPack(int x, int y, ID id) {
		super(x, y, id);

	}


	public void tick() {
		
	}


	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(x, y, 50, 50);
		g.setColor(Color.RED);
		g.fillRect(x+7, y+20, 35, 10);
		g.fillRect(x+20, y+7, 10, 35);
	
	}


	public Rectangle getBounds() {

		return new Rectangle(x, y, 50, 50);
	}

}
