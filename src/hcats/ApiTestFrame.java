package hcats;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import javax.swing.border.EmptyBorder;

public class ApiTestFrame extends JDialog {
	private static final long serialVersionUID = 1L;

	boolean status = false;
	private JFrame frame;
    private JLabel titleLabel;//function choose title label
    private JLabel itemLabel;
    private JLabel apiLabel;
    private JLabel chooseLabel;

    private JPanel itemPanel;
    private JPanel apiPanel;
    private JPanel choosePanel;

    private JCheckBox apiAll;
    private JCheckBox chooseAll;

    private JButton exportBtn;

    private ButtonGroup itemBg;
    private JToggleButton[] itemButton;

    private String fontName;
    private int itemNo = 0;

    private JButton addBtn;
    private JButton removeBtn;

    protected JList apiList;
    protected JList chooseList;
    protected DefaultListModel apiModel;
    protected DefaultListModel chooseModel;
    private JScrollPane apiSp;
    private JScrollPane chooseSp;

    private ArrayList<String> itemName = new ArrayList<String>();
    private Map<String, ArrayList<String>> item_data = new HashMap<String, ArrayList<String>>();
    private Map<String, String> choose_data_search = new HashMap<String, String>();

    //size setting	
    Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
    private int screen_Width = 1920;
    private int screen_Height = 1080;
    private int frame_Width = (int) (screen_Height * 0.6 * 1.583);
    private int frame_Height = (int) (screen_Height * 0.6);

    Comparator<String> comparator = new Comparator<String>() {
        @Override
        public int compare(String string1, String string2) {
            StringTokenizer st = new StringTokenizer(string1);
            String s1 = st.nextToken();
            st = new StringTokenizer(string2);
            String s2 = st.nextToken();
            int i1 = (int) Double.parseDouble(s1);
            int i2 = (int) Double.parseDouble(s2);

            st = new StringTokenizer(s1, ".");
            st.nextToken();
            int f1 = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(s2, ".");
            st.nextToken();
            int f2 = Integer.parseInt(st.nextToken());
            if (i1 > i2) {
                return 1;
            } else if (i1 == i2) {
                if (f1 > f2) {
                    return 1;
                } else {
                    return -1;
                }
            } else {
                return -1;
            }
			//after jdk 1.7,return number must be opposite number
            //else return 0; //unavailable
        }
    };

    public ApiTestFrame(JFrame owner) {
        //設定
    	this.frame = owner;
        setTitle("功能選擇");
        setVisible(false);
        setModalityType(ModalityType.APPLICATION_MODAL);
        setFocusable(true);
        setModal(true);
        getContentPane().setLayout(null);
        setSize(frame_Width + 50, frame_Height + 50);
        setResizable(false);
        storeItemApi();
        initComponent();
    }

//    initComponent
    public void initComponent() {
        try {
            setContentPane(new JLabel(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/hcats/res/images/chooseBackground.png"))
                    .getScaledInstance(frame_Width, frame_Height, BufferedImage.SCALE_SMOOTH))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //font setting
        fontName = "Helvetica Neue";
        //icon setting
        ImageIcon icon = new ImageIcon(this.getClass().getResource("/hcats/res/images/checkbox.png"));
        ImageIcon iconSelected = new ImageIcon(this.getClass().getResource("/hcats/res/images/checkboxSelected.png"));

        int w = (int) ((int) frame_Width * 0.2105);
        int h = (int) ((int) frame_Height * 0.0667);
        double x;
        double y;
//        if (frame_Width > 854) {
//            x = frame_Width / 854;
//            y = frame_Height / 540;
//        } else {
//            x = 854 / frame_Width;
//            y = 540 / frame_Height;
//        }
        x = 1.2;
        y = 1.2;
        //1065/648

        //titleLabel
        titleLabel = new JLabel("功能選擇");
        titleLabel.setForeground(Color.white);
        titleLabel.setFont(new Font(fontName, Font.BOLD, 30));
        titleLabel.setBounds((int) (20 * x), (int) (15 * y), (int) (w * x), (int) (h * y));
        add(titleLabel);

        //exportBtn
        exportBtn = new JButton("導入");
        exportBtn.setBackground(new Color(27, 173, 255));
        exportBtn.setForeground(Color.BLACK);
        exportBtn.setOpaque(true);
        exportBtn.setFont(new Font(fontName, Font.BOLD, 18));
        exportBtn.setBounds((int) (712 * x), (int) (15 * y), (int) (120 * x), (int) (h * y));
        exportBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                status = true;
                setVisible(false);
            }
        });;
        add(exportBtn);

        //itemLabel
        itemLabel = new JLabel("項目");
        itemLabel.setForeground(Color.BLACK);
        itemLabel.setFont(new Font(fontName, Font.BOLD, 24));
        itemLabel.setBounds((int) (20 * x), (int) (80 * y), (int) (w * x), (int) (h * y));
        add(itemLabel);

