package tester;

import java.util.ArrayList;

public class SolutionTSP extends ArrayList<Integer> implements Solution{
    Integer getLatest() {
        return this.get(this.size()-1);
    }
}
