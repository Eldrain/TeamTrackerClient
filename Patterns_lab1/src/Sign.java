/**
 * Created by Артём on 22.09.2017.
 */
public class Sign implements IPrintable {
    private char sign;

    public Sign(char sign) {
        this.sign = sign;
    }

    @Override
    public void Print(IPrinter printer) {
        printer.print(sign);
    }
}
