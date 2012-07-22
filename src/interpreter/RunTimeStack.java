package interpreter;

import java.util.*;

/*
 * The part of the Interpreter where all the heavy lifting gets done.
 *
 * From the reader:
 *
 * Records and processes the stack of active frames.
 *
 */
public class RunTimeStack {
    private ArrayDeque<Integer> framePointers;
    private ArrayList<Integer> runStack;
    private int numArgs;

    public RunTimeStack() {
        framePointers = new ArrayDeque<Integer>();
        runStack = new ArrayList<Integer>();
        framePointers.add(0);
    }

    public void dump() {
        ArrayDeque<Integer> framePointersCopy = framePointers.clone();
        ArrayList<Integer> framePointersList = new ArrayList<Integer>();

        // Since ArrayDeque does not inherit from ArrayList like Stack
        // inherits from Vector, we must copy the ArrayDeque's contents
        // into an ArrayList.
        while(!framePointersCopy.isEmpty()) {
            framePointersList.add(framePointersCopy.removeLast());
        }

        int first = framePointersList.remove(0);

        // Makes printing frames a lot easier by grouping each frame together by separating
        // the frames in runStack using the subList method in List which ArrayList inherits
        // from.
        while(!framePointersList.isEmpty()) {
            System.out.print(runStack.subList(first, framePointersList.get(0)) + " ");
            first = framePointersList.remove(0);
        }

        System.out.println(runStack.subList(first, runStack.size()));
    }

    public int peek() {
        return runStack.get(size()-1);
    }

    public int pop() {
        int temp = runStack.get(size()-1);

        runStack.remove(size()-1);

        return temp;
    }

    public int push(int i) {
        runStack.add(i);

        return i;
    }

    public void newFrameAt(int offset) {
        framePointers.push(offset);
    }

    public void popFrame() {
        int temp = peek();
        int pop = framePointers.pop(); 

        while(size()-1 >= pop) {
            if(!runStack.isEmpty()) {
                pop();
            }
        }

        push(temp);
    }

    public int store(int offset) {
        int temp = pop();

        runStack.set(offset, temp);

        return offset;
    }

    public int load(int offset) {
        int temp = runStack.get(framePointers.peek() + offset);

        runStack.add(temp);

        return offset;
    }

    public Integer push(Integer i) {
        runStack.add(i);

        return i;
    }

    public int size() {
        return runStack.size();
    }

    public void setNumArgs(int numArgs) {
        this.numArgs = numArgs;
    }

    public int getNumArgs() {
        return numArgs;
    }

    public int getElementAt(int element) {
        return runStack.get(element);
    }

    public int peekFramePointers() {
        return framePointers.peek();
    }
}
