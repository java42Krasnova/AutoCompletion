
public interface AutoCompletion {
	
boolean addWord(String word);
boolean removeWord(String wordForRemove);
public Iterable<String> getCompletionOptions(String prefix);
}
