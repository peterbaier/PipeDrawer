package com.baier.cw3;

import javax.swing.JFrame;

public class NetworkViewer {
	public static void main(String[] args) {
		JFrame frame = new NetworkFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}