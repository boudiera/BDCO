
package GraphicPackage;

import InterfaceMVC.EnumView;
import Modele.Article;
import Modele.Commande;
import Modele.Menu;
import Modele.SingletonListCommande;
import Modele.TypeArticle;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Observable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

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
        initComponents();
        
        // Sets the Reservation Code of the selected Reservation and shows it in the View
        this.reservationCode = FrameReservationList.singletonFrameReservationList().getSelectedReservationCode();        
        this.TextCodeReservation.setText("Reservation #" + this.reservationCode);
        
        // Update tables and values
        updateCommandeTable(GlobalGraphicView.singletonGlobalGraphicView().getController().getCommande(this.reservationCode));
        updateArticleTable(new ArrayList<Article>());
        updateTotalValue();
    }
    
    @Override
    public void dispose() {
        // When closing, calls the Reservation List window and deletes itself from the list of observers of the list of Commandes
        GlobalGraphicView.singletonGlobalGraphicView().getController().setView(EnumView.ReservationList);
        SingletonListCommande.singletonListCommande().deleteObserver(this);
        super.dispose();
    }
    
    @Override
    public void update(Observable o, Object arg) {
        this.updateCommandeTable(GlobalGraphicView.singletonGlobalGraphicView().getController().getCommande(this.reservationCode));
        this.updateSelectedCommande();
        this.updateMenuArticleTable();
        this.updateTotalValue();
    }
    
    @Override
    public boolean isSingleton(){
        return false;
    }
    
    private void updateTotalValue(){
        float total=0;
        for(int i=0; i<CommandeTable.getRowCount(); i++){
           total += ((Commande) ((SpecialJavaTableModel) this.CommandeTable.getModel()).getObjectAt(i)).getPrice();
        }
        TextTotalBillValue.setText("€ " + Float.toString(total));
    }
    
    private void updateMenuArticleTable(){
        if (this.ArticleTable.getSelectedRow() >= 0) {
            LinkedHashMap<String, Object> tableMap = new LinkedHashMap<>();
            
            // If the article is of the type "Menu", it updates the table of articles of this selected Menu
            if(((Article)((SpecialJavaTableModel) this.ArticleTable.getModel()).getObjectAt(this.ArticleTable.getSelectedRow())).getType().equals(TypeArticle.MENU)){
                for (Article a : ((Menu) ((SpecialJavaTableModel) this.ArticleTable.getModel()).getObjectAt(this.ArticleTable.getSelectedRow())).getList()) {
                    tableMap.put(a.getName(), a);
                }
            }

            TableModel model = new SpecialJavaTableModel(tableMap, Article.class);

            this.MenuArticleTable.setModel(model);

            try {
                DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
                centerRenderer.setHorizontalAlignment(JLabel.CENTER);
                this.ArticleTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
            } catch (Exception e) {}
        }else{
            // If the selected article is not a Menu, then just cleans the table of articles of menu
            TableModel model = new SpecialJavaTableModel(new LinkedHashMap<String, Object>(), Article.class);
            this.MenuArticleTable.setModel(model);
        }
    }
    
    private void updateArticleTable(ArrayList<Article> listArticle){
        LinkedHashMap<String, Object> tableMap = new LinkedHashMap<>();
        for (Article a : listArticle) {
            tableMap.put(a.getName(), a);
        }
        
        TableModel model = new SpecialJavaTableModel(tableMap, Article.class);
        
        this.ArticleTable.setModel(model);
        
        try{
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            this.ArticleTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        }catch(Exception e){}
    }
    
    private void updateCommandeTable(ArrayList<Commande> listCommand){
        LinkedHashMap<String, Object> tableMap = new LinkedHashMap<>();
        for (Commande c : listCommand) {
            tableMap.put(c.getIdentifier(), c);
        }
        
        TableModel model = new SpecialJavaTableModel(tableMap, Commande.class);
        
        this.CommandeTable.setModel(model);
        
        try{
            this.CommandeTable.getColumnModel().getColumn(0).setPreferredWidth(180);
            this.CommandeTable.getColumnModel().getColumn(1).setPreferredWidth(10);
            
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            this.CommandeTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        }catch(Exception e){}
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
        PaneCommandeItems1 = new javax.swing.JScrollPane();
        MenuArticleTable = new javax.swing.JTable();
        TextTitleCommandeItems1 = new javax.swing.JLabel();

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

        ButtonDeleteSelectedCommand.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Modele/Icons/database--minus.png"))); // NOI18N
        ButtonDeleteSelectedCommand.setText("Effacer Commande");
        ButtonDeleteSelectedCommand.setEnabled(false);
        ButtonDeleteSelectedCommand.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonDeleteSelectedCommandActionPerformed(evt);
            }
        });

        ButtonNewCommand.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Modele/Icons/database--plus.png"))); // NOI18N
        ButtonNewCommand.setText("Nouvelle Commande");
        ButtonNewCommand.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonNewCommandActionPerformed(evt);
            }
        });

        TextCodeReservation.setFont(new java.awt.Font("DejaVu Sans", 0, 14)); // NOI18N
        TextCodeReservation.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TextCodeReservation.setText("# ????");

        ButonGetBill.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Modele/Icons/compile.png"))); // NOI18N
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
        ArticleTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                ArticleTableMousePressed(evt);
            }
        });
        ArticleTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ArticleTableKeyPressed(evt);
            }
        });
        PaneCommandeItems.setViewportView(ArticleTable);
        ArticleTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        TextTitleCommandeItems.setFont(new java.awt.Font("DejaVu Sans", 0, 14)); // NOI18N
        TextTitleCommandeItems.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TextTitleCommandeItems.setText("Articles de la Commande");

        TextTotalBill.setFont(new java.awt.Font("DejaVu Sans", 0, 18)); // NOI18N
        TextTotalBill.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        TextTotalBill.setText("Total:");

        TextTotalBillValue.setFont(new java.awt.Font("DejaVu Sans", 1, 18)); // NOI18N
        TextTotalBillValue.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TextTotalBillValue.setText("€ 0");
        TextTotalBillValue.setToolTipText("");
        TextTotalBillValue.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        WindowTitle1.setFont(new java.awt.Font("DejaVu Sans", 0, 18)); // NOI18N
        WindowTitle1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        WindowTitle1.setText("Détails Réservation");

        MenuArticleTable.setModel(new DefaultTableModel());
        MenuArticleTable.setRowHeight(60);
        MenuArticleTable.getTableHeader().setReorderingAllowed(false);
        PaneCommandeItems1.setViewportView(MenuArticleTable);
        MenuArticleTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        TextTitleCommandeItems1.setFont(new java.awt.Font("DejaVu Sans", 0, 14)); // NOI18N
        TextTitleCommandeItems1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TextTitleCommandeItems1.setText("Articles du Menu Selectioné");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(WindowTitle1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TextCodeReservation, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(PaneCommandeList, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(ButtonNewCommand, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(ButtonDeleteSelectedCommand, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(WindowTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TextTitleCommandeItems, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(TextTotalBill, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TextTotalBillValue, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ButonGetBill, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(PaneCommandeItems, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(PaneCommandeItems1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addComponent(TextTitleCommandeItems1, javax.swing.GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE))))))
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
                    .addComponent(PaneCommandeItems, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(TextTitleCommandeItems1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PaneCommandeItems1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addComponent(PaneCommandeList, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(ButtonNewCommand, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ButtonDeleteSelectedCommand, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(TextTotalBillValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(ButonGetBill, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(TextTotalBill, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ButtonDeleteSelectedCommandActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonDeleteSelectedCommandActionPerformed
        if(this.CommandeTable.getSelectedRow() >= 0) {
            Commande comm = (Commande) ((SpecialJavaTableModel) this.CommandeTable.getModel()).getObjectAt(this.CommandeTable.getSelectedRow());
            GlobalGraphicView.singletonGlobalGraphicView().getController().deleteCommande(comm);
        }
    }//GEN-LAST:event_ButtonDeleteSelectedCommandActionPerformed

    private void ButtonNewCommandActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonNewCommandActionPerformed
        WindowView frameCommande = new FrameCommande(this);
        GlobalGraphicView.singletonGlobalGraphicView().showView(frameCommande);
    }//GEN-LAST:event_ButtonNewCommandActionPerformed

    private void ButonGetBillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButonGetBillActionPerformed
        int answer = JOptionPane.showConfirmDialog(this.getParent(), "Vous voulez vraiment facturer?", "Confirmation...", JOptionPane.YES_NO_OPTION);
        
        if(answer == JOptionPane.YES_OPTION){
            String FinalBill = "- - - - - FACTURE - - - - -\n\n";
            String FinalPrice = this.TextTotalBillValue.getText();
            
            for (Object obj : ((SpecialJavaTableModel) this.CommandeTable.getModel()).getAll().values()) {
                Commande comm = ((Commande) obj);
                
                FinalBill += "• " + comm.getIdentifier() + ":\n";
                FinalBill += "• Sub-Total: € " + comm.getPrice() + "\n";
                for (String nomA : comm.getRegroupeArticle().keySet()) {
                    FinalBill += "   : " + comm.getRegroupeArticle().get(nomA) + " " + nomA + "\n";
                }
                FinalBill += "\n";
                
                GlobalGraphicView.singletonGlobalGraphicView().getController().endCommande(comm);
                
                GlobalGraphicView.singletonGlobalGraphicView().getController().deleteCommande(comm);
            }
            this.updateSelectedCommande();
            this.updateMenuArticleTable();
            
            FinalBill += "- - - - - - - - - - - - - - - - -\n";
            FinalBill += "T O T A L :  " + FinalPrice + "\n";
            FinalBill += "- - - - - - - - - - - - - - - - -\n";
            JOptionPane.showMessageDialog(this.getParent(), FinalBill);
        }
    }//GEN-LAST:event_ButonGetBillActionPerformed

    private void CommandeTableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CommandeTableMousePressed
        this.updateSelectedCommande();
    }//GEN-LAST:event_CommandeTableMousePressed

    private void CommandeTableKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CommandeTableKeyPressed
        this.updateSelectedCommande();
    }//GEN-LAST:event_CommandeTableKeyPressed

    private void ArticleTableKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ArticleTableKeyPressed
        this.updateMenuArticleTable();
    }//GEN-LAST:event_ArticleTableKeyPressed

    private void ArticleTableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ArticleTableMousePressed
        this.updateMenuArticleTable();
    }//GEN-LAST:event_ArticleTableMousePressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable ArticleTable;
    private javax.swing.JButton ButonGetBill;
    private javax.swing.JButton ButtonDeleteSelectedCommand;
    private javax.swing.JButton ButtonNewCommand;
    private javax.swing.JTable CommandeTable;
    private javax.swing.JTable MenuArticleTable;
    private javax.swing.JScrollPane PaneCommandeItems;
    private javax.swing.JScrollPane PaneCommandeItems1;
    private javax.swing.JScrollPane PaneCommandeList;
    private javax.swing.JLabel TextCodeReservation;
    private javax.swing.JLabel TextTitleCommandeItems;
    private javax.swing.JLabel TextTitleCommandeItems1;
    private javax.swing.JLabel TextTotalBill;
    private javax.swing.JLabel TextTotalBillValue;
    private javax.swing.JLabel WindowTitle;
    private javax.swing.JLabel WindowTitle1;
    // End of variables declaration//GEN-END:variables
}
