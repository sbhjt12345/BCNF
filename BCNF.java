import java.util.*;

public class BCNF {

	/**
	 * Implement your algorithm here
	 **/
	public static Set<AttributeSet> decompose(AttributeSet attributeSet,
			Set<FunctionalDependency> functionalDependencies) {
<<<<<<< HEAD

		Set<AttributeSet> res = new HashSet<>();
		Set<AttributeSet> powerset = remove(powerset(attributeSet));
		System.out.println(powerset);

		for (AttributeSet i:powerset){
			System.out.println(i);
=======
//		AttributeSet X = new AttributeSet(attributeSet);
//		Set<FunctionalDependency> FD = new HashSet<FunctionalDependency>(functionalDependencies);
		Set<AttributeSet> res = new HashSet<>();
		Set<AttributeSet> powerset = powerset(attributeSet);
//		Set<AttributeSet> powerset = new HashSet<>();
//		for (AttributeSet mama : powersettemp){
//			AttributeSet temp22 = new AttributeSet();
//			for (Attribute ele : mama.transferToList()){
//				if (!ele.toString().isEmpty()){
//					temp22.addAttribute(ele);
//				}
//			}
//			powerset.add(temp22);
//			
//		}
		for (AttributeSet i:powerset){
>>>>>>> 53d71a355a2c9e7ecbdac71628eca762aa22f824
			AttributeSet clos = closure(i,functionalDependencies);
			AttributeSet closs = new AttributeSet();
			for (Attribute j:clos.transferToList()){
				if (attributeSet.contains(j)) closs.addAttribute(j);
			}
<<<<<<< HEAD
			System.out.println(closs);
=======
>>>>>>> 53d71a355a2c9e7ecbdac71628eca762aa22f824
			//clos = closs;
			if (i.equals(closs) || closs.equals(attributeSet)) {
				continue;
			}

			List<Attribute> restpart1 = new ArrayList<>(i.transferToList());
			for (Attribute xx:attributeSet.transferToList()){
				if (!closs.contains(xx)){
					restpart1.add(xx);
				}
			}
			AttributeSet rest = new AttributeSet(restpart1);
<<<<<<< HEAD
			System.out.println(rest);
=======
>>>>>>> 53d71a355a2c9e7ecbdac71628eca762aa22f824
			res.addAll(decompose(closs,functionalDependencies));
			res.addAll(decompose(rest,functionalDependencies));
			return res;
		}
		res.add(attributeSet);
		return res;
	}


	/**
	 * Recommended helper method
	 **/
	public static AttributeSet closure(AttributeSet attributeSet, Set<FunctionalDependency> functionalDependencies) {
		// TODO: implement me!
		AttributeSet update = new AttributeSet(attributeSet);
		AttributeSet newdep = new AttributeSet(attributeSet);
		HashMap<String,Integer> count = new HashMap<>();
		HashMap<String,Set<FunctionalDependency>> list = new HashMap<>();
		for (FunctionalDependency fd : functionalDependencies){
			count.put(fd.toString(), fd.getindependent().size());
			Iterator<Attribute> As = fd.getindependent().iterator();
			while (As.hasNext()){
				Attribute A = As.next();
				if (!list.containsKey(A.toString())){
					Set<FunctionalDependency> tempset = new HashSet<>();
					tempset.add(fd);
					list.put(A.toString(),tempset);
				}
				else{
					list.get(A.toString()).add(fd);
				}
			}
			while (update.size() != 0){
				Attribute tempA = update.transferToList().get(0);
				update.transferToList().remove(0);
				if (list.containsKey(tempA.toString())){
					Set<FunctionalDependency> miao= list.get(tempA.toString());
					for (FunctionalDependency tempX : miao){
						int minus1 = count.get(tempX.toString()) -1;
						count.put(tempX.toString(), minus1);
						if (minus1 == 0){
							AttributeSet dependX = tempX.dependent();
							AttributeSet add = new AttributeSet();
							for (Attribute xx : dependX.transferToList()){
								if (!newdep.transferToList().contains(xx)){
									add.addAttribute(xx);
								}
							}
							for (Attribute xx2 : add.transferToList()){
								update.addAttribute(xx2);
								newdep.addAttribute(xx2);
							}

						}
					}
				}
			}
		}
		return newdep;
	}

	public static Set<AttributeSet> powerset(AttributeSet attributeSet){
		Set<AttributeSet> sets = new HashSet<>();

		if (attributeSet.size()==0){
			sets.add(new AttributeSet(attributeSet));
			return sets;
		}
		List<Attribute> list = new ArrayList<>(attributeSet.transferToList());
		Attribute head = list.get(0);

		AttributeSet rest = new AttributeSet(list.subList(1, list.size()));
		
		for (AttributeSet temp:powerset(rest)){
<<<<<<< HEAD
//			System.out.println(temp);
=======
>>>>>>> 53d71a355a2c9e7ecbdac71628eca762aa22f824
			AttributeSet newSet = new AttributeSet();
			newSet.addAttribute(head);
			Iterator<Attribute> tt2 = temp.iterator();
			while (tt2.hasNext()) newSet.addAttribute(tt2.next());
			sets.add(newSet);
<<<<<<< HEAD
//			System.out.println(sets);
			sets.add(temp);
//			System.out.println(sets);
		}
		return sets;
	}
	
	public static Set<AttributeSet> remove (Set<AttributeSet> powerset){
	    Iterator<AttributeSet> poiter = powerset.iterator();
	    Set<AttributeSet> newres = new HashSet<>();
	    while (poiter.hasNext()){
	    	AttributeSet next = poiter.next();
	    	if (!next.isEmpty()) newres.add(next);
	    }	
	return newres;	
	}
   
	public static void main(String args[]){
		BCNF a = new BCNF();
	    AttributeSet attrs = new AttributeSet();
	    attrs.addAttribute(new Attribute("a"));
	    attrs.addAttribute(new Attribute("b"));
	    attrs.addAttribute(new Attribute("c"));
	    System.out.println(remove(a.powerset(attrs)));
	}
}

=======
			sets.add(temp);
		}

		return sets;
	}

}
>>>>>>> 53d71a355a2c9e7ecbdac71628eca762aa22f824
