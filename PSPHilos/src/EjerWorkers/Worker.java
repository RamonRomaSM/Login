package EjerWorkers;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Semaphore;

public class Worker extends Thread{
	String n;
	static volatile int cont=0;
	boolean libre;
	boolean terminar;
	String id;
	
	public Worker() {
		super("Worker"+cont);
		id=cont+"";
		cont++;
		n="";
		System.out.println("Worker"+cont);
		libre=true;
		terminar=false;
	}
	
	
	@Override
	public void run() {		
		while(!terminar) {
		
			try {
				
				Thread.sleep(99999999);
			} catch (InterruptedException e) {
				libre=false;
				try {		
					
					System.out.println(this.getName()+" dice: "+(Integer.parseInt(n)*1000));
					
				} catch (Exception e2) {
					System.out.println("TERMINANDO...");
				}
				
				
				
				
				
				libre=true;
				
				
				
			}
		}
		
	}	
	
}
