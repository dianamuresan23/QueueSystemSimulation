package strategy;

import java.util.ArrayList;

import task.Server;
import task.Task;

public class ConcreteStrategyQueue implements Strategy{

	public void addTask(ArrayList<Server> servers, Task t) {  //adaug la server-ul cu cele mai putine task-uri
		int size=servers.get(0).getTasks().size();
		for (Server s:servers)  //caut minimul
		{
			if (size>s.getTasks().size())
				size=s.getTasks().size();
		}
		for (int i=0;i<servers.size();i++) {  //adaug la server
		if (servers.get(i).getTasks().size()==size)
			{
			    servers.get(i).addTask(t);
			    break;
			}
		}
		
		
	}
	

}
