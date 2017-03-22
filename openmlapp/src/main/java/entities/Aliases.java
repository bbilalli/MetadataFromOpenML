package entities;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.List;

/**
 * Created by marcin on 6/4/16.
 */
@JacksonXmlRootElement(localName = "metaattributes")
public class Aliases{
    @JacksonXmlProperty(localName = "aliases")
    public List<Alias> Aliases;
}
