import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import net.miginfocom.swing.MigLayout;

public class GuiElements extends JPanel
{
    
    public JFrame window(String title)
    {
        JFrame Jf=new JFrame(title);
        Jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Jf.setResizable(true);
        Jf.getContentPane().setLayout(new MigLayout("insets 0 0 0 0"));
        return Jf;
    }
    public void frameResizeOverride(JFrame ele,boolean bool)
    {
        ele.setResizable(bool);
    }
    public void frameDefaultCloseOperationOverride(JFrame ele,int i)
    {
        ele.setDefaultCloseOperation(0);
    }
    public JPanel mainContainer(JFrame Jf,int r,int g,int b)
    {
        JPanel Jp=new JPanel(new MigLayout("insets 3 3 3 3"));
        Jp.setBackground(new Color(255,255,255));
        Jf.add(Jp);
        return Jp;
    }
    public JPanel containerBirthStyleAlign(JPanel J, String mLc)
    {
        JPanel Jp=new JPanel(new MigLayout());
        Jp.setBackground(new Color(255,255,255));
        Jp.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.black),BorderFactory.createEmptyBorder(0,0,0,0)));
        J.add(Jp,mLc);
        return Jp;
    }
    public void containerStyleBgOverride(JPanel ele,int r,int g,int b)
    {
        ele.setBackground(new Color(r,g,b));
    }
    public void containerStyleBorderColorOverride(JPanel ele,int r,int g,int b)
    {
        ele.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(r,g,b),ele.getBorder().getBorderInsets(ele).bottom),BorderFactory.createEmptyBorder(0,0,0,0)));
    }
    public void containerStyleBorderWidthOverride(JPanel ele,int width)
    {
        ele.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(((LineBorder)((CompoundBorder)(ele.getBorder())).getOutsideBorder()).getLineColor(),width),BorderFactory.createEmptyBorder(0,0,0,0)));
    }
    public JTextField EleBirthStyleAlign(JPanel C, int length,int breadth, String type, String text,String mLc)
    {
        JTextField Jl=new JTextField();
        Jl.setPreferredSize(new Dimension(length,breadth));
        switch (type) 
        {
            case "button":
            {    
                Jl.setBackground(Color.white);
                Jl.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(51,153,255), (int)(0.05*(((Jl.getPreferredSize().width)+(Jl.getPreferredSize().height))/2))),BorderFactory.createEmptyBorder(0,0,0,0)));
                Jl.setFont(new Font(Jl.getFont().getName(),Jl.getFont().getStyle(),(int)(.4*(Jl.getPreferredSize().height))));
                Jl.setText(text);
                Jl.setHorizontalAlignment(SwingConstants.CENTER);
                Jl.setForeground(new Color(51,153,255));
                Jl.setOpaque(true);
                Jl.setCursor(new Cursor(Cursor.HAND_CURSOR));
                Jl.setEditable(false);
                Jl.setFocusable(false);
                EleFunc(Jl,type);
                
                break;
            }    
            case "textArea":
            {    
                Jl.setBackground(Color.white);
                Jl.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK, 1),BorderFactory.createEmptyBorder(0,5,0,0)));
                Jl.setFont(new Font(Jl.getFont().getName(),2,(int)(.4*(Jl.getPreferredSize().height))));
                Jl.setHorizontalAlignment(SwingConstants.LEFT);
                Jl.setForeground(Color.black);
                Jl.setOpaque(true);
                break;
            }
            case "label":
            {
                Jl.setBackground(Color.white);
                Jl.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.WHITE, 0),BorderFactory.createEmptyBorder(0,0,0,0)));
                Jl.setFont(new Font(Jl.getFont().getName(),2,(int)(.15*(Jl.getPreferredSize().height))));
                Jl.setText(text);
                Jl.setHorizontalAlignment(SwingConstants.LEFT);
                Jl.setForeground(Color.black);
                Jl.setOpaque(true);
                Jl.setEditable(false);
                break;
            }
            case "ostentation":
            {    
                Jl.setBackground(Color.white);
                Jl.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(51,153,255), (int)(0.008*(((Jl.getPreferredSize().width)+(Jl.getPreferredSize().height))/2))),BorderFactory.createEmptyBorder(0,0,0,0)));
                Jl.setFont(new Font(Jl.getFont().getName(),Jl.getFont().getStyle(),(int)(.4*(Jl.getPreferredSize().height))));
                Jl.setText(text);
                Jl.setHorizontalAlignment(SwingConstants.CENTER);
                Jl.setForeground(new Color(51,153,255));
                Jl.setOpaque(true);
                Jl.setEditable(false);
                break;
            }
            case "bar":
            {    
                Jl.setBackground(Color.black);
                Jl.setMinimumSize(new Dimension(1,1));
                Jl.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK, 1),BorderFactory.createEmptyBorder(0,0,0,0)));
                Jl.setOpaque(true);
                Jl.setEditable(false);
                break;
            }
        }
        C.add(Jl,mLc);
        return Jl;
    }
    public void EleAlign(JPanel Jp,JTextField C, String mLc)
    {
        Jp.add(C,mLc);
    }
    public void EleStyleBgOverride(JTextField ele,int r,int g,int b)
    {
        ele.setBackground(new Color(r,g,b));
    }
    public void EleStyleBorderColorOverride(JTextField ele,int r,int g,int b)
    {
        if(ele.isEditable()==true)
            ele.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(r,g,b),ele.getBorder().getBorderInsets(ele).bottom),BorderFactory.createEmptyBorder(0,5,0,0)));
        else
            ele.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(r,g,b),ele.getBorder().getBorderInsets(ele).bottom),BorderFactory.createEmptyBorder(0,0,0,0)));
    }
    public void EleStyleBorderWidthOverride(JTextField ele,int width)
    {
        if(ele.isEditable()==true)
            ele.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder((((LineBorder)ele.getBorder()).getLineColor()),width),BorderFactory.createEmptyBorder(0,5,0,0)));
        else
            ele.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder((((LineBorder)ele.getBorder()).getLineColor()),width),BorderFactory.createEmptyBorder(0,0,0,0)));
    }
    public void EleStyleFontTypeOverride(JTextField ele,String fontName)
    {
        ele.setFont(new Font(fontName,ele.getFont().getStyle(),(int)(.4*ele.getPreferredSize().height)));
    }
    public void EleStyleFontStyleOverride(JTextField ele,int fontStyle)
    {
        ele.setFont(new Font(ele.getFont().getName(),fontStyle,(int)(.4*ele.getPreferredSize().height)));
    }
    public void EleStyleFontWidthOverride(JTextField ele,int width)
    {
        ele.setFont(new Font(ele.getFont().getName(),ele.getFont().getStyle(),width));
    }
        public void EleStyleTextOverride(JTextField ele,String text)
    {
        ele.setText(text);
    }
    public void EleStyleFgOverride(JTextField ele,int r,int g,int b)
    {
        ele.setForeground(new Color(r,g,b));
    }
    public void EleStyleOpacityOverride(JTextField ele,Boolean onOff)
    {
        ele.setOpaque(onOff);
    }
    public void scrollable(JPanel scrollclient, JPanel scrollPaneContainer, int length,int height)
    {
        JScrollPane jsp=new JScrollPane(scrollclient);
        if(height!=0 && length!=0)
            jsp.setPreferredSize(new Dimension(length,height));
        scrollPaneContainer.add(jsp, "center");
    }
    
    public void EleFunc(final JTextField Jl,String type)
    {
        switch (type) 
        {
            case "button":
            {
                final Color bgInertia=Jl.getBackground();
                final Color fgInertia=Jl.getForeground();
                final Border borderInertia=Jl.getBorder();
                Jl.addMouseListener(new MouseAdapter() 
                {
                    @Override
                    public void mouseEntered(MouseEvent e) 
                    {
                        Jl.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(((LineBorder)((CompoundBorder)Jl.getBorder()).getOutsideBorder()).getLineColor().darker(),Jl.getBorder().getBorderInsets(Jl).bottom),BorderFactory.createEmptyBorder(0,0,0,0)));
                        Jl.setForeground(Color.black);    
                        Jl.requestFocus();
                    }
                    @Override
                    public void mouseExited(MouseEvent e) 
                    {
                        Jl.setBackground(bgInertia);
                        Jl.setBorder(borderInertia);
                        Jl.setForeground(fgInertia);    
                    }
                    @Override
                    public void mousePressed(MouseEvent e) 
                    {
                        Jl.setBackground(bgInertia.darker());
                        Jl.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(((LineBorder)((CompoundBorder)Jl.getBorder()).getOutsideBorder()).getLineColor().darker(),Jl.getBorder().getBorderInsets(Jl).bottom),BorderFactory.createEmptyBorder(0,0,0,0)));
                        Jl.setForeground(Color.black);    
                    }
                    @Override
                    public void mouseReleased(MouseEvent e) 
                    {
                        Jl.setBackground(bgInertia);
                        Jl.setBorder(borderInertia);
                        Jl.setForeground(fgInertia);    
                    }
                    
                });   
            }
        }
    }
}