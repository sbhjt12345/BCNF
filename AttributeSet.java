import java.util.*;

/**
 * An unordered set of Attributes. This could very easily be a Java collection,
 * but an important operation (namely examining the powerset) is not easily done
 * with the Java collection.
 **/
public class AttributeSet {

	//a list of the backing attributes
	private final List<Attribute> _attributes;

	//construct an empty AttributeSet
	public AttributeSet() {
		_attributes = new ArrayList<>();
	}

	//copy constructor
	public AttributeSet(AttributeSet other) {
		_attributes = new ArrayList<>(other._attributes);
	}
	
	public AttributeSet(List<Attribute> listother){
		_attributes = listother;
	}

	public void addAttribute(Attribute a) {
		if(!_attributes.contains(a))
			_attributes.add(a);
	}
<<<<<<< HEAD
	
	public void append(AttributeSet other){
		this._attributes.addAll(other.transferToList());
	}
=======
>>>>>>> 53d71a355a2c9e7ecbdac71628eca762aa22f824

	public boolean contains(Attribute a) {
		return _attributes.contains(a);
	}

	public int size() {
		return _attributes.size();
	}
<<<<<<< HEAD
	
	public boolean isEmpty(){
		return this._attributes.size()==0;
	}
=======
>>>>>>> 53d71a355a2c9e7ecbdac71628eca762aa22f824

	public boolean equals(Object other) {
		if(other == null || !(other instanceof AttributeSet)){
			return false;
		}
		//TODO: you should probably implement this
<<<<<<< HEAD
		AttributeSet temp = (AttributeSet) other;
		if (this.transferToList().containsAll(temp.transferToList()) &&
			temp.transferToList().containsAll(this.transferToList())){
			return true;
		}
		return false;
=======
		return this == other;
>>>>>>> 53d71a355a2c9e7ecbdac71628eca762aa22f824
	}

	public Iterator<Attribute> iterator() {
		return _attributes.iterator();
	}

	public String toString() {
		String out = "";
		Iterator<Attribute> iter = iterator();
		while(iter.hasNext())
			out += iter.next() + "\t";

		return out;
	}
	
	public List<Attribute> transferToList(){
		return this._attributes;
	}


}
<<<<<<< HEAD

=======
>>>>>>> 53d71a355a2c9e7ecbdac71628eca762aa22f824
