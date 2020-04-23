package strategy;

import java.util.ArrayList;

import task.Server;
import task.Task;

public class Scheduler {
	private ArrayList<Server> servers=new ArrayList<Server>();
	private int maxNoServers;
	private int maxTasksperServer;
	private Strategy strategy;
	public enum SelectionPolicy{
		SHORTEST_QUEUE,SHORTEST_TIME;
	}
	public Scheduler(int maxNoServers,int maxTasksperServer)
	{
		for (int i=0;i<maxNoServers;i++)
		{
			Server s=new Server(i);
			servers.add(s);
			Thread thread=new Thread(s);
			thread.start();
		}

	}
	public void changeStrategy(SelectionPolicy policy)
	{
		if (policy==SelectionPolicy.SHORTEST_QUEUE)
		{
			strategy=new ConcreteStrategyTime();
		}
		if (policy==SelectionPolicy.SHORTEST_QUEUE)
		{
			strategy=new ConcreteStrategyQueue();
		}
	}
	public void dispatchTask(Task t)
	{
		this.strategy.addTask(this.servers, t);
	}
	public ArrayList<Server> getServers()
	{
		return this.servers;
	
	}


}
