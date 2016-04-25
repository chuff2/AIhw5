import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Your implementation of a naive bayes classifier. Please implement all four methods.
 */

public class NaiveBayesClassifierImpl implements NaiveBayesClassifier {

	//instance variables
	int v;//v value passed into train
	int businessDocsCount;//total number of business documents counted in training
	int sportsDocsCount;//total number of sports documents counted in training
	HashMap<Label, HashMap<String, Integer>> labelMap;//String is word and integer is occurance count
	HashMap<Label, Integer> summationTermByLabel;
	
	
  /**
   * Trains the classifier with the provided training data and vocabulary size
   */
  @Override
  public void train(Instance[] trainingData, int v) {
    // TODO : Implement
	  this.v = v;
	  this.businessDocsCount = 0;
	  this.sportsDocsCount = 0;
	  this.labelMap = new HashMap<Label, HashMap<String, Integer>>();
	  
	  //gonna store all the words in the vocab so that we can calc the summation term
	  //in the denominator of the smoothing function
	  HashSet<String> entireVocab = new HashSet<String>();
	  
	  //put all the labels into the hashmap
	  for(Label singleLabel : Label.values())
		  labelMap.put(singleLabel, new HashMap<String, Integer>());
	  
	  
	  for (Instance singleInstance : trainingData){
		  Label currInstLabel = singleInstance.label;
		  //update label counts
		  if (currInstLabel == Label.BUSINESS) 
			  businessDocsCount++;
		  else
			  sportsDocsCount++;
		  //add the words to the hashmap
		  for (int i = 0; i < singleInstance.words.length; i++){
			  //update the word count for this label-word pair
			  HashMap<String, Integer> newWordCountPair = labelMap.get(currInstLabel);
			  if (newWordCountPair.containsKey(singleInstance.words[i])){
				  Integer newCount = (Integer) ((int) newWordCountPair.get(singleInstance.words[i])) + 1;
				  newWordCountPair.put(singleInstance.words[i], newCount);
			  }
			  else{
				  newWordCountPair.put(singleInstance.words[i], 1);
			  }
			  labelMap.put(currInstLabel, newWordCountPair);
			  
			  //add to the entire vocab
			  if (!entireVocab.contains(singleInstance.words[i]))
				  entireVocab.add(singleInstance.words[i]);
		  }
	  }
	  
	  //loop through the entire vocab to build up the vocab count by label
	  this.summationTermByLabel = new HashMap<Label, Integer>();
	  for (Label singleLabel : Label.values()){
		  int countForLabel = 0;
		  for (String singleWordInVocab : entireVocab){
			  if (labelMap.get(singleLabel).containsKey(singleWordInVocab))
				  countForLabel += (int) labelMap.get(singleLabel).get(singleWordInVocab);
		  }
		  summationTermByLabel.put(singleLabel, (Integer) countForLabel);
	  }
	  //for debug purposes
	  //System.out.println(entireVocab.size() + " " + v);
	  
  }

  /*
   * Prints out the number of documents for each label
   */
  public void documents_per_label_count(){
    // TODO : Implement VERIFIED TO WORK
	  for (Label singleLabel : Label.values()){
		  int count = (singleLabel == Label.BUSINESS) ? businessDocsCount: sportsDocsCount;
		  System.out.println(singleLabel.toString() + "=" + count);
	  }
  }

  /*
   * Prints out the number of words for each label
   */
  public void words_per_label_count(){
    // TODO : Implement VERIFIED TO WORK
	  for (Label singleLabel : Label.values()){
		  HashMap<String, Integer> wordIntPair = labelMap.get(singleLabel);
		  int runningCount = 0;
		  for (String word : labelMap.get(singleLabel).keySet()) {
			  runningCount += (int) (labelMap.get(singleLabel).get(word));
		  }
		  System.out.println(singleLabel.toString() + "=" + runningCount);
	  }
  }

  /**
   * Returns the prior probability of the label parameter, i.e. P(SPAM) or P(HAM)
   */
  @Override
  public double p_l(Label label) {
    // TODO : Implement COMPLETED, BUT NOT VERIFIED
	  double total = (double) businessDocsCount + sportsDocsCount;
	  double priorProb = (label == Label.BUSINESS) ? ((double) businessDocsCount)/total: ((double) sportsDocsCount)/total;
    return priorProb;
  }

  /**
   * Returns the smoothed conditional probability of the word given the label, i.e. P(word|SPORTS) or
   * P(word|BUSINESS)
   */
  @Override
  public double p_w_given_l(String word, Label label) {
    // TODO : Implement COMPLETED, BUT NOT VERIFIED
	  
	  double condProb = (((double)labelMap.get(label).get(word))+0.00001)
			  /(((double) this.v)*0.00001 + ((double) summationTermByLabel.get(label)));
	  
    return condProb;
  }

  /**
   * Classifies an array of words as either SPAM or HAM.
   */
  @Override
  public ClassifyResult classify(String[] words) {
    // TODO : Implement
    return null; 
  }
  
  /*
   * Constructs the confusion matrix
   */
  @Override
  public ConfusionMatrix calculate_confusion_matrix(Instance[] testData){
    // TODO : Implement
    return null;
  }
  
}
