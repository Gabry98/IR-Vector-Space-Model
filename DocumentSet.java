/*
 * This class contains all the methods about the set of documents studied 
 * inside the vector model.
 */


import java.util.Comparator;
import java.util.LinkedList;

public class DocumentSet {
	private LinkedList<String[]> documents = new LinkedList<String[]>();
	private LinkedList<String> vocabulary = new LinkedList<String>();
	
	public void add(String[] d) { //adds a document inside the set
		documents.add(d);
		makeVocabulary(d);
	}
	
	public void remove(String[] d) { //removes a document inside the set
		documents.remove(d);
		cleanVocabulary(d);
	}
	
	public String[] getDocument(int index) { //returns a document
		return documents.get(index);
	}
	
	public LinkedList<String[]> getList() { //returns the entire list of documents
		return documents;
	}
	
	public void set(int index, String[] content) { //modifies a document of the set
		documents.set(index, content);
	}
	
	private void makeVocabulary(String[] d) { //makes a vocabulary of terms
		for(int j=0; j<d.length;j++) {
			if(!vocabulary.contains(d[j])) vocabulary.add(d[j]);
		}
		vocabulary.sort(Comparator.naturalOrder());
		
	}
	
	private void cleanVocabulary(String[] d) { //removes the terms of a deleted document
		
		for(int i=0; i<d.length; i++) {
			String term = d[i];
			if(documents.stream().noneMatch(doc->compareTerms(doc,term))) vocabulary.remove(term);
		}
		
		vocabulary.sort(Comparator.naturalOrder());
	}
	
	//if the term is not in any document of list, i will remove it from the vocabulary:
	private boolean compareTerms(String[] doc1, String term) {
		boolean compare = false;
		
		for(int i=0; i<doc1.length; i++) {
			if(doc1[i].equals(term)) compare = true;
		}
		
		return compare;
	}
	
	public LinkedList<String> getVocabulary() { //returns the vocabulary of terms
		return vocabulary;
	}
}
