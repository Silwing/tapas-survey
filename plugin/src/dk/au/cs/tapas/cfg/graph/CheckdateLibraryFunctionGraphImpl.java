package dk.au.cs.tapas.cfg.graph;

import dk.au.cs.tapas.analysis.AnalysisAnnotator;
import dk.au.cs.tapas.cfg.TemporaryVariableCallArgument;
import dk.au.cs.tapas.cfg.node.ResultNode;
import dk.au.cs.tapas.lattice.Context;
import dk.au.cs.tapas.lattice.element.AnalysisLatticeElement;
import dk.au.cs.tapas.lattice.element.BooleanLatticeElement;
import dk.au.cs.tapas.lattice.element.ValueLatticeElement;
import dk.au.cs.tapas.lattice.element.ValueLatticeElementImpl;
import org.jetbrains.annotations.NotNull;

/**
 * Created by Silwing on 27-05-2015.
 */
public class CheckdateLibraryFunctionGraphImpl extends LibraryFunctionGraphImpl {

    public CheckdateLibraryFunctionGraphImpl() {
        super(new boolean[]{ false, false, false }, false);
    }

    @NotNull
    @Override
    public AnalysisLatticeElement analyse(@NotNull ResultNode resultNode, @NotNull Context context, @NotNull AnalysisLatticeElement analysisLatticeElement, @NotNull AnalysisAnnotator annotator) {
        // Simple naive implementation
        return analysisLatticeElement.setTempsValue(context, ((TemporaryVariableCallArgument)resultNode.getCallArgument()).getArgument(), new ValueLatticeElementImpl(BooleanLatticeElement.top));
    }
}
