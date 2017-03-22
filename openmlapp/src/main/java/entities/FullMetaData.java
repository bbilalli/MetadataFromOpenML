package entities;

/**
 * Created by piotr on 06.04.2016.
 */
public class FullMetaData {
    private int meta_did;
    private String name;
    private String default_target_attribute;
    private String url;
    private double ClassCount;
    private double ClassEntropy;
    private double DecisionStumpAUC;
    private double DecisionStumpErrRate;
    private double DecisionStumpKappa;
    private double DefaultAccuracy;
    private double Dimensionality;
    private double EquivalentNumberOfAtts;
    private double HoeffdingAdwin_changes;
    private double HoeffdingAdwin_warnings;
    private double IncompleteInstanceCount;
    private double InstanceCount;
    private double J48_00001_AUC;
    private double J48_00001_ErrRate;
    private double J48_00001_Kappa;
    private double J48_0001_AUC;
    private double J48_0001_ErrRate;
    private double J48_0001_Kappa;
    private double J48_001_AUC;
    private double J48_001_ErrRate;
    private double J48_001_Kappa;
    private double JRipAUC;
    private double JRipErrRate;
    private double JRipKappa;
    private int MajorityClassSize;
    private double MaxNominalAttDistinctValues;
    private double MeanAttributeEntropy;
    private double MeanKurtosisOfNumericAtts;
    private double MeanMeansOfNumericAtts;
    private double MeanMutualInformation;
    private double MeanNominalAttDistinctValues;
    private double MeanSkewnessOfNumericAtts;
    private double MeanStdDevOfNumericAtts;
    private double MinNominalAttDistinctValues;
    private int MinorityClassSize;
    private double NBTreeAUC;
    private double NBTreeErrRate;
    private double NBTreeKappa;
    private double NaiveBayesAUC;
    private double NaiveBayesAdwin_changes;
    private double NaiveBayesAdwin_warnings;
    private double NaiveBayesDdm_changes;
    private double NaiveBayesDdm_warnings;
    private double NaiveBayesErrRate;
    private double NaiveBayesKappa;
    private double NegativePercentage;
    private double NoiseToSignalRatio;
    private double NumAttributes;
    private double NumBinaryAtts;
    private double NumMissingValues;
    private double NumNominalAtts;
    private double NumNumericAtts;
    private int NumberOfClasses;
    private int NumberOfFeatures;
    private int NumberOfInstances;
    private int NumberOfInstancesWithMissingValues;
    private int NumberOfMissingValues;
    private int NumberOfNumericFeatures;
    private int NumberOfSymbolicFeatures;
    private double PercentageOfBinaryAtts;
    private double PercentageOfMissingValues;
    private double PercentageOfNominalAtts;
    private double PercentageOfNumericAtts;
    private double PositivePercentage;
    private double REPTreeDepth1AUC;
    private double REPTreeDepth1ErrRate;
    private double REPTreeDepth1Kappa;
    private double REPTreeDepth2AUC;
    private double REPTreeDepth2ErrRate;
    private double REPTreeDepth2Kappa;
    private double REPTreeDepth3AUC;
    private double REPTreeDepth3ErrRate;
    private double REPTreeDepth3Kappa;
    private double RandomTreeDepth1AUC;
    private double RandomTreeDepth1ErrRate;
    private double RandomTreeDepth1Kappa;
    private double RandomTreeDepth2AUC;
    private double RandomTreeDepth2ErrRate;
    private double RandomTreeDepth2Kappa;
    private double RandomTreeDepth3AUC;
    private double RandomTreeDepth3ErrRate;
    private double RandomTreeDepth3Kappa;
    private double SVMe1AUC;
    private double SVMe1ErrRate;
    private double SVMe1Kappa;
    private double SVMe2AUC;
    private double SVMe2ErrRate;
    private double SVMe2Kappa;
    private double SVMe3AUC;
    private double SVMe3ErrRate;
    private double SVMe3Kappa;
    private double SimpleLogisticAUC;
    private double SimpleLogisticErrRate;
    private double SimpleLogisticKappa;
    private double StdvNominalAttDistinctValues;
    private double kNN_1NAUC;
    private double kNN_1NErrRate;
    private double kNN_1NKappa;
    private double kNN_2NAUC;
    private double kNN_2NErrRate;
    private double kNN_2NKappa;
    private double kNN_3NAUC;
    private double kNN_3NErrRate;
    private double kNN_3NKappa;
    private int nested_did;
    private String dataset;
    private String measure;
    private double score;
    private String algorithm;
    private int cnt;

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(this.getMeta_did());
        sb.append(",");
        sb.append(this.getName());
        sb.append(",");
        sb.append(this.getDefault_target_attribute());
        sb.append(",");
        sb.append(this.getUrl());
        sb.append(",");
        sb.append(this.getClassCount());
        sb.append(",");
        sb.append(this.getClassEntropy());
        sb.append(",");
        sb.append(this.getDecisionStumpAUC());
        sb.append(",");
        sb.append(this.getDecisionStumpErrRate());
        sb.append(",");
        sb.append(this.getDecisionStumpKappa());
        sb.append(",");
        sb.append(this.getDefaultAccuracy());
        sb.append(",");
        sb.append(this.getDimensionality());
        sb.append(",");
        sb.append(this.getEquivalentNumberOfAtts());
        sb.append(",");
        sb.append(this.getHoeffdingAdwin_changes());
        sb.append(",");
        sb.append(this.getHoeffdingAdwin_warnings());
        sb.append(",");
        sb.append(this.getIncompleteInstanceCount());
        sb.append(",");
        sb.append(this.getInstanceCount());
        sb.append(",");
        sb.append(this.getJ48_00001_AUC());
        sb.append(",");
        sb.append(this.getJ48_00001_ErrRate());
        sb.append(",");
        sb.append(this.getJ48_00001_Kappa());
        sb.append(",");
        sb.append(this.getJ48_0001_AUC());
        sb.append(",");
        sb.append(this.getJ48_0001_ErrRate());
        sb.append(",");
        sb.append(this.getJ48_0001_Kappa());
        sb.append(",");
        sb.append(this.getJ48_001_AUC());
        sb.append(",");
        sb.append(this.getJ48_001_ErrRate());
        sb.append(",");
        sb.append(this.getJ48_001_Kappa());
        sb.append(",");
        sb.append(this.getJRipAUC());
        sb.append(",");
        sb.append(this.getJRipErrRate());
        sb.append(",");
        sb.append(this.getJRipKappa());
        sb.append(",");
        sb.append(this.getMajorityClassSize());
        sb.append(",");
        sb.append(this.getMaxNominalAttDistinctValues());
        sb.append(",");
        sb.append(this.getMeanAttributeEntropy());
        sb.append(",");
        sb.append(this.getMeanKurtosisOfNumericAtts());
        sb.append(",");
        sb.append(this.getMeanMeansOfNumericAtts());
        sb.append(",");
        sb.append(this.getMeanMutualInformation());
        sb.append(",");
        sb.append(this.getMeanNominalAttDistinctValues());
        sb.append(",");
        sb.append(this.getMeanSkewnessOfNumericAtts());
        sb.append(",");
        sb.append(this.getMeanStdDevOfNumericAtts());
        sb.append(",");
        sb.append(this.getMinNominalAttDistinctValues());
        sb.append(",");
        sb.append(this.getMinorityClassSize());
        sb.append(",");
        sb.append(this.getNBTreeAUC());
        sb.append(",");
        sb.append(this.getNBTreeErrRate());
        sb.append(",");
        sb.append(this.getNBTreeKappa());
        sb.append(",");
        sb.append(this.getNaiveBayesAUC());
        sb.append(",");
        sb.append(this.getNaiveBayesAdwin_changes());
        sb.append(",");
        sb.append(this.getNaiveBayesAdwin_warnings());
        sb.append(",");
        sb.append(this.getNaiveBayesDdm_changes());
        sb.append(",");
        sb.append(this.getNaiveBayesDdm_warnings());
        sb.append(",");
        sb.append(this.getNaiveBayesErrRate());
        sb.append(",");
        sb.append(this.getNaiveBayesKappa());
        sb.append(",");
        sb.append(this.getNegativePercentage());
        sb.append(",");
        sb.append(this.getNoiseToSignalRatio());
        sb.append(",");
        sb.append(this.getNumAttributes());
        sb.append(",");
        sb.append(this.getNumBinaryAtts());
        sb.append(",");
        sb.append(this.getNumMissingValues());
        sb.append(",");
        sb.append(this.getNumNominalAtts());
        sb.append(",");
        sb.append(this.getNumNumericAtts());
        sb.append(",");
        sb.append(this.getNumberOfClasses());
        sb.append(",");
        sb.append(this.getNumberOfInstances());
        sb.append(",");
        sb.append(this.getNumberOfInstancesWithMissingValues());
        sb.append(",");
        sb.append(this.getNumberOfMissingValues());
        sb.append(",");
        sb.append(this.getNumberOfNumericFeatures());
        sb.append(",");
        sb.append(this.getNumberOfSymbolicFeatures());
        sb.append(",");
        sb.append(this.getPercentageOfBinaryAtts());
        sb.append(",");
        sb.append(this.getPercentageOfMissingValues());
        sb.append(",");
        sb.append(this.getPercentageOfNominalAtts());
        sb.append(",");
        sb.append(this.getPercentageOfNumericAtts());
        sb.append(",");
        sb.append(this.getPositivePercentage());
        sb.append(",");
        sb.append(this.getREPTreeDepth1AUC());
        sb.append(",");
        sb.append(this.getREPTreeDepth1ErrRate());
        sb.append(",");
        sb.append(this.getREPTreeDepth1Kappa());
        sb.append(",");
        sb.append(this.getREPTreeDepth2AUC());
        sb.append(",");
        sb.append(this.getREPTreeDepth2ErrRate());
        sb.append(",");
        sb.append(this.getREPTreeDepth2Kappa());
        sb.append(",");
        sb.append(this.getREPTreeDepth3AUC());
        sb.append(",");
        sb.append(this.getREPTreeDepth3ErrRate());
        sb.append(",");
        sb.append(this.getREPTreeDepth3Kappa());
        sb.append(",");
        sb.append(this.getRandomTreeDepth1AUC());
        sb.append(",");
        sb.append(this.getRandomTreeDepth1ErrRate());
        sb.append(",");
        sb.append(this.getRandomTreeDepth1Kappa());
        sb.append(",");
        sb.append(this.getRandomTreeDepth2AUC());
        sb.append(",");
        sb.append(this.getRandomTreeDepth2ErrRate());
        sb.append(",");
        sb.append(this.getRandomTreeDepth2Kappa());
        sb.append(",");
        sb.append(this.getRandomTreeDepth3AUC());
        sb.append(",");
        sb.append(this.getRandomTreeDepth3ErrRate());
        sb.append(",");
        sb.append(this.getRandomTreeDepth3Kappa());
        sb.append(",");
        sb.append(this.getSVMe1AUC());
        sb.append(",");
        sb.append(this.getSVMe1ErrRate());
        sb.append(",");
        sb.append(this.getSVMe1Kappa());
        sb.append(",");
        sb.append(this.getSVMe2AUC());
        sb.append(",");
        sb.append(this.getSVMe2ErrRate());
        sb.append(",");
        sb.append(this.getSVMe2Kappa());
        sb.append(",");
        sb.append(this.getSVMe3AUC());
        sb.append(",");
        sb.append(this.getSVMe3ErrRate());
        sb.append(",");
        sb.append(this.getSVMe3Kappa());
        sb.append(",");
        sb.append(this.getSimpleLogisticAUC());
        sb.append(",");
        sb.append(this.getSimpleLogisticErrRate());
        sb.append(",");
        sb.append(this.getSimpleLogisticKappa());
        sb.append(",");
        sb.append(this.getStdvNominalAttDistinctValues());
        sb.append(",");
        sb.append(this.getkNN_1NAUC());
        sb.append(",");
        sb.append(this.getkNN_1NErrRate());
        sb.append(",");
        sb.append(this.getkNN_1NKappa());
        sb.append(",");
        sb.append(this.getkNN_2NAUC());
        sb.append(",");
        sb.append(this.getkNN_2NErrRate());
        sb.append(",");
        sb.append(this.getkNN_2NKappa());
        sb.append(",");
        sb.append(this.getkNN_3NAUC());
        sb.append(",");
        sb.append(this.getkNN_3NErrRate());
        sb.append(",");
        sb.append(this.getkNN_3NKappa());
        sb.append(",");
        sb.append(this.getNested_did());
        sb.append(",");
        sb.append(this.getDataset());
        sb.append(",");
        sb.append(this.getMeasure());
        sb.append(",");
        sb.append(this.getScore());
        sb.append(",");
        sb.append(this.getAlgorithm());
        sb.append(",");
        sb.append(this.getCnt());

