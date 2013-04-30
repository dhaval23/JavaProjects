/**
 * CS 9053
 * Nobles and Warriors
 * Dhaval Doshi(0490700)
 */
import java.util.*;
/**
 * class TestWarriorNobles
 * contains the main() method of the code
 */
class TestWarriorNobles {
	 public static void  main(String[] args) {
	        Noble art = new Noble("King Arthur");
	        Noble lance = new Noble("Lancelot du Lac");
	        Noble jim = new Noble("Jim");
	        Noble linus = new Noble("Linus Torvalds");
	        Noble billie = new Noble("Bill Gates");

	        Warrior cheetah = new Warrior("Tarzan", 10);
	        Warrior wizard = new Warrior("Merlin", 15);
	        Warrior theGovernator = new Warrior("Conan", 12);
	        Warrior nimoy = new Warrior("Spock", 15);
	        Warrior lawless = new Warrior("Xena", 20);
	        Warrior mrGreen = new Warrior("Hulk", 8);
	        Warrior dylan = new Warrior("Hercules", 3);

	        jim.hire(nimoy);
	        lance.hire(theGovernator);
	        art.hire(wizard);
	        lance.hire(dylan);
	        linus.hire(lawless);
	        billie.hire(mrGreen);
	        art.hire(cheetah);

	        System.out.println(jim);
	        System.out.println(lance);
	        System.out.println(art);
	        System.out.println(linus);
	        System.out.println(billie);

	        cheetah.runaway();
	        System.out.println(art);

	        art.battle(lance);
	        jim.battle(lance);
	        linus.battle(billie);
	        billie.battle(lance);
	    }

}

/**
 * class Warrior
 * contains a constructor,private attributes and public get & set method to access the attributes of Warrior and a runaway method. 
 */
