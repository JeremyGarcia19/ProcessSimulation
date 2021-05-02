/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * This class defines the attributes of a process and its methods.
 * @version 1.0
 * @author JEREMY
 * @since 2021-03-27
 */
public class Process {
    private String name;
    private int pid;
    private int numInstT;
    private int numInstE;
    private int memory;
    private String status;
    private int memoryLocation;

    /**
     * Constructor Method
     * @param name Process name
     * @param pid   Process id
     * @param numInstT Number of process instructions
     * @param memory Memory allocated to the process
     * @param memoryLocation Last memory location of the process
     */
    public Process(String name, int pid, int numInstT, int memory, int memoryLocation){
        this.name = name;  
        this.pid = pid;
        this.numInstT = numInstT;
        this.memory = memory;
        this.memoryLocation = memoryLocation;        
    }

    /**
     * Method to name a process.
     * @param name Process name
     */
    public void setName(String name){
        this.name = name;
    }
    
    /**
     * Method to assign the process id.
     * @param pid Process id
     */
    public void setPid(int pid){
        this.pid = pid;
    }
    
    /**
     * Method to assign the number of instructions.
     * @param numInstT Number of total process instructions
     */
    public void setNumInstT(int numInstT){
        this.numInstT = numInstT;
    }
    
    /**
     * Method to assign the number of instructions executed
     * @param numInstE Number of instructions executed
     */
    public void setNumInstE(int numInstE){
        this.numInstE = numInstE;
    }    
    
    /**
     * Method to assign the total memory of the process
     * @param memory Memory allocated to the process
     */
    public void setMemory(int memory){
        this.memory = memory;
    }
    
    /**
     * Methodo to assign the status of the process
     * @param status Process status
     */
    public void setStatus(String status){
        this.status = status;
    }

    /**
     * Method to assign the last memory location
     * @param memoryLocation Last memory location of the process
     */
    public void setMemoryLocation(int memoryLocation){
        this.memoryLocation = memoryLocation;
    }
    
    /**
     * Method to get the process name
     * @return name (Process name)  
     */
    public String getName(){
        return this.name;
    }
    
    /**
     * Method to get the process id.
     * @return pid (Process id)
     */
    public int getPid(){
        return this.pid;
    }
    
    /**
     * Method to get the number of instructions.
     * @return numInstT (Number of total process instructions)
     */
    public int getNumInstT(){
        return this.numInstT;
    }
    
    /**
     * Method to get the number of instructions executed
     * @return numInstE (Number of instructions executed)
     */
    public int getNumInstE(){
        return this.numInstE;
    }    
    
    /**
     * Method to get the total memory of the process
     * @return memory (Memory allocated to the process)
     */
    public int getMemory(){
        return this.memory;
    }    
    
    /**
     * Method to get the status of the process
     * @return status (Process status)
     */
    public String getStatus(){
        return this.status;
    }    

    /**
     * Method to get the last memory location
     * @return memoryLocation Last memory location of the process
     */
    public int getMemoryLocation(){
        return this.memoryLocation;
    }   
    
    /**
     * Overridden method of Object class
     * @return string with information about the process
     */
    @Override
    public String toString(){
        return "Name: " + this.name + "\nPID: " + this.pid + "\nTotal Instructions: " + this.numInstT + "\nInstructions Executed:" + this.numInstE + "\nAllocated Memory: " + this.memoryLocation + "-" + (this.memoryLocation+this.memory);
    }   
}


