package project_alpha;

import java.awt.Graphics2D;

import javax.swing.ImageIcon;

public class Background {
	private  ImageIcon lv0_img = new ImageIcon(getClass().getClassLoader().getResource("lv0.png"));
	private  ImageIcon castle_img = new ImageIcon(getClass().getClassLoader().getResource("castle.png"));
	
	private  ImageIcon practice_img = new ImageIcon(getClass().getClassLoader().getResource("practice.png"));
	private  ImageIcon normal_img = new ImageIcon(getClass().getClassLoader().getResource("normal.png"));
	private  ImageIcon hard_img = new ImageIcon(getClass().getClassLoader().getResource("hard.png"));
	private  ImageIcon chaos_img = new ImageIcon(getClass().getClassLoader().getResource("chaos.png"));
	
	private  ImageIcon lv1_img = new ImageIcon(getClass().getClassLoader().getResource("lv_1.png"));
	private  ImageIcon lv2_img = new ImageIcon(getClass().getClassLoader().getResource("lv_2.png"));
	private  ImageIcon lv3_img = new ImageIcon(getClass().getClassLoader().getResource("lv_3.png"));
	private  ImageIcon lv4_img = new ImageIcon(getClass().getClassLoader().getResource("lv_4.png"));
	private  ImageIcon lv5_img = new ImageIcon(getClass().getClassLoader().getResource("lv_5.png"));

	private  ImageIcon help1_img = new ImageIcon(getClass().getClassLoader().getResource("help1.png"));
	private  ImageIcon help2_img = new ImageIcon(getClass().getClassLoader().getResource("help2.png"));
	private  ImageIcon help3_img = new ImageIcon(getClass().getClassLoader().getResource("help3.png"));
	private  ImageIcon help4_img = new ImageIcon(getClass().getClassLoader().getResource("help4.png"));
	private  ImageIcon help5_img = new ImageIcon(getClass().getClassLoader().getResource("help5.png"));
	
	private  ImageIcon story1_img = new ImageIcon(getClass().getClassLoader().getResource("story1.png"));
	private  ImageIcon story2_img = new ImageIcon(getClass().getClassLoader().getResource("story2.png"));
	private  ImageIcon story3_img = new ImageIcon(getClass().getClassLoader().getResource("story3.png"));
	
	private  ImageIcon author_img = new ImageIcon(getClass().getClassLoader().getResource("author.png"));
	
	private  ImageIcon complete1_img = new ImageIcon(getClass().getClassLoader().getResource("complete1.png"));
	private  ImageIcon complete2_img = new ImageIcon(getClass().getClassLoader().getResource("complete2.png"));
	private  ImageIcon complete3_img = new ImageIcon(getClass().getClassLoader().getResource("complete3.png"));
	private  ImageIcon complete4_img = new ImageIcon(getClass().getClassLoader().getResource("complete4.png"));
	
	
	public Background () {}
	
	public void draw(Graphics2D g, int location, int mode) {
		if(location>0&&location<=10) {
			if(mode==0)g.drawImage(practice_img.getImage(), 0,0,null);
			if(mode==1)g.drawImage(normal_img.getImage(), 0,0,null);
			if(mode==2)g.drawImage(hard_img.getImage(), 0,0,null);
			if(mode==3)g.drawImage(chaos_img.getImage(), 0,0,null);
		}
		if(location==0) {
			g.drawImage(lv0_img.getImage(), 0,0,null);
		}else if(location==10) {
			g.drawImage(castle_img.getImage(), 0,0,null);
		}else if(location==1) {
			g.drawImage(lv1_img.getImage(), 0,0,null);
		}else if(location==2) {
			g.drawImage(lv2_img.getImage(), 0,0,null);
		}else if(location==3) {
			g.drawImage(lv3_img.getImage(), 0,0,null);
		}else if(location==4) {
			g.drawImage(lv4_img.getImage(), 0,0,null);
		}else if(location==5) {
			g.drawImage(lv5_img.getImage(), 0,0,null);
		}else if(location==60) {
			g.drawImage(help1_img.getImage(), 0,0,null);
		}else if(location==61) {
			g.drawImage(help2_img.getImage(), 0,0,null);
		}else if(location==62) {
			g.drawImage(help3_img.getImage(), 0,0,null);
		}else if(location==63) {
			g.drawImage(help4_img.getImage(), 0,0,null);
		}else if(location==64) {
			g.drawImage(help5_img.getImage(), 0,0,null);
		}else if(location==70) {
			g.drawImage(story1_img.getImage(), 0,-20,null);
		}else if(location==71) {
			g.drawImage(story2_img.getImage(), 0,0,null);
		}else if(location==72) {
			g.drawImage(story3_img.getImage(), 0,-20,null);
		}else if(location==80) {
			g.drawImage(author_img.getImage(), 0,0,null);			
		}else if(location==100) {
			g.drawImage(complete1_img.getImage(), 0,0,null);
		}else if(location==101) {
			g.drawImage(complete2_img.getImage(), 0,0,null);
		}else if(location==102) {
			g.drawImage(complete3_img.getImage(), 0,0,null);
		}else if(location==103) {
			g.drawImage(complete4_img.getImage(), 0,0,null);
		}
	}
}
