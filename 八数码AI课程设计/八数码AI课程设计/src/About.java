
import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2008</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class About extends JFrame  implements ActionListener {
    JPanel contentPane;
    JLabel jLabel1 = new JLabel();
    JLabel jLabel2 = new JLabel();
    JLabel jLabel3 = new JLabel();
    JLabel jLabel4 = new JLabel();
    JLabel jLabel5 = new JLabel();
    JButton jButton1 = new JButton();
    public About() {
        try {
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            jbInit();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Component initialization.
     *
     * @throws java.lang.Exception
     */
    private void jbInit() throws Exception{
        contentPane = (JPanel) getContentPane();
        contentPane.setLayout(null);
        setSize(new Dimension(485, 285));
        setTitle("关于");
        jLabel1.setBorder(BorderFactory.createLoweredBevelBorder());
        jLabel1.setText("学校： 厦门大学嘉庚学院  07级  计算机科学与技术系  计算机班");
        jLabel1.setBounds(new Rectangle(49, 72, 371, 30));
        jLabel2.setBackground(Color.magenta);
        jLabel2.setFont(new java.awt.Font("宋体", Font.BOLD, 15));
        jLabel2.setForeground(Color.blue);
        jLabel2.setBorder(BorderFactory.createEtchedBorder());
        jLabel2.setText("人工智能经典：        八数码 A星算法");
        jLabel2.setBounds(new Rectangle(68, 16, 311, 38));
        jLabel3.setBorder(BorderFactory.createLoweredBevelBorder());
        jLabel3.setText("开发小组组长：江伟坚  小组成员：林昕  郑毅  陈东虹");
        jLabel3.setBounds(new Rectangle(49, 106, 371, 31));
        jLabel4.setBorder(BorderFactory.createLoweredBevelBorder());
        jLabel4.setText("指导老师：  康恺");
        jLabel4.setBounds(new Rectangle(49, 185, 291, 30));
        jLabel5.setBorder(BorderFactory.createLoweredBevelBorder());
        jLabel5.setText("Email:  kongwaigin@hotmail.com   QQ:116411600");
        jLabel5.setBounds(new Rectangle(49, 146, 371, 29));
        jButton1.setBounds(new Rectangle(351, 184, 69, 31));
        jButton1.setText("确定");
        contentPane.add(jLabel2);
        contentPane.add(jLabel4);
        contentPane.add(jLabel3);
        contentPane.add(jLabel1);
        contentPane.add(jLabel5);
        contentPane.add(jButton1);
        jButton1.addActionListener(this);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent event)
    {
    	if(event.getSource()==jButton1)
    	{
    		this.dispose();
    	}
    }
    
    
}