package com.kfoodsys.ui;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.kfoodsys.utils.Auth;
import com.kfoodsys.utils.MsgBox;
import com.kfoodsys.utils.XImage;
import com.kfoodsys.utils.XJdbc;
import java.awt.Color;
import javax.swing.UIManager;

/**
 *
 * @author phuho
 */
public class MainJFrame extends javax.swing.JFrame {

    /**
     * Creates new form MainJFrame
     */
    private int xMouse;
    private int yMouse;

    public MainJFrame() {
        initComponents();
        init();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblLogo = new javax.swing.JLabel();
        btnContinue = new javax.swing.JButton();
        btnContinue1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnCaNhan = new javax.swing.JButton();
        btnGioiThieu = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("KFood - Quản lý quán ăn nhanh chóng hiệu quả");
        setResizable(false);

        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/kfoodsys/icons/logo.png"))); // NOI18N

        btnContinue.setBackground(new java.awt.Color(51, 0, 51));
        btnContinue.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnContinue.setForeground(new java.awt.Color(255, 255, 255));
        btnContinue.setText("Tiếp tục với Hồ Hoàng Phú");
        btnContinue.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnContinue.setFocusable(false);
        btnContinue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnContinueActionPerformed(evt);
            }
        });

        btnContinue1.setBackground(new java.awt.Color(219, 104, 108));
        btnContinue1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnContinue1.setForeground(new java.awt.Color(255, 255, 255));
        btnContinue1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/kfoodsys/icons/icons8-exit-20.png"))); // NOI18N
        btnContinue1.setText("Đăng xuất");
        btnContinue1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnContinue1.setFocusable(false);
        btnContinue1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnContinue1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("K-FOOD");

        btnCaNhan.setBackground(new java.awt.Color(51, 0, 51));
        btnCaNhan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCaNhan.setForeground(new java.awt.Color(255, 255, 255));
        btnCaNhan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/kfoodsys/icons/icons8-info-squared-20.png"))); // NOI18N
        btnCaNhan.setText("Thông tin");
        btnCaNhan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCaNhan.setFocusable(false);
        btnCaNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCaNhanActionPerformed(evt);
            }
        });

        btnGioiThieu.setBackground(new java.awt.Color(219, 104, 108));
        btnGioiThieu.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnGioiThieu.setForeground(new java.awt.Color(255, 255, 255));
        btnGioiThieu.setText("Giới thiệu");
        btnGioiThieu.setBorder(null);
        btnGioiThieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGioiThieuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(60, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(45, 45, 45)
                        .addComponent(btnGioiThieu, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(btnCaNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnContinue1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(btnContinue, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(57, 57, 57))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnGioiThieu, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(btnContinue, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnCaNhan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnContinue1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(39, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnContinueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContinueActionPerformed
        moDialog();
    }//GEN-LAST:event_btnContinueActionPerformed

    private void btnContinue1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContinue1ActionPerformed
        logout();
    }//GEN-LAST:event_btnContinue1ActionPerformed

    private void btnCaNhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCaNhanActionPerformed
        caNhan();
    }//GEN-LAST:event_btnCaNhanActionPerformed

    private void btnGioiThieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGioiThieuActionPerformed
        new GioiThieuJDialog(this, true).setVisible(true);
    }//GEN-LAST:event_btnGioiThieuActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
        } catch (Exception e) {
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainJFrame().setVisible(true);
            }
        });
    }

    public void init() {
        setIconImage(XImage.getAppIcon());
        while (!XJdbc.checkConnectSQL().equals("OK")) {
            MsgBox.alert(this, XJdbc.checkConnectSQL());
            new SetupSystemJDialog(this, true).setVisible(true);
        }
        getContentPane().setBackground(new Color(219, 104, 108));
        new ChaoJDialog(this, true).setVisible(true);
        new DangNhapJDialog(this, true).setVisible(true);
        setLblAuth();
    }

    public void moDialog() {
        if (Auth.isManager()) {
            new QuanLyJDialog(this, true).setVisible(true);
        } else if (Auth.isNVBep()) {
            new NhanVienBepJDialog(this, true).setVisible(true);
        } else if (Auth.isNVOrder()) {
            new NhanVienOrderJDialog(this, true).setVisible(true);
        }
    }

    public void logout() {
        if (MsgBox.confirm(this, "Bạn muốn đăng xuất?")) {
            Auth.clear();
            new DangNhapJDialog(this, true).setVisible(true);
            setLblAuth();
        }
    }

    public void caNhan() {
        new CaNhanJDialog(this, true).setVisible(true);
    }

    public void setLblAuth() {
        if (Auth.isLogin()) {
            btnContinue.setText("Tiếp tục với " + Auth.user.getHoTen());
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCaNhan;
    private javax.swing.JButton btnContinue;
    private javax.swing.JButton btnContinue1;
    private javax.swing.JButton btnGioiThieu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblLogo;
    // End of variables declaration//GEN-END:variables
}
