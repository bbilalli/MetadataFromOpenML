package entities;

/**
 * Created by piotr on 05.04.2016.
 */
public class PredictiveAccuracy {
    private int did;
    private String dataset;
    private double score;
    private String algorithm;

    public PredictiveAccuracy(){

    }

    public PredictiveAccuracy(int did, String dataset, double score, String algorithm) {
        this.did = did;
        this.dataset = dataset;
        this.score = score;
        this.algorithm = algorithm;
    }

    public int getDid() {
        return did;
    }

    public void setDid(int did) {
        this.did = did;
    }

    public String getDataset() {
        return dataset;
    }

    public void setDataset(String dataset) {
        this.dataset = dataset;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }
}
