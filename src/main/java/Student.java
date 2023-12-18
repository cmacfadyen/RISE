package main.java;

import java.util.Arrays;

public class Student{
	String name;
	Course[] choices;
	Course placedCourse;
	boolean placed;
	int rankPlaced;
	int round; //used when attempting to schedule

	public Student(String name, Course[] choices) {

		this.name = name;
		this.choices = choices;
		this.placed = false;
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

	
	public int getRound() {
		return round;
	}

	public void setRound(int round) {
		this.round = round;
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
	
	public Double getWeightOfCurrentRound() {
		return this.choices[this.round].weight;
	}
	public Double getWeightOfNextRound() {
		return this.choices[this.round + 1].weight;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", placedCourse=" + placedCourse + ", placed=" + placed + ", rankPlaced="
				+ rankPlaced + "]";
	}



	
	
}
