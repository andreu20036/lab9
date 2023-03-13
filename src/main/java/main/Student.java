package main;

import java.time.*;
import java.util.Objects;

public class Student implements Comparable<Student>
{
    private int id;
    private String surname;
    private String name;
    private String fatherName;
    private LocalDate birthday;
    private String address;
    private String phoneNumber;
    private String faculty;
    private int course;
    private String group;

    public int getId()
    {
        return id;
    }
    public String getSurname()
    {
        return surname;
    }
    public String getName()
    {
        return name;
    }
    public String getFatherName()
    {
        return fatherName;
    }
    public LocalDate getBirthday()
    {
        return birthday;
    }
    public String getAddress()
    {
        return address;
    }
    public String getPhoneNumber()
    {
        return phoneNumber;
    }
    public String getFaculty()
    {
        return faculty;
    }
    public int getCourse()
    {
        return course;
    }
    public String getGroup()
    {
        return group;
    }

    public void setId(int id)
    {
        if(id > 1000) this.id = id;
    }
    public void setSurname(String surname)
    {
        if(surname.length() > 2) this.surname = surname;
    }
    public void setName(String name)
    {
        if(name.length() > 2) this.name = name;
    }
    public void setFatherName(String fatherName)
    {
        if(fatherName.length() > 2) this.fatherName = fatherName;
    }
    public void setBirthday(String birthday)
    {
        LocalDate birthdayDT = LocalDate.parse(birthday);
        if(birthdayDT.getYear() >= 2000) this.birthday = birthdayDT;
    }
    public void setAddress(String address)
    {
        if(address.length() > 5) this.address = address;
    }
    public void setPhoneNumber(String phoneNumber)
    {
        if(phoneNumber.length() >= 10 && phoneNumber.length() < 20) this.phoneNumber = phoneNumber;
    }
    public void setFaculty(String faculty)
    {
        if(faculty.length() > 5) this.faculty = faculty;
    }
    public void setCourse(int course)
    {
        if(course > 0 && course < 10) this.course = course;
    }
    public void setGroup(String group)
    {
        if(group.length() > 4) this.group = group;
    }

    @Override
    public String toString()
    {
        return  id + " " + surname + " " + name + " " + fatherName + " " + birthday + " " + address + " "
                + phoneNumber + " " + faculty + " " + course + " " + group;

    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id && course == student.course && Objects.equals(surname, student.surname) && Objects.equals(name, student.name) && Objects.equals(fatherName, student.fatherName) && Objects.equals(birthday, student.birthday) && Objects.equals(address, student.address) && Objects.equals(phoneNumber, student.phoneNumber) && Objects.equals(faculty, student.faculty) && Objects.equals(group, student.group);
    }
    @Override
    public int hashCode()
    {
        return Objects.hash(id, surname, name, fatherName, birthday, address, phoneNumber, faculty, course, group);
    }
    Student()
    {
        this(0, "", "", "", "2000-01-01",
                "", "", "", 0, "");
    }
    Student(int id, String surname, String name, String fatherName, String birthday,
            String address, String phoneNumber, String faculty, int course, String group)
    {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.fatherName = fatherName;
        this.birthday = LocalDate.parse(birthday);
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.faculty = faculty;
        this.course = course;
        this.group = group;
    }
    @Override
    public int compareTo(Student o)
    {
        int result = surname.compareTo(o.surname);
        if(result == 0) return name.compareTo(o.name);
        return result;
    }
}