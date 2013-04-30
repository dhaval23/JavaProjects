/**
 * CS 9053
 * TestInheritance
 * Dhaval Doshi(0490700)
 */
import java.util.ArrayList;

/**
 * class TestInheritence
 *contains main function
 */
class TestInheritence 
{
	 public static void main(String[] args) {

	        Lord sam = new Lord("Sam");
	        Lord joe = new Lord("Joe");
	        Lord janet = new Lord("Janet");

	        PersonWithStrength randy = new PersonWithStrength("Randolf the Elder", 250);
	        PersonWithStrength barclay = new PersonWithStrength("Barclay the Bold", 300);

	        Swordsman hardy = new Swordsman("TuckTuckTheHardy", 60);
	        Swordsman stout = new Swordsman("TuckTuckTheStout", 40);
	        Archer samantha = new Archer("Samantha", 50);
	        Archer pethora = new Archer("Pethora", 50);	
	        Wizard thora = new Wizard("Thorapleth", 70);
	 
	        sam.hire(samantha);
	        janet.hire(hardy);	
	        janet.hire(stout);
		
	        janet.hire(thora);
	        joe.battle(randy);        
	        joe.battle(sam);	
	        janet.battle(barclay);
			
	        janet.hire(samantha);	
	        janet.hire(pethora);
		
	        janet.battle(barclay);	
	        sam.battle(barclay);	
	        joe.battle(barclay);

	        thora.runsaway();

	        System.out.println(sam);
	        System.out.println(joe);
	        System.out.println(janet);
	        System.out.println(randy);
	        System.out.println(barclay);
	    }  
}

abstract class Protector
{
	private Lord lordRef;
	private String pName;
	private double strength=0;
	/**
	 * Protector()
	 * default constructor
	 */
	public Protector(){}
	/**
	 * Protector()
	 * constructor initializes the protector object
	 * @param name:	name of the protector
	 * @param c: strength of the protector
	 */
	public Protector(String name, double c)
	{
		pName=name;
		strength=c;
	}
	
	
	/**
	 * getProtectorName()
	 * @return pName:Proector Name
	 */
	public String getProtectorName()
	{
		return this.pName;
	}
	/**
	 * getProtectorStrength()
	 * @return strength: strength of the Protector
	 */
	public double getProtectorStrength()
	{
		return this.strength;
	}
	/**
	 * setProtectorStrength()
	 * function to assign a given strength to protector
	 * @param value: value of strength
	 */
	public void setProtectorStrength(double value)
	{
		strength=value;
	}
	/**
	 * getLordRef()
	 * @return lordRef: reference of the lord of the protector
	 */
	public Lord getLordRef() {
		return lordRef;
	}
	/**
	 * setLordRef()
	 * assigns a reference of a Lord to the lordRef pointer
	 * @param lordRef
	 */
	public void setLordRef(Lord lordRef) {
		this.lordRef = lordRef;
	}
	/**
	 * runsaway()
	 * function removes the protector from the list of lord if protector quits
	 */
	void runsaway()
	{
		if(getProtectorStrength()>0 && getLordRef()==null)
		{
			System.out.println("So long "+this.getLordRef()+". I am out of here -- "+this.pName);
			lordRef.removePro(this);
			this.setLordRef(null);
			
		}
		else
		{
			System.out.println("Warrior already dead or has no job");
		}
	}
	abstract void hailKing();

}
/**
 * abstract class Noble
 *contains noble constructor and functions hire and battle
 */
