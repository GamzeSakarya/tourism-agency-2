package View;

import Helper.Helper;
import Model.Reservation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class ReservationAddGUI extends JFrame {
    private JPanel wrapper;
    private JPanel panel1;
    private JTextField fld_res_room_id;
    private JTextField fld_res_user;
    private JTextField fld_res_check_in;
    private JTextField fld_res_note;
    private JTextField fld_res_check_out;
    private JTextField fld_res_email;
    private JTextField fld_res_adult_amount;
    private JTextField fld_res_child_num;
    private JTextField fld_res_total_price;
    private JButton btn_res_save;

    public ReservationAddGUI() {
        add(wrapper);
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Tourism Agency");
        setVisible(true);

        btn_res_save.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_res_room_id) || Helper.isFieldEmpty(fld_res_user) || Helper.isFieldEmpty(fld_res_check_in) || Helper.isFieldEmpty(fld_res_check_out) || Helper.isFieldEmpty(fld_res_adult_amount) || Helper.isFieldEmpty(fld_res_child_num) || Helper.isFieldEmpty(fld_res_total_price)) {
                Helper.showMsg("Alanlar bos!");
            } else {
                //  use Reservation add  ->  add(Reservation reservation) {
                Reservation reservation = new Reservation(
                        Integer.parseInt(fld_res_room_id.getText()),
                        fld_res_user.getText(),
                        fld_res_email.getText(),
                        fld_res_note.getText(),
                        Integer.parseInt(fld_res_room_id.getText()),
                        fld_res_check_in.getText(),
                        fld_res_check_out.getText(),
                        Integer.parseInt(fld_res_adult_amount.getText()),
                        Integer.parseInt(fld_res_child_num.getText()),
                        Integer.parseInt(fld_res_total_price.getText())
                );
                Reservation.add(reservation);
                Helper.showMsg("Kayit basarili!");


            }
        });
    }


    public static void main(String[] args) {
        new ReservationAddGUI();
    }


    /*
    Tam metinler
    id
            client_name
    client_email
            client_note
    room_id
            check_in
    check_out
            adult_numb
    child_numb
            total_price

     */


}
