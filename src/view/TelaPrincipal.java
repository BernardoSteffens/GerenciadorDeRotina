/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import controller.ControllerPrincipal;
import java.util.List;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import model.TarefaDiaria;

/**
 *
 * @author Bernardo
 */
public class TelaPrincipal extends javax.swing.JFrame {

    private ControllerPrincipal controller = new ControllerPrincipal();
    private List<TarefaDiaria> tarefas;
    
    public TelaPrincipal() {
        initComponents();
        setLocationRelativeTo(null);
        ControllerPrincipal controller = new ControllerPrincipal();
        
        DefaultTableModel modelo = new DefaultTableModel(new Object[]{"Título", "Concluida"},0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 1;
            }
            
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if(columnIndex == 0){
                    return String.class;
                } 
                return Boolean.class;
            }
        };
        
        modelo.addTableModelListener(new TableModelListener(){
            public void tableChanged(TableModelEvent e){
                int row = e.getFirstRow();
                int column = e.getColumn();
                
                if (column == 1 && e.getType() == TableModelEvent.UPDATE) {
                    Boolean concluida = (Boolean) modelo.getValueAt(row, column);
                    int linhaSelecionada = tblResultados.getSelectedRow();
                    
                    TarefaDiaria tarefaAlterada = tarefas.get(linhaSelecionada);
                    controller.atualizarConcluidaTarefa(tarefaAlterada, concluida);
                }
            }
        
        });
        
        tblResultados.setModel(modelo);
        
        atualizarDataDeHoje();
        atualizarTarefasDoDia();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        btnTarefaDiaria = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        lblDataHoje = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblResultados = new javax.swing.JTable();
        btnTarefaSemanal = new javax.swing.JButton();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gerenciador de Rotina - Tela Inicial");
        setResizable(false);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setText("Gerenciador de Rotina");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Tarefas de Hoje:");

        btnTarefaDiaria.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnTarefaDiaria.setText("Tarefas Diárias");
        btnTarefaDiaria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTarefaDiariaActionPerformed(evt);
            }
        });

        jLabel3.setText("Data de Hoje:");

        lblDataHoje.setText("00/00/0000");

        tblResultados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Título", "Concluída"
            }
        ));
        jScrollPane2.setViewportView(tblResultados);

        btnTarefaSemanal.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnTarefaSemanal.setText("Tarefas Semanais");
        btnTarefaSemanal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTarefaSemanalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblDataHoje, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 535, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnTarefaDiaria, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
                    .addComponent(btnTarefaSemanal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(19, 19, 19))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(lblDataHoje)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(btnTarefaDiaria, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnTarefaSemanal, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(180, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTarefaDiariaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTarefaDiariaActionPerformed
        controller.abrirTelaTarefaDiaira();
        this.dispose();
    }//GEN-LAST:event_btnTarefaDiariaActionPerformed

    private void btnTarefaSemanalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTarefaSemanalActionPerformed
        controller.abrirTelaTarefaSemanal();
        this.dispose();
    }//GEN-LAST:event_btnTarefaSemanalActionPerformed
    
    public void atualizarTarefasDoDia(){
        DefaultTableModel modelo = (DefaultTableModel) tblResultados.getModel();
        modelo.setRowCount(0);
        tarefas = controller.gerarTarefasDoDia();

        for (TarefaDiaria tarefa : tarefas) {
            modelo.addRow(new Object[]{
                tarefa.getTitulo(),
                tarefa.isConcluida()
            });
        }
    }
    
    public void atualizarDataDeHoje() {
        lblDataHoje.setText(controller.getData());
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnTarefaDiaria;
    private javax.swing.JButton btnTarefaSemanal;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblDataHoje;
    private javax.swing.JTable tblResultados;
    // End of variables declaration//GEN-END:variables

}
