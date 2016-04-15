/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphicPackage;

import FactoriesLayer.ConnectionInfo;
import FactoriesLayer.TheConnection;
import Modele.Article;
import Modele.Commande;
import Modele.Factory;
import Modele.Reservation;
import Modele.SingletonListCommande;
import Modele.Table;
import Modele.TypeArticle;
import Modele.UniqueArticle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author trentini
 */
public class FrameReservationDetails extends javax.swing.JFrame {

    ArrayList<Commande> listCommand;
    
    /**
     * Creates new form FrameReservationDetails
     */
    public FrameReservationDetails() {
        initComponents();
        
        Reservation selectedReservation = FrameReservationList.singletonFrameReservationList().getSelectedReservation();
        
        this.TextCodeReservation.setText("Code: " + selectedReservation.getCodeReservation());
        
        listCommand = SingletonListCommande.singletonListCommande().getListCommandByReservationCode(selectedReservation.getCodeReservation());
        
        //
        ArrayList<Article> la = new ArrayList<>();
        la.add(UniqueArticle.createDrink("a", (float) 20.5, "mexicain"));
        
        this.listCommand.add(new Commande(1234, "Kiki", la));
        
        la.add(UniqueArticle.createDrink("a", (float) 3.3, "mex"));
        this.listCommand.add(new Commande(1234, "Jojo", la));
        
        la.add(UniqueArticle.createDrink("a", (float) 100.1, "mex"));
        this.listCommand.add(new Commande(1234, "Jojo", la));
        
        this.listCommand.add(new Commande(15, "Arnaud Zizi", new ArrayList<Article>()));
        //
        
        updateCommandeTable();
    }
    
    @Override
    public void dispose() {
        GlobalGraphicView.singletonGlobalGraphicView().setWindow(EnumWindow.ReservationList);
        GlobalGraphicView.singletonGlobalGraphicView().showView(true);

        super.dispose();
    }
    
    private void updateCommandeTable(){
        
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;   //all cells false
            }
        };
        
        model.addColumn("Identifier");
        model.addColumn("SubTotal");
        
        this.CommandeTable.setModel(model);
        for (Commande c : this.listCommand) {
            model.addRow(new Object[]{ c.getIdentifier(), new Float(c.getPrice()) });
        }
        
        sertCommandeTable(model);
    }
    
    private void sertCommandeTable(DefaultTableModel model){
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(this.CommandeTable.getModel());
        this.CommandeTable.setRowSorter(sorter);
        List<RowSorter.SortKey> sortKeys = new ArrayList<>();
        
        sortKeys.add(new RowSorter.SortKey(model.findColumn("Identifier"), SortOrder.ASCENDING));
        sorter.setSortKeys(sortKeys);
        sorter.sort();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PaneCommandeList = new javax.swing.JScrollPane();
        CommandeTable = new javax.swing.JTable();
        WindowTitle = new javax.swing.JLabel();
        ButtonDeleteSelectedCommand = new javax.swing.JButton();
        ButtonNewCommand = new javax.swing.JButton();
        TextCodeReservation = new javax.swing.JLabel();
        ButonGetBill = new javax.swing.JButton();
        PaneCommandeItems = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        TextTitleCommandeItems = new javax.swing.JLabel();
        TextTotalBill = new javax.swing.JLabel();
        TextTotalBillValue = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        CommandeTable.setModel(new DefaultTableModel());
        CommandeTable.setRowHeight(60);
        PaneCommandeList.setViewportView(CommandeTable);

        WindowTitle.setFont(new java.awt.Font("DejaVu Sans", 0, 18)); // NOI18N
        WindowTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        WindowTitle.setText("Reservation Details");

        ButtonDeleteSelectedCommand.setText("Delete Selected Command");
        ButtonDeleteSelectedCommand.setEnabled(false);
        ButtonDeleteSelectedCommand.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonDeleteSelectedCommandActionPerformed(evt);
            }
        });

        ButtonNewCommand.setText("New Command");
        ButtonNewCommand.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonNewCommandActionPerformed(evt);
            }
        });

        TextCodeReservation.setFont(new java.awt.Font("DejaVu Sans", 0, 18)); // NOI18N
        TextCodeReservation.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TextCodeReservation.setText("# ????");

        ButonGetBill.setText("Get Bill");
        ButonGetBill.setToolTipText("");
        ButonGetBill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButonGetBillActionPerformed(evt);
            }
        });

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Spaghetti",  new Float(123.6)},
                {"Boisson",  new Float(1250.99)},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Item", "Price"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Float.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable2.setRowHeight(60);
        PaneCommandeItems.setViewportView(jTable2);
        if (jTable2.getColumnModel().getColumnCount() > 0) {
            jTable2.getColumnModel().getColumn(0).setPreferredWidth(100);
            jTable2.getColumnModel().getColumn(1).setPreferredWidth(10);
        }

        TextTitleCommandeItems.setFont(new java.awt.Font("DejaVu Sans", 0, 14)); // NOI18N
        TextTitleCommandeItems.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TextTitleCommandeItems.setText("Items for the Selected Commande");

        TextTotalBill.setFont(new java.awt.Font("DejaVu Sans", 0, 14)); // NOI18N
        TextTotalBill.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        TextTotalBill.setText("Total:");

        TextTotalBillValue.setFont(new java.awt.Font("DejaVu Sans", 0, 14)); // NOI18N
        TextTotalBillValue.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TextTotalBillValue.setText("€ 1 250,00");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(WindowTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(TextCodeReservation, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ButtonNewCommand, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(ButtonDeleteSelectedCommand, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(ButonGetBill, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                        .addGap(15, 15, 15))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(PaneCommandeList, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(TextTotalBill, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(TextTotalBillValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(PaneCommandeItems, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(TextTitleCommandeItems, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TextCodeReservation, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(WindowTitle, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(TextTitleCommandeItems, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PaneCommandeItems, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(PaneCommandeList, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TextTotalBill, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TextTotalBillValue, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ButtonNewCommand, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ButtonDeleteSelectedCommand, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ButonGetBill, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ButtonDeleteSelectedCommandActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonDeleteSelectedCommandActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ButtonDeleteSelectedCommandActionPerformed

    private void ButtonNewCommandActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonNewCommandActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ButtonNewCommandActionPerformed

    private void ButonGetBillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButonGetBillActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ButonGetBillActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrameReservationDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameReservationDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameReservationDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameReservationDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameReservationDetails().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButonGetBill;
    private javax.swing.JButton ButtonDeleteSelectedCommand;
    private javax.swing.JButton ButtonNewCommand;
    private javax.swing.JTable CommandeTable;
    private javax.swing.JScrollPane PaneCommandeItems;
    private javax.swing.JScrollPane PaneCommandeList;
    private javax.swing.JLabel TextCodeReservation;
    private javax.swing.JLabel TextTitleCommandeItems;
    private javax.swing.JLabel TextTotalBill;
    private javax.swing.JLabel TextTotalBillValue;
    private javax.swing.JLabel WindowTitle;
    private javax.swing.JTable jTable2;
    // End of variables declaration//GEN-END:variables
}
