package Chat.Application;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Receiver implements ActionListener{
    JTextField text;
    static JPanel a1;
    static Box Vertical = Box.createVerticalBox();
    static DataOutputStream dout;
    static JFrame f = new JFrame();
    Receiver()
    {
        f.setLayout(null);
        JPanel p1 = new JPanel();
        p1.setBackground(new Color(7,94,84));
        p1.setBounds(0,0, 450, 70);
        p1.setLayout(null);
        f.add(p1);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("Icons/3.png"));
        Image i2 = i1.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel back = new JLabel(i3);
        back.setBounds(5, 20, 25, 25);
        p1.add(back);
        
        back.addMouseListener(new MouseAdapter()
                {
                    public void mouseClicked(MouseEvent ae)
                    {
                        System.exit(0);
                    }
                });
        
        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("Icons/boy.png"));
        Image i5 = i4.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        JLabel Profile= new JLabel(i6);
        Profile.setBounds(40, 10, 50, 50);
        p1.add(Profile);
        
        ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("Icons/video.png"));
        Image i8 = i7.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon i9= new ImageIcon(i8);
        JLabel Video= new JLabel(i9);
        Video.setBounds(300, 20, 30, 30);
        p1.add(Video);
        
        ImageIcon i10 = new ImageIcon(ClassLoader.getSystemResource("Icons/phone.png"));
        Image i11 = i10.getImage().getScaledInstance(35, 30, Image.SCALE_DEFAULT);
        ImageIcon i12= new ImageIcon(i11);
        JLabel Phone= new JLabel(i12);
        Phone.setBounds(360, 20, 35, 30);
        p1.add(Phone);
        
        ImageIcon i13 = new ImageIcon(ClassLoader.getSystemResource("Icons/3icon.png"));
        Image i14 = i13.getImage().getScaledInstance(10, 25, Image.SCALE_DEFAULT);
        ImageIcon i15= new ImageIcon(i14);
        JLabel dot= new JLabel(i15);
        dot.setBounds(420, 20, 10, 25);
        p1.add(dot);
        
        JLabel name = new JLabel("Chirag");
        name.setBounds(110, 15, 100, 18);
        name.setForeground(Color.WHITE);
        name.setFont(new Font("SANS_SERIF", Font.BOLD, 18));
        p1.add(name);
        
        JLabel Status = new JLabel("Active");
        Status.setBounds(110, 35, 100, 18);
        Status.setForeground(Color.WHITE);
        Status.setFont(new Font("SANS_SERIF", Font.BOLD, 14));
        p1.add(Status);
        
        a1 = new JPanel();
        a1.setBounds(5,75, 440, 570);
        f.add(a1);
        
        text = new JTextField();
        text.setBounds(5,655,310,40);
        text.setFont(new Font("SANS_SERIF", Font.PLAIN, 16));
        f.add(text);
        
        JButton send = new JButton("Bless Me");
        send.setBounds(320, 655, 123, 40);
        send.setBackground(new Color(7,94,84));
        send.setForeground(Color.WHITE);
        send.addActionListener(this);
        send.setFont(new Font("SANS_SERIF", Font.BOLD, 12));
        f.add(send);
        
        
        f.setSize(450, 700);
        f.setLocation(800,100);
        f.setUndecorated(true);
        f.getContentPane().setBackground(Color.WHITE);
                            
        f.setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        String out = text.getText();
        //JLabel output = new JLabel(out);
        JPanel p2 = formatLabel(out);
        //p2.add(output);
        a1.setLayout(new BorderLayout());
        
        
        JPanel right = new JPanel(new BorderLayout());
        right.add(p2, BorderLayout.LINE_END);
        
        Vertical.add(right);
        Vertical.add(Box.createVerticalStrut(15));
        
        a1.add(Vertical, BorderLayout.PAGE_START);
        try {
            dout.writeUTF(out);
        } catch (IOException ex) {
            Logger.getLogger(Receiver.class.getName()).log(Level.SEVERE, null, ex);
        }
        text.setText("");
             
        f.repaint();
        f.invalidate();
        f.validate();
    }
    
    public static JPanel formatLabel(String out){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
      
      JLabel output = new JLabel("<html><p style=\"width: 150px\">" + out + "</p></html/>");
      output.setFont(new Font("Tahoma", Font.BOLD, 16));
      output.setBackground(new Color(37,211,102));
      output.setOpaque(true);
      output.setBorder(new EmptyBorder(15, 15, 15, 50));
      panel.add(output);
      
      Calendar cal = Calendar.getInstance();
      SimpleDateFormat sdf = new  SimpleDateFormat("HH:mm");
      
      JLabel time = new JLabel();
      time.setText(sdf.format(cal.getTime()));
      panel.add(time);
      
        
      return panel;
    }
    public static void main(String args[])
    {
        new Receiver();
        
        try{
            Socket s = new Socket("127.0.0.1", 6001);
             DataInputStream din = new DataInputStream(s.getInputStream());
             dout = new DataOutputStream(s.getOutputStream());
             while(true)
                {
                    a1.setLayout(new BorderLayout());
                    String msg = din .readUTF();
                    JPanel panel = formatLabel(msg);
                    
                    JPanel left = new JPanel(new BorderLayout());
                    left.add(panel, BorderLayout.LINE_START);
                    Vertical.add(left);
                    
                    Vertical.add(Box.createVerticalStrut(15));
                    
                    a1.add(Vertical, BorderLayout.PAGE_START);
                    f.validate();
                }
                
                
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
