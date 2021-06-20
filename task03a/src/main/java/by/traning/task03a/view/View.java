package by.traning.task03a.view;

/**
 * interface for working with the view
 */
public interface View {
    /**
     * display method on the screen
     * @param string data row
     */
    void show(String string);

    /**
     * screen reading method
     * @return data row
     */
    String read();
}
