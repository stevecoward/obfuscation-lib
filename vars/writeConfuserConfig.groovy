def call(String confusedBasePath, String projectBasePath, String projectName, String confuserTemplatePath, String confuserProjectTemplatePath) {
    powershell """
    New-Item -Type Directory -Path ${confusedBasePath} -ErrorAction SilentlyContinue

    # Define the strings to replace
    \$replacementPairs = @{
        'BASE_DIR' = '${projectBasePath}'
        'OUTPUT_DIR' = '${confusedBasePath}'
        'PROJECT_NAME' = '${projectName}.exe'
    }

    # Define the path to the file
    \$filePath = "${confuserTemplatePath}"

    # Read the content of the file
    \$content = Get-Content -Path \$filePath -Raw

    # Replace the strings
    foreach (\$key in \$replacementPairs.Keys) {
        \$content = \$content -replace [regex]::Escape(\$key), \$replacementPairs[\$key]
    }

    # Write the modified content back to the file
    \$content | Set-Content -Path ${confuserProjectTemplatePath}
    """
}