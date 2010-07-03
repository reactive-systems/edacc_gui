/*
 * EDACCGridSettingsView.java
 *
 * Created on Nov 26, 2009, 9:34:21 PM
 */
package edacc;

import edacc.gridqueues.GridQueuesController;
import edacc.model.GridQueue;
import edacc.model.GridQueueDAO;
import edacc.model.NoConnectionToDBException;
import java.awt.Color;
import java.awt.event.FocusEvent;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;
import org.jdesktop.application.Action;

/**
 *
 * @author Daniel D.
 */
public class EDACCGridSettingsView extends javax.swing.JDialog {

    private enum DialogMode {

        Create, Edit
    };
    private DialogMode mode;
    private EDACCManageGridQueuesDialog manageGridQueuesDialog;
    private GridQueue currentQueue;
    private final Color BAD = new Color(255, 102, 102);
    private final Color GOOD = Color.white;

    /** Creates new form EDACCGridSettingsView */
    public EDACCGridSettingsView(java.awt.Frame parent, boolean modal, EDACCManageGridQueuesDialog manageGridQueuesDialog) {
        super(parent, modal);
        initComponents();
        this.manageGridQueuesDialog = manageGridQueuesDialog;
        setLocationRelativeTo(manageGridQueuesDialog);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblNumNodes = new javax.swing.JLabel();
        lblWallTime = new javax.swing.JLabel();
        txtNumNodes = new javax.swing.JTextField();
        txtWalltime = new javax.swing.JTextField();
        lblMaxJobsInQueue = new javax.swing.JLabel();
        txtMaxJobsInQueue = new javax.swing.JTextField();
        lblPBSScript = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();
        lblName = new javax.swing.JLabel();
        lblLocation = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        txtLocation = new javax.swing.JTextField();
        lblNumCPUs = new javax.swing.JLabel();
        txtNumCPUs = new javax.swing.JTextField();
        lblAvailNodes = new javax.swing.JLabel();
        txtAvailNodes = new javax.swing.JTextField();
        lblDescription = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        taDescription = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        btnOk = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        pbsScriptMessage = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(edacc.EDACCApp.class).getContext().getResourceMap(EDACCGridSettingsView.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setMinimumSize(new java.awt.Dimension(330, 390));
        setModalExclusionType(java.awt.Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
        setName("Form"); // NOI18N

        lblNumNodes.setLabelFor(txtNumNodes);
        lblNumNodes.setText(resourceMap.getString("lblNumNodes.text")); // NOI18N
        lblNumNodes.setName("lblNumNodes"); // NOI18N

        lblWallTime.setLabelFor(txtWalltime);
        lblWallTime.setText(resourceMap.getString("lblWallTime.text")); // NOI18N
        lblWallTime.setName("lblWallTime"); // NOI18N

        txtNumNodes.setText(resourceMap.getString("txtNumNodes.text")); // NOI18N
        txtNumNodes.setToolTipText(resourceMap.getString("txtNumNodes.toolTipText")); // NOI18N
        txtNumNodes.setName("txtNumNodes"); // NOI18N
        txtNumNodes.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tcSelectOnFocus(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tcSelectOnFocus(evt);
            }
        });
        txtNumNodes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                EDACCGridSettingsView.this.keyReleased(evt);
            }
        });

        txtWalltime.setText(resourceMap.getString("txtWalltime.text")); // NOI18N
        txtWalltime.setToolTipText(resourceMap.getString("txtWalltime.toolTipText")); // NOI18N
        txtWalltime.setName("txtWalltime"); // NOI18N
        txtWalltime.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tcSelectOnFocus(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tcSelectOnFocus(evt);
            }
        });
        txtWalltime.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                EDACCGridSettingsView.this.keyReleased(evt);
            }
        });

        lblMaxJobsInQueue.setLabelFor(txtMaxJobsInQueue);
        lblMaxJobsInQueue.setText(resourceMap.getString("lblMaxJobsInQueue.text")); // NOI18N
        lblMaxJobsInQueue.setName("lblMaxJobsInQueue"); // NOI18N

        txtMaxJobsInQueue.setText(resourceMap.getString("txtMaxJobsInQueue.text")); // NOI18N
        txtMaxJobsInQueue.setToolTipText(resourceMap.getString("txtMaxJobsInQueue.toolTipText")); // NOI18N
        txtMaxJobsInQueue.setName("txtMaxJobsInQueue"); // NOI18N
        txtMaxJobsInQueue.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tcSelectOnFocus(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tcSelectOnFocus(evt);
            }
        });
        txtMaxJobsInQueue.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                EDACCGridSettingsView.this.keyReleased(evt);
            }
        });

        lblPBSScript.setText(resourceMap.getString("lblPBSScript.text")); // NOI18N
        lblPBSScript.setName("lblPBSScript"); // NOI18N

        btnAdd.setText(resourceMap.getString("btnPBSScript.text")); // NOI18N
        btnAdd.setToolTipText(resourceMap.getString("btnPBSScript.toolTipText")); // NOI18N
        btnAdd.setName("btnPBSScript"); // NOI18N
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdd(evt);
            }
        });

        lblName.setLabelFor(txtName);
        lblName.setText(resourceMap.getString("lblName.text")); // NOI18N
        lblName.setName("lblName"); // NOI18N

        lblLocation.setLabelFor(txtLocation);
        lblLocation.setText(resourceMap.getString("lblLocation.text")); // NOI18N
        lblLocation.setName("lblLocation"); // NOI18N

        txtName.setText(resourceMap.getString("txtName.text")); // NOI18N
        txtName.setToolTipText(resourceMap.getString("txtName.toolTipText")); // NOI18N
        txtName.setName("txtName"); // NOI18N
        txtName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tcSelectOnFocus(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tcSelectOnFocus(evt);
            }
        });
        txtName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                EDACCGridSettingsView.this.keyReleased(evt);
            }
        });

        txtLocation.setText(resourceMap.getString("txtLocation.text")); // NOI18N
        txtLocation.setToolTipText(resourceMap.getString("txtLocation.toolTipText")); // NOI18N
        txtLocation.setName("txtLocation"); // NOI18N
        txtLocation.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tcSelectOnFocus(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tcSelectOnFocus(evt);
            }
        });

        lblNumCPUs.setText(resourceMap.getString("lblNumCPUs.text")); // NOI18N
        lblNumCPUs.setName("lblNumCPUs"); // NOI18N

        txtNumCPUs.setText(resourceMap.getString("txtNumCPUs.text")); // NOI18N
        txtNumCPUs.setToolTipText(resourceMap.getString("txtNumCPUs.toolTipText")); // NOI18N
        txtNumCPUs.setName("txtNumCPUs"); // NOI18N
        txtNumCPUs.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tcSelectOnFocus(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tcSelectOnFocus(evt);
            }
        });
        txtNumCPUs.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                EDACCGridSettingsView.this.keyReleased(evt);
            }
        });

        lblAvailNodes.setLabelFor(txtAvailNodes);
        lblAvailNodes.setText(resourceMap.getString("lblAvailNodes.text")); // NOI18N
        lblAvailNodes.setName("lblAvailNodes"); // NOI18N

        txtAvailNodes.setText(resourceMap.getString("txtAvailNodes.text")); // NOI18N
        txtAvailNodes.setToolTipText(resourceMap.getString("txtAvailNodes.toolTipText")); // NOI18N
        txtAvailNodes.setName("txtAvailNodes"); // NOI18N
        txtAvailNodes.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tcSelectOnFocus(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tcSelectOnFocus(evt);
            }
        });
        txtAvailNodes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                EDACCGridSettingsView.this.keyReleased(evt);
            }
        });

        lblDescription.setLabelFor(taDescription);
        lblDescription.setText(resourceMap.getString("lblDescription.text")); // NOI18N
        lblDescription.setName("lblDescription"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        taDescription.setColumns(20);
        taDescription.setRows(5);
        taDescription.setToolTipText(resourceMap.getString("taDescription.toolTipText")); // NOI18N
        taDescription.setName("taDescription"); // NOI18N
        taDescription.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tcSelectOnFocus(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tcSelectOnFocus(evt);
            }
        });
        jScrollPane1.setViewportView(taDescription);

        jPanel1.setName("jPanel1"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(edacc.EDACCApp.class).getContext().getActionMap(EDACCGridSettingsView.class, this);
        btnOk.setAction(actionMap.get("btnOk")); // NOI18N
        btnOk.setText(resourceMap.getString("btnOk.text")); // NOI18N
        btnOk.setToolTipText(resourceMap.getString("btnOk.toolTipText")); // NOI18N
        btnOk.setName("btnOk"); // NOI18N

        btnCancel.setAction(actionMap.get("btnCancel")); // NOI18N
        btnCancel.setText(resourceMap.getString("btnCancel.text")); // NOI18N
        btnCancel.setToolTipText(resourceMap.getString("btnCancel.toolTipText")); // NOI18N
        btnCancel.setName("btnCancel"); // NOI18N

        pbsScriptMessage.setForeground(resourceMap.getColor("pbsScriptMessage.foreground")); // NOI18N
        pbsScriptMessage.setText(resourceMap.getString("pbsScriptMessage.text")); // NOI18N
        pbsScriptMessage.setName("pbsScriptMessage"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnCancel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 118, Short.MAX_VALUE)
                        .addComponent(btnOk))
                    .addComponent(pbsScriptMessage))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(pbsScriptMessage)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancel)
                    .addComponent(btnOk)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblLocation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblNumNodes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblNumCPUs, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblWallTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblAvailNodes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblMaxJobsInQueue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblDescription, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblPBSScript, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtLocation, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                            .addComponent(txtNumNodes, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                            .addComponent(txtNumCPUs, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                            .addComponent(txtWalltime, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                            .addComponent(txtAvailNodes, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                            .addComponent(txtMaxJobsInQueue, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                            .addComponent(btnAdd, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                            .addComponent(txtName, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtLocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLocation))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNumNodes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNumNodes))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNumCPUs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNumCPUs))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(lblWallTime))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtWalltime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAvailNodes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAvailNodes))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMaxJobsInQueue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMaxJobsInQueue))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDescription)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPBSScript)
                    .addComponent(btnAdd))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private JFileChooser pbsFileChooser;
    private void btnAdd(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdd
        if (pbsFileChooser == null) {
            pbsFileChooser = new JFileChooser();
            pbsFileChooser.setMultiSelectionEnabled(false);
        }
        if (pbsFileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            GridQueuesController.getInstance().addPBSScript(pbsFileChooser.getSelectedFile());
        }
        checkInputs();
    }//GEN-LAST:event_btnAdd

    private void keyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_keyReleased
        checkInputs();
    }//GEN-LAST:event_keyReleased

    /**
     * Selects the complete text of the src-TextField/TextArea on the
     * focusGained event respectively deselects the text on the focusLost event.
     * Add this method to the focusGained and focusLost events of all textfields
     * which should select all text on focusGained and deselect it on focusLost.
     * The source of this event must be from the supertype JTextComponent!
     * @param evt
     */
    private void tcSelectOnFocus(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tcSelectOnFocus
        if (!(evt.getSource() instanceof JTextComponent))
            return;
        JTextComponent src = (JTextComponent) evt.getSource();
        if (evt.getID() == FocusEvent.FOCUS_GAINED)
            src.selectAll();
        else if (evt.getID() == FocusEvent.FOCUS_LOST)
            src.setSelectionEnd(0);
    }//GEN-LAST:event_tcSelectOnFocus

    /**
     * Loads the given grid queue settings to the dialog's elements.
     * If q is <code>null</code>, the dialog starts in creation mode and a new
     * queue will be created. Otherwise the given queue will be updated.
     * @param q
     * @throws SQLException
     */
    public void loadSettings(GridQueue q) throws SQLException {
        GridQueuesController.getInstance().clearPBSScript();
        if (q != null) {
            currentQueue = q;
            setDialogMode(DialogMode.Edit);
        } else {
            currentQueue = new GridQueue();
            setDialogMode(DialogMode.Create);
        }
        showQueue(currentQueue);
    }

    private void showQueue(GridQueue q) {
        if (q == null) {
            return;
        }
        // if (q != null) {
        txtName.setText(q.getName());
        txtLocation.setText(q.getLocation());
        txtNumNodes.setText(String.valueOf(q.getNumNodes()));
        txtNumCPUs.setText(String.valueOf(q.getNumCPUs()));
        txtWalltime.setText(String.valueOf(q.getWalltime()));
        txtAvailNodes.setText(String.valueOf(q.getAvailNodes()));
        txtMaxJobsInQueue.setText(String.valueOf(q.getMaxJobsQueue()));
        taDescription.setText(q.getDescription());
        /*  } else {
        txtName.setText("");
        txtLocation.setText("");
        txtNumNodes.setText("");
        txtNumCPUs.setText("");
        txtWalltime.setText("");
        txtAvailNodes.setText("");
        txtMaxJobsInQueue.setText("");
        taDescription.setText("");
        setDialogMode(DialogMode.Create);
        }*/
        checkInputs();
        txtName.requestFocus();
    }

    @Action
    public void btnOk() {
        try {
            String name = txtName.getText();
            String location = txtLocation.getText();
            int numNodes = Integer.parseInt(txtNumNodes.getText());
            int numCPUs = Integer.parseInt(txtNumCPUs.getText());
            int walltime = Integer.parseInt(txtWalltime.getText());
            int availNodes = Integer.parseInt(txtAvailNodes.getText());
            int maxJobsInQueue = Integer.parseInt(txtMaxJobsInQueue.getText());
            String description = taDescription.getText();

            currentQueue.setName(name);
            currentQueue.setLocation(location);
            currentQueue.setNumNodes(numNodes);
            currentQueue.setNumCPUs(numCPUs);
            currentQueue.setWalltime(walltime);
            currentQueue.setAvailNodes(availNodes);
            currentQueue.setMaxJobsQueue(maxJobsInQueue);
            currentQueue.setDescription(description);

            if (mode == DialogMode.Create) {
                GridQueuesController.getInstance().createNewGridQueue(currentQueue);
            } else {
                GridQueuesController.getInstance().saveEditedGridQueue(currentQueue);
            }
            this.setVisible(false);
            manageGridQueuesDialog.refreshView();
        } catch (NoConnectionToDBException e) {
            JOptionPane.showMessageDialog(this, "Couldn't save settings. No connection to database", "No database connection", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error while saving settings: \n" + e.getMessage(), "Error saving settings", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error saving settings, integers expected", "Integers expected", JOptionPane.ERROR_MESSAGE);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Error saving settings: The specified PBS script couldn't be found!", "File not found", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error saving settings: " + e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void setDialogMode(DialogMode mode) {
        this.mode = mode;
        if (mode == DialogMode.Create) {
            btnOk.setText("Create Queue");
        } else {
            btnOk.setText("Save Queue");
        }
    }

    @Action
    public void btnCancel() {
        GridQueuesController.getInstance().addPBSScript(null);
        this.setVisible(false);
    }

    public void checkInputs() {
        boolean error = false;
        GridQueue queueWithSameName = GridQueueDAO.queueWithSameNameExistsInCache(txtName.getText());
        
        if (txtName.getText().equals("") || (queueWithSameName != null && queueWithSameName != currentQueue)) {
            txtName.setBackground(BAD);
            error = true;
        } else {
            txtName.setBackground(GOOD);
        }

        if (!checkIntegerTextField(txtAvailNodes)) {
            error = true;
        }
        if (!checkIntegerTextField(txtMaxJobsInQueue)) {
            error = true;
        }
        if (!checkIntegerTextField(txtNumCPUs)) {
            error = true;
        }
        if (!checkIntegerTextField(txtNumNodes)) {
            error = true;
        }
        if (!checkIntegerTextField(txtWalltime)) {
            error = true;
        }

        // Show PBS script error message if no pbs script has been specified
        boolean pbsError = mode == DialogMode.Create && !GridQueuesController.getInstance().hasTmpPBSScript();
        pbsScriptMessage.setVisible(pbsError);

        btnOk.setEnabled(!error && !pbsError);
    }

    /**
     * Checks if a TextField expecting an int has a valid int in it and
     * modifies the colors of the textfield (Color.BAD for invalid ints and
     * Color.GOOD for valid ints).
     * A string representation of an int is valid if it is != 0 and if it can
     * be converted to a valid int.
     * @param tf
     * @return if the text of the TextField is a valid int.
     */
    private boolean checkIntegerTextField(JTextField tf) {
        try {
            if (Integer.parseInt(tf.getText()) == 0) {
                tf.setBackground(BAD);
                return false;
            } else {
                tf.setBackground(GOOD);
                return true;
            }
        } catch (NumberFormatException e) {
            tf.setBackground(BAD);
            return false;
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnOk;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAvailNodes;
    private javax.swing.JLabel lblDescription;
    private javax.swing.JLabel lblLocation;
    private javax.swing.JLabel lblMaxJobsInQueue;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblNumCPUs;
    private javax.swing.JLabel lblNumNodes;
    private javax.swing.JLabel lblPBSScript;
    private javax.swing.JLabel lblWallTime;
    private javax.swing.JLabel pbsScriptMessage;
    private javax.swing.JTextArea taDescription;
    private javax.swing.JTextField txtAvailNodes;
    private javax.swing.JTextField txtLocation;
    private javax.swing.JTextField txtMaxJobsInQueue;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtNumCPUs;
    private javax.swing.JTextField txtNumNodes;
    private javax.swing.JTextField txtWalltime;
    // End of variables declaration//GEN-END:variables
}
