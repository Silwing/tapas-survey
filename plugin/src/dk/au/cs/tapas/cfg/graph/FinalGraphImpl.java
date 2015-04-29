package dk.au.cs.tapas.cfg.graph;

import dk.au.cs.tapas.analysis.ContextNodePair;
import dk.au.cs.tapas.analysis.ContextNodePairImpl;
import dk.au.cs.tapas.cfg.node.CallNode;
import dk.au.cs.tapas.cfg.node.ExitNode;
import dk.au.cs.tapas.cfg.node.Node;
import dk.au.cs.tapas.lattice.Context;
import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * Created by budde on 4/28/15.
 */
public class FinalGraphImpl implements Graph{
    private final Node exitNode, entryNode;
    private final Map<String, FunctionGraph> functionGraphMap;
    private Set<Node> nodes;

    public FinalGraphImpl(Graph graph, Map<String, FunctionGraph> functionGraphMap) {

        this.functionGraphMap = functionGraphMap;
        entryNode = graph.getEntryNode();
        exitNode = findExitNode(entryNode);
        assert exitNode != null;

    }

    private Node findExitNode(Node entryNode) {
        return findExitNode(entryNode, new HashSet<>());
    }

    private Node findExitNode(Node entryNode, HashSet<Node> objects) {
        if (objects.contains(entryNode)) {
            return null;
        }

        if(entryNode.getSuccessors().length == 0){
            return entryNode;
        }

        for (Node child : entryNode.getSuccessors()) {
            Node prospect = findExitNode(child);
            if(prospect != null){
                return prospect;
            }
        }

        return null;
    }

    @NotNull
    @Override
    public Node getExitNode() {
        return exitNode;
    }

    @NotNull
    @Override
    public Node getEntryNode() {
        return entryNode;
    }

    @Override
    public Set<ContextNodePair> getFlow(ContextNodePair contextNodePair) {
        Node node = contextNodePair.getNode();
        Context context = contextNodePair.getContext();
        Set<ContextNodePair> nodes = new HashSet<>();
        if(node instanceof CallNode){
            nodes.add(new ContextNodePairImpl(
                    contextNodePair.getContext().addNode((CallNode) node),
                    functionGraphMap.get(((CallNode) node).getFunctionName()).getEntryNode()));
        } else if(node instanceof ExitNode){
            nodes.add(new ContextNodePairImpl(
                    contextNodePair.getContext().popNode(),
                    context.getLastCallNode().getResultNode()));
        } else {
            for(Node successor: node.getSuccessors()){
                nodes.add(new ContextNodePairImpl(contextNodePair.getContext(), successor));
            }
        }
        return nodes;
    }

    @Override
    public Set<Node> getNodes() {
        if(nodes != null){
            return new HashSet<>(nodes);
        }

        nodes = new HashSet<>();
        addNodes(entryNode, nodes);
        functionGraphMap.values().stream().forEach((FunctionGraph graph) -> nodes.addAll(graph.getNodes()));
        return getNodes();
    }

    private void addNodes(Node node, Set<Node> nodes) {
        if(nodes.add(node)){
            for(Node successor: node.getSuccessors()){
                addNodes(successor, nodes);
            }
        }
    }
}
