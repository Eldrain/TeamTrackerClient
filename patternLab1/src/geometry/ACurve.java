package geometry;

/**
 * Created by Артём on 04.10.2017.
 */
public abstract class ACurve implements ICurve {
    IPoint a, b;

    public ACurve(IPoint a, IPoint b) {
        this.a = a;
        this.b = b;
    }
}
