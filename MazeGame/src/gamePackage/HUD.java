package gamePackage;

import java.awt.Color;
import java.awt.Graphics;

public class HUD {
	
	public static int HEALTH = 100;
	
	public void tick(){
		HEALTH=Game.clamp(HEALTH, 0, 100);
	}
	
	public void render(Graphics g){
		g.setColor(Color.gray);
		g.fillRect(60, 60, 200, 30);
		g.setColor(Color.green);
		g.fillRect(60, 60, HEALTH * 2, 30);
		g.setColor(Color.white);
		g.drawRect(60, 60, 200, 30);
		
	}

}
