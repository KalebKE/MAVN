/*
JUNGPanelAdapter -- a class within the Machine Artificial Vision Network
(Machine Artificial Vision Network).
Copyright (C) 2012, Kaleb Kircher.

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
package mavn.network.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.print.Printable;
import java.io.File;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;
import org.apache.commons.collections15.functors.MapTransformer;
import org.apache.commons.collections15.map.LazyMap;
import edu.uci.ics.jung.algorithms.layout.AbstractLayout;
import edu.uci.ics.jung.algorithms.layout.StaticLayout;
import edu.uci.ics.jung.graph.DirectedGraph;
import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.visualization.GraphZoomScrollPane;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.annotations.AnnotationControls;
import edu.uci.ics.jung.visualization.control.CrossoverScalingControl;
import edu.uci.ics.jung.visualization.control.EditingModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ScalingControl;
import edu.uci.ics.jung.visualization.decorators.EdgeShape;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import java.awt.Paint;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import org.apache.commons.collections15.Factory;
import org.apache.commons.collections15.Transformer;

/**
 * JUNG Panel Adaptor class uses an Adaptor Pattern to take
 * the MANV Network's and render the them with the JUNG Framework's Graphical Network.
 */
public class JUNGPanelAdapter extends JPanel implements Printable
{

    ArrayList verticies = new ArrayList();
    double[][] w2;
    double[][] w1;
    double[][] w0;
    // indicies to keep track of the verticies in the collection
    private int inputX;
    private int inputY;
    private int andMin;
    private int andMax;
    private int orMin;
    private int orMax;
    private int output;
    private DirectedGraph<Number, Number> graph;
    private AbstractLayout<Number, Number> layout;
    //the visual component and renderer for the graph
    private VisualizationViewer<Number, Number> vv;

    Factory<Number> vertexFactory = new VertexFactory();
    Factory<Number> edgeFactory = new EdgeFactory();
    // count the number of verticies
    int nodeCount = 0;
    // count the number of edges
    int edgeCount = 0;

