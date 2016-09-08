package main.solution;

import java.util.ArrayList;

public class SolutionTSP extends ArrayList<Integer> implements Solution{
    public Integer getLatest() {
        return this.get(this.size()-1);
    }
}
