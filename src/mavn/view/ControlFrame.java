/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Main.java
 *
 * Created on Nov 2, 2011, 2:43:32 PM
 */
package mavn.view;

import mavn.controller.Controller;
import mavn.controller.ControllerInterface;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.text.DecimalFormat;
import javax.swing.JFrame;
import javax.swing.SpinnerNumberModel;
import mavn.model.MatrixModelInterface;
import mavn.model.TargetModel;
import mavn.model.ThetaModel;
import mavn.model.W0Model;
import mavn.model.W1Model;
import mavn.model.W2Model;
import mavn.observer.ResultsObserver;
import mavn.observer.TargetObserver;
import mavn.observer.ThetaObserver;
import mavn.observer.W0Observer;
import mavn.observer.W1Observer;
import mavn.observer.W2Observer;

/**
 *
 * @author Kaleb
 */
public class ControlFrame extends JFrame implements W2Observer, W1Observer, W0Observer, ThetaObserver, TargetObserver, ResultsObserver
{

    private ControllerInterface w2Controller;
    private ControllerInterface w1Controller;
    private ControllerInterface w0Controller;
    private ControllerInterface thetaController;
    private ControllerInterface targetController;
    private ControllerInterface resultsController;
    private DecimalFormat decFormat;
    private double[][] currentMatrixTheta;
    private double[][] currentMatrixW2;
    private double[][] currentMatrixW1;
    private double[][] currentMatrixW0;
    private double[][] currentMatrixTarget;
    private double[][] currentMatrixResults;
    private MatrixModelInterface w2Model;
    private MatrixModelInterface w1Model;
    private MatrixModelInterface w0Model;
    private MatrixModelInterface thetaModel;
    private MatrixModelInterface targetModel;
    private MatrixTemplatePanel matrix;
    private SpinnerNumberModel modelPoints;
    private SpinnerNumberModel modelMW2;
    private SpinnerNumberModel modelNW2;
    private SpinnerNumberModel modelMW1;
    private SpinnerNumberModel modelNW1;
    private SpinnerNumberModel modelMW0;
    private SpinnerNumberModel modelNW0;
    private SpinnerNumberModel modelMTheta;
    private SpinnerNumberModel modelNTheta;
    private SpinnerNumberModel modelMTarget;
    private SpinnerNumberModel modelNTarget;
    private SpinnerNumberModel modelXY;
    private String decimalFormat;

    /** Creates new form Main */
    public ControlFrame()
    {
        initComponents();
        initMatricies();
        initDecimalFormat();
        initModels();
        initControllers();
        initSpinners();
        initLabels();

        this.setExtendedState(Frame.MAXIMIZED_BOTH);
    }
    
    /**
     * Get the current Target Matrix.
     * @return the Target Matrix
     */
    public double[][] getCurrentMatrixTarget()
    {
        return currentMatrixTarget;
    }

    /**
     * Get the current Theta Matrix.
     * @return the Theta Matrix
     */
    public double[][] getCurrentMatrixTheta()
    {
        return currentMatrixTheta;
    }

    /**
     * Get the current W0 Matrix.
     * @return the W0 Matrix
     */
    public double[][] getCurrentMatrixW0()
    {
        return currentMatrixW0;
    }

    /**
     * Get the current W1 Matrix.
     * @return the W1 matrix
     */
    public double[][] getCurrentMatrixW1()
    {
        return currentMatrixW1;
    }

    /**
     * Get the current W2 Matrix.
     * @return the W2 matrix
     */
    public double[][] getCurrentMatrixW2()
    {
        return currentMatrixW2;
    }

    /**
     * Set the current Results Matrix.
     * @param currentMatrixResults the new Results Matrix
     */
    public void setCurrentMatrixResults(double[][] currentMatrixResults)
    {
        this.currentMatrixResults = currentMatrixResults;
    }

    /**
     * Set current Target Matrix.
     * @param currentMatrixTarget the new Target Matrix
     */
    public void setCurrentMatrixTarget(double[][] currentMatrixTarget)
    {
        this.currentMatrixTarget = currentMatrixTarget;
    }

    /**
     * Set current Theta Matrix.
     * @param currentMatrixTheta the new Theta Matrix
     */
    public void setCurrentMatrixTheta(double[][] currentMatrixTheta)
    {
        this.currentMatrixTheta = currentMatrixTheta;
    }

    /**
     * Set current W0 Matrix.
     * @param currentMatrixW0 the new W0 Matrix
     */
    public void setCurrentMatrixW0(double[][] currentMatrixW0)
    {
        this.currentMatrixW0 = currentMatrixW0;
    }

    /**
     * Set current W1 Matrix.
     * @param currentMatrixW1 the new W1 Matrix
     */
    public void setCurrentMatrixW1(double[][] currentMatrixW1)
    {
        this.currentMatrixW1 = currentMatrixW1;
    }

