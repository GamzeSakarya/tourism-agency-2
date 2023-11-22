package View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Helper.Config;
import Helper.Helper;
import Model.Admin;
import Model.Employee;
import Model.User;


public class LoginGUI extends JFrame {
    private JPanel wrapper;
    private JPanel wtop;
    private JPanel wbottom;
    private JTextField fld_user_name;
    private JPasswordField fld_user_pass;
    private JButton btn_login;

    public LoginGUI() {
        add(wrapper);
        setSize(400, 400);
        int x = Helper.screenCenter("x", getSize());
        int y = Helper.screenCenter("y", getSize());
        setLocation(x, y);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);
        btn_login.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_user_name) || Helper.isFieldEmpty(fld_user_pass)) {
                Helper.showMsg("fill");
            } else {
                User u = User.getFetch(fld_user_name.getText(), fld_user_pass.getText());
                if (u == null) {
                    Helper.showMsg("Kullanıcı bulunamadı!.Kullanıcı adı veya şifre yanlış!");
                } else {
                    switch (u.getType()) {
                        case "admin":
                            AdminGUI adminGUI = new AdminGUI((Admin) u);
                            break;
                        case "employee":
                            EmployeeGUI employeeGUI = new EmployeeGUI((Employee) u);
                            break;

                    }
                    dispose();
                }
            }
        });
    }

    public static void main(String[] args) {
        Helper.setLayout();
        new LoginGUI();
    }

}



