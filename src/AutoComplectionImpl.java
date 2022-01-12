import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class AutoComplectionImpl implements AutoCompletion {
	private Collection<String> strCollection;

	private AutoComplectionImpl() {
	}
	public AutoComplectionImpl(Collection<String> coll) {
		//[YG] collection is an encapsulation. Getting encapsulated collection from outside is not acceptable
		this.strCollection = coll;
	}
	@Override
	public boolean addWord(String word) {
		return strCollection.add(word);
	}

	@Override
	public boolean removeWord(String wordForRemove) {
		
		return strCollection.remove(wordForRemove);
	}

	@Override
	public Iterable<String> getCompletionOptions(String prefix) {
		List<String> listOfWordsStartsWith = new ArrayList<>();
		//[YG] no matter of collection forEach will give O[N]
		if (!strCollection.isEmpty()) {
			strCollection.forEach(word -> {
				if (word.startsWith(prefix)) 
					listOfWordsStartsWith.add(word);
			});
		}
		return listOfWordsStartsWith;
	}

}