    /**
     * Set current W2 Matrix.
     * @param currentMatrixW2 the new W1 Matrix
     */
    public void setCurrentMatrixW2(double[][] currentMatrixW2)
    {
        this.currentMatrixW2 = currentMatrixW2;
    }

    /**
     * Overload method to update the Results Matrix.
     * @param matrix the new matrix
     * @param description the new matrix description
     */
    @Override
    public void updateResultsMatrix(String results)
    {
        jTextAreaMatrixResults.append(results);
    }

    /**
     * Update the current Target Matrix.
     * @param matrix the new Target Matrix
     */
    @Override
    public void updateTargetMatrix(double[][] matrix)
    {
        setCurrentMatrixTarget(matrix);

        String stringMatrix = "";

        for (int i = 0; i < matrix.length; i++)
        {
            for (int j = 0; j < matrix[i].length; j++)
            {
                stringMatrix += decFormat.format(matrix[i][j]) + "\t";
            }

            stringMatrix += "\n";
        }

        jTextAreaMatrixTarget.setText(stringMatrix);
    }

    /**
     * Update the current Theta Matrix.
     * @param matrix the new Theta Matrix
     */
    @Override
    public void updateThetaMatrix(double[][] matrix)
    {
        setCurrentMatrixTheta(matrix);

        String stringMatrix = "";

        for (int i = 0; i < matrix.length; i++)
        {
            for (int j = 0; j < matrix[i].length; j++)
            {
                stringMatrix += decFormat.format(matrix[i][j]) + "\t";
            }

            stringMatrix += "\n";
        }

        jTextAreaMatrixTheta.setText(stringMatrix);
    }

    /**
     * Update the current W0 Matrix.
     * @param matrix the new W0 Matrix
     */
    @Override
    public void updateW0Matrix(double[][] matrix)
    {
        setCurrentMatrixW0(matrix);

        String stringMatrix = "";

        for (int i = 0; i < matrix.length; i++)
        {
            for (int j = 0; j < matrix[i].length; j++)
            {
                stringMatrix += decFormat.format(matrix[i][j]) + "\t";
            }

            stringMatrix += "\n";
        }

        jTextAreaMatrixW0.setText(stringMatrix);
    }

    /**
     * Update the current W1 Matrix.
     * @param matrix the new W1 Matrix
     */
    @Override
    public void updateW1Matrix(double[][] matrix)
    {
        // set the local copy to the new matrix
        setCurrentMatrixW1(matrix);

        // parse the rest into a string so it can be displayed
        String stringMatrix = "";

        for (int i = 0; i < matrix.length; i++)
        {
            for (int j = 0; j < matrix[i].length; j++)
            {
                stringMatrix += decFormat.format(matrix[i][j]) + "\t";
            }

            stringMatrix += "\n";
        }

        jTextAreaMatrixW1.setText(stringMatrix);
    }

    /**
     * Update the current W2 Matrix.
     * @param matrix the new W2 Matrix
     */
    @Override
    public void updateW2Matrix(double[][] matrix)
    {
        // Update the local copy with the new matrix
        setCurrentMatrixW2(matrix);

        // Parse the double out to a String so it can be displayed
        String stringMatrix = "";

        for (int i = 0; i < matrix.length; i++)
        {
            for (int j = 0; j < matrix[i].length; j++)
            {
                stringMatrix += decFormat.format(matrix[i][j]) + "\t";
            }

            stringMatrix += "\n";
        }

        jTextAreaMatrixW2.setText(stringMatrix);
    }

    private void initControllers()
    {
        // get a new instance of a controller
        w2Controller = new Controller(this, w2Model);
        w1Controller = new Controller(this, w1Model);
        w0Controller = new Controller(this, w0Model);
        thetaController = new Controller(this, thetaModel);
        targetController = new Controller(this, targetModel);
    }

    private void initDecimalFormat()
    {
        // Set the desired decimal format here.
        // *This can be overridden by the User Preferances panel!*
        decimalFormat = ("0.0000");

        // Creates a new DecimalFormatter for the text fields so we can
        // can control how many decimal places get printed.
        decFormat = new DecimalFormat(decimalFormat);
    }

    private void initLabels()
    {
        Font font1 = new Font("Neuropol", Font.PLAIN, 13);

        jLabelResults.setFont(font1);
        jLabelValidW2.setForeground(Color.red);
        jLabelValidW2.setFont(font1);
        jLabelValidW1.setForeground(Color.red);
        jLabelValidW1.setFont(font1);
        jLabelValidW0.setForeground(Color.red);
        jLabelValidW0.setFont(font1);
        targetMatrixLabel.setForeground(Color.red);
        targetMatrixLabel.setFont(font1);
        jLabelValidTheta.setForeground(Color.red);
        jLabelValidTheta.setFont(font1);
        jLabelHeaderTheta.setText("Theta Matrix:  " + "\u0398" + "\u2082");
    }

    private void initMatricies()
    {
        currentMatrixTheta = null;
        currentMatrixW2 = null;
        currentMatrixW1 = null;
        currentMatrixW0 = null;
        currentMatrixTarget = null;
    }

