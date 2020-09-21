package project_alpha;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Boss_blood {
	//g.fillRect(20, 940, 950, 50);
	//g.fillRect(25 + i*10, 943, 8, 44);
	//g.fillRect(25 + i*10, 943, 8, 44);
	public Boss_blood() {}
	
	public void draw(Graphics2D g, int blood) {
		g.setColor(Color.black);
		g.fillRect(20, 910, 950, 50);
		for(int i=0;i<94;i++) {
			g.setStroke(new BasicStroke(3));
			g.setColor(Color.white);
			g.fillRect(25 + i*10, 913, 8, 44);
		}
		for(int i=0;i<blood;i++) {   
			g.setStroke(new BasicStroke(3));
			g.setColor(Color.red);
			g.fillRect(25 + i*10, 913, 8, 44);
		}
		
		g.setColor(Color.black);
		g.fillRect(20, 935, 950, 25);
		for(int i=0;i<94;i++) {
			g.setStroke(new BasicStroke(3));
			g.setColor(Color.white);
			g.fillRect(25 + i*10, 938, 8, 22);
		}
		for(int i=0;i<blood-94;i++) {   
			g.setStroke(new BasicStroke(3));
			g.setColor(Color.red);
			g.fillRect(25 + i*10, 938, 8, 22);
		}
		
	}  //5+(8+2)*94+5------950***//940+(3+44+3)---990**10
}
