import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private JTable table;
    private JLabel age2;

    public database() {
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = getMessage();
                answer.setText(message);
                System.out.println(excuteAndShow(message));
            }
        });
    }

    private static String getMessage() {

        return "null";
    }
    private static boolean excuteAndShow(String message){

        return false;
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("database");
        frame.setContentPane(new database().mainframe);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
