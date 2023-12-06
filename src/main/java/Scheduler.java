package main.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

public class Scheduler {
	static final int NUM_CHOICES = 5; // students pick 5 choices
	static final int TRIALS = 100000; // how many times to try the algorithm
	static int bestSoFar = 0;// keep track of the best result
	static int[] results = new int[TRIALS]; // keep track of all results
	static String studentCsv = "/Users/charliemacfadyen/RISE/rise/src/StudentChoicesByNumber.csv";
	static String courseCsv = "/Users/charliemacfadyen/RISE/rise/src/course_weights.csv";

	public static void main(String[] args) {

		int[] successCounter = new int[TRIALS];

		for (int trial = 0; trial < TRIALS; trial++) {
			successCounter[trial] = runTrial();
			if (successCounter[trial] > bestSoFar) {
				bestSoFar = successCounter[trial];
			}
		}
		// export to csv
		// exportStudentList(students);
		System.out.println(bestSoFar);
//		System.out.println("**********");
//		for (int s : successCounter) {
//			System.out.println(s);
//		}
	}

	private static int runTrial() {
		// Read in courses first so they can be used to populate student choices
		List<Course> courses = readCourses(courseCsv);
		List<Student> students = readStudents(studentCsv, courses);
		int successes = 0;

		// Go through NUM_CHOICES (5) rounds of scheduling
		for (int round = 0; round < NUM_CHOICES; round++) {
			successes += schedule(students, round); // add on new successes each round
		}
		return successes;
	}

	/**
	 * Schedules students by sorting their courses by weight for the given "round"
	 * (where 0<=round<NUM_CHOICES), then attempting to place each student in that
	 * course. Unplaced students are added to a list to be attempted in the next
	 * round
	 * 
	 * @param students
	 * @param round
	 */
	private static int schedule(List<Student> students, int round) {
		int successesInRound = 0;
		// filter out unscheduled students
		List<Student> unscheduledStudents = new ArrayList<Student>();

		// put unscheduled students into a list
		for (Student student : students) {
			if (!student.placed) {
				unscheduledStudents.add(student);
			}
		}

		Collections.shuffle(unscheduledStudents);

		// sort them by weight of next course
		Collections.sort(unscheduledStudents, (s1, s2) -> ((Double) s1.getChoices()[round].getWeight())
				.compareTo((Double) (s2.getChoices()[round].getWeight())));
		// put them into course, if possible
		for (Student s : unscheduledStudents) {
			Course desiredCourse = s.getChoices()[round];
			if (!desiredCourse.isFull()) {
				s.setPlacedCourse(desiredCourse);
				s.setRankPlaced(round + 1);
				s.setPlaced(true);
				successesInRound++;
				// add student to the roster
				desiredCourse.getRoster().add(s);
				// check if course just filled
				if (desiredCourse.getRoster().size() >= desiredCourse.getCapacity()) {
					desiredCourse.setFull(true);
				}
			}

		}
		return successesInRound;

	}

	private static List<Course> readCourses(String csvFile) {
		List<Course> courses = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
			String line;

			// Read header (if it exists)
			// Uncomment the next line if CSV file has a header
			// br.readLine();

			while ((line = br.readLine()) != null) {
				String[] data = line.split(",");
				Integer id = Integer.parseInt(data[0]);
				String name = data[1].trim();
				double weight = Double.parseDouble(data[2]);
				String time = data[3].trim();
				int capacity = 24;// Integer.parseInt(data[4]);

				Course course = new Course(id, name, weight, capacity, time);
				courses.add(course);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return courses;
	}

	private static List<Student> readStudents(String csvFile, List<Course> courses) {
		List<Student> students = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
			String line;

			// Read header (if it exists)
			// Uncomment the next line if your CSV file has a header
			br.readLine();

			while ((line = br.readLine()) != null) {
				String[] data = line.split(",");
				String name = data[0].trim();
				// int[] courses = Arrays.copyOfRange(data, 1, data.length); // Courses start
				// from index 1

				// The ids of courses students want
				Course[] choices = new Course[NUM_CHOICES];

				for (int i = 0; i < NUM_CHOICES; i++) {
					Integer choice = Integer.parseInt(data[i + 1]);// choices start at index 1
					choices[i] = courses.stream().filter(e -> e.getId().equals(choice)).findFirst().orElse(null);

				}
				Student student = new Student(name, choices);
				students.add(student);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return students;
	}

	private static void printList(List l) {
		for (Object o : l) {
			System.out.println(o);
		}
	}
	
	private static void exportStudentList(List<Student> students) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("finalResults.csv"))) {
			for (Student s : students) {
				bw.write(s.toString());
				bw.newLine();

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}


}
