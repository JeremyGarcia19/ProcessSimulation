
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author JEREMY
 */
public class QueueProcess {
    static private QueueProcess queueInstance = null;
    private ArrayList<Process> queue;
    private QueueProcess(){
        this.queue = new ArrayList<Process>();
    }
    
    public static QueueProcess getQueue(){
        if(queueInstance == null){
           return queueInstance = new QueueProcess();
        }
        System.out.println("An instance already exists");
        return queueInstance;        
    }
    
    public void AddProcess(Process process){
        this.queue.add(0,process);
    }
    
    public void ViewQueue(){
        for (Process p : this.queue){
            System.out.println(p);
        }   
    }
    
    public void graphicQueue(){
        for ( Process p : this.queue){
            System.out.print("<-" + "["+ p.getPid() +"]");
        }
    }
    
    public int NumProcess(){
        return this.queue.size();
    }
    
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
    
    public ArrayList<Process> KillAll(){
        ArrayList<Process> aux = (ArrayList<Process>)this.queue.clone();
        this.queue.clear();
        return aux;
    }
    
    public Process getFirst(){
        return this.queue.get(this.queue.size()-1);
    }
    
    public boolean IsEmpty(){
        return this.queue.isEmpty();
    }
}
