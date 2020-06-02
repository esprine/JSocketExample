package server;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


public class ServerPanel extends JFrame {
	String[][] example = 
		{
				{"/127.0.0.1", "/192.168.0.21", "127.0.0.3", "127.0.0.4"},
				{"127.0.0.5", "127.0.0.6", "127.0.0.7", "127.0.0.8"},
				{"0", "127.0.0.9", "127.0.0.10", "127.0.0.11"}
		};
	
	int[][] color_ary = 
		{
				{1, 1, 1, 1},
				{1, 1, 1, 1},
				{0, 1, 1, 1}
		};
	
	static final long serialVersionUID = 0;
	private JPanel innerPanel = new JPanel();
	private List<?>[] list;
	private int maxSize;
	
	public void init() {
		this.dispose();
		List<?>[] list = new List<?>[color_ary.length];
		System.out.println(color_ary.length);
		for (int i = 0; i < list.length; i++) {
			list[i] = Arrays.stream(color_ary[i]).boxed().collect(Collectors.toList());
		}
		
		maxSize = list[0].size();
		for(List<?> i : list) {
			if (i.size() > maxSize) {
				maxSize = i.size();
			}
		}
		
		this.setSize(80 * maxSize, 40 + 80 * list.length);
		this.setTitle("Server Monitor");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		innerPanel.setLayout(new GridLayout(list.length, 3));
		innerPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		innerPanel.setBackground(Color.LIGHT_GRAY);
		
		this.add(innerPanel);
		this.list = list;
		this.initComp();
		this.setVisible(true);
	}
	
	public void initComp() {
		for (int i = 0; i < list.length; i++) {
			List<?> tempList = (List<?>) list[i];
			
			for (int j = 0; j < maxSize; j++) {
				JLabel lbl = new JLabel("");
				lbl.setOpaque(true);
				try {
					if (tempList.get(j) instanceof Integer
						&& (Integer) tempList.get(j) == 0) {
						lbl.setBackground(Color.BLACK);	
					} else if ((Integer) tempList.get(j) == 2) {
						lbl.setBackground(Color.RED);
					} else {
						lbl.setBackground(Color.WHITE);
					}
					lbl.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 3));
					innerPanel.add(lbl);
				} catch (Exception e) {
					lbl.setBackground(Color.LIGHT_GRAY);
					lbl.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 3));
					innerPanel.add(lbl);
					continue;
				}
			}
		}
	}
	
	public ServerPanel() {
		dispose();
		this.init();
	}
	
	public ServerPanel (String ip_addr) throws Exception {
		this.receiveIP(ip_addr);
		this.init();
	}
	
	public ServerPanel (String ip_addr, ServerPanel before) throws Exception {
		before.dispose();
		this.receiveIP(ip_addr);
		for (int i = 0; i < example.length; i++) {
			for (int j = 0; j < example[i].length; j++) {
				System.out.print(color_ary[i][j]);
			}
		}
		this.init();
	}
	
	public synchronized void receiveIP (String ip_addr) throws Exception {
		for (int i = 0; i < example.length; i++) {
			for (int j = 0; j < example[i].length; j++) {
				  if (example[i][j].equals(ip_addr)) {
					  System.out.println("ip : " + ip_addr);
					  color_ary[i][j]++;
				  }
			}
		}
	}
}
