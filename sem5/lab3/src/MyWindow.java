import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

public class MyWindow extends JFrame {
    private JLabel inputClassNameLabel = new JLabel("");
    private JTextField inputClassNameField = new JTextField("tests.MyClass");
    private JButton findClassBtn = new JButton("");
    private ButtonGroup group = new ButtonGroup();
    private String className = new String();

    private ResourceBundle resourcesBundle;

    public MyWindow(){
        setMyLocale(new Locale("ru", "RU"));
        setTitle("LAB_3");
        setSize(600,500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocation(350,150);

        inputClassNameLabel.setText(resourcesBundle.getString("input"));
        inputClassNameLabel.setLocation(20,0);
        inputClassNameLabel.setSize(150,20);
        add(inputClassNameLabel);

        inputClassNameField.setLocation(160,0);
        inputClassNameField.setSize(200,20);
        add(inputClassNameField);

        findClassBtn.setText(resourcesBundle.getString("getFields"));
        findClassBtn.setLocation(400,0);
        findClassBtn.setSize(130,20);
        add(findClassBtn);


        JRadioButton ruButton = new JRadioButton("RU", true);
        group.add(ruButton);
        JRadioButton enButton = new JRadioButton("EN", false);
        group.add(enButton);

        findClassBtn.addActionListener(e -> {
            className = inputClassNameField.getText();
            Class cl = null;
            Class[] classes = null;
            String superClass = null;
            try {
                cl = Class.forName(className);
                superClass = cl.getSuperclass().toString().substring(6);
                classes = cl.getInterfaces();

                JOptionPane.showMessageDialog(null,resourcesBundle.getString("message"));
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null,resourcesBundle.getString("error"),"", JOptionPane.ERROR_MESSAGE);

            }

            Field[] fields = cl.getDeclaredFields();
            Constructor[] constructors = cl.getDeclaredConstructors();
            Method[] methods = cl.getDeclaredMethods();
            System.out.println(classes.length);
            try(FileWriter writer = new FileWriter("MyFile.java", false))
            {
                if (classes.length == 0)
                {
                    writer.write("class " + className + " extends " + superClass + " {");
                }
                else {
                    writer.write("class " + className + " extends " + superClass + " implements ");
                    for (Class c: classes)
                    {
                        System.out.println(c.toString());
                        writer.write(c.toString().substring(9) + ", ");
                    }
                    writer.write(" {");
                }
                for (Field field : fields)
                {
                    writer.write("\n\t" + field.toString());

                }
                writer.write("\n");
                for (Constructor constructor : constructors)
                {
                    writer.write("\n\t" + constructor.toString());

                }
                writer.write("\n");
                for (Method method: methods)
                {
                    writer.write("\n\t" + method.toString());
                }
                writer.write("\n" + "}");
            }
            catch(IOException ex){
                System.out.println(ex.getMessage());
            }
        });

        enButton.addActionListener(e -> {
            setMyLocale(new Locale("en", "EN"));
            inputClassNameLabel.setText(resourcesBundle.getString("input"));
            findClassBtn.setText(resourcesBundle.getString("getFields"));
        });

        ruButton.addActionListener(e -> {
            setMyLocale(new Locale("ru", "RU"));
            inputClassNameLabel.setText(resourcesBundle.getString("input"));
            findClassBtn.setText(resourcesBundle.getString("getFields"));
        });

        enButton.setLocation(530,400);
        enButton.setSize(50,50);
        add(enButton);

        ruButton.setLocation(480,400);
        ruButton.setSize(50,50);
        add(ruButton);

        setLayout(null);
        setVisible(true);
    }

    private void setMyLocale(Locale locale){
        resourcesBundle = ResourceBundle.getBundle
                ("resources", locale);
    }

}
