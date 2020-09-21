package project_alpha;

import java.awt.Color;
import java.awt.Graphics2D;

import javax.swing.ImageIcon;

//import javax.swing.ImageIcon;

public class Player {
	private int zx=100,zy=100;
	private int[][][] player__coordinate = {
			{ {550,330} },  //D
			{ {650,330},{50,330} },  //DA
			{ {650,330},{50,330},{350,30} },  //DAW
			{ {650,330},{50,330},{350,30},{350,630} },  //DAWS
			//{ {650,330},{50,330},{350,30},{0,0},{650,630},{350,630},{50,630},{50,30},{650,30} }
			{ {670,330},{30,330},{350,10},{0,0},{670,650},{350,650},{30,650},{30,10},{670,10} }
			};    //DAWsCXZQE
	private int  playerX,playerY;

	private  ImageIcon player_img = new ImageIcon(getClass().getClassLoader().getResource("player.png"));
	
	public Player() {}	
	
	public void draw(Graphics2D g, int location, int[] player_place) {
		playerX = player__coordinate[location-1][player_place[1]-1][0];
		playerY = player__coordinate[location-1][player_place[1]-1][1];
		if(player_place[0]<4) { 
			playerX += (player_place[0]-1)*zx;
			playerY += 2*zy;
		}
		else if(player_place[0]>3 && player_place[0]<7) {
			playerX += (player_place[0]-4)*zx;
			playerY += 1*zy;
		}
		else { 
			playerX += (player_place[0]-7)*zx;
			playerY += 0*zy ;
		}
		g.setColor(Color.blue);
		//g.drawRect(playerX+30, playerY+30, 40, 40);
		g.drawImage(player_img.getImage(), playerX, playerY, null);
	}
		
}
