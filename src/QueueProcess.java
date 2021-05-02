
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * This class defines the attributes of a process queue and its methods.
 * Implement the Process class
 * @version 1.0
 * @author JEREMY
 * @since 2021-03-27
 */
public class QueueProcess {
    static private QueueProcess queueInstance = null;
    private ArrayList<Process> queue;
   
    /**
     * Constructor method of the class
     * Initialize the queue
     */
    private QueueProcess(){
        this.queue = new ArrayList<Process>();
    }
    
    /**
     * Method to instantiate a process queue.
     * Uses the Singleton design pattern.
     * If the instance does not exist it is created,
     * Else returns the same instance.
     * @return queueInstance (the instance of the process queue)
     */
    public static QueueProcess getQueue(){
        if(queueInstance == null){
           return queueInstance = new QueueProcess();
        }
        System.out.println("An instance already exists");
        return queueInstance;        
    }
    
    /**
     * Method to add a process to the queue.
     * Add it in the starting position (end of the queue).
     * @param process Process class object
     */
    public void AddProcess(Process process){
        this.queue.add(0,process);
    }
    
    /**
     * Method to print the information of the processes in the queue.
     */
    public void ViewQueue(){
        for (Process p : this.queue){
            System.out.println(p);
        }   
    }
    
    /**
     * Method that shows the queued processes with their id.
     */
    public void graphicQueue(){
        for ( Process p : this.queue){
            System.out.print("<-" + "["+ p.getPid() +"]");
        }
    }
    
    /**
     * Method that indicates how many processes are in the queue.
     * @return queue.size() (Queue size)
     */
    public int NumProcess(){
        return this.queue.size();
    }
    
    /**
     * Method that executes the instructions of a process.
     * Execute 5 process instructions.
     * If the process no longer has instructions, it´s removes from the queue and returns it.
     * Else the process is passed to the end of the queue (position 0).
     * @return aux (null or process removed from queue).
     */
    public Process Execute(){
        Process aux = null;
        if(!this.queue.isEmpty()){        
            aux = this.getFirst();
            aux.setNumInstT(aux.getNumInstT()-5);
            aux.setNumInstE(aux.getNumInstE()+5);            
            if(aux.getNumInstT() > 0){
                this.queue.add(0,aux);
                aux = null;
            }
            this.queue.remove(this.queue.size()-1);
            return aux;
        }
        else{
            System.out.println("The process queue is empty");
        }
        return aux;
    }
    
    /**
     * Method that sends a process to the end of the queue without executing instructions.
     */
    public void Next(){
        Process aux = null;
        if(!this.queue.isEmpty()){
            aux = this.getFirst();
            this.queue.add(0,aux);
            this.queue.remove(this.queue.size()-1);
        }
        else{
            System.out.println("The process queue is empty");
        }
    }    
    
    /**
     * Method that removes a process from the queue.
     * @return aux (null or process removed from queue)
     */
    public Process Kill(){
        Process aux = null;
        if(!this.queue.isEmpty()){
            aux = this.getFirst();
            this.queue.remove(this.queue.size()-1);
            return aux;
        }
        else{
            System.out.println("The process queue is empty");
        } 
        return aux;
    }
    
    /**
     * Method that removes all processes from the queue
     * @return aux (Array List of processes)
     */
    public ArrayList<Process> KillAll(){
        ArrayList<Process> aux = (ArrayList<Process>)this.queue.clone();
        this.queue.clear();
        return aux;
    }
    
    /**
     * Method to get the first process in the queue.
     * @return 
     */
    public Process getFirst(){
        return this.queue.get(this.queue.size()-1);
    }
    
    /**
     * Method to know if the queue is empty.
     * Returns true if the queue is empty
     * @return queue.isEmpty() (isEmpty method value of array list)
     */
    public boolean IsEmpty(){
        return this.queue.isEmpty();
    }
}
