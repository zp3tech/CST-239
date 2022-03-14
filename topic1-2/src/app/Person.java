/**
 * 
 */
package app;

/**
 * The Person class has a name, age, and weight. It defines two methods, run and
 * walk.
 * 
 * @author pahlz
 *
 */
public class Person {

	private String name;
	private int age;
	private float weight;

	public Person(String name, int age, float weight) {
		super();
		this.name = name;
		this.age = age;
		this.weight = weight;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	/**
	 * Prints to console message indicating "walk()" status of this object
	 */
	public void walk() {
		System.out.println("I am in walk()");
	}

	/**
	 * Prints to console message indicating "run()" status of this object
	 * 
	 * @param distance Enter float number for how far to run
	 * @return Currently always returns 0
	 */
	public float run(float distance) {
		System.out.println("I am in run()");
		return 0;
	}

	public static void main(String[] args) {
		Person me = new Person("Zach", 27, (float) 155.0);
		System.out.println("My name is " + me.getName());
		me.walk();
		me.run(69);
	}

}
