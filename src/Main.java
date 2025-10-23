
void main() {

    String filepath = "members.txt";
    String ptLogPath = "pt_log.txt";

    ValidateData validate = new ValidateData();
    ReadFile rf = new ReadFile(validate, filepath);
    WriteFile wf = new WriteFile(validate, ptLogPath);
    UserInteraction ui = new UserInteraction(rf, wf);
    try {
        ui.displayPrompt();
    } catch (Exception e) {
        e.printStackTrace();
    }
}
