//package Test;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//import org.moeaframework.Analyzer;
//import org.moeaframework.Executor;
//import org.moeaframework.Instrumenter;
//import org.moeaframework.core.NondominatedPopulation;
//import org.moeaframework.core.Problem;
//import org.moeaframework.core.Solution;
//import org.moeaframework.core.Variable;
//import org.moeaframework.core.operator.real.SBX;
//import org.moeaframework.core.operator.real.PolynomialMutation;
//import org.moeaframework.core.operator.real.TournamentSelection;
//
//public class StableMatchingProblem implements Problem {
//
//    private int numStudents;
//    private int numProjects;
//    private int projectCapacity;
//    private int[][] studentPreferences;
//    private int[][] projectPreferences;
//
//    public StableMatchingProblem(int numStudents, int numProjects, int projectCapacity, int[][] studentPreferences, int[][] projectPreferences) {
//        this.numStudents = numStudents;
//        this.numProjects = numProjects;
//        this.projectCapacity = projectCapacity;
//        this.studentPreferences = studentPreferences;
//        this.projectPreferences = projectPreferences;
//    }
//
//    @Override
//    public int getNumberOfVariables() {
//        return numStudents;
//    }
//
//    @Override
//    public int getNumberOfObjectives() {
//        return 2;
//    }
//
//    @Override
//    public int getNumberOfConstraints() {
//        return 0;
//    }
//
//    @Override
//    public Variable[] getVariables() {
//        Variable[] variables = new Variable[numStudents];
//        for (int i = 0; i < numStudents; i++) {
//            variables[i] = new Variable(0, numProjects - 1);
//        }
//        return variables;
//    }
//
//    @Override
//    public void evaluate(Solution solution) {
//        int[] assignment = new int[numStudents];
//        for (int i = 0; i < numStudents; i++) {
//            assignment[i] = (int) solution.getVariable(i).getValue();
//        }
//
//        int totalSatisfaction = 0;
//        int[] projectCounts = new int[numProjects];
//        for (int i = 0; i < numStudents; i++) {
//            int project = assignment[i];
//            totalSatisfaction += studentPreferences[i][project];
//            projectCounts[project]++;
//        }
//
//        for (int i = 0; i < numProjects; i++) {
//            if (projectCounts[i] > projectCapacity) {
//                totalSatisfaction -= 100; // penalty for exceeding project capacity
//            }
//        }
//
//        solution.setObjective(0, totalSatisfaction);
//        solution.setObjective(1, -totalSatisfaction); // maximize satisfaction, minimize penalty
//    }
//
//    public static void main(String[] args) {
//        int numStudents = 10;
//        int numProjects = 5;
//        int projectCapacity = 2;
//
//        int[][] studentPreferences = {
//                {3, 2, 1, 4, 0},
//                {2, 1, 3, 0, 4},
//                {1, 3, 2, 4, 0},
//                {4, 0, 2, 1, 3},
//                {0, 4, 1, 2, 3},
//                {2, 1, 4, 3, 0},
//                {1, 3, 0, 2, 4},
//                {3, 2, 4, 1, 0},
//                {0, 1, 2, 3, 4},
//                {4, 3, 1, 0, 2}
//        };
//
//        int[][] projectPreferences = {
//                {0, 1, 2, 3, 4},
//                {1, 2, 3, 4, 0},
//                {2, 3, 4, 0, 1},
//                {3, 4, 0, 1, 2},
//                {4, 0, 1, 2, 3}
//        };
//
//        StableMatchingProblem problem = new StableMatchingProblem(numStudents, numProjects, projectCapacity, studentPreferences, projectPreferences);
//
//        Executor executor = new Executor();
//        executor.withProblem(problem)
//                .withAlgorithm("NSGAII")
//                .withMaxEvaluations(10000)
//                .withPopulationSize(100)
//                .withOperator(new SBX(20, 0.5))
//                .withOperator(new PolynomialMutation(20, 0.1))
//                .withOperator(new TournamentSelection(2))
//                .run();
//
//        NondominatedPopulation population = executor.getResult();
//        Analyzer analyzer = new Analyzer();
//        analyzer.setProblem(problem);
//        analyzer.display(population);
//    }
//}
