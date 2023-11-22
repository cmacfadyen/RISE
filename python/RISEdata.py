import csv
import random

def read_course_data(file_path):
    courses = []

    with open(file_path, 'r') as csvfile:
        reader = csv.reader(csvfile)
        for row in reader:
            course_name, weight = row[0], float(row[1])
            courses.append((course_name, weight))

    return courses

def simulate_course_selection(courses, num_students):
    selected_courses = []

    for _ in range(num_students):
        student_choices = []
        remaining_courses = list(courses)

        for _ in range(5):
            if not remaining_courses:
                break

            # Choose a course based on the popularity weight
            chosen_course = random.choices(remaining_courses, weights=[weight for course, weight in remaining_courses])[0]
            
            # Remove the chosen course from the list to avoid duplicates
            remaining_courses.remove(chosen_course)
            
            # Add the chosen course to the student's choices
            student_choices.append(chosen_course[0])

        # Sort the student's choices based on the original weights
        student_choices.sort(key=lambda course: next(weight for name, weight in courses if name == course),reverse=True)

        selected_courses.append(student_choices)

    # Sort students based on the weight of their first course choice
    selected_courses.sort(key=lambda choices: next(weight for name, weight in courses if name == choices[0]))

    return selected_courses

def write_results_to_csv(results, output_file):
    with open(output_file, 'w', newline='') as csvfile:
        writer = csv.writer(csvfile)
        writer.writerow(['Student', 'Course 1', 'Course 2', 'Course 3', 'Course 4', 'Course 5'])
        
        for i, choices in enumerate(results, 1):
            row_data = [f"Student {i}"] + choices
            writer.writerow(row_data)

# Example usage with CSV file
file_path = 'courses_weights.csv'
output_file = 'results.csv'
courses = read_course_data(file_path)
num_students = 1000

selected_courses = simulate_course_selection(courses, num_students)

# Display the results
for i, choices in enumerate(selected_courses, 1):
    print(f"Student {i} chose courses in the following order: {', '.join(choices)}")

# Write results to a new CSV file
write_results_to_csv(selected_courses, output_file)
print(f"Results written to {output_file}.")
