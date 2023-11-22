package main.java;

import java.util.Arrays;

public class Student{
	String name;
	Course[] choices;
	Course placedCourse;
	boolean placed;
	int rankPlaced;

	public Student(String name, Course[] choices) {

		this.name = name;
		this.choices = choices;
		this.placed = false;
		this.rankPlaced = -1;
	}

	 public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Course[] getChoices() {
		return choices;
	}

	public void setChoices(Course[] choices) {
		this.choices = choices;
	}

	
	public Course getPlacedCourse() {
		return placedCourse;
	}

	public void setPlacedCourse(Course placedCourse) {
		this.placedCourse = placedCourse;
	}

	public boolean isPlaced() {
		return placed;
	}

	public void setPlaced(boolean placed) {
		this.placed = placed;
	}

	public int getRankPlaced() {
		return rankPlaced;
	}

	public void setRankPlaced(int rankPlaced) {
		this.rankPlaced = rankPlaced;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", placedCourse=" + placedCourse + ", placed=" + placed + ", rankPlaced="
				+ rankPlaced + "]";
	}

	
	
}
