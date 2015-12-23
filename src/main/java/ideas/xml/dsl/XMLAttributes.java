package ideas.xml.dsl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class XMLAttributes {
    private final List<XMLAttribute> attributes;

    public XMLAttributes() {
        this.attributes = new ArrayList<>();
    }

    public void add(XMLAttribute attribute) {
        attributes.add(attribute);
    }

    public Collection<XMLAttribute> attributes() {
        return attributes;
    }
}
