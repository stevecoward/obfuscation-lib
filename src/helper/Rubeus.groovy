package helper

import helper.ToolBase

class Rubeus extends ToolBase {
    static List<List<String>> strings = [
        [".cs", "Rubeus", "X9zB7aL2mQ8jH5wK"],
        [".cs", "ForgeTickets", "Y6kT1vW8nP2dQ4xJ"],
        [".cs", "EnumerateTickets", "M3pL9aR7fV2uZ5tB"],
        [".cs", "HarvestTicketGrantingTickets", "G1hJ4oW9kS3lE7nX"],
        [".cs", "ParseSaveTickets", "C5mN2rQ8vF6pL3zY"]
    ]
    static List<String> commands = ["klist", "triage"]

    Rubeus(String workspace, String projectName) {
        super(workspace, projectName, strings, commands)
    }

    @NonCPS
    def sanitize() {
        def assemblyInfoFile = new File("${this.workspace}\\${this.projectName}\\Properties\\AssemblyInfo.cs")
        def assemblyInfoText = assemblyInfoFile.text
        def newGUID = "[assembly: Guid(\"${UUID.randomUUID().toString()}\")]"

        assemblyInfoText = assemblyInfoText.replaceAll(/\[assembly:\sGuid.*/, newGUID)
        assemblyInfoText = assemblyInfoText.replaceAll(/(\[assembly:\sAssembly.*\(\").*/, '$1\")]')
        assemblyInfoText = assemblyInfoText.replaceAll(/\[assembly:\sAssemblyVersion.*/, "[assembly: AssemblyVersion(\"1.0.*\")]")
        assemblyInfoFile.write(assemblyInfoText)
    }

    @NonCPS
    def removeFunctions() {
        def infoFile = new File("${this.workspace}\\${this.projectName}\\Domain\\Info.cs")
        def infoFileText = infoFile.text

        infoFileText = infoFileText.replaceAll(/ShowLogo\(\)[\n\r\s]*\{[^}]*\}/, "ShowLogo() { }")
        infoFileText = infoFileText.replaceAll(/ShowUsage\(\)[\n\r\s]*\{[^}]*\}/, "ShowUsage() { }")
        infoFile.write(infoFileText)
    }
}