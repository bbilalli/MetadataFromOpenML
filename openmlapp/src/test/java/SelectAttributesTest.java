import org.junit.Assert;
import org.junit.Test;
import util.WekaRunner;
import weka.core.Attribute;
import weka.core.Instances;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class SelectAttributesTest {

    @Test
    public void selectAttributesTest() {

        String classifierName = "weka.classifiers.trees.J48";
        String[] classifierOptions = {"-U"};
        String filterName = "weka.filters.unsupervised.instance.Randomize";
        String[] filterOptions = {};

        String dataSetPath = "./dataset_61_iris.arff";

        WekaRunner wekaRunner = new WekaRunner(classifierName, classifierOptions, filterName, filterOptions, dataSetPath);
        Instances trainingInstance = wekaRunner.getTrainingInstance();
        System.out.println(trainingInstance);


        Instances instances = WekaRunner.selectAttributes(trainingInstance, new String[]{"sepalwidth", "petalwidth"});
        System.out.println(instances);

        Assert.assertNotNull(instances.attribute("sepalwidth"));
        Assert.assertNotNull(instances.attribute("petalwidth"));
    }

    @Test
    public void selectAttributesTestCsv() {

        String classifierName = "weka.classifiers.bayes.NaiveBayes";
        String[] classifierOptions = {};
        String filterName = "weka.filters.unsupervised.instance.Randomize";
        String[] filterOptions = {};

        String dataSetPath = "./zoo.csv";

        WekaRunner wekaRunner = new WekaRunner(classifierName, classifierOptions, filterName, filterOptions, dataSetPath);
        Instances trainingInstance = wekaRunner.getTrainingInstance();
        System.out.println(trainingInstance);

        @SuppressWarnings("unchecked")
        ArrayList<Attribute> attributes = Collections.<Attribute>list(trainingInstance.enumerateAttributes());
        List<String> list = attributes.stream().map(Attribute::name).collect(Collectors.toList());


        for (int i = 0; i < 10; i++) {
            List<String> randomAttributesPicked = pickRandom(list, 4);
            Instances instances = WekaRunner.selectAttributes(trainingInstance, randomAttributesPicked);
            System.out.println(instances);

            final int testNum = i;
            randomAttributesPicked.forEach(attributeName ->
                    Assert.assertNotNull(String.format("Failed test %d for list: %s, attribute: %s.",
                            testNum, randomAttributesPicked, attributeName), instances.attribute(attributeName)));
        }
    }

    private static <T> List<T> pickRandom(List<T> list, int count) {
        List<T> ret = new ArrayList<>(count);
        if(count > list.size()) {
            throw new IllegalArgumentException("count > list.size");
        }
        for (int i = 0; i < count; i++) {
            T item;
            while(ret.contains(item = list.get(ThreadLocalRandom.current().nextInt(0, list.size())))) {}
            ret.add(item);
        }
        return ret;
    }

}