abstract class Noble
{
	private String nobleName;
	private boolean alive;
	private double totalStrength;
	public Noble(){}
	/**
	 * Noble()
	 * constructor initializes the Noble object.
	 * @params: name of the Noble
	 */
	public Noble(String name)
	{
		 setNobleName(name);
		 this.setTotalStrength(0);
		 setAlive(true);
	}
	/**
	 * battle()
	 * function that gives result of the battle
	 * @param enemy
	 */
	public void battle(Noble enemy)
	{
		Noble attacker=this;
		System.out.println("\n"+attacker.getNobleName()+" battles "+enemy.getNobleName());
		if(!attacker.isAlive() && !enemy.isAlive())
		{
			System.out.println("Oh, NO ! They are both DEAD! YUCK !");
		}
		else if(attacker.getTotalStrength()==enemy.getTotalStrength())
		{	
			attacker.getSound();
			enemy.getSound();
			System.out.println("Mutual Annihilation: "+attacker.getNobleName()+" and "+enemy.getNobleName()+" die at each other's hands");
			attacker.loose();
			enemy.loose();
		}
		else if(attacker.isAlive() && !enemy.isAlive())
		{
			System.out.println("Deadman Attacking "+ attacker.getNobleName());
		}
		else if(!attacker.isAlive() && enemy.isAlive())
		{
			System.out.println("He is dead "+ enemy.getNobleName());
		}
		else if(attacker.getTotalStrength()>enemy.getTotalStrength())
		{
			attacker.getSound();
			enemy.getSound();
			System.out.println("\n"+attacker.getNobleName()+" defeats "+enemy.getNobleName());
			double fraction=enemy.getTotalStrength()/attacker.getTotalStrength();
			enemy.loose();
			
			attacker.win(fraction);
		}
		else if(attacker.getTotalStrength()<enemy.getTotalStrength())
		{
			attacker.getSound();
			enemy.getSound();
			System.out.println("\n"+enemy.getNobleName()+" defeats "+attacker.getNobleName());
			double fraction=attacker.getTotalStrength()/enemy.getTotalStrength();
			attacker.loose();
			enemy.win(fraction);
		}
	}
	/**
	 * loose()
	 * function kills the noble and its warriors on loosing the battle.
	 */
	abstract void loose();
	
	/**
	 * win()
	 * function to reduce the strength of the winning noble.
	 * @param frac
	 */
	abstract void win(double frac);
	abstract void getSound();
	/**
	 * various getter setters
	 */
	public double getTotalStrength() {
		return totalStrength;
	}
	public void setTotalStrength(double totalStrength) {
		this.totalStrength = totalStrength;
	}
	public String getNobleName() {
		return nobleName;
	}
	public void setNobleName(String nobleName) {
		this.nobleName = nobleName;
	}
	public boolean isAlive() {
		return alive;
	}
	public void setAlive(boolean alive) {
		this.alive = alive;
	}

}

/**
 * abstract class Warrior
 * contains a constructor,private attributes and public get & set method to access the attributes of Warrior 
 */
abstract class Warrior extends Protector
{
	/**
	 * Warrior()
	 * constructor initializes the Warrior object.
	 * @params: name of the Warrior, strength of the Warrior
	 */
	public Warrior(String name, double c)
	{
		super(name,c);
	}
	abstract void hailKing();
	/**
	 * finalHail()
	 * prints the Hail of protectors
	 * @param quote : takes the sound as per the subclass object
	 */
	public void finalHail(String quote)
	{
		System.out.println(quote+" "+this.getProtectorName()+" says: Take that in the name of my Lord, "+super.getLordRef().getNobleName() );
	}
}

/**
 * class Archer
 * Contains a constructor and a function to make hail sound
 */
class Archer extends Warrior
{
	/**
	 * Archer()
	 * Constructor initializes the Archer object
	 * @param name
	 * @param c
	 */
	public Archer(String name, double c)
	{
		super(name,c);
	}
	/**
	 * hailKing()
	 * makes a hailing sound and calls function to print the sound
	 */
	public void hailKing()
	{
		super.finalHail("TWANG!  ");
	}
}

/**
 * class Swordsman
 * Contains a constructor and a function to make hail sound
 */
class Swordsman extends Warrior
{
	/**
	 * Swordsman()
	 * Constructor initializes the Swordsman object
	 * @param name
	 * @param c
	 */
	public Swordsman(String name, double c)
	{
		super(name,c);
	}
	/**
	 * hailKing()
	 * makes a hailing sound and calls function to print the sound
	 */
	public void hailKing()
	{
		super.finalHail("CLANG!  ");
	}
}

/**
 * class Wizard
 * Contains a constructor and a function to make hail sound
 */
