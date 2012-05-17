/*
 * Copyright 2000-2007 Sun Microsystems, Inc.  All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Sun designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Sun in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Sun Microsystems, Inc., 4150 Network Circle, Santa Clara,
 * CA 95054 USA or visit www.sun.com if you need additional information or
 * have any questions.
 */

package ae.sun.awt;

import ae.java.awt.*;
import ae.java.awt.dnd.*;
import ae.java.awt.dnd.peer.DragSourceContextPeer;
import ae.java.awt.event.*;
//import ae.java.awt.im.InputMethodHighlight;
//import ae.java.awt.im.spi.InputMethodDescriptor;
import ae.java.awt.image.*;
import ae.java.awt.datatransfer.Clipboard;
import ae.java.awt.peer.*;
import java.beans.PropertyChangeListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.Map;
import java.util.Properties;

//import ae.sun.awt.im.InputContext;
import ae.sun.awt.image.ImageRepresentation;

public class HeadlessToolkit extends Toolkit
    implements ComponentFactory, KeyboardFocusManagerPeerProvider {

    private Toolkit tk;
    private ComponentFactory componentFactory;

    public HeadlessToolkit(Toolkit tk) {
        this.tk = tk;
        if (tk instanceof ComponentFactory) {
            componentFactory = (ComponentFactory)tk;
        }
    }

    public Toolkit getUnderlyingToolkit() {
        return tk;
    }

    /*
     * Component peer objects.
     */

    /* Lightweight implementation of Canvas and Panel */

    public CanvasPeer createCanvas(Canvas target) {
        return (CanvasPeer)createComponent(target);
    }

    public PanelPeer createPanel(Panel target) {
        return (PanelPeer)createComponent(target);
    }

    /*
     * Component peer objects - unsupported.
     */

    public WindowPeer createWindow(Window target)
        throws HeadlessException {
        throw new HeadlessException();
    }

    public FramePeer createFrame(Frame target)
        throws HeadlessException {
        throw new HeadlessException();
    }

    public DialogPeer createDialog(Dialog target)
        throws HeadlessException {
        throw new HeadlessException();
    }

    public ButtonPeer createButton(Button target)
        throws HeadlessException {
        throw new HeadlessException();
    }

    public TextFieldPeer createTextField(TextField target)
        throws HeadlessException {
        throw new HeadlessException();
    }

    public ChoicePeer createChoice(Choice target)
        throws HeadlessException {
        throw new HeadlessException();
    }

    public LabelPeer createLabel(Label target)
        throws HeadlessException {
        throw new HeadlessException();
    }

    public ListPeer createList(List target)
        throws HeadlessException {
        throw new HeadlessException();
    }

    public CheckboxPeer createCheckbox(Checkbox target)
        throws HeadlessException {
        throw new HeadlessException();
    }

    public ScrollbarPeer createScrollbar(Scrollbar target)
        throws HeadlessException {
        throw new HeadlessException();
    }

    public ScrollPanePeer createScrollPane(ScrollPane target)
        throws HeadlessException {
        throw new HeadlessException();
    }

    public TextAreaPeer createTextArea(TextArea target)
        throws HeadlessException {
        throw new HeadlessException();
    }

    public FileDialogPeer createFileDialog(FileDialog target)
        throws HeadlessException {
        throw new HeadlessException();
    }

    public MenuBarPeer createMenuBar(MenuBar target)
        throws HeadlessException {
        throw new HeadlessException();
    }

    public MenuPeer createMenu(Menu target)
        throws HeadlessException {
        throw new HeadlessException();
    }

    public PopupMenuPeer createPopupMenu(PopupMenu target)
        throws HeadlessException {
        throw new HeadlessException();
    }

    public MenuItemPeer createMenuItem(MenuItem target)
        throws HeadlessException {
        throw new HeadlessException();
    }

    public CheckboxMenuItemPeer createCheckboxMenuItem(CheckboxMenuItem target)
        throws HeadlessException {
        throw new HeadlessException();
    }

    public DragSourceContextPeer createDragSourceContextPeer(
        DragGestureEvent dge)
        throws InvalidDnDOperationException {
        throw new InvalidDnDOperationException("Headless environment");
    }

    public RobotPeer createRobot(Robot target, GraphicsDevice screen)
        throws AWTException, HeadlessException {
        throw new HeadlessException();
    }

    public  KeyboardFocusManagerPeer createKeyboardFocusManagerPeer(KeyboardFocusManager manager) throws HeadlessException {
        KeyboardFocusManagerPeerImpl peer = new KeyboardFocusManagerPeerImpl(manager);
        return peer;
    }

    public TrayIconPeer createTrayIcon(TrayIcon target)
      throws HeadlessException {
        throw new HeadlessException();
    }

    public SystemTrayPeer createSystemTray(SystemTray target)
      throws HeadlessException {
        throw new HeadlessException();
    }

    public boolean isTraySupported() {
        return false;
    }

    public GlobalCursorManager getGlobalCursorManager()
        throws HeadlessException {
        throw new HeadlessException();
    }

    /*
     * Headless toolkit - unsupported.
     */
    protected void loadSystemColors(int[] systemColors)
        throws HeadlessException {
        throw new HeadlessException();
    }

    public ColorModel getColorModel()
        throws HeadlessException {
        throw new HeadlessException();
    }

    public int getScreenResolution()
        throws HeadlessException {
        throw new HeadlessException();
    }

//    public Map mapInputMethodHighlight(InputMethodHighlight highlight)
//        throws HeadlessException {
//        throw new HeadlessException();
//    }

    public int getMenuShortcutKeyMask()
        throws HeadlessException {
        throw new HeadlessException();
    }

    public boolean getLockingKeyState(int keyCode)
        throws UnsupportedOperationException {
        throw new HeadlessException();
    }

    public void setLockingKeyState(int keyCode, boolean on)
        throws UnsupportedOperationException {
        throw new HeadlessException();
    }

    public Cursor createCustomCursor(Image cursor, Point hotSpot, String name)
        throws IndexOutOfBoundsException, HeadlessException {
        throw new HeadlessException();
    }

    public Dimension getBestCursorSize(int preferredWidth, int preferredHeight)
        throws HeadlessException {
        throw new HeadlessException();
    }

    public int getMaximumCursorColors()
        throws HeadlessException {
        throw new HeadlessException();
    }

    public <T extends DragGestureRecognizer> T
        createDragGestureRecognizer(Class<T> abstractRecognizerClass,
                                    DragSource ds, Component c,
                                    int srcActions, DragGestureListener dgl)
    {
        return null;
    }

    public int getScreenHeight()
        throws HeadlessException {
        throw new HeadlessException();
    }

    public int getScreenWidth()
        throws HeadlessException {
        throw new HeadlessException();
    }

    public Dimension getScreenSize()
        throws HeadlessException {
        throw new HeadlessException();
    }

    public Insets getScreenInsets(GraphicsConfiguration gc)
        throws HeadlessException {
        throw new HeadlessException();
    }

    public void setDynamicLayout(boolean dynamic)
        throws HeadlessException {
        throw new HeadlessException();
    }

    protected boolean isDynamicLayoutSet()
        throws HeadlessException {
        throw new HeadlessException();
    }

    public boolean isDynamicLayoutActive()
        throws HeadlessException {
        throw new HeadlessException();
    }

    public Clipboard getSystemClipboard()
        throws HeadlessException {
        throw new HeadlessException();
    }

    /*
     * Printing
     */
    public PrintJob getPrintJob(Frame frame, String jobtitle,
        JobAttributes jobAttributes,
        PageAttributes pageAttributes) {
        if (frame != null) {
            // Should never happen
            throw new HeadlessException();
        }
        throw new IllegalArgumentException(
                "PrintJob not supported in a headless environment");
    }

    public PrintJob getPrintJob(Frame frame, String doctitle, Properties props)
    {
        if (frame != null) {
            // Should never happen
            throw new HeadlessException();
        }
        throw new IllegalArgumentException(
                "PrintJob not supported in a headless environment");
    }

    /*
     * Headless toolkit - supported.
     */

    public void sync() {
        // Do nothing
    }

    public void beep() {
        // Send alert character
        System.out.write(0x07);
    }

    /*
     * Event Queue
     */
    public EventQueue getSystemEventQueueImpl() {
        return SunToolkit.getSystemEventQueueImplPP();
    }

    /*
     * Images.
     */
    public int checkImage(Image img, int w, int h, ImageObserver o) {
        return tk.checkImage(img, w, h, o);
    }

    public boolean prepareImage(
        Image img, int w, int h, ImageObserver o) {
        return tk.prepareImage(img, w, h, o);
    }

    public Image getImage(String filename) {
        return tk.getImage(filename);
    }

    public Image getImage(URL url) {
        return tk.getImage(url);
    }

    public Image createImage(String filename) {
        return tk.createImage(filename);
    }

    public Image createImage(URL url) {
        return tk.createImage(url);
    }

    public Image createImage(byte[] data, int offset, int length) {
        return tk.createImage(data, offset, length);
    }

    public Image createImage(ImageProducer producer) {
        return tk.createImage(producer);
    }

    public Image createImage(byte[] imagedata) {
        return tk.createImage(imagedata);
    }


    /*
     * Fonts
     */
    public FontPeer getFontPeer(String name, int style) {
        if (componentFactory != null) {
            return componentFactory.getFontPeer(name, style);
        }
        return null;
    }

    public FontMetrics getFontMetrics(Font font) {
        return tk.getFontMetrics(font);
    }

    public String[] getFontList() {
        return tk.getFontList();
    }

    /*
     * Desktop properties
     */

    public void addPropertyChangeListener(String name,
        PropertyChangeListener pcl) {
        tk.addPropertyChangeListener(name, pcl);
    }

    public void removePropertyChangeListener(String name,
        PropertyChangeListener pcl) {
        tk.removePropertyChangeListener(name, pcl);
    }

    /*
     * Modality
     */
    public boolean isModalityTypeSupported(Dialog.ModalityType modalityType) {
        return false;
    }

    public boolean isModalExclusionTypeSupported(Dialog.ModalExclusionType exclusionType) {
        return false;
    }

    /*
     * Always on top
     */
    public boolean isAlwaysOnTopSupported() {
        return false;
    }

    /*
     * AWT Event listeners
     */

    public void addAWTEventListener(AWTEventListener listener,
        long eventMask) {
        tk.addAWTEventListener(listener, eventMask);
    }

    public void removeAWTEventListener(AWTEventListener listener) {
        tk.removeAWTEventListener(listener);
    }

    public AWTEventListener[] getAWTEventListeners() {
        return tk.getAWTEventListeners();
    }

    public boolean isDesktopSupported() {
        return false;
    }

    public DesktopPeer createDesktopPeer(Desktop target)
    throws HeadlessException{
        throw new HeadlessException();
    }
}
