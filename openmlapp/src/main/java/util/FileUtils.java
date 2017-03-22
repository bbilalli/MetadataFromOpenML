package util;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sun.nio.ch.IOUtil;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by marcin on 26.05.16.
 */
public class FileUtils {

    public static File LAST_FILE = new File(".");
    public static String CSV_DELIMITER = ",";

    public List<String> getStrings(String path) {
        List<String> data = new ArrayList<>();
        Path file = Paths.get(path);
        try (InputStream in = Files.newInputStream(file);
             BufferedReader reader =
                     new BufferedReader(new InputStreamReader(in))) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                if(!line.isEmpty()){
                    data.add(line.intern());
                }
            }
        } catch (IOException x) {
            System.err.println(x);
        }
        return data;
    }

    public String getRawFile(String path){
        String content = null;

        File file = new File(path);
        try (FileInputStream fis = new FileInputStream(file)) {
            byte[] data = new byte[(int) file.length()];
            fis.read(data);
            fis.close();

            content = new String(data, "UTF-8");

            } catch (IOException e) {
            e.printStackTrace();
        }
       return content;
    }



    public void saveToCsv(File file, List<String> labels, List<Map<String, Object>> results) {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(file.getAbsolutePath(), "UTF-8");
            writer.println(String.join(CSV_DELIMITER, labels));
            for(Map<String,Object> result : results){
                List<String> values = labels.stream().map(attr->{
                    Object x =  result.get(attr);
                    if(x != null){
                        return x.toString();
                    }
                    return "";
                }).collect(Collectors.toList());
                writer.println(String.join(CSV_DELIMITER,values));
            }

            writer.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(writer != null){
                writer.close();
            }
        }
    }

    public List<FileChooser.ExtensionFilter> getArffAndCsvExtensions() {
        List<FileChooser.ExtensionFilter> extensionFilters = getCsvExtensions();
        extensionFilters.addAll(getArffExtensions());
        return extensionFilters;
    }

    public List<FileChooser.ExtensionFilter> getCsvExtensions() {
        List<FileChooser.ExtensionFilter> extensions = new ArrayList<>();
        extensions.add(new FileChooser.ExtensionFilter("CSV file", "*.csv"));
        return extensions;
    }

    public List<FileChooser.ExtensionFilter> getArffExtensions() {
        List<FileChooser.ExtensionFilter> extensions = new ArrayList<>();
        extensions.add(new FileChooser.ExtensionFilter("ARFF file", "*.arff"));
        return extensions;
    }

    public File saveFileViaDialog(List<FileChooser.ExtensionFilter> extensionList, Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().clear();



        fileChooser.getExtensionFilters().addAll(extensionList);

        fileChooser.setTitle("Save file");
        return fileChooser.showSaveDialog(stage);
    }

    public File openFileViaDialog(List<FileChooser.ExtensionFilter> extensionFilters, Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().clear();
        fileChooser.getExtensionFilters().addAll(extensionFilters);

        fileChooser.setTitle("Open file");
        fileChooser.setInitialDirectory(LAST_FILE);
        File file = fileChooser.showOpenDialog(stage);
        if(file != null) {
            FileUtils.LAST_FILE = file.getParentFile();
        }
        return file;
    }


}
