/**
 * Created by Артём on 15.09.2017.
 */
public class Student {
    private String name;

    public Student(String name) {
        this.name = name;
    }

    public void sayName() {
        System.out.print(name);
    }

    public void listen(String str) {
        System.out.println(name + " записал: " + str);
    }
}
