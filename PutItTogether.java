/*	Kathy Zeng
	Date: 4/5/21
	PutItTogether.java
	Description: This program uses CardLayout in a frame and use a bunch
	of JPanels for every page.
*/

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.CardLayout;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.Container;
import java.awt.Image;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import javax.swing.JFrame;	
import javax.swing.JPanel;

import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PutItTogether
{
	public static void main(String[] args) 
	{
		PutItTogether pit = new PutItTogether();
		pit.run();
	}

	// Creates a frame with .
	public void run() 
	{
		JFrame frame = new JFrame ("PutItTogether");
		frame.setSize(800, 800);
		frame.setLocation(0, 0);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		TogetherPanel tp = new TogetherPanel();
		frame.getContentPane().add(tp);
		frame.setVisible(true);
		frame.setResizable(false);
	}
}

class TogetherPanel extends JPanel //implements ActionListener//, MouseListener
{
	private Image image;
	private String imageName;
	private JButton button;
	private ButtonGroup bg;
	private String name;
	private JTextArea message;
	private CardLayout cards;
	private JPanel introPanel; // Panel for intro page
	private JPanel homePanel; // Panel for home page
	private JPanel picturePanel; // Panel for picture page
	private JPanel picturePage1Panel; // Panel for person 1 page
	private JPanel picturePage2Panel; // Panel for person 2 page
	private JPanel drawPanel; // Panel for drawing
	private IntroPage ip;
	private HomePage hp;
	private PicturePage  pp;
	private PicturePage1 person1;
	private PicturePage2 person2;
	private DrawPage dp;
	private Container con;
	private JButton buttonName;
	private Color color;
	private JCheckBox understand;
	private boolean rectDraw;
	private boolean ovalDraw;

	public TogetherPanel()
	{
		cards = new CardLayout();
		setLayout(cards);
		//rectDraw = false;
		//ovalDraw = false;
		//addActionListener(this);
		image = null;
		imageName = new String("wedding.jpg");
		button = new JButton("home");
		bg = new ButtonGroup();
		name = new String("");
		message = new JTextArea();
		getMyImage();
		introPanel = new JPanel();
		homePanel = new JPanel();
		picturePanel = new JPanel();
		picturePage1Panel = new JPanel();
		picturePage2Panel = new JPanel();
		drawPanel = new JPanel();
		ip = new IntroPage();
		add(ip, "IntroPage");
		cards.next(this);
		hp = new HomePage();
		add(hp, "HomePage");
		//pp = new PicturePage();
		//add(pp, "PicturePage");
		//person1 = new PicturePage1();
		//add(person1, "PicturePage1");
		//person2 = new PicturePage2();
		//add(person2, "PicturePage2");
		//dp = new DrawPage();
		//add(dp, "DrawPage");
		//con = frame.getContentPane();
		//cards.last(this);
		con = new Container();
		//add(con);
		//addActionListener(this);
		//addMouseListener(this);
		//color = new Color(red, green, blue);
	}
	
	// Gets my image file for couples are JaLisa E. Jefferson and Cory Jefferson.
	public void getMyImage()
	{
		File pict = new File(imageName);
		try
		{
			image = ImageIO.read(pict);
		}
		catch(IOException e)
		{
			System.err.println("\n\n\n" + imageName + " can't be found.\n\n\n\n");
			e.printStackTrace();
		}
	} 

	// Draws my image.
	public void paintComponent(Graphics g)
	{
		super.paintComponent ( g );
		//g.setBackground(Color.PINK);
		// g.drawImage();
	}

