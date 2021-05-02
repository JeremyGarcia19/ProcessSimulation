
import java.util.ArrayList;
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Class that defines the attributes of the operating system and its methods.
 * Implement the QueueProcess class.
 * Implementa the Process class.
 * @version 1.0
 * @author JEREMY
 * @since 2021-03-27
 */
public class OperatingSystem {
    private int id;
    private int generalTable;
    private QueueProcess queue;
    private ArrayList<Process> completed;
    private ArrayList<Process> deleted;
    static private OperatingSystem osInstance = null;
    
    /**
     * Constructor method of the class.
     */
    private OperatingSystem(){
        this.generalTable = 256;
        this.id = 0;
        this.queue = QueueProcess.getQueue();
        this.completed = new ArrayList<>();
        this.deleted = new ArrayList<>();
    }
    
    /**
     * Method to get an instance of the OperatingSystem object.
     * Uses the Singleton design pattern.
     * If the instance does not exist it is created,
     * Else returns the same instance.
     * @return osInstance (the instance of the operating system)
     */
    public static OperatingSystem getOS(){
        if(osInstance == null){
           return osInstance = new OperatingSystem();
        }
        System.out.println("An instance already exists");
        return osInstance;        
    }
    
    /**
     * Method to create a process and add it to the queue.
     * Print the queue after adding the process.
     * Decreases memory depending on process memory.
     * @param name Process name.
     */
    public void CreateProcess(String name){
        int memory = Memory();        
        if(this.generalTable-memory >= 0){          
            this.generalTable = this.generalTable-memory;
            Process process = new Process(name, Sequence(), NumInstructions(), memory, this.generalTable);
            this.queue.AddProcess(process);
            PrintQueue();
        }
        else{
            System.out.println("The process cannot create, run or kill other processes");
        }
    }
    
    /**
     * Method that shows the number of queued processes and the name of the completed and killed processes.
     */
    public void SystemStatus(){
        System.out.println("Ready Processes: " + this.queue.NumProcess());
        System.out.println("Processes Complete: ");
        showProcessList(this.completed);
        System.out.println("Processes Killed");
        showProcessList(this.deleted);
    }
    
    /**
     * Method that shows the information of the processes and how it is displayed.
     */
    public void PrintQueue(){
        System.out.println("Available Memory:" + this.generalTable);
        this.queue.ViewQueue();
        this.queue.graphicQueue();
    }
    
    /**
     * Method that indicates the execution of the process at the beginning of the queue.
     * Increase memory if a process no longer has any more instructions to execute.
     */
    public void ExecuteProcess(){
        Process aux;
        aux = this.queue.Execute();
        if(aux != null){
            this.completed.add(aux);
            this.generalTable += aux.getMemory();
        }
        this.queue.graphicQueue();
    }
    
    /**
     * Method that shows process information at the beginning of the queue.
     */
    public void CurrentProcess(){
        if(!this.queue.IsEmpty()){
            System.out.println(this.queue.getFirst());
        }else{
            System.out.println("The process queue is empty");
        }
    }
    
    /**
     * Method that indicates that the current process should move to the end of the queue.
     * Shows us how the queue is displayed after.
     */
    public void NextProcess(){
        this.queue.Next();
        this.queue.graphicQueue();        
    }
    
    /**
     * Indicates that the current process should exit the queue.
     * Increase memory depending on process memory.
     * Shows us how the queue is displayed after.
     */
    public void KillProcess(){
        Process aux;
        aux = this.queue.Kill();
        if(aux != null){
            this.deleted.add(aux);
            this.generalTable += aux.getMemory();
        }
        this.queue.graphicQueue();
    }
    
    /**
     * Method that indicates that all processes should be terminated.
     * Shows the name of all processes killed.
     */
    public void KillAllProcess(){
        if(!this.queue.IsEmpty()){
            ArrayList<Process> aux = this.queue.KillAll();
            System.out.println("Processes Killed");
            this.showProcessList(aux);
        }else{
            System.out.println("The process queue is empty");
        }
    }
    
    /**
     * Method that generates sequential integers.
     * @return id (Process id)
     */
    private int Sequence(){
        return ++this.id;
    } 
    
    /**
     * Method that generates a random number between 10 and 30.
     * @return Number of instructions that the process will have.
     */
    private int NumInstructions(){
        Random numAl = new Random();
        //random num between 10 and 30
        return numAl.nextInt(30-10+1) + 10;
    }
    
    /**
     * Method that returns the size in memory that a process occupies.
     * @return Process memory.
     */
    private int Memory(){
        Random numAl = new Random();
        int memArray[] = {32,64,128,256,512};
        int index = numAl.nextInt(4);
        return memArray[index];
    }
    
    /**
     * Method that displays the process names in an array list.
     * @param list Process array list.
     */
    public void showProcessList(ArrayList<Process> list){
        for (Process p : list){
            System.out.println(p.getName());
        }   
    }    
}
