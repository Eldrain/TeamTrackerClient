/**
 * Created by Артём on 22.09.2017.
 */
public class Text implements IPrintable {
    private IPrintable text[];

    public Text(IPrintable text[]) {
        this.text = text;
    }

    @Override
    public void Print(IPrinter printer) {
        for(IPrintable now : text) {
            now.Print(printer);
        }
    }
}
