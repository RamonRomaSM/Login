package EjerWorkers;

import java.util.concurrent.Semaphore;

public class Main {
	
	
	static int maxGenerators=0;
	
	static boolean para=false;
	
	
	
	public static void main(String[] args) throws InterruptedException {
		
		
		int maxWorkers=4;
		maxGenerators=10;
		
		Worker[]workers=new Worker[maxWorkers];
		Generator[]generators=new Generator[maxGenerators];
		
		Semaphore s=new Semaphore(maxWorkers);
		Semaphore s2=new Semaphore(1);//este es para escribir la n de worker
		
		
		
		//LA ACCION ES TAN RAPIDA QUE LOS WORKERS SE MUEREN DE STARVATION
		//POR QUE A VECES PARECE QUE EL THREAD 0 NO SE EJECUTA?
		
		
		for(int i=0;i<workers.length;i++) {
			workers[i]=new Worker();		
			workers[i].start();		
			
		}
		for(int i=0;i<generators.length;i++) {
			generators[i]=new Generator(workers,s,s2);
			System.out.println(generators[i].getName()+"/ "+generators[i].n);	
			
			
		}
		for(int i=0;i<generators.length;i++) {
			
			generators[i].start();		
			
		}
		
		
		
		
	
	}
	
}



















