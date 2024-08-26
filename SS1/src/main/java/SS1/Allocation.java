package SS1;

import java.util.*;

class Allocation {
    List<Integer> resourceAssignments; // Danh sách phân bổ tài nguyên
    double cost;
    double benefit;
    List<Resource> resources;
    List<Project> projects;

    public Allocation(int numProjects, List<Resource> resources, List<Project> projects) {
        this.resources = resources;
        this.projects = projects;
        resourceAssignments = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < numProjects; i++) {
            resourceAssignments.add(rand.nextInt(resources.size())); // Sử dụng kích thước của resources
        }
        this.cost = calculateCost();
        this.benefit = calculateBenefit();
    }

    private double calculateCost() {
        double totalCost = 0;
        for (int resourceId : resourceAssignments) {
            totalCost += resources.get(resourceId).getCost();
        }
        return totalCost;
    }

    private double calculateBenefit() {
        double totalBenefit = 0;
        for (int resourceId : resourceAssignments) {
            totalBenefit += resources.get(resourceId).getBenefit();
        }
        return totalBenefit;
    }

    public boolean isStable() {
        // Tạo bản đồ để theo dõi phân bổ hiện tại
        Map<Integer, Integer> projectToResource = new HashMap<>();
        for (int i = 0; i < projects.size(); i++) {
            projectToResource.put(projects.get(i).getId(), resourceAssignments.get(i));
        }

        // Kiểm tra sự ổn định
        for (int projectIndex = 0; projectIndex < projects.size(); projectIndex++) {
            Project project = projects.get(projectIndex);
            int currentResourceId = projectToResource.get(project.getId());

            // Xét tất cả tài nguyên không được phân bổ cho dự án này
            for (int resourceIndex = 0; resourceIndex < resources.size(); resourceIndex++) {
                if (resourceIndex == currentResourceId) continue; // Bỏ qua tài nguyên hiện tại

                Resource resource = resources.get(resourceIndex);
                // Kiểm tra nếu tài nguyên này sẽ tạo sự không ổn định
                for (int otherProjectIndex = 0; otherProjectIndex < projects.size(); otherProjectIndex++) {
                    if (otherProjectIndex == projectIndex) continue; // Bỏ qua dự án hiện tại

                    Project otherProject = projects.get(otherProjectIndex);
                    int otherResourceId = projectToResource.get(otherProject.getId());
                    Resource otherResource = resources.get(otherResourceId);

                    // Kiểm tra xem dự án khác có ưu tiên cao hơn cho tài nguyên này không
                    if (resource.getBenefit() > otherResource.getBenefit() && otherProject.getPriority() > project.getPriority()) {
                        return false; // Phân bổ không ổn định
                    }
                }
            }
        }
        return true; // Phân bổ ổn định
    }
}


