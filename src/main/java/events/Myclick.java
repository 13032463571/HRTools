package events;

import layouts.MyFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Myclick implements ActionListener
{
    MyFrame tf ;
    public Myclick(MyFrame tf )
    {
        this.tf=tf;
    }

    public void actionPerformed(ActionEvent e)
    {
        int  d = Integer.valueOf(tf.tf1.getText())+Integer.valueOf( tf.tf2.getText());
        tf.tf3.setText(String.valueOf(d));
    }
}
