
def call(Map config = [:]) {
    // Default values
    def branch = config.branch ?: 'main'
    def message = config.message ?: 'Automated update from Jenkins'
    def repoUrl = config.repoUrl ?: error("repoUrl is required")

    echo "Configuring Git user..."
    sh """
        git config --global user.name "Jenkins"
        git config --global user.email "jenkins@server"
    """

    echo "Checking out branch '${branch}'..."
    sh "git checkout ${branch} || git checkout -b ${branch} origin/${branch}"

    echo "Fetching remote changes and rebasing..."
    sh """
        git fetch origin ${branch}
        git rebase origin/${branch} || git rebase --abort
    """

    echo "Adding all changes..."
    sh "git add ."

    echo "Committing changes..."
    sh "git commit -m '${message}' || echo 'No changes to commit'"

    echo "Pushing changes to GitHub..."
    sh "git push https://${GIT_USERNAME}:${GIT_PASSWORD}@${repoUrl} ${branch}"
    
    echo "Git safe push completed successfully!"
}
