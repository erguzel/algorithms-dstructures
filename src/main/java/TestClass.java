public class TestClass {

    public int getValint() {
        return valint;
    }

    public String getValstring() {
        return valstring;
    }

    public boolean isValboolean() {
        return valboolean;
    }

    public void setValint(int valint) {
        this.valint = valint;
    }

    public void setValstring(String valstring) {
        this.valstring = valstring;
    }

    public void setValboolean(boolean valboolean) {
        this.valboolean = valboolean;
    }

    private int valint;
    private String  valstring;
    private boolean valboolean;

    public TestClass(int valint, String valstring, boolean valboolean) {
        this.valint = valint;
        this.valstring = valstring;
        this.valboolean = valboolean;
    }
}
