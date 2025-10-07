def call(String SonarQubeAPI, String Projectname, String ProjectKey) {
    // this defines the Sonar Scanner path inside the function itself
    def scannerHome = tool 'Sonar'

    withSonarQubeEnv("${SonarQubeAPI}") {
        sh """
            ${scannerHome}/bin/sonar-scanner \
            -Dsonar.projectName=${Projectname} \
            -Dsonar.projectKey=${ProjectKey} \
            -Dsonar.sources=. \
            -X
        """
    }
}
