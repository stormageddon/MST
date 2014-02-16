public class MSTException extends Exception {
    private String msg;

    public MSTException(String msg) {
	this.msg = msg;
    }

    public void renderError() {
	System.out.println(msg);
	System.exit(0);
    }

    @Override
    public String toString() {
	return msg;
    }
}
	