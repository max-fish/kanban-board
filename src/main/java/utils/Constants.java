package utils;

public class Constants {
//    public class ColumnRoles {
//        public static final String BACKLOG = "Backlog";
//        public static final String WORK_IN_PROGRESS = "Work in Progress";
//        public static final String ON_HOLD = "On Hold";
//        public static final String COMPLETED_WORK = "Completed Work";
//        public static final String INFO_ONLY = "Info Only";
//    }

    public enum ColumnRole {
        BACKLOG("Backlog"),
        WORK_IN_PROGRESS("Work in Progress"),
        ON_HOLD("On Hold"),
        COMPLETED_WORK("Completed Work"),
        INFO_ONLY("Info Only");

        public final String roleString;

        ColumnRole(String roleString){
            this.roleString = roleString;
        }

        public static ColumnRole getEnumFromRoleString(String roleString){
            switch (roleString){
                case "Backlog": return BACKLOG;
                case "Work in Progress": return WORK_IN_PROGRESS;
                case "On Hold": return ON_HOLD;
                case "Completed Work": return COMPLETED_WORK;
                case "Info Only": return INFO_ONLY;
                default: throw new IllegalArgumentException("Enum for role string does not exist");
            }
        }
    }
}
