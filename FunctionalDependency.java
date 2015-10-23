import java.util.Iterator;

/**
 * Represents a functional dependency, namely the dependent attributes
 * are determined by the independent set.
 *
 * Is mostly just an Immutable tuple with named fields.
 **/
public class FunctionalDependency {

	private final AttributeSet _independentAttributeSet;
	private final AttributeSet _dependentAttributeSet;
	//this FD represents independentSet -> dependentSet

	public FunctionalDependency(AttributeSet ind, AttributeSet dep) {
		_independentAttributeSet = new AttributeSet(ind);
		_dependentAttributeSet = new AttributeSet(dep);
	}

	public AttributeSet independent() {
		return new AttributeSet(_independentAttributeSet);
	}

	public AttributeSet dependent() {
		return new AttributeSet(_dependentAttributeSet);
	}
	
	public AttributeSet getindependent(){
		return this._independentAttributeSet;
	}
	
	public AttributeSet getdependent(){
		return this._dependentAttributeSet;
	}
	
	public String toString() {
		String out = "";
		Iterator<Attribute> independ = this.independent().iterator();
		while(independ.hasNext()) out += independ.next();
        out += "->";
        Iterator<Attribute> depend = this.dependent().iterator();
        while (depend.hasNext()) out += depend.next();
		return out;
	}
}