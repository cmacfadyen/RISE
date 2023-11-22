import csv
from itertools import zip_longest

def read_course_data(file_path):
    courses = []

    with open(file_path, 'r') as csvfile:
        reader = csv.reader(csvfile)
        for row in reader:
            course_name, weight = row[0], float(row[1])
            courses.append((course_name, weight))

    return courses

def read_student_choices(file_path):
    student_choices = []

    with open(file_path, 'r') as csvfile:
        reader = csv.reader(csvfile)
        next(reader)  # Skip header row
        for row in reader:
            student_name = row[0]
            choices = row[1:]
            student_choices.append((student_name, choices))

    return student_choices

def optimize_student_placement(courses, student_choices, capacity):
    placed_students = []
    not_placed_students = []

    for student_name, choices in student_choices:
        placed = False

        # Try to place the student in their choices
        for course_name in choices:
            # Find the course capacity and check if it's full
            current_capacity = sum(1 for s, c in placed_students if c == course_name)
            if current_capacity < capacity:
                placed_students.append((student_name, course_name))
                placed = True
                break

        if not placed:
            not_placed_students.append(student_name)

    return placed_students, not_placed_students

def write_placement_results(placed_students, not_placed_students, output_file):
    with open(output_file, 'w', newline='') as csvfile:
        writer = csv.writer(csvfile)
        writer.writerow(['Student', 'Placed Course', 'Not Placed'])
        
        for placed, not_placed in zip_longest(placed_students, not_placed_students, fillvalue=('', '')):
            writer.writerow([placed[0], placed[1], not_placed])

# Example usage
course_data_file = 'courses_weights.csv'
results_file = 'results.csv' #'simulated_student_choices.csv'
placement_output_file = 'placement_results.csv'
capacity_per_course = 24

courses = read_course_data(course_data_file)
student_choices = read_student_choices(results_file)

placed_students, not_placed_students = optimize_student_placement(courses, student_choices, capacity_per_course)

# Display the results
print("Students successfully placed:")
print(placed_students)
print("\nStudents not placed:")
print(not_placed_students)

# Write placement results to a new CSV file
write_placement_results(placed_students, not_placed_students, placement_output_file)
print(f"Placement results written to {placement_output_file}.")
