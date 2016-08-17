import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JPanel;

class gui extends GuiElements
{
    protected int r;
    protected int c;
    protected int Bwidth;
    protected int Bheight;
    protected int BasicViewPanelHeight;
    protected int AllViewPanelWidth;
    protected int MatrixHeight;
    protected int MatrixWidth;
    protected int barWidth;
    protected int barHeight;
    protected int matrixBoxWidth;
    protected int matrixBoxHeight;
    protected int AllViewPanelHeight;
    protected JFrame base;
    protected JPanel bgP;
    public gui(int r,int c,int Bwidth,int Bheight,int BasicviewPanelHeight,int barHeight,int matrixBoxWidth,int matrixBoxHeight)
    {
        this.r=r;
        this.c=c;
        this.Bwidth=Bwidth;
        this.Bheight=Bheight;
        this.BasicViewPanelHeight=BasicviewPanelHeight;
        this.barHeight=barHeight;
        this.matrixBoxWidth=matrixBoxWidth;
        this.matrixBoxHeight=matrixBoxHeight;
        AllViewPanelWidth=((Bwidth+3)*6);
        AllViewPanelHeight=((Bheight+6)*4);
        MatrixHeight=((matrixBoxHeight+12)*r)+50;
        MatrixWidth=((matrixBoxWidth+12)*c)+50;
        barWidth=(int)(0.1*MatrixHeight);
        base=super.window("Matrix Calculator");   
        bgP=super.mainContainer(base,255,255,255);
    }
    public void globalVariableUpdater()
    {
        AllViewPanelWidth=((Bwidth+6)*6);
        AllViewPanelHeight=((Bheight+6)*4);
        MatrixHeight=((matrixBoxHeight+12)*r)+50;
        MatrixWidth=((matrixBoxWidth+12)*c)+50;
        barWidth=(int)(0.1*MatrixHeight);    
    }
    public void ButtonPanel()
    {
        JPanel Bp=super.containerBirthStyleAlign(bgP,"");
        JTextField Btn[][]=new JTextField[4][3];
        String bText[][]={{"1","2","3"},{"4","5","6"},{"7","8","9"},{".","0","["}};
        for(int i=0;i<4;i++)
        {
            for(int j=0;j<3;j++)
            {
                Btn[i][j]=super.EleBirthStyleAlign(Bp,Bwidth,Bheight, "button", bText[i][j],"cell "+j+" "+i);
                super.EleStyleFontStyleOverride(Btn[i][j],3);   
            }
        }
    }
    public JPanel ViewPanel1()
    {
        JPanel Bp=super.containerBirthStyleAlign(bgP,"wrap 3,span 2 1,hidemode 3");
        JTextField dS = super.EleBirthStyleAlign(Bp,AllViewPanelWidth,(BasicViewPanelHeight), "ostentation", "","");
        return Bp;
    }
    public JPanel ViewPanel2(String inputPromt)
    {
        JPanel Bp=super.containerBirthStyleAlign(bgP,"wrap 3,span 2 1,hidemode 3");
        JPanel Bp1=super.containerBirthStyleAlign(Bp, "");
        JTextField dS1 = super.EleBirthStyleAlign(Bp1,AllViewPanelWidth,(2*BasicViewPanelHeight/3), "label", inputPromt,"wrap,span 2 1");
        final JTextField dS2 = super.EleBirthStyleAlign(Bp1,AllViewPanelWidth-100,(BasicViewPanelHeight/3), "textArea", "","");
        JTextField dS3 = super.EleBirthStyleAlign(Bp1,90,(BasicViewPanelHeight/3), "button", "Ok!","");
        super.containerStyleBorderColorOverride(Bp1, 51, 153, 255);
        super.containerStyleBorderWidthOverride(Bp1, (int)(0.008*(((dS1.getPreferredSize().width+3)+(dS1.getPreferredSize().height+3))/2)));
        dS2.requestFocus();
        return Bp;
    }
    public JPanel ViewPanel3(int scrollPaneLength,int scrollPaneHeight)
    {
        int effectiveScrollPaneWidth=AllViewPanelWidth-70;
        int leftSidePaddingWidth=(effectiveScrollPaneWidth-MatrixWidth)/2;
        JPanel Bp=super.containerBirthStyleAlign(bgP,"wrap 3,span 2 1,hidemode 3");
        JPanel Bp1=super.containerBirthStyleAlign(Bp, "");
        super.scrollable(Bp1, Bp, scrollPaneLength, scrollPaneHeight);
        JTextField dSp1;
        if((leftSidePaddingWidth+MatrixWidth+100)<effectiveScrollPaneWidth)
            dSp1= super.EleBirthStyleAlign(Bp1,leftSidePaddingWidth,1, "label", "","cell 0 2");
        else
            dSp1=super.EleBirthStyleAlign(Bp1,0,0, "", "","");
        JTextField dS1 = super.EleBirthStyleAlign(Bp1,(int)(barWidth),barHeight, "bar", "","cell 1 1");
        JTextField dS2 = super.EleBirthStyleAlign(Bp1,(int)(barWidth),barHeight, "bar", "","wrap 0, cell 3 1");
        JTextField dS3 = super.EleBirthStyleAlign(Bp1,barHeight,MatrixHeight, "bar", "","cell 1 2");
        JPanel Bp3=super.containerBirthStyleAlign(Bp1, "cell 2 2");
        super.containerStyleBorderColorOverride(Bp3, 255, 255, 255);
        JTextField mtxEle[][]=new JTextField[r][c];
        for(int i=0;i<r;i++)
        {
            for(int j=0;j<c;j++)
            {
                mtxEle[i][j] = super.EleBirthStyleAlign(Bp3,matrixBoxWidth,matrixBoxHeight, "textArea", "","cell "+j+" "+i);
                mtxEle[i][j].setName(((i+j)+i*(c-1))+"");
            }
        }
        JTextField dS4 = super.EleBirthStyleAlign(Bp1,barHeight,MatrixHeight, "bar", ""," cell 3 2,gapleft "+(barWidth-barHeight));
        JTextField btn = super.EleBirthStyleAlign(Bp1,90,(BasicViewPanelHeight/3), "button", "Ok!","wrap 0, gapleft 10");
        JTextField dS5 = super.EleBirthStyleAlign(Bp1,(int)(barWidth),barHeight, "bar", "","cell 1 3");
        JTextField dS6 = super.EleBirthStyleAlign(Bp1,(int)(barWidth),barHeight, "bar", "","wrap 0, cell 3 3");
        super.containerStyleBorderColorOverride(Bp1, 51, 153, 255);
        super.containerStyleBorderWidthOverride(Bp1, (int)(0.008*(((effectiveScrollPaneWidth)+(MatrixHeight))/2))); 
        mtxEle[0][0].requestFocus();
        return Bp;
    }
    public void enlarger(String title)
    {
        final JFrame jf=super.window("");
        JPanel p=super.mainContainer(jf,255,255,255);
        //ViewPanel3(p,(int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(),(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight());
        jf.setVisible(true);
    }
    public void FunctionPanel()
    {
        JPanel Bp=super.containerBirthStyleAlign(bgP,"");
        JTextField Btn[][]=new JTextField[4][3];
        String bText[][]={{"+","-","/"},{"Adj","Det","Inv"},{"Ch. Eq.","Ei Val","Ei Vec"},{"Rank","Diag","*"}};
        for(int i=0;i<4;i++)
        {
            for(int j=0;j<3;j++)
            {
                Btn[i][j]=super.EleBirthStyleAlign(Bp,Bwidth,Bheight, "button", bText[i][j],"cell "+j+" "+i);
                super.EleStyleFontStyleOverride(Btn[i][j],3);     
            }
        }
    }
    public JPanel basicSetup()
    {
        JPanel Vp=ViewPanel2(" Enter Equation :");
        
        ButtonPanel();
        FunctionPanel();
        base.setVisible(true);
        frameResizeOverride(base, true);
        base.pack();
        
        return Vp;
    }
    public JPanel repainter(String panelName,String Message,boolean isAlone)
    {
        bgP.removeAll();
        JPanel Vp;
        switch(panelName)
        {
            case "vP1":
            {
                Vp=ViewPanel1();
                break;
            }
            case "vP2r":
            {
                Vp=ViewPanel2(" Enter Row of Matrix ["+Message+"] :");
                break;
            }
            case "vP2c":
            {
                Vp=ViewPanel2(" Enter Column of Matrix ["+Message+"] :");
                break;
            }
            case "vP2e":
            {
                Vp=ViewPanel2(" Enter Equation :");
                break;
            }
            case "vP3":
            {
                Vp=ViewPanel3(AllViewPanelWidth,MatrixHeight+47);
                break;
            }
            default:
            {
                Vp=ViewPanel1();
                break;
            }
        }
        if(isAlone==false)
        {
            ButtonPanel();
            FunctionPanel();
        }
        bgP.revalidate();
        bgP.repaint();
        base.pack();
        return Vp;
    }
}
