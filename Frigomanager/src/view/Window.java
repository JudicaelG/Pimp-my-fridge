package view;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.Controller;
import model.Frigo;



public class Window extends JFrame implements ActionListener {
	

JFrame j = new JFrame("frigoManager");

private boolean statut;
private float temperature ;
private float temperatureext;
private float consigne ;
private float hygro;


 
 JButton b1 = new JButton("Valider");
 JButton b2 = new JButton("Accèder au Logs");
 JButton b3 = new JButton("Commander des produits");
 
 JTextField jtxt = new JTextField(""+ consigne);
 
 JPanel p = new JPanel();
 JPanel p1 = new JPanel();
 JPanel p2 = new JPanel();
 JPanel p3 = new JPanel();
 JPanel p4 = new JPanel();
 JPanel p5 = new JPanel();
 JPanel p6 = new JPanel();
 
 JLabel l1 = new JLabel("             Frigo MANAGER");
 JLabel l2 = new JLabel("Température intérieur");
 JLabel l3 = new JLabel("Température extérieur");
 JLabel l4 = new JLabel("Taux d'humidité");
 JLabel l5 = new JLabel("Témoin d'erreur");
 JLabel l7 = new JLabel(""+ this.temperature );
 JLabel l8 = new JLabel(""+ this.temperatureext);
 JLabel l9 = new JLabel(""+ this.hygro);
 
  
 Font f = new Font("Arial",Font.BOLD,30);
 
 GridLayout gl = new GridLayout (0,2);
 

public Window(boolean statut,float temperature,float temperatureext,float consigne,float hygro){
	 	
		
	 	this.statut = statut;
	 	this.temperature = temperature;
	 	this.consigne = consigne;
	 	this.hygro = hygro;
	 	this.temperatureext = temperatureext;
	 	
	 	j.setSize(900, 600);
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		jtxt.addActionListener(this);
		jtxt.setActionCommand("Ecrire");
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		
		b1.setActionCommand("Valider");
		b2.setActionCommand("Accèder au Logs");
		b3.setActionCommand("Commander des produits");
		
		 
		l1.setFont(f);
		
		p.setLayout(gl);
		p1.setLayout(gl);
		p2.setLayout(gl);
		p3.setLayout(gl);
		p4.setLayout(gl);
		p5.setLayout(gl);
		
		
		p1.add(l2);
		p1.add(l7);
		
		p2.add(l3);
		p2.add(l8);
		
		p3.add(l4);
		p3.add(l9);
		
		p4.add(jtxt);
		p4.add(b1);
		
		p5.add(l5);
		p5.add(p6);	
		
		p.add(l1);
		p.add(p4);
		p.add(p1);
		p.add(p2);
		p.add(p3);
		p.add(p5);
		
		
		p.add(b2);
		p.add(b3);
		
		
		j.add(p);
		
		j.setVisible(true);
 }
@Override
public void actionPerformed(ActionEvent e) {
	 if (e.getActionCommand().equals("Valider")){
		 this.consigne = Float.parseFloat(jtxt.getText());
		 System.out.println(this.consigne);
		 
	 }
	 else if (e.getActionCommand().equals("Accèder au Logs")){
		 System.out.println("le fichier txt est pas encore dispo");
	 }
	 else if (e.getActionCommand().equals("Commander des produits")){
		 try {
			Desktop.getDesktop().browse(new URI("https://www.auchandrive.fr/drive/nos-drives/p/schweighouse-sur-moder-897?utm_campaign=marque&utm_source=google&utm_medium=cpc&gclid=EAIaIQobChMI0MWa9dfQ1wIVEDwbCh155wh9EAAYASAAEgKocfD_BwE"));
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		}
	 }
	 else if (e.getActionCommand().equals("Ecrire")){
		 int firstime = 0;
		 if (firstime == 0){
		 jtxt.setText("");
		 firstime = 1;
		 }
	 }
	 }
public void setTemperature(float temperature) {
	this.temperature = temperature;
}
public void setTemperatureext(float temperatureext) {
	this.temperatureext = temperatureext;
}
public void setHygro(float hygro) {
	this.hygro = hygro;
}
public void setStatut(boolean statut) {
	this.statut = statut;
	
}
public boolean getStatut(){
	return this.statut;
}
public void update(){
	  l7.setText(""+ ""+ this.temperature);
	  l8.setText(""+ this.temperatureext);
	  l9.setText(""+ this.hygro);
	  
	  if (statut == true){
		  p6.setBackground(Color.RED);
	  }	
	  if (statut == false){
		  p6.setBackground(Color.GREEN);
	  }
}
public JFrame getFrame() {
	return j;
}
public float getConsigne() {
	return this.consigne;
	
}
}