package util;
import dao.MiscellaneousDao;
import entities.Alias;
import entities.Aliases;
import fantail.dc.*;
import fantail.dc.landmarking.*;
import fantail.dc.statistical.*;
import weka.core.Instances;
import weka.core.converters.ConverterUtils;
import weka.core.converters.ConverterUtils.DataSource;

import java.util.*;
import java.io.*;
import java.util.stream.Collectors;

/**
 *
 * Created by marcin on 6/4/16.
 */
public class MetadataExtractor {

    public Map<String,Object> extractMetadata(File file, List<String> selectedAttributes) throws Exception {
        List<DCValue> dcValues = extractMetadataFromFile(file);
        Map<String,Object> metadata = new HashMap<>();
        Aliases aliases = new MiscellaneousDao().getAttributeAliases();
        for (String attr :
                selectedAttributes) {
            List<DCValue> meta = findMetaAttribute(dcValues, attr);
            if(meta.size()>=1){
                metadata.put(attr,meta.get(0).value);
            }
            else {
                Alias a = aliases.Aliases.stream().filter(als->als.For.equals(attr)).findFirst().orElse(null);
                if(a != null && a.Alias != null){
                    meta = findMetaAttribute(dcValues,a.Alias);
                    if(meta.size()>=1){
                        metadata.put(attr,meta.get(0).value);
                    }
                }
                else {
                    System.out.println("Could not find " + attr + " in extracted data");
                }
            }
        }
        return metadata;
    }

    protected List<DCValue> findMetaAttribute(List<DCValue> dcValues, String name) {
        return dcValues.stream().filter(v->v.id.toLowerCase().equals(name.toLowerCase())).collect(Collectors.toList());
    }

    public Map<String,Object> extractAllMetadata(File file) throws Exception {
        List<DCValue> dcValues = extractMetadataFromFile(file);
        Map<String, Object> metadata = new HashMap<>();
        for (DCValue dcValue :
                dcValues) {
            metadata.put(dcValue.id,dcValue.value);
        }
        return metadata;
    }

    protected List<DCValue> extractMetadataFromFile(File file) throws Exception {
        String datasetPath = file.getAbsolutePath();
        DataSource source = new DataSource(datasetPath);
        Instances data = source.getDataSet();
        if (data.classIndex() == -1) {
            data.setClassIndex(data.numAttributes() - 1);
        }

        return extractAllMetaAttributes(data);
    }

    protected List<DCValue> extractAllMetaAttributes(Instances data) {
        Characterizer dc = new Statistical();
        List<DCValue> dcValues = new ArrayList<>();

        dcValues.addAll(Arrays.asList(dc.characterize(data)));

        dc = new AttributeCount();
        dcValues.addAll(Arrays.asList(dc.characterize(data)));


        dc = new AttributeType();
        dcValues.addAll(Arrays.asList(dc.characterize(data)));


        dc = new ClassAtt();
        dcValues.addAll(Arrays.asList(dc.characterize(data)));


        dc = new DefaultAccuracy();
        dcValues.addAll(Arrays.asList(dc.characterize(data)));


        dc = new IncompleteInstanceCount();
        dcValues.addAll(Arrays.asList(dc.characterize(data)));


        dc = new InstanceCount();
        dcValues.addAll(Arrays.asList(dc.characterize(data)));


        dc = new MissingValues();
        dcValues.addAll(Arrays.asList(dc.characterize(data)));


        dc = new NominalAttDistinctValues();
        dcValues.addAll(Arrays.asList(dc.characterize(data)));

        dc = new AttributeEntropy();
        dcValues.addAll(Arrays.asList(dc.characterize(data)));


        dc = new SimpleLandmarkers();
        dcValues.addAll(Arrays.asList(dc.characterize(data)));


        dc = new J48BasedLandmarker();
        dcValues.addAll(Arrays.asList(dc.characterize(data)));


        dc = new REPTreeBasedLandmarker();
        dcValues.addAll(Arrays.asList(dc.characterize(data)));

        dc = new RandomTreeBasedLandmarker();
        dcValues.addAll(Arrays.asList(dc.characterize(data)));
        return dcValues;
    }
}
