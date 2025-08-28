package io.github.mortenjenne;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class GardenTask extends Task{
    String gardenLocation;

}