    private void initModels()
    {
        // get a new instance of the W2Model
        w2Model = new W2Model();
        // register with the W2Moel observer
        w2Model.registerObserver(this);

        // get a new instance of the W1Model
        w1Model = new W1Model();
        // register with the W2Moel observer
        w1Model.registerObserver(this);

        // get a new instance of the W1Model
        w0Model = new W0Model();
        // register with the W2Moel observer
        w0Model.registerObserver(this);

        // get a new instance of the W1Model
        thetaModel = new ThetaModel();
        // register with the W2Moel observer
        thetaModel.registerObserver(this);

        // get a new instance of the W1Model
        targetModel = new TargetModel();
        // register with the W2Moel observer
        targetModel.registerObserver(this);
    }

    private void initSpinners()
    {
        // Initialize the JSpinners.
        // You can set the format and name of the JSpinners here.
        modelPoints = new SpinnerNumberModel(1000.0, 0.0, 100000.0, 1);

        modelXY = new SpinnerNumberModel(5.0, 0.0, 100.0, .5);
        modelMW2 = new SpinnerNumberModel(0.0, 0.0, 100.0, 1);
        modelNW2 = new SpinnerNumberModel(0.0, 0.0, 100.0, 1);
        modelMW1 = new SpinnerNumberModel(0.0, 0.0, 100.0, 1);
        modelNW1 = new SpinnerNumberModel(0.0, 0.0, 100.0, 1);
        modelMW0 = new SpinnerNumberModel(0.0, 0.0, 100.0, 1);
        modelNW0 = new SpinnerNumberModel(0.0, 0.0, 100.0, 1);
        modelMTheta = new SpinnerNumberModel(0.0, 0.0, 100.0, 1);
        modelNTheta = new SpinnerNumberModel(0.0, 0.0, 100.0, 1);
        modelMTarget = new SpinnerNumberModel(0.0, 0.0, 100.0, 1);
        modelNTarget = new SpinnerNumberModel(0.0, 0.0, 100.0, 1);    
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(ControlFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(ControlFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(ControlFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(ControlFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {

            public void run()
            {
                new ControlFrame().setVisible(true);
            }
        });
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainScrollPane = new javax.swing.JScrollPane();
        mainPanel = new javax.swing.JPanel();
        jPanelW2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaMatrixW2 = new javax.swing.JTextArea();
        jLabelHeaderW2 = new javax.swing.JLabel();
        importMatrixW2Button = new javax.swing.JButton();
        newW2MatrixButton = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel15 = new javax.swing.JLabel();
        jLabelValidW2 = new javax.swing.JLabel();
        editW2MatrixButton = new javax.swing.JButton();
        saveW2MatrixButton = new javax.swing.JButton();
        clearW2MatrixButton = new javax.swing.JButton();
        jPanelW0 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTextAreaMatrixW0 = new javax.swing.JTextArea();
        jLabel22 = new javax.swing.JLabel();
        w0TextAreaLabel = new javax.swing.JLabel();
        importW0MatrixButton = new javax.swing.JButton();
        newW0MatrixButton = new javax.swing.JButton();
        jSeparator13 = new javax.swing.JSeparator();
        jLabelValidW0 = new javax.swing.JLabel();
        editW0MatrixButton = new javax.swing.JButton();
        saveW0MatrixButton = new javax.swing.JButton();
        clearW0MatrixButton = new javax.swing.JButton();
        jPanelTheta = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaMatrixTheta = new javax.swing.JTextArea();
        jLabelHeaderTheta = new javax.swing.JLabel();
        importThetaMatrixButton = new javax.swing.JButton();
        newThetaMatrixButton = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel16 = new javax.swing.JLabel();
        jLabelValidTheta = new javax.swing.JLabel();
        editThetaMatrixButton = new javax.swing.JButton();
        saveThetaMatrixButton = new javax.swing.JButton();
        clearThetaMatrixButton = new javax.swing.JButton();
        jPanelResults = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTextAreaMatrixResults = new javax.swing.JTextArea();
        jLabel29 = new javax.swing.JLabel();
        jSeparator11 = new javax.swing.JSeparator();
        runButton = new javax.swing.JButton();
        jLabelResults = new javax.swing.JLabel();
        saveResultsButton = new javax.swing.JButton();
        clearResultsMatrix = new javax.swing.JButton();
        runPropertiesButton = new javax.swing.JButton();
        viewResultsButton = new javax.swing.JButton();
        jPanelTargets = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextAreaMatrixTarget = new javax.swing.JTextArea();
        importTargetMatrixButton = new javax.swing.JButton();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel17 = new javax.swing.JLabel();
        newTargetMatrixButton = new javax.swing.JButton();
        targetMatrixLabel = new javax.swing.JLabel();
        editTargetMatrixButton = new javax.swing.JButton();
        saveTargetMatrixButton = new javax.swing.JButton();
        clearTargetMatrixButton = new javax.swing.JButton();
        jPanelW1 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        importW1MatrixButton = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextAreaMatrixW1 = new javax.swing.JTextArea();
        newW1MatrixButton = new javax.swing.JButton();
        w1TextAreaLabel = new javax.swing.JLabel();
        jSeparator9 = new javax.swing.JSeparator();
        jLabelValidW1 = new javax.swing.JLabel();
        editW1MatrixButton = new javax.swing.JButton();
        saveW1MatrixButton = new javax.swing.JButton();
        clearW1MatrixButton = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItemImportModel = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        mainScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        mainScrollPane.setMaximumSize(new java.awt.Dimension(1200, 1000));

        mainPanel.setMaximumSize(new java.awt.Dimension(1200, 1000));
        mainPanel.setPreferredSize(new java.awt.Dimension(1431, 1000));

        jPanelW2.setPreferredSize(new java.awt.Dimension(650, 215));

        jTextAreaMatrixW2.setColumns(20);
        jTextAreaMatrixW2.setRows(5);
        jScrollPane1.setViewportView(jTextAreaMatrixW2);

        jLabelHeaderW2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabelHeaderW2.setText("Weight Matrix: W2");

        importMatrixW2Button.setText("Import Matrix");
        importMatrixW2Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                importMatrixW2ButtonActionPerformed(evt);
            }
        });

        newW2MatrixButton.setText("New Matrix");
        newW2MatrixButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newW2MatrixButtonActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Tahoma", 3, 11));
        jLabel15.setText("W2 Matrix: Defines Image Vector Directions");

        jLabelValidW2.setText("Valid W2 Import:");

        editW2MatrixButton.setText("Edit Matrix");
        editW2MatrixButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editW2MatrixButtonActionPerformed(evt);
            }
        });

        saveW2MatrixButton.setText("Save Matrix");

        clearW2MatrixButton.setText("Clear Matrix");

        javax.swing.GroupLayout jPanelW2Layout = new javax.swing.GroupLayout(jPanelW2);
        jPanelW2.setLayout(jPanelW2Layout);
        jPanelW2Layout.setHorizontalGroup(
            jPanelW2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelW2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelW2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelValidW2)
                    .addGroup(jPanelW2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jSeparator3)
                        .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabelHeaderW2, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelW2Layout.createSequentialGroup()
                            .addComponent(newW2MatrixButton)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(importMatrixW2Button)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(editW2MatrixButton)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(saveW2MatrixButton)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(clearW2MatrixButton))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)))
                .addContainerGap(58, Short.MAX_VALUE))
        );

        jPanelW2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {clearW2MatrixButton, editW2MatrixButton, importMatrixW2Button, newW2MatrixButton, saveW2MatrixButton});

        jPanelW2Layout.setVerticalGroup(
            jPanelW2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelW2Layout.createSequentialGroup()
                .addGroup(jPanelW2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelHeaderW2)
                    .addGroup(jPanelW2Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanelW2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(newW2MatrixButton)
                            .addComponent(importMatrixW2Button)
                            .addComponent(editW2MatrixButton)
                            .addComponent(saveW2MatrixButton)
                            .addComponent(clearW2MatrixButton))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelValidW2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.DEFAULT_SIZE, 13, Short.MAX_VALUE))
        );

        jPanelW0.setPreferredSize(new java.awt.Dimension(650, 215));

        jTextAreaMatrixW0.setColumns(20);
        jTextAreaMatrixW0.setRows(5);
        jScrollPane6.setViewportView(jTextAreaMatrixW0);

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel22.setText("Weight Matrix: W0");

        w0TextAreaLabel.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        w0TextAreaLabel.setText("W0 Matrix: Defines ORing Node Connections");

        importW0MatrixButton.setText("Import Matrix");
        importW0MatrixButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                importW0MatrixButtonActionPerformed(evt);
            }
        });

        newW0MatrixButton.setText("New Matrix");
        newW0MatrixButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newW0MatrixButtonActionPerformed(evt);
            }
        });

        jLabelValidW0.setText("Valid W0 Import:");

        editW0MatrixButton.setText("Edit Matrix");
        editW0MatrixButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editW0MatrixButtonActionPerformed(evt);
            }
        });

        saveW0MatrixButton.setText("Save Matrix");

        clearW0MatrixButton.setText("Clear Matrix");

        javax.swing.GroupLayout jPanelW0Layout = new javax.swing.GroupLayout(jPanelW0);
        jPanelW0.setLayout(jPanelW0Layout);
        jPanelW0Layout.setHorizontalGroup(
            jPanelW0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelW0Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelW0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22)
                    .addComponent(jLabelValidW0)
                    .addComponent(w0TextAreaLabel)
                    .addGroup(jPanelW0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelW0Layout.createSequentialGroup()
                            .addComponent(newW0MatrixButton)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(importW0MatrixButton)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(editW0MatrixButton)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(saveW0MatrixButton)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(clearW0MatrixButton)))
                    .addComponent(jSeparator13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(69, Short.MAX_VALUE))
        );

        jPanelW0Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {clearW0MatrixButton, editW0MatrixButton, importW0MatrixButton, newW0MatrixButton, saveW0MatrixButton});

        jPanelW0Layout.setVerticalGroup(
            jPanelW0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelW0Layout.createSequentialGroup()
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelW0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newW0MatrixButton)
                    .addComponent(importW0MatrixButton)
                    .addComponent(editW0MatrixButton)
                    .addComponent(saveW0MatrixButton)
                    .addComponent(clearW0MatrixButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(w0TextAreaLabel)
                .addGap(1, 1, 1)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelValidW0)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(jSeparator13, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTextAreaMatrixTheta.setColumns(20);
        jTextAreaMatrixTheta.setRows(5);
        jScrollPane2.setViewportView(jTextAreaMatrixTheta);

        jLabelHeaderTheta.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabelHeaderTheta.setText("Theta Matrix: Theta2");

        importThetaMatrixButton.setText("Import Matrix");
        importThetaMatrixButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                importThetaMatrixButtonActionPerformed(evt);
            }
        });

        newThetaMatrixButton.setText("New Matrix");
        newThetaMatrixButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newThetaMatrixButtonActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        jLabel16.setText("Theta Matrix: Defines Vector Boundaries");

        jLabelValidTheta.setText("Valid Theta Import:");

        editThetaMatrixButton.setText("Edit Matrix");
        editThetaMatrixButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editThetaMatrixButtonActionPerformed(evt);
            }
        });

        saveThetaMatrixButton.setText("Save Matrix");

        clearThetaMatrixButton.setText("Clear Matrix");

        javax.swing.GroupLayout jPanelThetaLayout = new javax.swing.GroupLayout(jPanelTheta);
        jPanelTheta.setLayout(jPanelThetaLayout);
        jPanelThetaLayout.setHorizontalGroup(
            jPanelThetaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelThetaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelThetaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelHeaderTheta, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelThetaLayout.createSequentialGroup()
                        .addComponent(newThetaMatrixButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(importThetaMatrixButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(editThetaMatrixButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(saveThetaMatrixButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(clearThetaMatrixButton))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelThetaLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabelValidTheta))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap(69, Short.MAX_VALUE))
        );

        jPanelThetaLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {clearThetaMatrixButton, editThetaMatrixButton, importThetaMatrixButton, newThetaMatrixButton, saveThetaMatrixButton});

        jPanelThetaLayout.setVerticalGroup(
            jPanelThetaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelThetaLayout.createSequentialGroup()
                .addComponent(jLabelHeaderTheta, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelThetaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newThetaMatrixButton)
                    .addComponent(importThetaMatrixButton)
                    .addComponent(editThetaMatrixButton)
                    .addComponent(saveThetaMatrixButton)
                    .addComponent(clearThetaMatrixButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel16)
                .addGap(2, 2, 2)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelValidTheta)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54))
        );

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel28.setText("Run Simulator:");

        jTextAreaMatrixResults.setColumns(20);
        jTextAreaMatrixResults.setRows(5);
        jScrollPane7.setViewportView(jTextAreaMatrixResults);

        jLabel29.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        jLabel29.setText("Results: The Outcomes of the Simulation");

        runButton.setText("Run!");
        runButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                runButtonActionPerformed(evt);
            }
        });

        saveResultsButton.setText("Save Result");

        clearResultsMatrix.setText("Clear Results");

        runPropertiesButton.setText(" Properties");
        runPropertiesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                runPropertiesButtonActionPerformed(evt);
            }
        });

        viewResultsButton.setText("View Results");

        javax.swing.GroupLayout jPanelResultsLayout = new javax.swing.GroupLayout(jPanelResults);
        jPanelResults.setLayout(jPanelResultsLayout);
        jPanelResultsLayout.setHorizontalGroup(
            jPanelResultsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelResultsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelResultsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelResultsLayout.createSequentialGroup()
                        .addComponent(jLabel28)
                        .addGap(70, 70, 70)
                        .addComponent(jLabelResults))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelResultsLayout.createSequentialGroup()
                        .addComponent(runPropertiesButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(runButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(viewResultsButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(saveResultsButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(clearResultsMatrix))
                    .addComponent(jSeparator11, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel29, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap(79, Short.MAX_VALUE))
        );

        jPanelResultsLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {clearResultsMatrix, runButton, runPropertiesButton, saveResultsButton, viewResultsButton});

        jPanelResultsLayout.setVerticalGroup(
            jPanelResultsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelResultsLayout.createSequentialGroup()
                .addGroup(jPanelResultsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelResultsLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabelResults))
                    .addComponent(jLabel28))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelResultsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(runPropertiesButton)
                    .addComponent(runButton)
                    .addComponent(viewResultsButton)
                    .addComponent(saveResultsButton)
                    .addComponent(clearResultsMatrix))
                .addGap(18, 18, 18)
                .addComponent(jLabel29)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator11, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        jTextAreaMatrixTarget.setColumns(20);
        jTextAreaMatrixTarget.setRows(5);
        jScrollPane3.setViewportView(jTextAreaMatrixTarget);

        importTargetMatrixButton.setText("Import Matrix");
        importTargetMatrixButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                importTargetMatrixButtonActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        jLabel17.setText("Current Targets: Defines Node Inputs");

        newTargetMatrixButton.setText("New Matrix");
        newTargetMatrixButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newTargetMatrixButtonActionPerformed(evt);
            }
        });

        targetMatrixLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        targetMatrixLabel.setText("Target Marix:");

        editTargetMatrixButton.setText("Edit Matrix");
        editTargetMatrixButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editTargetMatrixButtonActionPerformed(evt);
            }
        });

        saveTargetMatrixButton.setText("Save Matrix");

        clearTargetMatrixButton.setText("Clear Matrix");

        javax.swing.GroupLayout jPanelTargetsLayout = new javax.swing.GroupLayout(jPanelTargets);
        jPanelTargets.setLayout(jPanelTargetsLayout);
        jPanelTargetsLayout.setHorizontalGroup(
            jPanelTargetsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTargetsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelTargetsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE)
                    .addComponent(jLabel17)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE)
                    .addGroup(jPanelTargetsLayout.createSequentialGroup()
                        .addComponent(newTargetMatrixButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(importTargetMatrixButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(editTargetMatrixButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(saveTargetMatrixButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(clearTargetMatrixButton))
                    .addComponent(targetMatrixLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37))
        );

        jPanelTargetsLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {clearTargetMatrixButton, editTargetMatrixButton, importTargetMatrixButton, newTargetMatrixButton, saveTargetMatrixButton});

        jPanelTargetsLayout.setVerticalGroup(
            jPanelTargetsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTargetsLayout.createSequentialGroup()
                .addComponent(targetMatrixLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelTargetsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newTargetMatrixButton)
                    .addComponent(importTargetMatrixButton)
                    .addComponent(editTargetMatrixButton)
                    .addComponent(saveTargetMatrixButton)
                    .addComponent(clearTargetMatrixButton))
                .addGap(16, 16, 16)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanelW1.setMaximumSize(new java.awt.Dimension(650, 215));
        jPanelW1.setPreferredSize(new java.awt.Dimension(650, 215));

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel18.setText("Weight Matrix: W1");

        importW1MatrixButton.setText("Import Matrix");
        importW1MatrixButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                importW1MatrixButtonActionPerformed(evt);
            }
        });

        jTextAreaMatrixW1.setColumns(20);
        jTextAreaMatrixW1.setRows(5);
        jScrollPane5.setViewportView(jTextAreaMatrixW1);

        newW1MatrixButton.setText("New Matrix");
        newW1MatrixButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newW1MatrixButtonActionPerformed(evt);
            }
        });

        w1TextAreaLabel.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        w1TextAreaLabel.setText("W1 Matrix: Defines ANDing Node Connections");

        jLabelValidW1.setText("Valid W1 Import:");

        editW1MatrixButton.setText("Edit Matrix");
        editW1MatrixButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editW1MatrixButtonActionPerformed(evt);
            }
        });

        saveW1MatrixButton.setText("Save Matrix");

        clearW1MatrixButton.setText("Clear Matrix");

        javax.swing.GroupLayout jPanelW1Layout = new javax.swing.GroupLayout(jPanelW1);
        jPanelW1.setLayout(jPanelW1Layout);
        jPanelW1Layout.setHorizontalGroup(
            jPanelW1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelW1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelW1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18)
                    .addComponent(jLabelValidW1)
                    .addComponent(w1TextAreaLabel)
                    .addGroup(jPanelW1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jSeparator9, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelW1Layout.createSequentialGroup()
                            .addComponent(newW1MatrixButton)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(importW1MatrixButton)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(editW1MatrixButton)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(saveW1MatrixButton)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(clearW1MatrixButton))))
                .addContainerGap(58, Short.MAX_VALUE))
        );

        jPanelW1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {clearW1MatrixButton, editW1MatrixButton, importW1MatrixButton, newW1MatrixButton, saveW1MatrixButton});

        jPanelW1Layout.setVerticalGroup(
            jPanelW1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelW1Layout.createSequentialGroup()
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelW1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newW1MatrixButton)
                    .addComponent(importW1MatrixButton)
                    .addComponent(editW1MatrixButton)
                    .addComponent(saveW1MatrixButton)
                    .addComponent(clearW1MatrixButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(w1TextAreaLabel)
                .addGap(1, 1, 1)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelValidW1)
                .addGap(7, 7, 7)
                .addComponent(jSeparator9, javax.swing.GroupLayout.DEFAULT_SIZE, 13, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelW1, javax.swing.GroupLayout.DEFAULT_SIZE, 577, Short.MAX_VALUE)
                    .addComponent(jPanelTargets, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelW2, javax.swing.GroupLayout.DEFAULT_SIZE, 577, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanelW0, javax.swing.GroupLayout.DEFAULT_SIZE, 588, Short.MAX_VALUE)
                    .addComponent(jPanelTheta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelResults, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(260, Short.MAX_VALUE))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanelTheta, 0, 315, Short.MAX_VALUE)
                    .addComponent(jPanelW2, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelW0, javax.swing.GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
                    .addComponent(jPanelW1, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelResults, javax.swing.GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
                    .addComponent(jPanelTargets, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        mainPanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jPanelTheta, jPanelW2});

        mainPanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jPanelResults, jPanelTargets});

        mainScrollPane.setViewportView(mainPanel);

        jMenu1.setText("File");

        jMenuItemImportModel.setText("Import Model");
        jMenuItemImportModel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemImportModelActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemImportModel);

        jMenuItem1.setText("Save Model");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 1175, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 999, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void importMatrixW2ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_importMatrixW2ButtonActionPerformed
        w2Controller.importMatrix();
        jLabelValidW2.setForeground(Color.green);
    }//GEN-LAST:event_importMatrixW2ButtonActionPerformed

    private void newThetaMatrixButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newThetaMatrixButtonActionPerformed
        //matrix = new MatrixTemplatePanelTheta((((SpinnerNumberModel) nSpinnerTheta.getModel()).getNumber()).intValue(), (((SpinnerNumberModel) mSpinnerTheta.getModel()).getNumber()).intValue(), this);
    }//GEN-LAST:event_newThetaMatrixButtonActionPerformed

    private void importTargetMatrixButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_importTargetMatrixButtonActionPerformed
        targetController.importMatrix();
        targetMatrixLabel.setForeground(Color.green);
    }//GEN-LAST:event_importTargetMatrixButtonActionPerformed

    private void importW1MatrixButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_importW1MatrixButtonActionPerformed
    {//GEN-HEADEREND:event_importW1MatrixButtonActionPerformed
        w1Controller.importMatrix();

        jLabelValidW1.setForeground(Color.green);
    }//GEN-LAST:event_importW1MatrixButtonActionPerformed

    private void newW1MatrixButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_newW1MatrixButtonActionPerformed
    {//GEN-HEADEREND:event_newW1MatrixButtonActionPerformed
       // matrix = new MatrixTemplatePanelW1((((SpinnerNumberModel) nSpinnerW1.getModel()).getNumber()).intValue(), (((SpinnerNumberModel) mSpinnerW1.getModel()).getNumber()).intValue(), this);
    }//GEN-LAST:event_newW1MatrixButtonActionPerformed

    private void importW0MatrixButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_importW0MatrixButtonActionPerformed
    {//GEN-HEADEREND:event_importW0MatrixButtonActionPerformed
        w0Controller.importMatrix();

        jLabelValidW0.setForeground(Color.green);
    }//GEN-LAST:event_importW0MatrixButtonActionPerformed

    private void importThetaMatrixButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_importThetaMatrixButtonActionPerformed
    {//GEN-HEADEREND:event_importThetaMatrixButtonActionPerformed
        thetaController.importMatrix();

        jLabelValidTheta.setForeground(Color.green);
}//GEN-LAST:event_importThetaMatrixButtonActionPerformed

    private void runButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_runButtonActionPerformed
    {//GEN-HEADEREND:event_runButtonActionPerformed
        resultsController.runMavnAlgorithm();
    }//GEN-LAST:event_runButtonActionPerformed

    private void jMenuItemImportModelActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jMenuItemImportModelActionPerformed
    {//GEN-HEADEREND:event_jMenuItemImportModelActionPerformed
        ///controller.importMatrix();

        jLabelValidW0.setForeground(Color.green);
        jLabelValidW1.setForeground(Color.green);
        jLabelValidW2.setForeground(Color.green);
        targetMatrixLabel.setForeground(Color.green);
        jLabelValidTheta.setForeground(Color.green);
    }//GEN-LAST:event_jMenuItemImportModelActionPerformed

    private void newW2MatrixButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_newW2MatrixButtonActionPerformed
    {//GEN-HEADEREND:event_newW2MatrixButtonActionPerformed
        //matrix = new MatrixTemplatePanelW2((((SpinnerNumberModel) nSpinnerW2.getModel()).getNumber()).intValue(), (((SpinnerNumberModel) mSpinnerW2.getModel()).getNumber()).intValue(), this);
        jLabelValidW2.setForeground(Color.green);
    }//GEN-LAST:event_newW2MatrixButtonActionPerformed

    private void newW0MatrixButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_newW0MatrixButtonActionPerformed
    {//GEN-HEADEREND:event_newW0MatrixButtonActionPerformed
       // matrix = new MatrixTemplatePanelW0((((SpinnerNumberModel) nSpinnerW0.getModel()).getNumber()).intValue(), (((SpinnerNumberModel) mSpinnerW0.getModel()).getNumber()).intValue(), this);
    }//GEN-LAST:event_newW0MatrixButtonActionPerformed

    private void newTargetMatrixButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_newTargetMatrixButtonActionPerformed
    {//GEN-HEADEREND:event_newTargetMatrixButtonActionPerformed
       // matrix = new MatrixTemplatePanelTarget((((SpinnerNumberModel) nSpinnerTarget.getModel()).getNumber()).intValue(), (((SpinnerNumberModel) mSpinnerTarget.getModel()).getNumber()).intValue(), this);
    }//GEN-LAST:event_newTargetMatrixButtonActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jMenuItem1ActionPerformed
    {//GEN-HEADEREND:event_jMenuItem1ActionPerformed
        //controller.importMatrix();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void editW2MatrixButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_editW2MatrixButtonActionPerformed
    {//GEN-HEADEREND:event_editW2MatrixButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_editW2MatrixButtonActionPerformed

    private void editThetaMatrixButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_editThetaMatrixButtonActionPerformed
    {//GEN-HEADEREND:event_editThetaMatrixButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_editThetaMatrixButtonActionPerformed

    private void editW1MatrixButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_editW1MatrixButtonActionPerformed
    {//GEN-HEADEREND:event_editW1MatrixButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_editW1MatrixButtonActionPerformed

    private void editTargetMatrixButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_editTargetMatrixButtonActionPerformed
    {//GEN-HEADEREND:event_editTargetMatrixButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_editTargetMatrixButtonActionPerformed

    private void editW0MatrixButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_editW0MatrixButtonActionPerformed
    {//GEN-HEADEREND:event_editW0MatrixButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_editW0MatrixButtonActionPerformed

    private void runPropertiesButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_runPropertiesButtonActionPerformed
    {//GEN-HEADEREND:event_runPropertiesButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_runPropertiesButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton clearResultsMatrix;
    private javax.swing.JButton clearTargetMatrixButton;
    private javax.swing.JButton clearThetaMatrixButton;
    private javax.swing.JButton clearW0MatrixButton;
    private javax.swing.JButton clearW1MatrixButton;
    private javax.swing.JButton clearW2MatrixButton;
    private javax.swing.JButton editTargetMatrixButton;
    private javax.swing.JButton editThetaMatrixButton;
    private javax.swing.JButton editW0MatrixButton;
    private javax.swing.JButton editW1MatrixButton;
    private javax.swing.JButton editW2MatrixButton;
    private javax.swing.JButton importMatrixW2Button;
    private javax.swing.JButton importTargetMatrixButton;
    private javax.swing.JButton importThetaMatrixButton;
    private javax.swing.JButton importW0MatrixButton;
    private javax.swing.JButton importW1MatrixButton;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabelHeaderTheta;
    private javax.swing.JLabel jLabelHeaderW2;
    private javax.swing.JLabel jLabelResults;
    private javax.swing.JLabel jLabelValidTheta;
    private javax.swing.JLabel jLabelValidW0;
    private javax.swing.JLabel jLabelValidW1;
    private javax.swing.JLabel jLabelValidW2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItemImportModel;
    private javax.swing.JPanel jPanelResults;
    private javax.swing.JPanel jPanelTargets;
    private javax.swing.JPanel jPanelTheta;
    private javax.swing.JPanel jPanelW0;
    private javax.swing.JPanel jPanelW1;
    private javax.swing.JPanel jPanelW2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTextArea jTextAreaMatrixResults;
    private javax.swing.JTextArea jTextAreaMatrixTarget;
    private javax.swing.JTextArea jTextAreaMatrixTheta;
    private javax.swing.JTextArea jTextAreaMatrixW0;
    private javax.swing.JTextArea jTextAreaMatrixW1;
    private javax.swing.JTextArea jTextAreaMatrixW2;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JScrollPane mainScrollPane;
    private javax.swing.JButton newTargetMatrixButton;
    private javax.swing.JButton newThetaMatrixButton;
    private javax.swing.JButton newW0MatrixButton;
    private javax.swing.JButton newW1MatrixButton;
    private javax.swing.JButton newW2MatrixButton;
    private javax.swing.JButton runButton;
    private javax.swing.JButton runPropertiesButton;
    private javax.swing.JButton saveResultsButton;
    private javax.swing.JButton saveTargetMatrixButton;
    private javax.swing.JButton saveThetaMatrixButton;
    private javax.swing.JButton saveW0MatrixButton;
    private javax.swing.JButton saveW1MatrixButton;
    private javax.swing.JButton saveW2MatrixButton;
    private javax.swing.JLabel targetMatrixLabel;
    private javax.swing.JButton viewResultsButton;
    private javax.swing.JLabel w0TextAreaLabel;
    private javax.swing.JLabel w1TextAreaLabel;
    // End of variables declaration//GEN-END:variables
}
