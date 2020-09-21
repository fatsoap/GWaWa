package project_alpha;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public  class Gameplay extends JPanel implements KeyListener, ActionListener{
	
	private Timer timer;
	private Background bg;
	private Map map;
	private Deadzone deadzone;
	private Player player;
	private Boss_blood boss_blood;
	
	private boolean _l1 = false;
	private boolean _o = false;
	private boolean _l2 = false;
	private boolean _i = false;
	private boolean _t = false;
	private boolean _a = false;
	private boolean _super = false;
	
	private boolean play = false;
	private boolean lv_complete = false;
	private boolean dead = false;
	private boolean entered = false;
	private boolean atkTime = false;
	private boolean inSafeZone = true;
	
	private int attack = 1;
	private int blood = 94;
	private int[] player_place = {5,1};
	private int nowTime = 0;
	private int ability = 0;
	private int[] shop= {0,0,0};
	private boolean _shop=false;
	
	private int location = 0; 
	//title=>0, lv1=>1, lv2=>2, lv3=>3, lv4=>4, boss=>5, 
	//help=>60,61,62,63,64, story=>70,71,72, author=>80, complete=>100,101,102,103	
	private int option = 0;
	private int lv_option = 0;
	private int lv_mode = 0;
	private  ImageIcon option_icon_img = new ImageIcon(getClass().getClassLoader().getResource("lv_option_icon.png"));
	private  ImageIcon won_img = new ImageIcon(getClass().getClassLoader().getResource("won.png"));
	private  ImageIcon love_img = new ImageIcon(getClass().getClassLoader().getResource("love.png"));
	private  ImageIcon victory_img = new ImageIcon(getClass().getClassLoader().getResource("victory.png"));
	private  ImageIcon enter_img = new ImageIcon(getClass().getClassLoader().getResource("enter.png"));
	private  ImageIcon space_img = new ImageIcon(getClass().getClassLoader().getResource("space.png"));
	private  ImageIcon restart_img = new ImageIcon(getClass().getClassLoader().getResource("restart.png"));
	private  ImageIcon title_img = new ImageIcon(getClass().getClassLoader().getResource("title.png"));
	private  ImageIcon shop_img = new ImageIcon(getClass().getClassLoader().getResource("shop.png"));
	private  ImageIcon shop1_img = new ImageIcon(getClass().getClassLoader().getResource("shop1.png"));
	private  ImageIcon shop2_img = new ImageIcon(getClass().getClassLoader().getResource("shop2.png"));
	private  ImageIcon shop3_img = new ImageIcon(getClass().getClassLoader().getResource("shop3.png"));
	private  ImageIcon shop4_img = new ImageIcon(getClass().getClassLoader().getResource("shop4.png"));
	private  ImageIcon shop5_img = new ImageIcon(getClass().getClassLoader().getResource("shop5.png"));
	private  ImageIcon shop7_img = new ImageIcon(getClass().getClassLoader().getResource("shop7.png"));
	private  ImageIcon shop8_img = new ImageIcon(getClass().getClassLoader().getResource("shop8.png"));
	
	public Gameplay() {
		bg = new Background();
		map = new Map();
		deadzone = new Deadzone();
		player = new Player();
		boss_blood = new Boss_blood();
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(8,this);
		timer.start();
	}
	
	public void paint(Graphics g) {
		bg.draw((Graphics2D)g,location,lv_mode);
		//******************OUT_LEVEL***********************
		if(location==0) draw_title_option((Graphics2D) g);
		else if (location==10) draw_mode_option((Graphics2D)g);	
		else if (location>=60) draw_next_page_option((Graphics2D)g);
		//******************IN_LEVEL************************	
		else if(location>0 && location <6) {	
			//****************DRAW MAP**********************
			map.draw((Graphics2D)g,location);			
			//************DEADZONE MACHINE******************			
			if(play) {
				deadzone_machine((Graphics2D) g);	
			}			
			//**********DRAW PLAYER & BLOOD*****************		
			player.draw((Graphics2D)g, location, player_place);
			boss_blood.draw((Graphics2D)g, blood);
			//************DRAW LEVEL OPTION*****************
			draw_lv_option((Graphics2D)g);
		}	//**********************************************
		if(_shop) {
			draw_shop((Graphics2D)g);
		}
		//***************WIN STRING*************************
		if(blood <= 0 && location!=5) {
			play = false;
			lv_complete = true;	
			g.drawImage(victory_img.getImage(), 400, 650,null);
		}else if(blood <= 0 && location ==5) {
			play = false;
			lv_complete = true;
			g.drawImage(won_img.getImage(), 140, 300, null);
			g.drawImage(love_img.getImage(), 80, 500, null);
		}
		//**************************************************
	}

	public void actionPerformed(ActionEvent e) {
		timer.start();
		repaint();
	}

	public void keyTyped(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {}
	
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			initial_lv();
			back_to_title();
		}
		
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (location == 0 ) {
				if (option == 0 ) {
					location = 10;
				}else if (option == 1) {
					location = 60;  //help
				}else if (option == 2) {
					location = 70;  //story
				}else if (option == 3) {
					location = 80;  //author
				}
			}else if (location==10) {
				location = 1;
				entered = true;
			}else if(location >0 && location <=5 && !play){
				if (lv_complete && !dead && location!=5 && !_shop) {
					ran_shop();
					_shop=true;
				}else if (lv_complete && !dead && location!=5 && _shop) {
					_shop=false;
					ability=shop[lv_option];
					initial_lv(); 
					location+=1;
				}
				else if(lv_complete && !dead && location==5) {
					initial_lv();
					if(lv_mode!=0)location = 100;
					else back_to_title();
				}
				if(!play && !lv_complete && dead && lv_option==0) {
					ability=0;
					initial_lv();
				}
				if(!play && !lv_complete && dead && lv_option==1) {
					initial_lv();
					back_to_title();
				}			
				
			}else if(location>=60){
				if(location!=64&&location!=72&&location!=80&&location!=103)location+=1;
				else back_to_title();
			}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			if(!play && !lv_complete && !dead && entered) {	
				restart_lv();
			}
			if (play && atkTime && !_super) blood = blood-attack;	
			if (play && atkTime && _super) blood = blood-500;
		}	
		//**************UP_KEY*******************************
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			if(dead && lv_option==1) lv_option = 0;
			if (location == 0) {
				option -= 1;
				if (option == -1) option = 3;
			}else if (location == 10) {
				lv_mode -= 1;
				if (lv_mode == -1) lv_mode = 3;
			}
		}//************DOWN_KEY******************************
		if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			if(dead && lv_option==0) lv_option = 1;
			if (location == 0) {
				option += 1;
				if (option == 4) option = 0;	
			}else if (location == 10) {
				lv_mode += 1;
				if (lv_mode == 4) lv_mode = 0;
			}
		}//************SHOP_KEY******************************
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			if(_shop&&lv_option!=0) lv_option -=1;
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if(_shop&&lv_option!=2) lv_option +=1;
		}
		//**************************************************
		//MOVE	
		if(play && !lv_complete && !dead && !atkTime) {
			if(e.getKeyCode() == KeyEvent.VK_UP) movePlayer(1); //1
			if(e.getKeyCode() == KeyEvent.VK_DOWN) movePlayer(2);  //2
			if(e.getKeyCode() == KeyEvent.VK_LEFT) movePlayer(3);  //3	
			if(e.getKeyCode() == KeyEvent.VK_RIGHT) movePlayer(4);  //4			
			if(e.getKeyChar() == 'd') movePlayer(5);  //5
			if(e.getKeyChar() == 'c') movePlayer(6);  //6	
			if(e.getKeyChar() == 'x') movePlayer(7);  //7			
			if(e.getKeyChar() == 'z') movePlayer(8);  //8
			if(e.getKeyChar() == 'a') movePlayer(9);  //9
			if(e.getKeyChar() == 'q') movePlayer(10);  //10
			if(e.getKeyChar() == 'w') movePlayer(11);  //11
			if(e.getKeyChar() == 'e') movePlayer(12);  //12	
			if(e.getKeyChar() == 's') movePlayer(13);  //13
		}
		if(location == 0) {
			if(e.getKeyChar() == 'l') decoder(19);  
			if(e.getKeyChar() == 'a') decoder(55); 
			if(e.getKeyChar() == 't') decoder(37);
			if(e.getKeyChar() == 'i') decoder(26); 			
			if(e.getKeyChar() == 'o') decoder(2);
		}
	}
	
	public void movePlayer(int a) {
		if((player_place[0]) < 7 && a == 1) player_place[0] += 3; //up
		if((player_place[0]) > 3 && a == 2) player_place[0] -= 3; //down
		if((player_place[0])%3 != 1 && a == 3) player_place[0] -= 1;  //left
		if((player_place[0])%3 != 0 && a == 4) player_place[0] += 1;  //right
		if(location >= 2 && a == 5)  player_place[1] = 1;  //D
		if(location == 5 && a == 6)  player_place[1] = 5;  //C
		if(location == 5 && a == 7)  player_place[1] = 6;  //X
		if(location == 5 && a == 8)  player_place[1] = 7;  //Z
		if(location >= 2 && a == 9)  player_place[1] = 2;  //A
		if(location == 5 && a == 10) player_place[1] = 8;  //Q
		if(location >= 3 && a == 11) player_place[1] = 3;  //W
		if(location == 5 && a == 12) player_place[1] = 9;  //E
		if(location == 4 && a == 13) player_place[1] = 4;  //S
	}

	private void decoder(int code) {
		if(!_l1&&!_o&&!_l2&&!_i&&!_t&&!_a&&code==19) _l1=true;
		if(_l1&&_o&&!_l2&&!_i&&!_t&&!_a&&code==19) _l2=true;
		if(_l1&&_o&&_l2&&_i&&_t&&!_a&&code==55) _a=true;
		if(_l1&&_o&&_l2&&_i&&!_t&&!_a&&code==37) _t=true;
		if(_l1&&_o&&_l2&&!_i&&!_t&&!_a&&code==26) _i=true;
		if(_l1&&!_o&&!_l2&&!_i&&!_t&&!_a&&code==2) _o=true;		
	}
	
	
	private void draw_title_option(Graphics2D g) {
		g.setFont(new Font(null,Font.BOLD,20));
		g.setColor(Color.black);
		g.drawString("Press Enter to Select", 420, 950);
		if(_l1&&_o&&_l2&&_i&&_t&&_a) _super=true;
		g.drawImage(option_icon_img.getImage(), 340, 700 + option*55,null);
	}
	private void draw_mode_option(Graphics2D g) {
		g.setFont(new Font(null,Font.BOLD,20));
		g.setColor(Color.black);
		g.drawString("Press Enter to Select", 390, 950);
		g.drawImage(option_icon_img.getImage(), 300, 700 + lv_mode*55,null);
	}	
	private void draw_next_page_option(Graphics2D g) {
		g.setFont(new Font(null,Font.BOLD,20));
		if(location!=80)g.setColor(Color.black);
		else g.setColor(Color.gray);
		g.drawString("Press Enter to Next Page", 390, 950);
	}
	private void  ran_shop() {
		Random ran = new Random();
		shop[0]=ran.nextInt(7)+1;
		shop[1]=ran.nextInt(7)+1;
		shop[2]=ran.nextInt(7)+1;
		for(int  i=0;i<3;i++) {
			if(shop[i]==6)shop[i]=8;
			if(shop[i]==7)shop[i]=9;
			System.out.println(shop[i]);
		}
	}
	private void draw_shop(Graphics2D g) {
		//attack*2(1, react_time*1.5(2, atk_time*1.5(3, super(5, live+2(9, live+1(8, 
		//boss_blood*2(4
		g.drawImage(shop_img.getImage(), 0, 0,null);
		for(int i=0; i<3; i++) {
		if(shop[i]==1)g.drawImage(shop1_img.getImage(), 0+220*i, 600,null);
		if(shop[i]==2)g.drawImage(shop2_img.getImage(), 0+220*i, 600,null);
		if(shop[i]==3)g.drawImage(shop3_img.getImage(), 0+220*i, 600,null);
		if(shop[i]==4)g.drawImage(shop4_img.getImage(), 0+220*i, 600,null);
		if(shop[i]==5)g.drawImage(shop5_img.getImage(), 0+220*i, 600,null);
		if(shop[i]==8)g.drawImage(shop7_img.getImage(), 0+220*i, 600,null);
		if(shop[i]==9)g.drawImage(shop8_img.getImage(), 0+220*i, 600,null);
		}
		g.drawRect(lv_option*220, 600, 220, 300);
	}
	private void deadzone_machine(Graphics2D g) {
		int t=180;
		int react_time=80;
		if(location == 1 && lv_mode==1) t = 180;
		else if(location == 2 && lv_mode==1) t = 200;
		else if(location == 3 && lv_mode==1) t = 230;
		else if(location == 4 && lv_mode==1) t = 240;
		else if(location == 5 && lv_mode==1) t = 260;
		else if(location == 1 && lv_mode==2) t = 170;
		else if(location == 2 && lv_mode==2) t = 185;
		else if(location == 3 && lv_mode==2) t = 215;
		else if(location == 4 && lv_mode==2) t = 225;
		else if(location == 5 && lv_mode==2) t = 245;
		else if(location == 1 && lv_mode==3) t = 160;
		else if(location == 2 && lv_mode==3) t = 180;
		else if(location == 3 && lv_mode==3) t = 210;
		else if(location == 4 && lv_mode==3) t = 220;
		else if(location == 5 && lv_mode==3) t = 240;
		if(ability==2) {
			t=t+100;
			react_time=80;
		}
		if(ability==3) {
			t=t+40;
			react_time=120;
		}
		if(nowTime == 0) {
			deadzone.randomMine(location,lv_mode);
		}else if(nowTime < t-react_time) {
			atkTime = false;
			if((nowTime/20) %2 == 0) {
				deadzone.draw((Graphics2D)g, location, 1);
			}else if((nowTime/20)%2 == 1) {
				deadzone.draw((Graphics2D)g, location, 2);
			}								
		}else if(nowTime >= t-react_time) {
			deadzone.draw((Graphics2D)g, location, 3);			
		}if(nowTime == t-react_time) {
			deadzone.draw((Graphics2D)g, location, 3);
			inSafeZone = deadzone.check_player(player_place);
			if(inSafeZone) {
				atkTime = true;
			}else if(lv_mode!=0&&ability>6){
				ability-=1;
				if(ability==6) {
					play =  false;
					dead  =  true;
				}
			}else if(lv_mode!=0&&ability<5){
				play =  false;
				dead  =  true;
			}
		}
		nowTime = nowTime + 1;
		nowTime = nowTime%t;
		
	}
	private void draw_lv_option(Graphics2D g) {
		g.setFont(new Font(null,Font.BOLD,50));
		if(!play && lv_complete && !dead) {
			g.setColor(Color.green);
			if(location != 5) g.drawImage(enter_img.getImage(), 185, 710 ,null);
			if(location==5)draw_next_page_option((Graphics2D) g);
		}else if(!play && !lv_complete && !dead) {
			g.setColor(Color.green);
			g.drawImage(space_img.getImage(), 280, 650,null);
		}else if(!play && !lv_complete && dead) {
					g.drawImage(restart_img.getImage(), 305, 640 ,null);
					g.drawImage(title_img.getImage(), 305, 700 ,null);
					g.drawImage(option_icon_img.getImage(), 240, 640 + lv_option*50,null);
		}
	}
	
	
	private void back_to_title() {
		entered = false;
		location = 0;
		option=0;
		lv_mode=0;
		lv_option=0;
		attack = 1;
		ability=0;
		_l1=_l2=_a=_t=_i=_o=false;
		_super=false;
	}
	private void initial_lv() {
		player_place[0] = 5; 
		player_place[1] = 1;
		play=false;
		lv_complete = false;
		dead = false;
		entered = true;
		lv_option=0;
		blood = 94;	
		if(ability==1)attack=2;
		if(ability==4)blood = 188;
	}
	private void restart_lv() {
		atkTime = false;
		nowTime = 0;
		play = true;
		entered = false;
	}
	
}
