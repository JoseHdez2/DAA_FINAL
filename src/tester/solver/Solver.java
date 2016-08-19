package tester.solver;

import convenience.OptionsHash;
import tester.problem.Problem;
import tester.solution.Solution;

public interface Solver {

    Solution solve(Problem p, OptionsHash opt) throws Exception;
}
