package dk.au.cs.tapas.lattice;

/**
 * Created by budde on 4/19/15.
 *
 */
public interface LatticeElement<T extends LatticeElement> {

    T meet(T other);

    T join(T other);

    boolean containedIn(HeapMapLatticeElement thisAnalysis, T other, HeapMapLatticeElement otherAnalysis);

    void print(LatticePrinter printer);
}
