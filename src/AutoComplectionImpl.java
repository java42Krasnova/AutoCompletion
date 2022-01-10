import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;


public class AutoComplectionImpl implements AutoCompletion {
	private Collection<String> strCollection;
/* V.R. It is the good idea to do collection flexible. 
 *  But default public ctor is redundant. It is necessary to do it private to prevent its using
 */
	public AutoComplectionImpl() {
		this.strCollection = new HashSet<>();
	}
	public AutoComplectionImpl(Collection<String> coll) {
		this.strCollection = coll;
	}
	@Override
	public boolean addWord(String word) {
		return strCollection.add(word);
	}

	@Override
	public boolean removeWord(String wordForRemove) {
		// V.R. It isn't necessary to check collection. The method
		// remove() knows how to do it
		if (strCollection.isEmpty()) {
			return false;
			}
		return strCollection.remove(wordForRemove);
	}

	@Override
	public Iterable<String> getCompletionOptions(String prefix) {
		/* V.R.
		 * There is other way to do the same, but more effective.
		 * I mean collection.removeIf(Predicate<String>)
		 */
		List<String> listOfWordsStartsWith = new ArrayList<>();
		if (!strCollection.isEmpty()) {
			strCollection.forEach(word -> {
				if (word.startsWith(prefix)) 
					listOfWordsStartsWith.add(word);
			});
		}
		return listOfWordsStartsWith;
	}

}
