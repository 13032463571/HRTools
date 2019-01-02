package layouts;

import events.Myclick;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MyFrame extends Frame
{
    public TextField tf1  ;
    public TextField tf2 = new TextField() ;
    public TextField tf3 = new TextField() ;
    public void lauch()
    {
        this.setBounds(200,200,200,50);
        this.tf1 = new TextField(15);
        this.tf2 = new TextField(15);
        this.tf3 = new TextField(15);
        Label lb = new Label("  + ");
        Button bn = new Button("=");
        this.setLayout(new FlowLayout(5,5,FlowLayout.CENTER));
        this.add(tf1);
        this.add(lb);
        this.add(tf2);
        this.add(bn);
        bn.addActionListener(new Myclick(this));
        this.add(tf3);
        this.setVisible(true);
        this.addWindowListener(
                new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e)
                    {
                        System.exit(-1);
                    }
                }
        );
        this.pack();
    }
}
