/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package whiteboxgis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import javax.swing.AbstractButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import whitebox.interfaces.WhiteboxPlugin;
import whitebox.interfaces.WhiteboxPluginHost;
import whitebox.parallel.Parallel;

/**
 *
 * @author Bill Gardner <wgardner@socs.uoguelph.ca>
 */
public class TimingProfiler extends javax.swing.JFrame {

    // This is the no. of processors ("threads") that a tool with adjustable
    // parallelism should use.  It defaults to the max. no. available.
    private static int useProcessors = Runtime.getRuntime().availableProcessors();
    
    WhiteboxPluginHost host;    // initialized by constructor
    
    // This describes the tool currently being timed
    WhiteboxPlugin plugin;
    String[] pluginArgs;
    long pluginStart;   // nanosecs
    
    // Times and corresponding text fields
    long[] times;
    ArrayList<JTextField> fields;
    
    /**
     * Creates TimingProfiler window
     * 
     * @param host Parent frame
     */
    public TimingProfiler(WhiteboxPluginHost host) {
        // want to use icon WhiteboxGIS/resources/Images/timer_clock.png
        
        this.host = host;
        
        initComponents();
        jPanel1.setVisible(true);
        jPanel2.setVisible(true);
        jPanel3.setVisible(true);
        jPanel4.setVisible(true);
        this.setVisible(true);
        
        // collect the text fields into an array so it's easy to update times
        fields = new ArrayList<JTextField>();
        fields.add(jTextField1);
        fields.add(jTextField2);
        fields.add(jTextField3);
        fields.add(jTextField4);
        fields.add(jTextField5);
        fields.add(jTextField6);
        fields.add(jTextField7);
        fields.add(jTextField8);
        fields.add(jTextField9);
        fields.add(jTextField10);
        fields.add(jTextField11);
        fields.add(jTextField12);
        fields.add(jTextField13);
        fields.add(jTextField14);
        fields.add(jTextField15);
        fields.add(jTextField16);
        times = new long[fields.size()];
        
        // Go through the radio buttons, set as the default the one equal to
        // nprocs, and hide the remaining ones > nprocs, and their text fields
        // Problems:
        //      Hardcoded to 16; suppose nprocs > 16??
        int n = 0;
        for (Enumeration<AbstractButton> bg = selectProcsGrp.getElements(); bg.hasMoreElements(); ) {
            n++;
            JRadioButton b = (JRadioButton) bg.nextElement();
            if (n==useProcessors) {
                selectProcsGrp.setSelected(b.getModel(), true);
            } else if (n>useProcessors) {
                b.setVisible(false);
                fields.get(n-1).setVisible(false);
            }
        }
        
        this.pack();
    }
    
    /**
     * Start a record for timing a particular plugin with an array of arguments.
     * Calling reportTiming() will cause the Timing Profiler to display the results,
     * and it can rerun the plugin with the same arguments, typically with a
     * different no. of processors.
     * 
     * @param plugin Plugin whose name was given to WhiteboxGUI.runPlugin()
     * @param args Array of arguments given to WhiteboxGUI.runPlugin()
     */
    public void startTiming( WhiteboxPlugin plugin, String[] args ) {
        
        // find which radio button is currently clicked
        int n = 0;
        for (Enumeration<AbstractButton> bg = selectProcsGrp.getElements(); bg.hasMoreElements(); ) {
            n++;
            JRadioButton b = (JRadioButton) bg.nextElement();
            if (b.getModel().isSelected()) break;
        }
        useProcessors = n;  // do I even need useProcessors, or is it redundant?
        
        // update no. of processors so plugin can obtain it
        Parallel.setPluginProcessors(useProcessors);
        
        // remember the name and args for stopTiming() and rerunTool button
        this.host = host;
        this.plugin = plugin;
        this.pluginArgs = args;
        
        // force garbage collection so it doesn't occur during timing run
        System.gc();
        
        // very last thing, capture current time
        this.pluginStart = System.nanoTime();
    }
    
