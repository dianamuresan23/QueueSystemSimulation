package sim;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class SimulatorFrame extends JFrame{
	
	ArrayList<JTextField> fields=new ArrayList<JTextField>();   //text field-urile in care vor fi afisare serverele
	private JLabel clientsno=new JLabel("Number of Tasks:");
	private JTextField clno=new JTextField(5);
	private JLabel queueno=new JLabel("Number of Servers");
	private JTextField qno=new JTextField(5);
	private JLabel timelim=new JLabel("Time limit of arrival");
	private JTextField tlim=new JTextField(5);
	private JLabel maxtime=new JLabel("Maximum Processing Time");
	private JTextField mxt=new JTextField(5);
	private JLabel mintime=new JLabel("Minimum Processing Time");
	private JTextField mnt=new JTextField(5);
	private JButton start=new JButton("Start");
	JFrame f2;
	JTextArea logArea=new JTextArea(50,30);
	JScrollPane logpane=new JScrollPane(logArea);
	
	
	
	public SimulatorFrame()
	{
		JFrame f=new JFrame();    //frame cu introducere valori
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(new Dimension(600,300));
		
		f2=new JFrame();     //frame cu log si real time servers display
		f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f2.setSize(new Dimension(600,600));
		
		JPanel p1=new JPanel();
		logArea.setFont(new Font("TimesRoman",Font.BOLD,12));
		
		p1.setMaximumSize(new Dimension(40,40));
		p1.setLayout(new GridLayout(0,2));
		clientsno.setFont(new Font("TimesRoman",Font.BOLD,15));
		p1.add(clientsno);
		clno.setBackground(new Color(204, 204, 255));
		clno.setFont(new Font("TimesRoman",Font.BOLD,15));
		p1.add(clno);
		queueno.setFont(new Font("TimesRoman",Font.BOLD,15));
		p1.add(queueno);
		qno.setBackground(new Color(204, 204, 255));
		qno.setFont(new Font("TimesRoman",Font.BOLD,15));
		p1.add(qno);
		timelim.setFont(new Font("TimesRoman",Font.BOLD,15));
		p1.add(timelim);
		tlim.setBackground(new Color(204, 204, 255));
		tlim.setFont(new Font("TimesRoman",Font.BOLD,15));
		p1.add(tlim);
		maxtime.setFont(new Font("TimesRoman",Font.BOLD,15));
		p1.add(maxtime);
		mxt.setFont(new Font("TimesRoman",Font.BOLD,15));
		mxt.setBackground(new Color(204, 204, 255));
		p1.add(mxt);
		mintime.setFont(new Font("TimesRoman",Font.BOLD,15));
		p1.add(mintime);
		mnt.setFont(new Font("TimesRoman",Font.BOLD,15));
		mnt.setBackground(new Color(204, 204, 255));
		p1.add(mnt);
		start.setBackground(new Color(102, 102, 153));
        start.setFont(new Font("TimesRoman",Font.BOLD,15));
        start.setForeground(Color.WHITE);
		p1.add(start);
        p1.setBackground(new Color(153, 153, 255));
		f.setContentPane(p1);
		f.getContentPane().setBackground(new Color(153, 153, 255));
		f.setVisible(true);
		
		
	}
	public JPanel printTextFields(int s)
	{
		
		JPanel pp=new JPanel();
		pp.setLayout(new BoxLayout(pp, BoxLayout.PAGE_AXIS));
		for (int i=0;i<s;i++)    //s=numar de servere
		{
			JTextField tf=new JTextField(30);
			fields.add(tf);
		}
		for (JTextField f:fields)  //pentru fiecare server adaug un text field
		{
			f.setFont(new Font("TimesRoman",Font.BOLD,15));
			f.setBackground(new Color(204, 204, 255));
			pp.add(f);
		}
		pp.setBackground(new Color(204, 204, 255));
		return pp;
	
		
	}
	public void setTextFields(String s,int nr)  //adaug in text field-ul corespunzator continutul servere-lor
	{
				fields.get(nr).setText(s);
	}
	public JPanel printLabels(int s)
	{
		ArrayList<JLabel> labels=new ArrayList<JLabel>();
		JPanel pp=new JPanel();
		pp.setLayout(new BoxLayout(pp, BoxLayout.PAGE_AXIS));
		for (int i=0;i<s;i++)   //s=nr servere
		{
			int nr=i+1;
			String serv="Server "+nr;
			JLabel tf=new JLabel(serv);
			labels.add(tf);
		}
		for (JLabel la:labels)  //pentru fiecare server adaug o eticheta 
		{
			la.setFont(new Font("TimesRoman",Font.BOLD,15));
			pp.add(la);
		}
		pp.setBackground(new Color(204, 204, 255));
		return pp;
	
		
	}
   void addListener(ActionListener listen)	
	{
       start.addActionListener(listen);
	}
	public void openFrame(int s)  //construiesc frame-ul cu serverele si log-ul de evenimente si il fac vizibil
	{
		JPanel p3=new JPanel();
		p3.setLayout(new BoxLayout(p3,BoxLayout.X_AXIS));
		p3.setBackground(new Color(204, 204, 255));
	
		p3.add(this.printLabels(s));
		p3.add(this.printTextFields(s));
		
		JPanel p4=new JPanel();
		p4.setLayout(new BoxLayout(p4,BoxLayout.Y_AXIS));
		p4.add(p3);
		p4.add(logpane);
		f2.setContentPane(p4);
		this.f2.setVisible(true);
	}

    public String getClientsNo()
    {
    	return clno.getText();
    }
    public String getQueuesNo()
    {
    	return qno.getText();
    }
    public String getTimeLimit()
    {
    	return tlim.getText();
    }
    public String getMaxTime()
    {
    	return mxt.getText();
    }
    public String getMinTime()
    {
    	return mnt.getText();
    }
    public JButton getStart()
	{
		return start;
	}
    
   public void addLog(String s)  //scrie in log 
   {
	   logArea.append(s);
	   logArea.append("\n");
   }
   
 
    
}
