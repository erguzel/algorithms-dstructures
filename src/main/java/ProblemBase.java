

public abstract class ProblemBase {

    public long counter = 0;
    public long counter1 = 0;
    public ALogger<Object> LOGGER = null;

    public ProblemBase() {

        LOGGER = new ALogger<>(this.getClass());
    }


}
