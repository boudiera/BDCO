package GraphicPackage;

import InterfaceMVC.EnumView;
import FactoriesLayer.*;
import Modele.*;
import java.util.*;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author trentini
 */
public class FrameReservationList extends javax.swing.JFrame implements WindowView{

    final private static FrameReservationList singletonWindow = new FrameReservationList();
    
    private int selectedReservationCode;

    public int getSelectedReservationCode() {
        return selectedReservationCode;
    }
    
    /**
     * Creates new form FrameReservationList
     */
    private FrameReservationList() {
        initComponents();
        updateReservationTable(GlobalGraphicView.singletonGlobalGraphicView().getController().getReservationList());

        /*   TEST CODE
        ArrayList<Table> CodesTables = new ArrayList<>();
        CodesTables.add(new Table(2, "window", 4, 3, 2));
        CodesTables.add(new Table(5, "window", 4, 3, 2));
        CodesTables.add(new Table(6, "window", 4, 3, 2));
        CodesTables.add(new Table(15, "window", 4, 3, 2));

        this.ListReservs.add(new Reservation(1, CodesTables, 12, "BB", "123456", new ReservationDate(2016, 11, 20, 15, 30), Service.MIDI));
        this.ListReservs.add(new Reservation(5, new ArrayList<Table>(), 5, "AA", "123456", new ReservationDate(2016, 10, 5, 15, 30), Service.MIDI));
        this.ListReservs.add(new Reservation(6, new ArrayList<Table>(), 500, "CC", "123456", new ReservationDate(2016, 11, 2, 15, 30), Service.MIDI));
        //END OF TEST CODE
        */
    }
    
    public static FrameReservationList singletonFrameReservationList(){
        return FrameReservationList.singletonWindow;
    }
    
    @Override
    public void update(Observable o, Object arg) {
        updateReservationTable(GlobalGraphicView.singletonGlobalGraphicView().getController().getReservationList());
    }
    
    @Override
    public boolean isSingleton(){
        return true;
    }
    
    private void updateReservationTable(ArrayList<Reservation> listReservations){
        DefaultTableModel model = new DefaultTableModel(new String[]{ "Code", "Date", "Nom", "Téléphone", "No Personnes", "Table(s)" }, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;   //all cells false
            }
        };
        
        for (Reservation r : listReservations) {
           String stringListTables = "[ ";
           Iterator<Table> i = r.getCodeTable().iterator();
           if(!i.hasNext())
               stringListTables += "none";
           while(i.hasNext()){
               stringListTables += i.next().getCodeTable();
               if(i.hasNext())
                   stringListTables += ", ";
           }
           stringListTables += " ]";
            
            model.addRow(new Object[]{ r.getCodeReservation(), r.getDate().writeDateSortable(),
                r.getClientName(), r.getPhone(), r.getNbPersonnes(), stringListTables});
        }
        
        this.ReservationsTable.setModel(model);

        setReservationTableSortable(model);

