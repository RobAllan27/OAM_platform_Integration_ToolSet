package zenOSSJSONFormat;

public class Details
{
    private String[] zenoss_device_device_class;

    // was originally zenoss_device_device_class -  renamed with zenoss_device_device_class
    
    private String[] zenoss_device_priority;

    // was originally zenoss_device_priority -  renamed with zenoss_device_priority
    
    private String[] zenoss_device_production_state;
    
    // was originally zenoss_device_production_state -  renamed with zenoss_device_production_state

    public String[] getzenoss_device_device_class ()
    {
        return zenoss_device_device_class;
    }

    public void setzenoss_device_device_class (String[] zenoss_device_device_class)
    {
        this.zenoss_device_device_class = zenoss_device_device_class;
    }

    public String[] getzenoss_device_priority ()
    {
        return zenoss_device_priority;
    }

    public void setzenoss_device_priority (String[] zenoss_device_priority)
    {
        this.zenoss_device_priority = zenoss_device_priority;
    }

    public String[] getzenoss_device_production_state ()
    {
        return zenoss_device_production_state;
    }

    public void setzenoss_device_production_state (String[] zenoss_device_production_state)
    {
        this.zenoss_device_production_state = zenoss_device_production_state;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [zenoss_device_device_class = "+zenoss_device_device_class+", zenoss_device_priority = "+zenoss_device_priority+", zenoss_device_production_state = "+zenoss_device_production_state+"]";
    }
}
