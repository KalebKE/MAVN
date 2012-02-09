/*
MavnView -- a class within the Machine Artificial Vision Network(Machine Artificial Vision Network).
Copyright (C) 2012, Kaleb Kircher, Dennis Steele.

This program is free software; you can redistribute it and/or
modify it under the terms of the GNU General Public License
as published by the Free Software Foundation; either version 2
of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, write to the Free Software
Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */

/*
 * Main.java
 *
 * Created on Nov 2, 2011, 2:43:32 PM
 */
package mavn.view;

import file.controller.DirectoryController;
import mavn.state.properties.view.PropertiesFrame;
import java.awt.Color;
import javax.swing.JButton;

import mavn.controller.InputControllerInterface;
import java.awt.Font;
import java.awt.Frame;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JFrame;
import javax.swing.SwingWorker;
import mavn.controller.MavnControllerAbstract;
import mavn.controller.OutputControllerInterface;
import mavn.globals.Globals;
import mavn.model.InputModelInterface;
import mavn.observer.DartsObserver;
import mavn.observer.ResultsObserver;
import mavn.observer.TargetObserver;
import mavn.observer.ThetaObserver;
import mavn.observer.W0Observer;
import mavn.observer.W1Observer;
import mavn.observer.W2Observer;
import mavn.state.InputStateInterface;
import mavn.state.OutputStateInterface;
import org.math.plot.Plot2DPanel;
import util.components.BlinkerButton;
import util.components.InfiniteProgressPanel;

/**
 * The main View for the Mavn application.
 * @author Kaleb
 */
public class MavnView extends JFrame implements W2Observer, W1Observer, W0Observer, ThetaObserver, TargetObserver, ResultsObserver, DartsObserver
{
    // The collections of Modules used to manage the application.

    private ArrayList<InputControllerInterface> inputControllers;
    private ArrayList<InputModelInterface> inputModels;
    private ArrayList<InputStateInterface> inputStates;
    private ArrayList<OutputControllerInterface> outputControllers;
    private ArrayList<OutputStateInterface> outputStates;
    // Use to format the text in the text areas
    private DecimalFormat decFormat;
    // Used to open and save entire directories of files.
    private DirectoryController directoryController;
    // Used to entertain the user while the simulation runs.
    private InfiniteProgressPanel progressPanel;
    // The main Controller for the application.
    private MavnControllerAbstract controller;
    private String decimalFormat;

    /** Creates new form Main */
    public MavnView(MavnControllerAbstract controller)
    {
        inputControllers = new ArrayList<InputControllerInterface>();
        inputModels = new ArrayList<InputModelInterface>();
        inputStates = new ArrayList<InputStateInterface>();
        outputControllers = new ArrayList<OutputControllerInterface>();
        outputStates = new ArrayList<OutputStateInterface>();

        this.controller = controller;
        // Once the view has been instantiated, make sure the controller
        // has an instance
        this.controller.setView(this);
        this.controller.initDartGuns();
        this.controller.initInputModels();
        this.controller.initInputControllers();
        this.controller.initInputState();
        this.controller.initOutputController();
        this.controller.initOutputState();
        this.controller.initAlgorithms();
        // Make sure all of the input controllers have the right instances.
        this.controller.setInputControllersList(inputControllers);

        initComponents();

        initDecimalFormat();

        initModels();

        initControllers();

        initLabels();

        initState();

        progressPanel = new InfiniteProgressPanel();
        this.setGlassPane(progressPanel);

        this.setExtendedState(Frame.MAXIMIZED_BOTH);

        InputControllerInterface[] controllers =
        {
            inputControllers.get(Globals.TARGET_CONTROLLER), inputControllers.get(Globals.THETA_CONTROLLER), inputControllers.get(Globals.W0_CONTROLLER), inputControllers.get(Globals.W1_CONTROLLER), inputControllers.get(Globals.W2_CONTROLLER)
        };
        directoryController = new DirectoryController(controllers);
    }

    /**
     * Get the Clear Results Matrix Button. Clears the results from the
     * Results JTextArea.
     * @return the Clear Results Matrix Button.
     */
    public JButton getClearResultsMatrixButton()
    {
        return clearResultsMatrixButton;
    }

    /**
     * Get the Clear Target Matrix Button. Clears the results from the
     * Target JTextArea.
     * @return the Clear Target Matrix Button.
     */
    public JButton getClearTargetMatrixButton()
    {
        return clearTargetMatrixButton;
    }

    /**
     * Get the Clear Theta Matrix Button. Clears the results from the
     * Theta JTextArea.
     * @return the Clear Theta Matrix Button.
     */
    public JButton getClearThetaMatrixButton()
    {
        return clearThetaMatrixButton;
    }

    /**
     * Get the Clear W0 Matrix Button. Clears the results from the
     * W0 JTextArea.
     * @return the Clear W0 Matrix Button.
     */
    public JButton getClearW0MatrixButton()
    {
        return clearW0MatrixButton;
    }

    /**
     * Get the Clear W1 Matrix Button. Clears the results from the
     * W1 JTextArea.
     * @return the Clear W1 Matrix Button.
     */
    public JButton getClearW1MatrixButton()
    {
        return clearW1MatrixButton;
    }

    /**
     * Get the Clear W1 Matrix Button. Clears the results from the
     * W1 JTextArea.
     * @return the Clear W1 Matrix Button.
     */
    public JButton getClearW2MatrixButton()
    {
        return clearW2MatrixButton;
    }