        try{
            //this.ReservationsTable.getColumnModel().getColumn(model.findColumn("Code")).setMinWidth(0);
            this.ReservationsTable.getColumnModel().getColumn(model.findColumn("Code")).setMaxWidth(60);
            //this.ReservationsTable.getColumnModel().getColumn(model.findColumn("Code")).setWidth(0);
        }catch(Exception e){}
    }
    
    private void setReservationTableSortable(DefaultTableModel model){
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(this.ReservationsTable.getModel());
        this.ReservationsTable.setRowSorter(sorter);
        List<RowSorter.SortKey> sortKeys = new ArrayList<>();
        
        try{
            sortKeys.add(new RowSorter.SortKey(model.findColumn("Nom"), SortOrder.ASCENDING));
            sorter.setSortKeys(sortKeys);

            int unsortableColumn;

            unsortableColumn = model.findColumn("No Personnes");
            if(unsortableColumn != -1){
                sorter.setSortable(unsortableColumn, false);
            }

            unsortableColumn = model.findColumn("Table(s)");
            if(unsortableColumn != -1){
                sorter.setSortable(unsortableColumn, false);
            }
        }catch(Exception e){}
        
        sorter.sort();
    }
    
    @Override
    public void setVisible(boolean b) {
        super.setVisible(b);
    }
    
    private void updateSelectedReservation(){
        if(this.ReservationsTable.getSelectedRow() == -1){
            this.DeleteSelectedReservation.setEnabled(false);
            this.OpenSelectedReservation.setEnabled(false);
        }else{
            this.selectedReservationCode = (Integer) this.ReservationsTable.getValueAt(this.ReservationsTable.getSelectedRow(), 0);

            this.DeleteSelectedReservation.setEnabled(true);
            this.OpenSelectedReservation.setEnabled(true);
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

        WindowTitle = new javax.swing.JLabel();
        PaneOfReservationTable = new javax.swing.JScrollPane();
        ReservationsTable = new javax.swing.JTable();
        OpenSelectedReservation = new javax.swing.JButton();
        DeleteSelectedReservation = new javax.swing.JButton();
        AddNewReservation = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Restaurant BDCO - Liste de Réservations");

        WindowTitle.setFont(new java.awt.Font("DejaVu Sans", 0, 18)); // NOI18N
        WindowTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        WindowTitle.setText("Liste de Réservations");

        ReservationsTable.setModel(new DefaultTableModel());
        ReservationsTable.setRowHeight(30);
        ReservationsTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        ReservationsTable.getTableHeader().setReorderingAllowed(false);
        ReservationsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                ReservationsTableMousePressed(evt);
            }
        });
        ReservationsTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ReservationsTableKeyPressed(evt);
            }
        });
        PaneOfReservationTable.setViewportView(ReservationsTable);
        ReservationsTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        OpenSelectedReservation.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Modele/Icons/book-open-next.png"))); // NOI18N
        OpenSelectedReservation.setText("Ouvrir Reservation Selectioné");
        OpenSelectedReservation.setToolTipText("");
        OpenSelectedReservation.setActionCommand("Open Reservation #??");
        OpenSelectedReservation.setEnabled(false);
        OpenSelectedReservation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OpenSelectedReservationActionPerformed(evt);
            }
        });

        DeleteSelectedReservation.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Modele/Icons/book--minus.png"))); // NOI18N
        DeleteSelectedReservation.setText("Supprimer Reservation");
        DeleteSelectedReservation.setEnabled(false);
        DeleteSelectedReservation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteSelectedReservationActionPerformed(evt);
            }
        });

        AddNewReservation.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Modele/Icons/book--plus.png"))); // NOI18N
        AddNewReservation.setText("Ajouter Reservation");
        AddNewReservation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddNewReservationActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PaneOfReservationTable)
                    .addComponent(WindowTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(AddNewReservation)
                        .addGap(18, 18, 18)
                        .addComponent(DeleteSelectedReservation)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                        .addComponent(OpenSelectedReservation)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(WindowTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(PaneOfReservationTable, javax.swing.GroupLayout.DEFAULT_SIZE, 365, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AddNewReservation, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DeleteSelectedReservation, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(OpenSelectedReservation, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void OpenSelectedReservationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OpenSelectedReservationActionPerformed
        GlobalGraphicView.singletonGlobalGraphicView().getController().setView(EnumView.ResevationDetails);
    }//GEN-LAST:event_OpenSelectedReservationActionPerformed

    private void DeleteSelectedReservationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteSelectedReservationActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DeleteSelectedReservationActionPerformed

    private void AddNewReservationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddNewReservationActionPerformed
        GlobalGraphicView.singletonGlobalGraphicView().getController().setView(EnumView.ReservationCreation);
    }//GEN-LAST:event_AddNewReservationActionPerformed

    private void ReservationsTableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ReservationsTableMousePressed
        this.updateSelectedReservation();
    }//GEN-LAST:event_ReservationsTableMousePressed

    private void ReservationsTableKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ReservationsTableKeyPressed
        this.updateSelectedReservation();
    }//GEN-LAST:event_ReservationsTableKeyPressed

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
            java.util.logging.Logger.getLogger(FrameReservationList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameReservationList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameReservationList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameReservationList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameReservationList().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddNewReservation;
    private javax.swing.JButton DeleteSelectedReservation;
    private javax.swing.JButton OpenSelectedReservation;
    private javax.swing.JScrollPane PaneOfReservationTable;
    private javax.swing.JTable ReservationsTable;
    private javax.swing.JLabel WindowTitle;
    // End of variables declaration//GEN-END:variables
}

