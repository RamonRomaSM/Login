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
				s2.acquire();				
				//ahora es nuestro turno
				Worker mine=busca();	
				s2.release();
				s.acquire();
				mine.n=n;
				mine.interrupt();			
				s.release();											
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}	
	}	
	
	public Worker busca() {		
		boolean encontrado=false;	
		//es tu turno de buscar asi que vas a repetir la busqueda hasta que saques un worker
		
		while(!encontrado) {
			for(int i=ultimoEncontrado;i<workers.length;i++) {
					if(workers[i].libre) {
						encontrado=true;
						workers[i].libre=false;//lo reservamos
						if((ultimoEncontrado+1)==workers.length) {ultimoEncontrado=0;}
						else {ultimoEncontrado++;}
						return workers[i];
						}
				}
		}
		return null;
	}
}
