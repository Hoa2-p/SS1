package SS1;

import java.util.ArrayList;
import java.util.List;

public class ResourceAllocationPoC {
    public static void main(String[] args) {
        int numProjects = 5;
        int numResources = 20;

        List<Project> projects = new ArrayList<>();
        List<Resource> resources = new ArrayList<>();

        // Tạo danh sách tài nguyên với ID, chi phí và lợi ích
        for (int i = 0; i < numResources; i++) {
            resources.add(new Resource(i, Math.random() * 100, Math.random() * 200));
        }

        // Tạo danh sách dự án với ID và độ ưu tiên
        for (int i = 0; i < numProjects; i++) {
            projects.add(new Project(i, Math.random()));
        }

        MOEA moea = new MOEA(numProjects, numResources, resources, projects);
        moea.runAlgorithm();
        moea.applySMT();

        for (Allocation allocation : moea.population) {
            System.out.println("Allocation: " + allocation.resourceAssignments + ", Cost: " + allocation.cost + ", Benefit: " + allocation.benefit);
        }
    }
}


