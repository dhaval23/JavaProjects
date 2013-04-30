/**
 * CS 9053
 * Test Car
 * Dhaval Doshi
 */
import java.util.*;
/**
 * class BuiltCar
 * Contains main method that includes the test cases
 */
class BuiltCar {

	/**
	 * main()
	 * @param args
	 * @throws CloneNotSupportedException
	 */
	public static void main(String[] args) throws CloneNotSupportedException 
	{
		Car nCar=new Car("Ferrari",4,"Ceat",50,"V6",15000);
		System.out.println(nCar);
		
		Car fourWheel=new Car("BMW",4,"V6");
		System.out.println(fourWheel);
		
		Car fClone=fourWheel.clone();
		System.out.println("Cloned:\n"+fClone);
		
		Car myCar=new Car("Audi",3,"V8");
		System.out.println(myCar);
		
		System.out.println("Modified myCar:");
		myCar.removeWheel(2);
		Wheel zWheel=new Wheel("piston",45);
		myCar.addWheel(zWheel,2);
		myCar.removeEngine();
		Engine engNew=new Engine("V7",10000);
		myCar.addEngine(engNew);
		System.out.println(myCar);

		System.out.println("Equals executed");
		if(fourWheel.equals(fClone))
			System.out.println("Cars fourWheel & fClone are Equal");
		else 
			System.out.println("Cars are not equal");
		
		Car carList[]=new Car[3];
		int count=5;
		for(int i=0; i<3;i++)
			 carList[i]=new Car("Car"+(i+1),count--,"V4");
		System.out.println("\nOriginal Car Array");
		for(int i=0; i<3;i++)
			System.out.println(carList[i]);
		System.out.println("\nSorted Car Array");
		Arrays.sort(carList);
		for(int i=0; i<3;i++)
			System.out.println(carList[i]);
	}

}
/**
 * class Car
 *Creates the Car and its components and contains functions to modify the components of the car and toString method to display
 *Overrides clone,equals,hashCode & comareTo  methods
 */
class Car implements Cloneable,Comparable<Car>
{
	private String carName;
	private Wheel wheelList[];
	private Engine engine;
	/**
	 * Car()
	 * Constructor that creates and initializes the Car object when all entities of car are given
	 * @param cName
	 * @param numWheel
	 * @param wBrand
	 * @param wRad
	 * @param eng
	 * @param hp
	 */
	public Car(String cName,int numWheel,String wBrand,double wRad,String eng,double hp)
	{
		carName=cName;
		wheelList=new Wheel[numWheel];
		for (int i=0;i<numWheel;i++)
		{
			Wheel w=new Wheel(wBrand,wRad);
			wheelList[i]=w;
		}
		engine=new Engine(eng,hp);
	}
	/**
	 * Car()
	 * Constructor that creates and initializes the Car object when only name,no. of wheels & engine type of car are given and initializes rest to default
	 * @param cName
	 * @param numWheel
	 * @param eng
	 */
	public Car(String cName,int numWheel,String eng)
	{
		this(cName,numWheel,"MRF",40,eng,7000);
	}
	/**
	 * addEngine()
	 * Adds a engine to to the car only if no engine is present
	 * @param e
	 */
	public void addEngine(Engine e)
	{
		if(engine==null)
		{
			engine=e;
			System.out.println("Engine Added");
		}
		else
			System.out.println("Engine Already present");
	}
	/**
	 * removeEngine()
	 * removes an engine from a car if present
	 */
	public void removeEngine()
	{
		if(engine==null)
			System.out.println("No Engine to remove");
		else
		{
			engine=null;
			System.out.println("Engine Removed");
		}
	}
	/**
	 * addWheel()
	 * Adds a Wheel to to the car only if no Wheel is present at the specified position
	 * @param w
	 * @param pos
	 */
	public void addWheel(Wheel w,int pos)
	{
		if(pos>wheelList.length)
			System.out.println("The position is greater than wheel capacity");
		else if(wheelList[pos-1]==null)
		{
			wheelList[pos-1]=w;
			System.out.println("Wheel added at position"+pos);
		}
		else
			System.out.println("Wheel already present");	
	}
	/**
	 * removeWheel()
	 * removes an wheel from a car if present at the given position
	 * @param pos
	 */
	public void removeWheel(int pos)
	{
		if(wheelList[pos-1]!=null)
		{
			wheelList[pos-1]=null;
			System.out.println("Wheel removed at "+pos);
		}
		else
			System.out.println("No Wheel present at "+pos+" to remove");
	}
	/**
	 * toString()
	 * function to display the Car object
	 * @return string to be displayed
	 */
	public String toString()
	{
		int i=1;
		String rString="";
		rString+="Car Name:" + carName+"\n";
		
		if(engine!=null)
			rString+=engine.toString();
		else
			rString+="Engine Missing";
		rString+="\nWheeler: "+wheelList.length+"\n";
		for(Wheel w: wheelList)
		{
			rString+="position "+ i++ +" wheel: \n";
			if(w!=null)
				rString+=w.toString()+"\n";
			else
				rString+="No Wheel";
		}
		
		return rString;
	}
	/**
	 * compareTo
	 * Compares no. of wheels of two cars
	 * @param t
	 */
	@Override
	public int compareTo(Car t) 
	{
		if(this.wheelList.length==t.wheelList.length)
				return 0;
		else if(this.wheelList.length>t.wheelList.length)
				return 1;
		else
				return -1;
	}
	/**
	 * clone()
	 * Function clones the Car objects and its components including wheels & engine
	 */
	@Override
	public Car clone() throws CloneNotSupportedException
	{
		Car newClone=(Car) super.clone();
		newClone.engine=engine.clone();
		for(int i=0;i<wheelList.length;i++)
		{
			newClone.wheelList[i]=wheelList[i].clone();
		}
		return newClone;
	}
	/**
	 * equals()
	 * functions returns true if both Cars are equal
	 * @param obj
	 */
	@Override
	public boolean equals(Object obj) {
        if(this == obj)     // check if parameter references the same object
            return true;
	if((obj == null) || (obj.getClass() != this.getClass()))	 // check if the given object is null or diff class instance
            return false;

        Car carobj = (Car)obj;
        if(this.carName.equals(carobj.carName) && this.engine != null && this.engine.equals(carobj.engine))	// Check for similar engines
        {								
            if(this.wheelList.length == carobj.wheelList.length)	// Check if same wheel drive, and return true if yes.
            {
 
            	boolean valid=true;
            	for(int i=0; i<wheelList.length; i++)
            	{	
            		if(this.wheelList[i]!=null && carobj.wheelList[i]!=null && !this.wheelList[i].equals(carobj.wheelList[i]))
            			{
            				valid=false;
            				break;
            			}
            	}
            	if(valid)
            		return true;
            	else 
            		return false;
            }
        }
        return false;
    }
	/**
	 * hashCode()
	 * function used by equals()
	 */
	@Override
	public int hashCode() {
        int hash = 5;				
        hash = 17 * hash + (this.engine != null ? this.engine.hashCode() : 0) + (this.wheelList.length!=0 ? this.wheelList.hashCode() : 0);	
        return hash;
    }
	
}
/**
 * class Engine
 * Creates a engine for the car and toString method to display the engine object
 * Overrides clone,hashCode and equals methods
 */
