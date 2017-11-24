//variable pour la sonde DHT12
#include "DHT.h"
#include "stdlib.h"
#define DHTPIN 2  // Pin où est branché la sonde
#define DHTTYPE DHT22   //définition du type de la sonde (DHT11, DHT22, ...)
// Initialisation de la sonde.
DHT dht(DHTPIN, DHTTYPE);


void setup() {
  Serial.begin(9600);
  pinMode(5, OUTPUT); //Défintion du PIN 5 de l'Arduino en mode sortie/
  dht.begin();
}
  double Thermister(int RawADC) {
  double Temp;
  // See http://en.wikipedia.org/wiki/Thermistor for explanation of formula
  Temp = log(((10240000/RawADC) - 10000));
  Temp = 1 / (0.00148144 + (0.00026268 * Temp) + (0.00000017572 * Temp * Temp * Temp));
  Temp = Temp - 273.15;           // Convert Kelvin to Celcius
  return Temp;
}

void loop() {

  //Déclaration des tableaux pour envoyer les résultats sur le port série pour l'interface java
  char hygrom[10]; 
  char temps[10];
  char tempext[10];
  char tagueule[10]; 

  float h = dht.readHumidity();  //Lecture de l'hygrométrie sur la sonde DHT22
  
  float t = dht.readTemperature();  //Lecture de la température sur la sonde DHT22

  double tempq = Thermister(analogRead(0));  // Read sensor;
 

  
  // Boucle pour vérifier que la sonde DHT22 est bien branché
  if (isnan(h) || isnan(t)) {
    Serial.println("La lecture de la sonde est impossible");
    return;
  }
  
 
  int consigne = 0; //Déclaration de Consigne
  consigne = Serial.read(); //On récupére la consigne de tempéarature envoyer de l'interface java
  
  snprintf(hygrom, 10, "%f", h);
  snprintf(temps, 10, "%f", t);
  snprintf(tempext, 10, "%f", tempq);
  String tempsext = String(tempq);
  String hygro = String(h);
  String temp = String(t);
  String pie = "jambon";
  String finale = (hygro + pie + temp + pie + tempsext);
    
  
  if ( consigne == 1) {
    digitalWrite(5, HIGH); // Allume le module peltier

    }
   else if(consigne == 0) {
  digitalWrite(5, LOW); // Eteind le module peltier
    }

    
  Serial.println(finale);
 
  delay(2000);
}





