package zenOSSJSONFormat;

public class Component
{
    private String uid;

    private String text;

    private String uuid;

    private String url;

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

    public String getUuid ()
    {
        return uuid;
    }

    public void setUuid (String uuid)
    {
        this.uuid = uuid;
    }

    public String getUrl ()
    {
        return url;
    }

    public void setUrl (String url)
    {
        this.url = url;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [uid = "+uid+", text = "+text+", uuid = "+uuid+", url = "+url+"]";
    }
}