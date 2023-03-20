package fr.eni.ecole.listener;

import java.time.LocalDateTime;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import fr.eni.ecole.BO.Utilisateur;

/**
 * Application Lifecycle Listener implementation class Ecouteur
 *
 */
@WebListener
public class Ecouteur implements HttpSessionAttributeListener {

    /**
     * Default constructor. 
     */
    public Ecouteur() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see HttpSessionAttributeListener#attributeAdded(HttpSessionBindingEvent)
     */
    public void attributeAdded(HttpSessionBindingEvent evt)  
    { 
    	if(evt.getName().equals("userConnected"))
    	{
    		System.out.println(((Utilisateur)evt.getValue()).getNom() + " a :" + LocalDateTime.now());
    	}
         // TODO Auto-generated method stub
    }

	/**
     * @see HttpSessionAttributeListener#attributeRemoved(HttpSessionBindingEvent)
     */
    public void attributeRemoved(HttpSessionBindingEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see HttpSessionAttributeListener#attributeReplaced(HttpSessionBindingEvent)
     */
    public void attributeReplaced(HttpSessionBindingEvent arg0)  { 
         // TODO Auto-generated method stub
    }
	
}
