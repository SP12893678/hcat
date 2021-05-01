package hcats;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.StringTokenizer;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

class MyCellRenderer extends JCheckBox implements ListCellRenderer {

	private static final long serialVersionUID = 1L;

	public MyCellRenderer() {
    }

    public Component getListCellRendererComponent(
            JList list,
            Object value,
            int index,
            boolean isSelected,
            boolean cellHasFocus) {

        JCheckBox checkBox = (JCheckBox) value;

        String apiName = "";
        String apiContext = "";
        StringTokenizer st = new StringTokenizer(checkBox.getText(), "(");
        if (st.hasMoreTokens()) {
            apiName = st.nextToken().trim();
            apiContext = st.nextToken().trim();
        }
        String newString = "<html>" + apiName + "<br/>(" + apiContext + "</html>";
        setText(newString);
        setBackground(new Color(250, 250, 250));
        setForeground(Color.BLACK);
        setIconTextGap(10);
        setFont(new Font("Helvetica Neue", Font.BOLD, 13));

        if (isSelected) {
            setIcon(new ImageIcon(this.getClass().getResource("/hcats/res/images/selected.png")));
            setBackground(new Color(0, 146, 255));
            setForeground(Color.WHITE);
        } else {
            setIcon(new ImageIcon(this.getClass().getResource("/hcats/res/images/unselected.png")));
            setBackground(new Color(250, 250, 250));
            setForeground(Color.BLACK);
        }

        return this;
    }
}
