package hcats;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.jb2011.lnf.beautyeye.*;

import fontloader.Fontloader;
import toggleBtn.ToggleBtn;

public class HCATS extends JFrame {
	
	private static final long serialVersionUID = 1L;
    Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
    private int screen_Width = (int) screenSize.getWidth();
	private int screen_Height = (int) screenSize.getHeight();
	private int frame_Width = (int) (1080 * 0.6 * 1.583);
	private int frame_Height = (int) (1080 * 0.6);
	StartingPanel startingPanel = new StartingPanel(frame_Width,frame_Height);
	MainPanel mainPanel = new MainPanel(frame_Width,frame_Height);
	LoadingPanel loadingPanel = new LoadingPanel(frame_Width,frame_Height);
	ApiTestFrame ID = new ApiTestFrame(this);
	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) throws IOException {
		EventQueue.invokeLater(new Runnable() {
			public void run() 
			{
				try 
				{
				     BeautyEyeLNFHelper.translucencyAtFrameInactive = false;
			         BeautyEyeLNFHelper.launchBeautyEyeLNF();
			         UIManager.put("RootPane.setupButtonVisible", false);
			         Fontloader fontloader = new Fontloader("");
			     	 fontloader.addDefaultFont();
			     	 fontloader.loadFont();
			         HCATS frame = new HCATS();
			         DataBase.getDatabase().setFrame(frame);
				     frame.setCshisLibrary();
					 frame.setVisible(true);
					 frame.startingPanel.timer.scheduleAtFixedRate(frame.startingPanel.process, 2500, 3);
				} 
				catch (Exception e) 
				{
					
				}	
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public HCATS() {
		super("HCATS");
		setIconImage(new ImageIcon(this.getClass().getResource("/hcats/res/images/CAT.png")).getImage());
		setVisible(false);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setSize(frame_Width+50, frame_Height+50);
		setLocation(screen_Width/2 - frame_Width/2 , screen_Height/2 - frame_Height/2);
		setPanel();
	}
	
	public void setPanel()
	{
		JLayeredPane layeredPane = new JLayeredPane();
		startingPanel.setBounds(0, 0, frame_Width, frame_Height);
		mainPanel.setBounds(0, 0, frame_Width, frame_Height);
		loadingPanel.setBounds(0, 0, frame_Width, frame_Height);	
		layeredPane.add(startingPanel,JLayeredPane.PALETTE_LAYER);
		layeredPane.add(mainPanel,JLayeredPane.DEFAULT_LAYER);
		layeredPane.add(loadingPanel,JLayeredPane.PALETTE_LAYER);
		layeredPane.setBounds(0, 0, frame_Width, frame_Height);	
		setContentPane(layeredPane);
	}
	
	public class MainPanel extends JPanel {
		
		private static final long serialVersionUID = 1L;
		int width;
		int height;
		
		int leftside_x;
		int leftside_width;
		int center_x;
		int center_width;
		int rightside_x;
		int rightside_width;
			
		boolean run_status = false;
		boolean status = false;
		JLabel step_label;
		JLabel toggle_label;
		ToggleBtn toggleBtn;
        ToggleBtn COM_toggleBtn;
        ToggleBtn SAMDC_toggleBtn;
        ToggleBtn HPC_toggleBtn;
		JButton function_selector;
		JButton run_btn;
		JLabel leftside_box;
		JLabel rightside_box;
		Image rightside_bg;
		Image leftside_bg;
		JLabel result_title;
		JTextArea result_area;
		JScrollPane scroller;
		JLabel apilist_title;	
		JList api_list;
		
		public MainPanel(int width,int height)
		{
			this.width = width;
    		this.height = height;
			setLayout(null);
			setPreferredSize(new Dimension(width, height));
			setOpaque(false);
			setPosition();
			setImage();
			setComponent();
		}
		
		public void setPosition()
		{
			leftside_x = 0;
			leftside_width = (int) (height*0.45);
			rightside_width = (int) (height*0.45);
			center_width = width - leftside_width * 2;
			center_x = leftside_x + leftside_width;
			rightside_x = width - rightside_width;
		}
		
		public void setImage()
		{
			rightside_bg= new ImageIcon(this.getClass().getResource("/hcats/res/images/mainRight.jpg")).getImage().getScaledInstance( (int) (height*0.45),height, Image.SCALE_SMOOTH);
			leftside_bg= new ImageIcon(this.getClass().getResource("/hcats/res/images/mainLeft.jpg")).getImage().getScaledInstance( (int) (height*0.45),height, Image.SCALE_SMOOTH);
		}
		
		public void setComponent()
		{
			step_label = new JLabel();
			step_label.setHorizontalAlignment(SwingConstants.CENTER);
			step_label.setFont(new Font("微軟正黑體", 1, 20));
			step_label.setText("<html><center><font size=\"7\">步驟</font></center><br/>" 
					        +"<center>開COM</center>" 
							+"<center>↓</center>"
							+"<center>安全認證</center>"
							+"<center>↓</center>"
							+"<center>（醫師人員認證）</center>"
							+"<center>↓</center>"
							+"<center>開始測試</center>"
							+"<center>↓</center>"
							+"<center>關COM</center></html>");
			step_label.setBounds(-10, -20, (int) (height*0.45), height);
			add(step_label);
			
			toggle_label = new JLabel("Com Switch");
			toggle_label.setForeground(Color.black);
			toggle_label.setFont(new Font("Fira Code Light", Font.PLAIN, 18));
			toggle_label.setBounds(750, 90, 252, 110);
	        add(toggle_label);
	        
	        toggle_label = new JLabel("Verify Switch");	        
	        toggle_label.setForeground(Color.black);
	        toggle_label.setFont(new Font("Fira Code Light", Font.PLAIN, 18));
	        toggle_label.setBounds(750, 170, 252, 110);
	        add(toggle_label);
	        
	        toggle_label = new JLabel("HPC Switch");	        
	        toggle_label.setForeground(Color.black);
	        toggle_label.setFont(new Font("Fira Code Light", Font.PLAIN, 18));
	        toggle_label.setBounds(750, 250, 252, 110);
	        add(toggle_label);
	        
	        COM_toggleBtn = new ToggleBtn(45);
	        COM_toggleBtn.setFocusable(false);
	        COM_toggleBtn.setBounds(900, 120, COM_toggleBtn.getWidth(), COM_toggleBtn.getHeight());
	        setComSwitch();
	        add(COM_toggleBtn);
	        
	        SAMDC_toggleBtn = new ToggleBtn(45);
            SAMDC_toggleBtn.setEnabled(false);
	        SAMDC_toggleBtn.setFocusable(false);
	        SAMDC_toggleBtn.setBounds(900, 200, SAMDC_toggleBtn.getWidth(), SAMDC_toggleBtn.getHeight());
	        setVerifySwitch();
	        add(SAMDC_toggleBtn);
	        
	        HPC_toggleBtn = new ToggleBtn(45);
            HPC_toggleBtn.setEnabled(false);
	        HPC_toggleBtn.setFocusable(false);
	        HPC_toggleBtn.setBounds(900, 280, HPC_toggleBtn.getWidth(), HPC_toggleBtn.getHeight());
	        setHPCSwitch();
	        add(HPC_toggleBtn);

	        function_selector= new JButton("功能選擇");
	        function_selector.setFont(new Font("微軟正黑體", 1, 18));
	        function_selector.setFocusable(false);
	        function_selector.setBounds(770, 400, 200, 50);
	        function_selector.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) 
	            {
	            	ID.showDialog();
	            	if(ID.status)
	            	{
	            		DefaultListModel df = ID.returnListModel();
		        	    DefaultListModel m = new DefaultListModel();
		        	    ArrayList<String> temp = new ArrayList<String>();
		        	    for(int i=0 ;i<df.getSize();i++) 
		        	    {	        	    	
			        	    JCheckBox jb = (JCheckBox)df.getElementAt(i);
			        	    String apiName="";
			        	    StringTokenizer st = new StringTokenizer(jb.getText(),"(");
			        	    if(st.hasMoreTokens()) 
			        	    {
			        	    	apiName = st.nextToken().trim();
			        	    	//apiContext = st.nextToken().trim();
			        	    }
			        	    //String newString = "<html>"+apiName+"<br/>("+apiContext+"</html>";
			        	    String newString = apiName;
			        	    temp.add(newString);	        	    	      
		        	    } 
		        	    Collections.sort(temp,comparator);
		        	    for(String newString:temp)
		        	    	m.addElement(newString);
		        	    api_list.setModel(m);
		        	    result_area.setText("請先執行");
		        	    run_status = false;
	            	}
	            }
	        });
	        add(function_selector);
	        
	        run_btn = new JButton("開始執行");
	        run_btn.setFont(new Font("微軟正黑體", 1, 18));
	        run_btn.setFocusable(false);
	        run_btn.setBounds(770, 500, 200, 50);
	        run_btn.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) 
	            {
	            	loadingPanel.play();
	            	Thread thread = new Thread(new Runnable() {
	                    @Override
	                    public void run() {
		                    ArrayList<String> tmp = new ArrayList<String>();
	                    	ListModel listModel = api_list.getModel();
	                    	for(int index=0;index < listModel.getSize();index++)
	                    	{
	                    		String select_Text = listModel.getElementAt(index).toString();
	                    		String API = select_Text.substring(select_Text.indexOf(" ")+1);
	                    		if(DataBase.getDatabase().data_api.get(API)!=null)
	                    			tmp.add(API);
	                    	}
		                    if(tmp.isEmpty())
		                    {
		                    	loadingPanel.finish();
		                    	JOptionPane.showMessageDialog(getFrame(),"請先導入功能","錯誤訊息",JOptionPane.INFORMATION_MESSAGE);
		                    }
//		                    else if(CsHislibrary.Istance.csVerifySAMDC()!=0)
//			            	{
//			            	    loadingPanel.finish();
//			            	    JOptionPane.showMessageDialog(getFrame(),"請先通過安全認證","錯誤訊息",JOptionPane.INFORMATION_MESSAGE);
//			            	}
		                    else
		                    {
		                    	run_status = true;
		                        /*執行*/
		                        String package_Name = "hcats.";
			                    for(String api:tmp)
			                    {
			                    	try
			                    	{
			                    		ApiBase api_test = (ApiBase) Class.forName(package_Name+api).getConstructor(String.class).newInstance(api);
			                    	}
				                    catch (InstantiationException e) {
										e.printStackTrace();
									} 
				                    catch (IllegalAccessException e) {
										e.printStackTrace();
									} 
				                    catch (ClassNotFoundException e) {
										e.printStackTrace();
									} 
				                    catch (IllegalArgumentException e) {
										e.printStackTrace();
									} 
				                    catch (SecurityException e) {
										e.printStackTrace();
									} 
				                    catch (InvocationTargetException e) {
										e.printStackTrace();
									} 
				                    catch (NoSuchMethodException e) {
										e.printStackTrace();
									}
			                    }
			                    run_status = true;
			                    api_list.setSelectedIndex(0);
			                    String select_Text = api_list.getModel().getElementAt(api_list.getSelectedIndex()).toString();
                    			String API = select_Text.substring(select_Text.indexOf(" ")+1);
                    			result_area.setText(DataBase.getDatabase().data_print_to_textarea(API));
			                    loadingPanel.finish();
		                    }	
	                    }
	                });  
	            	if(!COM_toggleBtn.isSelected())
	            	{
	            	    loadingPanel.finish();
	            	    JOptionPane.showMessageDialog(getFrame(),"請先開啟連接埠","錯誤訊息",JOptionPane.INFORMATION_MESSAGE);
	            	}
	            	else if(!SAMDC_toggleBtn.isSelected())
	            	{
	            	    loadingPanel.finish();
	            	    JOptionPane.showMessageDialog(getFrame(),"請先通過安全認證","錯誤訊息",JOptionPane.INFORMATION_MESSAGE);
	            	}
	            	else
	            	{
	            	    thread.start();
	            	}
	            }
	        });
	        add(run_btn);
	    
	        leftside_box = new JLabel();	
	        leftside_box.setIcon(new ImageIcon(leftside_bg));
	        leftside_box.setBounds(leftside_x, 0, leftside_width, height);
			add(leftside_box);
			
			rightside_box = new JLabel();	
			rightside_box.setIcon(new ImageIcon(rightside_bg));
			rightside_box.setBounds(rightside_x, 0,  rightside_width , height);
			add(rightside_box);

			result_title = new JLabel();
			result_title.setText("輸出內容");
			result_title.setFont(new Font("微軟正黑體", 1, 18));
			result_title.setHorizontalAlignment(SwingConstants.CENTER);
			result_title.setOpaque(true);
			result_title.setBackground(new java.awt.Color(204, 204, 204));
			result_title.setBounds(center_x, 0, center_width ,30 );
	        add(result_title);
	        
	        result_area= new JTextArea();
	        result_area.setText("");
	        result_area.setFont(new Font("微軟正黑體",1,18));
	        result_area.setEditable(false);
	        result_area.setLineWrap(true);
	        scroller = new JScrollPane(result_area);
	        scroller.setBounds(center_x , 30, center_width ,(int)(height*0.6)-30); 
	        add(scroller);
	        
	        apilist_title = new JLabel();	
	        apilist_title.setText("已選取的api"); 
	        apilist_title.setFont(new Font("微軟正黑體", 1, 18));
	        apilist_title.setHorizontalAlignment(SwingConstants.CENTER);
	        apilist_title.setOpaque(true);
	        apilist_title.setBackground(new java.awt.Color(204, 204, 204));
	        apilist_title.setBounds(center_x ,(int)(height*0.6), center_width ,30);
	        add(apilist_title);
	        