        //apiLabel
        apiLabel = new JLabel("相關API");
        apiLabel.setForeground(Color.BLACK);
        apiLabel.setFont(new Font(fontName, Font.BOLD, 24));
        apiLabel.setBounds((int) (195 * x), (int) (80 * y), (int) (w * x), (int) (h * y));
        add(apiLabel);

        //apiAll
        apiAll = new JCheckBox("全選");
        apiAll.setFont(new Font(fontName, Font.BOLD, 24));
        apiAll.setOpaque(false);
        apiAll.setBounds((int) (410 * x), (int) (80 * y), (int) (w * x), (int) (h * y));
        apiAll.setIcon(icon);
        apiAll.setSelectedIcon(iconSelected);
        apiAll.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                JCheckBox jb = (JCheckBox) e.getSource();
                if (jb.isSelected()) {
                    apiList.clearSelection();
                    int start = 0;
                    int end = apiList.getModel().getSize() - 1;
                    if (end >= 0) {
                        apiList.setSelectionInterval(start, end);
                    }
                } else {
                    apiList.clearSelection();
                }
            }
        });
        add(apiAll);

        //chooseLabel
        chooseLabel = new JLabel("已選取API");
        chooseLabel.setForeground(Color.BLACK);
        chooseLabel.setFont(new Font(fontName, Font.BOLD, 24));
        chooseLabel.setBounds((int) (540 * x), (int) (80 * y), (int) (w * x), (int) (h * y));
        add(chooseLabel);

        //chooseAll
        chooseAll = new JCheckBox("全選");
        chooseAll.setFont(new Font(fontName, Font.BOLD, 24));
        chooseAll.setOpaque(false);
        chooseAll.setBounds((int) (755 * x), (int) (80 * y), (int) (w * x), (int) (h * y));
        chooseAll.setIcon(icon);
        chooseAll.setSelectedIcon(iconSelected);
        chooseAll.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                JCheckBox jb = (JCheckBox) e.getSource();
                if (jb.isSelected()) {
                    chooseList.clearSelection();
                    int start = 0;
                    int end = chooseList.getModel().getSize() - 1;
                    if (end >= 0) {
                        chooseList.setSelectionInterval(start, end);
                    }
                } else {
                    chooseList.clearSelection();
                }
            }
        });
        add(chooseAll);

        //itemPanel
        itemPanel = new JPanel(new GridLayout(itemName.size(), 1, 10, 10));
        itemPanel.setBounds((int) (20 * x), (int) (120 * y), (int) (150 * x), (int) (350 * y));
        itemPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        itemPanel.setBorder(BorderFactory.createLineBorder(new Color(62, 147, 179), 4));
        itemPanel.setBackground(Color.white);
        add(itemPanel);

        //apiPanel
        apiPanel = new JPanel();
        apiPanel.setBounds((int) (195 * x), (int) (120 * y), (int) (290 * x), (int) (350 * y));
        apiPanel.setBorder(BorderFactory.createLineBorder(new Color(62, 147, 179), 4));
        apiPanel.setBackground(Color.white);
        add(apiPanel);

        //choosePanel
        choosePanel = new JPanel();
        choosePanel.setBounds((int) (540 * x), (int) (120 * y), (int) (290 * x), (int) (350 * y));
        choosePanel.setBorder(BorderFactory.createLineBorder(new Color(62, 147, 179), 4));
        choosePanel.setBackground(Color.white);
        add(choosePanel);

        //item btn create
        itemBg = new ButtonGroup();
        itemButton = new JToggleButton[itemName.size()];

        for (int i = 0; i < itemName.size(); i++) {
            itemButton[i] = new JToggleButton(itemName.get(i));
            itemButton[i].setFocusable(false);
            itemButton[i].setOpaque(true);
            itemButton[i].setBackground(new Color(250, 250, 250));
            itemButton[i].setForeground(Color.black);
            itemButton[i].setFont(new Font(fontName, Font.BOLD, 18));
            itemBg.add(itemButton[i]);
            itemPanel.add(itemButton[i]);
            itemButton[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JToggleButton bt = (JToggleButton) e.getSource();
                    itemNo = itemName.indexOf(bt.getText());
                    refreshList();
                    apiAll.setSelected(false);
                    chooseAll.setSelected(false);
                }
            });
            itemButton[i].addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    JToggleButton b = (JToggleButton) e.getSource();
                    if (b.isSelected()) {
                        b.setBackground(new Color(0, 146, 255));
                        b.setForeground(Color.white);
                    } else {
                        b.setBackground(new Color(250, 250, 250));
                        b.setForeground(Color.black);
                    }
                }
            });
        }
        itemButton[0].setSelected(true);

        //addbtn
        addBtn = new JButton(">");
        addBtn.setBounds((int) (498 * x), (int) (250 * y), (int) (30 * x), (int) (30 * y));
        addBtn.setOpaque(true);
        addBtn.setFont(new Font(fontName, Font.BOLD, 18));
        addBtn.setBackground(new Color(53, 165, 252));
        addBtn.setForeground(Color.black);
        addBtn.setBorder(BorderFactory.createLineBorder(new Color(62, 147, 179), 2));
        addBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int[] addindex = apiList.getSelectedIndices();

                ArrayList<String> temp = item_data.get(itemName.get(itemNo));//item arraylist
                String item = itemName.get(itemNo);

                for (int i = 0; i < addindex.length; i++) {
                    String apiName = item_data.get(item).get(addindex[i]);
                    choose_data_search.put(apiName, item);
                    chooseModel.addElement(new JCheckBox(apiName));
                }

                for (int i = (addindex.length - 1); i >= 0; i--) {
                    apiModel.remove(addindex[i]);
                    temp.remove(addindex[i]);
                }
                refreshList();
                apiAll.setSelected(false);
                chooseAll.setSelected(false);
            }
        });
        add(addBtn);

        //removeBtn
        removeBtn = new JButton("<");
        removeBtn.setBounds((int) (498 * x), (int) (300 * y), (int) (30 * x), (int) (30 * y));
        removeBtn.setOpaque(true);
        removeBtn.setFont(new Font(fontName, Font.BOLD, 18));
        removeBtn.setBackground(new Color(53, 165, 252));
        removeBtn.setForeground(Color.black);
        removeBtn.setBorder(BorderFactory.createLineBorder(new Color(62, 147, 179), 2));
        removeBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int[] removeindex = chooseList.getSelectedIndices();

                String apiName = "";
                String apiItem = "";
                for (int i = 0; i < removeindex.length; i++) {
                    JCheckBox jb = (JCheckBox) chooseModel.get(removeindex[i]);
                    apiName = jb.getText();
                    apiItem = choose_data_search.get(apiName);
                    item_data.get(apiItem).add(apiName);//add api to the original group
                }
                for (int i = (removeindex.length - 1); i >= 0; i--) {
                    chooseModel.remove(removeindex[i]);
                }
                refreshList();
                apiAll.setSelected(false);
                chooseAll.setSelected(false);
            }
        });
        add(removeBtn);

        //apiSp_Jlist
        apiModel = new DefaultListModel();
        refreshList();
        apiList = listMaker(apiModel);
        apiSp = new JScrollPane();
        apiSp.getViewport().setView(apiList);
        apiSp.setPreferredSize(new Dimension((int) (275 * x), (int) (335 * y)));
        apiPanel.add(apiSp, BorderLayout.CENTER);

        chooseModel = new DefaultListModel();
        chooseList = listMaker(chooseModel);
        chooseSp = new JScrollPane();
        chooseSp.getViewport().setView(chooseList);
        chooseSp.setPreferredSize(new Dimension((int) (275 * x), (int) (335 * y)));
        choosePanel.add(chooseSp, BorderLayout.CENTER);

    }//end of initComponent

    public JList listMaker(DefaultListModel model) {
        JList l = new JList();
        l = new JList(model);

        /*  Mode Select -Mutilple choice without pressing ctrl */
        l.setSelectionModel(new DefaultListSelectionModel() {
			private static final long serialVersionUID = 1L;

			@Override
            public void setSelectionInterval(int index0, int index1) {
                if (super.isSelectedIndex(index0)) {
                    super.removeSelectionInterval(index0, index1);
                } else {
                    super.addSelectionInterval(index0, index1);
                }
            }
        });

        l.setFixedCellHeight(50);

        /* CellRenderer setting */
        MyCellRenderer renderer = new MyCellRenderer();
        l.setCellRenderer(renderer);
        return l;
    }

    public void refreshList() {
        apiModel.removeAllElements();
        ArrayList<String> temp = new ArrayList<String>();
        temp = item_data.get(itemName.get(itemNo));

        //Collections.sort overrided
        if (temp == null) {
            apiModel.clear();
        } else {
            Collections.sort(temp, comparator);
            for (String apiName : temp) {
                apiModel.addElement(new JCheckBox(apiName));
            }
        }
    }

    public void storeItemApi() {
        try {
        	InputStream is = getClass().getResourceAsStream("/hcats/res/txt/HCATSapi.txt");
            InputStreamReader fr = new InputStreamReader (is,"UTF-8"); 
            BufferedReader bf = new BufferedReader(fr);

            ArrayList<String> item_api = new ArrayList<String>();
            String strData;
            String tmp = "";
            String iName = "";

            while ((strData = bf.readLine()) != null) {
                if (strData.substring(0, 1).equals("#")) {
                    StringTokenizer st = new StringTokenizer(strData, "#");
                    if (st.hasMoreTokens()) {
                        tmp = st.nextToken();
                    }
                    //store
                    if (strData.substring(1, 2).equals("E")) {
                        item_data.put(iName, item_api);
                        item_api = new ArrayList<String>();
                    } else {
                        itemName.add(tmp);
                        iName = tmp;
                    }
                } else {
                    item_api.add(strData);
                }
            }
            bf.close();
            fr.close();
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }

    public DefaultListModel returnListModel() {
        return chooseModel;
    }

    public void showDialog() {
    	setLocation(frame.getLocation().x + 15,frame.getLocation().y + 15);
        status = false;
        setVisible(true);
    }
}
