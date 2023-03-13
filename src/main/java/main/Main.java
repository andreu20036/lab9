package main;

import java.sql.*;
import java.util.*;
import java.util.Date;

public class Main {
    private Connection connection;
    public static void main(String[] args) {
        Main prog = new Main();
        prog.run();
    }

    private void run() {
        List<Student> students = madeListOfStudents();
        show(studentsWithSameFaculty(students));
        show(studentsWhoBornAfterYear(students));

        students.sort(null);
        show(students);

        students.sort(Comparator.comparing(Student::getFaculty).thenComparing(Student::getBirthday));
        show(students);

        System.out.println("\n" + getFaculties(students));

        howMuchStudentsInFaculties(students);
    }

    List<Student> madeListOfStudents() {
        List<Student> students = new ArrayList<>();
        try {
            connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/students", "andreu20036", "riven");
            try (PreparedStatement ps = connection.prepareStatement("select * from student");
                 ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String surname = rs.getString("surname");
                    String name = rs.getString("name");
                    String fatherName = rs.getString("fatherName");
                    Date birthday = rs.getDate("birthday");
                    String address = rs.getString("address");
                    String phoneNumber = rs.getString("phoneNumber");
                    String faculty = rs.getString("faculty");
                    int course = rs.getInt("course");
                    String group = rs.getString("group");
                    students.add(new Student(id, surname,name, fatherName,birthday.toString(),address,phoneNumber,faculty,course,group));
                }
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return students;
    }
    void show(List<Student> listOfStudents)
    {
        System.out.println();
        for (Iterator<Student> iterator = listOfStudents.iterator(); iterator.hasNext(); )
        {
            Student student =  iterator.next();
            System.out.println(student.toString());
        }
    }
    List<Student> studentsWithSameFaculty(List<Student> students)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введіть факультет: ");//"Engineering"
        String Faculty = scanner.nextLine();
        return students.stream()
                .filter(x -> Faculty.equals(x.getFaculty()))
                .toList();
    }
    List<Student> studentsWhoBornAfterYear(List<Student> students)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введіть рік: ");
        int year = scanner.nextInt();
        return students.stream()
                .filter(x -> x.getBirthday().getYear() > year)
                .toList();
    }
    Set<String> getFaculties(List<Student> students)
    {
        Set<String> faculties = new HashSet<>();
        for (Student student : students) faculties.add(student.getFaculty());
        return faculties;
    }
    void howMuchStudentsInFaculties(List<Student> students)
    {
        Map<String, Integer> map = new HashMap<>();
        for (Student student : students)
        {
            String faculty = student.getFaculty();
            Integer count = map.get(faculty);
            if (count == null) map.put(faculty, 1);
            else map.put(faculty, ++count);
        }
        for (Map.Entry<String, Integer> entry : map.entrySet())
        {
            System.out.println(entry);
        }
    }
}
