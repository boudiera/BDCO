/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphicPackage;

import InterfaceMVC.EnumView;
import FactoriesLayer.ConcreteRequeteFactory;
import Modele.Article;
import Modele.Commande;
import Modele.Reservation;
import Modele.SingletonListCommande;
import Modele.UniqueArticle;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author trentini
 */
public class FrameReservationDetails extends javax.swing.JFrame implements WindowView {

    private final int reservationCode;

    public int getReservationCode() {
        return reservationCode;
    }
    
    /**
     * Creates new form FrameReservationDetails
     */
    public FrameReservationDetails() {
        this.reservationCode = FrameReservationList.singletonFrameReservationList().getSelectedReservationCode();
        
        initComponents();
        
        /*
        ArrayList<Article> la = new ArrayList<>();
        la.add(UniqueArticle.createDrink("a", (float) 20.5, "mexicain"));
        la.add(UniqueArticle.createDrink("a", (float) 3.3, "mex"));
        
        SingletonListCommande.singletonListCommande().addCommand(1, new Commande(15, "Arnaud Zizi", la, 0));
        SingletonListCommande.singletonListCommande().addCommand(1, new Commande(15, "abcd", new ArrayList<Article>(), 01));
        SingletonListCommande.singletonListCommande().addCommand(2, new Commande(15, "hhh", new ArrayList<Article>(), 50));
        SingletonListCommande.singletonListCommande().addCommand(5, new Commande(15, "momomo", la, 500));
        SingletonListCommande.singletonListCommande().addCommand(5, new Commande(15, "yuiyui", la, 88));
        SingletonListCommande.singletonListCommande().addCommand(3, new Commande(15, "Arnaud Zizi", la, 0));
        SingletonListCommande.singletonListCommande().addCommand(3, new Commande(15, "abcd", new ArrayList<Article>(), 01));
        SingletonListCommande.singletonListCommande().addCommand(4, new Commande(15, "hhh", new ArrayList<Article>(), 50));
        SingletonListCommande.singletonListCommande().addCommand(4, new Commande(15, "momomo", la, 500));
        SingletonListCommande.singletonListCommande().addCommand(4, new Commande(15, "yuiyui", la, 88));
        //*/
        
        this.TextCodeReservation.setText("Reservation #" + this.reservationCode);
        
        updateCommandeTable(GlobalGraphicView.singletonGlobalGraphicView().getController().getCommande(this.reservationCode));
        updateArticleTable(new ArrayList<Article>());
    }
    
    @Override
    public void dispose() {
        GlobalGraphicView.singletonGlobalGraphicView().getController().setView(EnumView.ReservationList);
        SingletonListCommande.singletonListCommande().deleteObserver(this);
        super.dispose();
    }
    
    @Override
    public void update(Observable o, Object arg) {
        updateCommandeTable(GlobalGraphicView.singletonGlobalGraphicView().getController().getCommande(this.reservationCode));
        this.updateSelectedCommande();
    }
    
    @Override
    public boolean isSingleton(){
        return false;
    }
    
    private void updateArticleTable(ArrayList<Article> listArticle){
        DefaultTableModel model = new DefaultTableModel(new String[]{"Article", "Prix"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;   //all cells false
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 1) {
                    return Float.class;
                } else {
                    return Object.class;
                }
            }
        };

        for (Article a : listArticle) {
            model.addRow(new Object[]{a.getName(), a.getPrice()});
        }

        this.ArticleTable.setModel(model);
        
