@NonCPS
def call() {
    replaceStrings();
    sanitizeAssemblyInfo();
    removeFunctions();
}

@NonCPS
def replaceStrings() {
    def tool
    
    switch(PROJECT_NAME) {
        case "Rubeus":
            tool = new helper.Rubeus(WORKSPACE, PROJECT_NAME)
            break
        case "Seatbelt":
            tool = new helper.Seatbelt(WORKSPACE, PROJECT_NAME)
            break
        default:
             println("Skipped replaceStrings for ${PROJECT_NAME}.")
             return
    }

    tool.replaceStrings()
}

@NonCPS
def sanitizeAssemblyInfo() {
    def tool
    
    switch(PROJECT_NAME) {
        case "Rubeus":
            tool = new helper.Rubeus(WORKSPACE, PROJECT_NAME)
            break
        default:
            println("Skipped sanitizeAssemblyInfo for ${PROJECT_NAME}.")
            return
    }

    tool.sanitize()
}

@NonCPS
def removeFunctions() {
    def tool

    switch(PROJECT_NAME) {
        case "Rubeus":
            tool = new helper.Rubeus(WORKSPACE, PROJECT_NAME)
            break
        case "Seatbelt":
            tool = new helper.Seatbelt(WORKSPACE, PROJECT_NAME)
            break
        default:
            println("Skipped removeFunctions for ${PROJECT_NAME}.")
            return
    }

    tool.removeFunctions()
}