package main.java;

import java.util.ArrayList;

public class Course{
	Integer id;
	String name;
	int capacity;
	double weight;
	ArrayList<Student> roster;
	boolean isFull;
	String time;
	
	public Course(Integer id, String name, double weight, int capacity,String time) {
		this.id = id;
		this.name = name;
		this.weight = weight;
		this.capacity = capacity;
		this.time=time;
		this.roster = new ArrayList<Student>();
		this.isFull = false;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public ArrayList<Student> getRoster() {
		return roster;
	}

	public void setRoster(ArrayList<Student> roster) {
		this.roster = roster;
	}

	public boolean isFull() {
		return isFull;
	}

	public void setFull(boolean isFull) {
		this.isFull = isFull;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "Course [name=" + name + ", capacity=" + capacity + ", weight=" + weight + ", roster=" + roster.size()
				+ ", isFull=" + isFull + ", time=" + time + "]";
	}
	
	
}