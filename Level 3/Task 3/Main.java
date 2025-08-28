import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame implements ActionListener {

    private JTextField display;
    private String operator = "";
    private double number1 = 0;
    private boolean start = true;

    public static void main(String[] args) {
        new Main();
    }

    public Main() {
        setTitle("Modern Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel container = new JPanel(new BorderLayout(10, 10));
        container.setBackground(new Color(0x2D2D2D));

        display = new JTextField();
        display.setEditable(false);
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        display.setFont(new Font("Arial", Font.PLAIN, 36));
        display.setBackground(new Color(0x4A4A4A));
        display.setForeground(Color.WHITE);
        display.setBorder(javax.swing.BorderFactory.createEmptyBorder(16, 16, 16, 16));

        JPanel gridPanel = new JPanel(new GridLayout(6, 4, 10, 10));
        gridPanel.setBackground(new Color(0x2D2D2D));

        String[][] buttons = {
                {"%", "CE", "C", "<-"},
                {"1/x", "x²", "√x", "/"},
                {"7", "8", "9", "*"},
                {"4", "5", "6", "-"},
                {"1", "2", "3", "+"},
                {"+/-", "0", ".", "="}
        };

        for (int y = 0; y < buttons.length; y++) {
            for (int x = 0; x < buttons[y].length; x++) {
                String label = buttons[y][x];
                JButton button = createButton(label);
                gridPanel.add(button);
            }
        }

        container.add(display, BorderLayout.NORTH);
        container.add(gridPanel, BorderLayout.CENTER);

        add(container, BorderLayout.CENTER);
        setSize(400, 600);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JButton createButton(String label) {
        JButton button = new JButton(label);
        button.setFont(new Font("Arial", Font.PLAIN, 20));
        button.addActionListener(this);

        if (label.equals("=")) {
            button.setBackground(new Color(0xFF8F00));
            button.setForeground(Color.WHITE);
        } else if (label.matches("[0-9.]")) {
            button.setBackground(new Color(0x616161));
            button.setForeground(Color.WHITE);
        } else {
            button.setBackground(new Color(0x424242));
            button.setForeground(Color.WHITE);
        }

        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String value = ((JButton) e.getSource()).getText();
        onButtonClick(value);
    }

    private void onButtonClick(String value) {
        if (value.matches("[0-9]")) {
            handleNumber(value);
        } else {
            handleOperator(value);
        }
    }

    private void handleNumber(String value) {
        if (start) {
            display.setText("");
            start = false;
        }
        display.setText(display.getText() + value);
    }

    private void handleOperator(String value) {
        String currentText = display.getText();
        if (currentText.isEmpty() &&
                !(value.equals("C") || value.equals("CE") || value.equals("+/-") || value.equals("."))) {
            return;
        }

        switch (value) {
            case "+":
            case "-":
            case "*":
            case "/":
                if (!operator.isEmpty() && !start) {
                    calculate();
                }
                operator = value;
                number1 = currentText.isEmpty() ? 0 : Double.parseDouble(currentText);
                start = true;
                break;
            case "=":
                if (operator.isEmpty()) return;
                calculate();
                operator = "";
                start = true;
                break;
            case "C":
                display.setText("");
                operator = "";
                number1 = 0;
                start = true;
                break;
            case "CE":
                display.setText("");
                start = true;
                break;
            case "<-":
                if (!currentText.isEmpty()) {
                    display.setText(currentText.substring(0, currentText.length() - 1));
                }
                break;
            case ".":
                if (!display.getText().contains(".")) {
                    display.setText(display.getText() + ".");
                    start = false;
                }
                break;
            case "+/-":
                if (!currentText.isEmpty() && !currentText.equals("0")) {
                    display.setText(String.valueOf(Double.parseDouble(currentText) * -1));
                }
                break;
            case "%":
                if (!currentText.isEmpty()) {
                    display.setText(String.valueOf(Double.parseDouble(currentText) / 100.0));
                    start = true;
                }
                break;
            case "1/x":
                if (!currentText.isEmpty()) {
                    double n = Double.parseDouble(currentText);
                    if (n == 0) {
                        display.setText("Error");
                    } else {
                        display.setText(String.valueOf(1 / n));
                    }
                    start = true;
                }
                break;
            case "x²":
                if (!currentText.isEmpty()) {
                    display.setText(String.valueOf(Math.pow(Double.parseDouble(currentText), 2)));
                    start = true;
                }
                break;
            case "√x":
                if (!currentText.isEmpty()) {
                    double n = Double.parseDouble(currentText);
                    if (n < 0) {
                        display.setText("Error");
                    } else {
                        display.setText(String.valueOf(Math.sqrt(n)));
                    }
                    start = true;
                }
                break;
        }
    }

    private void calculate() {
        double number2 = Double.parseDouble(display.getText());
        double result = 0;

        switch (operator) {
            case "+":
                result = number1 + number2;
                break;
            case "-":
                result = number1 - number2;
                break;
            case "*":
                result = number1 * number2;
                break;
            case "/":
                if (number2 == 0) {
                    display.setText("Error");
                    return;
                }
                result = number1 / number2;
                break;
        }

        if (result == (long) result) {
            display.setText(String.format("%d", (long) result));
        } else {
            display.setText(String.format("%s", result));
        }
    }
}
