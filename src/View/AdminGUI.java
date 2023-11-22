package View;

import Model.Admin;
import Helper.Helper;
import Helper.Config;
import Model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminGUI extends JFrame {
    private JPanel wrapper;
    private JTabbedPane tab_admin;
    private JLabel lbl_welcome;
    private JPanel pnl_top;
    private JButton btn_logout;
    private JPanel pnl_user_list;
    private JScrollPane scrl_user_list;
    private JTable tbl_user_list;
    private JPanel pnl_user_form;
    private JTextField fld_user_name;
    private JTextField fld_user_uname;
    private JPasswordField fld_user_pass;
    private JComboBox cmb_user_type;
    private JButton btn_user_add;
    private JTextField fld_name_update;
    private JTextField fld_uname_update;
    private JPasswordField fld_pass_update;
    private JComboBox cmb_user_update;
    private JButton btn_user_update;
    private JTextField fld_id_update;
    private DefaultTableModel mdl_user_list;
    private Object[] row_user_list;
    private JPopupMenu userMenu;

    public final Admin admin;

    public AdminGUI(Admin admin) {
        this.admin = admin;
        add(wrapper);
        setSize(1000, 500);
        int x = Helper.screenCenter("x", getSize());
        int y = Helper.screenCenter("y", getSize());
        setLocation(x, y);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);

        lbl_welcome.setText("Hoşgeldin : " + admin.getName());

        userMenu = new JPopupMenu();
        JMenuItem deleteUser = new JMenuItem("Sil");
        userMenu.add(deleteUser);

        deleteUser.addActionListener(e -> {
            int selectedRow = tbl_user_list.getSelectedRow();
            if (selectedRow >= 0) {
                // Seçilen bir satır var, işlemi devam ettirebilirsiniz.
                int select_id = Integer.parseInt(tbl_user_list.getValueAt(selectedRow, 0).toString());
                if (User.delete(select_id)) {
                    Helper.showMsg("done");
                    loadUserModel();
                } else {
                    Helper.showMsg("error");
                }
            } else {
                // Seçilen bir satır yok, kullanıcıya bir hata mesajı gösterin veya işlemi iptal edin.
                Helper.showMsg("Lütfen bir kullanıcı seçin.");
            }

        });
        mdl_user_list = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0 || column == 1 || column == 2 || column == 3 || column == 4)
                    return false;
                return super.isCellEditable(row, column);
            }
        };

        Object[] col_user_list = new Object[]{"ID", "Ad Soyad", "Kullanıcı Adı", "Şifre", "Üyelik Tipi"};
        mdl_user_list.setColumnIdentifiers(col_user_list);
        tbl_user_list.setModel(mdl_user_list);
        row_user_list = new Object[col_user_list.length];
        loadUserModel();
        tbl_user_list.setModel(mdl_user_list);
        tbl_user_list.setComponentPopupMenu(userMenu);
        tbl_user_list.getTableHeader().setReorderingAllowed(false);
        tbl_user_list.getSelectionModel().addListSelectionListener(e -> {
            try {
                String select_user_id = tbl_user_list.getValueAt(tbl_user_list.getSelectedRow() , 0).toString();
                fld_id_update.setText(select_user_id);
                String select_user_name= tbl_user_list.getValueAt(tbl_user_list.getSelectedRow() , 1).toString();
                fld_name_update.setText(select_user_name);
                String select_user_uname= tbl_user_list.getValueAt(tbl_user_list.getSelectedRow() , 2).toString();
                fld_uname_update.setText(select_user_uname);
                String select_user_pass= tbl_user_list.getValueAt(tbl_user_list.getSelectedRow() , 3).toString();
                fld_pass_update.setText(select_user_pass);
            } catch (Exception ex) {
            }
        });
        btn_logout.addActionListener(e -> {
            dispose();
            new LoginGUI();
        });
        btn_user_add.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_user_name) || Helper.isFieldEmpty(fld_user_uname) || Helper.isFieldEmpty(fld_user_pass)) {
                Helper.showMsg("fill");
            } else {
                String name = fld_user_name.getText();
                String uname = fld_user_uname.getText();
                String pass = String.valueOf(fld_user_pass.getPassword());
                String type = cmb_user_type.getSelectedItem().toString();
                if (User.add(name, uname, pass, type)) {
                    Helper.showMsg("done");
                    loadUserModel();
                    fld_user_name.setText(null);
                    fld_user_uname.setText(null);
                    fld_user_pass.setText(null);
                }
            }
        });
        btn_user_update.addActionListener(e -> {
            if (Helper.confirm("Emin misiniz?")) {
                String idText = fld_id_update.getText();
                String name = fld_name_update.getText();
                String uname = fld_uname_update.getText();
                String pass = fld_pass_update.getText();
                String type = cmb_user_update.getSelectedItem().toString();

                if (!idText.isEmpty()) {
                    try {
                        int id = Integer.parseInt(idText);
                        if (User.update(id, name, uname, pass, type)) {
                            Helper.showMsg("Başarılı");
                            loadUserModel();
                        } else {
                            Helper.showMsg("Hata");
                        }
                    } catch (NumberFormatException ex) {
                        Helper.showMsg("Geçersiz ID formatı");
                        ex.printStackTrace(); // Hatanın tam ayrıntılarını görmek için
                    }
                } else {
                    Helper.showMsg("ID boş olamaz");
                }
            }
        });

    }
    public void loadUserModel () {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_user_list.getModel();
        clearModel.setRowCount(0);
        for (User obj : User.getList() ){

            int i = 0;
            row_user_list[i++] = obj.getId();
            row_user_list[i++] = obj.getName();
            row_user_list[i++] = obj.getUname();
            row_user_list[i++] = obj.getPass();
            row_user_list[i++] = obj.getType();
            mdl_user_list.addRow(row_user_list);

        }
    }

    public static void main(String[] args) {
        Helper.setLayout();
        AdminGUI adminGUI = new AdminGUI(new Admin(1, "name", "uname", "pass", "type"));
    }
}
