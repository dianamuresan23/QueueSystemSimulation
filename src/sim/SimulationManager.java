package sim;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;

import strategy.Scheduler;
import strategy.Scheduler.SelectionPolicy;
import task.Server;
import task.Task;

public class SimulationManager implements Runnable{
	public SelectionPolicy selection=SelectionPolicy.SHORTEST_QUEUE;
	private int timeLimit;
	private int clientsN;
	private int serversNo;
	private int maxProcTime;
	private int minProcTime;
	
	private Scheduler scheduler;
	private SimulatorFrame frame;
	private ArrayList<Task> generatedTasks=new ArrayList<Task>();
	public SimulationManager(SimulatorFrame fr)
	{
		this.frame=fr;
		this.frame.addListener(new Listener());
		
	}
	public SimulationManager(SimulatorFrame frame,int timeLimit,int clientsNo,int queuesNo,int maxTime,int minTime)
	{
		this.frame=frame;
		this.clientsN=clientsNo;
		this.maxProcTime=maxTime;
		this.minProcTime=minTime;
		this.serversNo=queuesNo;
		this.timeLimit=timeLimit;
		generateRandomTasks();
		scheduler=new Scheduler(serversNo,10);
		scheduler.changeStrategy(selection);
	}
	public int getClientsNo()
	{
		return this.clientsN;
	}
	class Listener implements ActionListener{
	public void actionPerformed(ActionEvent e)
		{
			if (e.getSource()==frame.getStart())
			{
				
				SimulationManager sim;
				sim=new SimulationManager(frame,Integer.parseInt(frame.getTimeLimit()),Integer.parseInt(frame.getClientsNo()),Integer.parseInt(frame.getQueuesNo()),Integer.parseInt(frame.getMaxTime()),Integer.parseInt(frame.getMinTime()));
		        frame.openFrame(Integer.parseInt(frame.getQueuesNo()));
				Thread thread =new Thread(sim);
		         thread.start();		
			}
		}
	}
	public void setClients()
	{ this.clientsN=Integer.parseInt(this.frame.getClientsNo());
	}
	public void setServers()
	{ this.serversNo=Integer.parseInt(this.frame.getQueuesNo());
	}
	public void setMaxProcTime()
	{ this.minProcTime=Integer.parseInt(this.frame.getMinTime());
	}
	public void setMinProcTime()
	{ this.maxProcTime=Integer.parseInt(this.frame.getMaxTime());
	}
	public void setTimeLimit()
	{ this.timeLimit=Integer.parseInt(this.frame.getTimeLimit());
	}
	
	public void generateRandomTasks()
	{
		Random rand=new Random();
		for (int i=0;i<clientsN;i++) 
		{
		      int n=0;
		      n= rand.nextInt((maxProcTime - minProcTime) + 1) + minProcTime;
	          int processingTime=n;
	          System.out.println(processingTime);
	          n = rand.nextInt((timeLimit - 1) + 1) + 1;
	          int arrivalTime=n;
	          System.out.println(arrivalTime);
	          Task t=new Task(i+1,arrivalTime,processingTime);
	          System.out.println(t.toString());
	          this.generatedTasks.add(t);
		}
		Collections.sort(generatedTasks);
		System.out.println(generatedTasks);
	    
	}
	void displayFields()
	{
		for(int i=0;i<scheduler.getServers().size();i++)//pentru fiecare server scriu in text field-ul corespunzator
		{
			String str=scheduler.getServers().get(i).toString();
			frame.setTextFields(str,i);
		}
	}
	public void displayRemoved(ArrayList<Task> removed, int currentTime)
	{
		//Collections.sort(removed);
	    for (Task task:removed)
	    {
	    	if (task.getFinishingTime()==currentTime)
	    	{
	    		String ss="Task "+ task.getId() +" was removed";
	    		frame.addLog(ss);
	           // removed.remove(task);
	    	}
	    }	
		
	}
	public void run()
	{
		int currentTime=1;
		ArrayList<Task> deleted=new ArrayList<Task>();
		
		while(currentTime<=timeLimit)
		{
			String s="Time="+currentTime;
			frame.addLog(s);
			ArrayList<Task> sametime=new ArrayList<Task>();
			displayFields();
			System.out.println(currentTime);
			for(Task t:this.generatedTasks)
			{
				if (t.getarrivalTime()==currentTime)
				{
					sametime.add(t);
				}		
			}
			for (Task tt:sametime)
			{
				this.scheduler.dispatchTask(tt);
				frame.addLog(tt.displayTask());
				deleted.add(tt);
				this.generatedTasks.remove(tt);
			}
			displayFields();
			displayRemoved(deleted,currentTime);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			currentTime++;
			
		}
		
		int empty=0;
		
		while(empty==0)
		{
			int nr=0;
		   for (int i = 0; i <scheduler.getServers().size(); i++)
		   { 
			if (!(scheduler.getServers().get(i).getServer().isEmpty()))
			{	
				try {
					displayFields();		
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}	
			}
			else nr++;
		   }
		   if (nr==scheduler.getServers().size())
			   empty=1;
		   }
		
	displayFields();	
		
	}
	

}
