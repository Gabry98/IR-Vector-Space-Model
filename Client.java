/*
 * In this class i will show you the functionality of a Vector Space Model, which calculates
 * the ranking of every document inside a specified set, compared to a specified query. 
 * This program was created to show the importance of one of the main 
 * Information Retrieval models. I will use the DecimalFormat class to approximate
 * the values.
 */

import java.text.DecimalFormat;

public class Client {
	
	public static void main(String[] args) {
		
		DecimalFormat df = new DecimalFormat("0.00");
		
		//STEP 1: Documents Creation
		String[] doc1 = {"search","information"};
		String[] doc2 = {"information","retrieval","semantic","web"};
		String[] doc3 = {"search","information","retrieval"};
		
		//STEP 2: I add the documents to a collection:
		DocumentSet ds = new DocumentSet();
		ds.add(doc1);
		ds.add(doc2);
		ds.add(doc3);
		
		//STEP 3: Creation of the specified query:
		String[] query = {"semantic","search","retrieval"};
		
		//STEP 4: I make the Vector Model and print the similarities of the documents:
		VectorModel v = new VectorModel(ds);
		System.out.println("Similarity 1: "+df.format(v.similarity(0, query)));
		System.out.println("Similarity 2: "+df.format(v.similarity(1, query)));
		System.out.println("Similarity 3: "+df.format(v.similarity(2, query)));
		
	}
	
	
}
