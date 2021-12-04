package gamePackage;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import gamePackage.Game.STATE;

public class Menu extends MouseAdapter{
	
	private Game game;
	private Handler handler;
	public Menu(Game game, Handler handler){
		this.game=game;
		this.handler=handler;
	}
	
	public void tick(){
		
	}
	
	public void render(Graphics g){
		if(game.gameState==STATE.Menu){
			Font font=new Font("arial", 1, 50);
			Font font2= new Font("arial", 1, 30);
			g.setFont(font);
			
			g.setColor(Color.WHITE);
			
			g.drawString("Menu", 240, 50);
			g.setFont(font2);
			g.drawRect(210, 100, 200, 64);
			g.drawString("Play", 280, 140);

			g.drawRect(210, 200, 200, 64);
			g.drawString("Help", 280, 240);
			g.drawRect(210, 300, 200, 64);
			g.drawString("Quit", 280, 340);
		}else if(game.gameState==STATE.Help){
			Font font=new Font("arial", 1, 50);
			Font font2= new Font("arial", 1, 30);
			g.setFont(font);
			
			g.setColor(Color.WHITE);
			
			g.drawString("Instructions", 170, 50);
			g.setFont(font2);
			g.drawString("You are the white square", 130, 120);
			g.drawString("Use W, A, S, D to move and dodge ", 80, 200);
			g.drawString("the enemies", 220, 240);
			g.drawRect(210, 300, 200, 64);
			g.drawString("Back", 280, 340);
		}
		
	}
	
	public void mousePressed(MouseEvent e){
		
	}
	
	
	public void mouseReleased(MouseEvent e){
		int mx= e.getX();
		int my= e.getY();
		if(game.gameState==STATE.Menu){
			if(mouseOver(mx, my, 210, 100, 200, 64)){
				game.gameState=STATE.Game;
				handler.loadLevel();
			}
			
			if(mouseOver(mx, my, 210, 300, 200, 64)){
				System.exit(1);
			}
			
			if(mouseOver(mx, my, 210, 200, 200, 64)){
				game.gameState=STATE.Help;
			}
		}
		if(game.gameState==STATE.Help&&mouseOver(mx, my, 210, 300, 200, 64))game.gameState=STATE.Menu;
	}
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height){
		if(mx>x&&mx<x+width){
			if(my>y&&my<y+height){
				return true;
			}else return false;
		}else return false;
	}

}
