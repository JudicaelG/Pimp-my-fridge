package controller;


import java.io.IOException;

import model.Frigo;
import view.Window;

public class Controller  {
	
	private float consigne ;
	private float lisntemp ;
	private float lisntempext;
	private float lisnthygro;
	
	private String data;
	
	Frigo f; 
	Window w;
	ArduinoConnector Ac;
	Thread t;
	
	public Controller()  {
	 f = new Frigo(this.lisntemp,this.lisntempext,this.consigne,this.lisnthygro,0);
	 w = new Window(false,this.lisntemp,this.lisntempext,this.consigne,this.lisnthygro);
	 Ac  = new ArduinoConnector();
	 Ac.initialize();
	 t = new Thread(){
		 public void run() {
			//the following line will keep this app alive for 1000 seconds,
			//waiting for events to occur and responding to them (printing incoming messages to console).
			try {Thread.sleep(1000000);} catch (InterruptedException ie) {}
			}
			};
	 t.start();
	 System.out.println("Started");
	
	}
	public void run(){
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		updateController();
		updateWindow();
		updateModel();
		//bouchonbullshitvalues();
		alert();
		this.consigne = w.getConsigne();
		throwconsigne(this.consigne);
		
	}	
	public void updateModel(){	
		f.setConsigne(f.getId(),consigne);
		f.setTemperature(lisntemp);
		f.setTemperatureext(lisntempext);
		f.setHygro(lisnthygro);
	}
	public void updateWindow(){	
		w.setTemperature(f.getTemperature());
		w.setTemperatureext(f.getTemperatureext());
		w.setHygro(f.getHygro());
	}	
	public void alert(){
		if (lisntemp >= 25 || lisntemp <= 0){
			w.setStatut(true);
			w.update();
		}
		else if (lisnthygro >= 50){
			w.setStatut(true);
			w.update();
		}
		else if (lisntempext >= 30 ||lisntempext <= -5){
			w.setStatut(true);
			w.update();
		}
		else {
			w.setStatut(false);
			w.update();
			
		}
	}
	public Frigo getFrigo() {
		return f;
	}
	public Window getWindow() {
		return w;
	}
	public void setConsigne(float consigne) {
		this.consigne = consigne;
	}
	/*public void bouchonbullshitvalues(){
		lisntemp++;
		lisntempext++;
		lisnthygro++;
		
	}*/
public void updateController(){
		this.lisntemp = Float.parseFloat(Ac.gettemp()) ;
		this.lisnthygro = Float.parseFloat(Ac.gethygro());
		this.lisntempext = Float.parseFloat(Ac.gettempext());
		
	}
public void throwconsigne(float consigne){
	
	if (lisntemp > consigne){
	try {
		Ac.getstream().write(1);
		System.out.println("lolololololo");
	} catch (IOException e) {
		e.printStackTrace();
	}
    }else{
	  try {
		Ac.getstream().write(0);
		System.out.println("prout");
	} catch (IOException e) {
		e.printStackTrace();
	}
	  
}
}
}