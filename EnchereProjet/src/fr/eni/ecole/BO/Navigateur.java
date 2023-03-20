package fr.eni.ecole.BO;

import java.util.List;

public class Navigateur<T> 
{
	List<T> lst;
	int position;
	
	public Navigateur(List<T> l)
	{
		lst=l;
		position=0;
	}
	
	public T deplacement(TypeDeplacement sens)
	{
		switch (sens) 
		{
		case PREMIER:
			return premier();
			
		case PRECEDENT:
			return precedent();
			
		case SUIVANT:
			return suivant();
		
		case DERNIER:
			return dernier();
			
		
		default:
			break;
		}
		
		
		return null;
		
	}

	private T dernier() 
	{
		position=lst.size()-1;
		return lst.get(position);
	}

	private T suivant() 
	{
		if(position==lst.size()-1)
		{
			position=0;
		}
		else
		{
			position++;
		}
		return lst.get(position);
	}

	private T precedent() {
		if(position==0)
		{
			position=lst.size()-1;
		}
		else
		{
			position--;
		}
		return lst.get(position);
	}

	private T premier() 
	{
		position=0;
		return lst.get(position);
	}

}
