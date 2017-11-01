/**
 * Created by Артём on 19.09.2017.
 */
public class Classroom {

    public void translate(String str, Student students[]) {
        for(Student std : students)
            std.listen(str);
    }
}
