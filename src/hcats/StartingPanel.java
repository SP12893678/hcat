package hcats;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.util.*;
import java.util.Timer;
import javax.swing.*;

public class StartingPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
    URL url = this.getClass().getResource("/hcats/res/images/logo.gif");
    ImageIcon icon = new ImageIcon(url);
    Image image = icon.getImage();
    
	int a = 255;
	int R = 0;
	int G = 0;
	int B = 0;
	int i = 0;
	Graphics2D g2d;
	Timer timer = new Timer(); 
	Process process = new Process();
	public StartingPanel(int width,int height)
    {
		setVisible(true);
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
//        g2d.setFont(new Font("Fira Code Light", Font.BOLD, 4*R));
        g2d.setColor(getBackground());
        int pos_x = this.getWidth()/2;
        int pos_y = this.getHeight()/2;
        g2d.setColor(new Color(R,G,B,a));
        g2d.fillRect(0, 0, pos_x*2, pos_y*2);
        /*-------------------------------------------------------*/
        g2d.drawImage(image, -33, -1, this);
    }
          
    public class Process extends TimerTask {
		@Override
		public void run() 
		{
			image = null;
			if(R >= 255)
			{
				a--;
			}
			else
			{
				R++;
    			G++;
    			B++;
			}
			if(a <= 0)
			{
				timer.cancel();
				setVisible(false);
			}
			updateUI();
		}
	}
}