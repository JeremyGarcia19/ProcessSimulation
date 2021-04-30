
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author JEREMY
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int opc=0;
        OperatingSystem OS = OperatingSystem.getOS();
        Scanner input = new Scanner(System.in);
        while (opc != 9){
            System.out.println("\n\nProcess Simulation");
            System.out.println("1.Create New Process");
            System.out.println("2.View Current System Status");
            System.out.println("3.Print Process Queue");
            System.out.println("4.Run Current Process");
            System.out.println("5.View Current Process");
            System.out.println("6.Next Process");
            System.out.println("7.Kill Current Process");
            System.out.println("8.Kill Everything And Finish"); 
            System.out.println("9.Exit"); 
            System.out.print("OPC:");
            opc = input.nextInt();
            switch (opc) {
                case 1:
                    String name;
                    System.out.print("Process name: ");
                    name = input.next();
                    OS.CreateProcess(name);
                break;
                
                case 2:
                   OS.SystemStatus();
                break;

                case 3:
                   OS.PrintQueue();
                break;

                case 4:
                   OS.ExecuteProcess();
                break;

                case 5:
                   OS.CurrentProcess();
                break;

                case 6:
                   OS.NextProcess();
                break;

                case 7:
                   OS.KillProcess();
                break;

                case 8:
                   OS.KillAllProcess();
                break;
                
                case 9:
                   System.out.println("Leave");
                   
                break;

                default:
                    System.out.println("INVALID OPTION");
                break;                        
            }
        }
        

        
        
        
    }
    
    
}