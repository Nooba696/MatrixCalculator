import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JViewport;

public class MatrixBasics
{
    gui obj=new gui(3,3,80,30,150,9,50,50);
    JPanel Vp=null;
    
    DatabaseElements dbEle=new DatabaseElements();
                
    private String matrixName="";
    private String eq="";
    private String copy="";
    private String tableHeadings1[][]={{"Name","Row","Col"},{"VARCHAR(255)","INTEGER","INTEGER"}};
    private String clause[][]={{tableHeadings1[0][0]},{"="},{""}};
    private String rowData[]={"'"+matrixName+"'","",null};
    private String columnData[][]={{"Col"},{""}};
    private String mProps[]={"Row","Col"};
    private String operators=" |(| |)| |Adj| |Det| |Inv| |Ch.Eq.| |EiVal| |EiVec| |Rank| |Diag| |*| |/| |+| |-|";
    private ResultSet rs;
    
    private JTextField activeTextField;
    private long lastPressProcessed = 0;
   
    
    public void activeTextFieldFinder1()
    {
        activeTextField=((JTextField)((JPanel)(Vp.getComponent(0))).getComponent(1));
    }
    public void activeTextFieldFinder2()
    {
        for(int i=0;i<obj.r*obj.c;i++)
        {
            ((JTextField)((JPanel)((JPanel)((JViewport)((JScrollPane)(Vp.getComponent(0))).getComponent(0)).getComponent(0)).getComponent(4)).getComponent(i)).addMouseListener(new MouseAdapter()
            {
                @Override
                public void mouseClicked(MouseEvent e) 
                {
                    activeTextField=((JTextField)e.getComponent());
                    activeTextField.setName(((JTextField)e.getComponent()).getName());
                    setArray();
                }
            });
        }
    }
    public void activeTextFieldFinder3()
    {
        activeTextField=((JTextField)((JPanel)((JPanel)((JViewport)((JScrollPane)(Vp.getComponent(0))).getComponent(0)).getComponent(0)).getComponent(4)).getComponent(0));
        setArray();
    }
    public void panelFunc(String VpType)
    {
        if(VpType.equals("vP3"))
        {
            activeTextFieldFinder3();
            activeTextFieldFinder2();
        }
        else
            activeTextFieldFinder1();
        JPanel Bp=((JPanel)obj.bgP.getComponent(1));
        for(int i=0;i<12;i++)
        {
            Bp.getComponent(i).addMouseListener(new MouseAdapter()
            {
                @Override
                public void mouseClicked(MouseEvent e) 
                {
                    activeTextField.setText(activeTextField.getText()+((JTextField)e.getComponent()).getText());
                    switch (((JTextField)e.getComponent()).getText()) 
                    {
                        case "[":
                        {
                            ((JTextField)e.getComponent()).setText("]");
                            break;
                        }
                        case "]":
                        {
                            ((JTextField)e.getComponent()).setText("[");
                            break;
                        }
                    }
                }
            });
        }
        JPanel Fp=((JPanel)obj.bgP.getComponent(2));
        for(int i=0;i<12;i++)
        {
            Fp.getComponent(i).addMouseListener(new MouseAdapter()
            {
                @Override
                public void mouseClicked(MouseEvent e) 
                {
                    activeTextField.setText(activeTextField.getText()+((JTextField)e.getComponent()).getText());
                }
            });
        }
    }
    
    
    public void setRowColPrep()
    {
        matrixName=eq.substring(eq.indexOf("[")+1,eq.indexOf("]"));
        obj.base.setTitle("Matrix Calculator : Matrix ["+matrixName+"]");
        clause[2][0]=matrixName;
        eq=eq.substring(eq.indexOf("]")+1);
        Vp=obj.repainter("vP2r",matrixName,false);
        Vp=obj.repainter("vP2r",matrixName,false);
        panelFunc("vP2r");
    }
    public void setRowOnAction() throws SQLException
    {
        rowData[0]="'"+matrixName+"'";
        rowData[1]=((JTextField)((JPanel)(Vp.getComponent(0))).getComponent(1)).getText().toString();
        dbEle.InsertTableData((dbEle.dbmsUrl+dbEle.dbmsName),dbEle.dbmsUsername,dbEle.dbmsPassword,dbEle.insertTableStatement("MatrixProperties",rowData));
        Vp=obj.repainter("vP2c",matrixName,false);
        panelFunc("vP2c");
    }
    public void setColOnAction() throws SQLException
    {
        columnData[1][0]=((JTextField)((JPanel)(Vp.getComponent(0))).getComponent(1)).getText().toString();
        dbEle.EditTableData((dbEle.dbmsUrl+dbEle.dbmsName),dbEle.dbmsUsername,dbEle.dbmsPassword,(dbEle.editTableStatement("MatrixProperties",columnData,(dbEle.createClauseStatement("Where", clause)))));
        rs=(dbEle.getTableData((dbEle.dbmsUrl+dbEle.dbmsName),dbEle.dbmsUsername,dbEle.dbmsPassword,(dbEle.getTableDataStatement("MatrixProperties",mProps,(dbEle.createClauseStatement("Where", clause))))));
        rs.next();
        obj.r=rs.getInt("Row");
        obj.c=rs.getInt("Col");
        obj.globalVariableUpdater();
        Vp=obj.repainter("vP3","",false);
        activeTextField.setBackground(Color.yellow);
        String tableHeadings2[][]=new String[2][obj.c];
        for(int i=0;i<obj.c;i++)
        {
            tableHeadings2[0][i]="c"+i;
            tableHeadings2[1][i]="INTEGER";
        }
        dbEle.createTable((dbEle.dbmsUrl+dbEle.dbmsName),dbEle.dbmsUsername,dbEle.dbmsPassword,(dbEle.createTableStatement(matrixName,tableHeadings2,"")));
        panelFunc("vP3");
    }
    public void setArrayOnAction() throws SQLException
    {
        String ret[]=new String[obj.r*obj.c];
        for(int i=0;i<ret.length;i++)
        {
            ret[i]=((JTextField)((JPanel)((JPanel)((JViewport)((JScrollPane)(Vp.getComponent(0))).getComponent(0)).getComponent(0)).getComponent(4)).getComponent(i)).getText().toString();
        }
        dbEle.InsertTableData((dbEle.dbmsUrl+dbEle.dbmsName),dbEle.dbmsUsername,dbEle.dbmsPassword,dbEle.insertTableStatement(matrixName,ret,obj.r,obj.c));
        if(eq.indexOf("[")==-1)
        {
            try 
            {
                showArray((new EquationHandler()).MatrixPostfixEqEval(copy,operators));
            } 
            catch (InterruptedException ex) 
            {
                Logger.getLogger(MatrixBasics.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else
        {
            setRow();
        }
    }
    public void setRow()
    {
        setRowColPrep();    
        (((JPanel)(Vp.getComponent(0))).getComponent(2)).addMouseListener(new MouseAdapter() 
        {
            @Override
            public void mouseClicked(MouseEvent e) 
            {
                try 
                {
                    setRowOnAction();
                    setColumn();
                }
                catch (SQLException ex) 
                {
                    Logger.getLogger(MatrixBasics.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        activeTextField.addKeyListener(new KeyAdapter() 
        {
            @Override
            public void keyPressed(KeyEvent e) 
            {
                if(e.getKeyCode()==KeyEvent.VK_ENTER)
                {
                    try 
                    {
                       setRowOnAction();
                       setColumn();
                    } 
                    catch (SQLException ex) 
                    {
                        Logger.getLogger(MatrixBasics.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
    }
    public void setColumn()
    {
        (((JPanel)(Vp.getComponent(0))).getComponent(2)).addMouseListener(new MouseAdapter() 
        {
            @Override
            public void mouseClicked(MouseEvent e) 
            {
                try 
                {
                    setColOnAction();
                }
                catch (SQLException ex) 
                {
                    Logger.getLogger(MatrixBasics.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        activeTextField.addKeyListener(new KeyAdapter() 
        {
            @Override
            public void keyPressed(KeyEvent e) 
            {
                if(e.getKeyCode()==KeyEvent.VK_ENTER)
                {
                    try 
                    {
                       setColOnAction();
                    } 
                    catch (SQLException ex) 
                    {
                        Logger.getLogger(MatrixBasics.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        
    }
    
    public void spacer()
    {
        activeTextField.addKeyListener(new KeyAdapter() 
        {
            @Override
            public void keyPressed(KeyEvent e) 
            {
                if(e.getKeyCode()==KeyEvent.VK_RIGHT && System.currentTimeMillis() - lastPressProcessed > 500)
                {
                    if(!(activeTextField.getName()).equals(""+(obj.r*obj.c-1)))
                    {
                        activeTextField=((JTextField)((JPanel)((JPanel)((JViewport)((JScrollPane)(Vp.getComponent(0))).getComponent(0)).getComponent(0)).getComponent(4)).getComponent(Integer.parseInt(activeTextField.getName())+1));
                        activeTextField.requestFocus();
                        lastPressProcessed = System.currentTimeMillis();
                        spacer();
                    }
                }
                else if(e.getKeyCode()==KeyEvent.VK_LEFT && System.currentTimeMillis() - lastPressProcessed > 500)
                {
                    if(!(activeTextField.getName()).equals(""+0))
                    {
                        activeTextField=((JTextField)((JPanel)((JPanel)((JViewport)((JScrollPane)(Vp.getComponent(0))).getComponent(0)).getComponent(0)).getComponent(4)).getComponent(Integer.parseInt(activeTextField.getName())-1));
                        activeTextField.requestFocus();
                        lastPressProcessed = System.currentTimeMillis();
                        spacer();
                    }
                }
                else if(e.getKeyCode()==KeyEvent.VK_UP && System.currentTimeMillis() - lastPressProcessed > 500)
                {
                    if(Integer.parseInt(activeTextField.getName())>obj.c-1)
                    {
                        activeTextField=((JTextField)((JPanel)((JPanel)((JViewport)((JScrollPane)(Vp.getComponent(0))).getComponent(0)).getComponent(0)).getComponent(4)).getComponent(Integer.parseInt(activeTextField.getName())-obj.c));
                        activeTextField.requestFocus();
                        lastPressProcessed = System.currentTimeMillis();
                        spacer();
                    }
                }
                else if(e.getKeyCode()==KeyEvent.VK_DOWN && System.currentTimeMillis() - lastPressProcessed > 500)
                {
                    if(Integer.parseInt(activeTextField.getName())<(obj.c*(obj.r-1)))
                    {
                        activeTextField=((JTextField)((JPanel)((JPanel)((JViewport)((JScrollPane)(Vp.getComponent(0))).getComponent(0)).getComponent(0)).getComponent(4)).getComponent(Integer.parseInt(activeTextField.getName())+obj.c));
                        activeTextField.requestFocus();
                        lastPressProcessed = System.currentTimeMillis();
                        spacer();
                    }
                }
            }
        });
    }
    public void setArray()
    {
        ((JPanel)((JViewport)(((JScrollPane)(Vp.getComponent(0))).getComponent(0))).getComponent(0)).getComponent(6).addMouseListener(new MouseAdapter() 
        {
            @Override
            public void mouseClicked(MouseEvent e) 
            {
                try 
                {
                    setArrayOnAction();
                } 
                catch (SQLException ex) 
                {
                    Logger.getLogger(MatrixBasics.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        activeTextField.addKeyListener(new KeyAdapter() 
        {
            @Override
            public void keyPressed(KeyEvent e) 
            {
                
                if(e.getKeyCode()==KeyEvent.VK_ENTER)
                {
                    try 
                    {
                        setArrayOnAction();
                    } 
                    catch (SQLException ex) 
                    {
                        Logger.getLogger(MatrixBasics.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        spacer();
        
    }
      
    public int element(String mtName,int i,int j) throws SQLException
    {
        matrixName=mtName;
        String mProp2[]={"c"+j};
        rs=(dbEle.getTableData((dbEle.dbmsUrl+dbEle.dbmsName),dbEle.dbmsUsername,dbEle.dbmsPassword,(dbEle.getTableDataStatement(matrixName,mProp2,""))));
        for(int k=0;k<=i;k++)
        rs.next();
        return (rs.getInt(1));
    }
    public int row(String mtName) throws SQLException
    {
        matrixName=mtName;
        clause[2][0]=matrixName;
        String mProp2[]={"Row"};
        rs=(dbEle.getTableData((dbEle.dbmsUrl+dbEle.dbmsName),dbEle.dbmsUsername,dbEle.dbmsPassword,(dbEle.getTableDataStatement("MatrixProperties",mProp2,(dbEle.createClauseStatement("Where", clause))))));
        rs.next();
        return (rs.getInt("Row"));
    }
    public int column(String mtName) throws SQLException
    {
        matrixName=mtName;
        clause[2][0]=matrixName;
        String mProp2[]={"Col"};
        rs=(dbEle.getTableData((dbEle.dbmsUrl+dbEle.dbmsName),dbEle.dbmsUsername,dbEle.dbmsPassword,(dbEle.getTableDataStatement("MatrixProperties",mProp2,(dbEle.createClauseStatement("Where", clause))))));
        rs.next();
        return (rs.getInt("Col"));
    }
    public void showArray(String mtName) throws SQLException
    {
        matrixName=mtName;
        obj.r = row(mtName);
        obj.c = column(mtName);
        obj.globalVariableUpdater();
        obj.base.setTitle("Matrix Calculator : Resultant Matrix");
        Vp=obj.repainter("vP3","",true);
        Vp=obj.repainter("vP3","",true);
        int cnt=0;
        ((JTextField)((JPanel)((JViewport)(((JScrollPane)(Vp.getComponent(0))).getComponent(0))).getComponent(0)).getComponent(6)).setText("Exit!");
        for(int i=0;i<obj.r;i++)
        {
            for(int j=0;j<obj.c;j++)
            {
                ((JTextField)((JPanel)((JPanel)((JViewport)((JScrollPane)(Vp.getComponent(0))).getComponent(0)).getComponent(0)).getComponent(4)).getComponent(cnt++)).setText((element(matrixName,i,j))+"");
            }
        }
        ((JPanel)((JViewport)(((JScrollPane)(Vp.getComponent(0))).getComponent(0))).getComponent(0)).getComponent(6).addMouseListener(new MouseAdapter() 
        {
            @Override
            public void mouseClicked(MouseEvent e) 
            {
                try 
                {
                    dbEle.deleteDB(dbEle.dbmsUrl, dbEle.dbmsUsername, dbEle.dbmsPassword, dbEle.dbmsName);
                } 
                catch (SQLException ex) 
                {
                    Logger.getLogger(MatrixBasics.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.exit(0);
                
            }
        });
    }
    public void dBDefinition(String mtName, String MatrixValues[], int r,int c) throws SQLException
    {
        matrixName=mtName;
        rowData[0]="'"+matrixName+"'";
        rowData[1]=r+"";
        dbEle.InsertTableData((dbEle.dbmsUrl+dbEle.dbmsName),dbEle.dbmsUsername,dbEle.dbmsPassword,dbEle.insertTableStatement("MatrixProperties",rowData));
        columnData[1][0]=c+"";
        clause[2][0]=matrixName;
        dbEle.EditTableData((dbEle.dbmsUrl+dbEle.dbmsName),dbEle.dbmsUsername,dbEle.dbmsPassword,(dbEle.editTableStatement("MatrixProperties",columnData,(dbEle.createClauseStatement("Where", clause)))));
        String tableHeadings[][]=new String[2][c];
        for(int i=0;i<c;i++)
        {
            tableHeadings[0][i]="c"+i;
            tableHeadings[1][i]="INTEGER";
        }
        dbEle.createTable((dbEle.dbmsUrl+dbEle.dbmsName),dbEle.dbmsUsername,dbEle.dbmsPassword,(dbEle.createTableStatement(matrixName,tableHeadings,"")));
        dbEle.InsertTableData((dbEle.dbmsUrl+dbEle.dbmsName),dbEle.dbmsUsername,dbEle.dbmsPassword,dbEle.insertTableStatement(matrixName,MatrixValues,r,c));
    }
    public void setEquation() throws ClassNotFoundException, SQLException
    {
        Vp=obj.basicSetup();
        panelFunc("vP2e");
        ((JTextField)((JPanel)(Vp.getComponent(0))).getComponent(1)).requestFocus();
        dbEle.registerDriver(dbEle.jdbcDriver);
        dbEle.createDB(dbEle.dbmsUrl,dbEle.dbmsUsername,dbEle.dbmsPassword,dbEle.dbmsName);
        dbEle.createTable((dbEle.dbmsUrl+dbEle.dbmsName),dbEle.dbmsUsername,dbEle.dbmsPassword,(dbEle.createTableStatement("MatrixProperties",tableHeadings1,tableHeadings1[0][0])));
        (((JPanel)(Vp.getComponent(0))).getComponent(2)).addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                try
                {
                    eq=((new EquationHandler()).infixtoPostfix(((JTextField)((JPanel)(Vp.getComponent(0))).getComponent(1)).getText().toString().trim(),operators));
                    copy=eq;
                    setRow();
                }
                catch (Exception ex)
                {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        activeTextField.addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyPressed(KeyEvent e)
            {
                if(e.getKeyCode()==KeyEvent.VK_ENTER)
                {
                    try
                    {
                        eq=((new EquationHandler()).infixtoPostfix(((JTextField)((JPanel)(Vp.getComponent(0))).getComponent(1)).getText().toString().trim(),operators));
                        copy=eq;
                        setRow();
                    }
                    catch (Exception ex)
                    {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
    }
}
