package coursePackage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class CourseRecommender {
  private List<Student> allStudents;
  private List<Student> ALIGNStudents;
  private Map<String, CourseStatistics> courseInfo;
  private static final String FILTER_CS5001 = "CS 5001";
  private static final String FILTER_CS5002 = "CS 5002";
  private static final String FILTER_CS5004 = "CS 5004";
  private static final String FILTER_CS5008 = "CS 5008";
  private static final Float DEFAULT_FLOAT_VALUE = 0f;
  private static final Integer DEFAULT_INCREMENT = 1;

  /**
   * A constructor that takes in three input parameters:
   * @param allStudents, every student in our system, stored as a List of Students
   * @param ALIGNStudents, every ALIGN student in our system, stored as a list of Students
   * @param courseInfo, every course taken by the students in our system, stored as a Map of
   *                    String (Course Name) to Course Statistics (Further detailed information)
   *                    objects.
   * */
  public CourseRecommender(List<Student> allStudents, List<Student> ALIGNStudents,
      Map<String, CourseStatistics> courseInfo) {
    this.allStudents = allStudents;
    this.ALIGNStudents = ALIGNStudents;
    this.courseInfo = courseInfo;
  }

  /**
   * An alternative constructor for the Course Recommender class
   * @param allStudents, every student in our system, stored as a List of Students
   * ALIGNStudents is assigned by calling our private helper method:
   *                     this.filterOutAlignStudents()
   * courseInfo is assigned by calling our private helper method:
   *                     this.computeCollegeCoursesStatistics()
   * */
  public CourseRecommender(List<Student> allStudents) {
    this.allStudents = allStudents;
    this.ALIGNStudents = this.filterOutAlignStudents(allStudents);
    this.courseInfo = this.computeCollegeCoursesStatistics(allStudents);
  }

  public List<Student> getAllStudents() {
    return allStudents;
  }

  public List<Student> getALIGNStudents() {
    return ALIGNStudents;
  }

  public Map<String, CourseStatistics> getCourseInfo() {
    return courseInfo;
  }

  /**
   * filterOutAlignStudents is a private helper method that will take in a List of Students
   * and return a filtered list of only ALIGN students
   * @param allStudents, list of All Students (including Non-ALIGN students), stored as a List of
   *                     Student objects
   * @return a filtered list of Students, with non-ALIGN students removed.
   * This method calls on another private method this.isALIGNStudent, to check if a student
   * meets the qualifications for an ALIGN student
   * */
  private List<Student> filterOutAlignStudents(List<Student> allStudents){
    return allStudents
        .stream()
        .filter(student -> this.isALIGNStudent(student.getTakenCourses()))
        .collect(Collectors.toList());
  }

  /**
   * isALIGNStudent is a private helper method that will take in a list of Courses and see if
   * any of the Courses match up to the ALIGN course curriculum:
   * @param courses, a list of Courses
   * @return True if at least one of the course codes match with any of the Course Codes in the
   * ALIGN curriculum. Method will return false if no ALIGN courses are found in the course list.
   * */
  private Boolean isALIGNStudent(List<Course> courses){
    return courses
        .stream()
        .anyMatch(course -> course.getCourseCode().equals(FILTER_CS5001) ||
                course.getCourseCode().equals(FILTER_CS5002) ||
                course.getCourseCode().equals(FILTER_CS5004) ||
                course.getCourseCode().equals(FILTER_CS5008)
        );
  }

  /**
   * computeCollegeStatistics is a private helper method that will take in a list of Students
   * and generate a Map of new Course information using the Student and Course information:
   * @param allStudents as a list of all the students in that college
   * @return a Map of String and Course Statistics data objects that track a Course Name and further
   * student information about that course.
   * */
  private Map<String, CourseStatistics> computeCollegeCoursesStatistics(List<Student> allStudents){
    Map<String, CourseStatistics> newCourseInfo = new HashMap<>();
    for(Student student: allStudents){
      for(Course course: student.getTakenCourses()){
        if(! newCourseInfo.containsKey(course.getCourseName())){
          // if current course is not in our map, add as a new course:
          newCourseInfo.putIfAbsent(course.getCourseName(),
              this.createNewCourseStatistic(student, course));
        }
        else{
          // else, update an existing course in map:
          newCourseInfo.put(course.getCourseName(), this.updateCourseStatistic(student, course,
                  newCourseInfo.get(course.getCourseName())));
        }
      }
    }
    return newCourseInfo;
  }

  /**
   * createNewCourseStatistics is a private helper method that will generate a new CourseStatistic
   * date type using an input Student and an input Course:
   * @param student as a Student object
   * @param course as a Course object
   * @return newly generated CourseStatistic, using the defined inputs.
   * */
  private CourseStatistics createNewCourseStatistic (Student student, Course course){
    // check if student is an ALIGN student:
    if(this.isALIGNStudent(student.getTakenCourses())){
      // if ALIGN student, generate "All students" and ALIGN values:
      return new CourseStatistics(course, DEFAULT_INCREMENT, DEFAULT_INCREMENT,
          course.getGrade(), course.getGrade());
    }
    // else, only generate values for "All" students:
    else return new CourseStatistics(course, DEFAULT_INCREMENT, 0,
        course.getGrade(), DEFAULT_FLOAT_VALUE);
  }

  /**
   * updateCourseStatistic is a private helper method that will generate an updated CourseStatistic
   * date type using an input Student, an input Course, and the original CourseStatistic
   * information:
   * @param student as a Student object
   * @param course as a Course object
   * @param original CourseStatistic data type, with the original data values:
   * @return an updated CourseStatistic, generated using the defined inputs.
   * */
  private CourseStatistics updateCourseStatistic(Student student, Course course,
      CourseStatistics original){
    // store original student counts in Integer holders:
    Integer originalCountAll = original.getPastStudentsAll();
    Integer originalALIGNCount = original.getPastALIGNStudents();
    // increment new student counts, do not change the ALIGN count yet:
    Integer newCountAll = originalCountAll + DEFAULT_INCREMENT;
    Integer newALIGNCount = originalALIGNCount;
    // calculate the new average for ALL students using the following formula:
    Float newAverageAll = (
        originalCountAll * original.getAverageGradeAllStudents() + course.getGrade())/ newCountAll;
    // do not change the ALIGN average yet:
    Float newALIGNAverage = original.getAverageGradeALIGNStudents();
    // if student is in the ALIGN program, update values accordingly:
    if(this.isALIGNStudent(student.getTakenCourses())){
      newALIGNCount ++;
      newALIGNAverage = (originalALIGNCount * original.getAverageGradeALIGNStudents() +
          course.getGrade())/ newALIGNCount;
    }
    // return updated Course Statistic:
    return new CourseStatistics(course, newCountAll, newALIGNCount, newAverageAll,
        newALIGNAverage);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof CourseRecommender)) {
      return false;
    }
    CourseRecommender that = (CourseRecommender) o;
    return Objects.equals(getAllStudents(), that.getAllStudents())
        && Objects.equals(getALIGNStudents(), that.getALIGNStudents())
        && Objects.equals(getCourseInfo(), that.getCourseInfo());
  }

  /**
   * recommendCourses will return a list of recommended courses for the students to take
   * @param student information, stored as a Student object
   * @return a List of three recommended courses that the student has not taken yet:
   * (1) The first recommended course is the course that is taken by the most students,
   * (2) The second recommended course is the course that has the highest average grade,
   * (3) The third recommended course is the course that the most ALIGN students have taken.
   * */
  public List<Course> recommendCourses(Student student){
    List<Course> recommendations = new ArrayList<>();
    recommendations.add(this.findCourseTakenByMostStudents(student));
    recommendations.add(this.findCourseWithHighestAverage(student));
    recommendations.add(this.findCourseTakenByMostALIGNStudents(student));
    return recommendations;
  }

  /**
   * findCourseTakenByMostStudents is a private helper method that will return a Course object that
   * the input Student has not yet taken, and has the highest overall enrollment:
   * @param student information as a Student object.
   * @return the course with the highest overall enrollment, that the input student has not taken.
   * */
  private Course findCourseTakenByMostStudents(Student student){
    Course recommendedCourse = null;
    Integer maxStudent = 0;
    // iterate through map entry:
    for(Map.Entry<String, CourseStatistics> entry: this.getCourseInfo().entrySet()){
      // check that the past student enrollment is greater than max
      if(entry.getValue().getPastStudentsAll() > maxStudent &&
          // check that the input student has not taken this class yet:
          ! student.getTakenCourses().contains(entry.getValue().getCourse())){
        // if conditions are met, update values:
        maxStudent = entry.getValue().getPastStudentsAll();
        recommendedCourse = entry.getValue().getCourse();
      }
    }
    // return recommended class:
    return recommendedCourse;
  }

  /**
   * findCourseTakenByMostStudents is a private helper method that will return a Course object that
   * the input Student has not yet taken, and has the highest overall grade average:
   * @param student information as a Student object.
   * @return the course with the highest overall grade average, that the input student has not
   * taken.
   * */
  private Course findCourseWithHighestAverage(Student student){
    Course recommendedCourse = null;
    Float highestAverage = DEFAULT_FLOAT_VALUE;
    for(Map.Entry<String, CourseStatistics> entry: this.getCourseInfo().entrySet()){
      if(entry.getValue().getAverageGradeAllStudents() > highestAverage &&
          ! student.getTakenCourses().contains(entry.getValue().getCourse())){
        highestAverage = entry.getValue().getAverageGradeAllStudents();
        recommendedCourse = entry.getValue().getCourse();
      }
    }
    return recommendedCourse;
  }


  /**
   * findCourseTakenByMostStudents is a private helper method that will return a Course object that
   * the input Student has not yet taken, and has the highest overall ALIGN student enrollment:
   * @param student information as a Student object.
   * @return the course with the highest overall ALIGN student enrollment, that the input student
   * has not taken.
   * */
  private Course findCourseTakenByMostALIGNStudents(Student student){
    Course recommendedCourse = null;
    Integer maxStudent = 0;
    for(Map.Entry<String, CourseStatistics> entry: this.getCourseInfo().entrySet()){
      if(entry.getValue().getPastALIGNStudents() > maxStudent &&
          ! student.getTakenCourses().contains(entry.getValue().getCourse())){
        maxStudent = entry.getValue().getPastALIGNStudents();
        recommendedCourse = entry.getValue().getCourse();
      }
    }
    return recommendedCourse;
  }

  @Override
  public int hashCode() {
    return Objects.hash(getAllStudents(), getALIGNStudents(), getCourseInfo());
  }

  @Override
  public String toString() {
    return "CourseRecommender{" +
        "allStudents=" + allStudents +
        ", ALIGNStudents=" + ALIGNStudents +
        ", CourseInfo=" + courseInfo +
        '}';
  }
}
