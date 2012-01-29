/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Main.java
 *
 * Created on Nov 2, 2011, 2:43:32 PM
 */
package ann.view;

import ann.controller.Controller;
import ann.controller.ControllerInterface;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.text.DecimalFormat;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.SpinnerNumberModel;
import ann.math.Matrix;
import ann.math.SHL;
import ann.math.CalcTheta;
import ann.model.MatrixModelInterface;
import ann.model.TargetModel;
import ann.model.ThetaModel;
import ann.model.W0Model;
import ann.model.W1Model;
import ann.model.W2Model;
import ann.observer.TargetObserver;
import ann.observer.ThetaObserver;
import ann.observer.W0Observer;
import ann.observer.W1Observer;
import ann.observer.W2Observer;

/**
 *
 * @author Kaleb
 */
public class ControlFrame extends JFrame implements W2Observer, W1Observer, W0Observer, ThetaObserver, TargetObserver
{
    private ControllerInterface w2Controller;
    private ControllerInterface w1Controller;
    private ControllerInterface w0Controller;
    private ControllerInterface thetaController;
    private ControllerInterface targetController;
    private DecimalFormat decFormat;
    private String decimalFormat;
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

    /** Creates new form Main */
    public ControlFrame()
    {
        initComponents();

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

        // get a new instance of a controller
        w2Controller = new Controller(this, w2Model);
        w1Controller = new Controller(this, w1Model);
        w0Controller = new Controller(this, w0Model);
        thetaController = new Controller(this, thetaModel);
        targetController = new Controller(this, targetModel);

        jLabelHeaderTheta.setText("Theta Matrix:  " + "\u0398" + "\u2082");

        // Set the desired decimal format here.
        // *This can be overridden by the User Preferances panel!*
        decimalFormat = ("0.0000");

        // Creates a new DecimalFormatter for the text fields so we can
        // can control how many decimal places get printed.
        decFormat = new DecimalFormat(decimalFormat);

        currentMatrixTheta = null;
        currentMatrixW2 = null;
        currentMatrixW1 = null;
        currentMatrixW0 = null;
        currentMatrixTarget = null;

        this.setExtendedState(Frame.MAXIMIZED_BOTH);

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
        mSpinnerW2.setModel(modelMW2);
        nSpinnerW2.setModel(modelNW2);
        mSpinnerW1.setModel(modelMW1);
        nSpinnerW1.setModel(modelNW1);
        mSpinnerW0.setModel(modelMW0);
        nSpinnerW0.setModel(modelNW0);
        mSpinnerTheta.setModel(modelMTheta);
        nSpinnerTheta.setModel(modelNTheta);
        mSpinnerTarget.setModel(modelMTarget);
        nSpinnerTarget.setModel(modelNTarget);

        Font font1 = new Font("Neuropol", Font.PLAIN, 13);

        jLabelResults.setFont(font1);
        jLabelValidW2.setForeground(Color.red);
        jLabelValidW2.setFont(font1);
        jLabelValidW1.setForeground(Color.red);
        jLabelValidW1.setFont(font1);
        jLabelValidW0.setForeground(Color.red);
        jLabelValidW0.setFont(font1);
        jLabelValidTarget.setForeground(Color.red);
        jLabelValidTarget.setFont(font1);
        jLabelValidTheta.setForeground(Color.red);
        jLabelValidTheta.setFont(font1);

        jSpinnerNumPoints.setModel(modelPoints);
        jSpinnerXTarget.setModel(modelXY);
        jSpinnerYTarget.setModel(modelXY);
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
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        importMatrixW2Button = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        mSpinnerW2 = new javax.swing.JSpinner();
        nSpinnerW2 = new javax.swing.JSpinner();
        jButtonGenerateTemplateW2 = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel15 = new javax.swing.JLabel();
        jLabelValidW2 = new javax.swing.JLabel();
        jPanelW1 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        jLabel19 = new javax.swing.JLabel();
        importMatrixW1Button = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextAreaMatrixW1 = new javax.swing.JTextArea();
        jLabel21 = new javax.swing.JLabel();
        mSpinnerW1 = new javax.swing.JSpinner();
        nSpinnerW1 = new javax.swing.JSpinner();
        jButtonGenerateTemplateW1 = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        jSeparator9 = new javax.swing.JSeparator();
        jLabelValidW1 = new javax.swing.JLabel();
        jPanelW0 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTextAreaMatrixW0 = new javax.swing.JTextArea();
        jLabel22 = new javax.swing.JLabel();
        jSeparator12 = new javax.swing.JSeparator();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        importMatrixW0Button = new javax.swing.JButton();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        mSpinnerW0 = new javax.swing.JSpinner();
        nSpinnerW0 = new javax.swing.JSpinner();
        jButtonGenerateTemplateW0 = new javax.swing.JButton();
        jSeparator13 = new javax.swing.JSeparator();
        jLabelValidW0 = new javax.swing.JLabel();
        jPanelTheta = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaMatrixTheta = new javax.swing.JTextArea();
        jSeparator2 = new javax.swing.JSeparator();
        jLabelHeaderTheta = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        importThetaMatrixButton = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        mSpinnerTheta = new javax.swing.JSpinner();
        nSpinnerTheta = new javax.swing.JSpinner();
        jButtonGenerateTemplateTheta = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel16 = new javax.swing.JLabel();
        jLabelValidTheta = new javax.swing.JLabel();
        jPanelResults = new javax.swing.JPanel();
        jSeparator10 = new javax.swing.JSeparator();
        jLabel28 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTextAreaMatrixResults = new javax.swing.JTextArea();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jSeparator11 = new javax.swing.JSeparator();
        jButtonRun = new javax.swing.JButton();
        jCheckBoxRandomDart = new javax.swing.JCheckBox();
        jCheckBoxSingleDart = new javax.swing.JCheckBox();
        jSeparator14 = new javax.swing.JSeparator();
        jLabel11 = new javax.swing.JLabel();
        jSpinnerNumPoints = new javax.swing.JSpinner();
        jLabel9 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jSpinnerXTarget = new javax.swing.JSpinner();
        jSpinnerYTarget = new javax.swing.JSpinner();
        jLabelResults = new javax.swing.JLabel();
        jPanelTargets = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextAreaMatrixTarget = new javax.swing.JTextArea();
        jSeparator5 = new javax.swing.JSeparator();
        importTargetButton = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel17 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        mSpinnerTarget = new javax.swing.JSpinner();
        nSpinnerTarget = new javax.swing.JSpinner();
        jButtonGenerateTemplateTarget = new javax.swing.JButton();
        jLabelValidTarget = new javax.swing.JLabel();
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

        jLabelHeaderW2.setText("Weight Matrix: W2");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 10));
        jLabel2.setText("Import Matrix from .CSV:");

        importMatrixW2Button.setText("Import Matrix");
        importMatrixW2Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                importMatrixW2ButtonActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 10));
        jLabel1.setText("Define Matrix Size:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 2, 11));
        jLabel3.setText("M x N");

        jButtonGenerateTemplateW2.setText("Generate Template");
        jButtonGenerateTemplateW2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGenerateTemplateW2ActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Tahoma", 3, 11));
        jLabel15.setText("W2 Matrix:");

        jLabelValidW2.setText("Valid W2 Import:");

        javax.swing.GroupLayout jPanelW2Layout = new javax.swing.GroupLayout(jPanelW2);
        jPanelW2.setLayout(jPanelW2Layout);
        jPanelW2Layout.setHorizontalGroup(
            jPanelW2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelW2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelW2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelW2Layout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelW2Layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(importMatrixW2Button))
                    .addComponent(jLabelHeaderW2)
                    .addGroup(jPanelW2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanelW2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addGroup(jPanelW2Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanelW2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanelW2Layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(jButtonGenerateTemplateW2))
                                    .addGroup(jPanelW2Layout.createSequentialGroup()
                                        .addComponent(mSpinnerW2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(nSpinnerW2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelW2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelW2Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabelValidW2))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
            .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 646, Short.MAX_VALUE)
        );
        jPanelW2Layout.setVerticalGroup(
            jPanelW2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelW2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelW2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelHeaderW2)
                    .addComponent(jLabel15)
                    .addComponent(jLabelValidW2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelW2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelW2Layout.createSequentialGroup()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(importMatrixW2Button)
                        .addGap(16, 16, 16)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelW2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(mSpinnerW2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nSpinnerW2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonGenerateTemplateW2))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanelW1.setMaximumSize(new java.awt.Dimension(650, 215));
        jPanelW1.setPreferredSize(new java.awt.Dimension(650, 215));

        jLabel18.setText("Weight Matrix: W1");

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 10));
        jLabel19.setText("Import Matrix from .CSV");

        importMatrixW1Button.setText("Import Matrix");
        importMatrixW1Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                importMatrixW1ButtonActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 10));
        jLabel20.setText("Define Matrix Size:");

        jTextAreaMatrixW1.setColumns(20);
        jTextAreaMatrixW1.setRows(5);
        jScrollPane5.setViewportView(jTextAreaMatrixW1);

        jLabel21.setFont(new java.awt.Font("Tahoma", 2, 11));
        jLabel21.setText("M x N");

        jButtonGenerateTemplateW1.setText("Generate Template");
        jButtonGenerateTemplateW1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGenerateTemplateW1ActionPerformed(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Tahoma", 3, 11));
        jLabel23.setText("W1 Matrix:");

        jLabelValidW1.setText("Valid W1 Import:");

        javax.swing.GroupLayout jPanelW1Layout = new javax.swing.GroupLayout(jPanelW1);
        jPanelW1.setLayout(jPanelW1Layout);
        jPanelW1Layout.setHorizontalGroup(
            jPanelW1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelW1Layout.createSequentialGroup()
                .addGroup(jPanelW1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator9, javax.swing.GroupLayout.DEFAULT_SIZE, 699, Short.MAX_VALUE)
                    .addGroup(jPanelW1Layout.createSequentialGroup()
                        .addGroup(jPanelW1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelW1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(jPanelW1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel18)
                                    .addGroup(jPanelW1Layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addGroup(jPanelW1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel20)
                                            .addComponent(jLabel19)))))
                            .addGroup(jPanelW1Layout.createSequentialGroup()
                                .addGap(57, 57, 57)
                                .addComponent(importMatrixW1Button))
                            .addGroup(jPanelW1Layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(mSpinnerW1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(nSpinnerW1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelW1Layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addComponent(jButtonGenerateTemplateW1))
                            .addGroup(jPanelW1Layout.createSequentialGroup()
                                .addGap(90, 90, 90)
                                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(12, 12, 12)
                        .addGroup(jPanelW1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelW1Layout.createSequentialGroup()
                                .addComponent(jLabel23)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabelValidW1))
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 429, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelW1Layout.setVerticalGroup(
            jPanelW1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelW1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelW1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(jLabel23)
                    .addComponent(jLabelValidW1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelW1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelW1Layout.createSequentialGroup()
                        .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(importMatrixW1Button)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelW1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(mSpinnerW1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nSpinnerW1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonGenerateTemplateW1))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelW0.setPreferredSize(new java.awt.Dimension(650, 215));

        jTextAreaMatrixW0.setColumns(20);
        jTextAreaMatrixW0.setRows(5);
        jScrollPane6.setViewportView(jTextAreaMatrixW0);

        jLabel22.setText("Weight Matrix: W0");

        jLabel24.setFont(new java.awt.Font("Tahoma", 3, 11));
        jLabel24.setText("W0 Matrix:");

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 10));
        jLabel25.setText("Import Matrix from .CSV");

        importMatrixW0Button.setText("Import Matrix");
        importMatrixW0Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                importMatrixW0ButtonActionPerformed(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 10));
        jLabel26.setText("Define Matrix Size:");

        jLabel27.setFont(new java.awt.Font("Tahoma", 2, 11));
        jLabel27.setText("M x N");

        jButtonGenerateTemplateW0.setText("Generate Template");
        jButtonGenerateTemplateW0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGenerateTemplateW0ActionPerformed(evt);
            }
        });

        jLabelValidW0.setText("Valid W0 Import:");

        javax.swing.GroupLayout jPanelW0Layout = new javax.swing.GroupLayout(jPanelW0);
        jPanelW0.setLayout(jPanelW0Layout);
        jPanelW0Layout.setHorizontalGroup(
            jPanelW0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelW0Layout.createSequentialGroup()
                .addGroup(jPanelW0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelW0Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel22))
                    .addComponent(jSeparator12, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelW0Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel25))
                    .addGroup(jPanelW0Layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(importMatrixW0Button))
                    .addGroup(jPanelW0Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanelW0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanelW0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanelW0Layout.createSequentialGroup()
                                    .addComponent(mSpinnerW0, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(nSpinnerW0, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jLabel26))))
                    .addGroup(jPanelW0Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jButtonGenerateTemplateW0)))
                .addGroup(jPanelW0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelW0Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelValidW0))
                    .addGroup(jPanelW0Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 407, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(14, 14, 14))
            .addGroup(jPanelW0Layout.createSequentialGroup()
                .addComponent(jSeparator13, javax.swing.GroupLayout.DEFAULT_SIZE, 644, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelW0Layout.setVerticalGroup(
            jPanelW0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelW0Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelW0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(jLabel24)
                    .addComponent(jLabelValidW0))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelW0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelW0Layout.createSequentialGroup()
                        .addComponent(jSeparator12, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(importMatrixW0Button)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel27)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelW0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(mSpinnerW0, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nSpinnerW0, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonGenerateTemplateW0))
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator13, javax.swing.GroupLayout.DEFAULT_SIZE, 17, Short.MAX_VALUE)
                .addGap(7, 7, 7))
        );

        jTextAreaMatrixTheta.setColumns(20);
        jTextAreaMatrixTheta.setRows(5);
        jScrollPane2.setViewportView(jTextAreaMatrixTheta);

        jLabelHeaderTheta.setText("Theta Matrix:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 10));
        jLabel4.setText("Import Matrix from .CSV");

        importThetaMatrixButton.setText("Import Matrix");
        importThetaMatrixButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                importThetaMatrixButtonActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 10));
        jLabel5.setText("Define Matrix Size:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 2, 11));
        jLabel6.setText("M x N");

        jButtonGenerateTemplateTheta.setText("Generate Template");
        jButtonGenerateTemplateTheta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGenerateTemplateThetaActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Tahoma", 3, 11));
        jLabel16.setText("Theta Matrix:");

        jLabelValidTheta.setText("Valid Theta Import:");

        javax.swing.GroupLayout jPanelThetaLayout = new javax.swing.GroupLayout(jPanelTheta);
        jPanelTheta.setLayout(jPanelThetaLayout);
        jPanelThetaLayout.setHorizontalGroup(
            jPanelThetaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 699, Short.MAX_VALUE)
            .addGroup(jPanelThetaLayout.createSequentialGroup()
                .addGroup(jPanelThetaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelThetaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4))
                    .addGroup(jPanelThetaLayout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(importThetaMatrixButton))
                    .addGroup(jPanelThetaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel5))
                    .addGroup(jPanelThetaLayout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jButtonGenerateTemplateTheta))
                    .addGroup(jPanelThetaLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(mSpinnerTheta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nSpinnerTheta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelThetaLayout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelThetaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabelHeaderTheta))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelThetaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelThetaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelThetaLayout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelValidTheta))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 429, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        jPanelThetaLayout.setVerticalGroup(
            jPanelThetaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelThetaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelThetaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelHeaderTheta, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(jLabelValidTheta))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelThetaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelThetaLayout.createSequentialGroup()
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(importThetaMatrixButton)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelThetaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(mSpinnerTheta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nSpinnerTheta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonGenerateTemplateTheta))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(249, 249, 249))
        );

        jLabel28.setText("Run Simulator:");

        jTextAreaMatrixResults.setColumns(20);
        jTextAreaMatrixResults.setRows(5);
        jScrollPane7.setViewportView(jTextAreaMatrixResults);

        jLabel29.setFont(new java.awt.Font("Tahoma", 3, 11));
        jLabel29.setText("Results:");

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 10));
        jLabel30.setText("Run Simulation!");

        jButtonRun.setText("Run!");
        jButtonRun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRunActionPerformed(evt);
            }
        });

        jCheckBoxRandomDart.setText("Random Dart Deluge!");
        jCheckBoxRandomDart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxRandomDartActionPerformed(evt);
            }
        });

        jCheckBoxSingleDart.setSelected(true);
        jCheckBoxSingleDart.setText("Single Dart");
        jCheckBoxSingleDart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxSingleDartActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 2, 10));
        jLabel11.setText("Select # of Points:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 2, 10));
        jLabel9.setText("Select Boundaries:");

        jLabel12.setFont(new java.awt.Font("Tahoma", 3, 11));
        jLabel12.setText("X  ,  Y");

        javax.swing.GroupLayout jPanelResultsLayout = new javax.swing.GroupLayout(jPanelResults);
        jPanelResults.setLayout(jPanelResultsLayout);
        jPanelResultsLayout.setHorizontalGroup(
            jPanelResultsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelResultsLayout.createSequentialGroup()
                .addGroup(jPanelResultsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator11, javax.swing.GroupLayout.DEFAULT_SIZE, 689, Short.MAX_VALUE)
                    .addGroup(jPanelResultsLayout.createSequentialGroup()
                        .addGroup(jPanelResultsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelResultsLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(jPanelResultsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jSpinnerNumPoints, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11)
                                    .addGroup(jPanelResultsLayout.createSequentialGroup()
                                        .addComponent(jSpinnerXTarget, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jSpinnerYTarget, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanelResultsLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanelResultsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jCheckBoxSingleDart, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jCheckBoxRandomDart, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jSeparator10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jSeparator14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanelResultsLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel28))
                            .addGroup(jPanelResultsLayout.createSequentialGroup()
                                .addGap(59, 59, 59)
                                .addComponent(jButtonRun))
                            .addGroup(jPanelResultsLayout.createSequentialGroup()
                                .addGap(46, 46, 46)
                                .addComponent(jLabel30)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelResultsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelResultsLayout.createSequentialGroup()
                                .addComponent(jLabel29)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelResults))
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 426, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelResultsLayout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                .addGap(527, 527, 527))
            .addGroup(jPanelResultsLayout.createSequentialGroup()
                .addGap(84, 84, 84)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(576, Short.MAX_VALUE))
        );
        jPanelResultsLayout.setVerticalGroup(
            jPanelResultsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelResultsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelResultsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(jLabel29)
                    .addComponent(jLabelResults))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelResultsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelResultsLayout.createSequentialGroup()
                        .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBoxSingleDart)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBoxRandomDart)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel30)
                        .addGap(9, 9, 9)
                        .addComponent(jButtonRun)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator14, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSpinnerNumPoints, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel12)
                        .addGap(7, 7, 7)
                        .addGroup(jPanelResultsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jSpinnerXTarget, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSpinnerYTarget, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator11, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel7.setText("Target Selection:");

        jTextAreaMatrixTarget.setColumns(20);
        jTextAreaMatrixTarget.setRows(5);
        jScrollPane3.setViewportView(jTextAreaMatrixTarget);

        importTargetButton.setText("Import Target");
        importTargetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                importTargetButtonActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 10));
        jLabel10.setText("Import Matrix from .CSV");

        jLabel17.setFont(new java.awt.Font("Tahoma", 3, 11));
        jLabel17.setText("Current Targets:");

        jLabel31.setFont(new java.awt.Font("Tahoma", 2, 11));
        jLabel31.setText("M x N");

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 10));
        jLabel32.setText("Define Matrix Size:");

        jButtonGenerateTemplateTarget.setText("Generate Template");
        jButtonGenerateTemplateTarget.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGenerateTemplateTargetActionPerformed(evt);
            }
        });

        jLabelValidTarget.setText("Valid Target Import:");

        javax.swing.GroupLayout jPanelTargetsLayout = new javax.swing.GroupLayout(jPanelTargets);
        jPanelTargets.setLayout(jPanelTargetsLayout);
        jPanelTargetsLayout.setHorizontalGroup(
            jPanelTargetsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelTargetsLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(jSeparator7, javax.swing.GroupLayout.DEFAULT_SIZE, 1083, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanelTargetsLayout.createSequentialGroup()
                .addGroup(jPanelTargetsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelTargetsLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel7))
                    .addGroup(jPanelTargetsLayout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(importTargetButton))
                    .addGroup(jPanelTargetsLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE))
                    .addGroup(jPanelTargetsLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel32))
                    .addGroup(jPanelTargetsLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(mSpinnerTarget, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nSpinnerTarget, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelTargetsLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jButtonGenerateTemplateTarget))
                    .addGroup(jPanelTargetsLayout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelTargetsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelTargetsLayout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabelValidTarget))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(468, 468, 468))
        );
        jPanelTargetsLayout.setVerticalGroup(
            jPanelTargetsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTargetsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelTargetsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel17)
                    .addComponent(jLabelValidTarget))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelTargetsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelTargetsLayout.createSequentialGroup()
                        .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(importTargetButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel32)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel31)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelTargetsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(mSpinnerTarget, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nSpinnerTarget, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonGenerateTemplateTarget))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(81, 81, 81)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelW2, javax.swing.GroupLayout.PREFERRED_SIZE, 646, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanelW0, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 654, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanelTargets, javax.swing.GroupLayout.PREFERRED_SIZE, 654, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanelResults, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelTheta, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelW1, javax.swing.GroupLayout.DEFAULT_SIZE, 699, Short.MAX_VALUE))
                .addGap(103, 103, 103))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanelW1, 0, 214, Short.MAX_VALUE)
                    .addComponent(jPanelW2, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelW0, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanelTheta, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanelTargets, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelResults, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(262, 262, 262))
        );

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
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 1388, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(mainScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 767, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void importMatrixW2ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_importMatrixW2ButtonActionPerformed
        w2Controller.importMatrix();
        jLabelValidW2.setForeground(Color.green);
    }//GEN-LAST:event_importMatrixW2ButtonActionPerformed

    private void jButtonGenerateTemplateThetaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGenerateTemplateThetaActionPerformed
        matrix = new MatrixTemplatePanelTheta((((SpinnerNumberModel) nSpinnerTheta.getModel()).getNumber()).intValue(), (((SpinnerNumberModel) mSpinnerTheta.getModel()).getNumber()).intValue(), this);
    }//GEN-LAST:event_jButtonGenerateTemplateThetaActionPerformed

    private void importTargetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_importTargetButtonActionPerformed
        targetController.importMatrix();
        jLabelValidTarget.setForeground(Color.green);
    }//GEN-LAST:event_importTargetButtonActionPerformed

    private void importMatrixW1ButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_importMatrixW1ButtonActionPerformed
    {//GEN-HEADEREND:event_importMatrixW1ButtonActionPerformed
        w1Controller.importMatrix();

        jLabelValidW1.setForeground(Color.green);
    }//GEN-LAST:event_importMatrixW1ButtonActionPerformed

    private void jButtonGenerateTemplateW1ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonGenerateTemplateW1ActionPerformed
    {//GEN-HEADEREND:event_jButtonGenerateTemplateW1ActionPerformed
        matrix = new MatrixTemplatePanelW1((((SpinnerNumberModel) nSpinnerW1.getModel()).getNumber()).intValue(), (((SpinnerNumberModel) mSpinnerW1.getModel()).getNumber()).intValue(), this);
    }//GEN-LAST:event_jButtonGenerateTemplateW1ActionPerformed

    private void importMatrixW0ButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_importMatrixW0ButtonActionPerformed
    {//GEN-HEADEREND:event_importMatrixW0ButtonActionPerformed
        w0Controller.importMatrix();

        jLabelValidW0.setForeground(Color.green);
    }//GEN-LAST:event_importMatrixW0ButtonActionPerformed

    private void importThetaMatrixButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_importThetaMatrixButtonActionPerformed
    {//GEN-HEADEREND:event_importThetaMatrixButtonActionPerformed
        thetaController.importMatrix();

        jLabelValidTheta.setForeground(Color.green);
}//GEN-LAST:event_importThetaMatrixButtonActionPerformed

    private void jButtonRunActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonRunActionPerformed
    {//GEN-HEADEREND:event_jButtonRunActionPerformed
        if (jCheckBoxRandomDart.isSelected())
        {
            Matrix matrixMath = new Matrix();
            SHL shl = new SHL();
            CalcTheta theta = new CalcTheta();

            // Used to count the number of hits on a individual shape in the image
            int[][] uniqueHits = new int[currentMatrixW1.length][1];

            // Used to calculate the ratio of hits to misses on a individual shape in the image
            double[][] uniqueHitsRatio = new double[currentMatrixW1.length][1];

            // Used to count the number of hits in a spot where two or more images overlap
            int sharedHits = 0;

            // Used to calculate the ratio of hits to misses in a spot where two or more images overlap
            double sharedHitsRatio = 0;

            // The number of darts fired at the image
            double darts = 100000;

            // For loop to fire the darts
            for (int i = 0; i < darts; i++)
            {
                // Random number generator for the points
                Random random = new Random();

                // Create a double array for the random points
                double[][] points =
                {
                    {
                        // A random double no larger than 5 (the outer bounds of the image)
                        random.nextDouble() * (((SpinnerNumberModel) jSpinnerXTarget.getModel()).getNumber()).intValue()
                    },
                    {
                        // A random double no larger than 5 (the outer bounds of the image)
                        random.nextDouble() * (((SpinnerNumberModel) jSpinnerYTarget.getModel()).getNumber()).intValue()
                    }
                };

                // SHL[(W2*P)+Theta2]
                double[][] matrix0 = matrixMath.multiply(currentMatrixW2, points);
                double[][] matrix1 = matrixMath.addMatrix(matrix0, currentMatrixTheta);
                double[][] matrix2 = shl.calculate(matrix1);

                // SHL[(W1*P)+Theta1]
                double[][] matrix3 = matrixMath.multiply(currentMatrixW1, matrix2);
                double[][] theta1 = theta.calculateAnd(currentMatrixW1);
                double[][] matrix4 = matrixMath.addMatrix(matrix3, theta1);
                double[][] matrix5 = shl.calculate(matrix4);

                // Check for unique hits and tally them
                for (int k = 0; k < matrix5.length; k++)
                {
                    for (int l = 0; l < matrix5[k].length; l++)
                    {
                        if (matrix5[k][l] == 1)
                        {
                            uniqueHits[k][l]++;
                        }
                    }
                }

                // SHL[(W0*P)+Theta0]
                double[][] theta2 = theta.calculateOr(currentMatrixW0);
                double[][] matrix6 = matrixMath.multiply(currentMatrixW0, matrix5);
                double[][] matrix7 = matrixMath.addMatrix(matrix6, theta2);
                double[][] matrix8 = shl.calculate(matrix7);

                // Check for shared hits and tally them
                for (int k = 0; k < matrix8.length; k++)
                {
                    for (int l = 0; l < matrix8[k].length; l++)
                    {
                        if (matrix8[k][l] == 1)
                        {
                            sharedHits++;
                        }
                    }
                }

                updateResultsMatrixRandom(matrix8, "Shape #:");
            }

            // Calculate the ratio of unique hits
            for (int k = 0; k < uniqueHits.length; k++)
            {
                for (int l = 0; l < uniqueHits[k].length; l++)
                {
                    uniqueHitsRatio[k][l] = uniqueHits[k][l] / darts;
                }
            }

            // Calculate the ratio of shared hits
            sharedHitsRatio = sharedHits / darts;

            updateResultsMatrixRandom(uniqueHitsRatio, "Input #:");

            appendResultsMatrix(sharedHitsRatio, "Shape:");
        } else if (jCheckBoxSingleDart.isSelected())
        {
            Matrix matrix = new Matrix();
            SHL shl = new SHL();
            CalcTheta theta = new CalcTheta();

            // SHL[(W2*P)+Theta2]
            appendResultsMatrix("Input Layer");
            double[][] matrix0 = matrix.multiply(currentMatrixW2, currentMatrixTarget);
            appendResultsMatrix(currentMatrixW2, "W2:");
            appendResultsMatrix(currentMatrixTarget, "Target:");
            appendResultsMatrix(currentMatrixTheta, "Theta2:");
            appendResultsMatrix(matrix0, "W2*Target");
            double[][] matrix1 = matrix.addMatrix(matrix0, currentMatrixTheta);
            appendResultsMatrix(matrix1, "(W2*Target) + Theta2");
            double[][] matrix2 = shl.calculate(matrix1);
            appendResultsMatrix(matrix2, "SHL[(W2*Target) + Theta2]");

            // SHL[(W1*P)+Theta1]
            appendResultsMatrix("Hidden Layer");
            double[][] matrix3 = matrix.multiply(currentMatrixW1, matrix2);
            double[][] theta1 = theta.calculateAnd(currentMatrixW1);
            appendResultsMatrix(currentMatrixW1, "W1:");
            appendResultsMatrix(theta1, "Theta1 (ANDing):");
            appendResultsMatrix(matrix3, "W1*SHL[(W2*Target)+Theta2]:");
            double[][] matrix4 = matrix.addMatrix(matrix3, theta1);
            appendResultsMatrix(matrix4, "(W1*SHL[(W2*Target)+Theta2])+Theta1");
            double[][] matrix5 = shl.calculate(matrix4);
            appendResultsMatrix(matrix4, "SHL[(W1*SHL[(W2*Target)+Theta2])+Theta1]");

            // SHL[(W0*P)+Theta0]
            appendResultsMatrix("Output Layer");
            double[][] theta2 = theta.calculateOr(currentMatrixW0);
            double[][] matrix6 = matrix.multiply(currentMatrixW0, matrix5);
            appendResultsMatrix(currentMatrixW0, "W:");
            appendResultsMatrix(theta2, "Theta0 (ORing):");
            appendResultsMatrix(matrix6, "(W0*SHL[(W1*SHL[(W2*Target)+Theta2])+Theta1]");
            double[][] matrix7 = matrix.addMatrix(matrix6, theta2);
            appendResultsMatrix(matrix7, "(W0*SHL[(W1*SHL[(W2*Target)+Theta2])+Theta1]+Theta0");
            double[][] matrix8 = shl.calculate(matrix7);
            appendResultsMatrix(matrix8, "SHL[(W0*SHL[(W1*SHL[(W2*Target)+Theta2])+Theta1]+Theta0]");

            if (matrix8[0][0] == 1)
            {
                jLabelResults.setForeground(Color.green);
                jLabelResults.setText("Hit!");
            }
            if (matrix8[0][0] != 1)
            {
                jLabelResults.setForeground(Color.red);
                jLabelResults.setText("Miss!");
            }
        }
    }//GEN-LAST:event_jButtonRunActionPerformed

    private void jMenuItemImportModelActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jMenuItemImportModelActionPerformed
    {//GEN-HEADEREND:event_jMenuItemImportModelActionPerformed
        ///controller.importMatrix();

        jLabelValidW0.setForeground(Color.green);
        jLabelValidW1.setForeground(Color.green);
        jLabelValidW2.setForeground(Color.green);
        jLabelValidTarget.setForeground(Color.green);
        jLabelValidTheta.setForeground(Color.green);
    }//GEN-LAST:event_jMenuItemImportModelActionPerformed

    private void jCheckBoxRandomDartActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jCheckBoxRandomDartActionPerformed
    {//GEN-HEADEREND:event_jCheckBoxRandomDartActionPerformed
        if (jCheckBoxRandomDart.isSelected())
        {
            jCheckBoxSingleDart.setSelected(false);
        }
    }//GEN-LAST:event_jCheckBoxRandomDartActionPerformed

    private void jCheckBoxSingleDartActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jCheckBoxSingleDartActionPerformed
    {//GEN-HEADEREND:event_jCheckBoxSingleDartActionPerformed
        if (jCheckBoxSingleDart.isSelected())
        {
            jCheckBoxRandomDart.setSelected(false);
        }
    }//GEN-LAST:event_jCheckBoxSingleDartActionPerformed

    private void jButtonGenerateTemplateW2ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonGenerateTemplateW2ActionPerformed
    {//GEN-HEADEREND:event_jButtonGenerateTemplateW2ActionPerformed
        matrix = new MatrixTemplatePanelW2((((SpinnerNumberModel) nSpinnerW2.getModel()).getNumber()).intValue(), (((SpinnerNumberModel) mSpinnerW2.getModel()).getNumber()).intValue(), this);
        jLabelValidW2.setForeground(Color.green);
    }//GEN-LAST:event_jButtonGenerateTemplateW2ActionPerformed

    private void jButtonGenerateTemplateW0ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonGenerateTemplateW0ActionPerformed
    {//GEN-HEADEREND:event_jButtonGenerateTemplateW0ActionPerformed
        matrix = new MatrixTemplatePanelW0((((SpinnerNumberModel) nSpinnerW0.getModel()).getNumber()).intValue(), (((SpinnerNumberModel) mSpinnerW0.getModel()).getNumber()).intValue(), this);
    }//GEN-LAST:event_jButtonGenerateTemplateW0ActionPerformed

    private void jButtonGenerateTemplateTargetActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonGenerateTemplateTargetActionPerformed
    {//GEN-HEADEREND:event_jButtonGenerateTemplateTargetActionPerformed
        matrix = new MatrixTemplatePanelTarget((((SpinnerNumberModel) nSpinnerTarget.getModel()).getNumber()).intValue(), (((SpinnerNumberModel) mSpinnerTarget.getModel()).getNumber()).intValue(), this);
    }//GEN-LAST:event_jButtonGenerateTemplateTargetActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jMenuItem1ActionPerformed
    {//GEN-HEADEREND:event_jMenuItem1ActionPerformed
        //controller.importMatrix();
    }//GEN-LAST:event_jMenuItem1ActionPerformed


    /*
     * Set the current Theta Matrix used for the mathematical model.
     */
    public void setCurrentMatrixResults(double[][] currentMatrixResults)
    {
        this.currentMatrixResults = currentMatrixResults;
    }

    /*
     * Set the current Theta Matrix used for jTextArea.
     */
    public void updateResultsMatrix(double[][] matrix)
    {
        String stringMatrix = "";

        for (int i = 0; i < matrix.length; i++)
        {
            for (int j = 0; j < matrix[i].length; j++)
            {
                stringMatrix += decFormat.format(matrix[i][j]) + "\t";
            }

            stringMatrix += "\n";
        }

        jTextAreaMatrixResults.setText(stringMatrix);
    }

    /*
     * Set the current Theta Matrix used for the mathematical model.
     */
    public void setCurrentMatrixResultsRandom(double[][] currentMatrixResults)
    {
        this.currentMatrixResults = currentMatrixResults;
    }

    /*
     * Set the current Theta Matrix used for jTextArea.
     */
    public void updateResultsMatrixRandom(double[][] matrix, String description)
    {
        String stringMatrix = "";

        int count = 0;
        for (int i = 0; i < matrix.length; i++)
        {
            for (int j = 0; j < matrix[i].length; j++)
            {
                count++;
                stringMatrix += description + " " + count;
                stringMatrix += "\n";
                stringMatrix += decFormat.format(matrix[i][j]) + "\t";
            }

            stringMatrix += "\n";
        }

        jTextAreaMatrixResults.setText(stringMatrix);
    }

    /*
     * Set the current Theta Matrix used for jTextArea.
     */
    public void appendResultsMatrix(double[][] matrix, String description)
    {
        String stringMatrix = "\n" + description;

        stringMatrix += "\n";
        for (int i = 0; i < matrix.length; i++)
        {
            for (int j = 0; j < matrix[i].length; j++)
            {
                stringMatrix += decFormat.format(matrix[i][j]) + "\t";
            }

            stringMatrix += "\n";
        }

        jTextAreaMatrixResults.append(stringMatrix);
    }

    /*
     * Set the current Theta Matrix used for jTextArea.
     */
    public void appendResultsMatrix(double matrix)
    {
        String stringMatrix = "\n" + Double.toString(matrix);

        jTextAreaMatrixResults.append(stringMatrix);
    }

    /*
     * Set the current Theta Matrix used for jTextArea.
     */
    public void appendResultsMatrix(String description)
    {
        String stringMatrix = "\n" + description;

        jTextAreaMatrixResults.append(stringMatrix);
    }

    /*
     * Set the current Theta Matrix used for jTextArea.
     */
    public void appendResultsMatrix(double matrix, String description)
    {
        String stringMatrix = "\n" + description;

        stringMatrix += "\n" + Double.toString(matrix);

        jTextAreaMatrixResults.append(stringMatrix);
    }

    /*
     * Set the current Theta Matrix used for the mathematical model.
     */
    public void setCurrentMatrixTarget(double[][] currentMatrixTarget)
    {
        this.currentMatrixTarget = currentMatrixTarget;
    }

    /**
     * Set the current Theta Matrix used for jTextArea.
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

    /*
     * Set the current Theta Matrix used for the mathematical model.
     */
    public void setCurrentMatrixTheta(double[][] currentMatrixTheta)
    {
        this.currentMatrixTheta = currentMatrixTheta;
    }

    /**
     * Set the current Theta Matrix used for jTextArea.
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

    /*
     * Set the current W0 Matrix used for the mathematical model.
     */
    public void setCurrentMatrixW0(double[][] currentMatrixW0)
    {
        this.currentMatrixW0 = currentMatrixW0;
    }

    /*
     * Set the current W0 Matrix used for jTextArea.
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

    /*
     * Set the current W1 Matrix used for the mathematical model.
     */
    public void setCurrentMatrixW1(double[][] currentMatrixW1)
    {
        this.currentMatrixW1 = currentMatrixW1;
    }

    /*
     * Set the current W1 Matrix used for jTextArea.
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

    /*
     * Set the current W2 Matrix used for the mathematical model.
     */
    public void setCurrentMatrixW2(double[][] currentMatrixW2)
    {
        this.currentMatrixW2 = currentMatrixW2;
    }

    /**
     * Set the current W2 Matrix.
     * @param matrix
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

    public double[][] getCurrentMatrixTarget()
    {
        return currentMatrixTarget;
    }

    public double[][] getCurrentMatrixTheta()
    {
        return currentMatrixTheta;
    }

    public double[][] getCurrentMatrixW0()
    {
        return currentMatrixW0;
    }

    public double[][] getCurrentMatrixW1()
    {
        return currentMatrixW1;
    }

    public double[][] getCurrentMatrixW2()
    {
        return currentMatrixW2;
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
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton importMatrixW0Button;
    private javax.swing.JButton importMatrixW1Button;
    private javax.swing.JButton importMatrixW2Button;
    private javax.swing.JButton importTargetButton;
    private javax.swing.JButton importThetaMatrixButton;
    private javax.swing.JButton jButtonGenerateTemplateTarget;
    private javax.swing.JButton jButtonGenerateTemplateTheta;
    private javax.swing.JButton jButtonGenerateTemplateW0;
    private javax.swing.JButton jButtonGenerateTemplateW1;
    private javax.swing.JButton jButtonGenerateTemplateW2;
    private javax.swing.JButton jButtonRun;
    private javax.swing.JCheckBox jCheckBoxRandomDart;
    private javax.swing.JCheckBox jCheckBoxSingleDart;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelHeaderTheta;
    private javax.swing.JLabel jLabelHeaderW2;
    private javax.swing.JLabel jLabelResults;
    private javax.swing.JLabel jLabelValidTarget;
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
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JSpinner jSpinnerNumPoints;
    private javax.swing.JSpinner jSpinnerXTarget;
    private javax.swing.JSpinner jSpinnerYTarget;
    private javax.swing.JTextArea jTextAreaMatrixResults;
    private javax.swing.JTextArea jTextAreaMatrixTarget;
    private javax.swing.JTextArea jTextAreaMatrixTheta;
    private javax.swing.JTextArea jTextAreaMatrixW0;
    private javax.swing.JTextArea jTextAreaMatrixW1;
    private javax.swing.JTextArea jTextAreaMatrixW2;
    private javax.swing.JSpinner mSpinnerTarget;
    private javax.swing.JSpinner mSpinnerTheta;
    private javax.swing.JSpinner mSpinnerW0;
    private javax.swing.JSpinner mSpinnerW1;
    private javax.swing.JSpinner mSpinnerW2;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JScrollPane mainScrollPane;
    private javax.swing.JSpinner nSpinnerTarget;
    private javax.swing.JSpinner nSpinnerTheta;
    private javax.swing.JSpinner nSpinnerW0;
    private javax.swing.JSpinner nSpinnerW1;
    private javax.swing.JSpinner nSpinnerW2;
    // End of variables declaration//GEN-END:variables
}
