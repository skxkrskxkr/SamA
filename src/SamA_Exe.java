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
		test.read_Material("test.XLS");
		
		for(int i = 0; i < test.production_code.size(); i++) {
			System.out.println("production code :" + test.production_code.get(i));
			System.out.println("production date :" + test.production_date.get(i));
			
		}
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