	// IntroPage has a null layout, prints a welcome message, and info to
	// all of the following pages in a JTextArea. JTextField as one's name. 
	// Has a checkbox to make sure user understand instructions.
	public class IntroPage extends JPanel
	{
		public IntroPage()
		{
			setLayout(null);
			setBackground(Color.PINK);
			JTextField name = new JTextField("Name", 30);
			name.setBounds(300, 50, 200, 50);
			add(name);

			JTextArea ins = new JTextArea("Intro: ", 10, 30);
			ins.setText("Welcome to PutItTogether.java\n\nIf you understand"
				+ " all the information, you check the box that takes to "
				+ "homepage. In homepage panel, a JTextArea is being copied"
				+ " from the last page. A user either click one of a radio"
				+ " button to see info about a couple or to make some colors"
				+ " and draw some shapes. First radio button takes to an image"
				+ " to let a user click one of a person to view their info."
				+ " For two info panels, they have their picture with their"
				+ " info in it. Second radio button takes to a drawing panel.");
			ins.setPreferredSize(new Dimension(790, 500) );
			ins.setLineWrap(true);
			ins.setWrapStyleWord(true);
			ins.setBounds(100, 200, 600, 450);
			JScrollPane scroller = new JScrollPane(ins);
			scroller.setBounds(100, 200, 600, 450);
			add(scroller);

			JLabel names = new JLabel("Name");
			names.setBounds(260, 50, 150, 50);
			add(names);
			understand = new JCheckBox("I understand it");
			UnderstandListener ul = new UnderstandListener();
			understand.addActionListener(ul);
			understand.setSelected(false);
			understand.setBounds(100, 700, 200, 20);
			add(understand);
			button.setBounds(400, 700, 75, 50);
			add(button);
		}
	}

	// Prints a welcome message with a name, and info to all of the following pages
	// in a JTextArea. Two radio buttons are for seeing a person's info
	// or drawing a shape.
	public class HomePage extends JPanel
	{
		public HomePage()
		{
			setLayout(new FlowLayout() );
			JLabel label = new JLabel("Welcome " + name);
			Font ft = new Font("Arial", Font.BOLD, 40);
			label.setFont(ft);
			//getContentPane().add(label);
			label.setBounds(10,0,  500,100);

			JTextArea ins = new JTextArea("Intro:", 10, 30);
			ins.setText("Welcome to PutItTogether.java\n\nIf you understand"
				+ " all the information, you check the box that takes to "
				+ "homepage. In homepage panel, a JTextArea is being copied"
				+ " to the next page. A user either click one of a radio"
				+ " button to see info about a couple or to make some colors"
				+ " and draw some shapes. First radio button takes to an image"
				+ " to let a user click one of a person to view their info."
				+ " For two info panels, they have their picture with their"
				+ " info in it. Second radio button takes to a drawing panel.");
			ins.setLineWrap(true);
			ins.setWrapStyleWord(true);
			ins.setBounds(100, 200, 600, 450);

			JLabel select = new JLabel("Please select which page you" +
				" would like to see.");
			label.setFont(ft);
			label.setBounds(10, 450, 10, 800);

			JScrollPane scroller = new JScrollPane(ins);
			scroller.setBounds(100, 200, 600, 350);
			add(scroller);
	
			JRadioButton info = new JRadioButton("To see information about friend and me.");	// construct button  
			bg.add(info);					// add button to panel	
			info.setBounds(10, 550, 10, 100);
			RButton1Listener rb1 = new RButton1Listener(); // add listener to JRadioButton
			info.addActionListener(rb1); // add JRadioButton to Panel 	
	
			JRadioButton create = new JRadioButton("To make some colors and draw some shapes.");	// construct button  
			bg.add(create);		// add b2utton to panel	
			create.setBounds(10, 650, 10, 200);
			RButton2Listener rb2 = new RButton2Listener();
			create.addActionListener(rb2); 		// add listener to button
			homePanel.add(create);
		}
		
	}

	// Draws an image of a couple from feet to toes. A user clicks on one
	// of a person.
	class PicturePage extends JPanel // Needs two variables for subclasses
	{
		private int xpos; // center the picture in the panel for x-values.
		private int ypos; // center the picture in the panel for y-values.
		
		public PicturePage()
		{
			setLayout(new BorderLayout() );
			xpos = 250;
			ypos = 300;
			JLabel picture = new JLabel("Picture");
			Font font = new Font("Arial", Font.BOLD, 40);
			picture.setFont(font);
			JButton picButton = new JButton("home");
			System.out.println(picButton);
			add(picture, BorderLayout.NORTH);
			add(picButton, BorderLayout.SOUTH);
			//addMouseListener(this);
		}

