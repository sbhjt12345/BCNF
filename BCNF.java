import java.util.*;

public class BCNF {

	/**
	 * Implement your algorithm here
	 **/
	public static Set<AttributeSet> decompose(AttributeSet attributeSet,
			Set<FunctionalDependency> functionalDependencies) {
		AttributeSet X = new AttributeSet(attributeSet);
		Set<FunctionalDependency> FD = new HashSet<FunctionalDependency>(functionalDependencies);
		Set<AttributeSet> res = new HashSet<>();
		if (X.size()==0 || X==null) return res;
		Set<AttributeSet> powerset = powerset(X);
		for (AttributeSet i:powerset){
			AttributeSet clos = closure(i,FD);
			AttributeSet closs = new AttributeSet();
			for (Attribute j:getSet(clos)){
				if (X.contains(j)) closs.addAttribute(j);
			}
			clos = closs;
			if (i.equals(clos) || clos.equals(X)) continue;
			Set<Attribute> atst = getSet(X);
			AttributeSet restOfClosure = new AttributeSet();
			// second part 
			for (Attribute n:getSet(i)){
				restOfClosure.addAttribute(n);
			}
			for (Attribute m:atst){
				if (!clos.contains(m)) restOfClosure.addAttribute(m);
			}
			res.addAll(decompose(clos,FD));
			res.addAll(decompose(restOfClosure,FD));
			return res;
		}
		res.add(X);
		return res;
	}

	/**
	 * Recommended helper method
	 **/
	public static AttributeSet closure(AttributeSet attributeSet, Set<FunctionalDependency> functionalDependencies) {
		// TODO: implement me!
		AttributeSet oldd = new AttributeSet();
		AttributeSet newd = new AttributeSet(attributeSet);
		boolean check = true;
		while (!oldd.equals(newd)){
			oldd = new AttributeSet(newd);
			for (FunctionalDependency fd : functionalDependencies){
				if (fd.independent().size() <= newd.size()){
					Iterator<Attribute> iter_fdind = fd.independent().iterator();
					while (iter_fdind.hasNext()){
						Attribute fdnext = iter_fdind.next();
						if (!newd.contains(fdnext)){
							check = false;                 
							break;
						} 
					}
				}
				if (check == true) {
					Iterator<Attribute> iter_fddep = fd.dependent().iterator();
					while (iter_fddep.hasNext()){
						newd.addAttribute(iter_fddep.next());
					}
				}
				check = true;
			}
		}

		return newd;
	}

	public static Set<AttributeSet> powerset(AttributeSet attributeSet){
		Set<AttributeSet> sets = new HashSet<>();
		if (attributeSet.size()==0 || attributeSet == null){
			sets.add(new AttributeSet());
			return sets;
		}
		List<Attribute> list = new ArrayList<>();
		Iterator<Attribute> ite = attributeSet.iterator();
		while (ite.hasNext()){
			list.add(ite.next());
		}
		Attribute head = list.get(0);
		Set<Attribute> rest_temp = new HashSet<Attribute>(list.subList(1, list.size()));
		AttributeSet rest = new AttributeSet();
		Iterator<Attribute> tt = rest_temp.iterator();
		while (tt.hasNext()){
			rest.addAttribute(tt.next());
		}
		for (AttributeSet temp:powerset(rest)){
			AttributeSet newSet = new AttributeSet();
			newSet.addAttribute(head);
			Iterator<Attribute> tt2 = temp.iterator();
			while (tt2.hasNext()) newSet.addAttribute(tt2.next());
			sets.add(newSet);
			sets.add(temp);
		}

		return sets;
	}

	public static Set<Attribute> getSet(AttributeSet haha){
		Iterator<Attribute> miao = haha.iterator();
		Set<Attribute> res = new HashSet<Attribute>();
		while (miao.hasNext()){
			res.add(miao.next());
		}
		return res;
	}
}
