package by.traning.task07.bean;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * composite object
 */
@Data
public class Composite implements Component {
    /**
     * a field describing the specific type of this object
     */
    private final Type type;
    /**
     * list of child components
     */
    private List<Component> components = new ArrayList<>();

    public Composite(Type type) {
        this.type = type;
    }

    /**
     * method for adding a component
     *
     * @param component component
     */
    public void add(Component component) {
        components.add(component);
    }

    /**
     * method for getting a list of components by a defined type
     *
     * @param type component type
     * @return list of components
     */
    public List<Component> getByType(Type type) {
        List<Component> result = new ArrayList<>();
        for (Component component : components) {
            if (((Composite) component).getType() == type) {
                result.add(component);
            } else {
                List<Component> temp;
                if (((Composite) component).getType() != Type.MARK &&
                        (temp = ((Composite) component).getByType(type)) != null) {
                    result.addAll(temp);
                }
            }
        }
        return result;
    }

    public void delete(Component component) {
        components.remove(component);
    }

    @Override
    public String collect() { //TODO :enum delimt or switch
        StringBuilder stringBuilder = new StringBuilder();
        for (Component component : components) {
            stringBuilder.append(component.collect());
            if (type == Type.PARAGRAPH) {
                stringBuilder.deleteCharAt(0).insert(0,"\t");
            }
            if (type == Type.SENTENCE) {
                stringBuilder.append(" ");
            }
            if (type == Type.TEXT) {
                stringBuilder.deleteCharAt(stringBuilder.length() - 1).append("\n");
            }
        }
        return stringBuilder.toString();
    }
}
