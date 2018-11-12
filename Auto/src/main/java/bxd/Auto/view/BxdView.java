package bxd.Auto.view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import org.jsoup.Connection;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;

import bxd.Auto.Dao.BuyDAO;
import bxd.Auto.Dao.PlanDAO;
import bxd.Auto.Model.BuyHistory;
import bxd.Auto.Model.Plan;
import bxd.Auto.Util.BetScheduler;
import bxd.Auto.Util.StringUtil;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;

public class BxdView extends JFrame{

	private JPanel contentPane;
	private JPanel personPane;
	private JTextField userNameTxt;
	private JPasswordField passwordTxt;
	private JLabel kaiJiangNum;
	private JLabel lb_welcome;
	private JLabel todayYingli;
	private Connection CON=null;
	private JTextField zhiSun;
	private JTextField zhiYing;
	private PlanDAO pd=new PlanDAO();
	private BuyDAO bd=new BuyDAO();
	private JTable planTable;
	private JTable buyTable;
	private static BxdView frame =null;
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static TimerTask tt=null;
	static Timer t=new Timer();
	private JTextField shouDongNum;
	private JTextField shouDongMuti;
	private JTextField textField_1;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new BxdView();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
					TimerTask timeTask=new TimerTask() {
						@Override
						public void run() {
							frame.renewPlan();
						}
					};
			    	new Timer().schedule(timeTask, 2000,30000);
			    	
			    	
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public BxdView() {
		setTitle("半K段软件v1.0");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 854, 821);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		personPane=new JPanel();
		personPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		personPane.setBounds(0, 0, 837, 115);
		contentPane.add(personPane);
		personPane.setLayout(null);
		
		JLabel lb_userName = new JLabel("用户名");
		lb_userName.setBounds(10, 17, 40, 15);
		personPane.add(lb_userName);
		
		JLabel lb_password = new JLabel("密  码");
		lb_password.setBounds(10, 50, 40, 15);
		personPane.add(lb_password);
		
		userNameTxt = new JTextField();
		userNameTxt.setText("9100z");
		userNameTxt.setBounds(52, 14, 115, 21);
		personPane.add(userNameTxt);
		userNameTxt.setColumns(10);
		
		passwordTxt = new JPasswordField();
		passwordTxt.setToolTipText("");
		passwordTxt.setBounds(52, 47, 115, 21);
		personPane.add(passwordTxt);
		
		contentPane.add(personPane);
		
		lb_welcome = new JLabel("未登录"+" 余额：0.00");
		lb_welcome.setBounds(278, 3, 242, 40);
		personPane.add(lb_welcome);
		
