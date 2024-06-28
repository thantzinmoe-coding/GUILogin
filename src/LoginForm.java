import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LoginForm {
    private JTextField txtName;
    private JButton btnCancel;
    private JButton btnSave;
    private JPanel Login;
    private JTextField txtAge;
    private JTextField txtInfo;
    private JFrame frame;

    private String _connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=JAVAProj;user=sa;password=sasa@123;trustServerCertificate=true";

    public void ClearControls(){
        txtName.setText("");
        txtAge.setText("");
        txtName.requestFocusInWindow();
    }

    public LoginForm(){
        frame = new JFrame("Login Form");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(450, 300));
        frame.add(Login);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClearControls();
            }
        });
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent a) {
                String query ="INSERT INTO test_tbl (Name, Age) VALUES (?, ?)";
                try (Connection connection = DriverManager.getConnection(_connectionUrl);
                     PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                    preparedStatement.setString(1, txtName.getText());
                    preparedStatement.setInt(2, Integer.parseInt(txtAge.getText()));

                    // Execute the insert command
                    int rowsInserted = preparedStatement.executeUpdate();
                    if (rowsInserted > 0) {
                        txtInfo.setText("A new row was inserted successfully!");
                        ClearControls();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
