package com.baier.cw3;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class NetworkFrame extends JFrame {
	private JPanel panel;
	private DisplayPanel panel2;
	private ArrayList<Junction> junctions = new ArrayList<>();
	private ArrayList<Connector> connectors = new ArrayList<>();
	private ArrayList<Pipe> pipes = new ArrayList<>();
	private JButton loadFileBTN;
	private ArrayList<String> linesOFjunctions;
	private ArrayList<String> linesOFconnectors;
	private ImageIcon icon = new ImageIcon("D:\\Lion\\Pictures\\eli.png");

	public NetworkFrame() {
		setTitle("Network Presenter");
		setIconImage(icon.getImage());
		setSize(800, 600);
		setLayout(new GridLayout(1, 2)); 
		ActionListener clickListener = new ClickListener();
		loadFileBTN = new JButton("Load File");
		loadFileBTN.addActionListener(clickListener);
		panel = new JPanel();
		panel2 = new DisplayPanel();
		panel.add(loadFileBTN);
		add(panel2); 
		add(panel); 
		
	}

	class ClickListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			junctions = new ArrayList<>(); 
			try {
				Scanner loaded = new Scanner(LoadDialog());
				String networkChooser = loaded.nextLine();
				filterFile(loaded);	
				switch (networkChooser) {
					case "Connector Network":
						addToJunctions();
						addToConnectors();
						
						for (Junction x : junctions){
							System.out.println(x.getName());
							System.out.println(x.getxCord());
							System.out.println(x.getyCord());

						}
						for(Connector x : connectors){
							System.out.println(x.getFirstJunction());
							System.out.println(x.getSecondJunction());
						}
						
						
						repaint();
						
						break;
					
					case "Pipe Network":
						addToJunctions();
						addToPipe();
						
						for(Pipe x : pipes){
							System.out.println(x.getFirstJunction());
							System.out.println(x.getSecondJunction());
							System.out.println(x.getDirection());
						}
						
						repaint();
						
						break;

					default:
						System.err.println("theres no such thing as a gruffalo");
						break;
					}
				
				loaded.close();
				
			} catch (NullPointerException e1) {
				System.err.println("You have not selected any File!");
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			
	
		}
	}
	
	public static File LoadDialog() {
		File f = null;
		JFileChooser selectfile = new JFileChooser();
		int returnVal = selectfile.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			f = selectfile.getSelectedFile();
		}
		return f;
	}
	
	private void addToJunctions(){
		
		for (int i = 0; i < linesOFjunctions.size(); i++) {
			Junction j = new Junction();
			Scanner each = new Scanner(linesOFjunctions.get(i)).useDelimiter(",");
			int counter = 0;
			while (each.hasNext()) {
				String word = each.next();
				counter++;
				switch (counter) {
				case 1:
					j.setName(word);
					break;

				case 2:
					j.setxCord(Integer.parseInt(word));
					break;

				case 3:
					j.setyCord(Integer.parseInt(word));
					break;

				default:
					break;
				}
			}

			junctions.add(j);
			each.close();
		}
	}
	
	
	private void addToConnectors(){

		for (int i = 0; i < linesOFconnectors.size(); i++) {
			Connector con = new Connector();
			Scanner each = new Scanner(linesOFconnectors.get(i)).useDelimiter(",");
			int counter = 0;
			while (each.hasNext()) {
				String word = each.next();
				counter++;
				switch (counter) {
				case 1:
					con.setFirstJunction(word);
					break;

				case 2:
					con.setSecondJunction(word);
					break;

				default:
					break;
				}
			}

			connectors.add(con);
			each.close();
		}
	}

	private void addToPipe(){
		

		for (int i = 0; i < linesOFconnectors.size(); i++) {
			Pipe pipe = new Pipe();
			Scanner each = new Scanner(linesOFconnectors.get(i)).useDelimiter(",");
			int counter = 0;
			while (each.hasNext()) {
				String word = each.next();
				counter++;
				switch (counter) {
				case 1:
					pipe.setFirstJunction(word);
					break;

				case 2:
					pipe.setSecondJunction(word);
					break;
				case 3:
					pipe.setDirection(word);

				default:
					break;
				}

				pipes.add(pipe);
				
			}
		}
	}

	
	private void filterFile(Scanner file){
		ArrayList<String> tempArray= new ArrayList<>();
		linesOFjunctions = new ArrayList<>();
		linesOFconnectors = new ArrayList<>();
		while(file.hasNextLine()){
			tempArray.add(file.nextLine());
		}
		String start = "";
		String end   = "";

		for (int search = 0; search <= 2; search++) {
			switch (search) {
			case 0:
				start = "Junctions";
				end = "Junctions END";
				break;
			case 1:
				start = "Connectors";
				end = "Connectors END";
				break;
			case 2:
				start = "Pipes";
				end = "Pipes END";
				break;

			default:
				break;
			}

			int first = 0;
			int last = 0;
			for (int line = 0; line < tempArray.size(); line++) {
				if (tempArray.get(line).equals(start)) {
					first = line + 1;
				}
				if (tempArray.get(line).equals(end)) {
					last = line - 1;
				}
			}
			for (int i = first; i <= last; i++) {
				if (first == 0) {break;}
				if (start.equals("Junctions")) {
					linesOFjunctions.add(tempArray.get(i));
				} else {
					linesOFconnectors.add(tempArray.get(i));
				}
			}
		}

	}
	
	
	public int[] getJunctionsCoordinates(String firstJunction, String secondJunction){
		int[] result = new int[4];
		for(Junction x : junctions){
			if (x.getName().equals(firstJunction)){
				result[0] = x.getxCord();
				result[1] = x.getyCord();
			}
			if (x.getName().equals(secondJunction)){
				result[2] = x.getxCord();
				result[3] = x.getyCord();
			}
		}
		return result;
	}
	
	

	class DisplayPanel extends JPanel {
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;

			BufferedImage welcome = null;
			try {
				welcome = ImageIO.read(new File("D:\\Lion\\Pictures\\00001382.jpg"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (junctions.isEmpty()){
				g2.setColor(Color.YELLOW);
				g2.setFont(new Font("Arial",Font.BOLD,42));
				g2.drawString("WELCOME", 150, 150);
				g2.drawImage(welcome, null , 10, 10);
			}else{
				if (connectors.isEmpty()){
					for (Pipe y : pipes){
						if (y.getDirection().equals("horizontal")){
							g2.setColor(Color.BLUE);
							g2.drawLine(getJunctionsCoordinates(y.getFirstJunction(), y.getSecondJunction())[0]+10,
										getJunctionsCoordinates(y.getFirstJunction(), y.getSecondJunction())[1]+10, 
										getJunctionsCoordinates(y.getFirstJunction(), y.getSecondJunction())[2]+10, 
										getJunctionsCoordinates(y.getFirstJunction(), y.getSecondJunction())[1]+10);
							g2.drawLine(getJunctionsCoordinates(y.getFirstJunction(), y.getSecondJunction())[2]+10,
										getJunctionsCoordinates(y.getFirstJunction(), y.getSecondJunction())[1]+10, 
										getJunctionsCoordinates(y.getFirstJunction(), y.getSecondJunction())[2]+10, 
										getJunctionsCoordinates(y.getFirstJunction(), y.getSecondJunction())[3]+10);
						}
						else if (y.getDirection().equals("vertical")){
							g2.setColor(Color.BLUE);
							g2.drawLine(getJunctionsCoordinates(y.getFirstJunction(), y.getSecondJunction())[0]+10,
										getJunctionsCoordinates(y.getFirstJunction(), y.getSecondJunction())[1]+10, 
										getJunctionsCoordinates(y.getFirstJunction(), y.getSecondJunction())[0]+10, 
										getJunctionsCoordinates(y.getFirstJunction(), y.getSecondJunction())[3]+10);
							g2.drawLine(getJunctionsCoordinates(y.getFirstJunction(), y.getSecondJunction())[0]+10,
										getJunctionsCoordinates(y.getFirstJunction(), y.getSecondJunction())[3]+10, 
										getJunctionsCoordinates(y.getFirstJunction(), y.getSecondJunction())[2]+10, 
										getJunctionsCoordinates(y.getFirstJunction(), y.getSecondJunction())[3]+10);
						}else {
							System.out.println("not today mate");
						}
					}
				}
				for (Connector x : connectors){
					g2.setColor(Color.BLUE);
					g2.drawLine(getJunctionsCoordinates(x.getFirstJunction(), x.getSecondJunction())[0]+10,
								getJunctionsCoordinates(x.getFirstJunction(), x.getSecondJunction())[1]+10, 
								getJunctionsCoordinates(x.getFirstJunction(), x.getSecondJunction())[2]+10, 
								getJunctionsCoordinates(x.getFirstJunction(), x.getSecondJunction())[3]+10);
				}
				for (Junction x : junctions){
				g2.setColor(Color.BLACK);
				g2.setFont(new Font("Ariel", Font.BOLD,23));
				g2.drawString(x.getName(), x.getxCord(), x.getyCord()-10);
				g2.setColor(Color.YELLOW);
				g2.fillOval(x.getxCord(), x.getyCord(), 40, 20);
				}
			}
			junctions.clear();
			connectors.clear();
			pipes.clear();
		}
	}
}