class Wizard extends Protector
{
	/**
	 * Wizard()
	 * Constructor initializes the Wizard object
	 * @param name
	 * @param c
	 */
	public Wizard(String name, double c)
	{
		super(name,c);
	}
	/**
	 * hailKing()
	 * makes a hailing sound
	 */
	public void hailKing()
	{
		System.out.println("POOF");
	}
}


class Lord extends Noble
{
	private ArrayList<Protector> protectorList=new ArrayList<Protector>();
	/**
	 * Lord()
	 * constructor initializes Lord object by calling the Noble constructor
	 * @param lordName
	 */
	public Lord(String lordName)
	{
		super(lordName);
	}
	/**
	 * hire()
	 * function hires the protector for a noble
	 * @param w: protector to be hired
	 */
	public void hire(Protector w)
	{
		if(w.getProtectorStrength()>0 && isAlive())
		{
			if(w.getLordRef()==null)
			{
				this.protectorList.add(w);
				this.setTotalStrength(this.getTotalStrength() + w.getProtectorStrength());
				w.setLordRef(this);
			}
			else
				System.out.println("Warrior already hired");
		}
		else
		{
			System.out.println(this.getNobleName()+" could not hire "+w.getProtectorName());
		}
	}
	/**
	 * removePro()
	 * function that removes the protector from the protector list of a noble  and accordingly changes the total strength. 
	 * @param: protector object
	 */
	public void removePro(Protector w)
	{
		this.setTotalStrength(getTotalStrength()-w.getProtectorStrength());
		protectorList.remove(w);
	}
	/**
	*toString()
	*function for displaying the Lord information (overriding the toString method for a Lord)
	*@returns: String to be displayed whenever a Lord object is printed
	*/
	public String toString()
	{
		String rString="\n"+this.getNobleName()+" has an army of "+this.protectorList.size();
		for(Protector i:this.protectorList)
		{
			rString+="\n"+ i.getProtectorName() + " : "+ i.getProtectorStrength()+"\n";
		}
		return rString;
		
	}
	/**
	 * loose()
	 * function kills the Lord and its protectors on loosing the battle.
	 */
	public void loose()
	{
		this.setTotalStrength(0);
		this.setAlive(false);
		for(Protector w : protectorList)
		{
			w.setProtectorStrength(0);
		}
	}
	/**
	 * win()
	 * function to reduce the strength of the winning Lord.
	 * @param frac
	 */
	public void win(double f)
	{
		for(Protector w : this.protectorList)
		{	
			double s=w.getProtectorStrength();
			s=s-(s*f);
			w.setProtectorStrength(s);
		}
	}
	
	/**
	 * getSound()
	 * get sound for all the protectors in the list
	 */
	void getSound() 
	{
		for(Protector p: this.protectorList)
		{
			p.hailKing();
		}
	}
}


class PersonWithStrength extends Noble
{
	/**
	 * PersonWithStrength()
	 * constructor to initialize PersonWithStrength object
	 * @param personName
	 * @param personStrength
	 */
	public PersonWithStrength(String personName,double personStrength)
	{
		setNobleName(personName);
		this.setTotalStrength(personStrength);
		setAlive(true);
	}
	/**
	*toString()
	*function for displaying the PersonWithStrength information (overriding the toString method for a PersonWithStrength)
	*@returns: String to be displayed whenever a PersonWithStrength object is printed
	*/
	public String toString()
	{
		String rString="\n"+this.getNobleName()+": "+this.getTotalStrength();
		return rString;
	}
	/**
	 * loose()
	 * function kills the noble and its protectors on loosing the battle.
	 */
	public void loose()
	{
		this.setTotalStrength(0);
		this.setAlive(false);
	}
	/**
	 * win()
	 * function to reduce the strength of the winning noble.
	 * @param frac
	 */
	public void win(double frac)
	{	
			double s=this.getTotalStrength();
			s=s-(s*frac);
			setTotalStrength(s);
	}
	
	/**
	 * getSound()
	 * does nothing but needs to be over-rided as Noble is abstract class
	 */
	void getSound() {}	
}