    /**
     * Get the Edit Target Matrix Button. Edits the results from the
     * Target JTextArea.
     * @return the Edit Target Matrix Button.
     */
    public JButton getEditTargetMatrixButton()
    {
        return editTargetMatrixButton;
    }

    /**
     * Get the Edit Theta Matrix Button. Edits the results from the
     * Theta JTextArea.
     * @return the Edit Theta Matrix Button.
     */
    public JButton getEditThetaMatrixButton()
    {
        return editThetaMatrixButton;
    }

    /**
     * Get the Edit W0 Matrix Button. Edits the results from the
     * W0 JTextArea.
     * @return the Edit W0 Matrix Button.
     */
    public JButton getEditW0MatrixButton()
    {
        return editW0MatrixButton;
    }

    /**
     * Get the Edit W0 Matrix Button. Edits the results from the
     * W0 JTextArea.
     * @return the Edit W0 Matrix Button.
     */
    public JButton getEditW1MatrixButton()
    {
        return editW1MatrixButton;
    }

    /**
     * Get the Edit W2 Matrix Button. Edits the results from the
     * W2 JTextArea.
     * @return the Edit W2 Matrix Button.
     */
    public JButton getEditW2MatrixButton()
    {
        return editW2MatrixButton;
    }

    /**
     * Get the Import W2 Matrix Button. Imports the data for the model and the
     * W2 JTextArea.
     * @return the Import W2 Matrix Button.
     */
    public JButton getImportMatrixW2Button()
    {
        return importMatrixW2Button;
    }

    /**
     * Get the Import Target Matrix Button. Imports the data for the model and the
     * Target JTextArea.
     * @return the Import Target Matrix Button.
     */
    public JButton getImportTargetMatrixButton()
    {
        return importTargetMatrixButton;
    }

    /**
     * Get the Import Theta Matrix Button. Imports the data for the model and the
     * Theta JTextArea.
     * @return the Import Theta Matrix Button.
     */
    public JButton getImportThetaMatrixButton()
    {
        return importThetaMatrixButton;
    }

    /**
     * Get the Import W0 Matrix Button. Imports the data for the model and the
     * W0 JTextArea.
     * @return the Import W0 Matrix Button.
     */
    public JButton getImportW0MatrixButton()
    {
        return importW0MatrixButton;
    }

    /**
     * Get the Import W1 Matrix Button. Imports the data for the model and the
     * W1 JTextArea.
     * @return the Import W1 Matrix Button.
     */
    public JButton getImportW1MatrixButton()
    {
        return importW1MatrixButton;
    }

    /**
     * Get the New Target Matrix Button. Creates the data for the model and the
     * Target JTextArea.
     * @return the New Target Matrix Button.
     */
    public JButton getNewTargetMatrixButton()
    {
        return newTargetMatrixButton;
    }

    /**
     * Get the New Theta Matrix Button. Creates the data for the model and the
     * Theta JTextArea.
     * @return the New Theta Matrix Button.
     */
    public JButton getNewThetaMatrixButton()
    {
        return newThetaMatrixButton;
    }

    /**
     * Get the New W0 Matrix Button. Creates the data for the model and the
     * W0 JTextArea.
     * @return the New W0 Matrix Button.
     */
    public JButton getNewW0MatrixButton()
    {
        return newW0MatrixButton;
    }

    /**
     * Get the New W1 Matrix Button. Creates the data for the model and the
     * W1 JTextArea.
     * @return the New W1 Matrix Button.
     */
    public JButton getNewW1MatrixButton()
    {
        return newW1MatrixButton;
    }

    /**
     * Get the New W2 Matrix Button. Creates the data for the model and the
     * W2 JTextArea.
     * @return the New W2 Matrix Button.
     */
    public JButton getNewW2MatrixButton()
    {
        return newW2MatrixButton;
    }

    /**
     * Get the Output States collection.
     * @return the Output States collection.
     */
    public ArrayList<OutputStateInterface> getOutputStates()
    {
        return outputStates;
    }

    /**
     * Get the Properties Button. This button allows the user
     * to edit the properties of the application.
     * @return the Properties Button.
     */
    public JButton getPropertiesButton()
    {
        return propertiesButton;
    }

    /**
     * Get the Run Button. This button allows the user to run the simulation.
     * @return the Run Button.
     */
    public JButton getRunButton()
    {
        return runButton;
    }

    /**
     * Get the Save Results Matrix Button. Saves the data for the model and the
     * Results JTextArea.
     * @return the Save Results Matrix Button.
     */
    public JButton getSaveResultsButton()
    {
        return saveResultsButton;
    }

    /**
     * Get the Target Results Matrix Button. Saves the data for the model and the
     * Target JTextArea.
     * @return the Save Target Matrix Button.
     */
    public JButton getSaveTargetMatrixButton()
    {
        return saveTargetMatrixButton;
    }

    /**
     * Get the Theta Results Matrix Button. Saves the data for the model and the
     * Theta JTextArea.
     * @return the Save Theta Matrix Button.
     */
    public JButton getSaveThetaMatrixButton()
    {
        return saveThetaMatrixButton;
    }

    /**
     * Get the W0 Results Matrix Button. Saves the data for the model and the
     * W0 JTextArea.
     * @return the Save W0 Matrix Button.
     */
    public JButton getSaveW0MatrixButton()
    {
        return saveW0MatrixButton;
    }

