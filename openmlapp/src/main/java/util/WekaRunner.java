package util;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.core.Attribute;
import weka.core.Instances;
import weka.core.OptionHandler;
import weka.core.Utils;
import weka.core.converters.CSVLoader;
import weka.core.pmml.PMMLFactory;
import weka.core.pmml.PMMLModel;
import weka.filters.Filter;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class WekaRunner {

    private final Classifier classifier;
    private final Filter filter;
    private final File dataSetFile, testDataFile;
    private final Instances trainingInstance, testData;
    private Evaluation evaluation;

    public WekaRunner(String classifierName, String[] classifierOptions,
                      String filterName, String[] filterOptions, String dataSetPath, String testDataPath) {
        this.classifier = loadClassifier(classifierName, classifierOptions);
        this.filter = loadFilter(filterName, filterOptions);
        this.dataSetFile = getFileIfExists(dataSetPath);
        this.trainingInstance = loadTrainingInstance(dataSetFile);
        this.testDataFile = getFileIfExists(testDataPath);
        this.testData = loadTrainingInstance(testDataFile);
    }

    public WekaRunner(String classifierName, String[] classifierOptions,
                      String filterName, String[] filterOptions, String dataSetPath) {
        this.classifier = loadClassifier(classifierName, classifierOptions);
        this.filter = loadFilter(filterName, filterOptions);
        this.dataSetFile = getFileIfExists(dataSetPath);
        this.trainingInstance = loadTrainingInstance(dataSetFile);
        this.testDataFile = null;
        this.testData = null;
    }

    public WekaRunner(String classifierName, String[] classifierOptions,
                      String filterName, String[] filterOptions, Instances instances) {
        this.classifier = loadClassifier(classifierName, classifierOptions);
        this.filter = loadFilter(filterName, filterOptions);
        this.dataSetFile = null;
        this.trainingInstance = instances;
        this.testDataFile = null;
        this.testData = null;
    }


    public WekaRunner(String serializedPath, String filterName,
                      String[] filterOptions, String dataSetPath) {
        this.classifier = loadSerializedClassifier(serializedPath);
        this.filter = loadFilter(filterName, filterOptions);
        this.dataSetFile = getFileIfExists(dataSetPath);
        this.trainingInstance = loadTrainingInstance(dataSetFile);
        this.testDataFile = null;
        this.testData = null;
    }

    public void execute() {
        Instances filtered = getFiltered();

        try {
            classifier.buildClassifier(filtered);
        } catch (Exception e){
            throw new IllegalStateException("Training classifier exception.", e);
        }

        Evaluation evaluation = getEvaluation();
    }

    public File getDataSetFile() {
        return dataSetFile;
    }

    public File getTestDataFile() {
        return testDataFile;
    }

    public Instances getTestData() {
        return testData;
    }

    public void setEvaluation(Evaluation evaluation) {
        this.evaluation = evaluation;
    }

    public Classifier getClassifier() {
        return classifier;
    }

    public Filter getFilter() {
        return filter;
    }

    public Instances getTrainingInstance() {
        return trainingInstance;
    }

    private Instances getFiltered(){
        Instances filtered;
        try {
            filter.setInputFormat(trainingInstance);
            filtered = Filter.useFilter(trainingInstance, filter);
        } catch (Exception e) {
            throw new IllegalStateException("Filtering dataSet exception.", e);
        }
        return filtered;
    }

    public Evaluation getEvaluation() {
        if(evaluation == null){
            Instances filtered = getFiltered();
            try {
                evaluation = new Evaluation(filtered);
                if(testData == null) {
                    evaluation.crossValidateModel(
                        classifier, filtered, 10, trainingInstance.getRandomNumberGenerator(1));
                } else {
                    evaluation.evaluateModel(classifier, testData);
                }
            } catch (Exception e) {
                throw new IllegalStateException("Folding exception.", e);
            }
        }
        return evaluation;
    }

    private void serializeToFile(String path) {
        if(path == null || path.trim().isEmpty()){
            throw new IllegalArgumentException("Path to dataSet file should not be empty");
        }
        File file = new File(path);
        if(file.isDirectory()){
            throw new IllegalArgumentException(String.format("File \"%s\" is a directory!", file.getAbsolutePath()));
        }
        if(!(classifier instanceof PMMLModel)){
            throw new IllegalArgumentException(classifier.getClass() + " is not PMMLModel.");
        }
        PMMLModel pmmlModel = (PMMLModel)classifier;
        try {
            PMMLFactory.serializePMMLModel(pmmlModel, file);
        } catch (Exception e) {
            throw new IllegalArgumentException("Serialization exception.", e);
        }

//        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))){
//            oos.writeObject(classifier);
//        } catch (IOException e) {
//            throw new IllegalArgumentException("Serialization exception.", e);
//        }
    }

    public String toString() {
        StringBuffer result;

        result = new StringBuffer();
        result.append("Weka - Demo\n===========\n\n");

        result.append("Classifier...: "
                + classifier.getClass().getName() + " "
                + Utils.joinOptions(((OptionHandler)classifier).getOptions()) + "\n");
        if (filter instanceof OptionHandler)
            result.append("Filter.......: "
                    + filter.getClass().getName() + " "
                    + Utils.joinOptions(((OptionHandler) filter).getOptions()) + "\n");
        else
            result.append("Filter.......: "
                    + filter.getClass().getName() + "\n");
        result.append("Training file: "
                + trainingInstance + "\n");
        result.append("\n");

        result.append(classifier.toString() + "\n");
        result.append(getEvaluation().toSummaryString() + "\n");
        try {
            result.append(getEvaluation().toMatrixString() + "\n");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        try {
            result.append(getEvaluation().toClassDetailsString() + "\n");
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return result.toString();
    }

    private static Classifier loadSerializedClassifier(String path) {
        File file = getFileIfExists(path);
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (Classifier) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new IllegalArgumentException("Deserialization exception.", e);
        }
    }

    private static File getFileIfExists(String path){
        if(path == null || path.trim().isEmpty()){
            throw new IllegalArgumentException("Path to dataSet file should not be empty");
        }
        File file = new File(path);
        if(!file.exists()){
            throw new IllegalArgumentException(String.format("File \"%s\" does not exists", file.getAbsolutePath()));
        }
        if(file.isDirectory()){
            throw new IllegalArgumentException(String.format("File \"%s\" is a directory!", file.getAbsolutePath()));
        }
        return file;
    }

    public static Instances loadTrainingInstanceSecured(File file) {
        file = getFileIfExists(file.getAbsolutePath());
        return loadTrainingInstance(file);
    }

    public static Instances loadTrainingInstance(File file) {
        try {
            Instances instances;
            if(file.getAbsolutePath().toLowerCase().endsWith(".csv")) {
                CSVLoader csvLoader = new CSVLoader();
                csvLoader.setSource(file);
                instances = csvLoader.getDataSet();
            } else {
                instances = new Instances(
                        new BufferedReader(new FileReader(file)));
            }
            instances.setClassIndex(instances.numAttributes() - 1);
            return instances;
        } catch (Exception e) {
            throw new IllegalArgumentException(
                    String.format("Cannot load training instance from file %s. ",
                            file.getAbsolutePath()), e);
        }
    }

    public static Instances selectAttributes(Instances instances, String[] selected) {
        return selectAttributes(instances, Arrays.asList(selected));
    }

    public static Instances selectAttributes(Instances instances, List<String> selected) {
        Instances ret = new Instances(instances);
        for (int i = 0; i < ret.numAttributes(); i++) {
            Attribute attribute = ret.attribute(i);
            String name = attribute.name();
            if (ret.classIndex() != i && selected.stream().noneMatch(s -> s.equals(name))) {
                ret.deleteAttributeAt(i);
                i--;
            }
        }
        return ret;
    }

    private static Classifier loadClassifier(String classifierName, String[] classifierOptions) {
        try {
            Classifier classifier = (Classifier) Class.forName(classifierName).newInstance();
            if (classifier instanceof OptionHandler)
                ((OptionHandler) classifier).setOptions(classifierOptions);
            return classifier;
        } catch (Exception e) {
            throw new IllegalArgumentException(
                    String.format("Cannot load classifier for name %s with options %s. ",
                            classifierName, Arrays.toString(classifierOptions)), e);
        }
    }

    private static Filter loadFilter(String filterName, String[] filterOptions) {
        try {
            Filter filter = (Filter) Class.forName(filterName).newInstance();
            if (filter instanceof OptionHandler)
                ((OptionHandler) filter).setOptions(filterOptions);
            return filter;
        } catch (Exception e) {
            throw new IllegalArgumentException(
                    String.format("Cannot load filter for name %s with options %s. ",
                            filterName, Arrays.toString(filterOptions)), e);
        }
    }

    public static void weka(String classifierName) {
        String[] classifierOptions = {};
        String filterName = "weka.filters.unsupervised.instance.Randomize";
        String[] filterOptions = {};

        String dataSetPath = "./dataset_61_iris.arff";
        String outputModel = "./trained.model";

        WekaRunner wekaRunner = new WekaRunner(classifierName, classifierOptions, filterName, filterOptions, dataSetPath);
        wekaRunner.execute();
        wekaRunner.serializeToFile(outputModel);
        System.out.println(wekaRunner);

        WekaRunner wekaRunner2 = new WekaRunner(outputModel, filterName, filterOptions, dataSetPath);
        System.out.println(wekaRunner2);
    }

    public static void main(String[] args){
        String classifierName = "weka.classifiers.trees.J48";
        weka(classifierName);
    }


}
