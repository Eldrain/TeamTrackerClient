/**
 * Created by Артём on 22.09.2017.
 */
public class Main {

    public static void main(String[] args) {
        /*IPrintable arr[] = {new Sign('A'),
                new Word("bc"), new Sign(' '),
                new Word("def")};*/

        Text text = new Text(new IPrintable[] {new Sign('A'),
                new Word("bc"), new Sign(' '),
                new Word("def")});
        /*text.Print(new PrinterDefault());
        text.Print(new PrinterSpecial());*/

        Printer printer = new Printer();

        printer.Print(text);

    }
}
