import oracle.jrockit.jfr.JFR;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.xml.transform.Result;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class database {
    private JPanel mainframe;
    private JCheckBox gender;
    private JCheckBox name;
    private JCheckBox age1;
    private JCheckBox num;
    private JTextField numText;
    private JTextField nameText;
    private JTextField age1Text;
    private JCheckBox cClass;
    private JCheckBox addr;
    private JCheckBox depart;
    private JTextField classText;
    private JTextField addrText;
    private JTextField departText;
    private JTextField age2Text;
    private JRadioButton male;
    private JRadioButton female;
    private JButton search;
    private JTextArea answer;
    private JLabel age2;

    public database() {
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = getMessage(e);
                answer.setText(message);
                System.out.println(excuteAndShow(message));
            }
        });
    }

    private String getMessage(ActionEvent e) {
        String message = "";
        String tmp;
        if (num.isSelected()) {
            tmp = numText.getText();
            if (tmp.contains("%") || tmp.contains("_"))
                message += "(sid like '" + tmp + "')and ";
            else
                message += "(sid = " + tmp + ")and ";
        }
        if (name.isSelected()) {
            tmp = nameText.getText();
            if (tmp.contains("%") || tmp.contains("_"))
                message += "(sname like '" + tmp + "')and ";
            else
                message += "(sname = '" + tmp + "')and ";
        }
        if (age1.isSelected()) {
            message += "(sage >= " + age1Text.getText() + " and sage <= " + age2Text.getText() + ")and ";
        }
        if (gender.isSelected()) {
            if (male.isSelected()) message += "(ssex = '男')and ";
            if (female.isSelected()) message += "(ssex = '女')and ";
        }
        if (cClass.isSelected()) {
            tmp = classText.getText();
            if (tmp.contains("%") || tmp.contains("_"))
                message += "(sclass like '" + tmp + "')and ";
            else
                message += "(sclass = '" + tmp + "')and ";
        }
        if (depart.isSelected()) {
            tmp = departText.getText();
            if (tmp.contains("%") || tmp.contains("_"))
                message += "(sdepart like '" + tmp + "')and ";
            else
                message += "(sdepart = '" + tmp + "')and ";
        }
        if (addr.isSelected()) {
            tmp = addrText.getText();
            if (tmp.contains("%") || tmp.contains("_"))
                message += "(saddr = '" + tmp + "')and ";
            else
                message += "(saddr = '" + tmp + "')and ";
        }
        if (!message.equals("")) {
            message = message.substring(0, message.length() - 4);
            message = "select * from student where " + message;
        }
        else
            message = "select * from student";
        return message;
    }

    private boolean excuteAndShow(String message) {
        ResultSet rs=null;
        try {
            new SQLDriver();
            rs = SQLDriver.getConnection().createStatement().executeQuery(message);
        } catch (Exception e) {
            e.printStackTrace();
        }

        createAnsFrame(rs);
        return false;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("database");
        frame.setContentPane(new database().mainframe);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void createAnsFrame(ResultSet rs) {
        JFrame newFrame = new JFrame("检索结果：");
        newFrame.setSize(500, 200);
        newFrame.setVisible(true);

        Object[] columnTitle = {"学号", "姓名", "年龄", "性别", "班级", "系别", "地址"};
        JScrollPane jscrollpane = new JScrollPane();
        String[][] tableData = new String[100][7];

        int i = 0;
        try {
            while(rs.next()){
                tableData[i][0] = rs.getString("sid");
                tableData[i][1] = rs.getString("sname");
                tableData[i][2] = rs.getString("sage");
                tableData[i][3] = rs.getString("ssex");
                tableData[i][4] = rs.getString("sclass");
                tableData[i][5] = rs.getString("sdepart");
                tableData[i][6] = rs.getString("saddr");
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JTable table = new JTable(Arrays.copyOfRange(tableData,0,i), columnTitle);
        jscrollpane.setBounds(200, 100, 550, 650);
        jscrollpane.setViewportView(table);//这句很重要
        table.setRowHeight(35);

        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, r);

        newFrame.add(jscrollpane);
    }
}
