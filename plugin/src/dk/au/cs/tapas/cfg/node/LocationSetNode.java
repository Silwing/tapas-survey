package dk.au.cs.tapas.cfg.node;

import dk.au.cs.tapas.lattice.HeapLocation;
import dk.au.cs.tapas.lattice.TemporaryHeapVariableName;

import java.util.Set;

/**
 * Created by budde on 4/27/15.
 */
public interface LocationSetNode extends Node {

    TemporaryHeapVariableName getTargetTempHeapName();

}
