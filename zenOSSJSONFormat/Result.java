package zenOSSJSONFormat;

public class Result
{
    private String totalCount;

    private Events[] events;

    private String asof;

    private String success;

    public String getTotalCount ()
    {
        return totalCount;
    }

    public void setTotalCount (String totalCount)
    {
        this.totalCount = totalCount;
    }

    public Events[] getEvents ()
    {
        return events;
    }

    public void setEvents (Events[] events)
    {
        this.events = events;
    }

    public String getAsof ()
    {
        return asof;
    }

    public void setAsof (String asof)
    {
        this.asof = asof;
    }

    public String getSuccess ()
    {
        return success;
    }

    public void setSuccess (String success)
    {
        this.success = success;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [totalCount = "+totalCount+", events = "+events+", asof = "+asof+", success = "+success+"]";
    }
}
			
		
