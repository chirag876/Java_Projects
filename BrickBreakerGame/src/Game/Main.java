package Game;

import javax.swing.JFrame;

public class Main 
{
	public static void main(String[] args) 
	{
		JFrame obj = new JFrame();
		GamePlay gameplay= new GamePlay();
		obj.setBounds(10,10,700,600);
		obj.setTitle("BreakOut Wall");
		obj.setResizable(true);
		obj.setVisible(true);
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		obj.add(gameplay);
 	}
	

}
