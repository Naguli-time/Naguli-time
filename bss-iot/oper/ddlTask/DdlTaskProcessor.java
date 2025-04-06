public class DdlTaskProcessor implements ItemProcessor<DdlTaskBO, DdlTaskBO> {

    @Override
    public DdlTaskBO process(DdlTaskBO task) throws Exception {
        String ddlScript = generateDdlScript(task);
        String rollbackScript = generateRollbackScript(task);
        
        task.getOperDdlTaskInfoVO().setDdlScript(ddlScript);
        task.getOperDdlTaskInfoVO().setRollbackScript(rollbackScript);
        return task;
    }

    private String generateDdlScript(DdlTaskBO task) {
        OperDdlTaskInfoVO info = task.getOperDdlTaskInfoVO();
        switch (info.getTaskType()) {
            case "TABLE_CREATE":
                return generateTableCreateScript(task);
            case "COLUMN_ADD":
                return generateColumnAddScript(task);
            case "INDEX_CREATE":
                return generateIndexCreateScript(task);
            case "SEQUENCE_CREATE":
                return generateSequenceCreateScript(task);
            default:
                throw new IllegalArgumentException("Unknown task type: " + info.getTaskType());
        }
    }
    
    private String generateRollbackScript(DdlTaskBO task) {
        OperDdlTaskInfoVO info = task.getOperDdlTaskInfoVO();
        switch (info.getTaskType()) {
            case "TABLE_CREATE":
                return "DROP TABLE " + info.getTargetName() + " CASCADE;";
            case "COLUMN_ADD":
                return "ALTER TABLE " + info.getTargetName() + " DROP COLUMN " + info.getColumnNames().split("&")[0] + ";";
            case "INDEX_CREATE":
                return "DROP INDEX " + info.getTargetName() + "_idx;";
            case "SEQUENCE_CREATE":
                return "DROP SEQUENCE " + info.getTargetName() + "_seq;";
            default:
                throw new IllegalArgumentException("Unknown task type: " + info.getTaskType());
        }
    }

    private String generateTableCreateScript(DdlTaskBO task) {
        OperDdlTaskInfoVO info = task.getOperDdlTaskInfoVO();
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE ").append(info.getTargetName()).append(" (");
        
        String[] colNames = info.getColumnNames().split("&");
        String[] colTypes = info.getColumnTypes().split("&");
        // 빈 문자열을 포함하도록 -1을 사용하여 split
        String[] constraints = info.getConstraintsTypes().split("&", -1);

        for (int i = 0; i < colNames.length; i++) {
            sb.append(colNames[i]).append(" ").append(colTypes[i]);
            if (!constraints[i].isEmpty()) {
                sb.append(" ").append(constraints[i].trim());
            }
            if (i < colNames.length - 1) {
                sb.append(", ");
            }
        }
        sb.append(");\n");

        // COMMENT 추가
        sb.append("COMMENT ON TABLE ").append(info.getTargetName()).append(" IS 'Table for ")
          .append(info.getTargetName()).append("';\n");
        for (String col : colNames) {
            sb.append("COMMENT ON COLUMN ").append(info.getTargetName()).append(".")
              .append(col).append(" IS 'Column for ").append(col).append("';\n");
        }
        return sb.toString();
    }

    private String generateColumnAddScript(DdlTaskBO task) {
        OperDdlTaskInfoVO info = task.getOperDdlTaskInfoVO();
        StringBuilder sb = new StringBuilder();
        String[] colNames = info.getColumnNames().split("&");
        String[] colTypes = info.getColumnTypes().split("&");

        for (int i = 0; i < colNames.length; i++) {
            sb.append("ALTER TABLE ").append(info.getTargetName())
              .append(" ADD COLUMN ").append(colNames[i])
              .append(" ").append(colTypes[i]).append(";\n");
            // COMMENT 추가
            sb.append("COMMENT ON COLUMN ").append(info.getTargetName()).append(".")
              .append(colNames[i]).append(" IS 'Column for ").append(colNames[i]).append("';\n");
        }
        return sb.toString();
    }

    private String generateIndexCreateScript(DdlTaskBO task) {
        OperDdlTaskInfoVO info = task.getOperDdlTaskInfoVO();
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE INDEX ")
          .append(info.getTargetName()).append("_idx ON ")
          .append(info.getTargetName()).append(" (")
          .append(info.getColumnNames().replace("&", ", "))
          .append(");");
        return sb.toString();
    }

    private String generateSequenceCreateScript(DdlTaskBO task) {
        OperDdlTaskInfoVO info = task.getOperDdlTaskInfoVO();
        return "CREATE SEQUENCE " + info.getTargetName() + "_seq;";
    }
}