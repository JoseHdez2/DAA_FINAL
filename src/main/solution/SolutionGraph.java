package main.solution;

import java.util.ArrayList;

public class SolutionGraph extends ArrayList<Integer> implements Solution {

    
    /** Get latest element in solution. */
    public Integer getLatest() {
        return this.get(this.size()-1);
    }
}
