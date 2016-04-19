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
import Modele.Menu;
import Modele.SingletonListCommande;
import Modele.TypeArticle;
import Modele.UniqueArticle;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Observable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author trentini
 */
public class FrameMenu extends javax.swing.JFrame implements WindowView {

    private FrameCommande windowCommande;
    
    private Article selectedMenu;
    
    /**
     * Creates new form FrameCommande
     */
    public FrameMenu(FrameCommande windowCommande) {
        
        this.windowCommande = windowCommande;
        this.windowCommande.setEnabled(false);    //the old window is set to disabled (it means we can reactivate the window the next time it is set)
        this.selectedMenu = this.windowCommande.getSelectedMenu();
        
        initComponents();

        this.TextCodeReservation.setText("Reservation #" + this.windowCommande.getThisCommande().getCodeReservation());
        
        updateListArticlesMenu();
    }
    
    private void updateListArticlesMenu() {
        for(TypeArticle type : TypeArticle.values()){
            LinkedHashMap<String, Object> tableMap = new LinkedHashMap<>();
            
            for(Article a : GlobalGraphicView.singletonGlobalGraphicView().getController().getMenuArticles(type, this.windowCommande.getSelectedMenu().getName()).values()){
                tableMap.put(a.getName(), a);
            }
            
            TableModel model = new SpecialJavaTableModel(tableMap, Article.class);
            
            switch(type){
                case ENTREE:    this.Entrees.setModel(model);    break;
                case BOISSON:   this.Boissons.setModel(model);   break;
                case PLAT:      this.Plats.setModel(model);      break;
                case DESSERT:   this.Desserts.setModel(model);   break;
                case MENU:      break;
            }
        }
    }
    
    private void updateCommandeMenu(){
        LinkedHashMap<String, Object> tableMap = new LinkedHashMap<>();

        for(Article a : ((Menu)this.selectedMenu).getList()){
            tableMap.put(a.getName(), a);
        }

        TableModel model = new SpecialJavaTableModel(tableMap, Article.class);
        
        this.SelectedArticlesTable.setModel(model);
    }
     
    @Override
    public void dispose() {
        GlobalGraphicView.singletonGlobalGraphicView().showView(windowCommande);
        //super.dispose();
    }
     
