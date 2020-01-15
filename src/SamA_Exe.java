import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class SamA_Exe extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SamA_Exe frame = new SamA_Exe();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		SamA_Raw_Material test = new SamA_Raw_Material();
		SamA_Order test1 = new SamA_Order();
		
		test.read_Material("material.xls");
		test1.read_Order("order.xls");
		
//		for(int i = 0; i < test.production_code.size(); i++) {
//			System.out.println("production code :" + test1.production_code.get(i));
//			//System.out.println("production date :" + test1.alloy2.get(i));		
//		}
//		
//		for(int i = 0; i < test1.production_code.size(); i++) {
//			System.out.println("production code :" + test1.production_code.get(i));
//			//System.out.println("production date :" + test1.alloy2.get(i));		
//		}
	}

	/**
	 * Create the frame.
	 */
	public SamA_Exe() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

}
