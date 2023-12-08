package studentmanagementSystem;

public class Student {


    private Integer id;
    private String name;
    private Integer age;
    private Integer roll_no;
    private String city;

    public Student() {
    }

    public Student(String name, Integer age, Integer roll_no, String city) {

        this.name = name;
        this.age = age;
        this.roll_no = roll_no;
        this.city = city;
    }



    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }


    public Integer getRoll_no() {
        return roll_no;
    }

    public String getCity() {
        return city;
    }


    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", roll_no=" + roll_no +
                ", city='" + city + '\'' +
                '}';
    }
}
