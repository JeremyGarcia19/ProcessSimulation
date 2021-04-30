/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author JEREMY
 */
public class Process {
    private String name;
    private int pid;
    private int numInstT;
    private int numInstE;
    private int memory;
    private String status;
    private int memoryLocation;

    public Process(String name, int pid, int numInstT, int memory, int memoryLocation){
        this.name = name;  
        this.pid = pid;
        this.numInstT = numInstT;
        this.memory = memory;
        this.memoryLocation = memoryLocation;
        
    }

    public void setName(String name){
        this.name = name;
    }
    
    public void setPid(int pid){
        this.pid = pid;
    }
    
    public void setNumInstT(int numInstT){
        this.numInstT = numInstT;
    }
    
    public void setNumInstE(int numInstE){
        this.numInstE = numInstE;
    }    
    
    public void setMemory(int memory){
        this.memory = memory;
    }
    
    public void setStatus(String status){
        this.status = status;
    }

    public void setMemoryLocation(int memoryLocation){
        this.memoryLocation = memoryLocation;
    }
    
    public String getName(){
        return this.name;
    }
    
    public int getPid(){
        return this.pid;
    }
    
    public int getNumInstT(){
        return this.numInstT;
    }
    
    public int getNumInstE(){
        return this.numInstE;
    }    
    
    public int getMemory(){
        return this.memory;
    }    
    
    public String getStatus(){
        return this.status;
    }    
    
    public int getMemoryLocation(){
        return this.memoryLocation;
    }   
    
    
    @Override
    public String toString(){
        return "Name: " + this.name + "\nPID: " + this.pid + "\nTotal Instructions: " + this.numInstT + "\nInstructions Executed:" + this.numInstE + "\nAllocated Memory: " + this.memoryLocation + "-" + (this.memoryLocation+this.memory);
    }
    
    
}


