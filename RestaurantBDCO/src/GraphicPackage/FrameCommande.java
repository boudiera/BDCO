/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphicPackage;

import InterfaceMVC.EnumView;
import InterfaceMVC.Exceptions.NewCommandeException;
import InterfaceMVC.Exceptions.ReservationException;
import Modele.Article;
import Modele.Commande;
import Modele.TypeArticle;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Observable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author trentini
 */
public class FrameCommande extends javax.swing.JFrame implements WindowView {

    private FrameReservationDetails windowReservationDetails;
    
    /**
     * Creates new form FrameCommande
     */
    public FrameCommande(FrameReservationDetails windowReservationDetails) {
        
        this.windowReservationDetails = windowReservationDetails;
        this.windowReservationDetails.setEnabled(false);    //the old window is set to disabled (it means we can reactivate the window the next time it is set)
        
        initComponents();
        
        DefaultTableModel model[] = new DefaultTableModel[6];
        for(int i=0; i<6; i++){
            model[i] = new DefaultTableModel(new String[] {"Nom", "Type", "Prix", "Spécialité"}, 0) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;   //all cells false
                }
            };
        }
        Entrees.setModel(model[0]);
        Plats.setModel(model[1]);
        Desserts.setModel(model[2]);
        Boissons.setModel(model[3]);
        Menus.setModel(model[4]);
        SelectedArticlesTable.setModel(model[5]);
        updateCarte(1, model);
    }
    
    private void updateCarte(int codeCarte, DefaultTableModel model[]) {
        int i=0;
        for(TypeArticle type : TypeArticle.values()){
            ArrayList<Article> list = GlobalGraphicView.singletonGlobalGraphicView().getController().getArticles(codeCarte, type);
            for(Article article : list){
                model[i].addRow(new Object[] {article.getName(), article.getType().toString(), Float.toString(article.getPrice()), article.getSpeciality().toString() });
            }
            i++;
        }
    }
     
    @Override
    public void dispose() {
        ((GlobalGraphicView) GlobalGraphicView.singletonGlobalGraphicView().getController().getView()).showView(windowReservationDetails);
        super.dispose();
    }
     
    @Override
    public void update(Observable o, Object o1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
     
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        SelectedArticlesTable = new javax.swing.JTable();
        ButtonDeleteSelectedArticle = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        ButtonFinishThisCommande = new javax.swing.JButton();
        ButtonAddSelectedArticle = new javax.swing.JButton();
        TabsArticlesTypes = new javax.swing.JTabbedPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        Entrees = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        Plats = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        Desserts = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        Boissons = new javax.swing.JTable();
        jScrollPane6 = new javax.swing.JScrollPane();
        Menus = new javax.swing.JTable();
        FieldClientName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Restaurant BDCO - Nouvelle Commande");

        SelectedArticlesTable.setModel(new DefaultTableModel());
        SelectedArticlesTable.setRowHeight(30);
        SelectedArticlesTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        SelectedArticlesTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(SelectedArticlesTable);
        SelectedArticlesTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        ButtonDeleteSelectedArticle.setText("Supprimer Article Selectioné");
        ButtonDeleteSelectedArticle.setToolTipText("");
        ButtonDeleteSelectedArticle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonDeleteSelectedArticleActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("DejaVu Sans", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Nouvelle Commande");

        ButtonFinishThisCommande.setText("Créer Nouvelle Commande");
        ButtonFinishThisCommande.setToolTipText("");
        ButtonFinishThisCommande.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonFinishThisCommandeActionPerformed(evt);
            }
        });

        ButtonAddSelectedArticle.setText("Ajouter Article Selectioné");
        ButtonAddSelectedArticle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonAddSelectedArticleActionPerformed(evt);
            }
        });

        Entrees.setModel(new DefaultTableModel());
        Entrees.setRowHeight(30);
        Entrees.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        Entrees.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(Entrees);
        Entrees.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        TabsArticlesTypes.addTab("ENTREE", jScrollPane2);

        Plats.setModel(new DefaultTableModel());
        Plats.setRowHeight(30);
        Plats.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        Plats.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(Plats);
        Plats.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        TabsArticlesTypes.addTab("PLAT", jScrollPane3);

        Desserts.setModel(new DefaultTableModel());
        Desserts.setRowHeight(30);
        Desserts.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        Desserts.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(Desserts);
        Desserts.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        TabsArticlesTypes.addTab("DESSERT", jScrollPane4);

        Boissons.setModel(new DefaultTableModel());
        Boissons.setRowHeight(30);
        Boissons.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        Boissons.getTableHeader().setReorderingAllowed(false);
        jScrollPane5.setViewportView(Boissons);
        Boissons.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        TabsArticlesTypes.addTab("BOISSON", jScrollPane5);

        Menus.setModel(new DefaultTableModel());
        Menus.setRowHeight(30);
        Menus.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        Menus.getTableHeader().setReorderingAllowed(false);
        jScrollPane6.setViewportView(Menus);
        Menus.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        TabsArticlesTypes.addTab("MENU", jScrollPane6);

        FieldClientName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        FieldClientName.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        FieldClientName.setText(Calendar.getInstance().getTime().toString());
        FieldClientName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FieldClientNameActionPerformed(evt);
            }
        });

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Identifiant de la Commande:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(FieldClientName, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(ButtonAddSelectedArticle, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(TabsArticlesTypes, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 479, Short.MAX_VALUE)
                            .addComponent(ButtonDeleteSelectedArticle, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ButtonFinishThisCommande, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 896, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TabsArticlesTypes, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ButtonAddSelectedArticle, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                    .addComponent(ButtonDeleteSelectedArticle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ButtonFinishThisCommande, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(FieldClientName, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ButtonFinishThisCommandeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonFinishThisCommandeActionPerformed
        if(this.SelectedArticlesTable.getRowCount() > 0){
            ArrayList<Article> list = new ArrayList<>();

            for(int row=0; row < this.SelectedArticlesTable.getRowCount(); row++){
                String value[] = new String[this.SelectedArticlesTable.getColumnCount()];

                for(int col=0; col < this.SelectedArticlesTable.getColumnCount(); col++){
                    value[col] = (String) this.SelectedArticlesTable.getValueAt(row, col);
                }

                list.add(new Article(value[0], TypeArticle.valueOf(value[1]), Float.valueOf(value[2]), value[3]));
            }

            Commande commande = new Commande(this.windowReservationDetails.getReservationCode(), this.FieldClientName.getText(), list, 0);
            Modele.SingletonListCommande.singletonListCommande().addCommand(commande.getCodeReservation(), commande);
            this.dispose();
        }else{
            JOptionPane.showMessageDialog(this.getParent(), new NewCommandeException().getMessage());
        }
    }//GEN-LAST:event_ButtonFinishThisCommandeActionPerformed

    private void ButtonAddSelectedArticleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonAddSelectedArticleActionPerformed
        int row = 0;
        JTable jtable = new JTable();
        
        switch(TypeArticle.valueOf(this.TabsArticlesTypes.getTitleAt(this.TabsArticlesTypes.getSelectedIndex()))){
            case ENTREE:
                row = this.Entrees.getSelectedRow();
                jtable = this.Entrees;
                break;
            case PLAT:
                row = this.Plats.getSelectedRow();
                jtable = this.Plats;
                break;
            case DESSERT:
                row = this.Desserts.getSelectedRow();
                jtable = this.Desserts;
                break;
            case BOISSON:
                row = this.Boissons.getSelectedRow();
                jtable = this.Boissons;
                break;
            case MENU:
                row = this.Menus.getSelectedRow();
                jtable = this.Menus;
                break;
            default:
                row = 0;
                break;
        }
        
        DefaultTableModel model = (DefaultTableModel) this.SelectedArticlesTable.getModel();
        if(row >= 0){
            model.addRow(new Object[]{ jtable.getValueAt(row, 0), jtable.getValueAt(row, 1), jtable.getValueAt(row, 2), jtable.getValueAt(row, 3) });
        }
    }//GEN-LAST:event_ButtonAddSelectedArticleActionPerformed

    private void ButtonDeleteSelectedArticleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonDeleteSelectedArticleActionPerformed
        DefaultTableModel model = (DefaultTableModel) this.SelectedArticlesTable.getModel();
        if(this.SelectedArticlesTable.getSelectedRow() >= 0){
            model.removeRow(this.SelectedArticlesTable.getSelectedRow());
        }
    }//GEN-LAST:event_ButtonDeleteSelectedArticleActionPerformed

    private void FieldClientNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FieldClientNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FieldClientNameActionPerformed

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
            java.util.logging.Logger.getLogger(FrameCommande.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameCommande.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameCommande.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameCommande.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new FrameCommande().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Boissons;
    private javax.swing.JButton ButtonAddSelectedArticle;
    private javax.swing.JButton ButtonDeleteSelectedArticle;
    private javax.swing.JButton ButtonFinishThisCommande;
    private javax.swing.JTable Desserts;
    private javax.swing.JTable Entrees;
    private javax.swing.JTextField FieldClientName;
    private javax.swing.JTable Menus;
    private javax.swing.JTable Plats;
    private javax.swing.JTable SelectedArticlesTable;
    private javax.swing.JTabbedPane TabsArticlesTypes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    // End of variables declaration//GEN-END:variables
}
