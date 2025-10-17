def call(String message = "Updated environment variables") {
    withCredentials([usernamePassword(credentialsId: 'github-cred', passwordVariable: 'GIT_PASSWORD', usernameVariable: 'GIT_USERNAME')]) {
        sh """
            git config --global user.name "Jenkins"
            git config --global user.email "jenkins@server"
            git checkout main || git checkout -b main origin/main
            git add .
            git commit -m "${message}"
            git push https://${GIT_USERNAME}:${GIT_PASSWORD}@github.com/LondheShubham153/Wanderlust-Mega-Project.git main
        """
    }
}
