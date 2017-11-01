/**
 * Created by Артём on 15.09.2017.
 */
public class Lecturer {
    private String name, text[];
    private int phrase;

    public Lecturer(String name, String text[]) {
        this.name = name;
        this.text = text;
        phrase = 0;
    }

    public String say() {
        System.out.println(name + " сказал: " + text[phrase]);
        int n = phrase++;
        if(phrase == text.length)
            phrase = 0;

        return text[n];
    }
}
