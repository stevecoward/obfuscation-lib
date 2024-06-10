package helper

class ToolBase {
    public String workspace = "";
    public String projectName = "";
    public List<List<String>> strings = []
    public List<String> commands = []

    ToolBase(String workspace, String projectName, List<List<String>> strings, List<String> commands) {
        this.workspace = workspace
        this.projectName = projectName
        this.strings = strings
        this.commands = commands
    }

    @NonCPS
    def replaceStrings() {
        def currentDir = new File("${workspace}")
        def fileText

        strings.each { itemList ->
            currentDir.eachFileRecurse({
                file ->
                if (file.name.endsWith(itemList[0])) {
                    fileText = file.text
                    fileText = fileText.replaceAll(itemList[1], itemList[2])
                    file.write(fileText)
                }
            })
        }
    }

    def executeCommands(script, String confusedBasePath) {
        try {
            commands.each { command ->
                script.bat """${confusedBasePath}\\${projectName}.exe ${command}"""
            }
        } catch (Exception e) {
            script.error "Failed to execute command"
            throw e
        }
    }
}