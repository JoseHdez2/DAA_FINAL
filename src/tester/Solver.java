package tester;

import tester.problem.Problem;

public interface Solver {

    OptionsHash solve(Problem p, OptionsHash opt);
}
