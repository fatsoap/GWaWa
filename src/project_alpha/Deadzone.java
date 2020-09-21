package project_alpha;

import java.awt.Graphics2D;
import java.util.Random;

import javax.swing.ImageIcon;

public class Deadzone {
	private int zx=100;
	private int zy=100;
	private Random ran;
	private int[] safe_zone1 = new int[2];
	private int[] safe_zone2 = new int[2];
	private int[] safe_zone3 = new int[2];
	private  ImageIcon mine_img = new ImageIcon(getClass().getClassLoader().getResource("mine.png"));
	
	private int[][][] zone_coordinate = {
	{ {550,330} },  //D
	{ {650,330},{50,330} },  //DA
	{ {650,330},{50,330},{350,30} },  //DAW
	{ {650,330},{50,330},{350,30},{350,630} },  //DAWS
	//{ {650,330},{50,330},{350,30},{0,0},{650,630},{350,630},{50,630},{50,30},{650,30} }
	{ {670,330},{30,330},{350,10},{0,0},{670,650},{350,650},{30,650},{30,10},{670,10} }
	};    //DAWsCXZQE

	
	public Deadzone() {
		ran = new Random();
	}
	
	/*********************
			Q W E
			8 3 9
			A S D
			2 4 1
			Z X C
			7 6 5
	*********************/
	public void draw(Graphics2D g, int location, int clr) {			
		if(location>=1 && location<=4) {  //*****************************
			for(int k=0; k<location; k++) {
				for(int i=0; i<3; i++) {
					int place = 3*(3-i)-2;
					for(int j=0; j<3; j++) {
						if(!check_zone(place,k+1)) {
							if(clr==3 ||clr==1)g.drawImage(mine_img.getImage(),
									 j*zx +zone_coordinate[location-1][k][0]+3,
									 i*zy +zone_coordinate[location-1][k][1]+3, 
									 null);	
						}
						place++;
					}
				}
			}//k=>0~location			
		}//location=>1~4
		if(location==5) { //***************************************
			for(int k=0;k<9; k++) {
				if(k!=3) {
					for(int i=0; i<3; i++) {
						int place = 3*(3-i)-2;
						for(int j=0; j<3; j++) {
							if(!check_zone(place,k+1)) {
								if(clr==3 ||clr==1)g.drawImage(mine_img.getImage(),
									     j*zx +zone_coordinate[location-1][k][0]+3,
									     i*zy +zone_coordinate[location-1][k][1]+3, 
									     null);	
							}
							place++;
						}
					}
				}//avoid k==3  => S=4
			}//k=>0~7			
		}//location=>5
	}//draw.method

	
	
	public void randomMine(int location,int mode) {
		safe_zone1[0] = ran.nextInt(9)+1;
		safe_zone2[0] = ran.nextInt(9)+1;
		safe_zone3[0] = ran.nextInt(9)+1;
		if(location != 5) {
			safe_zone1[1] = ran.nextInt(location)+1;
			safe_zone2[1] = ran.nextInt(location)+1;
			safe_zone3[1] = ran.nextInt(location)+1;
		}else if(location == 5) {
			int k = ran.nextInt(8)+1;
			if(k==4)safe_zone1[1] = 9;
			else safe_zone1[1] = k;
			k = ran.nextInt(8)+1;
			if(k==4)safe_zone2[1] = 9;
			else safe_zone2[1] = k;
			k = ran.nextInt(8)+1;
			if(k==4)safe_zone3[1] = 9;
			else safe_zone3[1] = k;			
		}
		if(mode==2) {
			safe_zone3[0]=safe_zone1[0];
			safe_zone3[1]=safe_zone1[1];
		}else if (mode==3) {
			safe_zone2[0]=safe_zone1[0];
			safe_zone2[1]=safe_zone1[1];
			safe_zone3[0]=safe_zone1[0];
			safe_zone3[1]=safe_zone1[1];
		}
	}
	
	public boolean check_player (int[] player_place) {
		boolean safe = false;
		
		if      (safe_zone1[0] == player_place[0] && safe_zone1[1] == player_place[1]) safe = true;
		else if (safe_zone2[0] == player_place[0] && safe_zone2[1] == player_place[1]) safe = true;
		else if (safe_zone3[0] == player_place[0] && safe_zone3[1] == player_place[1]) safe = true;
		else     safe = false;
		
		return safe;
	}
	
	private boolean check_zone(int place,int zone) {
		boolean safe = false;  //true=safe

		if(place==safe_zone1[0] && zone==safe_zone1[1]) safe =true;
		else if(place==safe_zone2[0] && zone==safe_zone2[1]) safe =true;
		else if(place==safe_zone3[0] && zone==safe_zone3[1]) safe =true;
		else safe=false;
		return safe;
	}

}