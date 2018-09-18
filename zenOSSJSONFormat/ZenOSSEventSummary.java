package zenOSSJSONFormat;

public class ZenOSSEventSummary
{
    private Result result;

    private String action;

    private String method;

    private String tid;

    private String uuid;

    private String type;

    public Result getResult ()
    {
        return result;
    }

    public void setResult (Result result)
    {
        this.result = result;
    }

    public String getAction ()
    {
        return action;
    }

    public void setAction (String action)
    {
        this.action = action;
    }

    public String getMethod ()
    {
        return method;
    }

    public void setMethod (String method)
    {
        this.method = method;
    }

    public String getTid ()
    {
        return tid;
    }

    public void setTid (String tid)
    {
        this.tid = tid;
    }

    public String getUuid ()
    {
        return uuid;
    }

    public void setUuid (String uuid)
    {
        this.uuid = uuid;
    }

    public String getType ()
    {
        return type;
    }

    public void setType (String type)
    {
        this.type = type;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [result = "+result+", action = "+action+", method = "+method+", tid = "+tid+", uuid = "+uuid+", type = "+type+"]";
    }
}
