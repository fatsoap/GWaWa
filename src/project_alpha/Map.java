package project_alpha;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Map {
	private int zx=100;
	private int zy=100;
	private int[][][] map_coordinate = {
			{ {550,330} },  //D
			{ {650,330},{50,330} },  //DA
			{ {650,330},{50,330},{350,30} },  //DAW
			{ {650,330},{50,330},{350,30},{350,630} },  //DAWS
			//{ {650,330},{50,330},{350,30},{0,0},{650,630},{350,630},{50,630},{50,30},{650,30} }
			{ {670,330},{30,330},{350,10},{0,0},{670,650},{350,650},{30,650},{30,10},{670,10} }
			};    //DAWsCXZQE
	
	public Map() {}
	
	public void draw(Graphics2D g, int location) {
		
		if(location>=1 && location<=4) {  //*****************************
			for(int k=0; k<location; k++) {
				for(int i=0; i<3; i++) {
					for(int j=0; j<3; j++) {
						g.setColor(Color.white);
						g.fillRect(j*zx +map_coordinate[location-1][k][0],
								   i*zy +map_coordinate[location-1][k][1],
								   zx, zy);						
						g.setStroke(new BasicStroke(3));
						g.setColor(Color.black);
						g.drawRect(j*zx +map_coordinate[location-1][k][0],
								   i*zy +map_coordinate[location-1][k][1],
								   zx, zy);
					}
				}
			}//k=>0~location			
		}//location=>1~4
		if(location==5) { //***************************************
			for(int k=0;k<9; k++) {
				if(k!=3) {
					for(int i=0; i<3; i++) {
						for(int j=0; j<3; j++) {
							g.setColor(Color.white);
							g.fillRect(j*zx +map_coordinate[location-1][k][0],
									   i*zy +map_coordinate[location-1][k][1],
									   zx, zy);
							g.setStroke(new BasicStroke(3));
							g.setColor(Color.black);
							g.drawRect(j*zx +map_coordinate[location-1][k][0],
									   i*zy +map_coordinate[location-1][k][1],
									   zx, zy);
						}
					}  
				}//avoid k=3 => S=4	
			}//k=>0~7		
		}//location=>5
		
	}//draw.method
}