		public void paintComponent(Graphics g)
		{
			super.paintComponent ( g );
			getMyImage();
			g.drawImage(image, 200, 200, 500, 400, this);
		}
		
		public void mousePressed(MouseEvent evt)
		{
			requestFocusInWindow();
			xpos = evt.getX() - 110; // these numbers put the click about the center
			ypos = evt.getY() - 150; // of the picture.
			cards.next(this);
			repaint();
		}

		public void mouseReleased(MouseEvent evt) {}
		public void mouseClicked(MouseEvent evt) {}
		public void mouseEntered(MouseEvent evt) {}
		public void mouseExited(MouseEvent evt) {}
	}

	// The page shows a person's info with the picture.
	class PicturePage1 extends JPanel
	{
		public PicturePage1()
		{
			buttonName = new JButton("<html> <center> See info for <br>" +
				"the other person </center> </html>");
			add(buttonName);
			JLabel name = new JLabel("Name: JaLisa E. Jefferson\n");
			add(name);
			JLabel birthDate = new JLabel("Birth date: March 13, 1982\n");
			add(birthDate);
			JLabel age = new JLabel("Age: 39\n");
			add(age);
			JLabel hobbies = new JLabel("Hobbies: Playing games, Reading,"
				+ " Cooking and Hiking");
			add(hobbies);
			add(button, BorderLayout.SOUTH);
		}

		public void paintComponent(Graphics g)
		{
			super.paintComponent ( g );
			getMyImage();
			g.drawImage(image, 200, 100, 300, 300, 212, 185, 362, 872, this);
		}
	}

	// The page shows an another person's info with the picture.
	class PicturePage2 extends JPanel
	{
		public PicturePage2()
		{
			buttonName = new JButton("<html> <center> See info for <br>" +
				"the other person </center> </html>");
			add(buttonName);
			JLabel name = new JLabel("Name: Cory Jefferson");
			add(name);
			JLabel birthDate = new JLabel("Birth date: April 3, 1988");
			add(birthDate);
			JLabel age = new JLabel("Age: 33");
			add(age);
			JLabel hobbies = new JLabel("Hobbies: Hiking, Cooking,"
				+ " playing in beach, and hang out with friends");
			add(hobbies);
			add(button, BorderLayout.SOUTH);
		}

		public void paintComponent(Graphics g)
		{
			super.paintComponent ( g );
			getMyImage();
			g.drawImage(image, 200, 100, 300, 300, 333, 62, 591, 888, this);
		}
	}

	// Draws a shape either rectangle or oval and user gets to decide
	// what color(s) to use with sliders.
	class DrawPage extends JPanel
	{
		
		public DrawPage()
		{
			setLayout(new GridLayout(1, 2) );
			drawPicture();
			add(button, BorderLayout.SOUTH);
			drawPanel();
		}

		public void drawPicture()
		{
			JPanel leftPanel = new JPanel();
			//leftPanel.setLayout(new FlowLayout() );
			JMenuBar bar = new JMenuBar();
			JMenu shapes = new JMenu("Shapes");
			JMenuItem rect = new JMenuItem("Rectangle");
			JMenuItem oval = new JMenuItem("Oval");
			
			ShapeMenuHandler smh = new ShapeMenuHandler();		
			rect.addActionListener(smh);
			oval.addActionListener(smh);	
	
			shapes.add(rect);
			shapes.add(oval);

			bar.add(shapes);
			add(bar);
			JLabel red = new JLabel("Red");
			red.setFont(new Font("Serif", Font.BOLD, 20));
			add(red);
			JSlider slider1 = new JSlider(0, 255, 255, 255);
			slider1.setMajorTickSpacing(5);	// create tick marks on slider every 5 units
			slider1.setPaintTicks(true);
			slider1.setLabelTable( slider1.createStandardLabels(5) ); // create labels on tick marks
			slider1.setPaintLabels(true);
			slider1.setOrientation(JSlider.HORIZONTAL);
			SliderListener slistener1 = new SliderListener();
			slider1.addChangeListener(slistener1);
			add(slider1);

			JLabel green = new JLabel("Green");
			green.setFont(new Font("Serif", Font.BOLD, 20));
			add(green);
			JSlider slider2 = new JSlider(0, 0, 255, 0);
			slider2.setMajorTickSpacing(5);	// create tick marks on slider every 5 units
			slider2.setPaintTicks(true);
			slider2.setLabelTable( slider2.createStandardLabels(5) ); // create labels on tick marks
			slider2.setPaintLabels(true);
			slider2.setOrientation(JSlider.HORIZONTAL);
			SliderListener2 slistener2 = new SliderListener2();
			slider2.addChangeListener(slistener2);
			add(slider2);

			JLabel blue = new JLabel("Blue");
			blue.setFont(new Font("Serif", Font.BOLD, 20));
			add(blue);
			JSlider slider3 = new JSlider(0, 255, 255, 255);
			slider3.setMajorTickSpacing(5);	// create tick marks on slider every 5 units
			slider3.setPaintTicks(true);
			slider3.setLabelTable( slider3.createStandardLabels(5) ); // create labels on tick marks
			slider3.setPaintLabels(true);
			slider3.setOrientation(JSlider.HORIZONTAL);
			SliderListener3 slistener3 = new SliderListener3();
			slider3.addChangeListener(slistener3);
			add(slider3);
		}