//	        String[] api_list_array = {"csGetCardNo","hisGetBasicData","hisGetRegisterPrevent","hisReadPrescription","hisGetCardStatus","hisGetTreatmentNeedHPC","hisGetCumulativeData","hisGetCumulativeFee"};
	        String[] api_list_array = {};
	        api_list = new JList(api_list_array);
			api_list.setFont(new Font("微軟正黑體",1,22));
			api_list.setFixedCellHeight(30);
			api_list.setSelectionMode(0);
			api_list.addListSelectionListener(new ListSelectionListener() {
	            public void valueChanged(ListSelectionEvent e) {
	                if(e.getValueIsAdjusting())
	                {
	                	loadingPanel.play();
		            	Thread thread = new Thread(new Runnable() {
		                    @Override
		                    public void run() {
		                    	try
		                    	{
		                    		ListModel listModel = api_list.getModel();
//		    	                	result_area.setText(listModel.getElementAt(api_list.getSelectedIndex()).toString());
		                    		if(!run_status)
		                    			result_area.setText("請先執行");
		                    		else
		                    		{
		                    			String select_Text = listModel.getElementAt(api_list.getSelectedIndex()).toString();
		                    			String API = select_Text.substring(select_Text.indexOf(" ")+1);
//		                    			String API = select_Text;
		                    			result_area.setText(DataBase.getDatabase().data_print_to_textarea(API));
		                    		}
		                    	}
		                    	finally
		                    	{
		                    		loadingPanel.finish();
		                    	}
		                    	
		                    }
		                });         
		            	thread.start();
	                }
	            }
	        });
			scroller = new JScrollPane(api_list);
			scroller.setBounds(center_x ,(int)(height*0.6)+30, center_width ,(int)(height*0.4)-60); 
	        add(scroller);
		}
		
		public void setComSwitch()
		{
			COM_toggleBtn.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) 
	            {
	                final JToggleButton toggleBtn = ((JToggleButton)e.getSource());
	                if(toggleBtn.isSelected())
	                {
	                    Object[] selectionValues = new Object[30];            
	                    String buff = "COM";
	                    for(int num=0;num<selectionValues.length;num++)
	                    {   
	                        selectionValues[num] = buff + (num+1);
	                    }                   
	                    final Object comresult = JOptionPane.showInputDialog(
	                            getFrame(),
	                            "選擇: ",
	                            "開啟連接埠",
	                            JOptionPane.PLAIN_MESSAGE,
	                            null,
	                            selectionValues,
	                            selectionValues[0]
	                    );
	                    if(comresult != null)
	                    {
	                        loadingPanel.play();
	      	            	Thread thread = new Thread(new Runnable() {
	      	            		int errorcode;
	      	                    @Override
	      	                    public void run() {
	      	                    	try
	      	                    	{
	      	                    		int com_Num = Integer.valueOf(comresult.toString().substring(3));
	      	                    		errorcode = CsHislibrary.Istance.csOpenCom(com_Num-1);
	      	                    	}
	      	                    	finally
	      	                    	{
	      	                    		loadingPanel.finish();
	      	                    		status = (DataBase.getDatabase().alert_error(errorcode))?true:false;
	      	                    		toggleBtn.setSelected(status);
	      	                    		HPC_toggleBtn.setSelected(false);
			                            SAMDC_toggleBtn.setSelected(false);
	      	                    		HPC_toggleBtn.setEnabled(status);
	      	                            SAMDC_toggleBtn.setEnabled(status);
	      	                    	}
	      	                    }
	      	                });         
	      	            	thread.start();
	                    }
	                    else
	                    {
	                        toggleBtn.setSelected(false);
                            status = false;
	                    }
	                }
	                else
	                {
	                	loadingPanel.play();
		            	Thread thread = new Thread(new Runnable() {
		            		int errorcode;
		                    @Override
		                    public void run() {
		                    	try
		                    	{
		                    		errorcode = CsHislibrary.Istance.csCloseCom();
		                    	}
		                    	finally
		                    	{
		                    		loadingPanel.finish();
		                    		status = (!DataBase.getDatabase().alert_error(errorcode))?true:false;
		                    		toggleBtn.setSelected(status);
		                            HPC_toggleBtn.setEnabled(status);
		                            SAMDC_toggleBtn.setEnabled(status);
		                    	}
		                    }
		                });
		            	thread.start();
	                }
	            }
	        });
		}
		
		public void setVerifySwitch()
		{
			SAMDC_toggleBtn.addActionListener(new ActionListener() {
	            public void actionPerformed(final ActionEvent e)
	            {
	            	final JToggleButton toggleBtn = ((JToggleButton)e.getSource());
	            	loadingPanel.play();
	            	Thread thread = new Thread(new Runnable() {
	            		int errorcode;
	                    @Override
	                    public void run() {
	                    	try
	                    	{
		    	                errorcode = CsHislibrary.Istance.csVerifySAMDC();
	                    	}
	                    	finally
	                    	{
	                    		loadingPanel.finish();
	                    		toggleBtn.setSelected((DataBase.getDatabase().alert_error(errorcode))?true:false);
	                    	}
	                    }
	                });         
	            	thread.start();
	            }
	        });
		}
		
		public void setHPCSwitch()
		{
			HPC_toggleBtn.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) 
	            {
	                final JToggleButton toggleBtn = ((JToggleButton)e.getSource());
	                if(toggleBtn.isSelected())
	                {
		                loadingPanel.play();
		            	Thread thread = new Thread(new Runnable() {
		            		int errorcode;
		                    @Override
		                    public void run() {
		                    	try
		                    	{
		                    		errorcode = CsHislibrary.Istance.hpcVerifyHPCPIN();
		                    	}
		                    	finally
		                    	{
		                    		loadingPanel.finish();
		                    		toggleBtn.setSelected((DataBase.getDatabase().alert_error(errorcode))?true:false);
		                    	}
		                    }
		                });  
		            	thread.start();
	                }
	            }
	        });
		}
	}

	public JFrame getFrame()
	{
		return this;
	}
	  
	public void setCshisLibrary()
	{
		String libraryPath = this.getClass().getResource("/hcats/res/lib/").getPath();
		System.setProperty("jna.library.path",libraryPath);
//		System.setProperty("jna.debug_load","true");
	}
	
	Comparator<String> comparator = new Comparator<String>() {
		@Override
		public int compare(String string1, String string2) {
			StringTokenizer st = new StringTokenizer(string1);
			String s1 = st.nextToken();
			st = new StringTokenizer(string2);
			String s2 = st.nextToken();
			int i1=(int)Double.parseDouble(s1);
			int i2=(int)Double.parseDouble(s2);
			
			st = new StringTokenizer(s1,".");
			st.nextToken();
			int f1 = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(s2,".");
			st.nextToken();
			int f2 = Integer.parseInt(st.nextToken());
			if(i1>i2)
				return 1;
			else if(i1==i2){
				if(f1>f2)
					return 1;
				else 
					return -1;
			}
			else return -1;
			//after jdk 1.7,return number must be opposite number
			//else return 0; //unavailable
		}
	};
}
