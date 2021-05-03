package SO;

import Process.Process;
import Process.QueueProcess;
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
    private static final int maxMem = 256;
    private int[] globalMemTable;
    private QueueProcess queue;
    private ArrayList<Process> completed;
    private ArrayList<Process> deleted;
    static private OperatingSystem osInstance = null;
    
    /**
     * Constructor method of the class.
     */
    private OperatingSystem(){
        this.globalMemTable = InitializeMemTable();
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
        ArrayList<Integer> memLocations;
        int memory = Memory();        
        if(this.EnoughMem(memory)){          
            Process process = new Process(name, Sequence(), NumInstructions(), memory);
            this.queue.AddProcess(process);
            memLocations = this.UpdateMemTable(process,1);
            //this.showMem();
            process.setMemoryLocation(memLocations);
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
        ShowProcessList(this.completed);
        System.out.println("Processes Killed");
        ShowProcessList(this.deleted);
    }
    
    /**
     * Method that shows the information of the processes and how it is displayed.
     */
    public void PrintQueue(){
        System.out.println("Available Memory:" + this.AvailableMemory());
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
            this.UpdateMemTable(aux,2);
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
            this.UpdateMemTable(aux,2);
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
            this.ShowProcessList(aux);
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
    public void ShowProcessList(ArrayList<Process> list){
        for (Process p : list){
            System.out.println(p.getName());
        }   
    } 
    
    /**
     * Method to initialize global memory table
     * @return mmory (array initialized to 0)
     */
    public static int[] InitializeMemTable(){
        int[] memory = new int[maxMem];
        for (int i = 0; i < maxMem; i++){
            memory[i] = 0;
        }
        return memory;
    }
    
    /**
     * Method that updates the memory table
     * @param process Process created or removed.
     * @param methodOp Method option.
     * @return  memLocations (ArrayList with process memory locations)
     */
    public ArrayList<Integer> UpdateMemTable(Process process, int methodOp){
        int memP = process.getMemory();
        int Pid = process.getPid();
        ArrayList<Integer> memLocations = new ArrayList<>();
        int i=0,j=1;
        while (i < maxMem && j <= memP){
            //Case which a process is created
            if (methodOp == 1 && Pid != this.globalMemTable[i] && this.globalMemTable[i] == 0){
                this.globalMemTable[i] = Pid;               
                memLocations.add(i); //Memory location.
                j++;
            }
            //Case which a process is killed
            if (methodOp == 2 && Pid == this.globalMemTable[i]){
                this.globalMemTable[i] = 0;
            }
            i++;
        }
        if (!memLocations.isEmpty()){
            return memLocations;
        }
        
        return null;
    }
    
    /**
     * Method to know if the memory is full
     * @return boolean
     */
    public boolean isFull(){
        for (int i=0; i<maxMem; i++){
            if(this.globalMemTable[i] == 0){
                return false;
            }           
        }
        return true;
    }
    
    /**
     * Method to know if there is enough memory for a process
     * @param memP Process memory
     * @return boolean
     */
    public boolean EnoughMem(int memP){
        int cont = 0;
        for(int i = 0; i < maxMem; i++){
            if (this.globalMemTable[i] == 0){
                cont ++;
            }
        }
        return cont >= memP;
    }
    
    /**
     * Method to get available memory.
     * @return avMem (available memory)
     */
    public int AvailableMemory(){
        int avMem = 0;
        for (int i = 0; i < maxMem; i++){
            if (this.globalMemTable[i] == 0){
                avMem ++;
            }
        }
        return avMem;
    }
    
    public void showMem(){
        for(int i = 0; i < maxMem; i++){
            System.out.println(this.globalMemTable[i]);
        }
    }
    
}
