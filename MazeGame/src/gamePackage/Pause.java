package gamePackage;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

import gamePackage.Game.STATE;

public class Pause extends MouseAdapter{
	
	private Game game;
	private boolean help = false;
	
	public Pause(Game game){
		this.game=game;
	}
	
	public void tick(){
		
	}
	
	public void render(Graphics g){
		if(game.gameState==STATE.Game&&Game.paused==false){
			Font font = new Font("arial", 1, 30);
			g.setFont(font);
			g.setColor(Color.GREEN);
			g.fillRect(500, 30, 100, 50);
			g.setColor(Color.YELLOW);
			g.drawString("Pause", 505, 65);
		}
		else if(game.gameState==STATE.Game&&Game.paused){
			g.setColor(Color.DARK_GRAY);
			g.fillRect(50, 50, 540, 340);
			if(help==false){
				Font font = new Font("arial", 1, 50);
				Font font2 = new Font("arial", 1, 30);
				g.setFont(font);
			
				g.setColor(Color.YELLOW);
				g.drawString("Paused", 230, 100);
				g.setFont(font2);
				g.drawRect(210, 120, 200, 64);
				g.drawString("Continue", 250, 160);
	
				g.drawRect(210, 210, 200, 64);
				g.drawString("Help", 280, 250);
				g.drawRect(210, 300, 200, 64);
				g.drawString("Quit", 280, 340);
			}
			else if(help== true){
				Font font = new Font("arial", 1, 50);
				Font font2 = new Font("arial", 1, 30);
				g.setFont(font);
				g.setColor(Color.YELLOW);
				g.drawString("Instructions", 170, 90);
				g.setFont(font2);
				g.drawString("You are the white square", 130, 150);
				g.drawString("Use W, A, S, D to move and dodge ", 80, 200);
				g.drawString("the enemies", 220, 240);
				g.drawRect(210, 300, 200, 64);
				g.drawString("Back", 280, 340);
			}
		}
	}
	
	private boolean mouseOver(int mx, int my, int x,int y, int width, int height){
		if(mx>x&&mx<x+width){
			if(my>y&&my<y+height){
				return true;
			}else return false;
		}else return false;
	}
	
	public void mousePressed(MouseEvent e){
		
	}
	
	public void mouseReleased(MouseEvent e){
		int mx=e.getX();
		int my=e.getY();
		if(game.gameState==STATE.Game){
			if(Game.paused == false){
				if(mouseOver(mx, my, 500, 30, 100, 50)){
					Game.paused=true;
					return;
				}
			}
			else if(Game.paused){
				if(help == false){
					if(mouseOver(mx, my, 210, 120, 200, 64))Game.paused=false;
					else if(mouseOver(mx, my, 210, 210, 200, 64)){
						help = true;
					}
					else if(mouseOver(mx, my, 210, 300, 200, 64))System.exit(1);
				}
				else if(help==true){
					if(mouseOver(mx, my, 210, 300, 200, 64))help=false;
				}
			}
		}
		
	}
}
