def call(String SonarQubeAPI, String Projectname, String ProjectKey) {
    // Jenkins में configure किया गया SonarQube Scanner tool
    def scannerHome = tool 'Sonar'

    // Jenkins में "Manage Jenkins → Configure System → SonarQube servers" में जो नाम दिया है वही यहाँ पास करें
    withSonarQubeEnv("${SonarQubeAPI}") {
        sh """
            ${scannerHome}/bin/sonar-scanner \
            -Dsonar.projectName=${Projectname} \
            -Dsonar.projectKey=${ProjectKey} \
            -Dsonar.sources=. \
            -Dsonar.host.url=${SONAR_HOST_URL} \
            -Dsonar.login=${SONAR_AUTH_TOKEN} \
            -X
        """
    }
}

