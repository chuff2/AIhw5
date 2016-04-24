import java.util.HashMap;
import java.util.Map;

/**
 * Your implementation of a naive bayes classifier. Please implement all four methods.
 */

public class NaiveBayesClassifierImpl implements NaiveBayesClassifier {

  /**
   * Trains the classifier with the provided training data and vocabulary size
   */
  @Override
  public void train(Instance[] trainingData, int v) {
    // TODO : Implement
  }

  /*
   * Prints out the number of documents for each label
   */
  public void documents_per_label_count(){
    // TODO : Implement
  }

  /*
   * Prints out the number of words for each label
   */
  public void words_per_label_count(){
    // TODO : Implement
  }

  /**
   * Returns the prior probability of the label parameter, i.e. P(SPAM) or P(HAM)
   */
  @Override
  public double p_l(Label label) {
    // TODO : Implement
    return 0;
  }

  /**
   * Returns the smoothed conditional probability of the word given the label, i.e. P(word|SPORTS) or
   * P(word|BUSINESS)
   */
  @Override
  public double p_w_given_l(String word, Label label) {
    // TODO : Implement
    return 0;
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
