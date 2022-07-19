package coursePackage;

import java.util.Objects;

public class CourseStatistics {
  private Course course;
  private Integer pastStudentsAll;
  private Integer pastALIGNStudents;
  private Float averageGradeAllStudents;
  private Float averageGradeALIGNStudents;
  /**
   * The constructor for the Course Statistics Class
   * @param course, an individual course- stored as a Course object.
   * @param pastStudentsAll, the total number of students who have taken the course- stored as an
   *                         Integer.
   * @param pastALIGNStudents, the total number of ALIGN students who have taken the course- stored
   *                           as an Integer.
   * @param averageGradeAllStudents, the average grade of all the students who have taken the
   *                                 course- stored as a Float.
   * @param averageGradeALIGNStudents, the average grade of all ALIGN students who have taken the
   *                                   course- stored as a Float.
   * */
  public CourseStatistics(Course course, Integer pastStudentsAll,
      Integer pastALIGNStudents, Float averageGradeAllStudents,
      Float averageGradeALIGNStudents) {
    this.course = course;
    this.pastStudentsAll = pastStudentsAll;
    this.pastALIGNStudents = pastALIGNStudents;
    this.averageGradeAllStudents = averageGradeAllStudents;
    this.averageGradeALIGNStudents = averageGradeALIGNStudents;
  }

  public Course getCourse() {
    return course;
  }

  public Integer getPastStudentsAll() {
    return pastStudentsAll;
  }

  public Integer getPastALIGNStudents() {
    return pastALIGNStudents;
  }

  public Float getAverageGradeAllStudents() {
    return averageGradeAllStudents;
  }

  public Float getAverageGradeALIGNStudents() {
    return averageGradeALIGNStudents;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof CourseStatistics)) {
      return false;
    }
    CourseStatistics that = (CourseStatistics) o;
    return Objects.equals(getCourse(), that.getCourse())
        && Objects.equals(getPastStudentsAll(), that.getPastStudentsAll())
        && Objects.equals(getPastALIGNStudents(), that.getPastALIGNStudents())
        && Objects.equals(getAverageGradeAllStudents(), that.getAverageGradeAllStudents())
        && Objects.equals(getAverageGradeALIGNStudents(),
        that.getAverageGradeALIGNStudents());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getCourse(), getPastStudentsAll(), getPastALIGNStudents(),
        getAverageGradeAllStudents(), getAverageGradeALIGNStudents());
  }

  @Override
  public String toString() {
    return "CourseStatistics{" +
        "course='" + course + '\'' +
        ", pastStudentsAll=" + pastStudentsAll +
        ", pastALIGNStudents=" + pastALIGNStudents +
        ", averageGradeAllStudents=" + averageGradeAllStudents +
        ", averageGradeALIGNStudents=" + averageGradeALIGNStudents +
        '}';
  }
}
