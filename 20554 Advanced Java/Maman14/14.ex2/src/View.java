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
        setLayout(new BorderLayout());
        add(canvas, BorderLayout.CENTER);
        add(createShapePanel(), BorderLayout.EAST);
        changeLabelsForShape();
        addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseReleased(MouseEvent mouseEvent)
            {
                vc.screenClick(mouseEvent.getX(), mouseEvent.getY());
            }
        });
    }

    public JPanel createShapePanel()
    {
        JPanel shapePanel = new JPanel();
        shapePanel.setLayout(new BoxLayout(shapePanel, BoxLayout.Y_AXIS));

        lblTitle = new JLabel("Add new Shape:");
        comboShape = new JComboBox(shapeChooser());
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

        lblStartX = new JLabel("Origin X:");
        txtStartX = new JTextField();
        shapePanel.add(lblStartX);
        shapePanel.add(txtStartX);

        lblStartY = new JLabel("Origin Y:");
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
        String selectedShape = comboShape.getSelectedItem().toString().toLowerCase();
        if (selectedShape.equals("line"))
        {
            lblEndX.setText("End X:");
            lblEndY.setText("End Y:");
        }
        else
        {
            lblEndX.setText("Width:");
            lblEndY.setText("Height:");
        }
    }

    public void btnAddShapeClick()
    {
        int startX, startY, endX, endY;
        try
        {
            startX = Integer.parseInt(txtStartX.getText());
            startY = Integer.parseInt(txtStartY.getText());
            endX = Integer.parseInt(txtEndX.getText());
            endY = Integer.parseInt(txtEndY.getText());
        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(this, "Please specify valid numbers for shape dimensions.");
            return;
        }
        String selectedShape = (String) comboShape.getSelectedItem();
        if (selectedShape.equals("Line"))
        {
            vc.addLine(new MyLine(startX, startY, endX, endY, getColorFromCombo()));
        }
        else if (selectedShape.equals("Oval"))
        {
            vc.addOval(new MyOval(startX, startY, endX, endY, getColorFromCombo(), true));
        }
        else if (selectedShape.equals("Rectangle"))
        {
            vc.addRect(new MyRectangle(startX, startY, endX, endY, getColorFromCombo(), true));
        }
    }

    public Color getColorFromCombo()
    {
        String strColor = comboColor.getSelectedItem().toString().toLowerCase();
        if (strColor.equals("red"))
        {
            return Color.RED;
        }
        if (strColor.equals("blue"))
        {
            return Color.BLUE;
        }
        if (strColor.equals("green"))
        {
            return Color.GREEN;
        }
        if (strColor.equals("yellow"))
        {
            return Color.YELLOW;
        }

        return Color.BLACK;
    }

    public String[] shapeChooser()
    {
        int answer = JOptionPane.showOptionDialog(null,
                "What shapes would you like to draw?",
                "",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                new String[]{"All shapes", "Lines only", "Bounded shapes only"},
                null);

        switch (answer)
        {
            case 0:
                return new String[]{"Line", "Oval", "Rectangle"};
            case 1:
                return new String[]{"Line"};
            case 2:
                return new String[]{"Oval", "Rectangle"};
            case JOptionPane.CLOSED_OPTION:
                System.exit(0);
        }
        return new String[]{};
    }
}
