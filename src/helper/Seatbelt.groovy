package helper

class Seatbelt extends ToolBase {
    static List<List<String>> strings = [
        [".cs", "Seatbelt", "X9zB7aL2mQ8jH5wK"],
        [".cs", "private const string Version = \"1.2.2\";", ""],
    ]
    static List<String> commands = ["AntiVirus", "DotNet"]

    Seatbelt(String workspace, String projectName) {
        super(workspace, projectName, strings, commands)
    }

    @NonCPS
    def removeFunctions() {
        def infoFile = new File("${this.workspace}\\${this.projectName}\\Seatbelt.cs")
        def infoFileText = infoFile.text

        infoFileText = infoFileText.replaceAll(/PrintLogo\(\)[\n\r\s]*\{(?:[^{}]*|\{(?:[^{}]*|\{[^{}]*\})*\})*\}/, "PrintLogo() { }")
        infoFile.write(infoFileText)
    }
}