    /**
     * Get the W1 Results Matrix Button. Saves the data for the model and the
     * W1 JTextArea.
     * @return the Save W1 Matrix Button.
     */
    public JButton getSaveW1MatrixButton()
    {
        return saveW1MatrixButton;
    }

    /**
     * Get the W2 Results Matrix Button. Saves the data for the model and the
     * W2 JTextArea.
     * @return the Save W2 Matrix Button.
     */
    public JButton getSaveW2MatrixButton()
    {
        return saveW2MatrixButton;
    }

    /**
     * Get the View Results Button. Allows the user to view the results of the
     * simulation.
     * @return the View Results Button.
     */
    public JButton getViewResultsButton()
    {
        return viewResultsButton;
    }

    /**
     * Update the results for the simulation.
     * @param hit the points of darts that hit a shape
     * @param miss the points of darts that missed a shape
     */
    @Override
    public void updateDartResults(ArrayList<double[][]> hit, ArrayList<double[][]> miss)
    {
        outputStates.get(Globals.RESULTS_STATE).setHit(hit);
        outputStates.get(Globals.RESULTS_STATE).setMiss(miss);
    }

    /**
     * Overload method to update the Results Matrix.
     * @param matrix the new matrix
     * @param description the new matrix description
     */
    @Override
    public void updateResultsMatrix(String results)
    {
        outputControllers.get(Globals.RESULTS_CONTROLLER).setResults(results);
        outputStates.get(Globals.RESULTS_STATE).run();
        resultsTextArea.append(results);
        algorithmWorker.cancel(true);
    }

    /**
     * Update the current Target Matrix.
     * @param matrix the new Target Matrix
     */
    @Override
    public void updateTargetMatrix(double[][] matrix)
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

