/**
 * Created by Артём on 22.09.2017.
 */
public class PrinterDefault implements IPrinter {
    @Override
    public void print(String text) {
        System.out.print(text);
    }

    @Override
    public void print(char ch) {
        System.out.print(ch);
    }
}
