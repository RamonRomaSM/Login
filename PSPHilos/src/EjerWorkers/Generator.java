package EjerWorkers;

import java.util.concurrent.Semaphore;

public class Generator extends Thread{
	Semaphore s;
	Semaphore s2;
	Worker[] workers;
	String n;
	static volatile int cont=0;
	static volatile int ultimoEncontrado=0;
	
	
	public Generator(Worker[]workers,Semaphore s,Semaphore s2) {
		n=cont+"";
		cont++;
		this.s=s;
		this.s2=s2;
		this.workers=workers;
		
	}
	
	
	@Override
	public void run() {		
		/*activo semaforo 1,activo semaforo2; busco worker libre, lo encuentro y ya puedo liberar semaforo 1
		 * 
		 */
		
			try {
				
				s.acquire();
				
				//ahora es nuestro turno
				Worker mine=busca();
				
				
				s2.acquire();
				mine.libre=false;			
				mine.n=n;
				
				
				mine.interrupt();
				s.release();
				s2.release();
			
				
							
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		
		
	
	}	
	
	public Worker busca() {
		boolean encontrado=false;
		//TODO:	guardar el ultimo libre y empezar desde ahi
		
		//es tu turno de buscar asi que vas a repetir la busqueda hasta que saques un worker
		
		while(!encontrado) {
			for(int i=ultimoEncontrado;i<workers.length;i++) {
					if(workers[i].libre) {
						encontrado=true;
						if((ultimoEncontrado+1)==workers.length) {ultimoEncontrado=0;}
						else {ultimoEncontrado++;}
						return workers[i];
						}
				}
		}
		return null;
	}
}
