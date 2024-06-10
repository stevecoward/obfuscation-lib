@NonCPS
def call(String basePath) {
    executeCommands(basePath);
}

@NonCPS
def executeCommands(String basePath) {
    def tool

    switch(PROJECT_NAME) {
        case "Rubeus":
            tool = new helper.Rubeus(WORKSPACE, PROJECT_NAME)
            break
        case "Seatbelt":
            tool = new helper.Seatbelt(WORKSPACE, PROJECT_NAME)
            break
        default:
            println("Unknown project: ${PROJECT_NAME}.")
            return
    }

    tool.executeCommands(this, basePath)
}