/*	Kathy Zeng
	Date: 4/5/21
	PutItTogether.java
	Description: This program uses CardLayout in a frame.
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

// These pages need to be field variables and MouseListener.
class TogetherPanel extends JPanel //implements ActionListener
{
	//private Image image;
	private String imageName;
	private JButton button;
	//private MainPanel mp;
	private String name;
	private JTextArea message;
	private CardLayout cards;
	private IntroPage ip;
	private HomePage hp;
	//private PicturePage  pp;
	//private PicturePage1 person1;
	//private PicturePage2 person2;
	//private DrawPage dp;
	
	public TogetherPanel()
	{
		//addActionListener(this);
		//image = null;
		// imageName = new String("Couples.jpg");
		button = new JButton("home");
		name = new String("");
		message = new JTextArea();
		cards = new CardLayout();
		setLayout(cards);
		//add(cards);
		//getMyImage();
		ip = new IntroPage();
		add(ip, "Intro page");
		cards.next(this);
		hp = new HomePage();
		add(hp, "HomePage");
		//pp = new PicturePage(this, cards);
		//add(pp, "PicturePage");

		/*person1 = new PicturePage1(this, cards);
		add(person1, "PicturePage1");
		person2 = new PicturePage2(this, cards);
		add(person2, "PicturePage2");
		dp = new DrawPage(this, cards);
		add(dp, "DrawPage");*/
		
	}

	/*public void getMyImage()
	{
		Scanner picture = null;
		File pict = new File(picture);
		try
		{
			picture = ImageIO.read(pict);
		}
		catch(IOException e)
		{
			System.err.println("\n\n\n" + image + " can't be found.\n\n\n\n");
			e.printStackTrace();
		}
	} */
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent ( g );
		//g.setBackground(Color.PINK);
		// g.drawImage();
	}

	class IntroPage extends JPanel
	{
		public IntroPage()
		{
			setLayout(null);
			setBackground(Color.PINK);
			JTextField name = new JTextField("Name", 30);
			name.setBounds(300, 50, 200, 50);
			add(name);

			JTextArea ins = new JTextArea("Intro:", 10, 30);
			ins.setPreferredSize(new Dimension(790, 500) );
			ins.setLineWrap(true);
			ins.setWrapStyleWord(true);
			ins.setBounds(100, 200, 600, 450);
			JScrollPane scroller = new JScrollPane(ins);
			scroller.setBounds(100, 200, 600, 450);
			add(scroller);

			JCheckBox understand = new JCheckBox("I understand it");
			//UnderstandListener ul = new UnderstandListener();
			//understand.addActionListener(ul);
			understand.setSelected(false);
			understand.setBounds(200, 700, 200, 20);
			add(understand);
		}
	}
	
	class HomePage extends JPanel
	{
		public HomePage()
		{
			JLabel label = new JLabel("Welcome " + name);
			Font ft = new Font("Arial", Font.BOLD, 40);
			label.setFont(ft);
			//getContentPane().add(label);
			label.setBounds(10,0,  500,100);
			JTextArea ins = new JTextArea("Intro:", 10, 30);
			ins.setLineWrap(true);
			ins.setWrapStyleWord(true);
			ins.setBounds(100, 200, 600, 450);
			JScrollPane scroller = new JScrollPane(ins);
			scroller.setBounds(100, 200, 600, 350);
			add(scroller);
		}
		
	}

	/*class PicturePage extends JPanel // Needs two variables for subclasses
	{
		private PicturePage pg;
		private CardLayout cards;
		public PicturePage()
		{
		}
		
		public PicturePage(PicturePage pgIn, CardLayout cardsIn)
		{
			pg = pgIn;
			cards = cardsIn;
			JLabel picture = new JLabel("Picture");
			Font font = new Font("Arial", Font.BOLD, 40);
			picture.setFont(font);
		}
	}*/

	/*class PicturePage1 extends JPanel
	{
		private PicturePage1 pg1;
		private CardLayout card;
		public PicturePage1()
		{
		}
		
		public PicturePage1(PicturePage1 pg1In, CardLayout cardIn)
		{
			pg1 = pg1In;
			card = cardIn;
			JLabel name = new JLabel("Name: ");
			add(name);
			JLabel birthDate = new JLabel("Birth date: ");
			add(birthDate);
			JLabel age = new JLabel("Age: ");
			add(age);
			JLabel hobbies = new JLabel("Hobbies: ");
			add(hobbies);
		}

		
	}

	class PicturePage2 extends JPanel
	{
		private PicturePage2 pg2;
		private CardLayout card;

		public PicturePage2()
		{
		}
		
		public PicturePage2(PicturePage2 pg2In, CardLayout cardIn)
		{
			pg2 = pg2In;
			card = cardIn;
			JLabel name = new JLabel("Name: ");
			add(name);
			JLabel birthDate = new JLabel("Birth date: ");
			add(birthDate);
			JLabel age = new JLabel("Age: ");
			add(age);
			JLabel hobbies = new JLabel("Hobbies: ");
			add(hobbies);
		}

		
	}

	class DrawPage extends JPanel
	{
		private DrawPage dp;
		private CardLayout card;
		//private JMenu shapes;
		public DrawPage(DrawPage dpIn, CardLayout cardIn)
		{	
			dp = dpIn;
			card = cardIn;
			//shapes = new JMenu("Shapes");
			//shapes = drawPicture();
		}

		public void drawPicture()
		{
			JPanel menuPanel = new JPanel();
			menuPanel.setLayout(new FlowLayout() );
			JMenuBar bar = new JMenuBar();
			JMenu shapes = new JMenu("Shapes");

			JMenuItem rect = new JMenuItem("Rectangle");
			JMenuItem oval = new JMenuItem("Oval");

			//ShapeMenuHandler smh = new ShapeMenuHandler();		
			//rect.addActionListener(smh);
			//oval.addActionListener(smh);	
	
			shapes.add(rect);
			shapes.add(oval);

			bar.add(shapes, BorderLayout.NORTH);
		}

		
	} */

	/*class UnderstandListener implements ActionListener
	{
		public void actionPerformed(ActionEvent evt)
		{
			//Container next = new Container();
			String command = new String("");
			if(command.equals("I understand it") )
			{
				//next.show(hp, "HomePage");
				// card.next(c);  
			}
		}	
	} */
}
