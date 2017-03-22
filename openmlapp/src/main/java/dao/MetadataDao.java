package dao;

import java.sql.SQLException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by marcin on 26.05.16.
 */
public class MetadataDao extends BaseDao {

    public MetadataDao(){
        this.basicAttributesList = getStrings("./mandatoryAttributes.txt");
    }

    public List<String> basicAttributesList;//= Arrays.asList("did,name,measure,algorithm_class,algorithm,algorithm_parameters,score".split("[,]"));
    public final String scoreField = "score";

    public static String $(String ...sql){
        return String.join(System.getProperty("line.separator"),sql);
    }

    public List<Map<String,Object>> selectMetadataByAlgorithmClass(String algorithmClass, List<String> metaAttributes, String measure) throws SQLException, ClassNotFoundException {
        List<String> allAttributes = prepareSelectAttributes(metaAttributes);
        String query = $("SELECT ",String.join(",", allAttributes));
        query = $(query, " FROM algorithm_measures_on_datasets amd",
                    "join dataset_metadata meta on amd.did = meta.did",
                "where amd.measure = '" + measure + "'",
                "and amd.algorithm_class = '" + algorithmClass + "'"
                );


        List<Map<String, Object>> rawMetadata = executeQuery(allAttributes, query);

        String groupingField = "algorithm_class";
        return groupWithMedians(rawMetadata, x -> (String) x.get("did") + "__" + (String) x.get(groupingField));
    }

    public List<Map<String,Object>> selectMetadataByAlgorithmVersion(String algorithmVersion, List<String> metaAttributes, String measure) throws SQLException, ClassNotFoundException {
        List<String> allAttributes = prepareSelectAttributes(metaAttributes);
        String query = $("SELECT ",String.join(",", allAttributes));
        query = $(query, " FROM algorithm_measures_on_datasets amd",
                "join dataset_metadata meta on amd.did = meta.did",
                "where amd.measure = '" + measure + "'",
                "and amd.algorithm = '" + algorithmVersion + "'"
        );


        List<Map<String, Object>> rawMetadata = executeQuery(allAttributes, query);

        String groupingField = "algorithm";
        return groupWithMedians(rawMetadata, x -> (String) x.get("did") + "__" + (String) x.get(groupingField));
    }

    public List<Map<String,Object>> selectMetadataByAlgorithmParams(String algorithmParams, List<String> metaAttributes, String measure) throws SQLException, ClassNotFoundException {
        List<String> allAttributes = prepareSelectAttributes(metaAttributes);
        String query = $("SELECT ",String.join(",", allAttributes));

        String[] tmp = algorithmParams.split("[#]");
        String algorithmName = tmp[0];
        String actualParams = tmp[1].replace("<empty>","");

        query = $(query, " FROM algorithm_measures_on_datasets amd",
                "join dataset_metadata meta on amd.did = meta.did",
                "where amd.measure = '" + measure + "'",
                "and amd.algorithm = '" + algorithmName +  "'"
        );

        if(actualParams.isEmpty()){
            query = $(query,
                    "and amd.algorithm_parameters IS NULL"
                    );
        }
        else {
            query = $(query,
                    "and amd.algorithm_parameters= '" + actualParams + "'"
                    );
        }


        List<Map<String, Object>> rawMetadata = executeQuery(allAttributes, query);
        return groupWithMedians(rawMetadata, x -> (String) x.get("did") + "__" + (String)x.get("algorithm") + (String) x.get("algorithm_params") );
    }


    protected List<String> prepareSelectAttributes(List<String> metaAttributes) {
        List<String> metaAttributesList = metaAttributes.stream().map(ma -> "meta." + ma).collect(Collectors.toList());

        List<String> allAttributes = new ArrayList<>();
        allAttributes.addAll(basicAttributesList.stream().map(ba-> "amd." + ba).collect(Collectors.toList()));
        allAttributes.addAll(metaAttributesList);
        return allAttributes;
    }

    protected List<Map<String, Object>> executeQuery(List<String> allAttributes, String query) throws SQLException, ClassNotFoundException {
        List<Map<String,Object>> rawMetadata = new ArrayList<>();
        this.executeQuery(query,(resultRow)->{
            Map<String,Object> row = new HashMap<>();
            for (String attr : allAttributes) {
                String column = attr.split("[.]")[1];
                if(column.equals(scoreField)){
                    row.put(column,resultRow.getDouble(attr));
                }
                else {
                    row.put(column,resultRow.getString(attr));
                }
            }
            rawMetadata.add(row);
        });
        return rawMetadata;
    }

    protected List<Map<String, Object>> groupWithMedians(List<Map<String, Object>> rawMetadata, Function<Map<String, Object>, String> groupByFunction) {
        List<Map<String,Object>> metadata = new ArrayList<>();
        Map<String,List<Map<String,Object>>> groups = rawMetadata.stream().collect(Collectors.groupingBy(groupByFunction));
        for(Map.Entry<String,List<Map<String,Object>>> group : groups.entrySet()){
            List<Double> measures = group.getValue().stream().map(x->(Double)x.get(scoreField)).collect(Collectors.toList());
            measures.sort((o1, o2) -> o1 < o2 ? -1 : o1 > o2 ? 1 : 0);
            int cnt = measures.size();
            Double median = cnt % 2 == 0 ? (measures.get((cnt >> 1)-1) + measures.get(cnt >> 1))/2.0 : measures.get((cnt >> 1));

            Map<String,Object> metadataRow = group.getValue().get(0);
            metadataRow.remove(scoreField);
            metadataRow.put(scoreField,median);
            metadata.add(metadataRow);
        }
        return metadata;
    }
}
