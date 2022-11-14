package com.cuscuzmachine;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Growthbook {

    private Integer status;
    private Map<String, Feature> features;
    private LocalDateTime dateUpdated;

    public Boolean isOn(String featureName){

        Feature f = this.features.get(featureName);

        if(f != null){
            if(f.getDefaultValue() instanceof Boolean)
                return (Boolean) f.getDefaultValue();
        }

        return false;
    }

    public String getStringFeatureValue(String featureName){
        return getFeatureValue(featureName, String.class);
    }
    public Boolean getBooleanFeatureValue(String featureName){
        return getFeatureValue(featureName, Boolean.class);
    }
    public <E> E getFeatureValue(String featureName, Class<E> e){
        Feature f = this.features.get(featureName);

        if(f != null && f.getDefaultValue() != null ){
            return OMUtils.getOB().convertValue(f.getDefaultValue(), e);
        }

        return null;
    }

}