		public void drawPanel()
		{
			JPanel rightPanel = new JPanel();
			rightPanel.setLayout(new FlowLayout() );
			JScrollPane scroller = new JScrollPane(rightPanel);
			scroller.setBounds(475, 70, 25, 100);
			add(scroller);
		}

		/// Need 2 variables for adjusting size
		public void paintComponent(Graphics g)
		{
			super.paintComponent ( g );
			int xvalue = 50;
			int yvalue = 50;
			JPanel rightPanel = new JPanel();
			rightPanel.setLayout(new FlowLayout() );
			JScrollPane scroller = new JScrollPane(rightPanel);
			scroller.setBounds(0, 100, 25, 100);
			add(scroller);
			//xvalue = ;
			if(rectDraw)
			{
				g.fillRect(xvalue, yvalue, 100, 100);
				System.out.println("Hello");
				repaint();
			}
			else if(ovalDraw)
			{
				g.fillOval(xvalue, yvalue, 100, 100);
				repaint();
			}
			//System.out.println(rectDraw);
			//System.out.println(ovalDraw);
		}
	} 
		
	class UnderstandListener implements ActionListener
	{
		public void actionPerformed(ActionEvent evt)
		{
			//Container next = new Container();

			String command = evt.getActionCommand();
			if(command.equals("home") )
			{
				understand.setSelected(true);
				//cards.show(hp, "HomePage");  
				cards.next(con);
				//System.out.println("Hello");
			}
		}	
	} 

	class RButton1Listener implements ActionListener
	{
		public void actionPerformed(ActionEvent evt)
		{
			String command = evt.getActionCommand();
			if(command.equals("To see information  about friend and me.") )
			{
				cards.next(con);
				//cards.show(pp, "PicturePage");  
			}
		}
	}

	class RButton2Listener implements ActionListener
	{
		public void actionPerformed(ActionEvent evt)
		{
			String command = evt.getActionCommand();
			if(command.equals("To make some colors and draw some shapes.") )
			{
				cards.next(con);
				//cards.show(dp, "DrawPage");  
			}
		}
	}

	class ShapeMenuHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent evt)
		{
			String command = evt.getActionCommand();
			if(command.equals("Rectangle") )
			{
				rectDraw = true;
				ovalDraw = false;
			}
			else if(command.equals("Oval") )
			{
				ovalDraw = true;
				rectDraw = false;
			}
		}
	}

	class SliderListener implements ChangeListener
	{
		public void stateChanged (ChangeEvent evt) 
		{
			setBackground(color);
		}
	}

	class SliderListener2 implements ChangeListener
	{
		public void stateChanged (ChangeEvent evt) 
		{
			setBackground(color);
		}
	}

	class SliderListener3 implements ChangeListener
	{
		public void stateChanged (ChangeEvent evt) 
		{
			setBackground(color);
		}
	}
}
