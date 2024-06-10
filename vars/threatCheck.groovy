def call(String threatCheckPath, String artifactPath) {
    def batchCommand = """
        @echo off
        ${threatCheckPath} -f ${artifactPath} | findstr /C:"Identified end of bad bytes at offset" || exit 0
    """
    try {
        def batOutput = bat(returnStdout: true, script: batchCommand)
        if (batOutput.contains("Identified end of bad bytes at offset")) {
            throw new Exception("Threat found!")
        }
    } catch (Exception e) {
        unstable(message: "${env.PROJECT_NAME} is unstable: ${e.message}")
    }
}