package zenOSSJSONFormat;

public class Events
{
    private String summary;

    private String clearid;

    private String count;

    private String dedupid;

    private String eventGroup;

    private String ntevid;

    private String DevicePriority;

    private String eventClassMapping;

    private String id;

    private String eventClassKey;

    private String facility;

    private Details details;

    private Component component;

    private String priority;

    private String ipAddress;

    private String eventState;

    private String monitor;

    private String evid;

    private String lastTime;

    private String[] DeviceGroups;

    private String[] Location;

    private String severity;

    private String ownerid;

    private String agent;

    private EventClass eventClass;

    private String message;

    private String firstTime;

    private String eventKey;

    private DeviceClass[] DeviceClass;

    private Device device;

    private String[] Systems;

    private String stateChange;

    private String prodState;

    public String getSummary ()
    {
        return summary;
    }

    public void setSummary (String summary)
    {
        this.summary = summary;
    }

    public String getClearid ()
    {
        return clearid;
    }

    public void setClearid (String clearid)
    {
        this.clearid = clearid;
    }

    public String getCount ()
    {
        return count;
    }

    public void setCount (String count)
    {
        this.count = count;
    }

    public String getDedupid ()
    {
        return dedupid;
    }

    public void setDedupid (String dedupid)
    {
        this.dedupid = dedupid;
    }

    public String getEventGroup ()
    {
        return eventGroup;
    }

    public void setEventGroup (String eventGroup)
    {
        this.eventGroup = eventGroup;
    }

    public String getNtevid ()
    {
        return ntevid;
    }

    public void setNtevid (String ntevid)
    {
        this.ntevid = ntevid;
    }

    public String getDevicePriority ()
    {
        return DevicePriority;
    }

    public void setDevicePriority (String DevicePriority)
    {
        this.DevicePriority = DevicePriority;
    }

    public String getEventClassMapping ()
    {
        return eventClassMapping;
    }

    public void setEventClassMapping (String eventClassMapping)
    {
        this.eventClassMapping = eventClassMapping;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getEventClassKey ()
    {
        return eventClassKey;
    }

    public void setEventClassKey (String eventClassKey)
    {
        this.eventClassKey = eventClassKey;
    }

    public String getFacility ()
    {
        return facility;
    }

    public void setFacility (String facility)
    {
        this.facility = facility;
    }

    public Details getDetails ()
    {
        return details;
    }

    public void setDetails (Details details)
    {
        this.details = details;
    }

    public Component getComponent ()
    {
        return component;
    }

    public void setComponent (Component component)
    {
        this.component = component;
    }

    public String getPriority ()
    {
        return priority;
    }

    public void setPriority (String priority)
    {
        this.priority = priority;
    }

    public String getIpAddress ()
    {
        return ipAddress;
    }

    public void setIpAddress (String ipAddress)
    {
        this.ipAddress = ipAddress;
    }

    public String getEventState ()
    {
        return eventState;
    }

    public void setEventState (String eventState)
    {
        this.eventState = eventState;
    }

    public String getMonitor ()
    {
        return monitor;
    }

    public void setMonitor (String monitor)
    {
        this.monitor = monitor;
    }

    public String getEvid ()
    {
        return evid;
    }

    public void setEvid (String evid)
    {
        this.evid = evid;
    }

    public String getLastTime ()
    {
        return lastTime;
    }

    public void setLastTime (String lastTime)
    {
        this.lastTime = lastTime;
    }

    public String[] getDeviceGroups ()
    {
        return DeviceGroups;
    }

    public void setDeviceGroups (String[] DeviceGroups)
    {
        this.DeviceGroups = DeviceGroups;
    }

    public String[] getLocation ()
    {
        return Location;
    }

    public void setLocation (String[] Location)
    {
        this.Location = Location;
    }

    public String getSeverity ()
    {
        return severity;
    }

    public void setSeverity (String severity)
    {
        this.severity = severity;
    }

    public String getOwnerid ()
    {
        return ownerid;
    }

    public void setOwnerid (String ownerid)
    {
        this.ownerid = ownerid;
    }

    public String getAgent ()
    {
        return agent;
    }

    public void setAgent (String agent)
    {
        this.agent = agent;
    }

    public EventClass getEventClass ()
    {
        return eventClass;
    }

    public void setEventClass (EventClass eventClass)
    {
        this.eventClass = eventClass;
    }

    public String getMessage ()
    {
        return message;
    }

    public void setMessage (String message)
    {
        this.message = message;
    }

    public String getFirstTime ()
    {
        return firstTime;
    }

    public void setFirstTime (String firstTime)
    {
        this.firstTime = firstTime;
    }

    public String getEventKey ()
    {
        return eventKey;
    }

    public void setEventKey (String eventKey)
    {
        this.eventKey = eventKey;
    }

    public DeviceClass[] getDeviceClass ()
    {
        return DeviceClass;
    }

    public void setDeviceClass (DeviceClass[] DeviceClass)
    {
        this.DeviceClass = DeviceClass;
    }

    public Device getDevice ()
    {
        return device;
    }

    public void setDevice (Device device)
    {
        this.device = device;
    }

    public String[] getSystems ()
    {
        return Systems;
    }

    public void setSystems (String[] Systems)
    {
        this.Systems = Systems;
    }

    public String getStateChange ()
    {
        return stateChange;
    }

    public void setStateChange (String stateChange)
    {
        this.stateChange = stateChange;
    }

    public String getProdState ()
    {
        return prodState;
    }

    public void setProdState (String prodState)
    {
        this.prodState = prodState;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [summary = "+summary+", clearid = "+clearid+", count = "+count+", dedupid = "+dedupid+", eventGroup = "+eventGroup+", ntevid = "+ntevid+", DevicePriority = "+DevicePriority+", eventClassMapping = "+eventClassMapping+", id = "+id+", eventClassKey = "+eventClassKey+", facility = "+facility+", details = "+details+", component = "+component+", priority = "+priority+", ipAddress = "+ipAddress+", eventState = "+eventState+", monitor = "+monitor+", evid = "+evid+", lastTime = "+lastTime+", DeviceGroups = "+DeviceGroups+", Location = "+Location+", severity = "+severity+", ownerid = "+ownerid+", agent = "+agent+", eventClass = "+eventClass+", message = "+message+", firstTime = "+firstTime+", eventKey = "+eventKey+", DeviceClass = "+DeviceClass+", device = "+device+", Systems = "+Systems+", stateChange = "+stateChange+", prodState = "+prodState+"]";
    }
}
