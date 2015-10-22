import java.util.*;

public class BCNF {

	/**
	 * Implement your algorithm here
	 **/
	public static Set<AttributeSet> decompose(AttributeSet attributeSet,
			Set<FunctionalDependency> functionalDependencies) {
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
			AttributeSet clos = closure(i,functionalDependencies);
			AttributeSet closs = new AttributeSet();
			for (Attribute j:clos.transferToList()){
				if (attributeSet.contains(j)) closs.addAttribute(j);
			}
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
			AttributeSet newSet = new AttributeSet();
			newSet.addAttribute(head);
			Iterator<Attribute> tt2 = temp.iterator();
			while (tt2.hasNext()) newSet.addAttribute(tt2.next());
			sets.add(newSet);
			sets.add(temp);
		}

		return sets;
	}

}
