/**
 * Created by Артём on 22.09.2017.
 */
public class Printer  extends PrinterDefault implements IPrinterDelegate {

    @Override
    public void Print(IPrintable prt) {
        prt.Print(this);
    }
}