    @Override
    public void update(Observable o, Object o1) {
        this.updateListArticlesMenu();
        this.updateCommandeMenu();
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
        WindowTitle = new javax.swing.JLabel();
        ButtonCreateMenu = new javax.swing.JButton();
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
        TextCodeReservation = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Restaurant BDCO - Nouvelle Commande");

        SelectedArticlesTable.setModel(new DefaultTableModel());
        SelectedArticlesTable.setRowHeight(30);
        SelectedArticlesTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        SelectedArticlesTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(SelectedArticlesTable);
        SelectedArticlesTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        WindowTitle.setFont(new java.awt.Font("DejaVu Sans", 0, 18)); // NOI18N
        WindowTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        WindowTitle.setText("Nouvelle Menu");

        ButtonCreateMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Modele/Icons/report--plus.png"))); // NOI18N
        ButtonCreateMenu.setText("Créer Menu");
        ButtonCreateMenu.setToolTipText("");
        ButtonCreateMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonCreateMenuActionPerformed(evt);
            }
        });

        ButtonAddSelectedArticle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Modele/Icons/plus-button.png"))); // NOI18N
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

        TextCodeReservation.setFont(new java.awt.Font("DejaVu Sans", 0, 14)); // NOI18N
        TextCodeReservation.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TextCodeReservation.setText("# ????");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(ButtonAddSelectedArticle, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(TabsArticlesTypes, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 479, Short.MAX_VALUE)
                            .addComponent(ButtonCreateMenu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(WindowTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TextCodeReservation, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(WindowTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextCodeReservation, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TabsArticlesTypes, javax.swing.GroupLayout.DEFAULT_SIZE, 444, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ButtonAddSelectedArticle, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ButtonCreateMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ButtonCreateMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonCreateMenuActionPerformed
        if(this.SelectedArticlesTable.getRowCount() > 0){
            ArrayList<Article> list = new ArrayList<>();

            for(int row=0; row < this.SelectedArticlesTable.getRowCount(); row++){
                String value[] = new String[this.SelectedArticlesTable.getColumnCount()];

                for(int col=0; col < this.SelectedArticlesTable.getColumnCount(); col++){
                    value[col] = (String) this.SelectedArticlesTable.getValueAt(row, col);
                }
              
                list.add(GlobalGraphicView.singletonGlobalGraphicView().getController().getArticlesByName(1, TypeArticle.valueOf(value[1])).get(value[0]));
                
                Article a = GlobalGraphicView.singletonGlobalGraphicView().getController().getArticlesByName(this.windowCommande.getCodeCarte(), TypeArticle.MENU).get(value[0]);
                GlobalGraphicView.singletonGlobalGraphicView().getController().addArticleMenu(null, (Menu) this.selectedMenu);
            }

            GlobalGraphicView.singletonGlobalGraphicView().getController().verifyMenu((Menu) this.selectedMenu);
            
            //Commande commande = SingletonListCommande.singletonListCommande().getCommande(this.windowCommande.getWindowReservationDetails().getReservationCode(), );
            //GlobalGraphicView.singletonGlobalGraphicView().getController().addArticleCommande(selectedMenu, commande);
            this.dispose();
        }else{
            JOptionPane.showMessageDialog(this.getParent(), new NewCommandeException().getMessage());
        }
    }//GEN-LAST:event_ButtonCreateMenuActionPerformed

    private void ButtonAddSelectedArticleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonAddSelectedArticleActionPerformed
        int row = 0;
        JTable jtable = new JTable();
        TypeArticle ta = null;
        
        switch(TypeArticle.valueOf(this.TabsArticlesTypes.getTitleAt(this.TabsArticlesTypes.getSelectedIndex()))){
            case ENTREE:
                row = this.Entrees.getSelectedRow();
                jtable = this.Entrees;
                ta = TypeArticle.ENTREE;
                break;
            case PLAT:
                row = this.Plats.getSelectedRow();
                jtable = this.Plats;
                ta = TypeArticle.PLAT;
                break;
            case DESSERT:
                row = this.Desserts.getSelectedRow();
                jtable = this.Desserts;
                ta = TypeArticle.DESSERT;
                break;
            case BOISSON:
                row = this.Boissons.getSelectedRow();
                jtable = this.Boissons;
                ta = TypeArticle.BOISSON;
                break;
            case MENU:
                row = 0;
                ta = TypeArticle.MENU;
                break;
            default:
                break;
        }
        
        DefaultTableModel model = (DefaultTableModel) this.SelectedArticlesTable.getModel();
        if(row >= 0){
            model.addRow(new Object[]{ jtable.getValueAt(row, 0), jtable.getValueAt(row, 1), jtable.getValueAt(row, 2), jtable.getValueAt(row, 3) });
            
            Article article = GlobalGraphicView.singletonGlobalGraphicView().getController().getArticlesByName(this.windowCommande.getCodeCarte(), ta).get(jtable.getValueAt(row, 0));
            Menu menu = (Menu) GlobalGraphicView.singletonGlobalGraphicView().getController().getArticlesByName(this.windowCommande.getCodeCarte(), TypeArticle.MENU).get(selectedMenu);
            GlobalGraphicView.singletonGlobalGraphicView().getController().addArticleMenu(article, menu);
        }
    }//GEN-LAST:event_ButtonAddSelectedArticleActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Boissons;
    private javax.swing.JButton ButtonAddSelectedArticle;
    private javax.swing.JButton ButtonCreateMenu;
    private javax.swing.JTable Desserts;
    private javax.swing.JTable Entrees;
    private javax.swing.JTable Plats;
    private javax.swing.JTable SelectedArticlesTable;
    private javax.swing.JTabbedPane TabsArticlesTypes;
    private javax.swing.JLabel TextCodeReservation;
    private javax.swing.JLabel WindowTitle;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    // End of variables declaration//GEN-END:variables
}