        return sb.toString();
    }

    public int getMeta_did() {
        return meta_did;
    }

    public void setMeta_did(int meta_did) {
        this.meta_did = meta_did;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDefault_target_attribute() {
        return default_target_attribute;
    }

    public void setDefault_target_attribute(String default_target_attribute) {
        this.default_target_attribute = default_target_attribute;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public double getClassCount() {
        return ClassCount;
    }

    public void setClassCount(double classCount) {
        ClassCount = classCount;
    }

    public double getClassEntropy() {
        return ClassEntropy;
    }

    public void setClassEntropy(double classEntropy) {
        ClassEntropy = classEntropy;
    }

    public double getDecisionStumpAUC() {
        return DecisionStumpAUC;
    }

    public void setDecisionStumpAUC(double decisionStumpAUC) {
        DecisionStumpAUC = decisionStumpAUC;
    }

    public double getDecisionStumpErrRate() {
        return DecisionStumpErrRate;
    }

    public void setDecisionStumpErrRate(double decisionStumpErrRate) {
        DecisionStumpErrRate = decisionStumpErrRate;
    }

    public double getDecisionStumpKappa() {
        return DecisionStumpKappa;
    }

    public void setDecisionStumpKappa(double decisionStumpKappa) {
        DecisionStumpKappa = decisionStumpKappa;
    }

    public double getDefaultAccuracy() {
        return DefaultAccuracy;
    }

    public void setDefaultAccuracy(double defaultAccuracy) {
        DefaultAccuracy = defaultAccuracy;
    }

    public double getDimensionality() {
        return Dimensionality;
    }

    public void setDimensionality(double dimensionality) {
        Dimensionality = dimensionality;
    }

    public double getEquivalentNumberOfAtts() {
        return EquivalentNumberOfAtts;
    }

    public void setEquivalentNumberOfAtts(double equivalentNumberOfAtts) {
        EquivalentNumberOfAtts = equivalentNumberOfAtts;
    }

    public double getHoeffdingAdwin_changes() {
        return HoeffdingAdwin_changes;
    }

    public void setHoeffdingAdwin_changes(double hoeffdingAdwin_changes) {
        HoeffdingAdwin_changes = hoeffdingAdwin_changes;
    }

    public double getHoeffdingAdwin_warnings() {
        return HoeffdingAdwin_warnings;
    }

    public void setHoeffdingAdwin_warnings(double hoeffdingAdwin_warnings) {
        HoeffdingAdwin_warnings = hoeffdingAdwin_warnings;
    }

    public double getIncompleteInstanceCount() {
        return IncompleteInstanceCount;
    }

    public void setIncompleteInstanceCount(double incompleteInstanceCount) {
        IncompleteInstanceCount = incompleteInstanceCount;
    }

    public double getInstanceCount() {
        return InstanceCount;
    }

    public void setInstanceCount(double instanceCount) {
        InstanceCount = instanceCount;
    }

    public double getJ48_00001_AUC() {
        return J48_00001_AUC;
    }

    public void setJ48_00001_AUC(double j48_00001_AUC) {
        J48_00001_AUC = j48_00001_AUC;
    }

    public double getJ48_00001_ErrRate() {
        return J48_00001_ErrRate;
    }

    public void setJ48_00001_ErrRate(double j48_00001_ErrRate) {
        J48_00001_ErrRate = j48_00001_ErrRate;
    }

    public double getJ48_00001_Kappa() {
        return J48_00001_Kappa;
    }

    public void setJ48_00001_Kappa(double j48_00001_Kappa) {
        J48_00001_Kappa = j48_00001_Kappa;
    }

    public double getJ48_0001_AUC() {
        return J48_0001_AUC;
    }

    public void setJ48_0001_AUC(double j48_0001_AUC) {
        J48_0001_AUC = j48_0001_AUC;
    }

    public double getJ48_0001_ErrRate() {
        return J48_0001_ErrRate;
    }

    public void setJ48_0001_ErrRate(double j48_0001_ErrRate) {
        J48_0001_ErrRate = j48_0001_ErrRate;
    }

    public double getJ48_0001_Kappa() {
        return J48_0001_Kappa;
    }

    public void setJ48_0001_Kappa(double j48_0001_Kappa) {
        J48_0001_Kappa = j48_0001_Kappa;
    }

    public double getJ48_001_AUC() {
        return J48_001_AUC;
    }

    public void setJ48_001_AUC(double j48_001_AUC) {
        J48_001_AUC = j48_001_AUC;
    }

    public double getJ48_001_ErrRate() {
        return J48_001_ErrRate;
    }

    public void setJ48_001_ErrRate(double j48_001_ErrRate) {
        J48_001_ErrRate = j48_001_ErrRate;
    }

    public double getJ48_001_Kappa() {
        return J48_001_Kappa;
    }

    public void setJ48_001_Kappa(double j48_001_Kappa) {
        J48_001_Kappa = j48_001_Kappa;
    }

    public double getJRipAUC() {
        return JRipAUC;
    }

    public void setJRipAUC(double JRipAUC) {
        this.JRipAUC = JRipAUC;
    }

    public double getJRipErrRate() {
        return JRipErrRate;
    }

    public void setJRipErrRate(double JRipErrRate) {
        this.JRipErrRate = JRipErrRate;
    }

    public double getJRipKappa() {
        return JRipKappa;
    }

    public void setJRipKappa(double JRipKappa) {
        this.JRipKappa = JRipKappa;
    }

    public int getMajorityClassSize() {
        return MajorityClassSize;
    }

    public void setMajorityClassSize(int majorityClassSize) {
        MajorityClassSize = majorityClassSize;
    }

    public double getMaxNominalAttDistinctValues() {
        return MaxNominalAttDistinctValues;
    }

    public void setMaxNominalAttDistinctValues(double maxNominalAttDistinctValues) {
        MaxNominalAttDistinctValues = maxNominalAttDistinctValues;
    }

    public double getMeanAttributeEntropy() {
        return MeanAttributeEntropy;
    }

    public void setMeanAttributeEntropy(double meanAttributeEntropy) {
        MeanAttributeEntropy = meanAttributeEntropy;
    }

    public double getMeanKurtosisOfNumericAtts() {
        return MeanKurtosisOfNumericAtts;
    }

    public void setMeanKurtosisOfNumericAtts(double meanKurtosisOfNumericAtts) {
        MeanKurtosisOfNumericAtts = meanKurtosisOfNumericAtts;
    }

    public double getMeanMeansOfNumericAtts() {
        return MeanMeansOfNumericAtts;
    }

    public void setMeanMeansOfNumericAtts(double meanMeansOfNumericAtts) {
        MeanMeansOfNumericAtts = meanMeansOfNumericAtts;
    }

    public double getMeanMutualInformation() {
        return MeanMutualInformation;
    }

    public void setMeanMutualInformation(double meanMutualInformation) {
        MeanMutualInformation = meanMutualInformation;
    }

    public double getMeanNominalAttDistinctValues() {
        return MeanNominalAttDistinctValues;
    }

    public void setMeanNominalAttDistinctValues(double meanNominalAttDistinctValues) {
        MeanNominalAttDistinctValues = meanNominalAttDistinctValues;
    }

    public double getMeanSkewnessOfNumericAtts() {
        return MeanSkewnessOfNumericAtts;
    }

    public void setMeanSkewnessOfNumericAtts(double meanSkewnessOfNumericAtts) {
        MeanSkewnessOfNumericAtts = meanSkewnessOfNumericAtts;
    }

    public double getMeanStdDevOfNumericAtts() {
        return MeanStdDevOfNumericAtts;
    }

    public void setMeanStdDevOfNumericAtts(double meanStdDevOfNumericAtts) {
        MeanStdDevOfNumericAtts = meanStdDevOfNumericAtts;
    }

    public double getMinNominalAttDistinctValues() {
        return MinNominalAttDistinctValues;
    }

    public void setMinNominalAttDistinctValues(double minNominalAttDistinctValues) {
        MinNominalAttDistinctValues = minNominalAttDistinctValues;
    }

    public int getMinorityClassSize() {
        return MinorityClassSize;
    }

    public void setMinorityClassSize(int minorityClassSize) {
        MinorityClassSize = minorityClassSize;
    }

    public double getNBTreeAUC() {
        return NBTreeAUC;
    }

    public void setNBTreeAUC(double NBTreeAUC) {
        this.NBTreeAUC = NBTreeAUC;
    }

    public double getNBTreeErrRate() {
        return NBTreeErrRate;
    }

    public void setNBTreeErrRate(double NBTreeErrRate) {
        this.NBTreeErrRate = NBTreeErrRate;
    }

    public double getNBTreeKappa() {
        return NBTreeKappa;
    }

    public void setNBTreeKappa(double NBTreeKappa) {
        this.NBTreeKappa = NBTreeKappa;
    }

    public double getNaiveBayesAUC() {
        return NaiveBayesAUC;
    }

    public void setNaiveBayesAUC(double naiveBayesAUC) {
        NaiveBayesAUC = naiveBayesAUC;
    }

    public double getNaiveBayesAdwin_changes() {
        return NaiveBayesAdwin_changes;
    }

    public void setNaiveBayesAdwin_changes(double naiveBayesAdwin_changes) {
        NaiveBayesAdwin_changes = naiveBayesAdwin_changes;
    }

    public double getNaiveBayesAdwin_warnings() {
        return NaiveBayesAdwin_warnings;
    }

    public void setNaiveBayesAdwin_warnings(double naiveBayesAdwin_warnings) {
        NaiveBayesAdwin_warnings = naiveBayesAdwin_warnings;
    }

    public double getNaiveBayesDdm_changes() {
        return NaiveBayesDdm_changes;
    }

    public void setNaiveBayesDdm_changes(double naiveBayesDdm_changes) {
        NaiveBayesDdm_changes = naiveBayesDdm_changes;
    }

    public double getNaiveBayesDdm_warnings() {
        return NaiveBayesDdm_warnings;
    }

    public void setNaiveBayesDdm_warnings(double naiveBayesDdm_warnings) {
        NaiveBayesDdm_warnings = naiveBayesDdm_warnings;
    }

    public double getNaiveBayesErrRate() {
        return NaiveBayesErrRate;
    }

    public void setNaiveBayesErrRate(double naiveBayesErrRate) {
        NaiveBayesErrRate = naiveBayesErrRate;
    }

    public double getNaiveBayesKappa() {
        return NaiveBayesKappa;
    }

    public void setNaiveBayesKappa(double naiveBayesKappa) {
        NaiveBayesKappa = naiveBayesKappa;
    }

    public double getNegativePercentage() {
        return NegativePercentage;
    }

    public void setNegativePercentage(double negativePercentage) {
        NegativePercentage = negativePercentage;
    }

    public double getNoiseToSignalRatio() {
        return NoiseToSignalRatio;
    }

    public void setNoiseToSignalRatio(double noiseToSignalRatio) {
        NoiseToSignalRatio = noiseToSignalRatio;
    }

    public double getNumAttributes() {
        return NumAttributes;
    }

    public void setNumAttributes(double numAttributes) {
        NumAttributes = numAttributes;
    }

    public double getNumBinaryAtts() {
        return NumBinaryAtts;
    }

    public void setNumBinaryAtts(double numBinaryAtts) {
        NumBinaryAtts = numBinaryAtts;
    }

    public double getNumMissingValues() {
        return NumMissingValues;
    }

    public void setNumMissingValues(double numMissingValues) {
        NumMissingValues = numMissingValues;
    }

    public double getNumNominalAtts() {
        return NumNominalAtts;
    }

    public void setNumNominalAtts(double numNominalAtts) {
        NumNominalAtts = numNominalAtts;
    }

    public double getNumNumericAtts() {
        return NumNumericAtts;
    }

    public void setNumNumericAtts(double numNumericAtts) {
        NumNumericAtts = numNumericAtts;
    }

    public int getNumberOfClasses() {
        return NumberOfClasses;
    }

    public void setNumberOfClasses(int numberOfClasses) {
        NumberOfClasses = numberOfClasses;
    }

    public int getNumberOfFeatures() {
        return NumberOfFeatures;
    }

    public void setNumberOfFeatures(int numberOfFeatures) {
        NumberOfFeatures = numberOfFeatures;
    }

    public int getNumberOfInstances() {
        return NumberOfInstances;
    }

    public void setNumberOfInstances(int numberOfInstances) {
        NumberOfInstances = numberOfInstances;
    }

    public int getNumberOfInstancesWithMissingValues() {
        return NumberOfInstancesWithMissingValues;
    }

    public void setNumberOfInstancesWithMissingValues(int numberOfInstancesWithMissingValues) {
        NumberOfInstancesWithMissingValues = numberOfInstancesWithMissingValues;
    }

    public int getNumberOfMissingValues() {
        return NumberOfMissingValues;
    }

    public void setNumberOfMissingValues(int numberOfMissingValues) {
        NumberOfMissingValues = numberOfMissingValues;
    }

    public int getNumberOfNumericFeatures() {
        return NumberOfNumericFeatures;
    }

    public void setNumberOfNumericFeatures(int numberOfNumericFeatures) {
        NumberOfNumericFeatures = numberOfNumericFeatures;
    }

    public int getNumberOfSymbolicFeatures() {
        return NumberOfSymbolicFeatures;
    }

    public void setNumberOfSymbolicFeatures(int numberOfSymbolicFeatures) {
        NumberOfSymbolicFeatures = numberOfSymbolicFeatures;
    }

    public double getPercentageOfBinaryAtts() {
        return PercentageOfBinaryAtts;
    }

    public void setPercentageOfBinaryAtts(double percentageOfBinaryAtts) {
        PercentageOfBinaryAtts = percentageOfBinaryAtts;
    }

    public double getPercentageOfMissingValues() {
        return PercentageOfMissingValues;
    }

    public void setPercentageOfMissingValues(double percentageOfMissingValues) {
        PercentageOfMissingValues = percentageOfMissingValues;
    }

    public double getPercentageOfNominalAtts() {
        return PercentageOfNominalAtts;
    }

    public void setPercentageOfNominalAtts(double percentageOfNominalAtts) {
        PercentageOfNominalAtts = percentageOfNominalAtts;
    }

    public double getPercentageOfNumericAtts() {
        return PercentageOfNumericAtts;
    }

    public void setPercentageOfNumericAtts(double percentageOfNumericAtts) {
        PercentageOfNumericAtts = percentageOfNumericAtts;
    }

    public double getPositivePercentage() {
        return PositivePercentage;
    }

    public void setPositivePercentage(double positivePercentage) {
        PositivePercentage = positivePercentage;
    }

    public double getREPTreeDepth1AUC() {
        return REPTreeDepth1AUC;
    }

    public void setREPTreeDepth1AUC(double REPTreeDepth1AUC) {
        this.REPTreeDepth1AUC = REPTreeDepth1AUC;
    }

    public double getREPTreeDepth1ErrRate() {
        return REPTreeDepth1ErrRate;
    }

    public void setREPTreeDepth1ErrRate(double REPTreeDepth1ErrRate) {
        this.REPTreeDepth1ErrRate = REPTreeDepth1ErrRate;
    }

    public double getREPTreeDepth1Kappa() {
        return REPTreeDepth1Kappa;
    }

    public void setREPTreeDepth1Kappa(double REPTreeDepth1Kappa) {
        this.REPTreeDepth1Kappa = REPTreeDepth1Kappa;
    }

    public double getREPTreeDepth2AUC() {
        return REPTreeDepth2AUC;
    }

    public void setREPTreeDepth2AUC(double REPTreeDepth2AUC) {
        this.REPTreeDepth2AUC = REPTreeDepth2AUC;
    }

    public double getREPTreeDepth2ErrRate() {
        return REPTreeDepth2ErrRate;
    }

    public void setREPTreeDepth2ErrRate(double REPTreeDepth2ErrRate) {
        this.REPTreeDepth2ErrRate = REPTreeDepth2ErrRate;
    }

    public double getREPTreeDepth2Kappa() {
        return REPTreeDepth2Kappa;
    }

    public void setREPTreeDepth2Kappa(double REPTreeDepth2Kappa) {
        this.REPTreeDepth2Kappa = REPTreeDepth2Kappa;
    }

    public double getREPTreeDepth3AUC() {
        return REPTreeDepth3AUC;
    }

    public void setREPTreeDepth3AUC(double REPTreeDepth3AUC) {
        this.REPTreeDepth3AUC = REPTreeDepth3AUC;
    }

    public double getREPTreeDepth3ErrRate() {
        return REPTreeDepth3ErrRate;
    }

    public void setREPTreeDepth3ErrRate(double REPTreeDepth3ErrRate) {
        this.REPTreeDepth3ErrRate = REPTreeDepth3ErrRate;
    }

    public double getREPTreeDepth3Kappa() {
        return REPTreeDepth3Kappa;
    }

    public void setREPTreeDepth3Kappa(double REPTreeDepth3Kappa) {
        this.REPTreeDepth3Kappa = REPTreeDepth3Kappa;
    }

    public double getRandomTreeDepth1AUC() {
        return RandomTreeDepth1AUC;
    }

    public void setRandomTreeDepth1AUC(double randomTreeDepth1AUC) {
        RandomTreeDepth1AUC = randomTreeDepth1AUC;
    }

    public double getRandomTreeDepth1ErrRate() {
        return RandomTreeDepth1ErrRate;
    }

    public void setRandomTreeDepth1ErrRate(double randomTreeDepth1ErrRate) {
        RandomTreeDepth1ErrRate = randomTreeDepth1ErrRate;
    }

    public double getRandomTreeDepth1Kappa() {
        return RandomTreeDepth1Kappa;
    }

    public void setRandomTreeDepth1Kappa(double randomTreeDepth1Kappa) {
        RandomTreeDepth1Kappa = randomTreeDepth1Kappa;
    }

    public double getRandomTreeDepth2AUC() {
        return RandomTreeDepth2AUC;
    }

    public void setRandomTreeDepth2AUC(double randomTreeDepth2AUC) {
        RandomTreeDepth2AUC = randomTreeDepth2AUC;
    }

    public double getRandomTreeDepth2ErrRate() {
        return RandomTreeDepth2ErrRate;
    }

    public void setRandomTreeDepth2ErrRate(double randomTreeDepth2ErrRate) {
        RandomTreeDepth2ErrRate = randomTreeDepth2ErrRate;
    }

    public double getRandomTreeDepth2Kappa() {
        return RandomTreeDepth2Kappa;
    }

    public void setRandomTreeDepth2Kappa(double randomTreeDepth2Kappa) {
        RandomTreeDepth2Kappa = randomTreeDepth2Kappa;
    }

    public double getRandomTreeDepth3AUC() {
        return RandomTreeDepth3AUC;
    }

    public void setRandomTreeDepth3AUC(double randomTreeDepth3AUC) {
        RandomTreeDepth3AUC = randomTreeDepth3AUC;
    }

    public double getRandomTreeDepth3ErrRate() {
        return RandomTreeDepth3ErrRate;
    }

    public void setRandomTreeDepth3ErrRate(double randomTreeDepth3ErrRate) {
        RandomTreeDepth3ErrRate = randomTreeDepth3ErrRate;
    }

    public double getRandomTreeDepth3Kappa() {
        return RandomTreeDepth3Kappa;
    }

    public void setRandomTreeDepth3Kappa(double randomTreeDepth3Kappa) {
        RandomTreeDepth3Kappa = randomTreeDepth3Kappa;
    }

    public double getSVMe1AUC() {
        return SVMe1AUC;
    }

    public void setSVMe1AUC(double SVMe1AUC) {
        this.SVMe1AUC = SVMe1AUC;
    }

    public double getSVMe1ErrRate() {
        return SVMe1ErrRate;
    }

    public void setSVMe1ErrRate(double SVMe1ErrRate) {
        this.SVMe1ErrRate = SVMe1ErrRate;
    }

    public double getSVMe1Kappa() {
        return SVMe1Kappa;
    }

    public void setSVMe1Kappa(double SVMe1Kappa) {
        this.SVMe1Kappa = SVMe1Kappa;
    }

    public double getSVMe2AUC() {
        return SVMe2AUC;
    }

    public void setSVMe2AUC(double SVMe2AUC) {
        this.SVMe2AUC = SVMe2AUC;
    }

    public double getSVMe2ErrRate() {
        return SVMe2ErrRate;
    }

    public void setSVMe2ErrRate(double SVMe2ErrRate) {
        this.SVMe2ErrRate = SVMe2ErrRate;
    }

    public double getSVMe2Kappa() {
        return SVMe2Kappa;
    }

    public void setSVMe2Kappa(double SVMe2Kappa) {
        this.SVMe2Kappa = SVMe2Kappa;
    }

    public double getSVMe3AUC() {
        return SVMe3AUC;
    }

    public void setSVMe3AUC(double SVMe3AUC) {
        this.SVMe3AUC = SVMe3AUC;
    }

    public double getSVMe3ErrRate() {
        return SVMe3ErrRate;
    }

    public void setSVMe3ErrRate(double SVMe3ErrRate) {
        this.SVMe3ErrRate = SVMe3ErrRate;
    }

    public double getSVMe3Kappa() {
        return SVMe3Kappa;
    }

    public void setSVMe3Kappa(double SVMe3Kappa) {
        this.SVMe3Kappa = SVMe3Kappa;
    }

    public double getSimpleLogisticAUC() {
        return SimpleLogisticAUC;
    }

    public void setSimpleLogisticAUC(double simpleLogisticAUC) {
        SimpleLogisticAUC = simpleLogisticAUC;
    }

    public double getSimpleLogisticErrRate() {
        return SimpleLogisticErrRate;
    }

    public void setSimpleLogisticErrRate(double simpleLogisticErrRate) {
        SimpleLogisticErrRate = simpleLogisticErrRate;
    }

    public double getSimpleLogisticKappa() {
        return SimpleLogisticKappa;
    }

    public void setSimpleLogisticKappa(double simpleLogisticKappa) {
        SimpleLogisticKappa = simpleLogisticKappa;
    }

    public double getStdvNominalAttDistinctValues() {
        return StdvNominalAttDistinctValues;
    }

    public void setStdvNominalAttDistinctValues(double stdvNominalAttDistinctValues) {
        StdvNominalAttDistinctValues = stdvNominalAttDistinctValues;
    }

    public double getkNN_1NAUC() {
        return kNN_1NAUC;
    }

    public void setkNN_1NAUC(double kNN_1NAUC) {
        this.kNN_1NAUC = kNN_1NAUC;
    }

    public double getkNN_1NErrRate() {
        return kNN_1NErrRate;
    }

    public void setkNN_1NErrRate(double kNN_1NErrRate) {
        this.kNN_1NErrRate = kNN_1NErrRate;
    }

    public double getkNN_1NKappa() {
        return kNN_1NKappa;
    }

    public void setkNN_1NKappa(double kNN_1NKappa) {
        this.kNN_1NKappa = kNN_1NKappa;
    }

    public double getkNN_2NAUC() {
        return kNN_2NAUC;
    }

    public void setkNN_2NAUC(double kNN_2NAUC) {
        this.kNN_2NAUC = kNN_2NAUC;
    }

    public double getkNN_2NErrRate() {
        return kNN_2NErrRate;
    }

    public void setkNN_2NErrRate(double kNN_2NErrRate) {
        this.kNN_2NErrRate = kNN_2NErrRate;
    }

    public double getkNN_2NKappa() {
        return kNN_2NKappa;
    }

    public void setkNN_2NKappa(double kNN_2NKappa) {
        this.kNN_2NKappa = kNN_2NKappa;
    }

    public double getkNN_3NAUC() {
        return kNN_3NAUC;
    }

    public void setkNN_3NAUC(double kNN_3NAUC) {
        this.kNN_3NAUC = kNN_3NAUC;
    }

    public double getkNN_3NErrRate() {
        return kNN_3NErrRate;
    }

    public void setkNN_3NErrRate(double kNN_3NErrRate) {
        this.kNN_3NErrRate = kNN_3NErrRate;
    }

    public double getkNN_3NKappa() {
        return kNN_3NKappa;
    }

    public void setkNN_3NKappa(double kNN_3NKappa) {
        this.kNN_3NKappa = kNN_3NKappa;
    }

    public int getNested_did() {
        return nested_did;
    }

    public void setNested_did(int nested_did) {
        this.nested_did = nested_did;
    }

    public String getDataset() {
        return dataset;
    }

    public void setDataset(String dataset) {
        this.dataset = dataset;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
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

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }
}
