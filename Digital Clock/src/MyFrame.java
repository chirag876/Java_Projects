import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
public class MyFrame extends JFrame {
    SimpleDateFormat timeFormat;
    SimpleDateFormat DayFormat;
    SimpleDateFormat DateFormat;
    JLabel timeLabel;
    JLabel DayLabel;

    JLabel DateLabel;
    String time;
    String Day;
    String Date;

    MyFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("My Clock");
        this.setLayout(new FlowLayout());
        this.setSize(350, 200);
        this.setResizable(false);

        timeFormat = new SimpleDateFormat("hh:mm:ss a");
        DayFormat = new SimpleDateFormat("EEEE");
        DateFormat = new SimpleDateFormat("MMMMM dd, yyyy");

        timeLabel = new JLabel();
        timeLabel.setFont(new Font("Verdana", Font.PLAIN,50));
        timeLabel.setForeground(Color.red);
        timeLabel.setBackground(Color.lightGray);
        timeLabel.setOpaque(true);

        DayLabel = new JLabel();
        DayLabel.setFont(new Font("Verdana", Font.PLAIN,35));

        DateLabel = new JLabel();
        DateLabel.setFont(new Font("Verdana", Font.PLAIN,25));


        this.add(timeLabel);
        this.add(DayLabel);
        this.add(DateLabel);
        this.setVisible(true);

        setTime();
    }

    public void setTime() {
        while (true) {
            time = timeFormat.format(Calendar.getInstance().getTime());
            timeLabel.setText(time);

            Day = DayFormat.format(Calendar.getInstance().getTime());
            DayLabel.setText(Day);

            Date = DateFormat.format(Calendar.getInstance().getTime());
            DateLabel.setText(Date);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
