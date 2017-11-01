/**
 * Created by Артём on 15.09.2017.
 */
public class Lecture {

    public static void main(String[] args) {
        String text[] = {"ООП - нужная вещь", "Программирование - это хорошо!"};
        String names[] = {"Вася", "Коля", "Петя", "Олеся", "Иван"};
        Student st[] = new Student[5];

        for(int i = 0; i < 5; i++)
            st[i] = new Student(names[i]);

        Lecturer lect = new Lecturer("Александр Витальевич", text);
        Classroom room = new Classroom();

        for(int i = 0; i < text.length; i++)
            room.translate(lect.say(), st);
    }
}
