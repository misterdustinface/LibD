package base;

public abstract class Requestible {

	final public void request() {
		if (shouldAcceptRequest())
			performAction();
	}
	
	abstract protected boolean shouldAcceptRequest();
	abstract protected void performAction();
}
