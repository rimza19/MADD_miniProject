package com.example.waterusage.model;

import java.util.Objects;

public class Water {
    public String getContainerType() {
        return containerType;
    }

    public void setContainerType(String containerType) {
        this.containerType = containerType;
    }

    private String containerType;

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    private int volume;

    public Water(String containerType,int volume) {
        this.containerType = containerType;
        this.volume = volume;
    }

    public boolean equals(Object o){

        if (o instanceof Water)
            return containerType.equals(((Water) o).containerType);
        else
            return false;
    }

}
