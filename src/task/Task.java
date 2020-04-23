package task;


public class Task implements Comparable<Task> {
  private int arrivalTime;
  private int processingTime;
  private int waitingTime;
  private int finishingTime;
  private int id;
  public Task() {
	  
  }
  public Task(int id,int arrivalTime,int processingTime)
  {
	  this.arrivalTime=arrivalTime;
	  this.processingTime=processingTime;
	  this.id=id;
  }
  public int getarrivalTime()
  {
	  return this.arrivalTime;
  }
  public int getWaitingTime()
  {
	  return this.waitingTime;
  }
  public int getFinishingTime()
  {
	  return this.finishingTime;
  }
  public void setWaitingTime(int waitingTime )
  {
	  this.waitingTime=waitingTime;
  }
  public void setFinishingTime()
  {
	  this.finishingTime=this.arrivalTime+this.waitingTime+this.processingTime;
  }
  public int getprocessingTime()
  {
	  return this.processingTime;
  }
  public int getfinishTime()
  {
	  return this.arrivalTime+this.processingTime;
  }
  public void setarrivalTime(int arrivalTime)
  {
	  this.arrivalTime=arrivalTime;
  }
  public void setprocessingTime(int processingTime)
  {
	  this.processingTime=processingTime;
  }
  public int getId()
  {
	  return this.id;
  }
  @Override
 public String toString()
  {
	return "Task" + this.id +" ";
  }
 public  String displayTask()
 {
	 return "Task " + this.id+ " arrived at time " + this.arrivalTime +" with processing time "+this.processingTime +" and waits for " + this.waitingTime+" before finishes at time  "+ this.finishingTime;
 }

  public int compareTo(Task t) {   //crescator dupa timpul de sosire
		return -(t.getarrivalTime()-this.arrivalTime);
		
	}

}
