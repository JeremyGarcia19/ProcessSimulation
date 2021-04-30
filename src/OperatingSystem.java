
import java.util.ArrayList;
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author JEREMY
 */
public class OperatingSystem {
    private int id;
    private int generalTable;
    private QueueProcess queue;
    private ArrayList<Process> completed;
    private ArrayList<Process> deleted;
    
    static private OperatingSystem osInstance = null;
    
    private OperatingSystem(){
        this.generalTable = 256;
        this.id = 0;
        this.queue = QueueProcess.getQueue();
        this.completed = new ArrayList<>();
        this.deleted = new ArrayList<>();
    }
    
    public static OperatingSystem getOS(){
        if(osInstance == null){
           return osInstance = new OperatingSystem();
        }
        System.out.println("An instance already exists");
        return osInstance;        
    }
    
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
    
    public void SystemStatus(){
        System.out.println("Ready Processes: " + this.queue.NumProcess());
        System.out.println("Processes Complete: ");
        showProcessList(this.completed);
        System.out.println("Processes Killed");
        showProcessList(this.deleted);
    }
    
    public void PrintQueue(){
        System.out.println("Available Memory:" + this.generalTable);
        this.queue.ViewQueue();
        this.queue.graphicQueue();
    }
    
    public void ExecuteProcess(){
        Process aux;
        aux = this.queue.Execute();
        if(aux != null){
            this.completed.add(aux);
            this.generalTable += aux.getMemory();
        }
        this.queue.graphicQueue();
    }
    
    public void CurrentProcess(){
        if(!this.queue.IsEmpty()){
            System.out.println(this.queue.getFirst());
        }else{
            System.out.println("The process queue is empty");
        }
    }
    
    public void NextProcess(){
        this.queue.Next();
        this.queue.graphicQueue();        
    }
    
    public void KillProcess(){
        Process aux;
        aux = this.queue.Kill();
        if(aux != null){
            this.deleted.add(aux);
            this.generalTable += aux.getMemory();
        }
        this.queue.graphicQueue();
    }
    
    public void KillAllProcess(){
        if(!this.queue.IsEmpty()){
            ArrayList<Process> aux = this.queue.KillAll();
            System.out.println("Processes Killed");
            this.showProcessList(aux);
        }else{
            System.out.println("The process queue is empty");
        }
    }
    
    
    private int Sequence(){
        return ++this.id;
    } 
    
    private int NumInstructions(){
        Random numAl = new Random();
        //random num between 10 and 30
        return numAl.nextInt(30-10+1) + 10;
    }
    
    private int Memory(){
        Random numAl = new Random();
        int memArray[] = {32,64,128,256,512};
        int index = numAl.nextInt(4);
        return memArray[index];
    }
    
    public void showProcessList(ArrayList<Process> list){
        for (Process p : list){
            System.out.println(p.getName());
        }   
    }
    

    
    
}
