/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import controller.ControllerTarefaSemanal;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.Semana;
import model.TarefaSemanal;

/**
 *
 * @author Bernardo
 */
public class TelaTarefaSemanal extends javax.swing.JFrame {

    private ControllerTarefaSemanal controller = new ControllerTarefaSemanal();
    private List<Semana> semanas;
    private int idSemana;
    
    public TelaTarefaSemanal() {
        initComponents();
        setLocationRelativeTo(null);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                controller.abrirTelaPrincipal();
                fecharTela();   
            }

        });

        atualizarSemanas();
        inicializarTabela();
    }

    private void inicializarTabela(){
        DefaultTableModel tabela = new DefaultTableModel(new Object[]{"Título", "Descrição", "Prioridade", "Concluida"},0){
              @Override
            public boolean isCellEditable(int row, int column) {
                return column == 3;
            }
            
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                switch (columnIndex) {
                    case 2:
                        return Integer.class;
                    case 3:
                        return Boolean.class;
                    default:
                        return String.class;
                }
            }
        };
        
        tblResultados.setModel(tabela);
    }
    
    private void atualizarSemanas(){
        semanas = controller.listarSemanas();
        for(Semana semana : semanas){
            cbSemanas.addItem(semana.toString());
        }
    }
    
    private void atualizarTarefas(){
        List<TarefaSemanal> tarefas = controller.listarTarefasSemanais(idSemana);
        
        DefaultTableModel modelo = (DefaultTableModel) tblResultados.getModel();
        modelo.setRowCount(0);
            
        for (TarefaSemanal tarefa : tarefas) {
            modelo.addRow(new Object[]{
                tarefa.getTitulo(),
                tarefa.getDescricao(),
                tarefa.getPrioridade(),
                tarefa.isConcluida()
            });
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        cbSemanas = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        btnAdicionarSemana = new javax.swing.JButton();
        btnAdicionar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblResultados = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Gerenciador de Rotina - Tarefas Semanais");
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Tarefas Semanais");

        jPanel1.setPreferredSize(new java.awt.Dimension(853, 101));

        cbSemanas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbSemanasActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Semana:");

        btnAdicionarSemana.setText("Adicionar Semana");
        btnAdicionarSemana.setPreferredSize(new java.awt.Dimension(142, 40));
        btnAdicionarSemana.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarSemanaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbSemanas, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(btnAdicionarSemana, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(404, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbSemanas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(btnAdicionarSemana, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(55, Short.MAX_VALUE))
        );

        btnAdicionar.setText("Adicionar");
        btnAdicionar.setPreferredSize(new java.awt.Dimension(142, 40));
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
            }
        });

        tblResultados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tblResultados);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 833, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 833, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(btnAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
        controller.abrirTelaAdicionar();
        controller.setIdSemana(idSemana);
    }//GEN-LAST:event_btnAdicionarActionPerformed

    private void btnAdicionarSemanaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarSemanaActionPerformed
        controller.adicionarSemana();
        atualizarSemanas();
    }//GEN-LAST:event_btnAdicionarSemanaActionPerformed

    private void cbSemanasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSemanasActionPerformed
        idSemana = semanas.get(cbSemanas.getSelectedIndex()).getId();
        atualizarTarefas();
    }//GEN-LAST:event_cbSemanasActionPerformed

    private void fecharTela() {
        this.dispose();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnAdicionarSemana;
    private javax.swing.JComboBox<String> cbSemanas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblResultados;
    // End of variables declaration//GEN-END:variables
}
