package SS1;

class Project {
    private int id;
    private double priority;
    private int assignedResourceId; // ID của tài nguyên được phân bổ cho dự án

    public Project(int id, double priority) {
        this.id = id;
        this.priority = priority;
        this.assignedResourceId = -1; // Chưa được phân bổ tài nguyên nào
    }

    public int getId() {
        return id;
    }

    public double getPriority() {
        return priority;
    }

    public int getAssignedResourceId() {
        return assignedResourceId;
    }

    public void setAssignedResourceId(int assignedResourceId) {
        this.assignedResourceId = assignedResourceId;
    }
}

