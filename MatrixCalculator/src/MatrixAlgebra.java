
import java.sql.SQLException;
public class MatrixAlgebra extends MatrixBasics 
{
    public String add(String matrixName2,String matrixName1) throws SQLException
    {
        int r=super.row(matrixName1);
        int c=super.column(matrixName1);
        if((r!=super.row(matrixName2)) || (c!=super.column(matrixName2)))
            return "";
        String res[]=new String[r*c];
        int cnt=0;
        for(int i=0;i<r;i++)
        {
            for(int j=0;j<c;j++)
            {
                res[cnt++]=super.element(matrixName1,i,j)+super.element(matrixName2,i,j)+"";
            }
        }
        super.dBDefinition(matrixName1+"_Add_"+matrixName2, res, r, c);
        return matrixName1+"_Add_"+matrixName2;
    }
    
}
