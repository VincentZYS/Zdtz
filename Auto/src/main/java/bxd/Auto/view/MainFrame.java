package bxd.Auto.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private JPanel pPane;
	private JButton btnNewButton;
	private JLabel lblNewLabel;
	private static TimerTask tt=null;
	private static Timer t=new Timer();
	static StringBuilder sb=new StringBuilder();
	private JButton btnNewButton_1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 937, 809);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		pPane = new JPanel();
		pPane.setBounds(0, 0, 878, 71);
		contentPane.add(pPane);
		pPane.setLayout(null);
		
		btnNewButton = new JButton("开始");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(tt==null) {
					tt = new TimerTask() {
						@Override
						public void run() {
							Date date = new Date(System.currentTimeMillis());
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							
							String time=sdf.format(date)+"<br>";
							String kaitou="<html><body>";
							String jiewei="</body></html>";
							
							sb.append(time);
							String sets="";
							if(sb.length()==483) {
								System.out.println("sb的长度"+sb.toString());
								sets=kaitou+sb.delete(0, 23)+jiewei;
								System.out.println("sb的长度"+sb.toString());
							}else {
								sets=kaitou+sb.toString()+jiewei;
							}
							lblNewLabel.setText(sets);
							
							System.out.println(sets);
						}
					};
					
					t.schedule(tt, 2000,1000);
				}else {
					System.err.println("已经启动，请勿重复开启！");
				}
			}
		});
		btnNewButton.setBounds(10, 10, 93, 23);
		pPane.add(btnNewButton);
		
		btnNewButton_1 = new JButton("停止");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(tt!=null) {
					tt.cancel();
					tt=null;
				}else {
					System.out.println("已经停止，请勿重复停止！");
				}
			}
		});
		btnNewButton_1.setBounds(129, 10, 93, 23);
		pPane.add(btnNewButton_1);
		
		lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(12, 75, 899, 695);
		contentPane.add(lblNewLabel);
	}
}
