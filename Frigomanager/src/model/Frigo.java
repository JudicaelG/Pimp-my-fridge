package model;

import java.util.ArrayList;

import javax.security.auth.callback.Callback;

public class Frigo {
	

private float temperature;
private float temperatureext;
private float consigne;
private float hygro;
private int id;


public Frigo(float temperature,float temperatureext,float consigne,float hygro,int id){
	this.consigne = consigne;
	this.hygro = hygro;
	this.setId(id);
	this.temperature = temperature;
	this.temperatureext = temperatureext;
}

public float getTemperatureext() {
	return temperatureext;
}
public void setTemperatureext(float temperatureext) {
	this.temperatureext = temperatureext;
}
public float getConsigne(int id) {
	return consigne;
}
public void setConsigne(int id,float consigne) {
	this.consigne = consigne;
}
public float getHygro() {
	return hygro;
}
public void setHygro(float hygro) {
	this.hygro = hygro;
}
public void setTemperature(float temperature) {
	this.temperature = temperature;
}
public float getTemperature(){
	return temperature;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}


}
