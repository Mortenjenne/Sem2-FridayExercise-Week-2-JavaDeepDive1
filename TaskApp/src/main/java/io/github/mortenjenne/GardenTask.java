package io.github.mortenjenne;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@SuperBuilder
public class GardenTask extends Task implements Serializable {
    String gardenLocation;

    @Override
    public String toString() {
        return String.format(super.toString() + "Gardenlocation: %s \n",gardenLocation);
    }

}
