import java.io.*;

public class ObjectSerialize {
    public static void main(String[] args) {
        Student s = new Student("xiaoming", 182, 200);
        System.out.println(s);
        try {
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream("obj.dat")
            );
            out.writeObject(s);
            out.close();
            ObjectInputStream in = new ObjectInputStream(
                    new FileInputStream("obj.dat")
            );
            Student student = (Student) in.readObject();
            System.out.println(student);
            in.close();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

class Student implements Serializable{//将类实现可串行化接口（可序列化）
    String name;
    int age;
    int grade;

    public Student(String name, int age, int grade) {
        this.name = name;
        this.age = age;
        this.grade = grade;
    }

    public String toString(){
        return name + age + grade;
    }
}