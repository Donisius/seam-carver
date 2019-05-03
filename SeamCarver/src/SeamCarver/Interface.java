package SeamCarver;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import edu.princeton.cs.algs4.Picture;

public class Interface implements ActionListener{

	JTextField tf1,tf2;
	JButton b1,b2;
	
	Interface(){
		
		JFrame f = new JFrame();
		JLabel l1,l2;
		l1 = new JLabel("Enter the file path of the image");
		l1.setBounds(165,20,200,30);
		tf1 = new JTextField();
		tf1.setBounds(175,50,150,20);
		
		l2 = new JLabel("Enter number of seams to remove");
		l2.setBounds(155,100,200,30);
		tf2 = new JTextField();
		tf2.setBounds(175,130,150,20); 
        
        b1 = new JButton("Horizontal");
        b1.setBounds(50,200,100,50); 
        b2 = new JButton("Vertical");
        b2.setBounds(350,200,100,50);  
        
        b1.addActionListener(this);  
        b2.addActionListener(this); 
        f.add(tf1);f.add(tf2);f.add(b1);f.add(b2);f.add(l1);f.add(l2); 
        f.setSize(500,350);  
        
        f.setLayout(null);  
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	public void actionPerformed(ActionEvent e) {
		String s1 = tf1.getText();
		String s2 = tf2.getText();
		int a = Integer.parseInt(s2);
		
		Picture picture = new Picture(s1);
		
		picture.show();
		
		if(e.getSource() == b2) {
			for(int i = 0; i < a; i++) {
				
				picture = SeamCarver.getNextVerticalPicture(picture);
				
			}
		}
		
		else if(e.getSource() == b1) {
			for(int i = 0; i < a; i++) {
				
				picture = SeamCarver.getNextHorizontalPicture(picture);
				
			}
		}
		
		picture.show();
		
	}
}