    /**
     * Report the run time of the plugin for which startTiming() was called.
     * 
     * @param nanosecs Wallclock execution time of plugin in nanoseconds.
     */
    public void stopTiming() {
        
        long execTime = System.nanoTime() - pluginStart;
        float execSecs = (float) ((float)(execTime/100000000)/10.0);
        
        // NOTE: defer this to Java 8, when WhiteboxPlugin interface can have
        //  a default implementation of "none".  We want the method to be in
        //  the interface, but we don't want to force all the old serial
        //  plugins to implement it.
        //
        // interrogate the plugin to find out its style of parallelism
        // plugin.getParallelism();
        
        // store results for current no. of processors
        times[useProcessors-1] = execTime;
        fields.get(useProcessors-1).setText(String.format("%.1f", execSecs));
        
        // format results in scrollable text area
        log.append(String.format("Tool name: %s%n" +
                    "Arguments: %s%n" +
                    "Parallelism: %s%n" +
                    "No. processors: %d%n" +
                    "Execution time (sec): %.1f%n%n",
                    plugin.getName(),
                    Arrays.toString(pluginArgs),
                    "(unknown)",
                    useProcessors,  // or Parallel.getPluginProcessors()
                    execSecs));
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        selectProcsGrp = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        procBtn1 = new javax.swing.JRadioButton();
        jTextField1 = new javax.swing.JTextField();
        procBtn2 = new javax.swing.JRadioButton();
        jTextField2 = new javax.swing.JTextField();
        procBtn3 = new javax.swing.JRadioButton();
        jTextField3 = new javax.swing.JTextField();
        procBtn4 = new javax.swing.JRadioButton();
        jTextField4 = new javax.swing.JTextField();
        procBtn5 = new javax.swing.JRadioButton();
        jTextField5 = new javax.swing.JTextField();
        procBtn6 = new javax.swing.JRadioButton();
        jTextField6 = new javax.swing.JTextField();
        procBtn7 = new javax.swing.JRadioButton();
        jTextField7 = new javax.swing.JTextField();
        procBtn8 = new javax.swing.JRadioButton();
        jTextField8 = new javax.swing.JTextField();
        procBtn9 = new javax.swing.JRadioButton();
        jTextField9 = new javax.swing.JTextField();
        procBtn10 = new javax.swing.JRadioButton();
        jTextField10 = new javax.swing.JTextField();
        procBtn11 = new javax.swing.JRadioButton();
        jTextField11 = new javax.swing.JTextField();
        procBtn12 = new javax.swing.JRadioButton();
        jTextField12 = new javax.swing.JTextField();
        procBtn13 = new javax.swing.JRadioButton();
        jTextField13 = new javax.swing.JTextField();
        procBtn14 = new javax.swing.JRadioButton();
        jTextField14 = new javax.swing.JTextField();
        procBtn15 = new javax.swing.JRadioButton();
        jTextField15 = new javax.swing.JTextField();
        procBtn16 = new javax.swing.JRadioButton();
        jTextField16 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        copyToLogButton = new javax.swing.JButton();
        clearTimesButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        log = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        deleteLastReportButton = new javax.swing.JButton();
        saveLogToFileButton = new javax.swing.JButton();
        clearLogButton = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        rerunToolButton = new javax.swing.JButton();
        closeButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Timing Profiler");
        setMinimumSize(new java.awt.Dimension(800, 500));
        setPreferredSize(new java.awt.Dimension(800, 419));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setMinimumSize(new java.awt.Dimension(300, 300));
        jPanel1.setName(""); // NOI18N
        jPanel1.setPreferredSize(new java.awt.Dimension(300, 100));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("Procs");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel1.add(jLabel1, gridBagConstraints);

        jLabel2.setText("Time (s)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel1.add(jLabel2, gridBagConstraints);

        selectProcsGrp.add(procBtn1);
        procBtn1.setText("1");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel1.add(procBtn1, gridBagConstraints);

        jTextField1.setEditable(false);
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField1.setText("0.0");
        jTextField1.setPreferredSize(new java.awt.Dimension(80, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 74;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel1.add(jTextField1, gridBagConstraints);

        selectProcsGrp.add(procBtn2);
        procBtn2.setText("2");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel1.add(procBtn2, gridBagConstraints);

        jTextField2.setEditable(false);
        jTextField2.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField2.setText("0.0");
        jTextField2.setPreferredSize(new java.awt.Dimension(80, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 74;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel1.add(jTextField2, gridBagConstraints);

        selectProcsGrp.add(procBtn3);
        procBtn3.setText("3");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel1.add(procBtn3, gridBagConstraints);

        jTextField3.setEditable(false);
        jTextField3.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField3.setText("0.0");
        jTextField3.setPreferredSize(new java.awt.Dimension(80, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.ipadx = 74;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel1.add(jTextField3, gridBagConstraints);

        selectProcsGrp.add(procBtn4);
        procBtn4.setText("4");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel1.add(procBtn4, gridBagConstraints);

        jTextField4.setEditable(false);
        jTextField4.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField4.setText("0.0");
        jTextField4.setPreferredSize(new java.awt.Dimension(80, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.ipadx = 74;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel1.add(jTextField4, gridBagConstraints);

        selectProcsGrp.add(procBtn5);
        procBtn5.setText("5");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel1.add(procBtn5, gridBagConstraints);

        jTextField5.setEditable(false);
        jTextField5.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField5.setText("0.0");
        jTextField5.setPreferredSize(new java.awt.Dimension(80, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 74;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel1.add(jTextField5, gridBagConstraints);

        selectProcsGrp.add(procBtn6);
        procBtn6.setText("6");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel1.add(procBtn6, gridBagConstraints);

        jTextField6.setEditable(false);
        jTextField6.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField6.setText("0.0");
        jTextField6.setPreferredSize(new java.awt.Dimension(80, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 74;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel1.add(jTextField6, gridBagConstraints);

        selectProcsGrp.add(procBtn7);
        procBtn7.setText("7");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel1.add(procBtn7, gridBagConstraints);

        jTextField7.setEditable(false);
        jTextField7.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField7.setText("0.0");
        jTextField7.setPreferredSize(new java.awt.Dimension(80, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.ipadx = 74;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel1.add(jTextField7, gridBagConstraints);

        selectProcsGrp.add(procBtn8);
        procBtn8.setText("8");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel1.add(procBtn8, gridBagConstraints);

        jTextField8.setEditable(false);
        jTextField8.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField8.setText("0.0");
        jTextField8.setPreferredSize(new java.awt.Dimension(80, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.ipadx = 74;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel1.add(jTextField8, gridBagConstraints);

        selectProcsGrp.add(procBtn9);
        procBtn9.setText("9  ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel1.add(procBtn9, gridBagConstraints);

        jTextField9.setEditable(false);
        jTextField9.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField9.setText("0.0");
        jTextField9.setPreferredSize(new java.awt.Dimension(80, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 74;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel1.add(jTextField9, gridBagConstraints);

        selectProcsGrp.add(procBtn10);
        procBtn10.setText("10");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel1.add(procBtn10, gridBagConstraints);

        jTextField10.setEditable(false);
        jTextField10.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField10.setText("0.0");
        jTextField10.setPreferredSize(new java.awt.Dimension(80, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 74;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel1.add(jTextField10, gridBagConstraints);

        selectProcsGrp.add(procBtn11);
        procBtn11.setText("11");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel1.add(procBtn11, gridBagConstraints);

        jTextField11.setEditable(false);
        jTextField11.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField11.setText("0.0");
        jTextField11.setPreferredSize(new java.awt.Dimension(80, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.ipadx = 74;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel1.add(jTextField11, gridBagConstraints);

        selectProcsGrp.add(procBtn12);
        procBtn12.setText("12");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel1.add(procBtn12, gridBagConstraints);

        jTextField12.setEditable(false);
        jTextField12.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField12.setText("0.0");
        jTextField12.setPreferredSize(new java.awt.Dimension(80, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.ipadx = 74;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel1.add(jTextField12, gridBagConstraints);

        selectProcsGrp.add(procBtn13);
        procBtn13.setText("13");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel1.add(procBtn13, gridBagConstraints);

        jTextField13.setEditable(false);
        jTextField13.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField13.setText("0.0");
        jTextField13.setPreferredSize(new java.awt.Dimension(80, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 74;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel1.add(jTextField13, gridBagConstraints);

        selectProcsGrp.add(procBtn14);
        procBtn14.setText("14");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel1.add(procBtn14, gridBagConstraints);

        jTextField14.setEditable(false);
        jTextField14.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField14.setText("0.0");
        jTextField14.setPreferredSize(new java.awt.Dimension(80, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 74;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel1.add(jTextField14, gridBagConstraints);

        selectProcsGrp.add(procBtn15);
        procBtn15.setText("15");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel1.add(procBtn15, gridBagConstraints);

        jTextField15.setEditable(false);
        jTextField15.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField15.setText("0.0");
        jTextField15.setPreferredSize(new java.awt.Dimension(80, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.ipadx = 74;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel1.add(jTextField15, gridBagConstraints);

        selectProcsGrp.add(procBtn16);
        procBtn16.setText("16");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel1.add(procBtn16, gridBagConstraints);

        jTextField16.setEditable(false);
        jTextField16.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField16.setText("0.0");
        jTextField16.setPreferredSize(new java.awt.Dimension(80, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.ipadx = 74;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel1.add(jTextField16, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 20;
        gridBagConstraints.ipady = 20;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        getContentPane().add(jPanel1, gridBagConstraints);

        copyToLogButton.setText("Copy Times to Log");
        copyToLogButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                copyToLogButtonActionPerformed(evt);
            }
        });
        jPanel2.add(copyToLogButton);

        clearTimesButton.setText("Clear Times");
        clearTimesButton.setPreferredSize(new java.awt.Dimension(121, 23));
        clearTimesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearTimesButtonActionPerformed(evt);
            }
        });
        jPanel2.add(clearTimesButton);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        getContentPane().add(jPanel2, gridBagConstraints);

        jScrollPane1.setPreferredSize(new java.awt.Dimension(400, 200));

        log.setColumns(20);
        log.setRows(5);
        jScrollPane1.setViewportView(log);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.9;
        getContentPane().add(jScrollPane1, gridBagConstraints);

        deleteLastReportButton.setText("Delete Last Report");
        jPanel3.add(deleteLastReportButton);

        saveLogToFileButton.setText("Save Log to File");
        saveLogToFileButton.setPreferredSize(new java.awt.Dimension(123, 23));
        saveLogToFileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveLogToFileButtonActionPerformed(evt);
            }
        });
        jPanel3.add(saveLogToFileButton);

        clearLogButton.setText("Clear Log");
        clearLogButton.setPreferredSize(new java.awt.Dimension(123, 23));
        clearLogButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearLogButtonActionPerformed(evt);
            }
        });
        jPanel3.add(clearLogButton);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        getContentPane().add(jPanel3, gridBagConstraints);

        rerunToolButton.setText("Rerun Tool");
        rerunToolButton.setActionCommand("");
        rerunToolButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rerunToolButtonActionPerformed(evt);
            }
        });
        jPanel4.add(rerunToolButton);

        closeButton.setText("Close");
        closeButton.setPreferredSize(new java.awt.Dimension(85, 23));
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });
        jPanel4.add(closeButton);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        getContentPane().add(jPanel4, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rerunToolButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rerunToolButtonActionPerformed
        host.runPlugin(plugin.getName(), pluginArgs);
    }//GEN-LAST:event_rerunToolButtonActionPerformed

    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
        this.dispose();
    }//GEN-LAST:event_closeButtonActionPerformed

    private void clearTimesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearTimesButtonActionPerformed
        int n=0;
        for (JTextField f : fields) {
            f.setText("0.0");
            times[n++] = 0;
        }
    }//GEN-LAST:event_clearTimesButtonActionPerformed

    private void clearLogButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearLogButtonActionPerformed
        log.setText("");
    }//GEN-LAST:event_clearLogButtonActionPerformed

    private void copyToLogButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_copyToLogButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_copyToLogButtonActionPerformed

    private void saveLogToFileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveLogToFileButtonActionPerformed
        JFileChooser a = new JFileChooser();
        a.showSaveDialog(this);
       
    }//GEN-LAST:event_saveLogToFileButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton clearLogButton;
    private javax.swing.JButton clearTimesButton;
    private javax.swing.JButton closeButton;
    private javax.swing.JButton copyToLogButton;
    private javax.swing.JButton deleteLastReportButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JTextArea log;
    private javax.swing.JRadioButton procBtn1;
    private javax.swing.JRadioButton procBtn10;
    private javax.swing.JRadioButton procBtn11;
    private javax.swing.JRadioButton procBtn12;
    private javax.swing.JRadioButton procBtn13;
    private javax.swing.JRadioButton procBtn14;
    private javax.swing.JRadioButton procBtn15;
    private javax.swing.JRadioButton procBtn16;
    private javax.swing.JRadioButton procBtn2;
    private javax.swing.JRadioButton procBtn3;
    private javax.swing.JRadioButton procBtn4;
    private javax.swing.JRadioButton procBtn5;
    private javax.swing.JRadioButton procBtn6;
    private javax.swing.JRadioButton procBtn7;
    private javax.swing.JRadioButton procBtn8;
    private javax.swing.JRadioButton procBtn9;
    private javax.swing.JButton rerunToolButton;
    private javax.swing.JButton saveLogToFileButton;
    private javax.swing.ButtonGroup selectProcsGrp;
    // End of variables declaration//GEN-END:variables
}