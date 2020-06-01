/*
 * This class calculates the documents ranking compared to a query selected by input.
 */

public class VectorModel {
	
	private DocumentSet ds;
	
	public VectorModel(DocumentSet ds) {
		this.ds = ds;
	}
	
	public double similarity(int index, String[] query) { //calculates the similarity
		
		double[] qterm = queryTermFrequency(query);
		
		double[] dterm = termWeight(ds.getDocument(index));
		
		double sim = product(dterm,qterm)/(norm(dterm)*norm(qterm));
		
		return sim;
	}
	
	//calculates the terms frequency inside the query compared to the vocabulary:
	private double[] queryTermFrequency(String[] query) {
		double[] queryTermFrequency = new double[ds.getVocabulary().size()];
		
		for(int i=0; i<query.length; i++) {
			for(int j=0; j<ds.getVocabulary().size(); j++) {
				if(!ds.getVocabulary().get(j).equals(query[i]) && queryTermFrequency[j]!=1) queryTermFrequency[j] = 0;
				else queryTermFrequency[j] = 1;
			}
		}
		
		return queryTermFrequency;
	}
	
	//calculates the terms weight of a selected document:
	private double[] termWeight(String[] document) {
		double[] termWeight = new double[ds.getVocabulary().size()];
		
		for(int i=0; i<document.length; i++) {
			for(int j=0; j<ds.getVocabulary().size(); j++) {
				if(!ds.getVocabulary().get(j).equals(document[i]) && termWeight[j]!=1) termWeight[j] = 0;
				else termWeight[j] = 1;
			}
		}
		
		for(int k=0; k<termWeight.length; k++) {
			termWeight[k]= termWeight[k]*inverseDocumentFrequency(ds.getVocabulary().get(k));
		}
			
		return termWeight;
	}
	
	//calculates the Inverse Document Frequency of a selected term:
	private double inverseDocumentFrequency(String term) {
		double count = 0;
		
		for(int i=0; i<ds.getList().size(); i++) {
			if(freq(term,ds.getList().get(i))>0) count++;
		}
		return Math.log10(ds.getList().size()/count);
	}
	
	//calculates the frequency of a term inside a selected document:
	private double freq(String term, String[] document) {
		double count = 0;
		
		for(int i=0; i<document.length; i++) {
			if(term.equals(document[i])) count++;
		}
		
		return count;
	}
	
	//calculates the product between the weights of documents term and query's term:
	private double product(double[] dterm, double[] qterm) {
		double product = 0;
		
		for(int i=0; i<ds.getVocabulary().size(); i++) {
			product = product + (dterm[i]*qterm[i]);
		}
		
		return product;
	}
	
	//calculates the norm, of a selected array, for the documents and the query:
	private double norm(double[] array) {
		double norm = 0;
		
		for(int i=0; i<array.length; i++) {
			norm = norm + Math.pow(array[i], 2);
		}
		
		return Math.sqrt(norm);
	}
}
