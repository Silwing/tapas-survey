package dk.au.cs.tapas.lattice;

/**
 * Created by budde on 4/20/15.
 */
public class IntegerIndexLatticeElementImpl implements IntegerIndexLatticeElement {
    private final IntegerLatticeElement integer;

    public IntegerIndexLatticeElementImpl(ValueIntegerLatticeElement element) {
        integer = element;
    }

    @Override
    public IntegerLatticeElement getInteger() {
        return integer;
    }

    @Override
    public IndexLatticeElement meet(IndexLatticeElement other) {
        if (other.equals(top)) {
            return this;
        }

        if (other instanceof IntegerIndexLatticeElement) {
            return IndexLatticeElement.generateIntegerIndex(((IntegerIndexLatticeElement) other).getInteger().meet(getInteger()));
        }

        return bottom;


    }

    @Override
    public IndexLatticeElement join(IndexLatticeElement other) {
        if (other.equals(bottom)) {
            return this;
        }

        if (other instanceof IntegerIndexLatticeElement) {
            return IndexLatticeElement.generateIntegerIndex(((IntegerIndexLatticeElement) other).getInteger().join(getInteger()));
        }

        return top;
    }

    @Override
    public boolean containedIn(HeapMapLatticeElement thisAnalysis, IndexLatticeElement other, HeapMapLatticeElement otherAnalysis) {
        return containedIn(other);
    }

    @Override
    public void print(LatticePrinter printer) {
        integer.print(printer);
    }

    @Override
    public boolean equals(Object other) {
        return other == this || (other instanceof IntegerIndexLatticeElement && ((IntegerIndexLatticeElement) other).getInteger().equals(getInteger()));
    }

    @Override
    public boolean containedIn(IndexLatticeElement other) {

        if (other instanceof IntegerIndexLatticeElement){
            return getInteger().containedIn(((IntegerIndexLatticeElement) other).getInteger());
        }

        return other instanceof TopIndexLatticeElementImpl;
    }
}