        try {
            this.ArticleTable.getColumnModel().getColumn(model.findColumn("Article")).setPreferredWidth(150);
            this.ArticleTable.getColumnModel().getColumn(model.findColumn("Prix")).setPreferredWidth(10);

            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            this.ArticleTable.getColumnModel().getColumn(model.findColumn("Prix")).setCellRenderer(centerRenderer);
        } catch (Exception e) {}
    }
    
    private void updateCommandeTable(ArrayList<Commande> listCommand){
        DefaultTableModel model = new DefaultTableModel(new String[]{ "Identifier", "Sub-Total" }, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;   //all cells false
            }
            
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if(columnIndex == 1){
                    return Float.class;
                }else{
                    return Object.class;
                }
            }
        };
        
        for (Commande c : listCommand) {
            model.addRow(new Object[]{ c.getIdentifier(), c.getPrice() });
        }
        
        this.CommandeTable.setModel(model);
        sortCommandeTable(model);
        
        try{
            this.CommandeTable.getColumnModel().getColumn(model.findColumn("Identifier")).setPreferredWidth(180);
            this.CommandeTable.getColumnModel().getColumn(model.findColumn("Sub-Total")).setPreferredWidth(10);
            
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            this.CommandeTable.getColumnModel().getColumn(model.findColumn("Sub-Total")).setCellRenderer(centerRenderer);
        }catch(Exception e){}
    }
    
    private void sortCommandeTable(DefaultTableModel model){
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(this.CommandeTable.getModel());
        this.CommandeTable.setRowSorter(sorter);
        List<RowSorter.SortKey> sortKeys = new ArrayList<>();
        
        sortKeys.add(new RowSorter.SortKey(model.findColumn("Identifier"), SortOrder.ASCENDING));
        sorter.setSortKeys(sortKeys);
        sorter.sort();
    }
    
    public void updateSelectedCommande(){
        if (this.CommandeTable.getSelectedRow() == -1) {
            this.ButtonDeleteSelectedCommand.setEnabled(false);
            this.updateArticleTable(new ArrayList<Article>());
        } else {
            String identifier = (String) this.CommandeTable.getValueAt(this.CommandeTable.getSelectedRow(), 0);
            
            this.updateArticleTable(SingletonListCommande.singletonListCommande().getListArticlesByReservationCodeAndCommandeIdentifier(this.reservationCode, identifier));
            this.ButtonDeleteSelectedCommand.setEnabled(true);
        }
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
        ArticleTable = new javax.swing.JTable();
        TextTitleCommandeItems = new javax.swing.JLabel();
        TextTotalBill = new javax.swing.JLabel();
        TextTotalBillValue = new javax.swing.JLabel();
        WindowTitle1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Restaurant BDCO - Détails Réservation");

        CommandeTable.setModel(new DefaultTableModel());
        CommandeTable.setRowHeight(60);
        CommandeTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        CommandeTable.getTableHeader().setReorderingAllowed(false);
        CommandeTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                CommandeTableMousePressed(evt);
            }
        });
        CommandeTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CommandeTableKeyPressed(evt);
            }
        });
        PaneCommandeList.setViewportView(CommandeTable);
        CommandeTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        WindowTitle.setFont(new java.awt.Font("DejaVu Sans", 0, 18)); // NOI18N
        WindowTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        WindowTitle.setText("Liste de Commandes");

        ButtonDeleteSelectedCommand.setText("Effacer Commande");
        ButtonDeleteSelectedCommand.setEnabled(false);
        ButtonDeleteSelectedCommand.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonDeleteSelectedCommandActionPerformed(evt);
            }
        });

        ButtonNewCommand.setText("Nouvelle Commande");
        ButtonNewCommand.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonNewCommandActionPerformed(evt);
            }
        });

        TextCodeReservation.setFont(new java.awt.Font("DejaVu Sans", 0, 14)); // NOI18N
        TextCodeReservation.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TextCodeReservation.setText("# ????");

        ButonGetBill.setText("Produire la Facture");
        ButonGetBill.setToolTipText("");
        ButonGetBill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButonGetBillActionPerformed(evt);
            }
        });

        ArticleTable.setModel(new DefaultTableModel());
        ArticleTable.setRowHeight(60);
        ArticleTable.getTableHeader().setReorderingAllowed(false);
        PaneCommandeItems.setViewportView(ArticleTable);
        ArticleTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        TextTitleCommandeItems.setFont(new java.awt.Font("DejaVu Sans", 0, 14)); // NOI18N
        TextTitleCommandeItems.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TextTitleCommandeItems.setText("Détail de la Commande");

        TextTotalBill.setFont(new java.awt.Font("DejaVu Sans", 0, 18)); // NOI18N
        TextTotalBill.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        TextTotalBill.setText("Total:");

        TextTotalBillValue.setFont(new java.awt.Font("DejaVu Sans", 1, 18)); // NOI18N
        TextTotalBillValue.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TextTotalBillValue.setText("€ 1 250,00");
        TextTotalBillValue.setToolTipText("");
        TextTotalBillValue.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        WindowTitle1.setFont(new java.awt.Font("DejaVu Sans", 0, 18)); // NOI18N
        WindowTitle1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        WindowTitle1.setText("Détails Réservation");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ButtonNewCommand, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(ButtonDeleteSelectedCommand, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(ButonGetBill, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(TextTotalBill, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TextTotalBillValue, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(WindowTitle, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(PaneCommandeList, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(PaneCommandeItems, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(TextTitleCommandeItems, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(WindowTitle1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TextCodeReservation, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(WindowTitle1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextCodeReservation, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(WindowTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextTitleCommandeItems, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(PaneCommandeList, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TextTotalBill, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TextTotalBillValue, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(PaneCommandeItems, javax.swing.GroupLayout.DEFAULT_SIZE, 342, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(ButtonNewCommand, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ButtonDeleteSelectedCommand, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(ButonGetBill, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ButtonDeleteSelectedCommandActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonDeleteSelectedCommandActionPerformed
        if(this.CommandeTable.getSelectedRow() >= 0) {
            String identifier = (String) this.CommandeTable.getValueAt(this.CommandeTable.getSelectedRow(), 0);
            
            SingletonListCommande.singletonListCommande().removeCommand(this.reservationCode, identifier);
        }
        // TO-DO: voir si ça marche bien...
    }//GEN-LAST:event_ButtonDeleteSelectedCommandActionPerformed

    private void ButtonNewCommandActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonNewCommandActionPerformed
        WindowView frameCommande = new FrameCommande(this);
        GlobalGraphicView.singletonGlobalGraphicView().showView(frameCommande);
    }//GEN-LAST:event_ButtonNewCommandActionPerformed

    private void ButonGetBillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButonGetBillActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ButonGetBillActionPerformed

    private void CommandeTableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CommandeTableMousePressed
        this.updateSelectedCommande();
    }//GEN-LAST:event_CommandeTableMousePressed

    private void CommandeTableKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CommandeTableKeyPressed
        this.updateSelectedCommande();
    }//GEN-LAST:event_CommandeTableKeyPressed

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
    private javax.swing.JTable ArticleTable;
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
    private javax.swing.JLabel WindowTitle1;
    // End of variables declaration//GEN-END:variables
}
