package org.romil.wordnet;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeSet;

import edu.smu.tspell.wordnet.NounSynset;
import edu.smu.tspell.wordnet.Synset;
import edu.smu.tspell.wordnet.SynsetType;
import edu.smu.tspell.wordnet.VerbSynset;
import edu.smu.tspell.wordnet.WordNetDatabase;
import edu.smu.tspell.wordnet.WordSense;

public class WordnetSynonyms {
	static WordNetDatabase database;
	public static void main(String[] args){
		WordnetSynonyms w = new WordnetSynonyms();
		System.out.println(w.getSynonyms("good"));
	}
	public WordnetSynonyms()
	{
		System.setProperty("wordnet.database.dir", "/home/romil/Documents/WordNet-3.0/dict/");
		database = WordNetDatabase.getFileInstance();
	}
	/**
	 * @param args
	 */
	public TreeSet<String> getSynonyms(String word) {
		NounSynset nounSynset;
		HashSet<String> syn = new HashSet<String>();
		TreeSet<String> wordToWordForms=new TreeSet<String>();
		wordToWordForms.add(word);
		Synset[] allSynsets = database.getSynsets(word,SynsetType.NOUN);
		int len=allSynsets.length;
		for(int i=0;i<len;i++)
		{
			System.err.println(allSynsets[i]);
			nounSynset = (NounSynset)(allSynsets[i]);
			for (String wordForm1 : nounSynset.getWordForms()) {
				if (!wordForm1.equals(word)) {
					wordToWordForms.add(wordForm1);
					for (WordSense antonym : nounSynset.getAntonyms(wordForm1)) {
						for (String opposite : antonym.getSynset().getWordForms()) {
							wordToWordForms.add(opposite);
							//System.out.println(opposite);
						}
					}
				}
			}

		}
		return wordToWordForms;
	}

}