class Warrior
{
	Noble n;
	private String warriorName;
	private double strength=0;
	private String masterName;
	private boolean hireStatus=false;
	/**
	 * Warrior()
	 * constructor initializes the Warrior object.
	 * @params: name of the Warrior, strength of the Warrior
	 */
	public Warrior(String name, double c)
	{
		warriorName=name;
		strength=c;
	}
	/**
	 * getMaster()
	 * @return: Master Name
	 */
	public String getMaster()
	{
		return this.masterName;
	}
	/**
	 * setMasterName()
	 * function to assign a given noble to the given master
	 * @param: Master name, noble object
	 */
	public void setMasterName(String name, Noble nobName)
	{
		masterName=name;
		n=nobName;
	}
	/**
	 * getWarriorName()
	 * @return:Warrior Name
	 */
	public String getWarriorName()
	{
		return this.warriorName;
	}
	/**
	 * getWarriorStrength()
	 * @return: strength of the Warrior
	 */
	public double getWarriorStrength()
	{
		return this.strength;
	}
	/**
	 * setWarriorStrength()
	 * function to assign a given strength to Warrior
	 * @param: value of strength
	 */
	public void setWarriorStrength(double value)
	{
		strength=value;
	}
	/**
	 * getHireStatus()
	 * @return: boolean value, whether the noble is hired or no.
	 */
	public boolean getHireStatus()
	{
		return this.hireStatus;
	}
	/**
	 * setHireStatus()
	 * function sets hireStatus variable to true if a warrior is hired.
	 */
	public void setHireStatus()
	{
		hireStatus=true;
	}
	/**
	 * runaway()
	 * function called when a noble quits that removes the warrior from the nobles list. 
	 */
	void runaway()
	{
		if(strength>0 && getHireStatus())
		{
			System.out.println("So long "+this.masterName+". I am out of here -- "+this.warriorName);
			this.masterName=null;
			n.removeW(this);
		}
		else
		{
			System.out.println("Warrior already dead or has no job");
		}
	}
}
/**
*class Noble
*contains all the private attributes of a Noble and public methods for accessing and modify them, 
*/
class Noble
{
	private String nobleName;
	private boolean alive;
	private double armyValue,totalStrength;
	private ArrayList<Warrior> warriorList=new ArrayList<Warrior>();
	/**
	 * Noble()
	 * constructor initializes the Noble object.
	 * @params: name of the Noble
	 */
	public Noble(String name)
	{
		 nobleName=name;
		 this.armyValue=0;
		 this.totalStrength=0;
		 alive=true;
	}
	/**
	 * removeW()
	 * function that removes the warrior from the Warrior list of a noble  and accordingly changes the total strength. 
	 * @param: Warrior object
	 */
	public void removeW(Warrior w)
	{
		this.totalStrength=totalStrength-w.getWarriorStrength();
		warriorList.remove(w);
		armyValue--;
	}
	/**
	*toString()
	*function for displaying the Noble's information (overriding the toString method for a Noble)
	*@returns: String to be displayed whenever a noble object is printed
	*/
	public String toString()
	{
		String rString="\n"+this.nobleName+" has an army of "+this.armyValue;
		for(Warrior i:this.warriorList)
		{
			rString+="\n"+ i.getWarriorName() + " : "+ i.getWarriorStrength()+"\n";
		}
		return rString;
		
	}
	/**
	 * hire()
	 * function hires the warrior for a noble
	 * @param w: Warrior to be hired
	 */
	public void hire(Warrior w)
	{
		double warriorStrength = w.getWarriorStrength();
		if(warriorStrength>0 && alive==true)
		{
			if(w.getHireStatus()==false)
			{
				this.warriorList.add(w);
				this.totalStrength+=w.getWarriorStrength();
				w.setMasterName(nobleName,this);
				this.armyValue++;
				w.setHireStatus();
			}
			else
				System.out.println("Warrior already hired");
		}
		else
		{
			System.out.println("Hiring the warrior not possible");
		}
	}
	/**
	 * battle()
	 * function that gives result of the battle
	 * @param enemy
	 */
	public void battle(Noble enemy)
	{
		Noble attacker=this;
		System.out.println("\n"+attacker.nobleName+" battles "+enemy.nobleName);
		if(attacker.alive==false && enemy.alive==false)
		{
			System.out.println("Oh, NO ! They are both DEAD! YUCK !");
		}
		else if(attacker.totalStrength==enemy.totalStrength)
		{
			System.out.println("Mutual Annihilation: "+attacker.nobleName+" and "+enemy.nobleName+" die at each other's hands");
			attacker.loose();
			enemy.loose();
		}
		else if(attacker.alive==false && enemy.alive==true)
		{
			System.out.println("Deadman Attacking "+ attacker.nobleName);
		}
		else if(attacker.alive==true && enemy.alive==false)
		{
			System.out.println("He is dead "+ enemy.nobleName);
		}
		else if(attacker.totalStrength>enemy.totalStrength)
		{
			System.out.println("\n"+attacker.nobleName+" defeats "+enemy.nobleName);
			enemy.loose();
			double fraction=enemy.totalStrength/attacker.totalStrength;
			attacker.win(fraction);
		}
		else if(attacker.totalStrength<enemy.totalStrength)
		{
			System.out.println("\n"+enemy.nobleName+" defeats "+attacker.nobleName);
			attacker.loose();
			double fraction=attacker.totalStrength/enemy.totalStrength;
			attacker.win(fraction);
		}
	}
	/**
	 * loose()
	 * function kills the noble and its warriors on loosing the battle.
	 */
	public void loose()
	{
		this.totalStrength=0;
		this.alive=false;
		for(Warrior w : warriorList)
		{
			w.setWarriorStrength(0);
		}
	}
	/**
	 * win()
	 * function to reduce the strength of the winning noble.
	 * @param f
	 */
	public void win(double f)
	{
		for(Warrior w : this.warriorList)
		{	
			double s=w.getWarriorStrength();
			s=s-(s*f);
			w.setWarriorStrength(s);
		}
	}

}
