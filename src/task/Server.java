package task;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable{
private BlockingQueue<Task> tasks;
private AtomicInteger waitingTime;
private int id;

public Server(int id)
{
	tasks=new ArrayBlockingQueue<Task>(30);
	waitingTime=new AtomicInteger(0);
	this.id=id;
	
}
public void setWaitingTime(int i)
	{
	    AtomicInteger nr=new AtomicInteger(i);
		this.waitingTime=nr;
	}

public int getId()
{
	return this.id;
}
public BlockingQueue<Task> getServer() {
	return tasks;
}
public void addTask(Task n) 
{
	
	try {
		tasks.put(n);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
	n.setWaitingTime(waitingTime.get());   //setez timpul de asteptare al task-ului cu timpul de asteptare al server-ului
	n.setFinishingTime();
	waitingTime.addAndGet(n.getprocessingTime());  //incrementez timpul de asteptare al server-ului cu timpul de procesare al task-ului
}
public void run()
{
	while(true)
	{
		while (tasks.isEmpty()!=true)
		{
		    Task t=tasks.element();
		    if (t!=null) 
		       {
		           try {
			      Thread.sleep(1000*t.getprocessingTime());
		         }catch (InterruptedException e) {
	               e.printStackTrace();
		}
		tasks.remove();
		this.setWaitingTime(this.waitingTime.get()-t.getprocessingTime());
		//timpul de asteptare al server-ului se decrementeaza cu timpul de procesare al task-ului finalizat
				
		       }
		}	
	}
	
}
@Override
public String toString() {
	String str="";
	for (Task t:this.tasks)
	{
		str+=t.toString();
	}
	return str;
}
public BlockingQueue<Task> getTasks()
{
	return this.tasks;
}

}
