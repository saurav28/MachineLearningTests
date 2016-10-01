package ml.mahout;

import java.io.File;
import java.util.List;

import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;


/**
 * Code is inspired from https://mahout.apache.org/users/recommender/userbased-5-minutes.html
 * This code is adopted for Movie Lens dataset. 
 * Also the code is adopted to Mahout 0.12.2
 * @author Saurav Sarkar
 *
 */
public class MahoutBasedRecommender {
	
	public static void main (String a[]){
		MahoutBasedRecommender recommender = new MahoutBasedRecommender();
		recommender.recommendFiles();
	}
	
	public void recommendFiles(){
		try{
		DataModel model = new FileDataModel(new File("<path to data set>"));
		UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
		UserNeighborhood neighborhood = new ThresholdUserNeighborhood(0.1, similarity, model);
		UserBasedRecommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);
		
		List<RecommendedItem> recommendations = recommender.recommend(2, 5);
		for (RecommendedItem recommendation : recommendations) {
		  System.out.println(recommendation);
		}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
