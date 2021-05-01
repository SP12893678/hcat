package hcats;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JPanel;

public class LoadingPanel extends JPanel {

		private static final long serialVersionUID = 1L;
		boolean loading_status = true;
		int t = 90;
    	int p = -90;
    	
    	int a = 255;
    	int b = 255;
    	boolean b_back = false;
    	int ww = 50;
    	
    	int color_status = 1;
    	int R = 255;
    	int G = 100;
    	int B = 100;
    	
    	Graphics2D g2d;
    	Timer timer;
    	Process process;
    	public LoadingPanel(int width,int height)
        {
    		setVisible(false);
    		setOpaque(false);
        	setPreferredSize(new Dimension(width, height));
        	addMouseListener(new MouseAdapter() {});
        }

    	protected void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            paintText(g);
        }
        
        private void paintText(Graphics g)
        {
            g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setFont(new Font("Fira Code Light", Font.PLAIN, 16));
            g2d.setColor(getBackground());
            int pos_x = this.getWidth()/2;
            int pos_y = this.getHeight()/2;
            g2d.setColor(new Color(0,0,0,100));
            g2d.fillRect(0, 0, pos_x*2, pos_y*2);
            /*-------------------------------------------------------*/
        	g2d.setColor(new Color(R,G,B,255));
        	g2d.fillArc(pos_x - 55, pos_y - 55, 110, 110, t, p);
        	g2d.setColor(new Color(R,G,B,255));
        	g2d.fillArc(pos_x - 55, pos_y -55, 110, 110, t + 180, p);
        	g2d.setColor(new Color(R,G,B,a));
        	g2d.fillArc(pos_x - ww/2, pos_y - ww/2, ww, ww, 0, 360);
            g2d.setColor(new Color(255,255,255,255));
        	g2d.fillArc(pos_x - 50, pos_y - 50, 100, 100, 0, 360);
            /*-------------------------------------------------------*/
        	g2d.setColor(new Color(0,0,0,b));
        	String loading = "Loading";
        	int loading_w = g2d.getFontMetrics().stringWidth(loading);
        	g2d.drawString(loading, pos_x - loading_w/2, pos_y + 4);
        }
        
        public void initialize_status()
        {
    		t = 90;
        	p = -90;
        	a = 255;
        	b = 255;
        	b_back = false;
        	ww = 50;
        	color_status = 1;
        	R = 255;
        	G = 100;
        	B = 100;
        }
        
        public void play()
        {
        	if(loading_status)
        	{
            	initialize_status();
            	loading_status = false;
            	process = new Process();
            	timer = new Timer(); 
            	timer.scheduleAtFixedRate(process, 10, 4); 
            	setVisible(true);
        	}
        }
        
        public void finish()
        {
        	loading_status = true;
        	setVisible(false);
        }
              
        public class Process extends TimerTask {
    		@Override
    		public void run() 
    		{
    			control_color();
    			control_opacity_scale();
    			control_rotate();
    			text_opacity();
    			if(loading_status)
    			{
    				timer.cancel();
    			}
    			updateUI();
    		}
    	}
        
        public void control_rotate()
        {
        	t--;
			if(t <= 0)
			{
				t = 360;
			}
        }
        
        public void control_opacity_scale()
        {
        	if(a <= 0)
			{
				a = 255;
				ww = 50;
			}
			else
			{
				a--;
    			ww++;
			}
        }
        
        public void text_opacity()
        {
        	if(!b_back)
        	{
        		b--;
        		if(b <= 0)
        			b_back = true;
        	}
        	else
        	{
        		b++;
        		if(b >= 255)
        			b_back = false;
        	}
        }
        
        public void control_color()
        {
        	switch(color_status)
			{
				case 1:
					G++;
					if(G>=255)
						color_status = 2;
					break;
				case 2:
					R--;
					if(R<=100)
						color_status = 3;
					break;
				case 3:
					B++;
					if(B>=255)
						color_status = 4;
					break;
				case 4:
					G--;
					if(G<=100)
						color_status = 5;
					break;
				case 5:
					R++;
					if(R>=255)
						color_status = 6;
					break;
				case 6:
					B--;
					if(B<=100)
						color_status = 1;
					break;
				default:
					break;
			}
        }
    }