/*  Class LinkList  */
class LinkList
{
    protected String data;
    protected LinkList link;
     /*  Constructor  */
    public LinkList()
    {
        link = null;
        data = null;
    }    
    /*  Constructor  */
    public LinkList(String d,LinkList n)
    {
        data = d;
        link = n;
    }    
    /*  Function to set link to next LinkList  */
    public void setLink(LinkList n)
    {
        link = n;
    }    
    /*  Function to set data to current LinkList  */
    public void setData(String d)
    {
        data = d;
    }    
    /*  Function to get link to next node  */
    public LinkList getLink()
    {
        return link;
    }    
    /*  Function to get data from current LinkList  */
    public String getData()
    {
        return data;
    }
}