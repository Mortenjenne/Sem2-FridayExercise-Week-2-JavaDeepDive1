package io.github.mortenjenne;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class GardenTask extends Task{
    String gardenLocation;

    @Override
    public String toString() {
        return String.format(super.toString() + "Gardenlocation: %s \n",gardenLocation);
    }

}
