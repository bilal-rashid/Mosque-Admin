package com.islamabad.timing.namaztimings.model;

import com.islamabad.timing.namaztimings.utils.AppUtils;

/**
 * Created by Bilal Rashid on 7/23/2018.
 */

public class Mosque {
    public String name;
    public String location;
    public String fajar;
    public String zohar;
    public String asar;
    public String magrib;
    public String isha;
    public String juma;
    public String eid;
    public String last_updated;
    public String id;
    public String notes;

    @Override
    public boolean equals(Object obj) {
        Mosque mosque = (Mosque) obj;
        if(mosque.fajar.equals(this.fajar) &&
                mosque.zohar.equals(this.zohar) &&
                mosque.asar.equals(this.asar) &&
                mosque.magrib.equals(this.magrib) &&
                mosque.isha.equals(this.isha) &&
                mosque.notes.equals(this.notes) &&
                mosque.juma.equals(this.juma) &&
                mosque.eid.equals(this.eid) ){
            return true;

        }
        return false;
    }
    public String compare(Mosque mosque){
        String result="";
        if(!mosque.fajar.equals(this.fajar)){
            result = result+"Fajar Time Changed From "+ AppUtils.getMosqueTime(this.fajar) +" to "
            +AppUtils.getMosqueTime(mosque.fajar)+"\n";
        }
        if(!mosque.zohar.equals(this.zohar)){
            result = result+"Zohar Time Changed From "+ AppUtils.getMosqueTime(this.zohar) +" to "
                    +AppUtils.getMosqueTime(mosque.zohar)+"\n";
        }
        if(!mosque.asar.equals(this.asar)){
            result = result+"Asar Time Changed From "+ AppUtils.getMosqueTime(this.asar) +" to "
                    +AppUtils.getMosqueTime(mosque.asar)+"\n";
        }
        if(!mosque.magrib.equals(this.magrib)){
            result = result+"Magrib Time Changed From "+ AppUtils.getMosqueTime(this.magrib) +" to "
                    +AppUtils.getMosqueTime(mosque.magrib)+"\n";
        }
        if(!mosque.isha.equals(this.isha)){
            result = result+"Isha Time Changed From "+ AppUtils.getMosqueTime(this.isha) +" to "
                    +AppUtils.getMosqueTime(mosque.isha)+"\n";
        }
        if(!mosque.juma.equals(this.juma)){
            result = result+"Juma Time Changed From "+ AppUtils.getMosqueTime(this.juma) +" to "
                    +AppUtils.getMosqueTime(mosque.juma)+"\n";
        }
        if(!mosque.eid.equals(this.eid)){
            result = result+"Eid Time Changed to "
                    +AppUtils.getMosqueTime(mosque.eid)+"\n";
        }
        if(!mosque.notes.equals(this.notes)){
            result = result+mosque.notes+"\n";
        }
        return result;
    }
}