        inputStates.get(Globals.TARGET_STATE).matrixLoaded();
        this.firePropertyChange("propertiesChanged", !inputStates.get(Globals.TARGET_STATE).isMatrixLoaded(), inputStates.get(Globals.TARGET_STATE).isMatrixLoaded());
        targetMatrixTextArea.setText(stringMatrix);
    }

    /**
     * Update the current Theta Matrix.
     * @param matrix the new Theta Matrix
     */
    @Override
    public void updateThetaMatrix(double[][] matrix)
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

        inputStates.get(Globals.THETA_STATE).matrixLoaded();
        this.firePropertyChange("propertiesChanged", !inputStates.get(Globals.THETA_STATE).isMatrixLoaded(), inputStates.get(Globals.THETA_STATE).isMatrixLoaded());
        jTextAreaMatrixTheta.setText(stringMatrix);
    }

    /**
     * Update the current W0 Matrix.
     * @param matrix the new W0 Matrix
     */
    @Override
    public void updateW0Matrix(double[][] matrix)
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

        inputStates.get(Globals.W0_STATE).matrixLoaded();
        this.firePropertyChange("propertiesChanged", !inputStates.get(Globals.W0_STATE).isMatrixLoaded(), inputStates.get(Globals.W0_STATE).isMatrixLoaded());
        w0MatrixTextArea.setText(stringMatrix);
    }

    /**
     * Update the current W1 Matrix.
     * @param matrix the new W1 Matrix
     */
    @Override
    public void updateW1Matrix(double[][] matrix)
    {
        // parse the rest into a string so it can be displayed
        String stringMatrix = "";
        for (int i = 0; i
                < matrix.length; i++)
        {
            for (int j = 0; j
                    < matrix[i].length; j++)
            {
                stringMatrix += decFormat.format(matrix[i][j]) + "\t";
            }

            stringMatrix += "\n";
        }
        inputStates.get(Globals.W1_STATE).matrixLoaded();
        this.firePropertyChange("propertiesChanged", !inputStates.get(Globals.W1_STATE).isMatrixLoaded(), inputStates.get(Globals.W1_STATE).isMatrixLoaded());
        w1MatrixTextArea.setText(stringMatrix);
    }

    /**
     * Update the current W2 Matrix.
     * @param matrix the new W2 Matrix
     */
    @Override
    public void updateW2Matrix(double[][] matrix)
    {
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

        inputStates.get(Globals.W2_STATE).matrixLoaded();
        this.firePropertyChange("propertiesChanged", !inputStates.get(Globals.W2_STATE).isMatrixLoaded(), inputStates.get(Globals.W2_STATE).isMatrixLoaded());
        w2MatrixTextArea.setText(stringMatrix);
    }

    private void initControllers()
    {
        for (Iterator<InputControllerInterface> iter = controller.getInputControllers(); iter.hasNext();)
        {
            inputControllers.add(iter.next());
        }
        for (Iterator<OutputControllerInterface> iter = controller.getOutputControllers(); iter.hasNext();)
        {
            outputControllers.add(iter.next());
        }
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
        jLabelHeaderTheta.setText("Theta Matrix:  " + "\u0398" + "2");
    }

    private void initModels()
    {
        for (Iterator<InputModelInterface> iter = controller.getInputModels(); iter.hasNext();)
        {
            inputModels.add(iter.next());
        }
    }

    private void initState()
    {
        for (Iterator<InputStateInterface> iter = controller.getInputStates(); iter.hasNext();)
        {
            inputStates.add(iter.next());
        }
        for (Iterator<InputStateInterface> iter = inputStates.iterator(); iter.hasNext();)
        {
            iter.next().matrixUnloaded();
        }
        for (Iterator<OutputStateInterface> iter = controller.getOutputStates(); iter.hasNext();)
        {
            outputStates.add(iter.next());
        }
        for (Iterator<OutputStateInterface> iter = outputStates.iterator(); iter.hasNext();)
        {
            iter.next().simulationUnloaded();
        }

    }
    private SwingWorker algorithmWorker = new SwingWorker<Void, Void>()
    {

        @Override
        public Void doInBackground()
        {
            progressPanel.start();
            outputControllers.get(Globals.RESULTS_CONTROLLER).runMavnAlgorithm();
            return null;
        }

        @Override
        public void done()
        {
            progressPanel.stop();
        }
    };

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
        w2MatrixTextArea = new javax.swing.JTextArea();
        jLabelHeaderW2 = new javax.swing.JLabel();
        importMatrixW2Button = new BlinkerButton("Import Matrix", "Blue");
        newW2MatrixButton = new BlinkerButton("New Matrix", "Green");
        jSeparator3 = new javax.swing.JSeparator();
        jLabel15 = new javax.swing.JLabel();
        editW2MatrixButton = new BlinkerButton("Edit Matrix", "Orange");
        saveW2MatrixButton = new BlinkerButton("Save Matrix", "Green");
        clearW2MatrixButton = new BlinkerButton("Clear Matrix", "Red");
        jPanelW0 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        w0MatrixTextArea = new javax.swing.JTextArea();
        jLabel22 = new javax.swing.JLabel();
        w0TextAreaLabel = new javax.swing.JLabel();
        importW0MatrixButton = new BlinkerButton("Import Matrix", "Blue");
        newW0MatrixButton = new BlinkerButton("New Matrix", "Green");
        jSeparator13 = new javax.swing.JSeparator();
        editW0MatrixButton = new BlinkerButton("Edit Matrix", "Orange");
        saveW0MatrixButton = new BlinkerButton("Save Matrix", "Blue");
        clearW0MatrixButton = new BlinkerButton("Clear Matrix", "Red");
        jPanelTheta = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaMatrixTheta = new javax.swing.JTextArea();
        jLabelHeaderTheta = new javax.swing.JLabel();
        importThetaMatrixButton = new BlinkerButton("Import Matrix", "Blue");
        newThetaMatrixButton = new BlinkerButton("New Matrix", "Green");
        jSeparator4 = new javax.swing.JSeparator();
        jLabel16 = new javax.swing.JLabel();
        editThetaMatrixButton = new BlinkerButton("Edit Matrix", "Orange");
        saveThetaMatrixButton = new BlinkerButton("Save Matrix", "Blue");
        clearThetaMatrixButton = new BlinkerButton("Clear Matrix", "Red");
        jPanelResults = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        resultsTextArea = new javax.swing.JTextArea();
        jLabel29 = new javax.swing.JLabel();
        jSeparator11 = new javax.swing.JSeparator();
        runButton = new BlinkerButton("Run!", "Blue");
        jLabelResults = new javax.swing.JLabel();
        saveResultsButton = new BlinkerButton("Save Result", "Blue");
        clearResultsMatrixButton = new BlinkerButton("Clear Results", "Red");
        propertiesButton = new BlinkerButton("Properties", "Green");
        viewResultsButton = new BlinkerButton("View Results", "Orange");
        jPanelTargets = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        targetMatrixTextArea = new javax.swing.JTextArea();
        importTargetMatrixButton = new BlinkerButton("Import Matrix", "Blue");
        jSeparator7 = new javax.swing.JSeparator();
        jLabel17 = new javax.swing.JLabel();
        newTargetMatrixButton = new BlinkerButton("New Matrix", "Green");
        targetMatrixLabel = new javax.swing.JLabel();
        editTargetMatrixButton = new BlinkerButton("Edit Matrix", "Orange");
        saveTargetMatrixButton = new BlinkerButton("Save Matrix", "Blue");
        clearTargetMatrixButton = new BlinkerButton("Clear Matrix", "Red");
        jPanelW1 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        importW1MatrixButton = new BlinkerButton("Import Matrix", "Blue");
        jScrollPane5 = new javax.swing.JScrollPane();
        w1MatrixTextArea = new javax.swing.JTextArea();
        newW1MatrixButton = new BlinkerButton("New Matrix", "Green");
        w1TextAreaLabel = new javax.swing.JLabel();
        jSeparator9 = new javax.swing.JSeparator();
        editW1MatrixButton = new BlinkerButton("Edit Matrix", "Orange");
        saveW1MatrixButton = new BlinkerButton("Save Matrix", "Blue");
        clearW1MatrixButton = new BlinkerButton("Clear Matrix", "Red");
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItemImportModel = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                formPropertyChange(evt);
            }
        });

        mainScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        mainScrollPane.setMaximumSize(new java.awt.Dimension(1200, 1000));

        mainPanel.setMaximumSize(new java.awt.Dimension(1200, 1000));
        mainPanel.setPreferredSize(new java.awt.Dimension(1431, 1000));

        jPanelW2.setPreferredSize(new java.awt.Dimension(650, 215));

        w2MatrixTextArea.setColumns(20);
        w2MatrixTextArea.setRows(5);
        jScrollPane1.setViewportView(w2MatrixTextArea);

        jLabelHeaderW2.setFont(new java.awt.Font("Tahoma", 1, 11));
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
        jLabel15.setText("W2 Matrix: Defines Shape Vector Directions");

        editW2MatrixButton.setText("Edit Matrix");
        editW2MatrixButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editW2MatrixButtonActionPerformed(evt);
            }
        });

        saveW2MatrixButton.setText("Save Matrix");
        saveW2MatrixButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveW2MatrixButtonActionPerformed(evt);
            }
        });

        clearW2MatrixButton.setText("Clear Matrix");
        clearW2MatrixButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearW2MatrixButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelW2Layout = new javax.swing.GroupLayout(jPanelW2);
        jPanelW2.setLayout(jPanelW2Layout);
        jPanelW2Layout.setHorizontalGroup(
            jPanelW2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelW2Layout.createSequentialGroup()
                .addContainerGap()
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
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING))
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
                .addGap(26, 26, 26)
                .addComponent(jSeparator3, javax.swing.GroupLayout.DEFAULT_SIZE, 13, Short.MAX_VALUE))
        );

        jPanelW0.setPreferredSize(new java.awt.Dimension(650, 215));

        w0MatrixTextArea.setColumns(20);
        w0MatrixTextArea.setRows(5);
        jScrollPane6.setViewportView(w0MatrixTextArea);

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel22.setText("Weight Matrix: W0");

        w0TextAreaLabel.setFont(new java.awt.Font("Tahoma", 3, 11));
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

        editW0MatrixButton.setText("Edit Matrix");
        editW0MatrixButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editW0MatrixButtonActionPerformed(evt);
            }
        });

        saveW0MatrixButton.setText("Save Matrix");
        saveW0MatrixButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveW0MatrixButtonActionPerformed(evt);
            }
        });

        clearW0MatrixButton.setText("Clear Matrix");
        clearW0MatrixButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearW0MatrixButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelW0Layout = new javax.swing.GroupLayout(jPanelW0);
        jPanelW0.setLayout(jPanelW0Layout);
        jPanelW0Layout.setHorizontalGroup(
            jPanelW0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelW0Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelW0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(w0TextAreaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelW0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel22)
                        .addComponent(jScrollPane6)
                        .addGroup(jPanelW0Layout.createSequentialGroup()
                            .addComponent(newW0MatrixButton)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(importW0MatrixButton)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(editW0MatrixButton)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(saveW0MatrixButton)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(clearW0MatrixButton))
                        .addComponent(jSeparator13)))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(jSeparator13, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTextAreaMatrixTheta.setColumns(20);
        jTextAreaMatrixTheta.setRows(5);
        jScrollPane2.setViewportView(jTextAreaMatrixTheta);

        jLabelHeaderTheta.setFont(new java.awt.Font("Tahoma", 1, 11));
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

        jLabel16.setFont(new java.awt.Font("Tahoma", 3, 11));
        jLabel16.setText("Theta Matrix: Defines Vector Boundaries");

        editThetaMatrixButton.setText("Edit Matrix");
        editThetaMatrixButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editThetaMatrixButtonActionPerformed(evt);
            }
        });

        saveThetaMatrixButton.setText("Save Matrix");
        saveThetaMatrixButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveThetaMatrixButtonActionPerformed(evt);
            }
        });

        clearThetaMatrixButton.setText("Clear Matrix");
        clearThetaMatrixButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearThetaMatrixButtonActionPerformed(evt);
            }
        });

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
                    .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING))
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
                .addGap(26, 26, 26)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54))
        );

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel28.setText("Run Simulator:");

        resultsTextArea.setColumns(20);
        resultsTextArea.setRows(5);
        jScrollPane7.setViewportView(resultsTextArea);

        jLabel29.setFont(new java.awt.Font("Tahoma", 3, 11));
        jLabel29.setText("Results: The Outcomes of the Simulation");

        runButton.setText("Run!");
        runButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                runButtonActionPerformed(evt);
            }
        });

        saveResultsButton.setText("Save Result");
        saveResultsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveResultsButtonActionPerformed(evt);
            }
        });

        clearResultsMatrixButton.setText("Clear Results");
        clearResultsMatrixButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearResultsMatrixButtonActionPerformed(evt);
            }
        });

        propertiesButton.setText(" Properties");
        propertiesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                propertiesButtonActionPerformed(evt);
            }
        });

        viewResultsButton.setText("View Results");
        viewResultsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewResultsButtonActionPerformed(evt);
            }
        });

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
                        .addComponent(propertiesButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(runButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(viewResultsButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(saveResultsButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(clearResultsMatrixButton))
                    .addComponent(jSeparator11, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel29, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap(79, Short.MAX_VALUE))
        );

        jPanelResultsLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {clearResultsMatrixButton, propertiesButton, runButton, saveResultsButton, viewResultsButton});

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
                    .addComponent(propertiesButton)
                    .addComponent(runButton)
                    .addComponent(viewResultsButton)
                    .addComponent(saveResultsButton)
                    .addComponent(clearResultsMatrixButton))
                .addGap(18, 18, 18)
                .addComponent(jLabel29)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator11, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        targetMatrixTextArea.setColumns(20);
        targetMatrixTextArea.setRows(5);
        jScrollPane3.setViewportView(targetMatrixTextArea);

        importTargetMatrixButton.setText("Import Matrix");
        importTargetMatrixButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                importTargetMatrixButtonActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Tahoma", 3, 11));
        jLabel17.setText("Current Targets: Defines Node Inputs");

        newTargetMatrixButton.setText("New Matrix");
        newTargetMatrixButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newTargetMatrixButtonActionPerformed(evt);
            }
        });

        targetMatrixLabel.setFont(new java.awt.Font("Tahoma", 1, 11));
        targetMatrixLabel.setText("Target Marix:");

        editTargetMatrixButton.setText("Edit Matrix");
        editTargetMatrixButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editTargetMatrixButtonActionPerformed(evt);
            }
        });

        saveTargetMatrixButton.setText("Save Matrix");
        saveTargetMatrixButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveTargetMatrixButtonActionPerformed(evt);
            }
        });

        clearTargetMatrixButton.setText("Clear Matrix");
        clearTargetMatrixButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearTargetMatrixButtonActionPerformed(evt);
            }
        });

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

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel18.setText("Weight Matrix: W1");

        importW1MatrixButton.setText("Import Matrix");
        importW1MatrixButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                importW1MatrixButtonActionPerformed(evt);
            }
        });

        w1MatrixTextArea.setColumns(20);
        w1MatrixTextArea.setRows(5);
        w1MatrixTextArea.setPreferredSize(new java.awt.Dimension(165, 120));
        w1MatrixTextArea.setRequestFocusEnabled(false);
        jScrollPane5.setViewportView(w1MatrixTextArea);

        newW1MatrixButton.setText("New Matrix");
        newW1MatrixButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newW1MatrixButtonActionPerformed(evt);
            }
        });

        w1TextAreaLabel.setFont(new java.awt.Font("Tahoma", 3, 11));
        w1TextAreaLabel.setText("W1 Matrix: Defines ANDing Node Connections");

        editW1MatrixButton.setText("Edit Matrix");
        editW1MatrixButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editW1MatrixButtonActionPerformed(evt);
            }
        });

        saveW1MatrixButton.setText("Save Matrix");
        saveW1MatrixButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveW1MatrixButtonActionPerformed(evt);
            }
        });

        clearW1MatrixButton.setText("Clear Matrix");
        clearW1MatrixButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearW1MatrixButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelW1Layout = new javax.swing.GroupLayout(jPanelW1);
        jPanelW1.setLayout(jPanelW1Layout);
        jPanelW1Layout.setHorizontalGroup(
            jPanelW1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelW1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelW1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18)
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
                            .addComponent(clearW1MatrixButton)))
                    .addComponent(w1TextAreaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addGap(27, 27, 27)
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
                    .addComponent(jPanelResults, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
        inputControllers.get(Globals.W2_CONTROLLER).importMatrix();
    }//GEN-LAST:event_importMatrixW2ButtonActionPerformed
    private void newThetaMatrixButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newThetaMatrixButtonActionPerformed
        inputControllers.get(Globals.THETA_CONTROLLER).newMatrix();

    }//GEN-LAST:event_newThetaMatrixButtonActionPerformed
    private void importTargetMatrixButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_importTargetMatrixButtonActionPerformed
        inputControllers.get(Globals.TARGET_CONTROLLER).importMatrix();

    }//GEN-LAST:event_importTargetMatrixButtonActionPerformed
    private void importW1MatrixButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_importW1MatrixButtonActionPerformed
    {//GEN-HEADEREND:event_importW1MatrixButtonActionPerformed
        inputControllers.get(Globals.W1_CONTROLLER).importMatrix();
    }//GEN-LAST:event_importW1MatrixButtonActionPerformed
    private void newW1MatrixButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_newW1MatrixButtonActionPerformed
    {//GEN-HEADEREND:event_newW1MatrixButtonActionPerformed
        inputControllers.get(Globals.W1_CONTROLLER).newMatrix();
    }//GEN-LAST:event_newW1MatrixButtonActionPerformed
    private void importW0MatrixButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_importW0MatrixButtonActionPerformed
    {//GEN-HEADEREND:event_importW0MatrixButtonActionPerformed
        inputControllers.get(Globals.W0_CONTROLLER).importMatrix();

    }//GEN-LAST:event_importW0MatrixButtonActionPerformed
    private void importThetaMatrixButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_importThetaMatrixButtonActionPerformed
    {//GEN-HEADEREND:event_importThetaMatrixButtonActionPerformed
        inputControllers.get(Globals.THETA_CONTROLLER).importMatrix();
}//GEN-LAST:event_importThetaMatrixButtonActionPerformed
    private void runButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_runButtonActionPerformed
    {//GEN-HEADEREND:event_runButtonActionPerformed
        algorithmWorker.execute();
    }//GEN-LAST:event_runButtonActionPerformed
    private void jMenuItemImportModelActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jMenuItemImportModelActionPerformed
    {//GEN-HEADEREND:event_jMenuItemImportModelActionPerformed

        directoryController.getDirectoryChooser();
    }//GEN-LAST:event_jMenuItemImportModelActionPerformed
    private void newW2MatrixButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_newW2MatrixButtonActionPerformed
    {//GEN-HEADEREND:event_newW2MatrixButtonActionPerformed
        inputControllers.get(Globals.W2_CONTROLLER).newMatrix();
    }//GEN-LAST:event_newW2MatrixButtonActionPerformed
    private void newW0MatrixButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_newW0MatrixButtonActionPerformed
    {//GEN-HEADEREND:event_newW0MatrixButtonActionPerformed
        inputControllers.get(Globals.W0_CONTROLLER).newMatrix();

    }//GEN-LAST:event_newW0MatrixButtonActionPerformed
    private void newTargetMatrixButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_newTargetMatrixButtonActionPerformed
    {//GEN-HEADEREND:event_newTargetMatrixButtonActionPerformed
        inputControllers.get(Globals.TARGET_CONTROLLER).newMatrix();
    }//GEN-LAST:event_newTargetMatrixButtonActionPerformed
    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jMenuItem1ActionPerformed
    {//GEN-HEADEREND:event_jMenuItem1ActionPerformed
        //controller.importMatrix();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void editW2MatrixButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_editW2MatrixButtonActionPerformed
    {//GEN-HEADEREND:event_editW2MatrixButtonActionPerformed
        inputControllers.get(Globals.W2_CONTROLLER).editMatrix();
    }//GEN-LAST:event_editW2MatrixButtonActionPerformed
    private void editThetaMatrixButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_editThetaMatrixButtonActionPerformed
    {//GEN-HEADEREND:event_editThetaMatrixButtonActionPerformed
        inputControllers.get(Globals.THETA_CONTROLLER).editMatrix();
    }//GEN-LAST:event_editThetaMatrixButtonActionPerformed
    private void editW1MatrixButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_editW1MatrixButtonActionPerformed
    {//GEN-HEADEREND:event_editW1MatrixButtonActionPerformed
        inputControllers.get(Globals.W1_CONTROLLER).editMatrix();
    }//GEN-LAST:event_editW1MatrixButtonActionPerformed
    private void editTargetMatrixButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_editTargetMatrixButtonActionPerformed
    {//GEN-HEADEREND:event_editTargetMatrixButtonActionPerformed
        inputControllers.get(Globals.TARGET_CONTROLLER).editMatrix();
    }//GEN-LAST:event_editTargetMatrixButtonActionPerformed
    private void editW0MatrixButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_editW0MatrixButtonActionPerformed
    {//GEN-HEADEREND:event_editW0MatrixButtonActionPerformed
        inputControllers.get(Globals.W0_CONTROLLER).editMatrix();
    }//GEN-LAST:event_editW0MatrixButtonActionPerformed
    private void propertiesButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_propertiesButtonActionPerformed
    {//GEN-HEADEREND:event_propertiesButtonActionPerformed
        PropertiesFrame propertiesFrame = new PropertiesFrame(this);
    }//GEN-LAST:event_propertiesButtonActionPerformed
    private void formPropertyChange(java.beans.PropertyChangeEvent evt)//GEN-FIRST:event_formPropertyChange
    {//GEN-HEADEREND:event_formPropertyChange
        if (evt.getPropertyName().equals("propertiesChanged"))
        {
            if (inputStates.get(Globals.W2_STATE).isMatrixLoaded() && inputStates.get(Globals.W1_STATE).isMatrixLoaded() && inputStates.get(Globals.W0_STATE).isMatrixLoaded()
                    && inputStates.get(Globals.THETA_STATE).isMatrixLoaded())
            {
                outputStates.get(Globals.RESULTS_STATE).simulationLoaded();
            }
        }
    }//GEN-LAST:event_formPropertyChange

    private void viewResultsButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_viewResultsButtonActionPerformed
    {//GEN-HEADEREND:event_viewResultsButtonActionPerformed

        Iterator hits = outputStates.get(Globals.RESULTS_STATE).getHit().iterator();
        double[] xHit = new double[outputStates.get(Globals.RESULTS_STATE).getHit().size()];
        double[] yHit = new double[outputStates.get(Globals.RESULTS_STATE).getHit().size()];
        Iterator misses = outputStates.get(Globals.RESULTS_STATE).getMiss().iterator();
        double[] xMisses = new double[outputStates.get(Globals.RESULTS_STATE).getMiss().size()];
        double[] yMisses = new double[outputStates.get(Globals.RESULTS_STATE).getMiss().size()];

        int hitCount = 0;

        while (hits.hasNext())
        {
            double[][] tempHit = (double[][]) hits.next();

            xHit[hitCount] = tempHit[0][0];
            yHit[hitCount] = tempHit[1][0];
            hitCount++;

        }
        int missCount = 0;

        while (misses.hasNext())
        {
            double[][] tempHit = (double[][]) misses.next();
            xMisses[missCount] = tempHit[0][0];
            yMisses[missCount] = tempHit[1][0];
            missCount++;

        }
        Plot2DPanel plot = new Plot2DPanel();
        plot.addScatterPlot("Hit", Color.red, xHit, yHit);
        plot.addScatterPlot("Miss", Color.black, xMisses, yMisses);
        JFrame frame = new JFrame("a plot panel");
        frame.setSize(400, 400);
        frame.setContentPane(plot);
        frame.setVisible(true);
    }//GEN-LAST:event_viewResultsButtonActionPerformed
    private void clearW2MatrixButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_clearW2MatrixButtonActionPerformed
    {//GEN-HEADEREND:event_clearW2MatrixButtonActionPerformed
        w2MatrixTextArea.setText("");
        inputControllers.get(Globals.W2_CONTROLLER).setMatrixSet(false);
        inputStates.get(Globals.W2_STATE).matrixUnloaded();
    }//GEN-LAST:event_clearW2MatrixButtonActionPerformed
    private void clearThetaMatrixButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_clearThetaMatrixButtonActionPerformed
    {//GEN-HEADEREND:event_clearThetaMatrixButtonActionPerformed
        jTextAreaMatrixTheta.setText("");
        inputControllers.get(Globals.THETA_STATE).setMatrixSet(false);
        inputStates.get(Globals.THETA_STATE).matrixUnloaded();
    }//GEN-LAST:event_clearThetaMatrixButtonActionPerformed
    private void clearW1MatrixButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_clearW1MatrixButtonActionPerformed
    {//GEN-HEADEREND:event_clearW1MatrixButtonActionPerformed
        w1MatrixTextArea.setText("");
        inputControllers.get(Globals.W1_STATE).setMatrixSet(false);
        inputStates.get(Globals.W1_STATE).matrixUnloaded();
    }//GEN-LAST:event_clearW1MatrixButtonActionPerformed
    private void clearW0MatrixButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_clearW0MatrixButtonActionPerformed
    {//GEN-HEADEREND:event_clearW0MatrixButtonActionPerformed
        w0MatrixTextArea.setText("");
        inputControllers.get(Globals.W0_STATE).setMatrixSet(false);
        inputStates.get(Globals.W0_STATE).matrixUnloaded();
    }//GEN-LAST:event_clearW0MatrixButtonActionPerformed
    private void clearTargetMatrixButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_clearTargetMatrixButtonActionPerformed
    {//GEN-HEADEREND:event_clearTargetMatrixButtonActionPerformed
        targetMatrixTextArea.setText("");
        inputControllers.get(Globals.TARGET_STATE).setMatrixSet(false);
        inputStates.get(Globals.TARGET_STATE).matrixUnloaded();
    }//GEN-LAST:event_clearTargetMatrixButtonActionPerformed
    private void clearResultsMatrixButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_clearResultsMatrixButtonActionPerformed
    {//GEN-HEADEREND:event_clearResultsMatrixButtonActionPerformed
        resultsTextArea.setText("");
        outputStates.get(Globals.RESULTS_STATE).simulationLoaded();
        outputStates.get(Globals.RESULTS_STATE).propertiesLoaded();
    }//GEN-LAST:event_clearResultsMatrixButtonActionPerformed

    private void saveW2MatrixButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_saveW2MatrixButtonActionPerformed
    {//GEN-HEADEREND:event_saveW2MatrixButtonActionPerformed
        inputControllers.get(Globals.W2_CONTROLLER).saveMatrix();
    }//GEN-LAST:event_saveW2MatrixButtonActionPerformed

    private void saveThetaMatrixButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_saveThetaMatrixButtonActionPerformed
    {//GEN-HEADEREND:event_saveThetaMatrixButtonActionPerformed
        inputControllers.get(Globals.THETA_CONTROLLER).saveMatrix();
    }//GEN-LAST:event_saveThetaMatrixButtonActionPerformed

    private void saveW1MatrixButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_saveW1MatrixButtonActionPerformed
    {//GEN-HEADEREND:event_saveW1MatrixButtonActionPerformed
        inputControllers.get(Globals.W1_CONTROLLER).saveMatrix();
    }//GEN-LAST:event_saveW1MatrixButtonActionPerformed

    private void saveW0MatrixButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_saveW0MatrixButtonActionPerformed
    {//GEN-HEADEREND:event_saveW0MatrixButtonActionPerformed
        inputControllers.get(Globals.W0_CONTROLLER).saveMatrix();
    }//GEN-LAST:event_saveW0MatrixButtonActionPerformed

    private void saveTargetMatrixButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_saveTargetMatrixButtonActionPerformed
    {//GEN-HEADEREND:event_saveTargetMatrixButtonActionPerformed
        inputControllers.get(Globals.W2_CONTROLLER).saveMatrix();
    }//GEN-LAST:event_saveTargetMatrixButtonActionPerformed

    private void saveResultsButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_saveResultsButtonActionPerformed
    {//GEN-HEADEREND:event_saveResultsButtonActionPerformed
        outputControllers.get(Globals.RESULTS_CONTROLLER).saveResults();
    }//GEN-LAST:event_saveResultsButtonActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton clearResultsMatrixButton;
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
    private javax.swing.JTextArea jTextAreaMatrixTheta;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JScrollPane mainScrollPane;
    private javax.swing.JButton newTargetMatrixButton;
    private javax.swing.JButton newThetaMatrixButton;
    private javax.swing.JButton newW0MatrixButton;
    private javax.swing.JButton newW1MatrixButton;
    private javax.swing.JButton newW2MatrixButton;
    private javax.swing.JButton propertiesButton;
    private javax.swing.JTextArea resultsTextArea;
    private javax.swing.JButton runButton;
    private javax.swing.JButton saveResultsButton;
    private javax.swing.JButton saveTargetMatrixButton;
    private javax.swing.JButton saveThetaMatrixButton;
    private javax.swing.JButton saveW0MatrixButton;
    private javax.swing.JButton saveW1MatrixButton;
    private javax.swing.JButton saveW2MatrixButton;
    private javax.swing.JLabel targetMatrixLabel;
    private javax.swing.JTextArea targetMatrixTextArea;
    private javax.swing.JButton viewResultsButton;
    private javax.swing.JTextArea w0MatrixTextArea;
    private javax.swing.JLabel w0TextAreaLabel;
    private javax.swing.JTextArea w1MatrixTextArea;
    private javax.swing.JLabel w1TextAreaLabel;
    private javax.swing.JTextArea w2MatrixTextArea;
    // End of variables declaration//GEN-END:variables
}
