package strategy;

import java.util.ArrayList;

import task.Server;
import task.Task;

public interface Strategy {
	public void addTask(ArrayList<Server> servers ,Task t);

}
