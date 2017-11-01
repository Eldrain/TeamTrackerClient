/**
 * Created by Артём on 22.09.2017.
 */
public class Word implements IPrintable {
    private String word;

    public Word(String str) {
        word = str;
    }

    @Override
    public void Print(IPrinter printer) {
        printer.print(word);
    }
}
