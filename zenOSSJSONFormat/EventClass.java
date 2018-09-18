package zenOSSJSONFormat;

public class EventClass
{
    private String uid;

    private String text;

    public String getUid ()
    {
        return uid;
    }

    public void setUid (String uid)
    {
        this.uid = uid;
    }

    public String getText ()
    {
        return text;
    }

    public void setText (String text)
    {
        this.text = text;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [uid = "+uid+", text = "+text+"]";
    }
}
