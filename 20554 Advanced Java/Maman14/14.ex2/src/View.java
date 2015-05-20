import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by Stas on 20/05/2015 20:13.
 */

public class View extends JPanel
{
    private ViewController vc;
    private JPanel canvas;
    private JLabel lblTitle;
    private JComboBox comboShape;
    private JLabel lblColor;
    private JComboBox comboColor;
    private JLabel lblStartX;
    private JTextField txtStartX;
    private JLabel lblStartY;
    private JLabel lblEndX;
    private JTextField txtEndX;
    private JTextField txtStartY;
    private JTextField txtEndY;
    private JLabel lblEndY;
    private JButton btnAddShape;

    public View(ViewController vc)
    {
        this.vc = vc;
        initUI();
        this.vc.pictureCanvas = canvas; //TODO: set this
    }

    public void initUI()
    {
        canvas = new JPanel();
        canvas.setBackground(Color.PINK);
        setLayout(new BorderLayout());
        add(canvas, BorderLayout.CENTER);
        add(createShapePanel(), BorderLayout.EAST);
    }

    public JPanel createShapePanel()
    {
        JPanel shapePanel = new JPanel();
        shapePanel.setLayout(new BoxLayout(shapePanel, BoxLayout.Y_AXIS));

        lblTitle = new JLabel("Add new Shape:");
        comboShape = new JComboBox(new String[]{"Line", "Oval", "Rectangle"});
        comboShape.addItemListener(new ItemListener()
        {
            @Override
            public void itemStateChanged(ItemEvent itemEvent)
            {
                changeLabelsForShape();
            }
        });

        shapePanel.add(lblTitle);
        shapePanel.add(comboShape);

        lblColor = new JLabel("Choose color:");
        comboColor = new JComboBox(new String[]{"Red", "Blue", "Green", "Yellow"});
        shapePanel.add(lblColor);
        shapePanel.add(comboColor);

        lblStartX = new JLabel("Start X:");
        txtStartX = new JTextField();
        shapePanel.add(lblStartX);
        shapePanel.add(txtStartX);

        lblStartY = new JLabel("Start Y:");
        txtStartY = new JTextField();
        shapePanel.add(lblStartY);
        shapePanel.add(txtStartY);

        lblEndX = new JLabel("End X:");
        txtEndX = new JTextField();
        shapePanel.add(lblEndX);
        shapePanel.add(txtEndX);

        lblEndY = new JLabel("End Y:");
        txtEndY = new JTextField();
        shapePanel.add(lblEndY);
        shapePanel.add(txtEndY);

        btnAddShape = new JButton("Add shape");
        shapePanel.add(btnAddShape);
        btnAddShape.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                btnAddShapeClick();
            }
        });

        return shapePanel;
    }

    public void changeLabelsForShape()
    {

    }

    public void btnAddShapeClick()
    {
        String selectedShape = (String) comboShape.getSelectedItem();
        int startX = Integer.parseInt(txtStartX.getText());
        int startY = Integer.parseInt(txtStartY.getText());
        int endX = Integer.parseInt(txtEndX.getText());
        int endY = Integer.parseInt(txtEndY.getText());
        if (selectedShape == "Line")
        {
            vc.addLine(new MyLine(startX, startY, endX, endY, getColorFromCombo()));
        }
        else if (selectedShape == "Oval")
        {
            vc.addOval(new MyOval(startX, startY, endX, endY, getColorFromCombo(), true));
        }
        else if (selectedShape == "Rectangle")
        {
            vc.addRect(new MyRectangle(startX, startY, endX, endY, getColorFromCombo(), true));
        }
    }

    public Color getColorFromCombo()
    {
        String strColor = comboColor.getSelectedItem().toString().toLowerCase();
        if (strColor == "red")
        {
            return Color.RED;
        }
        if (strColor == "blue")
        {
            return Color.BLUE;
        }
        if (strColor == "green")
        {
            return Color.GREEN;
        }
        if (strColor == "yellow")
        {
            return Color.YELLOW;
        }

        return Color.BLACK;
    }
}