class Engine implements Cloneable
{
	private String Ename;
	private double power;
	public Engine(String engType,double hp) 
	{
		this.Ename=engType;
		power=hp;
	}
	/**
	 * toString()
	 * function to display the Engine object
	 * @return string to be displayed
	 */
	@Override
	public String toString()
	{
		String rString="";
		rString+="Engine Name: "+Ename+"\nHorse Power: "+power;
		
		return rString;
	}
	/**
	 * clone()
	 * Function clones the Car objects and its components including wheels & engine
	 */
	@Override
	public Engine clone() throws CloneNotSupportedException
	{
		Engine eClone=(Engine) super.clone();
		return eClone;
	}
	/**
	 * equals()
	 * functions returns true if engines of both Cars are equal
	 * @param obj
	 */
	@Override
	public boolean equals(Object obj) {
        if(this == obj)     // check if parameter references the same object
            return true;
	if((obj == null) || (obj.getClass() != this.getClass()))	 // check if the given object is null or diff class instance
            return false;

        Engine engObj = (Engine)obj;
        if(this.Ename.equals(engObj.Ename))	// Check for similar engine name using string class equals
        {								
            if(this.power == engObj.power)	// Check if horsepower is same
                return true;
        }
        return false;
    }
	/**
	 * hashCode()
	 * function used by equals()
	 */
	@Override
	public int hashCode()
	{
		int hash=7;
		hash=23 + hash * (int)power;
		return hash;
	}
}
/**
 * class Wheel
 * Creates a Wheel for the car and toString method to display the engine object
 * Overrides clone,hashCode and equals methods
 */
class Wheel implements Cloneable
{
	String brand;
	double radius;
	public Wheel(String wBrand, double wRad) 
	{
		brand=wBrand;
		radius=wRad;
	}
	/**
	 * toString()
	 * function to display the Wheel object
	 * @return string to be displayed
	 */
	@Override
	public String toString()
	{
		String rString="";
		rString+="Wheel Brand: "+brand+" Radius: "+radius;
		return rString;
	}
	/**
	 * clone()
	 * Function clones the Car objects and its components including wheels & engine
	 */
	@Override
	public Wheel clone() throws CloneNotSupportedException
	{
		Wheel wClone=(Wheel) super.clone();
		return wClone;
	}
	/**
	 * equals()
	 * functions returns true if all wheels of both Cars are equal
	 * @param obj
	 */
	@Override
	public boolean equals(Object obj)
	{
        if(this == obj)     // check if parameter references the same object
            return true;
	if((obj == null) || (obj.getClass() != this.getClass()))	 // check if the given object is null or different class instance
            return false;

        Wheel engObj = (Wheel)obj;
        if(this.brand.equals(engObj.brand))	// Check for similar brand name using string class equals
        {								
            if(this.radius == engObj.radius)	// Check if radius is same
                return true;
        }
        return false;
    }
	/**
	 * hashCode()
	 * function used by equals()
	 */
	@Override
	public int hashCode()
	{
		int hash=19;
		hash=23 * hash + (int)radius;
		return hash;
	}
	
}