    /**
     * Create an instance of a simple graph with popup controls to
     * create a graph.
     *
     */
    public JUNGPanelAdapter(double[][] w2, double[][] w1, double[][] w0)
    {
        this.w2 = w2;
        this.w1 = w1;
        this.w0 = w0;
        this.setPreferredSize(new Dimension(600, 400));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        // create a simple graph for the demo
        graph = new DirectedSparseMultigraph<Number, Number>();

        this.layout = new StaticLayout<Number, Number>(graph,
                new Dimension(600, 600));

        if (w2.length > 0)
        {
            findIndicies();
            createVerticies();
            drawEdges();
            drawVerticies();
        }

        vv = new VisualizationViewer<Number, Number>(layout);

        vv.setBackground(Color.getColor("#333333"));

        vv.getRenderContext().setVertexLabelTransformer(MapTransformer.<Number, String>getInstance(
                LazyMap.<Number, String>decorate(new HashMap<Number, String>(), new ToStringLabeller<Number>())));

        vv.setVertexToolTipTransformer(vv.getRenderContext().getVertexLabelTransformer());

        vv.getRenderContext().setEdgeShapeTransformer(new EdgeShape.Line());

        final GraphZoomScrollPane panel = new GraphZoomScrollPane(vv);
        this.add(panel);

        final EditingModalGraphMouse<Number, Number> graphMouse =
                new EditingModalGraphMouse<Number, Number>(vv.getRenderContext(), vertexFactory, edgeFactory);

        // the EditingGraphMouse will pass mouse event coordinates to the
        // vertexLocations function to set the locations of the vertices as
        // they are created
        // graphMouse.setVertexLocations(vertexLocations);
        vv.setGraphMouse(graphMouse);
        vv.addKeyListener(graphMouse.getModeKeyListener());

        graphMouse.setMode(ModalGraphMouse.Mode.TRANSFORMING);

        final ScalingControl scaler = new CrossoverScalingControl();
        JButton plus = new JButton("+");
        plus.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                scaler.scale(vv, 1.1f, vv.getCenter());
            }
        });
        JButton minus = new JButton("-");
        minus.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                scaler.scale(vv, 1 / 1.1f, vv.getCenter());
            }
        });

        JPanel controls = new JPanel();
        controls.add(plus);
        controls.add(minus);
        this.add(controls);
        this.setVisible(true);
    }

    public void createVerticies()
    {
        for (int i = 0; i <= output; i++)
        {
            graph.addVertex(vertexFactory.create());
        }

        Iterator iterate = graph.getVertices().iterator();
        while (iterate.hasNext())
        {
            verticies.add((Number) iterate.next());
        }

        Collections.sort(verticies);
    }

    public void drawEdges()
    {
        Iterator iterator = verticies.iterator();

        Number xNode = null;
        Number yNode = null;
        int count = 0;
        // draw the input layer edges
        while (iterator.hasNext())
        {

            Number v = (Number) iterator.next();

            if (count == 0)
            {
                xNode = v;
            }
            if (count == 1)
            {
                yNode = v;
            }
            if (count > 1 && count < w2[0].length + 2)
            {
                graph.addEdge(edgeCount, xNode, v, EdgeType.DIRECTED);
                edgeCount++;
                graph.addEdge(edgeCount, yNode, v, EdgeType.DIRECTED);
                edgeCount++;
            }
            count++;
        }
        // draw the ANDing to ORing edges
        for (int i = 0; i < w1.length; i++)
        {
            for (int j = 0; j < w1[i].length; j++)
            {
                if (w1[i][j] == 1)
                {
                    graph.addEdge(edgeCount, andMin + j, orMin + i, EdgeType.DIRECTED);
                    edgeCount++;
                }
            }
        }
        for (int i = 0; i < w0.length; i++)
        {
            for (int j = 0; j < w0[i].length; j++)
            {
                if (w0[i][j] == 1)
                {
                    graph.addEdge(edgeCount, orMin + i, output, EdgeType.DIRECTED);
                    edgeCount++;
                }
            }
        }
    }

    public void drawVerticies()
    {
        Iterator iterateVerticies = verticies.iterator();

        int count = 0;
        int x = 50;
        int y = 50;
        while (iterateVerticies.hasNext())
        {
            Number v = (Number) iterateVerticies.next();
            y += 50;
            if (count == inputX)
            {
                y = (int) (Math.floor((50 * w2[0].length / 2)) - 50);
            }
            if (count == inputY)
            {
                y = (int) (50 + Math.floor((50 * w2[0].length / 2)));
            }
            if (count == andMin)
            {
                x += 150;
                y = 50;
            }
            if (count == orMin)
            {
                x += 150;
                y = (int) (Math.floor((50 * w2[0].length / 2)) - 75);
            }
            if (count == output)
            {

                x += 150;
                y = (int) (Math.floor((50 * w2[0].length / 2)));
            }

            layout.setLocation(v, x, y);
            layout.lock(v, true);
            count++;
        }
    }

    public void findIndicies()
    {
        //Indicies to keep track of what nodes belong to what layer
        // in the verticies collection.
        // The first two indicies are for the X and Y coordinate
        // inputs.
        inputX = 0;
        inputY = 1;

        // The third index through the number of nodes in the
        // ANDing layer
        andMin = inputY + 1;
        andMax = andMin + w2[0].length - 1;

        // The third index through the number of nodes in the
        // ANDing layer + 1
        orMin = andMax + 1;
        orMax = orMin + w1.length - 1;
        output = orMax + 1;
    }

    public void fireNodes(final double[] result)
    {
        Transformer<Number, Paint> vertexPaint = new Transformer<Number, Paint>()
        {

            private final Color[] palette =
            {
                Color.GREEN, Color.BLACK, Color.RED
            };

            @Override
            public Paint transform(Number i)
            {
                if (i.doubleValue() == inputX || i.doubleValue() == inputY)
                {
                    if (result[inputX] == 1)
                    {
                        return palette[0];
                    }
                    if (result[inputY] == -1)
                    {
                        return palette[0];
                    }
                }
                if (i.doubleValue() >= andMin && i.doubleValue() <= andMax)
                {
                    if (result[i.intValue()] == 1)
                    {
                        return palette[0];
                    }
                    if (result[i.intValue()] == -1)
                    {
                        return palette[1];
                    }
                }
                if (i.doubleValue() >= orMin && i.doubleValue() <= orMax)
                {
                    if (result[i.intValue()] == 1)
                    {
                        return palette[0];
                    }
                    if (result[i.intValue()] == -1)
                    {
                        return palette[1];
                    }
                }
                if (i.doubleValue() == output)
                {
                    if (result[output] == 1)
                    {
                        return palette[0];
                    }
                    if (result[output] == -1)
                    {
                        return palette[1];
                    }
                }
                return palette[2];
            }
        };

        vv.getRenderContext().setVertexFillPaintTransformer(vertexPaint);
    }

    @Override
    public int print(java.awt.Graphics graphics,
            java.awt.print.PageFormat pageFormat, int pageIndex)
            throws java.awt.print.PrinterException
    {
        if (pageIndex > 0)
        {
            return (Printable.NO_SUCH_PAGE);
        } else
        {
            java.awt.Graphics2D g2d = (java.awt.Graphics2D) graphics;
            vv.setDoubleBuffered(false);
            g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());

            vv.paint(g2d);
            vv.setDoubleBuffered(true);

            return (Printable.PAGE_EXISTS);
        }
    }

    /**
     * copy the visible part of the graph to a file as a jpeg image
     * @param file
     */
    public void writeJPEGImage(File file)
    {
        int width = vv.getWidth();
        int height = vv.getHeight();

        BufferedImage bi = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = bi.createGraphics();
        vv.paint(graphics);
        graphics.dispose();

        try
        {
            ImageIO.write(bi, "jpeg", file);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    class VertexFactory implements Factory<Number>
    {

        @Override
        public Number create()
        {
            return nodeCount++;
        }
    }

    class EdgeFactory implements Factory<Number>
    {

        @Override
        public Number create()
        {
            return edgeCount++;
        }
    }
}
