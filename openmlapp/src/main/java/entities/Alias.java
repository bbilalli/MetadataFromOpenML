package entities;


import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Alias {

    @JacksonXmlProperty(localName = "for")
    public String For;

    @JacksonXmlProperty(localName = "alias")
    public String Alias;
}
