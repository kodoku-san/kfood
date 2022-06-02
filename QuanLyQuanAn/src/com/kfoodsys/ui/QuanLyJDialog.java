package com.kfoodsys.ui;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.kfoodsys.dao.ProceduDAO;
import com.kfoodsys.utils.Auth;
import com.kfoodsys.utils.MsgBox;
import com.kfoodsys.utils.XImage;
import com.raven.component.card.ModelCard;
import com.raven.component.chart.ModelChart;
import com.raven.event.EventMenuSelected;
import java.awt.Color;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import javax.swing.UIManager;

/**
 *
 * @author phuho
 */
public class QuanLyJDialog extends javax.swing.JDialog {

    private ProceduDAO proceduDAO = new ProceduDAO();
    Locale localeVN = new Locale("vi", "VN");
    NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
    public QuanLyJDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
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

        chart = new com.raven.component.chart.Chart();
        card1 = new com.raven.component.card.Card();
        card2 = new com.raven.component.card.Card();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        menu1 = new com.raven.component.menubar.Menu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Quản lý KFood - Quản lý quán ăn nhanh chóng hiệu quả");
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        card1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/icons8-money-64.png"))); // NOI18N

        card2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/icons8-users-64.png"))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 0, 51));
        jLabel1.setText("Thống kê");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 0, 51));
        jLabel2.setText("Dashboard");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(menu1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chart, javax.swing.GroupLayout.PREFERRED_SIZE, 791, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(card1, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(card2, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(card2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(card1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chart, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
            .addComponent(menu1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        chart.start();
    }//GEN-LAST:event_formWindowOpened

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
                QuanLyJDialog dialog = new QuanLyJDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.raven.component.card.Card card1;
    private com.raven.component.card.Card card2;
    private com.raven.component.chart.Chart chart;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private com.raven.component.menubar.Menu menu1;
    // End of variables declaration//GEN-END:variables

    public void init() {
//        setIconImage(XImage.getAppIcon());
        getContentPane().setBackground(new Color(255,255,255));                        
        menu1.getMenu().addEventMenuSelected(new EventMenuSelected() {
            @Override
            public void selected(int index) {
                switch(index) {
                    case 1: new QLMonAnJDialog(null, true).setVisible(true); break;
                    case 2: new QLNhanVienJDialog(null, true).setVisible(true); break;
                    case 3: new QLLichSuJDialog(null, true).setVisible(true); break;
                    case 4: new QLKhoJDialog(null, true).setVisible(true); break;
                    case 5: new QLBanAnJDialog(null, true).setVisible(true); break;
                    case 6: new ThongKeJDialog(null, true).setVisible(true); break;
                    case 7: new SetupMailJDialog(null, true).setVisible(true); break;
                    case 8: BackupDb(); break;
                }
            }
        });
        
        setDashboard();
    }
    
    public void BackupDb() {
        try {
            proceduDAO.BackupDatabase(Auth.cfs.getDatabase(), "F");
            MsgBox.alert(this, "Back up thành công!");
        } catch (Exception e) {
            MsgBox.error(this, e.getMessage());
        }
    }
    
    public void setDashboard() {
        chart.addLegend("Doanh thu", new Color(12, 84, 175), new Color(0, 108, 247));
        for(int i = 1; i <= 12; i++) {
            List<Object[]> list = proceduDAO.getDoanhThuThang(i);
            if((list.get(0)[0] == null)) {
                list.get(0)[0] =  0;
            }
            double doanhThu = Double.parseDouble(list.get(0)[0] + "");
            chart.addData(new ModelChart("Tháng " + i, new double[]{doanhThu}));
        }
        double tongDT;
        int tongNV;
        if(proceduDAO.getTongDT().get(0)[0] != null) {
            tongDT = (double) proceduDAO.getTongDT().get(0)[0];
        } else {
            tongDT = 0;
        }
        if(proceduDAO.getTongNV().get(0)[0] != null) {
            tongNV = (int) proceduDAO.getTongNV().get(0)[0];
        } else {
            tongNV = 0;
        }
        
        
        card1.setData(new ModelCard("Doanh thu", currencyVN.format(tongDT), "Tổng doanh thu"));
        card2.setData(new ModelCard("Nhân viên", tongNV + "", "Tổng nhân viên"));
    }
}
