package info6205.virus.simulation.gui;

import javax.swing.*;
import java.awt.event.*;

public class MouseEvent implements MouseWheelListener, MouseListener, MouseMotionListener {
    private int pressX;
    private int pressY;
    private SimulationRender render;
    private JPanel canvas;

    public MouseEvent(SimulationRender render, JPanel canvas) {
        this.render = render;
        this.canvas = canvas;
    }

    @Override
    public void mouseClicked(java.awt.event.MouseEvent e) {

    }

    @Override
    public void mousePressed(java.awt.event.MouseEvent e) {
        updateCross(e);
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        updateRenderZoom(e);
    }

    @Override
    public void mouseReleased(java.awt.event.MouseEvent e) {

    }

    @Override
    public void mouseEntered(java.awt.event.MouseEvent e) {

    }

    @Override
    public void mouseExited(java.awt.event.MouseEvent e) {

    }
    private void updateRenderZoom(MouseWheelEvent e){
        double zoom=render.getZoom();
        int rotation=e.getWheelRotation();
        zoom=zoom+(rotation*0.003);
        render.setZoom(zoom);
        render.cleanCanvas(canvas.getGraphics());
    }

    private void updateCross(java.awt.event.MouseEvent e){
        int x = e.getX();
        int y = e.getY();
        pressX = x;
        pressY = y;
        render.renderCrossLine(x, y, canvas.getGraphics());
    }

    private void updateRenderXY(java.awt.event.MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        int deltaX = x - pressX;
        int deltaY = y - pressY;
        render.setxLeftTopRealWorld(render.getxLeftTopRealWorld() - render.convert2RealWorldLength(deltaX));
        render.setyLeftTopRealWorld(render.getyLeftTopRealWorld() + render.convert2RealWorldLength(deltaY));
        render.cleanCanvas(canvas.getGraphics());
    }

    @Override
    public void mouseDragged(java.awt.event.MouseEvent e) {
        updateRenderXY(e);
        updateCross(e);
    }

    @Override
    public void mouseMoved(java.awt.event.MouseEvent e) {

    }
}