		JButton jb_dlu = new JButton("登  陆");
		jb_dlu.setBounds(177, 13, 87, 23);
		personPane.add(jb_dlu);
		jb_dlu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				jb_logonActionPerformed(evt);
			}
		});
		
		JButton jb_refresh = new JButton("刷  新");
		jb_refresh.setBounds(177, 46, 87, 23);
		personPane.add(jb_refresh);
		jb_refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				renewYueActionPerformed(evt);
			}
		});
		
		zhiSun = new JTextField();
		zhiSun.setText("10");
		zhiSun.setBounds(653, 47, 75, 21);
		personPane.add(zhiSun);
		zhiSun.setColumns(10);
		
		zhiYing = new JTextField();
		zhiYing.setText("330");
		zhiYing.setColumns(10);
		zhiYing.setBounds(653, 14, 75, 21);
		personPane.add(zhiYing);
		
		JLabel label_2 = new JLabel("止 损");
		label_2.setBounds(616, 50, 32, 15);
		personPane.add(label_2);
		
		JLabel label_3 = new JLabel("止 盈");
		label_3.setBounds(616, 17, 32, 15);
		personPane.add(label_3);
		
		JButton startButton = new JButton("开始挂机");
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				startJob();
			}
		});
		startButton.setBounds(741, 13, 87, 23);
		personPane.add(startButton);
		
		JButton stopButton = new JButton("停止挂机");
		stopButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				stopJob();
			}
		});
		stopButton.setBounds(741, 46, 87, 23);
		personPane.add(stopButton);
		
		todayYingli = new JLabel("今日盈利：0.00");
		todayYingli.setBounds(278, 42, 344, 33);
		personPane.add(todayYingli);
		
		shouDongNum = new JTextField();
		shouDongNum.setToolTipText("格式如：0102030405");
		shouDongNum.setBounds(460, 81, 156, 21);
		personPane.add(shouDongNum);
		shouDongNum.setColumns(10);
		
		JButton shDongTou = new JButton("手动投注");
		shDongTou.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String num=shouDongNum.getText();
				String muti=shouDongMuti.getText();
				try {
					JOptionPane.showMessageDialog(null, bd.shDongBet(CON, num, muti));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		shDongTou.setBounds(741, 81, 87, 23);
		personPane.add(shDongTou);
		
		shouDongMuti = new JTextField();
		shouDongMuti.setToolTipText("只能输整数哦！");
		shouDongMuti.setBounds(661, 81, 66, 21);
		personPane.add(shouDongMuti);
		shouDongMuti.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("投注号码");
		lblNewLabel.setBounds(402, 84, 54, 15);
		personPane.add(lblNewLabel);
		
		JLabel label_1 = new JLabel("倍数");
		label_1.setBounds(630, 84, 32, 15);
		personPane.add(label_1);
		
		textField_1 = new JTextField();
		textField_1.setToolTipText("开发中~敬请期待");
		textField_1.setColumns(10);
		textField_1.setBounds(10, 81, 254, 21);
		personPane.add(textField_1);
		
		JButton beiTou = new JButton("倍投设置");
		beiTou.setBounds(276, 80, 87, 23);
		personPane.add(beiTou);
		
		JPanel kaiJiangPan = new JPanel();
		kaiJiangPan.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		kaiJiangPan.setBounds(370, 115, 475, 58);
		contentPane.add(kaiJiangPan);
		kaiJiangPan.setLayout(null);
		
		JLabel label = new JLabel("北京赛车");
		label.setFont(new Font("黑体", Font.PLAIN, 18));
		label.setBounds(7, 17, 72, 23);
		kaiJiangPan.add(label);
		
		kaiJiangNum = new JLabel("");
		kaiJiangNum.setFont(new Font("黑体", Font.PLAIN, 18));
		kaiJiangNum.setBounds(83, 17, 382, 23);
		kaiJiangPan.add(kaiJiangNum);
		
		JScrollPane planScrollPane = new JScrollPane();
		planScrollPane.setBounds(0, 115, 368, 668);
		contentPane.add(planScrollPane);
		
		planTable = new JTable();
		planTable.setFont(new Font("宋体", Font.PLAIN, 14));
		planTable.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"计划方案"
				}
				) {
			boolean[] columnEditables = new boolean[] {
					false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		planScrollPane.setColumnHeaderView(planTable);
		planScrollPane.setViewportView(planTable);
		
		JScrollPane buyScrollPane = new JScrollPane();
		buyScrollPane.setBounds(370, 174, 467, 609);
		contentPane.add(buyScrollPane);
		
		buyTable = new JTable();
		buyTable.setFont(new Font("宋体", Font.PLAIN, 14));
		buyScrollPane.setColumnHeaderView(buyTable);
		buyScrollPane.setViewportView(buyTable);
		buyTable.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"当天投注记录"
				}
				) {
			boolean[] columnEditables = new boolean[] {
					false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
	}
	
	private void jb_logonActionPerformed(java.awt.event.ActionEvent evt) {
		if (StringUtil.isEmpty(userNameTxt.getText())) {
			JOptionPane.showMessageDialog(null, "用户名不能为空");
			return;
		}
		if (StringUtil.isEmpty(passwordTxt.getText())) {
			JOptionPane.showMessageDialog(null, "密码不能为空");
			return;
		}
		try {
			CON=bd.login(userNameTxt.getText(),passwordTxt.getText());
			if(CON!=null) {
					JOptionPane.showMessageDialog(null, "登录成功");
					userNameTxt.setEditable(false);
					passwordTxt.setEditable(false);
					renewBNB();
			}else {
				JOptionPane.showMessageDialog(null, "登录失败");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	private void renewYueActionPerformed(java.awt.event.ActionEvent evt) {
		try {
			renewBNB();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//启动程序的时候执行1次，以后每分钟执行1次，完成
	private void renewPlan() {
		try {
			Plan p=pd.getPlan();
			kaiJiangNum.setText(p.getTopGame());
			
			DefaultTableModel dtm=(DefaultTableModel) planTable.getModel();
			dtm.setRowCount(0); 
			String [] glist=p.getGameList();
			for(String s:glist) {
				Vector v=new Vector();
				v.add(s);
				dtm.addRow(v);
			}
			Date date = new Date(System.currentTimeMillis());
			System.out.println("更新计划成功！当前时间："+sdf.format(date));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	
	//登陆后执行1次v，以后每分钟执行1次，然后，投注成功后执行1次，开奖后执行1次
	//刷新余额和投注记录
	private void renewBNB() throws IOException{
		lb_welcome.setText("\n余额："+bd.getBalance(CON));
		
		DefaultTableModel dtm=(DefaultTableModel) buyTable.getModel();
		dtm.setRowCount(0); 
		
		Date date = new Date(System.currentTimeMillis());
		String time=sdf.format(date).substring(0,10);
		System.out.println("开始和结束时间："+time);
		
		BuyHistory bh=bd.getBuyHistory(CON, userNameTxt.getText(), passwordTxt.getText(),time);
		if(bh!=null) {
			for(String s:bh.getBuyArray()) {
				Vector v=new Vector();
				v.add(s);
				dtm.addRow(v);
			}
			String buySum=bh.getBuySum()==null?"0":bh.getBuySum();
			todayYingli.setText("共投注："+buySum+" "+"中奖总额："+bh.getWinSum());
			System.out.println("————————————————————————————更新投注记录成功！"+time+"————————————————————————————");
		}
	}	
	
	private void stopJob() {
		if(tt!=null) {
			tt.cancel();
			tt=null;
			JOptionPane.showMessageDialog(null, "停止成功！");
		}else {
			System.out.println("已经停止，请勿重复停止！");
			JOptionPane.showMessageDialog(null, "已停止！请勿重复停止！");
		}
	}
	
	private void startJob() {
		if(tt==null) {
			tt=new TimerTask() {
				@Override
				public void run() {
					Date date = new Date(System.currentTimeMillis());
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String time=sdf.format(date);
					System.out.println(time);
					time=time.substring(15, time.length());
					if(time.equals("3:30")||time.equals("8:30")) {
						float zy=Float.parseFloat(zhiYing.getText());
						float zs=Float.parseFloat(zhiSun.getText());
						float ye;
						try {
							ye = Float.parseFloat(bd.getBalance(CON));
							System.out.println("zy"+zy);
							System.out.println("zs"+zs);
							System.out.println("ye"+ye);
							if(ye>=zy||ye<=zs) {
								stopJob();
								JOptionPane.showMessageDialog(null, "挂机任务停止！请注意！");
							}else {
								String tz=bd.doBet2(CON);
								renewBNB();
								System.out.println("投注返回内容 ："+tz);
								if(!tz.equals("{\"msg\":\"下注成功\",\"success\":true}")) {
									stopJob();
									JOptionPane.showMessageDialog(null, "挂机任务停止！请注意！");
								}
							}
						} catch (NumberFormatException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			};
			t.schedule(tt, 2000,1000);
			JOptionPane.showMessageDialog(null, "开启成功！");
		}else {
			JOptionPane.showMessageDialog(null, "已经开启，请不要重复开启！");
		}
	}
}
