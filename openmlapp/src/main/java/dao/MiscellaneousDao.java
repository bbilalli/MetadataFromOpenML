package dao;

import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import entities.Aliases;
import util.FileUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by marcin on 26.05.16.
 */
public class MiscellaneousDao extends BaseDao {

    public List<String> selectAvailableMeasures() throws SQLException, ClassNotFoundException {
        List<String> measures = new ArrayList<>();

        this.executeQuery("SELECT * FROM available_measures",(result)->{
            measures.add(result.getString("measure"));
        });
        return measures;
    }

    public List<String> selectAvailableMetadata() throws SQLException, ClassNotFoundException {
        List<String> metadata = new ArrayList<>();

        this.executeQuery("SELECT * FROM available_metadata",(result)->{
            metadata.add(result.getString("quality"));
        });
        return metadata;
    }

    public List<String> selectMandatoryMetadata(){
        String path = "./mandatoryMetadata.txt";
        return getStrings(path);
    }



    public List<String> selectMandatoryAttributes(){
        return getStrings("./mandatoryAttributes.txt");
    }

    public List<String> selectMetadataPossibleToExtract(){
        return getStrings("./possibleToExtract.txt");
    }

    public Aliases getAttributeAliases(){
        JacksonXmlModule module = new JacksonXmlModule();
        module.setDefaultUseWrapper(false);
        XmlMapper xmlMapper = new XmlMapper(module);
        Aliases a = null;
        try {
             a = xmlMapper.readValue(new FileUtils().getRawFile("./aliases.xml"), Aliases.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(a == null){
            a = new Aliases();
            a.Aliases = new ArrayList<>();
        }
        return a;
    }
}
